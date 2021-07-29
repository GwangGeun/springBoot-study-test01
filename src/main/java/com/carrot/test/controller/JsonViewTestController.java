package com.carrot.test.controller;

import com.carrot.test.config.DivideConfig;
import com.carrot.test.controller.dto.JsonViewTestDto01;
import com.carrot.test.controller.dto.JsonViewTestDto02;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jsonview")
public class JsonViewTestController {

    /**
     * JsonView Test
     */
    @GetMapping("/internal")
    @JsonView(DivideConfig.Internal.class)
    public JsonViewTestDto01 internal(){

        JsonViewTestDto01 jsonViewTestDto01 = new JsonViewTestDto01();
        jsonViewTestDto01.setCommon("common01");
        jsonViewTestDto01.setExternal("external01");
        jsonViewTestDto01.setInternal("internal01");
        jsonViewTestDto01.setIgnored("ignored01");

        JsonViewTestDto02 jsonViewTestDto02 = new JsonViewTestDto02();
        jsonViewTestDto02.setCommon2("common02");
        jsonViewTestDto02.setExternal2("external02");
        jsonViewTestDto02.setInternal2("internal02");
        jsonViewTestDto02.setIgnored2("ignored02");

        jsonViewTestDto01.setJsonViewTestDto02(jsonViewTestDto02);

        return jsonViewTestDto01;
    }

    /**
     * JsonView Test
     */
    @GetMapping("/external")
//    @JsonView(DivideConfig.External.class)
    public JsonViewTestDto01 external(){

        JsonViewTestDto01 jsonViewTestDto01 = new JsonViewTestDto01();
        jsonViewTestDto01.setCommon("common01");
        jsonViewTestDto01.setExternal("external01");
        jsonViewTestDto01.setInternal("internal01");
        jsonViewTestDto01.setIgnored("ignored01");

        JsonViewTestDto02 jsonViewTestDto02 = new JsonViewTestDto02();
        jsonViewTestDto02.setCommon2("common02");
        jsonViewTestDto02.setExternal2("external02");
        jsonViewTestDto02.setInternal2("internal02");
        jsonViewTestDto02.setIgnored2("ignored02");

        jsonViewTestDto01.setJsonViewTestDto02(jsonViewTestDto02);

        return jsonViewTestDto01;
    }

}
