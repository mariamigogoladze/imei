import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLScriptFordaystat {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "q1w2e3r4";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/result.txt"), 1000 * 8192);
        String line;

        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
        ) {
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split("\\s+");
                if (arr.length < 1) {
                    continue;
                }
                for(int i = 0; i < 12; ++i){
                    if(arr[i].contains("\\N")){
                        arr[i] = null;
                    }
                }

                if(arr[12].contains("\\N")){
                    arr[12]="00:00:00";
                }

                String sql = "INSERT INTO daystat VALUES (" + arr[0] + ", " + arr[1] + ", " + arr[2] + "," + arr[3] + ", '" + arr[4] + "', " + arr[5] + ", "+arr[6]+", '"+arr[7]+"', "+arr[8]+", "+arr[9]+", "+arr[10]+", "+arr[11]+", null, '"+arr[12]+"')";
                stmt.executeUpdate(sql);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
