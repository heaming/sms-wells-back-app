package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvReturningGoodsSaveProductDvo;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 택배설치상품 반품관리
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.05.26
 */
public class WsnaPcsvReturningGoodsMgtDto {

    @ApiModel(value = "WsnaPcsvReturningGoodsMgtDto-SearchReq")
    public record SearchReq(
        String wareNo,
        String findGb,
        String startDt,
        String endDt,
        String cntrDtlNo, // 계약상세번호
        String rcgvpKnm,
        String bcNo,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno

    ) {
        public SearchReq {
            mexnoEncr = DbEncUtil.enc(mexnoEncr);
        }
    }

    @ApiModel(value = "WsnaPcsvReturningGoodsMgtDto-SearchRes")
    public record SearchRes(

        String findGb,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String wkPrgsStatCd,
        String wkPrgsStatNm,
        String reWkPrgsStatNm,
        String bcNo,
        String cntrNo,
        String cntrSn,
        String cntrDtlNo,
        String rcgvpKnm,
        String cralLocaraTno,
        String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)
        String cralIdvTno, //휴대개별전화번호(휴대폰번호)
        String locaraTno, //지역전화번호 (전화번호)
        String exnoEncr, //전화국번호암호화(전화번호)
        String idvTno, //개별전화번호(전화번호)
        String basePdCd,
        String basePdNm,
        String pdctPdCd,
        String cntrPdStrtdt,
        String fwSppIvcNo,
        String pcsvRcgvDt,
        String rcpdt,
        String pdUseDc,
        String cmptGd,
        String fnlGb,
        String rcpOgTpCd,
        String rcpIchrPrtnrNo,
        String prtnrKnm,
        String wkWareNo,
        String wareNm,
        String wareMngtPrtnrNo,
        String wareMngtPrtnrOgTpCd,
        String asLctCd,
        String asPhnCd,
        String asCausCd,
        String rpbLocaraCd,
        String siteAwSvTpCd,
        String siteAwAtcCd,
        String pdUswyCd,
        String asRefriDvCd,
        String bfsvcRefriDvCd,
        String urgtDvCd,
        int partCnt,
        String partCd1,
        String partNm1,
        String partQty1,
        String partCd2,
        String partNm2,
        String partQty2,
        String partCd3,
        String partNm3,
        String partQty3,
        String partCd4,
        String partNm4,
        String partQty4,
        String partCd5,
        String partNm5,
        String partQty5,
        String partCd6,
        String partNm6,
        String partQty6,
        String partCd7,
        String partNm7,
        String partQty7,
        String partCd8,
        String partNm8,
        String partQty8,
        String partCd9,
        String partNm9,
        String partQty9,
        String partCd10,
        String partNm10,
        String partQty10,
        String cntrCstNo,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlNm,
        String cntrDtlStatCd,
        String cntrDtlStatNm,
        String cntrRcpFshDtm,
        String adrId,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String rsgAplcDt,
        String rsgFshDt,
        String arvDt,
        String dtmChRsonCd,
        String pscocd,
        String dtmChRsonDtlCn,
        String reqdDt,
        String mngrDvCd,
        String dgr1LevlOgId,
        String dgr3LevlOgId,
        String editYn,
        String svBizHclsfCd,
        String pdQty,
        String istDt,
        String cstSvAsnNo,
        String ogId,
        String ogTpCd,
        String prtnrNo,
        String clnSppIvcNo,

        // 물류 수불처리
        String ostrTpCd,
        String ostrDt,
        String itmOstrNo,
        String ostrSn,
        String rmkCn,
        String logisticsPdCd,
        String logisticsPdNm,
        String logisticsPdQty,
        String asnDt
    ) {
        public SearchRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    @ApiModel(value = "WsnaPcsvReturningGoodsMgtDto-SaveReq")
    public record SaveReq(

        String findGb,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String wkPrgsStatCd,
        String wkPrgsStatNm,
        String reWkPrgsStatNm,
        String bcNo,
        String cntrNo,
        String cntrSn,
        String cntrDtlNo,
        String rcgvpKnm,
        String cralLocaraTno,

        String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)
        String cralIdvTno, //휴대개별전화번호(휴대폰번호)
        String locaraTno, //지역전화번호 (전화번호)
        String exnoEncr, //전화국번호암호화(전화번호)
        String idvTno, //개별전화번호(전화번호)
        String basePdCd,
        String basePdNm,
        String pdctPdCd,
        String cntrPdStrtdt,
        String fwSppIvcNo,
        String pcsvRcgvDt,
        String rcpdt,
        String pdUseDc,
        String cmptGd,
        String fnlGb,
        String rcpOgTpCd,
        String rcpIchrPrtnrNo,
        String prtnrKnm,
        String wkWareNo,
        String wareNm,
        String wareMngtPrtnrNo,
        String wareMngtPrtnrOgTpCd,
        String asLctCd,
        String asPhnCd,
        String asCausCd,
        String rpbLocaraCd,
        String siteAwSvTpCd,
        String siteAwAtcCd,
        String pdUswyCd,
        String asRefriDvCd,
        String bfsvcRefriDvCd,
        String urgtDvCd,
        int partCnt,
        String partCd1,
        String partNm1,
        String partQty1,
        String partCd2,
        String partNm2,
        String partQty2,
        String partCd3,
        String partNm3,
        String partQty3,
        String partCd4,
        String partNm4,
        String partQty4,
        String partCd5,
        String partNm5,
        String partQty5,
        String partCd6,
        String partNm6,
        String partQty6,
        String partCd7,
        String partNm7,
        String partQty7,
        String partCd8,
        String partNm8,
        String partQty8,
        String partCd9,
        String partNm9,
        String partQty9,
        String partCd10,
        String partNm10,
        String partQty10,
        String cntrCstNo,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlNm,
        String cntrDtlStatCd,
        String cntrDtlStatNm,
        String cntrRcpFshDtm,
        String adrId,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String rsgAplcDt,
        String rsgFshDt,
        String arvDt,
        String dtmChRsonCd,
        String pscocd,
        String dtmChRsonDtlCn,
        String reqdDt,
        String mngrDvCd,
        String dgr1LevlOgId,
        String dgr3LevlOgId,
        String editYn,
        String svBizHclsfCd,
        String pdQty,
        String istDt,
        String cstSvAsnNo,
        String ogId,
        String ogTpCd,
        String prtnrNo,
        String clnSppIvcNo,

        // 물류 수불처리
        String ostrTpCd,
        String ostrDt,
        String itmOstrNo,
        String ostrSn,
        String rmkCn,
        String logisticsPdCd,
        String logisticsPdNm,
        String logisticsPdQty,
        String fnlItmGdCd,
        String asnDt,
        List<WsnaPcsvReturningGoodsSaveProductDvo> products //상품 목록

    ) {}

    @ApiModel(value = "WsnaPcsvReturningGoodsMgtDto-FindLogisticsCentersRes")
    public record FindLogisticsCentersRes(
        String codeId,
        String codeName
    ) {}

    @ApiModel(value = "WsnaPcsvReturningGoodsMgtDto-FindItmOstrNoReq")
    public record FindItmOstrNoReq(
        String ostrTpCd, // 출고유형코드
        String ostrDt // 출고일자
    ) {}

}
