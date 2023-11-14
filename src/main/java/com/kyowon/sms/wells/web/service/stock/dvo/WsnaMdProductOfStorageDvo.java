package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaMdProductOfStorageDvo {
    String cstSvAsnNo;

    String cntrNo;

    String cntrSn;

    String cntrCstNo; // 계약자 고객번호

    String sellTpCd;

    String sellTpNm;

    String svBizHclsfCd;

    String svBizDclsfCd;

    String svBizDclsfNm;

    String wkPrgsStatCd;

    String wkPrgsStatNm;

    String istDt;

    String reqdDt;

    String rcgvpKnm;

    String cntrRcpFshDtm; //CONT_DT 계약일자

    String newAdrZip;

    String rnadr; //주소

    String rdadr; //주소 상세

    String cralLocaraTno; //휴대지역전화번호(휴대폰번호)

    @DBDecField
    String mexnoEncr; //휴대전화국번호암호화(휴대폰번호)

    String cralIdvTno; //휴대개별전화번호(휴대폰번호)

    String locaraTno; //지역전화번호 (전화번호)

    @DBDecField
    String exnoEncr; //전화국번호암호화(전화번호)

    String idvTno; //개별전화번호(전화번호)

    String rsgFshDt;

    String ostrCnfmDt;

    String ogId;

    String ogTpCd;

    String prtnrNo;

    String pdctPdCd;

    /* MD */
    String prtnrBzsNm;

    String pcsvCompNm;

    String sppIvcNo;

    String sppBzsPdId;

    /* 창고 */
    String wkWareNo;

    String wareMngtPrtnrNo;

    /* 상품 */

    int partCnt;

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
}
