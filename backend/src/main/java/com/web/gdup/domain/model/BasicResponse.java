package com.web.gdup.domain.model;

import io.swagger.annotations.ApiModelProperty;

public class BasicResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "message", position = 2)
    public String message;
    @ApiModelProperty(value = "data", position = 3)
    public Object data;
}