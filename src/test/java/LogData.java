import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "variableList",
        "unitList",
        "unitTypeList",
        "dataTypeList",
        "data"
})
public class LogData {

    @JsonProperty("variableList")
    private List<String> variableList = null;
    @JsonProperty("unitList")
    private List<String> unitList = null;
    @JsonProperty("unitTypeList")
    private List<String> unitTypeList = null;
    @JsonProperty("dataTypeList")
    private List<String> dataTypeList = null;
    @JsonProperty("data")
    private List<List<Object>> data = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("variableList")
    public List<String> getVariableList() {
        return variableList;
    }

    @JsonProperty("variableList")
    public void setVariableList(List<String> variableList) {
        this.variableList = variableList;
    }

    @JsonProperty("unitList")
    public List<String> getUnitList() {
        return unitList;
    }

    @JsonProperty("unitList")
    public void setUnitList(List<String> unitList) {
        this.unitList = unitList;
    }

    @JsonProperty("unitTypeList")
    public List<String> getUnitTypeList() {
        return unitTypeList;
    }

    @JsonProperty("unitTypeList")
    public void setUnitTypeList(List<String> unitTypeList) {
        this.unitTypeList = unitTypeList;
    }

    @JsonProperty("dataTypeList")
    public List<String> getDataTypeList() {
        return dataTypeList;
    }

    @JsonProperty("dataTypeList")
    public void setDataTypeList(List<String> dataTypeList) {
        this.dataTypeList = dataTypeList;
    }

    @JsonProperty("data")
    public List<List<Object>> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<List<Object>> data) {
        this.data = data;
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