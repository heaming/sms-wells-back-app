package com.kyowon.sms.wells.web.service.allocate.dto;

import java.util.Base64;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.AutomapConstructor;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsncBsEngineerAllocateSearchDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncBsEngineerAllocateSearchDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String ogId,
        String engId,
        String rgsnYn,
        List<String> pdGrpCds,
        List<String> svBizDclsfCds
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncBsEngineerAllocateSearchDto-SearchRes")
    public record SearchRes(
        String barcodeIssue,
        String cstGd,
        String spcAsTp,
        String contYn,
        String vstPrgsStat,
        String statNmDtl,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String pdNm,
        String sellTp,
        String mngerRglvlDv,
        String newAdrZip,
        String adrDtl,
        String ogNm,
        String svBizDclsf,
        String bfVstDuedt,
        String vstDuedt,
        String vstExpDuedt,
        String vstExpHh,
        String vstFshDt,
        String vstFshHh,
        String cstUnuitmCn,
        String fstRgstDt,
        String fstRgstTm,
        String engOgNm,
        String prtnrKnm,
        String prtnrNo,
        String hirFom,
        String siteAwAtcCd,
        String feamSite,
        String svProcsCn,
        String cstSignCn,
        String chngs,
        String cstSvAsnNo
    ) {
        @AutomapConstructor
        public SearchRes(
            String barcodeIssue,
            String cstGd,
            String spcAsTp,
            String contYn,
            String vstPrgsStat,
            String statNmDtl,
            String cntrNo,
            String cntrSn,
            String cstKnm,
            String locaraTno,
            String exnoEncr,
            String idvTno,
            String cralLocaraTno,
            String mexnoEncr,
            String cralIdvTno,
            String pdNm,
            String sellTp,
            String mngerRglvlDv,
            String newAdrZip,
            String adrDtl,
            String ogNm,
            String svBizDclsf,
            String bfVstDuedt,
            String vstDuedt,
            String vstExpDuedt,
            String vstExpHh,
            String vstFshDt,
            String vstFshHh,
            String cstUnuitmCn,
            String fstRgstDt,
            String fstRgstTm,
            String engOgNm,
            String prtnrKnm,
            String prtnrNo,
            String hirFom,
            String siteAwAtcCd,
            String feamSite,
            String svProcsCn,
            byte[] cstSignCn,
            String chngs,
            String cstSvAsnNo
        ) {
            this(
                barcodeIssue,
                cstGd,
                spcAsTp,
                contYn,
                vstPrgsStat,
                statNmDtl,
                cntrNo,
                cntrSn,
                cstKnm,
                locaraTno,
                StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr,
                idvTno,
                cralLocaraTno,
                StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr,
                cralIdvTno,
                pdNm,
                sellTp,
                mngerRglvlDv,
                newAdrZip,
                adrDtl,
                ogNm,
                svBizDclsf,
                bfVstDuedt,
                vstDuedt,
                vstExpDuedt,
                vstExpHh,
                vstFshDt,
                vstFshHh,
                cstUnuitmCn,
                fstRgstDt,
                fstRgstTm,
                engOgNm,
                prtnrKnm,
                prtnrNo,
                hirFom,
                siteAwAtcCd,
                feamSite,
                svProcsCn,
                cstSignCn != null ? Base64.getEncoder().encodeToString(cstSignCn) : "",
                chngs,
                cstSvAsnNo
            );
        }
    }

    @ApiModel(value = "WsncBsEngineerAllocateSearchDto-AggregateRes")
    public record AggregateRes(
        String vstPrgsStatCd,
        String statNmDtl
    ) {
        public AggregateRes(String vstPrgsStatCd, String statNmDtl) {
            this.vstPrgsStatCd = vstPrgsStatCd;
            this.statNmDtl = statNmDtl;
        }
    }
}
