import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DayIndicators {
    private String dayTime = "";
    private double temperature = 0;
    private double humidity = 0;
    private double windSpeed = 0;
    private double windDirection = 0;

    public DayIndicators(String dayTime, double temperature, double humidity, double windSpeed, double windDirection) {
        this.dayTime = dayTime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public DayIndicators() {
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public void Initil(String s, int i) {
        String ans = convertToDouble(s, i);
        this.setTemperature(Double.parseDouble(ans));
        i += ans.length();
        i++;
        ans = convertToDouble(s, i);
        this.setHumidity(Double.parseDouble(ans));
        i += ans.length();
        i++;
        ans = convertToDouble(s, i);
        this.setWindSpeed(Double.parseDouble(ans));
        i += ans.length();
        i++;
        ans = convertToDouble(s, i);
        this.setWindDirection(Double.parseDouble(ans));
        i += ans.length();
    }

    public String convertToDouble(String s, int i) {
        String temp = "";
        while (i < s.length() && s.charAt(i) != ',') {
            temp += s.charAt(i);
            i++;
        }
        return temp;
    }
//
    public static void maxTemperature(ArrayList<DayIndicators> arrayList, String outputFileName) {
        DayIndicators maxi = new DayIndicators("", -1000, 0, 0, 0);
        double avg = 0;
        int counter = 0;
        for (DayIndicators day : arrayList) {
            avg += day.getTemperature();
            counter++;
            day.setDayTime(day.convertTime(day.getDayTime()));
            if (day.getTemperature() > maxi.getTemperature()) {
                maxi = day;
            }
        }
        avg /= counter;
        String result = String.format("%.1f", maxi.getTemperature());
        String resultAVG = String.format("%.1f", avg);
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
            writter.write("Max temperature was in " + maxi.getDayTime() + " : " + result + " В°C" + '\n');
            writter.write("Average temperature was : " + resultAVG + " В°C" + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void miniHumidity(ArrayList<DayIndicators> arrayList, String outputFileName) {
        DayIndicators mini = new DayIndicators("", 0, 101, 0, 0);
        double avg = 0;
        int counter = 0;
        for (DayIndicators day : arrayList) {
            avg += day.getHumidity();
            counter++;
            if (day.getHumidity() < mini.getHumidity()) {
                mini = day;
            }
        }
        avg /= counter;
        String result = String.format("%.1f", mini.getHumidity());
        String resultAVG = String.format("%.1f", avg);
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName, true))) {
            writter.append("Minimum Humidity was in ").append(mini.getDayTime()).append(" : ").append(String.valueOf(result)).append((" %")).append(String.valueOf('\n'));
            writter.append("Average Humidity was : ").append(String.valueOf(resultAVG)).append(" %").append(String.valueOf('\n'));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void maxWindSpeed(ArrayList<DayIndicators> arrayList, String outputFileName) {
        DayIndicators maxi = new DayIndicators("", 0, 0, -1, 0);
        double avg = 0;
        int counter = 0;
        for (DayIndicators day : arrayList) {
            avg += day.getHumidity();
            counter++;
            if (day.getWindSpeed() > maxi.getWindSpeed()) {
                maxi = day;
            }
        }
        avg /= counter;
        String result = String.format("%.1f", maxi.getWindSpeed());
        String resultAVG = String.format("%.1f", avg);
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName, true))) {
            writter.append("Max Wind Speed was in ").append(maxi.getDayTime()).append(" : ").append(String.valueOf(result)).append(" km/h").append(String.valueOf('\n'));
            writter.append("Average Wind Speed was : ").append(String.valueOf(resultAVG)).append(" km/h").append(String.valueOf('\n'));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mostWindDirection(ArrayList<DayIndicators> arrayList, String outputFileName) {
        int west = 0;
        int east = 0;
        int north = 0;
        int south = 0;
        for (DayIndicators day : arrayList) {
            if (day.getWindDirection() > 315 && day.getWindDirection() < 45) {
                north++;
            }
            if (day.getWindDirection() > 45 && day.getWindDirection() < 135) {
                east++;
            }
            if (day.getWindDirection() > 135 && day.getWindDirection() < 225) {
                south++;
            }
            if (day.getWindDirection() > 225 && day.getWindDirection() < 315) {
                west++;
            }
        }
        String ans = "";
        if (Math.max(Math.max(Math.max(north, south), west), east) == north) {
            ans = "NORTH";
        }
        if (Math.max(Math.max(Math.max(north, south), west), east) == south) {
            ans = "SOUTH";
        }
        if (Math.max(Math.max(Math.max(north, south), west), east) == west) {
            ans = "WEST";
        }
        if (Math.max(Math.max(Math.max(north, south), west), east) == east) {
            ans = "EAST";
        }
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName, true))) {
            writter.append("Max Wind Direction was ").append(ans).append(String.valueOf('\n'));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertTime(String dayTime) {
        String year = dayTime.substring(0, 4);
        String day = dayTime.substring(6, 8);
        String month = dayTime.substring(4, 6);
        String hours = dayTime.substring(9, 11);
        String minutes = dayTime.substring(11, 13);
        return day + '/' + month + '/' + year + " " + "at time " + hours + "." + minutes;
    }
}
