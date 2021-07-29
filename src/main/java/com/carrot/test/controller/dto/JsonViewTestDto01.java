package com.carrot.test.controller.dto;

import com.carrot.test.config.DivideConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class JsonViewTestDto01 {
    @JsonView(DivideConfig.External.class)
    private String external;
    @JsonView(DivideConfig.Internal.class)
    private String internal;
    @JsonView({DivideConfig.Internal.class, DivideConfig.External.class})
    private String common;
    @JsonIgnore
    private String ignored;
    @JsonView(DivideConfig.Internal.class)
    private JsonViewTestDto02 jsonViewTestDto02;
}
