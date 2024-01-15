package com.kyowon.sms.wells.web.contract.interfaces.rest;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentReq;
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

class WctiContractCreateInterfaceControllerTest extends SpringTestSupport {
    private final String BASE_URL = CtContractConst.INTERFACE_URL_V1;

    /*
        -- SAMPLE DATA 추출
        SELECT C1.CNTR_NO
             , C1.CNTR_CST_NO
             , C1.COPN_DV_CD
             , C1.SELL_INFLW_CHNL_DTL_CD
             , C1.SELL_OG_TP_CD
             , C1.SELL_PRTNR_NO
             , C2.CO_CD
             , C2.BASE_PD_CD
             , C2.PD_QTY
             , C2.PD_BASE_AMT
             , C2.CNTR_AMT
             , C6.RCGVP_KNM
             , A1.NEW_ADR_ZIP
             , A1.RNADR
             , A1.RDADR
             , C4.FRISU_BFSVC_PTRM_UNIT_CD
             , C4.FRISU_BFSVC_PTRM_N
          FROM TB_SSCT_CNTR_BAS C1
        INNER JOIN TB_SSCT_CNTR_DTL C2
            ON C1.CNTR_NO = C2.CNTR_NO
        INNER JOIN TB_SSCT_CNTR_ADR_REL C5
            ON C2.CNTR_NO = C5.DTL_CNTR_NO
           AND C2.CNTR_SN = C5.DTL_CNTR_SN
           AND C5.ADRPC_TP_CD = '3'
           AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN C5.VL_STRT_DTM AND C5.VL_END_DTM
        INNER JOIN TB_SSCT_CNTR_ADRPC_BAS C6
            ON C5.CNTR_ADRPC_ID = C6.CNTR_ADRPC_ID
        INNER JOIN TB_GBCO_ADR_BAS A1
            ON C6.ADR_ID = A1.ADR_ID
        INNER JOIN TB_SSCT_CNTR_WELLS_DTL C4
            ON C2.CNTR_NO = C4.CNTR_NO
           AND C2.CNTR_SN = C4.CNTR_SN
         WHERE 1 = 1
           AND C1.SELL_INFLW_CHNL_DTL_CD = '1010'
         --  AND C1.SELL_OG_TP_CD = 'ALC'
           AND EXISTS (SELECT '1'
                        FROM TB_PDBS_PD_PRC_DTL X2
                  INNER JOIN TB_PDBS_PD_PRC_FNL_DTL X3
                          ON X2.PD_PRC_DTL_ID = X3.PD_PRC_DTL_ID
                       WHERE 1 = 1
                         AND C2.BASE_PD_CD = X2.PD_CD)
           AND C1.CNTR_NO = 'W20225388200'
     */

    @Test
    @DisplayName("wells 일시불 사전점검 테스트")
    void testForPrescreeningContractForSinglePayment() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("5")
            .pdCd01("WP02110453")
            .rcpChnlDtl("2010")
            .dscDv("1")
            .uswy("0")
            .mngtPrd("3")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-singlepayment-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.PRCS_RSLT").value("S"))
        ;
    }

    @Test
    @DisplayName("wells 일시불 생성 테스트 (계약번호 자동 생성)")
    void testForCreateContractForSinglePaymentWithoutCntrNo() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("1")
//            .cntrNo("W20244903253")
//            .cntrSn("1")
            .cntrCstNo("031542082")
