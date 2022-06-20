import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws IOException {
        Set<String> imeis = new HashSet<>();
        Set<String> ids = new HashSet<>();

        BufferedReader imeiReader = new BufferedReader(new FileReader("src/main/resources/Untitled.csv"), 1000 * 8192);
        BufferedReader imeiReader7846 = new BufferedReader(new FileReader("src/main/resources/7846.dat"), 1000 * 8192);
        BufferedReader imeiReader7845 = new BufferedReader(new FileReader("src/main/resources/7845.dat"), 1000 * 8192);

        BufferedWriter printWriterPreR = new BufferedWriter(new FileWriter("src/main/resources/preResult.txt"), 32768);
        BufferedWriter printWriterR = new BufferedWriter(new FileWriter("src/main/resources/result.txt"), 32768);

        String line;
        while ((line = imeiReader.readLine()) != null) {
            imeis.add(line.trim());
        }

        while ((line = imeiReader7846.readLine()) != null) {
            String[] arr = line.split("\\s+");
            if (arr.length < 2) {
                continue;
            }
            String imei = arr[1];
            if (imeis.contains(imei)) {
                line += "\n";
                printWriterPreR.write(line);
                ids.add(arr[0]);
            }
        }

        while ((line = imeiReader7845.readLine()) != null) {
            String[] arr = line.split("\\s+");
            if (arr.length < 1) {
                continue;
            }
            String id = arr[0];
            if (ids.contains(id)) {
                line += "\n";
                printWriterR.write(line);
            }
        }

        printWriterPreR.close();
        printWriterR.close();
    }
}
