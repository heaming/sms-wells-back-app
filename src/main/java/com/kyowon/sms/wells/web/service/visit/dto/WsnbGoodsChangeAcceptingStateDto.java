package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 제품교체 접수 현황
 * </pre>
 *
 * @author leeminwoo
 * @since 2023.08.23
 */
public class WsnbGoodsChangeAcceptingStateDto {

    @ApiModel(value = "WsnbGoodsChangeAcceptingStateDto-SearchReq")
    public record SearchReq(
        String inqrBaseCd,
        String startDt,
        String endDt,
        String svCnrCd,
        String ogId,
        String prtnrNo,
        String aprAkStatCd,
        String tpChYn

    ) {}

    @ApiModel(value = "WsnbGoodsChangeAcceptingStateDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String aprAkStatCd,
        String tpChYn,
        String cntrDtlNo,
        String custNm,
        String custDiv,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String istDt,
        String changeRqstDt,
        String useDt,
        String pdctChngAkRsonCd,
        String schdDt,
        String recapOrFreeYn,
        String loc,
        String phn,
        String caus,
        String egerOpinion,
        String dratDtm,
        String docCrtrBlg,
        String docCrtrSbn,
        String docCrtrNm,
        String adminOpinion,
        String opinionDratDtm,
        String adminSbn,
        String adminNm,
        String procsRs,
        String vstCnfmDt,
        String procsDt,
        String procsrBlg,
        String procsrSbn,
        String procsrNm,
        String cstSvAsnNo,
        String oldSvBizDclsfCd,
        String newSvBizDclsfCd,
        String wkPrgsStatCd

    ) {}

    @ApiModel(value = "WsnbGoodsChangeAcceptingStateDto-SaveReq")
    public record SaveReq(

        String cntrNo,
        String cntrSn,
        String aprAkStatCd,
        String tpChYn,
        String cntrDtlNo,
        String custNm,
        String custDiv,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String istDt,
        String changeRqstDt,
        String useDt,
        String pdctChngAkRsonCd,
        String schdDt,
        String recapOrFreeYn,
        String loc,
        String phn,
        String caus,
        String egerOpinion,
        String dratDtm,
        String docCrtrBlg,
        String docCrtrSbn,
        String docCrtrNm,
        String adminOpinion,
        String opinionDratDtm,
        String adminSbn,
        String adminNm,
        String procsRs,
        String vstCnfmDt,
        String procsDt,
        String procsrBlg,
        String procsrSbn,
        String procsrNm,
        String cstSvAsnNo,
        String oldSvBizDclsfCd,
        String newSvBizDclsfCd,
        String wkPrgsStatCd

    ) {}
}
