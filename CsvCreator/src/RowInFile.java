import java.util.Comparator;
import java.util.Date;

public class RowInFile implements Comparable<RowInFile> {
    private boolean isFromLocalPhone;
    private double latitude;
    private double longitude;
    private double altitude;
    private double accuracy;
    private double speed;
    private Date timestamp;
    private double batteryLevel;
    private int batteryVoltage;
    private String batteryStatus;
    private int batteryTemperature;
    private String batteryConnected;

    public RowInFile(boolean isFromLocalPhone, double latitude, double longitude, double altitude, double accuracy, double speed, Date timestamp, double batteryLevel, int batteryVoltage, String batteryStatus, int batteryTemperature, String batteryConnected) {
        this.isFromLocalPhone = isFromLocalPhone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.accuracy = accuracy;
        this.speed = speed;
        this.timestamp = timestamp;
        this.batteryLevel = batteryLevel;
        this.batteryVoltage = batteryVoltage;
        this.batteryStatus = batteryStatus;
        this.batteryTemperature = batteryTemperature;
        this.batteryConnected = batteryConnected;
    }

    public boolean isFromLocalPhone() {
        return isFromLocalPhone;
    }

    public void setFromLocalPhone(boolean fromLocalPhone) {
        isFromLocalPhone = fromLocalPhone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(int batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public int getBatteryTemperature() {
        return batteryTemperature;
    }

    public void setBatteryTemperature(int batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
    }

    public String getBatteryConnected() {
        return batteryConnected;
    }

    public void setBatteryConnected(String batteryConnected) {
        this.batteryConnected = batteryConnected;
    }

    @Override
    public String toString() {
        return isFromLocalPhone + "," +
                latitude + "," +
                longitude + "," +
                altitude + "," +
                accuracy + "," +
                speed + "," +
                timestamp + "," +
                batteryLevel + "," +
                batteryVoltage + "," +
                batteryStatus + "," +
                batteryTemperature + "," +
                batteryConnected;

    }

    @Override
    public int compareTo(RowInFile rowToCompare) {
        Date timeToCompare = rowToCompare.getTimestamp();

        long comparison = this.getTimestamp().getTime() - timeToCompare.getTime();
        if (comparison > 0) return 1;
        else if (comparison < 0) return -1;
        else return 0;
    }

    public static Comparator<RowInFile> RowsComparator = new Comparator<RowInFile>() {

        @Override
        public int compare(RowInFile row1, RowInFile row2) {
            long comparison = row1.getTimestamp().getTime() - row2.getTimestamp().getTime();

            if (comparison > 0) {
                return 1;
            } else if (comparison < 0) {
                return -1;
            } else {
                return 0;
            }
        }

    };

}
