package com.kyowon.sms.wells.web.contract.interfaces.rest;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRegularShippingReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRentalReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateSinglePaymentReq;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;
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
    @DisplayName("wells 일시불 계약생성")
    void createContractForSinglePayment() throws Exception {
        // given
        /*CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .cntrNo("W2022538820A")
            .cntrSn("1")
            .rcpChnlDtl("2010")
            .cstKnm("조순옥")
            .cntrtAdrDvCd("1")
            .cntrtCralLocaraTno("010")
            .cntrtMexno("2222")
            .cntrtCralIdvTno("3333")
            .cntrtZip("16332")
            .cntrtBasAdr("경기 수원시 장안구 천천로22번길 34")
            .cntrtDtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            //.cntrCstNo("031095096")
            .cstKnm("조순옥")
            .istllKnm("조순옥")
            .istAdrDvCd("1")
            .istCstCralLocaraTno("010")
            .istCstMexno("2222")
            .istCstCralIdvTno("3333")
            .istZip("16332")
            .istBasAdr("경기 수원시 장안구 천천로22번길 34")
            .istDtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .pdCd01("WP05120580")
            .pdQty01("1")
            .subscAmt1("1000")
            .dpDvCd1("01")
            .cdno1("11122233334444")
            .crdcdIstmMcn1("1")
            .cdonrNm1("조순옥")
            .ag1("Y")
            .ag2("Y")
            .ag3("Y")
            .ag4("Y")
            .ag5("Y")
            .dscDv("2")
            .dscTp("1")
            .uswy("0")
            .mngtPrd("6")
            .inCls("5")
            .build();*/
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("5")
            .pdCd01("WP01110188")
            .rcpChnlDtl("1010")
            .dscDv("1")
            .uswy("1")
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
            .andExpect(jsonPath("$.body.PRCS_RSLT").value("F"))
            .andExpect(jsonPath("$.body.MSG").value("F"))
        ;
    }

    @Test
    @DisplayName("wells 일시불 사전점검 테스트")
    void testPrescreeningContractForSinglePayment() throws Exception {
        // given
        CreateSinglePaymentReq req = CreateSinglePaymentReq.builder()
            .inCls("5")
            .pdCd01("WP02110453")
            .rcpChnlDtl("2050")
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
    @DisplayName("wells 렌털 계약생성")
    void createContractForRental() throws Exception {
        // given
        String nowString = DateUtil.getNowString();
        /*CreateRentalReq req = CreateRentalReq.builder()
            .cntrNo("W2022538820A")
            .cntrSn("1")
            .rcpChnlDtl("2010")
            .rcpdt(nowString.substring(0, 7))
            .rcptm(nowString.substring(8, 13))
            .cstNm("조순옥")
            .adrDvCd("1")
            .cphonLocaraTno("010")
            .cphonExno("2222")
            .cphonIdvTno("3333")
            .zip("16332")
            .basAdr("경기 수원시 장안구 천천로22번길 34")
            .dtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .cstNo("031095096")
            .istCstNm("조순옥")
            .istAdrDvCd("1")
            .istCphonLocaraTno("010")
            .istCphonExno("2222")
            .istCphonIdvTno("3333")
            .istZip("16332")
            .istBasAdr("경기 수원시 장안구 천천로22번길 34")
            .istDtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .pdCd("WP05120580")
            .pdQty("1")
            .rtlfe1("12000")
            .cardAmt1("12000")
            .crcdno1("11122233334444")
            .cardIstmMcn1("1")
            .cdonrNm1("조순옥")
            .dscDvCd("2")
            .dscTpCd("1")
            .vstPrdCd("6")
            .uswyCd("0")
            .prtnrNo("1650501")
            .pifClcnUAgYn("Y")
            .pifThpOfrAgYn("Y")
            .mrktUtlzAgYn("Y")
            .fstrAgYn("Y")
            .pifBizFstrAgYn("Y")
            .cntrtRel("01")
            .txinvPblOjYn("Y")
            .txinvBzrno("1018139767")
            .txinvCphonLocaraTno("010")
            .txinvCphonExno("2222")
            .txinvCphonIdvTno("3333")
            .txinvEmadr("a@kyowon.co.kr")
            .build();*/

        CreateRentalReq req = CreateRentalReq.builder()
            .pdCd("WP01110188")
            .build();

        EaiWrapper<CreateRentalReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-rental-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
    }

    @Test
    @DisplayName("wells 렌탈 사전점검 테스트")
    void testPrescreeningContractForRental() throws Exception {
        // given
        CreateRentalReq req = CreateRentalReq.builder()
            .inDv("5")
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
    @DisplayName("wells 정기배송 계약생성")
    void createContractForRegularShipping() throws Exception {
        String nowString = DateUtil.getNowString();
        CreateRegularShippingReq req = CreateRegularShippingReq.builder()
            .cntrNo("W2022538820A")
            .cntrSn("1")
            .rcpdt(nowString.substring(0, 7))
            .rcptm(nowString.substring(8, 13))
            .cstNm("조순옥")
            .cphonLocaraTno("010")
            .cphonExno("2222")
            .cphonIdvTno("3333")
            .adrDvCd("1")
            .zip("16332")
            .basAdr("경기 수원시 장안구 천천로22번길 34")
            .dtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .cstNo("031095096")
            .cstEmadr("test@kyowon.com")
            .sppCstNm("조순옥")
            .sppCphonLocaraTno("010")
            .sppCphonExno("2222")
            .sppCphonIdvTno("3333")
            .sppAdrDvCd("1")
            .sppZip("16332")
            .sppBasAdr("경기 수원시 장안구 천천로22번길 34")
            .sppDtlAdr("528동 2003호 (정자동,백설마을삼환나우빌아파트)")
            .pdCd01("WP05120580")
            .pdQty01("1")
            .pdAmt01("10000")
            .pdVat01("1000")
            .pdDscAmt01("0")
            .cntrTam("10000")
            .sellAmt("10000")
            .cardAmt("10000")
            .cdcoDv("1")
            .crcdno1("11122233334444")
            .cardIstmMcn("1")
            .cdonrNm("조순옥")
            .dcsDv("2")
            .dcsTp("1")
            .sppPrd("3")
            .prtnrNo("1650501")
            .pifClcnUAgYn("Y")
            .pifThpOfrAgYn("Y")
            .mrktUtlzAgYn("Y")
            .pifTfYn("Y")
            .fstrAgYn("Y")
            .sellDvCd("EVXXX01")
            .cstClsCd("1")
            .txinvPblOjYn("Y")
            .txinvBzrno("1018139767")
            .txinvCphonLocaraTno("010")
            .txinvCphonExno("2222")
            .txinvCphonIdvTno("3333")
            .txinvEmadr("test@kyowon.co.kr")
            .lkCntrNo("W20221251542")
            .lkCntrSn("1")
            .rcpChnlDtl("2050")
            .build();

        EaiWrapper<CreateRegularShippingReq> dto = new EaiWrapper(req);

        // when & then
        MockHttpServletRequestBuilder request = post(BASE_URL + "/create-regular-shipping-contract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.header.ERR_OC_YN").value("N"));
    }
}
