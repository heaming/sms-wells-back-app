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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
* W-SV-I-0008 Cubig CC 방문중지 등록(팝업)
* */
@Slf4j
@RequiredArgsConstructor
@TestPropertySource(properties = {"spring.profiles.active=local,test", "active.profiles=local,test"})
class WsniCubigVisitStopControllerTest extends SpringTestSupport {

    @SpyBean
    private final EaiInterfaceService interfaceService;

    @Test
    void createCubigVisitStop() throws Exception {

        EaiWrapper<WsniCubigVisitStopDto.CreateReq> resEaiWrapper = new EaiWrapper<WsniCubigVisitStopDto.CreateReq>();
        WsniCubigVisitStopDto.CreateReq dto = WsniCubigVisitStopDto.CreateReq.builder()
            .cntrNo("W20191055379")
            .cntrSn("1")
            .sppStpDvCd("B")
            .tn1StpYm("999912")
            .ogTpCd("W06")
            .prtnrNo("37209")
            .build();

        resEaiWrapper.setBody(dto);

        MockHttpServletRequestBuilder req = post(SnServiceConst.REST_INTERFACE_URL_V1 + "/visit-stops")
            .content(objectMapper.writeValueAsString(resEaiWrapper));

        mockMvc.perform(req)
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
            .post("/W/SV/EAI_WSVI1007", req, WsniCubigVisitStopResIvo.class);
        log.debug(res.getResultCode());
    }
}
