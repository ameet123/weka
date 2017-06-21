import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "logRaw"
})
public class Body {

    @JsonProperty("logRaw")
    private LogRaw logRaw;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("logRaw")
    public LogRaw getLogRaw() {
        return logRaw;
    }

    @JsonProperty("logRaw")
    public void setLogRaw(LogRaw logRaw) {
        this.logRaw = logRaw;
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