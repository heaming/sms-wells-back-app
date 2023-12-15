package com.kyowon.sms.wells.web.competence.report.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.docs.dto.AttachFileDto;
import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import java.util.List;

public class WwpsgBusinessCellPhoneChMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 업무폰변경 관리 Search Request Dto
    @Builder
    @ApiModel("WwpsgBusinessCellPhoneChMgtDto-BaseSearchReq")
    public record BaseSearchReq(
        String rpotBizTpId,
        String rpotBizTpDvCd,
        String ogTpCd,
        String prtnrNo
    ) {
    }

    @ApiModel("WwpsgBusinessCellPhoneChMgtDto-SearchReq")
    public record SearchReq(
        String rpotBizTpId,
        String rpotBizTpDvCd,
        String ogTpCd,
        String rcpStartDtm,
        String rcpEndDtm,
        String rntAplcTpCd,
        String rpotBizProcsStatCd,
        String rpotBizAplcId,
        String procsSn,
        String prtnrNo

    ) {
    }

    @ApiModel("WwpsgBusinessCellPhoneChMgtDto-PopupSearchRes")
    public record PopupSearchRes(
        String rpotBizAplcId,
        String procsSn,
        String rpotBizProcsStatCd,
        String procsCn,
        String fnlMdfcDtm,
        String procsPrtnrNo,
        String procsPrtnrNm,
        String procsCralLocaraTno,
        String procsMexnoGbencr,
        String procsCralIdvTno,
        String rpotBizProcsStatNm
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
    // 임차관리 Search Result Dto
    @ApiModel("WwpsgBusinessCellPhoneChMgtDto-BaseSearchRes")
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

    @ApiModel("WwpsgBusinessCellPhoneChMgtDto-SellPrtnrRes")
    public record SellPrtnrRes(
        String ogTpCd,
        String prtnrNo,
        String rsbDvNm,
        String sellCralLocaraTno,
        String sellMexnoEncr,
        String sellCralIdvTno,
        String bldNm

    ) {
    }

    @ApiModel("WwpsgBusinessCellPhoneChMgtDto-SearchRes")
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
        String rcstCralIdvTno,
        String bizCralTelChTpCd,
        String bizCralTelChTpNm,
        String chRqdt,
        String sellPrtnrNo,
        String sellPrtnrNm,
        String bfchCralLocaraTno,
        String bfchMexnoEncr,
        String bfchCralIdvTno,
        String afchCralLocaraTno,
        String afchMexnoEncr,
        String afchCralIdvTno,
        String rsbDvNm,
        String rsbDvCd,
        String ogId,
        String ogNm
    ) {
    }

    @ApiModel("WwpsgBusinessCellPhoneChMgtDto-SaveReq")
    public record SaveReq(
        String rpotBizAplcId,
        String rpotBizTpId,
        String prtnrNo,
        String bizAkCn,
        String procsSn,
        String rpotBizProcsStatCd,
        String procsCn,
        String bizCralTelChTpCd,     /* 업무휴대전화변경유형코드 */
        String chRqdt,               /* 변경요청일자 */
        String rcstPrtnrNo,
        String sellPrtnrNo,          /* 판매파트너번호 */
        String bfchCralLocaraTno,    /* 변경전휴대지역전화번호 */
        String bfchMexnoEncr,        /* 변경전휴대전화국번호암호화 */
        String bfchCralIdvTno,       /* 변경전휴대개별전화번호 */
        String afchCralLocaraTno,    /* 변경후휴대지역전화번호 */
        String afchMexnoEncr,        /* 변경후휴대전화국번호암호화 */
        String afchCralIdvTno       /* 변경후휴대개별전화번호 */

    ){}
}
