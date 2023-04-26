package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WctaOrderDetailMngtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //주문상세조회/관리(렌탈) - 주문상세조회(렌탈-일반/계약번호/고객번호/설치자정보조회) Search Request Dto
    @ApiModel(value = "WctaOrderDetailMngtDto-SearchReq")
    public record SearchReq(
        String prdEnqry,
        String strtDt,
        String endDt,
        String rentalNmn,
        String hcsfVal,
        String hcsfMcsfVal,
        String pdCd,
        String pdNm,
        String alncmpCd,
        String sellEvCd,
        String sellPrtnrNo,
        List<String> dgr1LevlOgId,
        List<String> dgr2LevlOgId,
        List<String> dgr3LevlOgId,
        String cndtSellTpCd,
        List<String> sellOgTpCd,
        String booSellYn,
        String canYn,
        String slYn,
        String cntrNo,
        String cntrSn,
        String cntrCstNo,
        String istCralTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String rcgvpKnm
    ) {}

    //주문상세조회/관리(멤버쉽) - 주문상세조회(멤버쉽-일반/계약번호/게인정보조회) Search Request Dto
    @ApiModel(value = "WctaOrderDetailMngtDto-SearchOrderDetailMshPagesReq")
    public record SearchOrderDetailMshPagesReq(
        String rcpDateDv,
        String strtDt,
        String endDt,
        String dateSeltDv,
        String choStrtDt,
        String choEndDt,
        String sellTpCd,
        String cntrwTpCd,
        String sellInflwChnlDtlCd,
        String hcsfVal,
        String hcsfMcsfVal,
        String pdCd,
        String pdNm,
        String sellPrtnrNo,
        String cntrRcpFshDtYn,
        String cntrNo,
        String cntrSn,
        String bryyMmddEntrpNoCbno,
        String bryyMmdd,
        String sexDvCd,
        String bzrno,
        String cstKnm,
        String cntrCralTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrCstNo,
        String cntrPdEnddtYn
    ) {}

    //주문상세조회/관리(일시불) - 주문상세조회(일시불-일반/계약번호/게인정보조회) Search Request Dto
    @ApiModel(value = "WctaOrderDetailInqrDto-SearchOrderDetailSnglPmntPagesReq")
    public record SearchOrderDetailSnglPmntPagesReq(
        String searchGbn,
        String bryyMmdd,
        String bzrno,
        String sexGbn,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrCstNo,
        String cntrCanYn,
        String cntrNo,
        String cntrSn,
        String prdEnqry,
        String strtDt,
        String endDt,
        String hcsfVal,
        String hcsfMcsfVal,
        String pdCd,
        String pdNm,
        String alncmpCd,
        String sellEvCd,
        String sellPrtnrNo,
        List<String> dgr1LevlOgId,
        List<String> dgr2LevlOgId,
        List<String> dgr3LevlOgId,
        List<String> etcDv,
        List<String> sellOgTpCd
    ) {}

    //주문상세조회/관리(정기배송) - 주문상세조회(정기배송-일반/계약번호조회) Search Request Dto
    @ApiModel(value = "WctaOrderDetailMngtDto-SearchOrderDetailRglrDlvrPagesReq")
    public record SearchOrderDetailRglrDlvrPagesReq(
        String prdEnqry,
        String strtDt,
        String endDt,
        String cntrNo,
        String cntrSn,
        String cntrCstNo,
        String canYn,
        String slYn,
        List<String> sellOgTpCd,
        List<String> dgr1LevlOgId,
        List<String> dgr2LevlOgId,
        List<String> dgr3LevlOgId,
        String sellPrtnrNo,
        List<String> mchnDv
    ) {}

    //멤버쉽 확정관리 - 멤버쉽 확정관리(멤버쉽-일반/계약번호/게인정보조회) Search Request Dto
    @ApiModel(value = "WctaOrderDetailMngtDto-SaveMembershipConfirmsReq")
    public record SaveMembershipConfirmsReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        int cntrSn,
        @NotBlank
        String cntrCnfmYn,
        String cntrCnfmDt,
        String cntrPdStrtdt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //주문상세조회/관리(렌탈) - 주문상세조회(렌탈-일반/계약번호/고객번호/설치자정보조회) Search Result Dto
    @ApiModel("WctaOrderDetailMngtDto-SearchRes")
    public record SearchRes(
        String cntrDtlNo,
        String sellTpDtlNm,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNm,
        String dgr3LevlOgCd,
        String sellPrtnrNo,
        String prtnrKnm,
        String sellPrtnrCralTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cntrDt,
        String cltnDt,
        String cstKnm,
        String bryy,
        String bzrNo,
        String sexDvNm,
        String cntrCstNo,
        String adrZip,
        String cntrCstRnadr,
        String cntrCstRdadr,
        String rcgvpKnm,
        String istCralTno,
        String istCralLocaraTno,
        String istMexnoEncr,
        String istCralIdvTno,
        String istAdrZip,
        String istRnadr,
        String istRdadr,
        String sppOrdNo,
        String pdctIdno,
        String istAkDt,
        String sellInflwChnlDtlNm,
        String copnDvNm,
        String pdClsfNm,
        String pdNm,
        String basePdCd,
        String pdTpNm,
        String svPrd,
        String svTpCd,
        String svTpNm,
        String cntrRcpFshDt,
        String sppDuedt,
        String istDt,
        String slDt,
        String istmMcn,
        String cntrPdEnddt,
        String canDt,
        String reqdDt,
        String exnReqdDt,
        String recapDutyPtrmN,
        String cntrAmt,
        String cntrCtrAmt,
        String pdBaseAmt,
        String ctrVal,
        String rentalAmt2,
        String rentalDscAmt2,
        String rentalDscDfam,
        String booSellYn,
        String mchnChYn,
        String mchnCpsApyr,
        String ackmtPerfAmt,
        String ackmtPerfRt,
        String feeAckmtBaseAmt,
        String feeFxamYn,
        String dscApyDtlCd,
        String dscApyTpCd,
        String dscPmotCd,
        String mchnChTpCd,
        String ojCntrDtlNo,
        String ojBasePdCd,
        String ojPdNm,
        String bogoCd,
        String bogoPdCd,
        String bogoPdNm,
        String mpyMthdTpNm,
        String aftnInf,
        String aftnOwrKnm,
        String sellEvCd,
        String alncmpCd,
        String alncmpNm,
        String alncStatTpNm,
        String alncmpCstCd,
        String alncmpPrtnrNo,
        String cttRsCd,
        String cttRsNm,
        String fstRgstPrgId,
        String fstRgstUsrId,
        String fstRgstUsrNm,
        String fstPerfYm,
        String fnlPerfYm,
        String fstMngtYm,
        String fnlMngtYm,
        String pmotCd,
        String pmotFvrMcn,
        String pmotFvrAmt,
        String frisuBfsvcPtrmN,
        String canPdGdCd,
        String freExpnYn,
        String freExpnCnfmStrtdt,
        String freExpnCnfmYn,
        String freExpnCnfmDt,
        String combiNm,
        String fstRgstDt,
        String fstRgstTm,
        String z11Yn,
        String z13Yn,
        String z15Yn,
        String w22Yn,
        String cntrCralTno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String dntcYn,
        String stplDscAmt,
        String stplStrtdt,
        String stplCanDt,
        String stplRcpDt,
        String stplRentalTn,
        String mshCntrRcpFshDt,
        String mshCntrTempSaveDt,
        String mshReqdDt,
        String mshCntrPdEnddt,
        String lcet13,
        String svAmt,
        String rentalTn,
        String fgpt1PdNm,
        String fgpt1PdCd,
        String fgpt1Qty,
        String fgpt2PdNm,
        String fgpt2PdCd,
        String fgpt2Qty,
        String fgpt3PdNm,
        String fgpt3PdCd,
        String fgpt3Qty,
        String fgpt4PdNm,
        String fgpt4PdCd,
        String fgpt4Qty,
        String fgpt5PdNm,
        String fgpt5PdCd,
        String fgpt5Qty,
        String fgpt6PdNm,
        String fgpt6PdCd,
        String fgpt6Qty,
        String fgpt7PdNm,
        String fgpt7PdCd,
        String fgpt7Qty,
        String fgpt8PdNm,
        String fgpt8PdCd,
        String fgpt8Qty,
        String lcet15,
        String lcrpmn,
        String lcrpam,
        String lcetc6,
        String istAkArtcMoCn,
        String sconCn,
        String useElectTpCd,
        String lcrept,
        String lcck06,
        String levels,
        String safekey,
        String alncPrtnrDrmDvCd,
        String alncPrtnrDrmDvNm,
        String cntrtRelNm,
        String lcck07,
        String bryyBzrno,
        String ftfDvCd,
        String lcet10,
        String fnlAmt,
        String rentalAmt,
        String fnlPdCd,
        String fnlPdNm,
        String rentalPtrm,
        String rentalPtrm2,
        String leaseDvNm,
        String pmotTpNm,
        String mchnChTpNm,
        String dscApyDtlCdNm,
        String svPrdInfo,
        String lcflg3,
        String cntrChDtlRsonCd,
        String rstlYn,
        String pdChBfNmnN,
        String adnSvYn,
        String prmApyDvCd,
        String mpyBsdt,
        String stlmFnit,
        String vstRqdt,
        String pdctReqdRqdt,
        String lcremy,
        String lccany,
        String frisuYn,
        String lcpgubNm,
        String sppMthdTpNm,
        String wprsItstTpNm,
        String srcwtTpNm,
        String wtqltyTstYn,
        String lcchk8,
        String wrfrIstMthNm,
        String frisuRcvryTpNm,
        String dgr3LevlOgId,
        String pdQty,
        String highPdClsfNm,
        String middlePdClsfNm
    ) {}

    //주문상세조회/관리(멤버쉽) - 주문상세조회(멤버쉽-일반/계약번호/게인정보조회) Search Result Dto
    @ApiModel("WctaOrderDetailMngtDto-SearchOrderDetailMshPagesRes")
    public record SearchOrderDetailMshPagesRes(
        String cntrNo,
        String cntrSn,
        String cntrDtlNo,
        String cstKnm,
        String rcgvpKnm,
        String ojSellTpNm,
        String mshDvNm,
        String pdClsfNm,
        String pdCd,
        String pdNm,
        String pdQty,
        String svTpNm,
        String stplPtrm,
        String svPrd,
        String frisuBfsvcPtrmN,
        String spayFrisuBfsvcPtrmN,
        String pdLclsfNm,
        String pdDclsfNm,
        String cntrDtlStatNm,
        String dscApyTpCdNm,
        String dscApyDtlCdNm,
        String feeAckmtCt,
        String ackmtPerfAmt,
        String cntrCtrAmt,
        String stlmTpNm,
        String prmPtrmMcn,
        String adjDvNm,
        String sellEvNm,
        String frisuMshCrtYn,
        String cntrRcpFshDt,
        String cntrCnfmYn,
        String cntrCnfmDt,
        String cntrTempSaveDt,
        String hcrDuedt,
        String istDt,
        String dutyExnDtFrisu,
        String dutyExnDtRecap,
        String cntrPdEnddt,
        String canDt,
        String vstDuedt,
        String cttFshDt,
        String cntrCanRsonCd,
        String cntrCanRsonNm,
        String hcrOstrDvNm,
        String ojIstDt,
        String ojCanDt,
        String ojEnddt,
        String cttRsCd,
        String cttRsNm,
        String cttRsNmUsrId,
        String sellInflwChnlDtlNm,
        String sellPrtnrNo,
        String prtnrKnm,
        String ogCd,
        String rveCd,
        String ichrUsrId,
        String mpyMthdTpNm,
        String aftnInf,
        String mpyBsdt,
        String cntrCstNo,
        String cntrCralTno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String adrZip,
        String cntrCstRnadr,
        String cntrCstRdadr,
        String istCralTno,
        String istCralLocaraTno,
        String istMexnoEncr,
        String istCralIdvTno,
        String istAdrZip,
        String istRnadr,
        String istRdadr,
        String lcck05,
        String combiDv,
        String sdingCntrNo,
        String feeFxamYn,
        String sconCn,
        String ordCnfp,
        String fstRgstDt,
        String fstRgstTm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fnlMdfcDt,
        String fnlMdfcTm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String levelsnm,
        String dgr3LevlDgPrtnrNo,
        String bryyBzrno,
        String cntrwPosndTpNm,
        String stlmFnit,
        String cardExpdtYm,
        String pdBaseAmt,
        String pyMcn,
        String dutyPtrmN,
        String dscAmt,
        String prmMcn,
        String txinvPblOjYn,
        String vstNmnN,
        String svVstPrdCd,
        String bryyMmdd,
        String bzrno,
        String sexDvCd,
        String ojSellTpCd,
        String mshDvCd,
        String sellInflwChnlDtlCd,
        String refPdClsfVal,
        String dgr3LevlOgId,

        String dgr3LevlDgPrtnrNm
    ) {}

    //주문상세조회/관리(일시불) - 주문상세조회(일시불-일반/계약번호조회) Search Result Dto
    @ApiModel(value = "WctaOrderDetailInqrDto-SearchOrderDetailSnglPmntPagesRes")
    public record SearchOrderDetailSnglPmntPagesRes(
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String copnDvCd,
        String copnDvNm,
        String bzrno,
        String bryyMmdd,
        String sexDvCd,
        String rnmno,
        String rnmnoDvCd,
        String rnmnoEncr,
        String basePdCd,
        String basePdNm,
        String dscApyTpCd,
        String dscApyTpNm,
        String dscApyDtlCd,
        String dscApyDtlNm,
        String cntrRcpFshDtm,
        String sppDuedt,
        String slDt,
        String cntrCanDt,
        String cpsDt,
        String slPasgDt,
        String slCnfmYn,
        String cntrCanRsonCd,
        String canRsonNm,
        String cntrChDtlRsonCd,
        String cntrChDtlRsonNm,
        String frisuYn,
        String freExpnCnfmDtm,
        String cttTpCd,
        String cttTpNm,
        String iostDtlCd,
        String sppIvcCrtDtm,
        String booSellYn,
        String cntrTpCd,
        String cntrTpNm,
        String sellPrtnrNm,
        String fstRgstUsrId,
        String fstRgstDeptId,
        String rveCd,
        String uswyDv,
        String svPrd,
        String frisuAsPtrmN,
        String frisuBfsvcPtrmN,
        String recapMshYn,
        String alncmpNm,
        String sellEvCd,
        String sellEvNm,
        String fnlAmt,
        String sellAmt,
        String vat,
        String cntrAmt,
        String istmIntAmt,
        String feeAckmtBaseAmt,
        String crpUc,
        String totDscAmt,
        String feeAckmtCt,
        String ackmtPerfAmt,
        String feeAckmtTotAmt,
        String feeFxamYn,
        String pdSaleFee,
        String cashBlam,
        String istmMcn,
        String mmIstmAmt,
        String istmPcamAmt,
        String cntrCstNo,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String rcgvpKnm,
        String istlcCralLocaraTno,
        String istlcMexnoEncr,
        String istlcCralIdvTno,
        String istlcAdrZip,
        String istlcAdr,
        String istlcDadr,
        String cntrRelDtlNm,
        String alncmpCntrDrmVal,
        String alncmpCd,
        String alncPrtnrDrmVal,
        String spcOrdDv,
        String sppOrdIvcNo,
        String basePdInfo,
        String basePdCd2,
        String pdQty,
        String fgptPdNm1,
        String fgptPdNm2,
        String fgptPdNm3,
        String fgptPdNm4,
        String bfsvcBzsDvCd,
        String bfsvcBzsDvNm,
        String splyBzsDvCd,
        String splyBzsDvNm,
        String cntrCanDtm
    ) {}

    //주문상세조회/관리(정기배송) - 주문상세조회(정기배송-일반/계약번호조회) Search Result Dto
    @ApiModel("WctaOrderDetailMngtDto-SearchOrderDetailRglrDlvrPagesRes")
    public record SearchOrderDetailRglrDlvrPagesRes(
        String cntrDtlNo,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNm,
        String dgr3LevlOgCd,
        String sellPrtnrNo,
        String prtnrKnm,
        String sellPrtnrCralTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String cstKnm,
        String cstNo,
        String adrZip,
        String cntrCstRnadr,
        String cntrCstRdadr,
        String rcgvpKnm,
        String shpadrCralTno,
        String shpadrCralLocaraTno,
        String shpadrMexnoEncr,
        String shpadrCralIdvTno,
        String shpadrAdrZip,
        String shpadrRnadr,
        String shpadrRdadr,
        String sellInflwChnlDtlNm,
        String empDvVal,
        String copnDvNm,
        String mchnSellTpNm,
        String mchnCntrNo,
        String mchnRcgvpKnm,
        String mchnPdCd,
        String mchnPdNm,
        String mchnSvTpNm,
        String mchnSvPrd,
        String mchnPdMclsfNm,
        String mchnPdLclsfNm,
        String pdTpCm,
        String recapDutyPtrmN,
        String stplPtrm,
        String fnlAmt,
        String sellAmt,
        String vat,
        String cntrAmt,
        String mmBilAmt,
        String pdBaseAmt,
        String ackmtPerfRt,
        String ackmtPerfAmt,
        String dscMcn,
        String ctrAmt,
        String svTpNm,
        String spcYn,
        String svPrd,
        String sppFshDt,
        String sppMthdTpNm,
        String lctjobNm,
        String rglrSppBilDvNm,
        String mpyMthdTpNm,
        String txinvPblOjYn,
        String frisuBfsvcPtrmN,
        String lcmcnt,
        String lcck05Nm,
        String rcpPkgYn,
        String rcpPkgCd,
        String rcpPkgNm,
        String pkgYn,
        String pkgPrcApy,
        String pkgclsfNm,
        String pkgCd,
        String pkgNm,
        String lcscnt,
        String freCnfmYn,
        String ordCnfmYn,
        String dCnfmYn,
        String frisuYn,
        String lcecdd,
        String cntrRcpFshDt,
        String sppDuedt,
        String fstSppFshDt,
        String istDt,
        String cntrPdEnddt,
        String cltnRqdt,
        String reqdDt,
        String sppFshM,
        String cntrCnfmDtm,
        String slDtm,
        String freSppFshD,
        String lkCntrDtlNo,
        String lkCstKnm,
        String lkPdCd,
        String lkPdNm,
        String lkIstDt,
        String lkIstNmnN,
        String lkReqdDt,
        String pmotNm,
        String pmotTpCd,
        String pmotCd,
        String pmotSn,
        String fstRgstDt,
        String fstRgstTm,
        String sfkVal,
        String LevelsNm,
        String rveCd,
        String rveNm,
        String bizUsrId,
        String bizUsrNm,
        String cntrCralTno,
        String cntrtRelNm,
        String lcmmdc,
        String alncStatTpCd,
        String alncStatTpNm,
        String alncmpCd,
        String alncmpNm,
        String cltnCntrDtlStatNm,
        String cttRsCd,
        String cttRsNm,
        String sconCn,
        String cntrCstNo,
        String refPdClsfVal,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String mchnPdPrpVal01
    ) {}
}
