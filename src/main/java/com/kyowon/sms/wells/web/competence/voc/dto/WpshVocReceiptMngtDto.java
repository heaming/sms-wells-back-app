package com.kyowon.sms.wells.web.competence.voc.dto;

import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

import java.util.List;

public class WpshVocReceiptMngtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수 관리 Search Request Dto
    @Builder
    @ApiModel("WpshVocReceiptMngtDto-SearchReq")
    public record SearchReq(
        String startRcpDtm,
        String endRcpDtm,
        String vocBizTpCd,
        String vocRcpTitNm,
        String prtnrNo,
        String vocProcsStatCd
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // VOC 접수 관리 Search Result Dto
    @ApiModel("WpshVocReceiptMngtDto-SearchRes")
    public record SearchRes(
        String vocRcpId,
        String vocBizTpCd,
        String vocRcpCtgCd,
        String vocRcpTitNm,
        String pcpPrtnrNo,
        String procsDtm,
        String vocProcsStatCd,
        String cntrNo,
        String cntrSn,
        String cstNo,
        String fstRgstDtm,
        String rcpDtm,
        String rcpNm,
        String deptNm,
        String deptId,
        String rsbEnNm,
        String epno,
        String usrNm,
        String bizAkCn,
        String apnFileDocId,
        String attchDocIdNumberOfFiles
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수 관리 Save Request Dto
    @Builder
    @ApiModel("WpshVocReceiptMngtDto-SaveReq")
    public record SaveReq(
        String prtnrNo,
        String vocBizTpCd,
        String vocBizTpDtlCd,
        String bizAkCn,
        String vocRcpTitNm,
        String cntrNo,
        String cntrSn,
        String cntrCstNo,
        List<AttachFile> attachFiles
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수 관리 상세 Search Request Dto
    @Builder
    @ApiModel("WpshVocReceiptMngtDto-SearchDtlReq")
    public record SearchDtlReq(
        String vocRcpId
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // VOC 접수 관리 상세 Search Result Dto
    @ApiModel("WpshVocReceiptMngtDto-SearchDtlRes")
    public record SearchDtlRes(
        String vocRlyId,
        String vocRcpCtgCd,
        String vocRcpDvCd,
        String vocProcsStatCd,
        String rcpMoCn,
        String stfdEvlVal,
        String rlyCn,
        String rlyPrtnrNm,
        String fnlMdfcDtm,
        String attchDocIdNumberOfFiles
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수 관리 평가 Save Request Dto
    @Builder
    @ApiModel("WpshVocReceiptMngtDto-SaveEvlReq")
    public record SaveEvlReq(
        String vocRcpId,
        String stfdEvlVal
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수 관리 처리 Save Request Dto
    @Builder
    @ApiModel("WpshVocReceiptMngtDto-SaveProcsReq")
    public record SaveProcsReq(
        String vocRcpId,
        String vocProcsStatCd,
        String pcpPrtnrNo,
        String procsDtm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수 관리 접수 Save Request Dto
    @Builder
    @ApiModel("WpshVocReceiptMngtDto-SaveRcpReq")
    public record SaveRcpReq(
        String vocRcpId,
        String vocRcpCtgCd,
        String vocRcpDvCd,
        String rcpDtm,
        String rcpMoCn,
        String rcstPrtnrNo,
        String vocProcsStatCd
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // VOC 접수 관리 답변 Save Request Dto
    @Builder
    @ApiModel("WpshVocReceiptMngtDto-SaveRlyReq")
    public record SaveRlyReq(
        String vocRcpId,
        String vocRlyId,
        String rlyCn,
        List<AttachFile> attachFilesRly
    ) {
    }
}
