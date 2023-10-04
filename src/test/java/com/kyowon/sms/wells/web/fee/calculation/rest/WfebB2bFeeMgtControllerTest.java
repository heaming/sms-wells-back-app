package com.kyowon.sms.wells.web.fee.calculation.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebB2bFeeMgtDto.CreateReq;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.test.SpringTestSupport;

class WfebB2bFeeMgtControllerTest extends SpringTestSupport {

    private static final String BASE_URL = CtFeeConst.REST_URL_V1 + "/b2b";

    @Commit
    @Test
    @DisplayName("[B2B] B2B 실적생성")
    void aggregateB2bPerformance() throws Exception {
        CreateReq dto = new CreateReq("202308", "02");

        MockHttpServletRequestBuilder request = post(String.format("%s/aggregate", BASE_URL))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk());
    }
}
