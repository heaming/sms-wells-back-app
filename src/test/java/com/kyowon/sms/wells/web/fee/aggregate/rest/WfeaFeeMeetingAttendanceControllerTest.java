package com.kyowon.sms.wells.web.fee.aggregate.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto.SaveReq;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.test.SpringTestSupport;

class WfeaFeeMeetingAttendanceControllerTest extends SpringTestSupport {

    private static final String BASE_URL = CtFeeConst.REST_URL_V1 + "/fee-meeting-attendances";

    @Commit
    @Test
    @DisplayName("[P조직] 미팅참석자 실적 생성")
    void saveWellsFeeMeetingAttendances() throws Exception {
        // given
        SaveReq dto1 = new SaveReq("W01", "202308", "W0104", "02");

        // when & then
        MockHttpServletRequestBuilder request1 = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto1));

        mockMvc.perform(request1)
            .andExpect(status().isOk());

        //        // given
        //        SaveReq dto2 = new SaveReq("W01", "202308", "W0105", "02");
        //
        //        // when & then
        //        MockHttpServletRequestBuilder request2 = post(BASE_URL)
        //            .contentType(MediaType.APPLICATION_JSON)
        //            .content(objectMapper.writeValueAsString(dto2));
        //
        //        mockMvc.perform(request2)
        //            .andExpect(status().isOk());
    }

    @Commit
    @Test
    @DisplayName("[M조직] 미팅참석자 실적 생성")
    void saveWellsFeeMeetingAttendancesForManager() throws Exception {
        // given
        SaveReq dto1 = new SaveReq("W02", "202308", "W0204", "02");

        // when & then
        MockHttpServletRequestBuilder request1 = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto1));

        mockMvc.perform(request1)
            .andExpect(status().isOk());

        // given
        SaveReq dto2 = new SaveReq("W02", "202308", "W0205", "02");

        // when & then
        MockHttpServletRequestBuilder request2 = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto1));

        mockMvc.perform(request2)
            .andExpect(status().isOk());
    }

    @Commit
    @Test
    @DisplayName("[홈마스터] 미팅참석자 실적 생성")
    void saveWellsFeeMeetingAttendancesForHomemaster() throws Exception {
        // given
        SaveReq dto1 = new SaveReq("W03", "202308", "", "02");

        // when & then
        MockHttpServletRequestBuilder request1 = post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto1));

        mockMvc.perform(request1)
            .andExpect(status().isOk());
    }
}
