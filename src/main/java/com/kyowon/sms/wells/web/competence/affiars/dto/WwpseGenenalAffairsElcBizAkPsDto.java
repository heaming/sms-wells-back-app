package com.kyowon.sms.wells.web.competence.affiars.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.docs.dto.AttachFileDto;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import java.util.List;

public class WwpseGenenalAffairsElcBizAkPsDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 총무전자업무요청 현황 Search Request Dto
    @Builder
    @ApiModel("WwpseGenenalAffairsElcBizAkPsDvo-BaseSearchReq")
    public record BaseSearchReq(
        String rpotBizTpId,
        String rpotBizTpDvCd,
        String ogTpCd,
        String prtnrNo
    ) {
    }

    @ApiModel("WwpseGenenalAffairsElcBizAkPsDvo-SearchReq")
    public record SearchReq(
        String rpotBizTpId,
        String rpotBizTpDvCd,
        String ogTpCd,
        String rcpStartDtm,
        String rcpEndDtm,
        String rntAplcTpCd,
        String rpotBizProcsStatCd,
        String rpotBizAplcId,
        String procsSn

    ) {
    }

    @ApiModel("WwpsgBusinessManagerReportMgtDto-businessTypeRes")
    public record businessTypeRes(
        String rpotBizTpId,      /* 보고서업무유형ID */
        String rpotBizTpDvCd,    /* 보고서업무유형구분코드 */
        String rpotBizTpNm,      /* 보고서업무유형명 */
        String confArtcUseYn,    /* 확인사항사용여부 */
        String confArtcCn,       /* 확인사항내용 */
        String atentnCn,         /* 유의사항내용 */
        String useYn,            /* 사용여부 */
        Long sortOdr,            /* 정렬순서 */
        String dtaDlYn,          /* 데이터삭제여부 */
        String codeId,
        String codeName
    ) {
    }


    // *********************************************************
    // Result Dto
    // *********************************************************
    // Search Result Dto
    @ApiModel("WwpseGenenalAffairsElcBizAkPsDvo-BaseSearchRes")
    public record BaseSearchRes(
        String rpotBizAsnId,
        String rpotBizTpId,
        String dgYn,
        String ogTpCd,
        String prtnrNo,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String prtnrKnm

    ) {
    }

    @ApiModel("WwpseGenenalAffairsElcBizAkPsDvo-SearchRes")
    public record SearchRes(
        String rpotBizAplcId,    /* 보고서업무신청ID */
        String rpotBizTpId,      /* 보고서업무유형ID */
        String aplcnsPrtnrNo,    /* 신청자파트너번호 */
        String aplcDtm,          /* 신청일시 */
        String bizAkCn,          /* 업무요청내용 */
        String apnFileDocId,     /* 첨부파일문서ID */
        String bizAkBldCd,       /* 업무요청빌딩코드 */
        String bizAkBldNm,       /* 업무요청빌딩명 */
        String procsSn,                 /* 처리일련번호 */
        String rpotBizProcsStatCd,  /* 보고서업무처리상태코드 */
        String procsCn,               /* 처리내용 */
        String rntAplcTpCd,         /* 임차신청유형코드 */
        String rntAplcTpNm,
        String prtnrNo,
        String prtnrKnm,            /* 파트너 한글명 */
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcUsrNm,
        String rpotBizProcsStatNm,  /* 보고서업무처리상태코드 */
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String procsPrtnrKnm,
        String procsCralLocaraTno,
        String procsMexnoEncr,
        String procsCralIdvTno,
        String rcstPrtnrNo,
        String rcstPrtnrNm,
        String rcstCralLocaraTno,
        String rcstMexnoEncr,
        String rcstCralIdvTno
    ) {
    }

}
