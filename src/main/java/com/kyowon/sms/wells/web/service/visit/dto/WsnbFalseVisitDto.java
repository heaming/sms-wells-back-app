package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WsnbFalseVisitDto {
    @ApiModel(value = "WsnbFalseVisitDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @ValidDate
        String fromDate,
        @NotBlank
        @ValidDate
        String toDate
    ) {}

    @ApiModel(value = "WsnbFalseVisitDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgCd,
        String dgr2LevlOgCd,
        String dgr3LevlOgCd,
        String prtnrNo,
        String prtnrKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String bldNm,
        String cntr,
        String rcgvpKnm,
        String pdNm,
        String newAdrZip,
        String adr,
        String vstFshDstnVal,
        String vstFshDtHh,
        String rcpdt,
        String usrNm
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
        }
    }
}
