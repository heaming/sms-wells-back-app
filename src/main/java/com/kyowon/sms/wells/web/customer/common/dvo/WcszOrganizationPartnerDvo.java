package com.kyowon.sms.wells.web.customer.common.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

/**
 * 조직 파트너 정보 Dvo
 */
@Getter
@Setter
public class WcszOrganizationPartnerDvo {
    private String ogId; /* ep 조직 id */
    private String ogTpCd; /* ep 조직 구분 코드 */
    private String ogCd; /* ep 조직 코드 */
    private String ogNm; /* ep 조직 명 */
    private String pstnDvCd; /* ep 직급코드 */
    private String pstnNm; /* 직급 명 */
    private String epNo; /* ep 사번 */
    private String epNm; /* ep 명 */
    private String epMpNo1; /* ep휴대폰번호1 */
    @DBDecField
    private String epMpNo2; /* ep휴대폰번호2 */
    private String epMpNo3; /* ep휴대폰번호3 */
    private String bdvOgId; /* 사업단 조직 iD  */
    private String bdvOgCd; /* 사업단 조직 코드 */
    private String bdvOgNm; /* 사업단 */
    private String bdvPrtnrNo; /* 사업단장사번 */
    private String bdvPrtnrNm; /* 사업단장명 */
    private String gnrdvOgId; /* 총괄단 조직 ID  */
    private String gnrdvOgCd; /* 총괄단 조직 코드 */
    private String gnrdvOgNm; /* 총괄단 */
    private String gnrdvPrtnrNo; /* 총괄단장사번 */
    private String gnrdvPrtnrNm; /* 총괄단장명 */
    private String cnrOgId; /* 센터 조직 id */
    private String cnrOgCd; /* 센터 조직 코드 */
    private String cnrOgNm; /* 센터명 */
    private String cnrNo; /* 센터장사번 */
    private String cnrNm; /* 센터장명 */
    private String cnrOfficeTelNo1; /* 센타전화번호1 */
    @DBDecField
    private String cnrOfficeTelNo2; /* 센타전화번호2 */
    private String cnrOfficeTelNo3; /* 센타전화번호3 */
    private String cnrMpNo1; /* 센터장휴대폰번호1 */
    @DBDecField
    private String cnrMpNo2; /* 센터장휴대폰번호2 */
    private String cnrMpNo3; /* 센터장휴대폰번호3 */
    private String dtrcOgId; /* 지국 조직 iD */
    private String dtrcOgCd; /* 지국 조직 코드 */
    private String dtrcOgNm; /* 지국명 */
    private String dtrcNd; //* 지국장 사번 */
    private String dtrcNm; //* 지국장 명 */
    private String dtrcMpNo1; /* 지국장 휴대폰번호1 */
    @DBDecField
    private String dtrcMpNo2; /* 지국장 휴대폰번호2 */
    private String dtrcMpNo3; /* 지국장 휴대폰번호3 */
    private String searchText; /* 파트너 text */

}
