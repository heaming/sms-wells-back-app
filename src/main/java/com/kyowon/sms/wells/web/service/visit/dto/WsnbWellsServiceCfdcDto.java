package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WsnbWellsServiceCfdcDto {
    @ApiModel(value = "WsnbWellsServiceCfdcDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String searchType,
        String cntrNo,
        String cntrSn,
        String cstNo,
        String cstNm,
        String pno1,
        String pno2,
        String pno3,
        String bzrno,
        String sellPrtnrNo,
        String fromOgCd,
        String toOgCd,
        @NotBlank
        @ValidDate
        String fromDate,
        @NotBlank
        @ValidDate
        String toDate
    ) {
        public SearchReq {
            pno2 = DbEncUtil.enc(pno2);
        }
    }

    @ApiModel(value = "WsnbWellsServiceCfdcDto-SearchRes")
    public record SearchRes(
        String cstSvAsnNo,
        String cntrNo,
        String cntrSn,
        String sellPrtnrOgTpCd,
        String sellPrtnrNo,
        String sellPrtnrNm,
        String sellPrtnrOgCd,
        String cstNo,
        String cstNm,
        String cstMpno1,
        String cstMpno2,
        String cstMpno3,
        String cstPno1,
        String cstPno2,
        String cstPno3,
        String cstEmadr,
        String bzrno,
        String rcgvpNm,
        String adrId,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String wkExcnDt,
        String clsfCdSrnPrntCn,
        String psicPrtnrOgTpCd,
        String psicPrtnrNo,
        String psicPrtnrNm,
        String pdCd,
        String pdNm,
        String pdAbbrNm
    ) {
        public SearchRes {
            cstPno2 = DbEncUtil.dec(cstPno2);
            cstMpno2 = DbEncUtil.dec(cstMpno2);
        }
    }

    @ApiModel(value = "WsnbWellsServiceCfdcDto-ReportReq")
    public record ReportReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String prtnrKnm,
        String nm,
        String etcSelect,
        String publishDatetime
    ) {}

    @ApiModel(value = "WsnbWellsServiceCfdcDto-KakaoReq")
    public record KakaoReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String prtnrKnm,
        String nm,
        String etcSelect,
        String publishDatetime,
        String callingNumber,
        String receivingNumber,
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsnbWellsServiceCfdcDto-EmailReq")
    public record EmailReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String prtnrKnm,
        String nm,
        String etcSelect,
        String publishDatetime,
        String caller,
        String receiver,
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsnbWellsServiceCfdcDto-HistoryReq")
    public record HistoryReq(
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String prtnrKnm,
        String nm
    ) {}

    @ApiModel(value = "WsnbWellsServiceCfdcDto-HistoryRes")
    public record HistoryRes(
        String receiver,
        String sendDatetime,
        String sender,
        String cstSvAsnNo
    ) {}

    @ApiModel(value = "WsnbWellsServiceCfdcDto-FindOzReq")
    public record FindOzReq(
        String cstSvAsnNo,
        String rcgvpNm,
        String prtnrNm
    ) {}
}
