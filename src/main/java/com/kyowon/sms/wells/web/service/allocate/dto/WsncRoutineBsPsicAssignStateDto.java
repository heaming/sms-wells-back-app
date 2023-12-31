package com.kyowon.sms.wells.web.service.allocate.dto;

import java.util.Base64;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.AutomapConstructor;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.annotation.DBDecField;

import io.swagger.annotations.ApiModel;

public class WsncRoutineBsPsicAssignStateDto {
    @ApiModel(value = "WsncRoutineBsPsicAssignStateDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String mngrDvCd,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String ogId,
        String prtnrNo,
        String pdGrpCd,
        String pdCd,
        String startDt,
        String endDt,
        String svTpCd,
        String prgsStatCd,
        String rgsnExcdYn,
        String vacManaYn,
        List<String> wkDvCds

    ) {}

    @ApiModel(value = "WsncRoutineBsPsicAssignStateDto-SearchRes")
    public record SearchRes(
        String snRpblYn,
        String cstGdCd,
        String cstGdNm,
        String spcAsTpCd,
        String cstCnttYn,
        String vstPrgsStatCd,
        String vstPrgsStatNm,
        String statDtl,
        String cntrNo,
        String cntrSn,
        String cntrDtlNo,
        String rcgvpKnm,
        String cralLocaraTno,
        @DBDecField
        String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)
        String cralIdvTno,
        String locaraTno,
        @DBDecField
        String exnoEncr, //전화국번호암호화(전화번호)
        String idvTno,
        String pdCd,
        String pdNm,
        String sellTpCd,
        String sellTpNm,
        String mngerRglvlDvCd,
        String mngerRglvlDvNm,
        String expPart,
        String newAdrZip,
        String rndadr,
        String dgr2LevlOgId,
        String svBizMclsfCd,
        String svBizDclsfCd,
        String svBizDclsfNm,
        String bfVstDuedt,
        String vstDuedt,
        String vstCnfmdt,
        String vstCnfmHh,
        String vstFshDt,
        String vstFshHh,
        String cstUnuitmCn,
        String fstRgstDtm,
        String fstRgstTm,
        String ogId,
        String ogCd,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String pstnDvCd,
        String siteAwAtcCd,
        String siteAwAtcNm,
        String awAmt,
        String svProcsCn,
        String cstSignCn,
        String bgColo,
        String cntrDtlStatCd,
        String cntrDtlStatNm,
        String asMatItmGrpCd,
        String hirFomCd,
        String cstSvAsnNo

    ) {
        @AutomapConstructor
        public SearchRes(
            String snRpblYn,
            String cstGdCd,
            String cstGdNm,
            String spcAsTpCd,
            String cstCnttYn,
            String vstPrgsStatCd,
            String vstPrgsStatNm,
            String statDtl,
            String cntrNo,
            String cntrSn,
            String cntrDtlNo,
            String rcgvpKnm,
            String cralLocaraTno,
            String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)
            String cralIdvTno,
            String locaraTno,
            String exnoEncr, //전화국번호암호화(전화번호)
            String idvTno,
            String pdCd,
            String pdNm,
            String sellTpCd,
            String sellTpNm,
            String mngerRglvlDvCd,
            String mngerRglvlDvNm,
            String expPart,
            String newAdrZip,
            String rndadr,
            String dgr2LevlOgId,
            String svBizMclsfCd,
            String svBizDclsfCd,
            String svBizDclsfNm,
            String bfVstDuedt,
            String vstDuedt,
            String vstCnfmdt,
            String vstCnfmHh,
            String vstFshDt,
            String vstFshHh,
            String cstUnuitmCn,
            String fstRgstDtm,
            String fstRgstTm,
            String ogId,
            String ogCd,
            String ogNm,
            String prtnrKnm,
            String prtnrNo,
            String pstnDvCd,
            String siteAwAtcCd,
            String siteAwAtcNm,
            String awAmt,
            String svProcsCn,
            byte[] cstSignCn,
            String bgColo,
            String cntrDtlStatCd,
            String cntrDtlStatNm,
            String asMatItmGrpCd,
            String hirFomCd,
            String cstSvAsnNo
        ) {
            this(
                snRpblYn,
                cstGdCd,
                cstGdNm,
                spcAsTpCd,
                cstCnttYn,
                vstPrgsStatCd,
                vstPrgsStatNm,
                statDtl,
                cntrNo,
                cntrSn,
                cntrDtlNo,
                rcgvpKnm,
                cralLocaraTno,
                StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr,
                cralIdvTno,
                locaraTno,
                StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr,
                idvTno,
                pdCd,
                pdNm,
                sellTpCd,
                sellTpNm,
                mngerRglvlDvCd,
                mngerRglvlDvNm,
                expPart,
                newAdrZip,
                rndadr,
                dgr2LevlOgId,
                svBizMclsfCd,
                svBizDclsfCd,
                svBizDclsfNm,
                bfVstDuedt,
                vstDuedt,
                vstCnfmdt,
                vstCnfmHh,
                vstFshDt,
                vstFshHh,
                cstUnuitmCn,
                fstRgstDtm,
                fstRgstTm,
                ogId,
                ogCd,
                ogNm,
                prtnrKnm,
                prtnrNo,
                pstnDvCd,
                siteAwAtcCd,
                siteAwAtcNm,
                awAmt,
                svProcsCn,
                cstSignCn != null ? Base64.getEncoder().encodeToString(cstSignCn) : "",
                bgColo,
                cntrDtlStatCd,
                cntrDtlStatNm,
                asMatItmGrpCd,
                hirFomCd,
                cstSvAsnNo
            );
        }
    }
}
