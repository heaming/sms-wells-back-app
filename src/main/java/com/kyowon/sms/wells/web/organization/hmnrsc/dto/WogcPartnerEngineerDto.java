package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WogcPartnerEngineerDto {

    @ApiModel(value = "WogcPartnerEngineerDto-SearchEngineerReq")
    @Builder
    public record SearchEngineerReq(
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        @NotBlank
        String baseYm,
        @NotBlank
        String baseDt
    ) {
    }

    /**
     * @param ogCd            조직코드
     * @param ogNm            조직명
     * @param prtnrNo         파트너번호
     * @param rolDvNm         직무명
     * @param prtnrKnm        파트너성명
     * @param wkGrpNm         작업그룹
     * @param bizAgntYn       업무대행여부
     * @param wrkDt           근무일자
     * @param wrkDy           근무요일
     * @param dnlRsonNm       근무상태
     * @param rmkCn           비고사항
     * @param vcnStrtDt       휴가시작일자
     * @param vcnEndDt        휴가종료일자
     * @param bizAgntPrtnrNo  업무대행자 파트너번호
     * @param bizAgntPrtnrKnm 업무대행자 성명
     */
    @ApiModel(value = "WogcPartnerEngineerDto-SearchEngineerRes")
    public record SearchEngineerRes(
        String ogCd,
        String ogNm,
        String prtnrNo,
        String rolDvNm,
        String prtnrKnm,
        String wkGrpNm,
        String bizAgntYn,
        String wrkDt,
        String wrkDy,
        String dnlRsonNm,
        String rmkCn,
        String vcnStrtDt,
        String vcnEndDt,
        String bizAgntPrtnrNo,
        String bizAgntPrtnrKnm
    ) {
    }

}
