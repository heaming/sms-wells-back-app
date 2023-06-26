package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WsnbFinishLocationDto {
    @ApiModel(value = "WsnbFinishLocationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String asnOjYm,
        @NotBlank
        String mngtDvCd,
        @NotBlank
        String fshBaseDstn,
        String ogTpCd,
        String prtnrNo
    ) {}

    @ApiModel(value = "WsnbFinishLocationDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        Integer cntrSn,
        String asnOjYm,
        String cntr,
        String rcgvpKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String svpdSapCd,
        String pdctPdCd,
        String svpdNmAbbr1,
        String sellTpCd,
        String adr,
        String prtnrKnm,
        String vstFshDstnVal,
        String status
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
        }
    }
}
