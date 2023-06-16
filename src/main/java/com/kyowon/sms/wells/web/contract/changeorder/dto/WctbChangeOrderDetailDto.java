package com.kyowon.sms.wells.web.contract.changeorder.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

public class WctbChangeOrderDetailDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WctbChangeOrderDetailDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel("WctbChangeOrderDetailDto-SearchRes")
    public record SearchRes(
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrChHistRes> searchTbSsctCntrChHistResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrDchHistRes> searchTbSsctCntrDchHistResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrCstRelRes> searchTbSsctCntrCstRelResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrPrtnrRelRes> searchTbSsctCntrPrtnrRelResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrAdrRelRes> searchTbSsctCntrAdrRelResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrAdrChHistRes> searchTbSsctCntrAdrChHistResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrStlmRelRes> searchTbSsctCntrStlmRelResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrStlmChHistRes> searchTbSsctCntrStlmChHistResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrWellsDchHistRes> searchTbSsctCntrWellsDchHistResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctCntrRelRes> searchTbSsctCntrRelResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctMchnChHistRes> searchTbSsctMchnChHistResList,
        List<WctbChangeOrderDetailDto.SearchTbSsctRentalRstlChHistRes> searchTbSsctRentalRstlChHistResList
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrChHistRes")
    public record SearchTbSsctCntrChHistRes(
        String cntrNo,
        String histStrtDtm,
        String histEndDtm,
        String cntrCstNo,
        String copnDvCd,
        String sellInflwChnlDtlCd,
        String sellOgTpCd,
        String sellPrtnrNo,
        String cntrTpCd,
        String cntrNatCd,
        String cntrPrgsStatCd,
        String cstStlmInMthCd,
        String prrRcpCntrYn,
        String cntrTempSaveDtm,
        String cntrRcpFshDtm,
        String cntrStlmFshDtm,
        String cntrCnfmAprAkDtm,
        String cntrCnfmAprDtm,
        String cntrCnfmDtm,
        String cntrCanDtm,
        String cntrCanRsonCd,
        String cntrPrgsStatMoCn,
        String dsbGurTpCd,
        String cntrInflwPhDvCd,
        String pspcCstId,
        String cntrChRcpId,
        String dcevdnDocId,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrDchHistRes")
    public record SearchTbSsctCntrDchHistRes(
        String cntrDtlNo,
        String histStrtDtm,
        String histEndDtm,
        String basePdCd,
        String hgrPdCd,
        String pdQty,
        String sellTpCd,
        String sellTpDtlCd,
        String cntrDtlStatCd,
        String cntrPtrmUnitCd,
        String cntrPtrm,
        String cntrPdStrtdt,
        String cntrPdEnddt,
        String stplPtrmUnitCd,
        String stplPtrm,
        String svPtrmUnitCd,
        String svPrd,
        String cntrwTpCd,
        String blgCrpCd,
        String rveCrpCd,
        String coCd,
        String booSellTpCd,
        String pdGdCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String sellDscDvCd,
        String sellDscrCd,
        String sellDscCtrAmt,
        String sellDscTpCd,
        String stlmTpCd,
        String crncyDvCd,
        String apyExcr,
        String pdBaseAmt,
        String sellAmt,
        String dscAmt,
        String fnlAmt,
        String vat,
        String cntrAmt,
        String cntramDscAmt,
        String istmMcn,
        String istmPcamAmt,
        String istmIntAmt,
        String mmIstmAmt,
        String crpUcAmt,
        String sellFee,
        String cntrTam,
        String ackmtPerfRt,
        String ackmtPerfAmt,
        String cvfPerfAmt,
        String feeAckmtCt,
        String feeAckmtBaseAmt,
        String feeFxamYn,
        String sppDuedt,
        String resubYn,
        String rstlYn,
        String frisuYn,
        String frisuDsbTpCd,
        String txinvPblOjYn,
        String alncmpCd,
        String alncmpCntrDrmVal,
        String smtplId,
        String smtplSn,
        String bfOrdNo,
        String cntrChRcpId,
        String cntrChSn,
        String cntrChDtlAkCn,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrCstRelRes")
    public record SearchTbSsctCntrCstRelRes(
        String cntrCstRelId,
        String vlStrtDtm,
        String vlEndDtm,
        String cntrUnitTpCd,
        String cntrNo,
        String dtlCntrNo,
        String dtlCntrSn,
        String copnDvCd,
        String cstNo,
        String cntrCstRelTpCd,
        String cntrtRelCd,
        String insiCstTpCd,
        String lrnnGryCd,
        String cntrChRcpId,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrPrtnrRelRes")
    public record SearchTbSsctCntrPrtnrRelRes(
        String cntrPrtnrRelId,
        String vlStrtDtm,
        String vlEndDtm,
        String cntrNo,
        String ogTpCd,
        String prtnrNo,
        String cntrPrtnrTpCd,
        String cntrPrtnrTpDrmVal,
        String ogId,
        String rcpAoffceCd,
        String prrBizRgrYn,
        String alncPrtnrDrmVal,
        String alncPrtnrIdnrNm,
        String alncPrtnrDrmDvCd,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrAdrRelRes")
    public record SearchTbSsctCntrAdrRelRes(
        String cntrAdrRelId,
        String vlStrtDtm,
        String vlEndDtm,
        String adrpcTpCd,
        String cntrUnitTpCd,
        String cntrNo,
        String dtlCntrNo,
        String dtlCntrSn,
        String cntrAdrpcId,
        String vstRqdt,
        String vstAkStrtHm,
        String vstAkEndHm,
        String urgtOjYn,
        String cntrwPosndTpCd,
        String sppMthdTpCd,
        String sppIchrAoffceCd,
        String sppIchrUsrId,
        String cntrChRcpId,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrAdrChHistRes")
    public record SearchTbSsctCntrAdrChHistRes(
        String cntrAdrRelId,
        String histStrtDtm,
        String histEndDtm,
        String cntrNo,
        String cntrCstNo,
        String cntrtRelCd,
        String rcgvpKnm,
        String rcgvpEnm,
        String copnDvCd,
        String crpSpmtDrmNm,
        String natCd,
        String adrDvCd,
        String cralTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String emadr,
        String cnrSppYn,
        String ogTpCd,
        String bldCd,
        String mvsDstcRcvryBaseDtm,
        String mvsDstcRcvryDvCd,
        String mvsDstcRcvryDtm,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrStlmRelRes")
    public record SearchTbSsctCntrStlmRelRes(
        String cntrStlmRelId,
        String vlStrtDtm,
        String vlEndDtm,
        String cntrUnitTpCd,
        String cntrStlmId,
        String cntrNo,
        String dtlCntrNo,
        String dtlCntrSn,
        String dpTpCd,
        String rveDvCd,
        String islndIncmdcTpCd,
        String stlmAmt,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrStlmChHistRes")
    public record SearchTbSsctCntrStlmChHistRes(
        String cntrStlmId,
        String histStrtDtm,
        String histEndDtm,
        String cstNo,
        String cntrNo,
        String cntrtRelCd,
        String owrKnm,
        String owrEnm,
        String dpTpCd,
        String bnkCd,
        String cdcoCd,
        String acnoEncr,
        String crcdnoEncr,
        String cardExpdtYm,
        String mlgTpCd,
        String mlgDrmVal,
        String mpyBsdt,
        String hsCtfYn,
        String dcevdnDocId,
        String pyerNo,
        String vncoDvCd,
        String fnitAprRsCd,
        String fnitAprFshDtm,
        String fnitRsgFshDtm,
        String acFntImpsCd,
        String cardFntImpsCd,
        String acChAkDvCd,
        String rveCrpCd,
        String fntEvidDrmVal,
        String reuseOjYn,
        String bfCntrStlmId,
        String mvsDstcRcvryBaseDtm,
        String mvsDstcRcvryDvCd,
        String mvsDstcRcvryDtm,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrWellsDchHistRes")
    public record SearchTbSsctCntrWellsDchHistRes(
        String cntrNo,
        String cntrSn,
        String histStrtDtm,
        String histEndDtm,
        String frisuBfsvcPtrmUnitCd,
        String frisuBfsvcPtrmN,
        String frisuAsPtrmUnitCd,
        String frisuAsPtrmN,
        String istDt,
        String reqdDt,
        String cpsDt,
        String prmApyDvCd,
        String prmPtrmMcn,
        String sellEvCd,
        String bfsvcBzsDvCd,
        String splyBzsDvCd,
        String ocoCpsBzsDvCd,
        String hcrDvCd,
        String fmmbN,
        String frisuRcvryTpCd,
        String istPlcTpCd,
        String wrfrIstMthCd,
        String wtqltyTstYn,
        String srcwtTpCd,
        String wprsItstTpCd,
        String useElectTpCd,
        String tbhsEyn,
        String stvlvEyn,
        String kumonItrdtDvCd,
        String kumonCstIdkVal,
        String kumonRcomrIdkVal,
        String otsdLkDrmVal,
        String frisuMshCrtYn,
        String istMmBilMthdTpCd,
        String coIstDvCd,
        String coIstMngtDvCd,
        String coIstUswyCd,
        String istAkArtcMoCn,
        String sconCn,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctCntrRelRes")
    public record SearchTbSsctCntrRelRes(
        String cntrRelId,
        String vlStrtDtm,
        String vlEndDtm,
        String cntrUnitTpCd,
        String cntrRelTpCd,
        String cntrRelDtlCd,
        String baseCntrNo,
        String ojCntrNo,
        String cntrCstGrpId,
        String cntrRelDtlCn,
        String otsdLkDrmVal,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctMchnChHistRes")
    public record SearchTbSsctMchnChHistRes(
        String cntrNo,
        String cntrSn,
        String stplTn,
        String stplChSn,
        String stplTpCd,
        String stplPtrmUnitCd,
        String stplPtrm,
        String stplStrtdt,
        String stplEnddt,
        String stplDscAmt,
        String rstlStatCd,
        String stplRcpDtm,
        String rcpOgTpCd,
        String rcpPrtnrNo,
        String feeAckmtCt,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}

    @ApiModel("WctbChangeOrderDetailDto-SearchTbSsctRentalRstlChHistRes")
    public record SearchTbSsctRentalRstlChHistRes(
        String cntrNo,
        String cntrSn,
        String stplTn,
        String stplChSn,
        String stplTpCd,
        String stplPtrmUnitCd,
        String stplPtrm,
        String stplStrtdt,
        String stplEnddt,
        String stplDscAmt,
        String rstlStatCd,
        String stplRcpDtm,
        String rcpOgTpCd,
        String rcpPrtnrNo,
        String feeAckmtCt,
        String feeAckmtBaseAmt,
        String feeFxamYn,
        String ackmtPerfRt,
        String ackmtPerfAmt,
        String notyFwId,
        String stplCnfmDtm,
        String cnfmUsrId,
        String cntrChFshDtm,
        String stplCanDtm,
        String stplCanUsrId,
        String stplWdwlDtm,
        String stplWdwlUsrId,
        String stplWdwlCn,
        String stplDscStrtdt,
        String stplDscEnddt,
        String stplDscAcuAmt,
        String stplDscBorAmt,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {}
}
