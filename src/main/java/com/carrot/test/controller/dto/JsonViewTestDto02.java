package com.carrot.test.controller.dto;

import com.carrot.test.config.DivideConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class JsonViewTestDto02 {
    @JsonView(DivideConfig.External.class)
    private String external2;
    @JsonView(DivideConfig.Internal.class)
    private String internal2;
    @JsonView({DivideConfig.Internal.class, DivideConfig.External.class})
    private String common2;
    @JsonIgnore
    private String ignored2;
}
