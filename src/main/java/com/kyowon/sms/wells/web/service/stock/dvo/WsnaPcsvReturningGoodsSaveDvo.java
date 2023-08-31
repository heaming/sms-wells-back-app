package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WsnaPcsvReturningGoodsSaveDvo {

    String findGb;
    String cntrNo;
    String cntrSn;
    String sellTpCd;
    String sellTpNm;
    String cntrDtlStatCd;
    String cntrDtlStatNm;
    String rcgvpKnm;
    String basePdCd;
    String basePdNm;
    String cntrRcpFshDtm;
    String cntrPdStrtdt;
    String newAdrZip;
    String rnadr;
    String rdadr;
    String cralLocaraTno; //휴대지역전화번호(휴대폰번호)
    String mexnoEncr; //휴대전화국번호암호화(휴대폰번호)
    String cralIdvTno; //휴대개별전화번호(휴대폰번호)
    String locaraTno; //지역전화번호 (전화번호)
    String exnoEncr; //전화국번호암호화(전화번호)
    String idvTno; //개별전화번호(전화번호)
    String rsgAplcDt;
    String rsgFshDt;
    String cstSvAsnNo;
    String pdCd;
    String pdNm;
    String pdGdCd;
    String svBizDclsfCd;
    String svBizDclsfNm;
    String wkPrgsStatCd;
    String wkPrgsStatNm;
    String istDt;
    String reqdDt;
    String ogId;
    String ogTpCd;
    String prtnrNo;
    String vstFshDt;
    String useQty;
    String wareNo;
    String vstRqdt;
    String ogNm;
    String fstRgstUsrId;
    String prtnrKnm;
    String pdArvDt;
    String pdUseDc;
    String rtngdGd;
    String arvDt;
    String sppIvcNo;
    String sppProcsBzsNm;
    String rtngdNm;
    String fnlRtngdGd;
    String bcNo;
    String cntrCstNo;
    String urgtDvCd;
    String asLctCd;
    String asPhnCd;
    String asCausCd;
    String siteAwAtcCd;

    // 추가
    String siteAwSvTpCd; // 현장수당서비스유형코드
    String rpbLocaraCd; // 책임지역코드
    String svBizHclsfCd; // 서비스업무대분류코드
    String asRefriDvCd; // AS유무상구분코드
    String bfsvcRefriDvCd; // BS유무상구분코드
    String pdUswyCd; // 상품용도코드
    String pdHclsfId; // 상품대분류ID
    String pdMclsfId; // 상품중분류ID
    String pdLclsfId; // 상품소분류ID
    String pdDclsfId; // 상품세분류ID
    String dtmChRsonCd; //개봉여부코드
    String editYn; //수정여부
    String cntrDtlNo; //계약상세번호
    String wellsReqdDt; //wells철거일자

    /* 엔지니어 정보 조회 */
    String mngrDvCd;
    String dgr1LevlOgId;
    String dgr3LevlOgId;
    String brchOgId;

    // 물류수불처리 추가
    String ostrTpCd; // 출고유형코드
    String ostrDt; // 출고일자
    String itmOstrNo;
    String ostrSn;
    String itmStrNo;
    String strSn;

    // 물류에 전송하기 위한 데이터 dvo
    private String ostrAkNo;
    private String ostrAkSn;
    private String ostrAkTpCd;
    private String ostrAkRgstDt;
    private String strHopDt;
    private String lgstStrTpCd;
    private String iostAkDvCd;
    private String wareMngtPrtnrOgTpCd;
    private String sapIostTpCd;
    private String lgstSppMthdCd;
    private String ostrAkQty;
    private String ostrOjWareNo;
    private String svCnrCd;
    private String svCnrNm;
    private String rmkCn;

    //    List<WsnaPcsvReturningGoodsSaveProductDvo> products;

}
