package com.tsoft.bot.frontend.utility.services;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfigurations {

    private Map<String, String> headerOptions = new HashMap<>();

    public HeaderConfigurations setHeader(String key, String value){
        this.headerOptions.put(key, value);
        return this;
    }

    public HeaderConfigurations setContentType(String contentType) {
        headerOptions.remove("Content-type");
        if (!StringUtils.isEmpty(contentType)) {
            headerOptions.put("Content-type","application/JSON");
        }
        return this;
    }

    public Map<String, String> asMap(){
        return this.headerOptions;
    }
}
