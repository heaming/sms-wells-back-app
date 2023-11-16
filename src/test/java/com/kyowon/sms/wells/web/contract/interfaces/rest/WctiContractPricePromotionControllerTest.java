package com.kyowon.sms.wells.web.contract.interfaces.rest;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractPricePromotionDto;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.test.SpringTestSupport;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WctiContractPricePromotionControllerTest extends SpringTestSupport {
    private final String BASE_URL = CtContractConst.INTERFACE_URL_V1;

    @Test
    @DisplayName("가격 조회")
    void getContractPricePromotionTest() throws Exception {
        WctiContractPricePromotionDto.FindReq req = WctiContractPricePromotionDto.FindReq.builder()
            .PRTNR_NO("1738565")
            .PRTNR_OG_CD("Q921080")
            .PD_CD("WP01120451")
            .STPL_PRD_CD(72)
            .USWY_DV("0")
            .VST_PRD("6")
            .RGST_COST(100000)
            .CNTR_PTRM(72)
            .DSC_DV_CD("8")
            .build();

        EaiWrapper<WctiContractPricePromotionDto.FindReq> dto = new EaiWrapper<>(req);


        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/contract-price-promotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.CHK_RS").value("Y"))
            .andExpect(jsonPath("$.body.RENTAL_AMT_1").value(34900))
        ;
    }
}
