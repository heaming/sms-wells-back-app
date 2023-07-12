package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import com.kyowon.sflex.common.common.dto.SujiewonDto;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzPdBasDvo;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WctaReceiptBulkUploadDto {
    @Builder
    @ApiModel(
        value="WctaReceiptBulkUploadDto-FormatReq",
        description="Format Req Dto"
    )
    public record ValidateProspectReq(
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
    public record ValidateProspectRes(
        SujiewonDto.FormatRes adr,
        WctzPdBasDvo pdBas
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

    @Builder
    @ApiModel(
        value="WctaReceiptBulkUploadDto-FormatReq",
        description="Format Req Dto"
    )
    public record ValidateBulkRentalReq(
        @NotBlank
        String adr1,
        String adr2,
        @NotBlank
        String basePdCd,
        @NotBlank
        String svPdCd,
        @NotBlank
        String cstNo,
        @NotBlank
        String cstKnm,
        @NotBlank
        String copnDvCd,
        @ValidDate
        String bryyMmdd,
        String bzrno,
        @NotBlank
        String sexDvCd,
        @NotBlank
        String cralLocaraTno,
        @NotBlank
        String mexnoEncr,
        @NotBlank
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        @NotNull
        Integer cntrPtrm,
        @NotNull
        Long cntrAmt,
        @NotNull
        Integer stplPtrm,
        @NotBlank
        String alncmpDgPrtnrMapngCd,
        @NotBlank
        String alncmpDgPrtnrOgTpCd,
        @NotBlank
        String rentalDscDvCd,
        @NotBlank
        String rentalCrpDscrCd,
        @NotBlank
        String rentalDscTpCd,
        Long sellDscCtrAmt
    ) {}

    @Builder
    @ApiModel(
        value="WctaReceiptBulkUploadDto-ValidateRes",
        description="Validate Res Dto"
    )
    public record ValidateBulkRentalRes(
        String adrId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        String sellTpCd,
        String sellTpDtlCd,
        Integer svPrd,
        Double pdBaseAmt,
        Double sellAmt,
        Double dscAmt,
        Long cntramDscAmt,
        Double fnlAmt,
        Long vat,
        Double cntrTam,
        Float ackmtPerfRt,
        Long ackmtPerfAmt,
        Integer feeAckmtCt,
        Long feeAckmtBaseAmt,
        String sellInflwChnlDtlCd,
        String pdctPdRelId,
        String pdctPdCd,
        String pdctVlStrtDtm,
        String pdctVlEndDtm,
        Integer pdctPdQty,
        String svPdRelId,
        String svVlStrtDtm,
        String svVlEndDtm,
        Integer svPdQty
    ) {}

    @Builder
    @ApiModel(
        value="WctaReceiptBulkUploadDto-CreateProspectCstReq",
        description="대량 렌탈 계약 생성 요청 DTO"
    )
    public record CreateBulkRentalReq(
        @NotBlank
        String basePdCd,
        @NotBlank
        String copnDvCd,
        @NotBlank
        String cstNo,
        @NotBlank
        String alncmpDgPrtnrMapngCd,
        String alncmpCd,
        String alncmpDgPrtnrOgTpCd,
        Long cntrAmt,
        Integer cntrPtrm,
        @NotBlank
        String svPdCd,
        Integer stplPtrm,
        @NotBlank
        @ValidDate
        String pspcCstInflwDt,
        String rentalDscTpCd,
        String rentalDscDvCd,
        String rentalCrpDscrCd,
        Long sellDscCtrAmt,
        String alncmpSuscOrdNo,
        @NotBlank
        String adrId,
        String pdHclsfId,
        String pdMclsfId,
        String pdLclsfId,
        String pdDclsfId,
        @NotBlank
        String sellTpCd,
        @NotBlank
        String sellTpDtlCd, /*판매유형상세코드*/
        Integer svPrd,
        Double pdBaseAmt,
        Double sellAmt,
        Double dscAmt,
        Double fnlAmt,
        Long cntramDscAmt,
        Long vat,
        Double cntrTam,
        Float ackmtPerfRt,
        Long ackmtPerfAmt,
        Integer feeAckmtCt,
        Long feeAckmtBaseAmt,
        @NotBlank
        String sellInflwChnlDtlCd,
        @NotBlank
        String pdctPdRelId,
        @NotBlank
        String pdctPdCd,
        @NotBlank
        String pdctVlStrtDtm,
        @NotBlank
        String pdctVlEndDtm,
        Integer pdctPdQty,
        @NotBlank
        String svPdRelId,
        @NotBlank
        String svVlStrtDtm,
        @NotBlank
        String svVlEndDtm,
        Integer svPdQty
    ) {}
}
