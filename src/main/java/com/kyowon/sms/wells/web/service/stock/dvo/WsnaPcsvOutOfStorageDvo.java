package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvOutOfStorageDvo {

    String cntrNo; // 계약번호

    String cntrSn; // 계약일련번호

    String cntrCstNo; // 계약자 고객번호

    String sellTpCd; // 판매유형코드

    String sellTpNm; // 판매유형코드명

    String sellTpDtlCd; // 판매유형상세코드

    String sellTpDtlNm; // 판매유형상세코드명

    String cntrDtlStatCd; // 계약상세상태코드

    String cntrDtlStatNm; // 계약셍세상태코드명

    String rcgvpKnm; // 수령자한글명

    String basePdCd; // 기준상품코드

    String basePdNm; // 기준상품명

    String cntrRcpFshDtm; // 계약접수완료일시(출고요청일)

    String adrId; // 주소ID

    String newAdrZip; // 신주소우편번호

    String rnadr; // 도로명주소

    String rdadr; //주소 상세

    String cralLocaraTno; //휴대지역전화번호(휴대폰번호)

    @DBDecField
    String mexnoEncr; //휴대전화국번호암호화(휴대폰번호)

    String cralIdvTno; //휴대개별전화번호(휴대폰번호)

    String locaraTno; //지역전화번호 (전화번호)

    @DBDecField
    String exnoEncr; //전화국번호암호화(전화번호)

    String idvTno; //개별전화번호(전화번호)

    String rsgAplcDt; //해지신청일자

    String rsgFshDt; // 해지완료일자

    String cstSvAsnNo; // 배정번호

    String pdctPdCd; // 제품코드

    String pdctPdNm; // 제품명

    String svPdCd; // 서비스코드

    String svPdNm; // 서비스명

    String pdGdCd; // 제품등급

    String svBizHclsfCd; // 서비스업무대분류코드

    String svBizDclsfCd; // 서비스업무세분류코드

    String svBizDclsfNm; // 서비스업무세분류코드명

    String wkPrgsStatCd; // 서비스상태코드

    String wkPrgsStatNm; // 서비스상태코드명

    String istDt; // 설치일자

    String reqdDt; // 철거일자

    String ogId; // 조직ID

    String ogTpCd; // 조직유형코드

    String prtnrNo; // 파트너번호

    String prtnrKnm; // 파트너성명

    String vstFshDt; // 방문완료일자(출고확정일)

    String wkWareNo; // 창고번호

    String wareMngtPrtnrNo; // 창고관리파트너번호

    String wareMngtPrtnrOgTpCd; // 창고관리파트너조직유형코드

    String rpbLocaraCd; //VST_LOCARA_CD 방문지역코드

    String siteAwSvTpCd; // 현장수당서비스유형코드

    String siteAwAtcCd; // 현장수당항목코드

    String pdUswyCd; // 상품용도코드

    String asRefriDvCd; // AS유무상구분코드

    String bfsvcRefriDvCd; // BS유무상구분코드

    String urgtDvCd; // 긴급구분코드

    String ostrAkNo; // 서비스출고요청번호

    String lgstOstrAkNo; // 물류출고요청번호

    String ostrNo; // 물류출고번호

    /* 작업결과 필수 코드   */
    String pdGrpCd; // 상품그룹코드

    String asLctCd; //AS위치코드

    String asPhnCd; //AS현상코드

    String asCausCd; //AS원인코드

    /* 물류인터페이스 필수 코드  */

    String lgstWkMthdCd; // 물류작업방식코드

    int mpacSn; // 합포장번호

    /* 상품 */

    int partCnt; // 투입부품모델수

    String partCd1;

    String partNm1;

    String partQty1;

    String partCd2;

    String partNm2;

    String partQty2;

    String partCd3;

    String partNm3;

    String partQty3;

    String partCd4;

    String partNm4;

    String partQty4;

    String partCd5;

    String partNm5;

    String partQty5;

    String partCd6;

    String partNm6;

    String partQty6;

    String partCd7;

    String partNm7;

    String partQty7;

    String partCd8;

    String partNm8;

    String partQty8;

    String partCd9;

    String partNm9;

    String partQty9;

    String partCd10;

    String partNm10;

    String partQty10;

    String wlcmBfsvcYn;  // 웰컴BS 생성여부
}
