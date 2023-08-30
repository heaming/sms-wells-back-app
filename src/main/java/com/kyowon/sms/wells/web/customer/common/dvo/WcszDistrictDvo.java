package com.kyowon.sms.wells.web.customer.common.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

/**
 * 지국장 정보 Dvo
 */
@Getter
@Setter
public class WcszDistrictDvo {
    private String dtrcOgId; /* 지국장 조직 ID */
    private String dtrcOgTpCd; /* 지국장 조직 구분 코드 */
    private String dtrcOgCd; /* 지국장 조직 코드 */
    private String dtrcOgNm; /* 지국장 조직 명 */
    private String dtrcPrtnrNo; /* 지국장 사번 */
    private String dtrcPrtnrKnm; /* 지국장 이름 */
    private String dtrcPrtnrNmNo; /* 파트너명 : 파트너 한글명(파트너번호) */
    private String dtrcPstnDvCd; /* 지국장 직급코드 */
    private String dtrcPstnNm; /* 조직장 직급 명 */
    private String dtrcMpNo1; /* 지국장 휴대폰번호1 */
    @DBDecField
    private String dtrcMpNo2; /* 지국장 휴대폰번호2 */
    private String dtrcMpNo3; /* 지국장 휴대폰번호3 */
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
    private String cnrNm;/* 센터장명 */
    private String ogTpCd;/* 조직유형코드 */
    private String ogId;/* 조직ID */
    private String ogCd;/* 조직코드 */
}
