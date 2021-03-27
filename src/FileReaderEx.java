import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderEx {
//
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String outputFileName = "Output.txt";
        File inputFileName = new File(scanner.nextLine());
        ArrayList<DayIndicators> arrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName.getName()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("2021")) {
                    DayIndicators newDay = new DayIndicators();
                    newDay.setDayTime(line.substring(0, 13));
                    newDay.Initil(line, 14);
                    arrayList.add(newDay);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DayIndicators.maxTemperature(arrayList, outputFileName);
        DayIndicators.miniHumidity(arrayList, outputFileName);
        DayIndicators.maxWindSpeed(arrayList, outputFileName);
        DayIndicators.mostWindDirection(arrayList, outputFileName);

    }
}
