package com.kyowon.sms.wells.web.closing.expense.dto;

import java.util.List;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdcdRequestCleaningSuppliesMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리 - 청소(용품)비 신청 / 빌딩 조회
    @Builder
    @ApiModel(value = "WdcdRequestCleaningSuppliesMgtDto-CodeReq")
    public record CodeReq(
        String ogTpCd,
        String prtnrNo
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리 - 청소(용품)비 신청 / 직책 조회
    @Builder
    @ApiModel(value = "WdcdRequestCleaningSuppliesMgtDto-SearchRsbDvCdRes")
    public record SearchRsbDvCdRes(
        String rsbDvCd,
        String pstnDvCd,
        String cdCntn
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 청소 용품비 관리 - 청소(용품)비 신청 / 빌딩 조회
    @Builder
    @ApiModel(value = "WdcdRequestCleaningSuppliesMgtDto-CodeRes")
    public record CodeRes(
        String bldCd,
        String bldNm
    ) {}

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
        String clingCostSignApnFileId,
        String locaraTno, // 지역번호
        String exnoEncr,
        String idvTno,
        String ogTpCd
    ) {}

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
        //@MaskRequired(type = MaskingType.NAME)
        String cardPsrNm,
        String aplcDt,
        //@MaskRequired(type = MaskingType.NAME)
        String claimNm,
        String bldCd,
        String bilAmt,
        String locaraTno,
        //@MaskRequired(type = MaskingType.ALL)
        String exnoEncr,
        String idvTno,
        String clingCostSrcpApnFileId,
        String ogTpCd
    ) {}
}
