package com.kyowon.sms.wells.web.contract.risk.dto;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctcIncompletenessSalesDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 기기변경 부정행위 단건 조회 Search Request Dto
    @Builder
    @ApiModel("IncompletenessSalesDto-SearchByCntrNoReq")
    public record SearchByCntrNoReq(
        String baseCntrNo,
        String baseCntrSn,
        String ojCntrNo,
        String ojCntrSn
    ) {}

    // 기기변경 부정행위 Search Request Dto
    @Builder
    @ApiModel("IncompletenessSalesDto-SearchReq")
    public record SearchReq(
        String icptSellExrDt,
        String baseCntrRcpdt,
        String apyStrtDt,
        String apyEndDt,
        String cntrNo,
        String dgr1HgrOgCd,
        String dgr2HgrOgCd,
        String prtnrNo
    ) {}

    // 기기변경 부정행위 Save Request Dto
    @Builder
    @ApiModel("IncompletenessSalesDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        String icptSellId,
        String icptSellExrDt,
        String icptSellProcsTpCd,
        String icptSellRsonCn,
        String baseCntrNo,
        int baseCntrSn,
        String ojCntrNo,
        int ojCntrSn,
        String orglDtaDlYn,
        String fnlMdfcDtm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 기기변경 부정행위 Search Result Dto
    @ApiModel("IncompletenessSalesDto-SearchRes")
    public record SearchRes(
        String icptSellProcsTpCd,
        String icptSellRsonCn,
        String baseCntrNo,
        String baseCntrSn,
        String ojCntrNo,
        String ojCntrSn,
        String icptSellExrDt,
        String cntrPdStrtdt,
        String baseUsedCpsYn,
        String baseChdvcRerntYn,
        String baseRcgvpKnm,
        String baseIstDt,
        String baseReqdDt,
        String baseIstGapMm,
        String baseCralLocaraTno,
        String baseMexnoEncr,
        String baseCralIdvTno,
        String baseMpno,
        String basePdCd,
        String baseAdr,
        String prtnrKnm,
        String prtnrNo,
        String locaraCd,
        String ogCd,
        String ojChdvcRerntYn,
        String ojRcgvpKnm,
        String ojIstDt,
        String ojReqdDt,
        String ojIstGapMm,
        String ojPdCd,
        String ojAdr,
        String fnlMdfcDtm
    ) {
        public SearchRes {
            baseMpno = baseCralLocaraTno + CtContractConst.TNO_DELIM + DbEncUtil.dec(baseMexnoEncr)
                + CtContractConst.TNO_DELIM + baseCralIdvTno;
        }
    }
}
