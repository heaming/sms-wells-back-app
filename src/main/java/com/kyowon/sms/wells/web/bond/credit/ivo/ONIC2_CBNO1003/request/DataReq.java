package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Request 개별요청부
 */
@Setter
@Getter
@ToString
public class DataReq {
    @JsonProperty("keyDiv")
    private String keyDiv; /* 주민/사업자/법인/회원번호 조회키 구분 */
    @JsonProperty("rsdnNo")
    private String rsdnNo;/* 주민번호/사업자/법인번호/회원번호 */
    @JsonProperty("insHpNo")
    private String insHpNo;/* 설치지 휴대폰번호 */
    @JsonProperty("insHomNo")
    private String insHomNo;/* 설치지 자택 전화번호 */
    @JsonProperty("insAdr")
    private String insAdr;/* 설치지 주소 */
    @JsonProperty("inqRsnCd")
    private String inqRsnCd;/* 조회사유코드 */
    @JsonProperty("rtryReqNum")
    private String rtryReqNum;/* 재요청횟수 */
    @JsonProperty("infoCntiYn")
    private String infoCntiYn;/* 정보연속여부 */
    @JsonProperty("repotCnfimNo")
    private String repotCnfimNo;/* 보고서 인증번호 */
    @JsonProperty("shtItmSgmtReqCnt")
    private String shtItmSgmtReqCnt;/* raw data SEGMENT 요청건수 (반복건수) */
    @JsonProperty("rwDtaSgmtReqCnt")
    private String rwDtaSgmtReqCnt;/* 요약항목 SEGMENT 요청건수 (반복건수) */
    @JsonProperty("blnkD")
    private String blnkD;/* 공란 */
}
