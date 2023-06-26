package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbAdvancedSellFeeReplaceDto {
    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchReq")
    public record SearchReq(
        String searchGubun,
        String baseYm,
        String ogTpCd,
        String piaCsYn,
        String dgCstId,
        String cntrNo,
        String cntrSn,
        String feeChk,
        String feeCd
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchRes")
    public record SearchRes(
        String ogTpCd,
        String dgCstId,
        String dgCstNm,
        String baseYm,
        String cntrNo,
        String feeOcAmt,
        String piaCsYn,
        String feeOcYm,
        String piaFeeOcAmt,
        String slRcogDt,
        String stplTn,
        String slAmt,
        String cntrPdEnddt,
        String csRplcYm,
        String csRplcAmt,
        String feeNm,
        String dpSlipTrsNo,
        String sapSlpno,
        String ttrmCsAmt,
        String csRplcAggAmt,
        String piaFeeEotBlam,
        String fnlMdfcDtm,
        String usrNm
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchDtlSummaryRes")
    public record SearchDtlSummaryRes(
        String piaCsYn,
        String slAmt,
        String csRplcAmt,
        String feeOcAmt,
        String piaFeeOcAmt,
        String ttrmCsAmt,
        String csRplcAggAmt,
        String piaFeeEotBlam
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchAggregateRes")
    public record SearchAggregateRes(
        String ogTpCd,
        String dgCstId,
        String dgCstNm,
        String baseYm,
        String slAmt,
        String feeOcAmt,
        String piaFeeOcAmt,
        String feeOcYm,
        String piaCsYn,
        String piaSellFeeCrdovrResAmt,
        String csRplcAmt,
        String csRplcAmtCancBlam,
        String ttrmCsAmt,
        String piaFeeEotBlam
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchAggregateSummaryRes")
    public record SearchAggregateSummaryRes(
        String slAmt,
        String feeOcAmt,
        String piaFeeOcAmt,
        String piaCsYn,
        String piaSellFeeCrdovrResAmt,
        String csRplcAmt,
        String csRplcAmtCancBlam,
        String ttrmCsAmt,
        String piaFeeEotBlam
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchDivideRes")
    public record SearchDivideRes(
        String dgCstId,
        String dgCstNm,
        String baseYm,
        String piaSellFeeCrdovrBlam,
        String piaFeeOcAmt,
        String csRplcAmt1,
        String csRplcAmt2,
        String csRplcAmt3,
        String csRplcAmt4,
        String csRplcAmt5,
        String csRplcAmt6,
        String csRplcAmt7,
        String csRplcAmt8,
        String csRplcAmt9,
        String csRplcAmt10,
        String csRplcAmt11,
        String csRplcAmt12,
        String csRplcAmtTot,
        String piaFeeEotBlam
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchDivideSummaryRes")
    public record SearchDivideSummaryRes(
        String piaSellFeeCrdovrBlam,
        String piaFeeOcAmt,
        String csRplcAmt1,
        String csRplcAmt2,
        String csRplcAmt3,
        String csRplcAmt4,
        String csRplcAmt5,
        String csRplcAmt6,
        String csRplcAmt7,
        String csRplcAmt8,
        String csRplcAmt9,
        String csRplcAmt10,
        String csRplcAmt11,
        String csRplcAmt12,
        String csRplcAmtTot,
        String piaFeeEotBlam
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchCodeRes")
    public record SearchCodeRes(
        String codeId,
        String codeName
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SearchPopRes")
    public record SearchPopRes(
        String kwGrpCoCd,
        String slipFtrmDt,
        String feeOcAmt,
        String csRplcAmt,
        String piaSellFeeSmry,
        String dpSlpno
    ) {}

    @ApiModel("WdcbAdvancedSellFeeReplaceDto-SaveReq")
    public record SaveReq(
        String piaSellFeeSmry,
        String kwGrpCoCd,
        String fnlMdfcDtm
    ) {}
}