//            .dcde("1646422")
//            .rcpChnlDtl("2050")
            .dcde("1653227")
            .rcpChnlDtl("2010")
            .pdCd01("WP02110453")
            .dscDv("1")
            .uswy("0")
            .mngtPrd("3")
            .dpDvCd1("97")
            .subscAmt1("349000")
            .istllKnm("수령자")
            .istCstCralLocaraTno("010")
            .istCstMexno("1234")
            .istCstCralIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);
        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-singlepayment-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto))
            ;

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.PRCS_RSLT").value("S"))
        ;
    }

    @Test
    @DisplayName("wells 일시불 생성 테스트")
    void testCreateContractForSinglePayment() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("1")
            .cntrNo("W20244903293")
            .cntrSn("1")
            .cntrCstNo("031542082")
            .dcde("1646422")
            .pdCd01("WP02110453")
            .rcpChnlDtl("2050")
            .dscDv("1")
            .uswy("0")
            .mngtPrd("3")
            .dpDvCd1("97")
            .subscAmt1("349000")
            .istllKnm("수령자")
            .istCstCralLocaraTno("010")
            .istCstMexno("1234")
            .istCstCralIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);
        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-singlepayment-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto))
            ;

        /*"ERR_OC_YN":"X","RSP_MSG":"이미 존재하는 계약상세번호입니다."*/

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.PRCS_RSLT").value("S"))
        ;
    }

    @Test
    @DisplayName("wells 일시불 생성 실패 테스트 - 파트너 다름")
    void failTestForCreateContractForSinglePayment() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("1")
            .cntrNo("W20230510674")
            .cntrSn("5")
            .cntrCstNo("031542082")
            .dcde("1653227")
            .rcpChnlDtl("2010")
            .pdCd01("WP02110453")
            .dscDv("1")
            .uswy("0")
            .mngtPrd("3")
            .dpDvCd1("97")
            .subscAmt1("349000")
            .istllKnm("수령자")
            .istCstCralLocaraTno("010")
            .istCstMexno("1234")
            .istCstCralIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);
        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-singlepayment-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("X"))
            .andExpect(jsonPath("$.header.RSP_MSG").value("해당 계약은 해당 파트너의 계약이 아닙니다."))
        ;
    }

    @Test
    @DisplayName("wells 일시불 생성 실패 테스트 - 기계약")
    void failTestForCreateContractForSinglePayment2() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("1")
            .cntrNo("W20234902675")
            .cntrSn("1")
            .cntrCstNo("031542082")
            .dcde("1653227")
            .rcpChnlDtl("2010")
            .pdCd01("WP02110453")
            .dscDv("1")
            .uswy("0")
            .mngtPrd("3")
            .dpDvCd1("97")
            .subscAmt1("349000")
            .istllKnm("수령자")
            .istCstCralLocaraTno("010")
            .istCstMexno("1234")
            .istCstCralIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);
        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-singlepayment-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("X"))
            .andExpect(jsonPath("$.header.RSP_MSG").value("이미 존재하는 계약상세번호입니다."))
        ;
    }

    @Test
    @DisplayName("wells 일시불 생성 테스트 - 계약 상세만 추가")
    void testForCreateContractForSinglePaymentWithExistCntrNo() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("1")
            .cntrNo("W20234902675")
            .cntrSn("2")
            .cntrCstNo("031542082")
            .dcde("1653227")
            .rcpChnlDtl("2010")
            .pdCd01("WP02110453")
            .dscDv("1")
            .uswy("0")
            .mngtPrd("3")
            .dpDvCd1("97")
            .subscAmt1("349000")
            .istllKnm("수령자")
            .istCstCralLocaraTno("010")
            .istCstMexno("1234")
            .istCstCralIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")
            .build();
        EaiWrapper<CreateSinglePaymentReq> dto = new EaiWrapper(req);
        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-singlepayment-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.PRCS_RSLT").value("S"))
        ;
    }

    @Test
    @DisplayName("wells 렌탈 사전점검 테스트")
    void testForPrescreeningContractForRental() throws Exception {
        // given
        CreateRentalReq req2 = CreateRentalReq.builder()
            .inDv("5")
            .prtnrNo("1653227")
            .rcpChnlDtl("2010")
            .pdCd("WP02120342")
            .dscDvCd("8")
//            .dscTpCd("03")
            .rtlfe1("24900")
            .dutyUse("36")
            .vstPrdCd("3")
            .uswyCd("0")
            .chgMcn1("60")
            .rgstCost("100000")
            .build();

        CreateRentalReq req = CreateRentalReq.builder()
            .inDv("5")
            .prtnrNo("1653227")
            .rcpChnlDtl("2010")
            .pdCd("WP02120319")
            .dscDvCd("8")
            .dscTpCd("03")
            .rtlfe1("24900")
            .dutyUse("60")
            .vstPrdCd("3")
            .uswyCd("0")
            .chgMcn1("60")
            .rgstCost("100000")
            .build();

        /*
        * JOB_PSIC_EMPNO=1229,
SELL_DV_CD=07,
PRTNR_NO=1653227,
PD_CD=WP02120319,
DSC_DV_CD=8,
DSC_TP_CD=05,
RTLFE1=38900,
DUTY_USE=60,
VST_PRD_CD=3,
RENTAL_DSC_AMT5=0,
USWY_CD=0,
ISTM_PCAM_AMT=,
VAT=,
CHG_MCN1=60,
RGST_COST=100000,
SV_CHRAM=,
LEASE_TAM=2334000,
RCP_CHNL_DTL=2010*/

        EaiWrapper<CreateRentalReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-rental-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.RS_CD").value("S"))
        ;
    }

    @Test
    @DisplayName("wells 렌탈 생성 테스트")
    void testCreateContractForRentalWithoutCntrNoSn() throws Exception {
        // given
        CreateRentalReq req = CreateRentalReq.builder()
            .inDv("1")
            .cstNo("031542082")
            .prtnrNo("1653227")
            .rcpChnlDtl("2010")
            .pdCd("WP02120342")
            .dscDvCd("8")
            .dscTpCd("03")
            .rtlfe1("24900")
            .dutyUse("36")
            .vstPrdCd("3")
            .uswyCd("0")
            .chgMcn1("60")
            .rgstCost("100000")

            .aftnDvCd("1")
            .rtlfe1("349000")
            .istCstNm("수령자")
            .istCphonLocaraTno("010")
            .istCphonExno("1234")
            .istCphonIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")

            .build();

        EaiWrapper<CreateRentalReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-rental-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.RS_CD").value("S"))
        ;
    }

    @Test
    @DisplayName("wells 렌탈 생성 테스트")
    void testCreateContractForRental() throws Exception {
        // given
        CreateRentalReq req = CreateRentalReq.builder()
            .inDv("1")
            .cstNo("031542082")
            .prtnrNo("1653227")
            .rcpChnlDtl("2010")
            .pdCd("WP02120342")
            .dscDvCd("8")
            .dscTpCd("03")
            .rtlfe1("24900")
            .dutyUse("36")
            .vstPrdCd("3")
            .uswyCd("0")
            .chgMcn1("60")
            .rgstCost("100000")

            .aftnDvCd("1")
            .rtlfe1("349000")
            .istCstNm("수령자")
            .istCphonLocaraTno("010")
            .istCphonExno("1234")
            .istCphonIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")

            .build();

        EaiWrapper<CreateRentalReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-rental-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"))
            .andExpect(jsonPath("$.body.RS_CD").value("S"))
        ;
    }
}
