package plot.individual;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CsvPlotIndividualExperimentRecord {
    private Date timestamp;
    private double batteryLevelLocalSmartphone;
    private double batteryLevelRemoteSmartphone;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);

    public CsvPlotIndividualExperimentRecord(Date timestamp, double batteryLevelLocalSmartphone, double batteryLevelRemoteSmartphone) {
        this.timestamp = timestamp;
        this.batteryLevelLocalSmartphone = batteryLevelLocalSmartphone;
        this.batteryLevelRemoteSmartphone = batteryLevelRemoteSmartphone;
    }

    public String toCsvString(Date origin) {
        long deltaTimestamp = getDeltaTimestamp(origin);
        double batteryGaining = getBatteryGaining();
        return sdf.format(timestamp) + "," +
                deltaTimestamp + "," +
                batteryLevelLocalSmartphone + "," +
                batteryLevelRemoteSmartphone + "," +
                batteryGaining;
    }

    protected double getBatteryGaining() {
        return (batteryLevelLocalSmartphone - batteryLevelRemoteSmartphone) / batteryLevelRemoteSmartphone;
    }

    protected long getDeltaTimestamp(Date origin) {
        return (timestamp.getTime() - origin.getTime()) / 1000;
    }

    public double getBatteryLevelLocalSmartphone() {
        return batteryLevelLocalSmartphone;
    }

    public double getBatteryLevelRemoteSmartphone() {
        return batteryLevelRemoteSmartphone;
    }
}
