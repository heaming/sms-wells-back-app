package com.kyowon.sms.wells.web.competence.affiars.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwpseGenenalAffairsElcBizMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 총무전자업무관리 Search Request Dto
    @Builder
    @ApiModel("WwpseGenenalAffairsElcBizMgtDto-SearchReq")
    public record SearchReq(
        String rpotBizTpDvCd,
        String rpotBizTpId,
        String[] ogCds,
        String ogTpCd,
        String ogCd,
        String prtnrNo,
        String dgYn
    ) {
    }

    @ApiModel("WwpseGenenalAffairsElcBizMgtDto-CheckSearchReq")
    public record CheckSearchReq(
        String rpotBizTpId,
        String dgYn
    ) {
    }

    @ApiModel("WwpseGenenalAffairsElcBizMgtDto-SearchRes")
    public record SearchRes(
        String rpotBizAsnId,     /* 보고서업무배정ID */
        String rpotBizTpId,      /* 보고서업무유형ID */
        String dgYn,             /* 대표여부 */
        String ogTpCd,           /* 조직유형코드 */
        String prtnrNo,          /* 파트너번호 */
        String dtaDlYn,          /* 데이터삭제여부 */
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String prtnrKnm,
        String ogTpNm,
        String ogCd,
        String ogNm
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // BM보고서 관리 Search Result Dto
    @ApiModel("WwpseGenenalAffairsElcBizMgtDto-businessTypeRes")
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

    @ApiModel("WwpseGenenalAffairsElcBizMgtDto-OptionListRes")
    public record OptionListRes(
        String codeId,
        String codeName
    ) {
    }

    @ApiModel("WwpseGenenalAffairsElcBizMgtDto-PrtnrRes")
    public record PrtnrRes(
        String ogTpCd,
        String prtnrNo,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String ogCd,
        String ogNm
    ) {
    }

    @ApiModel("WwpseGenenalAffairsElcBizMgtDto-SaveReq")
    public record SaveReq(
        String rpotBizAsnId,
        String rpotBizTpId,
        String dgYn,
        String ogTpCd,
        String prtnrNo,
        String confArtcUseYn,
        String confArtcCn

    ) {
    }
}
