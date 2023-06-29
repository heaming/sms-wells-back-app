package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 택배설치상품 출고관리
 * </pre>
 *
 * @author junggheejin
 * @since 2023.05.24
 */
public class WsnaPcsvOutOfStorageMgtDto {

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-SearchReq")
    public record SearchReq(
        String findGb,
        String selCnt,
        String svBizDclsfCd,
        String wkPrgsStatCd,
        String pdCd,
        String wkWareNo,
        String startDt,
        String endDt,
        String vstFshDt,
        String ivcPrntSn
    ) {}

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-SearchRes")
    public record SearchRes(

        String cntrRcpFshDtm, //CONT_DT 계약일자
        String svBizHclsfCd,

        String svBizDclsfCd,

        String svBizDclsfNm,

        String wkPrgsStatCd,

        String wkPrgsStatNm,

        String vstFshDt,

        String cntrNo,

        String cntrSn,

        String rcgvpKnm,
        String pdCd,

        String pdNm,

        String reqdDt,

        String rsgFshDt, //CAN_DT 취소일자

        String cstSvAsnNo,

        String useQty,

        String wkWareNo,

        String prtnrNo,

        String pdGdCd,

        String istDt,

        String urgtYn,

        String rpbLocaraCd, //VST_LOCARA_CD 방문지역코드

        String asRefriDvCd,

        String bfsvcRefriDvCd,

        String filtSellTpCd,

        String pdSellTpCd,

        String pdUswyCd,

        String siteAwSvTpCd,

        String siteAwAtcCd,

        String rnadr, //주소

        String rdadr, //주소 상세

        String newAdrZip, //우편번호

        String cralLocaRaTno, //휴대지역전화번호(휴대폰번호)

        String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)

        String cralIdvTno, //휴대개별전화번호(휴대폰번호)

        String locaraTno, //지역전화번호 (전화번호)

        String exnoEncr, //전화국번호암호화(전화번호)

        String idvTno, //개별전화번호(전화번호)

        String rsgAplcDt, //해지신청일자

        String basePdCd, //기준상품코드

        String basePdNm, //기준상품명

        String ogId,

        String ogTpCd,

        String ivcPrntSn, //송장출력번호

        String prtnrKnm,

        String asLctCd, //AS위치코드 (작업결과용)

        String asPhnCd, //AS현상코드 (작업결과용)

        String asCausCd, //AS원인코드 (작업결과용)

        String wareMngtPrtnrNo //창고관리파트너번호
    ) {
        public SearchRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);

        }
    }

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cstSvAsnNo,

        @NotBlank
        String svBizDclsfCd,

        @NotBlank
        String svBizHclsfCd,
        @NotBlank
        String cntrNo,

        @NotBlank
        String pdGdCd,

        @NotBlank
        String pdGrpCd,

        @NotBlank
        String pdCd,

        @NotBlank
        String prtnrNo,

        @NotBlank
        String ogTpCd,

        @NotBlank
        String cntrSn,

        @NotBlank
        String urgtYn,

        String rpbLocaraCd,

        String asLctCd,

        String asPhnCd,

        String asCausCd,

        String pdUswyCd,

        String filtSellTpCd,

        String pdSellTpCd,

        String asRefriDvCd,

        String bfsvcRefriDvCd,

        String useQty,

        String wkWareNo,

        String wareMngtPrtnrNo,

        String siteAwSvTpCd,

        String siteAwAtcCd,

        String ivcPrntSn,

        String istDt

    ) {}

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-FindLogisticsCentersRes")
    public record FindLogisticsCentersRes(
        String codeId,
        String codeName
    ) {}

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-FindProductsReq")
    public record FindProductsReq(
        String svBizDclsfCd
    ) {}
    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-FindProductsRes")
    public record FindProductsRes(
        String pdCd,
        String pdNm,
        String pdGrpCd
    ) {}
    @ApiModel("WsnaPcsvOutOfStorageMgtDto-FindIvcPrntSnRes")
    public record FindIvcPrntSnRes(
        String codeId,
        String codeName
    ) {}
}
