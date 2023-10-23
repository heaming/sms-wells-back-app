package com.kyowon.sms.wells.web.bond.credit.ivo.ONIC2_CBNO1003.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Request 공통부
 */
@Setter
@Getter
public class CommReq {

    @JsonProperty("spczGropCd")
    private String spczGropCd; /* 전문그룹코드 */

    @JsonProperty("dealKindClfyCd")
    private String dealKindClfyCd; /* 거래종별코드 */

    @JsonProperty("dealDivCd")
    private String dealDivCd; /* 거래구분코드 */

    @JsonProperty("snrcFlag")
    private String snrcFlag; /* 송수신Flag */

    @JsonProperty("trunDiv")
    private String trunDiv; /* 단말기구분 */

    @JsonProperty("rplyCd")
    private String rplyCd; /* 응답코드 */

    @JsonProperty("etrcInttId")
    private String etrcInttId; /* 참가기관ID */

    @JsonProperty("inttSpczAdmnNo")
    private String inttSpczAdmnNo; /* 기관전문관리번호 */

    @JsonProperty("inttSpczTrnmHr")
    private String inttSpczTrnmHr; /* 기관전문전송시간 */

    @JsonProperty("niceSpczAdmnNo")
    private String niceSpczAdmnNo; /* NICE전문관리번호 */

    @JsonProperty("niceSpczTrnmHr")
    private String niceSpczTrnmHr; /* NICE전문전송시간 */

    @JsonProperty("blnkC")
    private String blnkC; /* 공란 */
    @JsonProperty("inqwtcnRsnCd")
    private String inqwtcnRsnCd; /* 조회동의사유코드 */

    @Override
    public String toString() {
        return "CommReq [spczGropCd=" + spczGropCd + ", dealKindClfyCd=" + dealKindClfyCd + ", dealDivCd=" + dealDivCd
            + ", snrcFlag=" + snrcFlag + ", trunDiv=" + trunDiv + ", rplyCd="
            + rplyCd + ", etrcInttId=" + etrcInttId + ", inttSpczAdmnNo=" + inttSpczAdmnNo
            + ", inttSpczTrnmHr=" + inttSpczTrnmHr + ", niceSpczAdmnNo=" + niceSpczAdmnNo
            + ", niceSpczTrnmHr=" + niceSpczTrnmHr + ", blnkC=" + blnkC
            + ", inqwtcnRsnCd=" + inqwtcnRsnCd + "]";
    }
}
