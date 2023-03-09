package com.kyowon.sms.wells.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchCompanyCodeReq;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import com.sds.sflex.system.test.SpringTestSupport;

class WwdbIntegrationDepositInterfaceControllerTest extends SpringTestSupport {

    private static final String BASE_URL = WdWithdrawalConst.INTERFACE_URL_V1 + "/integration-deposit";
    
    @Test
    @DisplayName("공통 통합입금 내역 회사선택 코드 조회")
    void test() throws Exception {
        String LNG_ID = "ko";
        
        SearchCompanyCodeReq req = new SearchCompanyCodeReq(LNG_ID);
        EaiWrapper<SearchCompanyCodeReq> dto = new EaiWrapper(req);
        
        MockHttpServletRequestBuilder request = post(BASE_URL + "/company-codes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto));
        
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
        
    }

}
