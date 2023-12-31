package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2022.11.22
 */
@Setter
@Getter
public class WsnbInstallLocationDvo {
    String chk;
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String dtlSn; /* 상세일련번호 */
    String istLctDtlCn; /* 설치위치상세 */
    String ogTpCd; /* 조직유형코드 */
    String wkPrtnrNo; /* 작업파트너번호 */
    String istDt; /* 설치일자 */
    String custNm; /* 고객명 */
    String sellTpNm; /* 유형명 */
    String pdctPdCd; /* 상품코드 */
    String sapMatCd; /* SAP코드 */
    String pdNm; /* 상품명 */
    String locaraTno; /* 지역전화번호 */
    @DBDecField
    String exnoEncr; /* 전화국번호암호화 */
    String idvTno; /* 개별전화번호 */
    String cralLocaraTno; /* 휴대지역전화번호 */
    @DBDecField
    String mexnoEncr; /* 휴대전화국번호암호화 */
    String cralIdvTno; /* 휴대개별전화번호 */
    String zip; /* 우편번호 */
    String adr; /* 주소 */
    String ogNm; /* 소속센터 */
    String prtnrKnm; /* 등록자명 */
    String regDtm; /* 최초등록일자 */

}
