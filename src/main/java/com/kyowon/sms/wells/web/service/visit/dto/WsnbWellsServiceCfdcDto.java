package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WsnbWellsServiceCfdcDto {
    @ApiModel(value = "WsnbWellsServiceCfdcDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String fromDate,
        @NotBlank
        @ValidDate
        String toDate,
        @NotBlank
        String searchType,
        String searchParam1,
        String searchParam2,
        String searchParam3,
        String searchParam4
    ) {
        public SearchReq {
            if (StringUtils.isNotEmpty(searchType) && searchType.equals("4"))
                searchParam3 = StringUtils.isNotEmpty(searchParam3) ? DbEncUtil.enc(searchParam3) : searchParam3;
        }
    }

    @ApiModel(value = "WsnbWellsServiceCfdcDto-SearchRes")
    public record SearchRes(
        String cntrCstNo,
        String asnOjYm,
        String cntrNo,
        Integer cntrSn,
        String cntr,
        String sellPrtnrNo,
        String ogCd,
        String svpdNmAbbr1,
        String cntrKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String bzrno,
        String installKnm,
        String adr,
        String wkExcnDt,
        String clsfCdSrnPrntCn,
        String prtnrKnm,
        String baseYm,
        String nm
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
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
        String receivingNumber
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
        String receiver
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
        String sender
    ) {}
}
