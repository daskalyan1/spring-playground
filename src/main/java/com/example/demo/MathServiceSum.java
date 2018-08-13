package com.example.demo;

import org.springframework.util.MultiValueMap;

import java.util.List;

public class MathServiceSum {

    private MultiValueMap<String, String> queryParam;

    public MultiValueMap<String, String> getQueryParam() {
        return queryParam;
    }
    public void setQueryParam(MultiValueMap<String, String> x){
        this.queryParam = x;
    }
}
