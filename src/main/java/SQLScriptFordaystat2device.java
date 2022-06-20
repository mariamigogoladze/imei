import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLScriptFordaystat2device {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "q1w2e3r4";

    public static void main(String[] args) throws IOException {
        BufferedReader preReader = new BufferedReader(new FileReader("src/main/resources/preResult.txt"), 1000 * 8192);
        String line;

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
        ) {
            while ((line = preReader.readLine()) != null) {
                String[] arr = line.split("\\s+");
                if (arr.length < 2) {
                    continue;
                }
                String sql = "INSERT INTO daystat2device VALUES (" + arr[0] + ", " + arr[1] + ", '" + arr[2] + "')";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
