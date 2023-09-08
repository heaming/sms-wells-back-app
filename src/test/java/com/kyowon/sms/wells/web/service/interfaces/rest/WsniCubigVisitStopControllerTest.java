package com.kyowon.sms.wells.web.service.interfaces.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCubigVisitStopDto;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_WSVI1007.request.WsniCubigVisitStopReqIvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_WSVI1007.response.WsniCubigVisitStopResIvo;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.common.service.EaiInterfaceService;
import com.sds.sflex.system.config.test.SpringTestSupport;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
* W-SV-I-0008 Cubig CC 방문중지 등록(팝업)
* */
@Slf4j
@RequiredArgsConstructor
@TestPropertySource(properties = {"spring.profiles.active=local", "active.profiles=local"})
class WsniCubigVisitStopControllerTest extends SpringTestSupport {

    private final EaiInterfaceService interfaceService;

    @Test
    @DisplayName("W-SV-I-0008 Cubig CC 방문중지 등록(팝업)")
    void createCubigVisitStop() throws Exception {

        //        WsniCubigVisitStopDto.CreateReq req = WsniCubigVisitStopDto.CreateReq.builder()
        //            .cntrNo("W20191055379")
        //            .cntrSn("1")
        //            .sppStpDvCd("B")
        //            .tn1StpYm("999912")
        //            .ogTpCd("W06")
        //            .prtnrNo("37209")
        //            .build();

        WsniCubigVisitStopDto.CreateReq req = new WsniCubigVisitStopDto.CreateReq(
            "W20191055379", "1", "", "B",
            "999912", "", "", "", "", "W06", "37209"
        );

        EaiWrapper<WsniCubigVisitStopDto.CreateReq> dto = new EaiWrapper<WsniCubigVisitStopDto.CreateReq>(req);

        // when & then
        MockHttpServletRequestBuilder request = post(SnServiceConst.REST_INTERFACE_URL_V1 + "/visit-stops")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk());
    }

    @Test
    void EAI_WSVI1007() throws Exception {

        WsniCubigVisitStopReqIvo req = new WsniCubigVisitStopReqIvo();
        req.setCntrNo("W20191055379");
        req.setCntrSn("1");
        req.setSppStpDvCd("B");
        req.setTn1StpYm("999912");
        req.setOgTpCd("W06");
        req.setPrtnrNo("37209");

        WsniCubigVisitStopResIvo res = interfaceService
            .post("/W/SV/EAI_WSVI1007/req", req, WsniCubigVisitStopResIvo.class);
        log.debug(res.getResultCode());
    }
}
