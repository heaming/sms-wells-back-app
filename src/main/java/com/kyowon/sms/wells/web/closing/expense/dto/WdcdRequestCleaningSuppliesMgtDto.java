package com.kyowon.sms.wells.web.closing.expense.dto;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import java.util.List;

public class WdcdRequestCleaningSuppliesMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리 - 청소(용품)비 신청
    @Builder
    @ApiModel(value = "WdcdRequestCleaningSuppliesMgtDto-CodeRes")
    public record CodeRes(
        String bldCd,
        String bldNm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리 - 청소(용품)비 신청
    @Builder
    @ApiModel(value = "WdcdRequestCleaningSuppliesMgtDto-SaveReq")
    public record SaveReq(
        String clingCostAdjRcpNo,
        String aplcDt,
        String bldCd,
        String contact,
        String bilAmt,
        String cardPsrNm,
        String claimNm,
        List<AttachFile> attachFiles,
        String clingCostSignApnFileId
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리 - 청소(용품)비 신청
    @Builder
    @ApiModel(value = "WdcdRequestCleaningSuppliesMgtDto-FindReq")
    public record FindReq(
        String clingCostAdjRcpNo
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리 - 청소(용품)비 신청
    @Builder
    @ApiModel(value = "WdcdRequestCleaningSuppliesMgtDto-FindRes")
    public record FindRes(
        String clingCostAdjRcpNo,
        String cardPsrNm,
        String aplcDt,
        String claimNm,
        String bldCd,
        String bilAmt,
        String contact,
        String clingCostSignApnFileId
    ) {

    }
}
