package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_WSVI1007.request.WsniCubigVisitStopReqIvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_WSVI1007.response.WsniCubigVisitStopResIvo;
import com.sds.sflex.common.common.service.EaiInterfaceService;
import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

/**
* W-SV-I-0008 Cubig CC 방문중지 등록(팝업)
* */
@Slf4j
@RequiredArgsConstructor
@TestPropertySource(properties = {"spring.profiles.active=local", "active.profiles=local"})
class WsniCubigVisitStopControllerTest extends SpringTestSupport {

    private final EaiInterfaceService interfaceService;

    /*@Test
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

        Header haeder = new ModelMapper().map(Header.of(), Header.class);
        haeder.setErrOcYn("N");

        resEaiWrapper.setHeader(haeder);
        resEaiWrapper.setBody(dto);
        MockHttpServletRequestBuilder req = post(SnServiceConst.REST_INTERFACE_URL_V1 + "/visit-stops")
            .content(objectMapper.writeValueAsString(resEaiWrapper));

        mockMvc.perform(req)
            .andExpect(status().isOk());
    }*/

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
