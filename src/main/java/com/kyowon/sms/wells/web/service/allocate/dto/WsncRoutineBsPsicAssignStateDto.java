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
        String chk,
        String snRpblYn,
        String cntrCstNo,//new
        String cstGdCd,
        String cstGdNm,

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
        String sellTpDtlCd,
        String sellTpDtlNm,

        String mngerRglvlDvCd,
        String mngerRglvlDvNm,
        String newAdrZip,
        String rndadr,
        String rltdSvBizDclsfCd,//new

        String clsfCdSrnPrntCn,//new
        String bfVstDuedt,
        String vstDuedt,
        String vstCnfmdt,
        String vstCnfmHh,

        String vstFshDt,
        String vstFshHh,
        String cstUnuitmCn,
        String fstRgstDtm,
        String l1HgrOgCd,//new

        String l2HgrOgCd,//new
        String l3HgrOgCd,//new
        String l1HgrOgNm,//new
        String l2HgrOgNm,//new
        String l3HgrOgNm,//new

        String cnfmPsicDvCd,//new
        String cnfmPsicPrtnrOgTpCd,//new
        String cnfmPsicPrtnrNo,//new
        String prtnrKnm,
        String pstnDvCd,

        String pstnDvNm,//new
        String siteAwAtcCd,
        String siteAwAtcNm,
        String engPrtnrGdCd,//new
        String awAmt,

        String svProcsCn,
        String cstSignCn,
        String bgColo,
        String cntrDtlStatCd,
        String cntrDtlStatNm,

        String cstSvAsnNo
    ) {
        @AutomapConstructor
        public SearchRes(
            String chk,
            String snRpblYn,
            String cntrCstNo,//new
            String cstGdCd,
            String cstGdNm,

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
            String sellTpDtlCd,
            String sellTpDtlNm,

            String mngerRglvlDvCd,
            String mngerRglvlDvNm,
            String newAdrZip,
            String rndadr,
            String rltdSvBizDclsfCd,//new

            String clsfCdSrnPrntCn,//new
            String bfVstDuedt,
            String vstDuedt,
            String vstCnfmdt,
            String vstCnfmHh,

            String vstFshDt,
            String vstFshHh,
            String cstUnuitmCn,
            String fstRgstDtm,
            String l1HgrOgCd,//new

            String l2HgrOgCd,//new
            String l3HgrOgCd,//new
            String l1HgrOgNm,//new
            String l2HgrOgNm,//new
            String l3HgrOgNm,//new

            String cnfmPsicDvCd,//new
            String cnfmPsicPrtnrOgTpCd,//new
            String cnfmPsicPrtnrNo,//new
            String prtnrKnm,
            String pstnDvCd,

            String pstnDvNm,//new
            String siteAwAtcCd,
            String siteAwAtcNm,
            String engPrtnrGdCd,//new
            String awAmt,

            String svProcsCn,
            byte[] cstSignCn,
            String bgColo,
            String cntrDtlStatCd,
            String cntrDtlStatNm,

            String cstSvAsnNo
        ) {
            this(
                chk,
                snRpblYn,
                cntrCstNo,//new
                cstGdCd,
                cstGdNm,

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
                sellTpDtlCd,
                sellTpDtlNm,

                mngerRglvlDvCd,
                mngerRglvlDvNm,
                newAdrZip,
                rndadr,
                rltdSvBizDclsfCd,//new

                clsfCdSrnPrntCn,//new
                bfVstDuedt,
                vstDuedt,
                vstCnfmdt,
                vstCnfmHh,

                vstFshDt,
                vstFshHh,
                cstUnuitmCn,
                fstRgstDtm,
                l1HgrOgCd,//new

                l2HgrOgCd,//new
                l3HgrOgCd,//new
                l1HgrOgNm,//new
                l2HgrOgNm,//new
                l3HgrOgNm,//new

                cnfmPsicDvCd,//new
                cnfmPsicPrtnrOgTpCd,//new
                cnfmPsicPrtnrNo,//new
                prtnrKnm,
                pstnDvCd,

                pstnDvNm,//new
                siteAwAtcCd,
                siteAwAtcNm,
                engPrtnrGdCd,//new
                awAmt,

                svProcsCn,
                cstSignCn != null ? Base64.getEncoder().encodeToString(cstSignCn) : "",
                bgColo,
                cntrDtlStatCd,
                cntrDtlStatNm,

                cstSvAsnNo
            );
        }
    }

    @ApiModel(value = "WsncRoutineBsPsicAssignStateDto-BulkExcelSearchRes")
    public record BulkExcelSearchRes(
        String snRpblYn,
        String cntrCstNo,//new
        String cstGdCd,
        String cstGdNm,

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
        String sellTpDtlCd,
        String sellTpDtlNm,

        String mngerRglvlDvCd,
        String mngerRglvlDvNm,
        String newAdrZip,
        String rndadr,
        String rltdSvBizDclsfCd,//new

        String clsfCdSrnPrntCn,//new
        String bfVstDuedt,
        String vstDuedt,
        String vstCnfmdt,
        String vstCnfmHh,

        String vstFshDt,
        String vstFshHh,
        String cstUnuitmCn,
        String fstRgstDtm,
        String l1HgrOgCd,//new

        String l2HgrOgCd,//new
        String l3HgrOgCd,//new
        String l1HgrOgNm,//new
        String l2HgrOgNm,//new
        String l3HgrOgNm,//new

        String cnfmPsicDvCd,//new
        String cnfmPsicPrtnrOgTpCd,//new
        String cnfmPsicPrtnrNo,//new
        String prtnrKnm,
        String pstnDvCd,

        String pstnDvNm,//new
        String siteAwAtcCd,
        String siteAwAtcNm,
        String engPrtnrGdCd,//new
        String awAmt,

        String svProcsCn,
        String bgColo,
        String cntrDtlStatCd,
        String cntrDtlStatNm,

        String cstSvAsnNo
    ) {}
}
