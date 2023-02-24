package com.kyowon.sms.wells.web.bond.consultation.dto;

import io.swagger.annotations.ApiModel;

public class WbncCustomerDto {

    @ApiModel(value = "WbncCustomerDto-SearchReq")
    public record SearchReq(
        String schClctamNo,
        String schCstNo,
        String schCntrMpno,
        String schCstNm,
        String schClctamPsic,
        String schSfK,
        String schIstMpno,
        String schDlqMcntStrt,
        String schDlqMcntEnd,
        String schFntDtStrt,
        String schFntDtEnd,
        String seachOjBlamStrt,
        String seachOjBlamEnd,
        String schCstDv,
        String schCpsnRsgYn,
        String schDv
    ) {}

    @ApiModel(value = "WbncCustomerDto-SearchRes")
    public record SearchRes(
        String ctt,
        String fnt,
        String cstNo,
        String cstNm,
        String dlqMcnt,
        String fnlCnslD,
        String ojAmt,
        String ojDp,
        String ojBlam,
        String totDlqAmt,
        String totDlqDp,
        String totDlqBlam,
        String dlqAmt,
        String dlqDp,
        String dlqBlam,
        String mmChramAmt,
        String mmChramDp,
        String mmChramBlam,
        String dlqAddAmt,
        String dlqAddDp,
        String dlqAddBlam,
        String ucAmt,
        String ucDp,
        String ucBlam,
        String totDpAmt,
        String spmtSl,
        String ccam,
        String lsfe,
        String rtlfe1,
        String rtlfeIstm1,
        String rtlfe2,
        String rtlfeIstm2,
        String clctamIchr,
        String cntrMpno,
        String istMpno,
        String vtAcBnk,
        String vtAcNo,
        String sfk,
        String clnPsbl,
        String clnPrcs,
        String cstStat,
        String cvcpInf,
        String unuslArtc
    ) {}

    @ApiModel(value = "WbncCustomerDetailDto-FindReq")
    public static record FindReq(
        String schBaseYm,
        String schCstNo,
        String schCntrNo,
        String schCntrSn
    ) {}

    @ApiModel(value = "WbncCustomerDetailDto-FindRes")
    public static record FindRes(
        String cntrNo,
        String cstNm,
        String bryyMmdd,
        String mpno1,
        String cstNo,
        String sfk,
        String buTp,
        String istllNm,
        String tno,
        String mpno2,
        String cntrAdr,
        String istAdr,
        String prchPdNm,
        String crtlMbStat,
        String rcpD,
        String istD,
        String canD,
        String reqdD,
        String aftn,
        String fntDt,
        String fntStat,
        String seller,
        String sellerCtplc,
        String cltnD1,
        String dsmn,
        String dsmnCtplc,
        String cltnD2,
        String blgBldStore,
        String storeTno,
        String alncCd,
        String alncNm,
        String alncStat,
        String vac,
        String tfDt,
        String clctamIchr,
        String dbtNff,
        String lwm,
        String adnSv,
        String adnSvCd,
        String slStp,
        String clnPsbl1,
        String clnPrcs1,
        String lwscBilAmt,
        String lwscDpAmt,
        String lwscBlam,
        String ucamAdp,
        String unuitmCn,
        String cnslPh,
        String crncyRs,
        String cstPrp,
        String dpr,
        String cnslTp,
        String cstStat,
        String clnPsbl2,
        String clnPrcs2,
        String promD,
        String promHh,
        String promTp,
        String promAmt,
        String promCn
    ) {}

    @ApiModel(value = "WbncCustomerDetailDto-FindCustomerDetailReq")
    public static record FindCustomerDetailReq(
        String schBaseYm,
        String schCstNo,
        String schCntrNo,
        String schCntrSn
    ) {}

    @ApiModel(value = "WbncCustomerDetailDto-FindCustomerDetailRes")
    public static record FindCustomerDetailRes(
        String fnt,
        String bizDv,
        String prdf,
        String pdctNm,
        String cntrNo,
        String cstNm,
        String dlqMcnt,
        String authRsgDt,
        String ojAmt,
        String ojDp,
        String ojBlam,
        String totDlqAmt,
        String totDlqDp,
        String totDlqBlam,
        String mmChramAmt,
        String mmChramDp,
        String mmChramBlam,
        String dlqAddAmt,
        String dlqAddDp,
        String dlqAddBlam,
        String svCs,
        String svDp,
        String svBlam,
        String ucAmt,
        String ucDp,
        String ucBlam,
        String totDpAmt,
        String spmtSl,
        String lsRntf,
        String vacDv,
        String vacBnk,
        String vacNo,
        String ccam,
        String lsfe,
        String rtlfe1,
        String rtlfeIstm1,
        String rtlfe2,
        String rtlfeIstm2,
        String dpr,
        String clctamIchr,
        String tfDt,
        String sfk
    ) {}

    @ApiModel(value = "WbncCustomertDetailDto-FindCounselHistoryReq")
    public static record FindCounselHistoryReq(
        String schBaseYm,
        String schCstNo,
        String schCntrNo,
        String schCntrSn
    ) {}

    @ApiModel(value = "WbncCustomerDetailDto-FindCounselHistoryRes")
    public static record FindCounselHistoryRes(
        String inDt,
        String inIchr,
        String inIchrNm,
        String cnslPh,
        String crncyRs,
        String dprNm,
        String cnslCn
    ) {}

}
