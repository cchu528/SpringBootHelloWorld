package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "private")
public class RunningInformation {

    public enum HealthWarningLevel {
        LOW, NORMAL, HIGH;

        public static HealthWarningLevel getHealthWarningLevel(int heartRate) {
            if (heartRate > 120) {
                return HealthWarningLevel.HIGH;
            } else if (heartRate > 75) {
                return HealthWarningLevel.NORMAL;
            } else if (heartRate >= 60) {
                return HealthWarningLevel.LOW;
            } else {
                // option 1: DANGER
                // option 2: Intentionally left blank
                // option 3: Exception
                // option 4: Print warning
                return null;
            }
        }
    }

    @Id
    @GeneratedValue
    private Long Id;

    private String runningId;
    private double latitude;
    private double longitude;
    private double runningDistance;
    private double totalRunningTime;
    private int heartRate;
    private HealthWarningLevel healthWarningLevel;
    private Date Timestamp = new Date();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "user_name")),
            @AttributeOverride(name = "address", column = @Column(name = "user_address"))
    })
    private final UserInfo userInfo;

    public RunningInformation () {
        this.userInfo = null;
    }

    public RunningInformation(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public RunningInformation (String username, String address) {
        this.userInfo = new UserInfo(username, address);
    }

    @JsonCreator
    public RunningInformation(@JsonProperty("userInfo") UserInfo userInfo,
                              @JsonProperty("runningId") String runningId,
                              @JsonProperty("latitude") String latitude,
                              @JsonProperty("longitude") String longitude,
                              @JsonProperty("runningDistance") String runningDistance,
                              @JsonProperty("totalRunningTime") String totalRunningTime,
                              @JsonProperty("heartRate") int heartRate,
                              @JsonProperty("timestamp") String timestamp) {

        this.userInfo = userInfo;
        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.Timestamp = new Date();
        this.setHeartRate(getRandomHeartRate(60, 200));

        System.out.println(this.heartRate);
    }

    @JsonIgnore
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getUserName() {
        return this.userInfo == null ? null : this.userInfo.getUsername();
    }

    public String getAddress() {
        return this.userInfo == null ? null : this.userInfo.getAddress();
    }

    @JsonIgnore
    public double getLatitude() {
        return latitude;
    }

    @JsonIgnore
    public double getLongitude() {
        return longitude;
    }

    @JsonIgnore
    public double getRunningDistance() {
        return runningDistance;
    }

    @JsonIgnore
    public Date getTimestamp() {
        return Timestamp;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
        this.healthWarningLevel = HealthWarningLevel.getHealthWarningLevel(this.heartRate);
    }

    public void setHealthWarningLevel(HealthWarningLevel healthWarningLevel) {

    }

    private int getRandomHeartRate(int min, int max) {
        Random rn = new Random();
        return min + rn.nextInt(max -min + 1);
    }
}
