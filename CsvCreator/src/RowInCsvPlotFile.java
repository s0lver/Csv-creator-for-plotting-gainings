import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RowInCsvPlotFile {
    private Date timestamp;
    private double batteryLevelLocalSmartphone;
    private double batteryLevelRemoteSmartphone;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);

    public RowInCsvPlotFile(Date timestamp, double batteryLevelLocalSmartphone, double batteryLevelRemoteSmartphone) {
        this.timestamp = timestamp;
        this.batteryLevelLocalSmartphone = batteryLevelLocalSmartphone;
        this.batteryLevelRemoteSmartphone = batteryLevelRemoteSmartphone;
    }

    public String toCsvString(Date origin) {
        long deltaTimestamp = timestamp.getTime() - origin.getTime();
        double batteryGaining = (batteryLevelLocalSmartphone - batteryLevelRemoteSmartphone) / batteryLevelRemoteSmartphone;
        return sdf.format(timestamp) + "," +
                deltaTimestamp + "," +
                batteryLevelLocalSmartphone + "," +
                batteryLevelRemoteSmartphone + "," +
                batteryGaining
                ;
    }
}
