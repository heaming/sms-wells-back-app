package com.kyowon.sms.wells.web.customer.prospective.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

public class WcsbCustomerInterfaceDto {

    /**
     * 웰스홈페이지 신규접수 req Dto
     */
    @ApiModel(value = "WcsbCustomerInterfaceDto-CreateNewReceiptInquiryReq")
    public record CreateNewReceiptInquiryReq(
        @JsonProperty("PSPC_CST_INFLW_DT")
        String pspcCstInflwDt, /* 가망고객유입일자 */
        @JsonProperty("APLC_SN")
        String aplcSn, /* 신청일련번호 */
        @JsonProperty("SELL_INFLW_CHNL_DV_CD")
        String sellInflwChnlDvCd, /* 판매유입채널구분코드 */
        @JsonProperty("INRT_PD_HCLSF_ID")
        String inrtPdHclsfId, /* 관심상품대분류ID */
        @JsonProperty("INRT_PD_MCLSF_ID")
        String inrtPdMclsfId, /* 관심상품중분류ID */
        @JsonProperty("INRT_PD_LCLSF_ID")
        String inrtPdLclsfId, /* 관심상품소분류ID */
        @JsonProperty("INRT_PD_DCLSF_ID")
        String inrtPdDclsfId, /* 상품세분류ID */
        @JsonProperty("PSPC_CST_KNM")
        String pspcCstKnm, /* 가망고객한글명 */
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno, /* 휴대지역전화번호 */
        @JsonProperty("MEXNO_ENCR")
        String mexnoEncr, /* 휴대전화국번호암호화 */
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno, /* 휴대개별전화번호 */
        @JsonProperty("LOCARA_TNO")
        String locaraTno, /* 지역전화번호 */
        @JsonProperty("EXNO_ENCR")
        String exnoEncr, /* 전화국번호암호화 */
        @JsonProperty("IDV_TNO")
        String idvTno, /* 개별전화번호 */
        @JsonProperty("ZIP1")
        String zip1, /*우편번호1*/
        @JsonProperty("ZIP2")
        String zip2, /*우편번호2*/
        @JsonProperty("RNADR")
        String rnadr, /*도로명주소*/
        @JsonProperty("RDADR")
        String rdadr, /*도로명상세주소*/
        @JsonProperty("RDADR2")
        String rdadr2, /*도로명상세주소2*/
        @JsonProperty("CNSL_PSB_STRT_DT")
        String cnslPsbStrtDt, /* 컨택요청일 */
        @JsonProperty("CNSL_PSB_STRT_HH")
        String cnslPsbStrtHh, /* 컨택요청시작시간 */
        @JsonProperty("CNSL_PSB_END_HH")
        String cnslPsbEndHh, /* 컨택요청종료시간 */
        @JsonProperty("PSPC_CST_RCP_CN")
        String pspcCstRcpCn, /* 가망고객요청내용 */
        @JsonProperty("FST_RGST_USR_ID")
        String fstRgstUsrId /* 최초등록사용자ID */
    ) {}

    /**
     * 웰스홈페이지 신규접수 res Dto
     */
    @ApiModel(value = "WcsbCustomerInterfaceDto-CreateNewReceiptInquiryRes")
    public record CreateNewReceiptInquiryRes(
        @JsonProperty("CHECK")
        String check, /* 결과상태 */
        @JsonProperty("RS_CD")
        String rsCd, /* 결과코드 [S : 성공, E999 : 에러]*/
        @JsonProperty("RS_MSG")
        String rsMsg, /* 결과상태 [성공 : 자료가 등록되었습니다 / 에러 : 문의 접수 중 오류가 발생했습니다.]*/
        @JsonProperty("ORSV")
        String orsv/* 여분 */
    ) {}

}
