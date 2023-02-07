package com.epam.qavn.core.DeviceManager;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@JsonPropertyOrder({ "name", "platformName", "platformVersion"})
public class Device {

    private String name;
    private String platformName;
    private String platformVersion;

    public Device(JsonObject jsonObject) {
        Gson gson = new Gson();
        this.name = gson.fromJson(jsonObject, Device.class).getName();
        this.platformName = gson.fromJson(jsonObject, Device.class).getPlatformName();
        this.platformVersion = gson.fromJson(jsonObject, Device.class).getPlatformVersion();
    }

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getName() {
        return name;
    }

    @JsonProperty("platformName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getPlatformName() {
        return platformName;
    }

    @JsonProperty("platformVersion")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getPlatformVersion() {
        return platformVersion;
    }
}
