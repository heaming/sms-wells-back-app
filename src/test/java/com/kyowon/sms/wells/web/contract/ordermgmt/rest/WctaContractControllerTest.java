package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class WctaContractControllerTest extends SpringTestSupport {
    private static final String BASE_URL = CtContractConst.REST_URL_V1 + "/contracts";
    private final WctaContractService service;

    @Test
    void getConfirmMemberships() throws Exception {
        WctaContractDto.SearchConfirmMshReq dto = WctaContractDto.SearchConfirmMshReq.builder()
            .rcpStrtDt("20230101")
            .rcpEndDt("20231231")
            .cnfmStrtDt("20230401")
            .cnfmEndDt("20231231")
            .frisuMshCrtYn("N")
            .build();
        List<WctaContractDto.SearchConfirmMshRes> confirmMemberships = service.getConfirmMemberships(dto);

        MockHttpServletRequestBuilder req = get(BASE_URL + "/memberships")
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(req)
            .andExpect(status().isOk());
    }
}
