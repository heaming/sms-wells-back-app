package com.kyowon.sms.wells.web.service.visit.dto;

import java.util.Base64;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.AutomapConstructor;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnbSnProcessingPsDto {
    @ApiModel(value = "WsnbSnProcessingPsDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String mngrDvCd,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String ogId,
        String prtnrNo,
        String pdGrpCd
    ) {}

    @ApiModel(value = "WsnbSnProcessingPsDto-SearchPuPartPdReq")
    public record SearchPuPartPdReq(
        @NotNull
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsnbSnProcessingPsDto-SearchCntrs")
    public record SearchCntrs(
        String reYn,
        String bcInMthdCd,
        String hdwrInRsonCd,
        String pblTms,
        String cntrNo,
        String cntrSn,
        String rcgvpKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String sellTpCd,
        String newAdrZip,
        String addr,
        String svBizDclsfCd,
        String lstmmVstCnfmdt,
        String vstCnfmdt,
        String vstDuedt,
        String vstExpHh,
        String ogCd,
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String vstPrgsStatCd,
        String svProcsCn,
        String cancYn,
        String bcPblDvCd,
        String cstSvAsnNo,
        String cstSignCn
    ) {
        @AutomapConstructor
        public SearchCntrs(
            String reYn,
            String bcInMthdCd,
            String hdwrInRsonCd,
            String pblTms,
            String cntrNo,
            String cntrSn,
            String rcgvpKnm,
            String cralLocaraTno,
            String mexnoEncr,
            String cralIdvTno,
            String locaraTno,
            String exnoEncr,
            String idvTno,
            String sapMatCd,
            String pdCd,
            String pdNm,
            String sellTpCd,
            String newAdrZip,
            String addr,
            String svBizDclsfCd,
            String lstmmVstCnfmdt,
            String vstCnfmdt,
            String vstDuedt,
            String vstExpHh,
            String ogCd,
            String ogNm,
            String prtnrNo,
            String prtnrKnm,
            String vstPrgsStatCd,
            String svProcsCn,
            String cancYn,
            String bcPblDvCd,
            String cstSvAsnNo,
            byte[] cstSignCn
        ) {
            this(
                reYn,
                bcInMthdCd,
                hdwrInRsonCd,
                pblTms,
                cntrNo,
                cntrSn,
                rcgvpKnm,
                cralLocaraTno,
                StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr,
                cralIdvTno,
                locaraTno,
                StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr,
                idvTno,
                sapMatCd,
                pdCd,
                pdNm,
                sellTpCd,
                newAdrZip,
                addr,
                svBizDclsfCd,
                lstmmVstCnfmdt,
                vstCnfmdt,
                vstDuedt,
                vstExpHh,
                ogCd,
                ogNm,
                prtnrNo,
                prtnrKnm,
                vstPrgsStatCd,
                svProcsCn,
                cancYn,
                bcPblDvCd,
                cstSvAsnNo,
                cstSignCn != null ? Base64.getEncoder().encodeToString(cstSignCn) : ""
            );
        }
    }

    @ApiModel(value = "WsnbSnProcessingPsDto-SearchRatio")
    public record SearchRatio(
        Long bsCntrTotal,
        Long curMmRe,
        Long procsTotal,
        Long curMmReProcs,
        Long scanTotal,
        Long curMmReScan,
        Long handTotal,
        Long curMmReHand,
        Long yetProcs,
        Long curMmReYetProcs,
        Long stpTotal,
        Long curMmReStp,
        Double ratProcsTotal,
        Double ratReProcs,
        Double ratStickTotal,
        Double ratReStick,
        Double ratScanTotal,
        Double ratReScan
    ) {}

    @ApiModel(value = "WsnbSnProcessingPsDto-SearchPuPartPdRes")
    public record SearchPuPartPdRes(
        String pdCd,
        String pdNm
    ) {}

}
