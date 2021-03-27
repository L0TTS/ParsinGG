import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWritterMang {

    public static void main(String[] args) {
        String outputFileName = "Output.txt";
        String[] array = { "one", "two", "three", "four" };

        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String value : array) {
                writter.write(value + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}