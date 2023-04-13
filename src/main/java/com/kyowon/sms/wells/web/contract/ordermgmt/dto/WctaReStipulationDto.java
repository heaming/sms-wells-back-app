package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class WctaReStipulationDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 재약정 관리 Search Request Dto
    @Builder
    @ApiModel("WctaReStipulationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @Pattern(regexp = "[12]")
        String copnDvCd,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno
    ) {
        public SearchReq {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.enc(mexnoEncr) : mexnoEncr;
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 재약정 관리 Search Result Dto
    @ApiModel("WctaReStipulationDto-SearchRes")
    public record SearchRes(
        String sellTpCd,
        String cntrDtlStatCd,
        String copnDvCd,
        String cntrCstNo,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String sexDvCd,
        String bryyMmdd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String pdNm,
        String stplPtrm,
        String istTn,
        String dlqYn,
        String reqdDt,
        String cntrPdEnddt,
        String cpsDt
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
        }
    }
}
