import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "wellUid",
        "wellboreUid",
        "sensorUid",
        "sensorPath",
        "bitOffset",
        "type",
        "logData"
})
public class LogRaw {

    @JsonProperty("wellUid")
    private String wellUid;
    @JsonProperty("wellboreUid")
    private String wellboreUid;
    @JsonProperty("sensorUid")
    private String sensorUid;
    @JsonProperty("sensorPath")
    private String sensorPath;
    @JsonProperty("bitOffset")
    private BitOffset bitOffset;
    @JsonProperty("type")
    private String type;
    @JsonProperty("logData")
    private LogData logData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("wellUid")
    public String getWellUid() {
        return wellUid;
    }

    @JsonProperty("wellUid")
    public void setWellUid(String wellUid) {
        this.wellUid = wellUid;
    }

    @JsonProperty("wellboreUid")
    public String getWellboreUid() {
        return wellboreUid;
    }

    @JsonProperty("wellboreUid")
    public void setWellboreUid(String wellboreUid) {
        this.wellboreUid = wellboreUid;
    }

    @JsonProperty("sensorUid")
    public String getSensorUid() {
        return sensorUid;
    }

    @JsonProperty("sensorUid")
    public void setSensorUid(String sensorUid) {
        this.sensorUid = sensorUid;
    }

    @JsonProperty("sensorPath")
    public String getSensorPath() {
        return sensorPath;
    }

    @JsonProperty("sensorPath")
    public void setSensorPath(String sensorPath) {
        this.sensorPath = sensorPath;
    }

    @JsonProperty("bitOffset")
    public BitOffset getBitOffset() {
        return bitOffset;
    }

    @JsonProperty("bitOffset")
    public void setBitOffset(BitOffset bitOffset) {
        this.bitOffset = bitOffset;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("logData")
    public LogData getLogData() {
        return logData;
    }

    @JsonProperty("logData")
    public void setLogData(LogData logData) {
        this.logData = logData;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}