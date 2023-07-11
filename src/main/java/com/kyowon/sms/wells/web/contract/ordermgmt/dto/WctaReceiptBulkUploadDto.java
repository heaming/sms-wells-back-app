package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.kyowon.sflex.common.common.dto.SujiewonDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPdBasDvo;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WctaReceiptBulkUploadDto {
    @Builder
    @ApiModel(
        value="WctaReceiptBulkUploadDto-FormatReq",
        description="Format Req Dto"
    )
    public record ValidateReq(
        @NotBlank
		String adr1,
		String adr2,
        @NotBlank
		String basePdCd,
		String svPdCd
    ) {}

    @Builder
    @ApiModel(
        value="WctaReceiptBulkUploadDto-ValidateRes",
        description="Validate Res Dto"
    )
    public record ValidateRes(
        SujiewonDto.FormatRes adr,
        WctaPdBasDvo pdBas
    ) {}

    @Builder
    @ApiModel(
        value="WctaReceiptBulkUploadDto-CreateProspectCstReq",
        description="가망고객 생성 요청 DTO"
    )
    public record CreateProspectCstReq(
        @NotBlank
        String basePdCd,
        @NotBlank
		@ValidDate
        String pspcCstInflwDt,
        @NotBlank
        String pspcCstKnm,
        @NotBlank
        String copnDvCd,
        @NotBlank
        @ValidDate
        String bryyMmdd,
        String bzrno,
        String sexDvCd,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        @NotBlank
        String adrId,
        @NotBlank
        String alncmpDgPrtnrMapngCd,
        String alncmpCd,
        String alncmpDgPrtnrOgTpCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String cnslMoCn
    ) {}
}
