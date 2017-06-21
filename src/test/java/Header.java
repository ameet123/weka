import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "correlationId",
        "originatingMachine",
        "originatingIP",
        "objectKlass",
        "operation",
        "timestamp"
})
public class Header {

    @JsonProperty("correlationId")
    private String correlationId;
    @JsonProperty("originatingMachine")
    private String originatingMachine;
    @JsonProperty("originatingIP")
    private String originatingIP;
    @JsonProperty("objectKlass")
    private String objectKlass;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("correlationId")
    public String getCorrelationId() {
        return correlationId;
    }

    @JsonProperty("correlationId")
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @JsonProperty("originatingMachine")
    public String getOriginatingMachine() {
        return originatingMachine;
    }

    @JsonProperty("originatingMachine")
    public void setOriginatingMachine(String originatingMachine) {
        this.originatingMachine = originatingMachine;
    }

    @JsonProperty("originatingIP")
    public String getOriginatingIP() {
        return originatingIP;
    }

    @JsonProperty("originatingIP")
    public void setOriginatingIP(String originatingIP) {
        this.originatingIP = originatingIP;
    }

    @JsonProperty("objectKlass")
    public String getObjectKlass() {
        return objectKlass;
    }

    @JsonProperty("objectKlass")
    public void setObjectKlass(String objectKlass) {
        this.objectKlass = objectKlass;
    }

    @JsonProperty("operation")
    public String getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(String operation) {
        this.operation = operation;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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