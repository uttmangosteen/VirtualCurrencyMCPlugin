package io.github.uttmangosteen.virtualCurrencyMCPlugin;

import org.intellij.lang.annotations.Language;

import java.sql.*;

public class MySQL {
    private Connection connection;
    private final String host;
    private final String port;
    private final String user;
    private final String pass;
    private final String dbname;

    public MySQL() {
        host = Global.config.getString("mysql.host", "localhost");
        port = Global.config.getString("mysql.port", "3306");
        user = Global.config.getString("mysql.user", "root");
        pass = Global.config.getString("mysql.pass", "");
        dbname = Global.config.getString("mysql.dbname", "virtual_currency_database");

        if (!connect()) {
            Main.plugin.getLogger().severe("DB接続失敗");
            Global.isRunning = false;
            return;
        }
        if (!isConnected()) {
            Main.plugin.getLogger().severe("DBを開けません");
            Global.isRunning = false;
            return;
        }
        if (createTables()) {
            Main.plugin.getLogger().info("DB接続成功");
            Global.isRunning = true;
        } else {
            Main.plugin.getLogger().severe("テーブル作成失敗");
            Global.isRunning = false;
        }
    }

    private boolean connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname
                    + "?autoReconnect=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            connection = DriverManager.getConnection(url, user, pass);
            Main.plugin.getLogger().info("DBに接続しました: " + url);
            return true;
        } catch (ClassNotFoundException e) {
            Main.plugin.getLogger().severe("MySQLドライバーが見つかりません: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            Main.plugin.getLogger().severe("データベース接続エラー: " + e.getMessage());
            return false;
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            Main.plugin.getLogger().warning("接続状態チェックエラー: " + e.getMessage());
            return false;
        }
    }

    public void connectionClose() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Main.plugin.getLogger().info("DB接続を解除");
            }
        } catch (SQLException e) {
            Main.plugin.getLogger().severe("接続切断エラー: " + e.getMessage());
        }
    }

    private boolean createTables() {
        try {
            @Language("MySQL") String transactions = """
                    CREATE TABLE IF NOT EXISTS transactions (
                        id BIGINT PRIMARY KEY,
                        block_id BIGINT NOT NULL,
                        timestamp BIGINT NOT NULL,
                        sender_uuid VARCHAR(36) NOT NULL,
                        sender_name VARCHAR(16) NOT NULL,
                        receiver_uuid VARCHAR(36) NOT NULL,
                        receiver_name VARCHAR(16) NOT NULL,
                        amount BIGINT NOT NULL,
                        fee BIGINT NOT NULL,
                        FOREIGN KEY (block_id) REFERENCES blocks(id)
                    )
                    """;
            execute(transactions);

            @Language("MySQL") String blocks = """
                    CREATE TABLE IF NOT EXISTS blocks (
                        id BIGINT PRIMARY KEY,
                        timestamp BIGINT NOT NULL,
                        nonce BIGINT NOT NULL,
                        previous_hash BINARY(32) NOT NULL,
                        hash BINARY(32) NOT NULL
                    )
                    """;
            execute(blocks);

            @Language("MySQL") String player_balance = """
                    CREATE TABLE IF NOT EXISTS player_balance (
                        uuid VARCHAR(36) PRIMARY KEY,
                        name VARCHAR(16) NOT NULL,
                        balance BIGINT NOT NULL DEFAULT 0
                    )
                    """;
            execute(player_balance);

            Main.plugin.getLogger().info("データベーステーブルを作成/確認しました");
            return true;
        } catch (SQLException e) {
            Main.plugin.getLogger().severe("テーブル作成エラー: " + e.getMessage());
            return false;
        }
    }

    private void execute(String sql) throws SQLException {
        if (!isConnected()) throw new SQLException("データベースに接続されていません");
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
}
