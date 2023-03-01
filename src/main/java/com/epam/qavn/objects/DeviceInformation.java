package com.epam.qavn.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@JsonPropertyOrder({"name", "avdName", "platformName", "platformVersion"})
public class DeviceInformation {

    private String name;
    private String avdName;
    private String platformName;
    private String platformVersion;

    public DeviceInformation(JsonObject jsonObject) {
        Gson gson = new Gson();
        this.name = gson.fromJson(jsonObject, DeviceInformation.class).getName();
        this.avdName = gson.fromJson(jsonObject, DeviceInformation.class).getAvdName();
        this.platformName = gson.fromJson(jsonObject, DeviceInformation.class).getPlatformName();
        this.platformVersion = gson.fromJson(jsonObject, DeviceInformation.class).getPlatformVersion();
    }

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getName() {
        return name;
    }

    @JsonProperty("avdName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getAvdName() {
        return avdName;
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
