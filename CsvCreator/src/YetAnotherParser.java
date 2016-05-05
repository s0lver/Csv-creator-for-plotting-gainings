import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class YetAnotherParser {
    private String lineToParse;
    private boolean isFromLocalPhone;

    private static final short COLUMN_LATITUDE = 1;
    private static final short COLUMN_LONGITUDE = 2;
    private static final short COLUMN_ALTITUDE = 3;
    private static final short COLUMN_ACCURACY = 4;
    private static final short COLUMN_SPEED = 5;
    private static final short COLUMN_TIMESTAMP = 6;
    private static final short COLUMN_BATTERY_LEVEL = 7;
    private static final short COLUMN_BATTERY_VOLTAGE = 8;
    private static final short COLUMN_BATTERY_STATUS = 9;
    private static final short COLUMN_BATTERY_TEMPERATURE = 10;
    private static final short COLUMN_BATTERY_CONNECTED = 11;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);


    public YetAnotherParser(String lineToParse, boolean isFromLocalPhone) {
        this.lineToParse = lineToParse;
        this.isFromLocalPhone = isFromLocalPhone;
    }

    public RowInFile parseLine() throws ParseException {
        // We do not care if it was obtained or not, we care about battery level
        String[] lineDivided = lineToParse.split(",");
        double latitude = Double.valueOf(lineDivided[COLUMN_LATITUDE]);
        double longitude = Double.valueOf(lineDivided[COLUMN_LONGITUDE]);
        double altitude = Double.valueOf(lineDivided[COLUMN_ALTITUDE]);
        double accuracy = Double.valueOf(lineDivided[COLUMN_ACCURACY]);
        double speed = Double.valueOf(lineDivided[COLUMN_SPEED]);
        Date timestamp = sdf.parse(lineDivided[COLUMN_TIMESTAMP]);
        double batteryLevel = Double.valueOf(lineDivided[COLUMN_BATTERY_LEVEL]);
        int batteryVoltage = Integer.valueOf(lineDivided[COLUMN_BATTERY_VOLTAGE]);
        String batteryStatus = lineDivided[COLUMN_BATTERY_STATUS];
        int batteryTemperature = Integer.valueOf(lineDivided[COLUMN_BATTERY_TEMPERATURE]);
        String batteryConnected = lineDivided[COLUMN_BATTERY_CONNECTED];

        return new RowInFile(isFromLocalPhone, latitude, longitude, altitude, accuracy, speed,
                timestamp, batteryLevel, batteryVoltage, batteryStatus, batteryTemperature, batteryConnected);

    }
}
