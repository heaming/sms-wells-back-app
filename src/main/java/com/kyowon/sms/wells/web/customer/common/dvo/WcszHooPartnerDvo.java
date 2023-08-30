package com.kyowon.sms.wells.web.customer.common.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * 조직장 정보 조회 Dvo
 */
@Getter
@Setter
public class WcszHooPartnerDvo {
    private String ogId; /* 조직장 조직 ID */
    private String ogTpCd; /* 조직장 조직 구분 코드 */
    private String ogCd; /* 조직장 조직 코드 */
    private String ogNm; /* 조직장 조직 명 */
    private String pstnDvCd; /* 조직장 직급코드 */
    private String pstnNm; /* 조직장 직급 명 */
    private String epNo; /* 조직장 사번 */
    private String epNm; /* 조직장 명 */
    private String epMpNo1; /* 조직장 휴대폰번호1 */
    private String epMpNo2; /* 조직장 휴대폰번호2 */
    private String epMpNo3; /* 조직장 휴대폰번호3 */
    private String bdvOgId; /* 사업단 조직 ID  */
    private String bdvOgCd; /* 사업단 조직 코드 */
    private String bdvOgNm; /* 사업단 */
    private String bdvPrtnrNo; /* 사업단장사번 */
    private String bdvPrtnrNm; /* 사업단장명 */
    private String gnrdvOgId; /* 총괄단 조직 ID  */
    private String gnrdvOgCd; /* 총괄단 조직 코드 */
    private String gnrdvOgNm; /* 총괄단 */
    private String gnrdvPrtnrNo; /* 총괄단장사번 */
    private String gnrdvPrtnrNm; /* 총괄단장명 */
    private String cnrOgId; /* 센터 조직 ID */
    private String cnrOgCd; /* 센터 조직 코드 */
    private String cnrOgNm; /* 센터명 */
    private String cnrNo; /* 센터장사번 */
    private String cnrNm; /* 센터장명 */
    private String cnrOfficeTelNO1; /* 센타전화번호1 */
    private String cnrOfficeTelNO2; /* 센타전화번호2 */
    private String cnrOfficeTelNO3; /* 센타전화번호3 */
    private String cnrMpNo1; /* 센터장휴대폰번호1 */
    private String cnrMpNo2; /* 센터장휴대폰번호2 */
    private String cnrMpNo3; /* 센터장휴대폰번호3 */
    private String dtrcOgCd; /* 지국코드 */
    private String dtrcOgNm; /* 지국명 */
    private String dtrcNd; /* 지국장 사번 */
    private String dtrcNm; /* 지국장 명 */
    private String dtrcMpNo1; /* 지국장 휴대폰번호1 */
    private String dtrcMpNo2; /* 지국장 휴대폰번호2 */
    private String dtrcMpNo3; /* 지국장 휴대폰번호3 */
}
