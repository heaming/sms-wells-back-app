package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvOutOfStorageDvo {

    String cntrRcpFshDtm; //CONT_DT 계약일자

    String svBizHclsfCd;

    String svBizDclsfCd;

    String svBizDclsfNm;

    String wkPrgsStatCd;

    String wkPrgsStatNm;

    String vstFshDt;

    String cntrNo;

    String cntrSn;

    String rcgvpKnm;
    String pdCd;

    String pdNm;

    String reqdDt;

    String rsgFshDt; //CAN_DT 취소일자

    String cstSvAsnNo;

    String useQty;

    String wkWareNo;

    String prtnrNo;

    String pdGdCd;

    String istDt;

    String urgtDvCd;

    String rpbLocaraCd; //VST_LOCARA_CD 방문지역코드

    String asRefriDvCd;

    String bfsvcRefriDvCd;

    String filtSellTpCd;

    String pdSellTpCd;

    String pdUswyCd;

    String siteAwSvTpCd;

    String siteAwAtcCd;

    String rnadr; //주소

    String rdadr; //주소 상세

    String newAdrZip; //우편번호

    String cralLocaRaTno; //휴대지역전화번호(휴대폰번호)

    @DBDecField
    String mexnoEncr; //휴대전화국번호암호화(휴대폰번호)

    String cralIdvTno; //휴대개별전화번호(휴대폰번호)

    String locaraTno; //지역전화번호 (전화번호)

    @DBDecField
    String exnoEncr; //전화국번호암호화(전화번호)

    String idvTno; //개별전화번호(전화번호)

    String rsgAplcDt; //해지신청일자

    String basePdCd; //기준상품코드

    String basePdNm; //기준상품명

    String ogId;

    String ogTpCd;

    String ivcPrntSn; //송장출력번호

    String prtnrKnm;

    String asLctCd; //AS위치코드 (작업결과용)

    String asPhnCd; //AS현상코드 (작업결과용)

    String asCausCd; //AS원인코드 (작업결과용)

    String wareMngtPrtnrNo; //창고관리파트너번호 (물류연동)
}
