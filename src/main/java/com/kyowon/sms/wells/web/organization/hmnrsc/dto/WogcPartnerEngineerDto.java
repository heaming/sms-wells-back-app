package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import org.apache.commons.lang.StringUtils;

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

    /**
     * 서비스 조 관리 조회 Request dto
     */
    @ApiModel(value = "WogcPartnerEngineerDto-FindJoeManagementReq")
    @Builder
    public record FindJoeManagementReq(
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String wkGrpCd,
        String rsbDvCd,
        String prtnrNo,
        String vlDt
    ) {
    }

    /**
     * 서비스 조 관리 조회 Response dto
     */
    @ApiModel(value = "WogcPartnerEngineerDto-FindJoeManagementRes")
    @Builder
    public record FindJoeManagementRes(
        String ogTpCd,
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String dgr3LevlOgNm,
        String dgr4LevlOgNm,
        String dgr5LevlOgNm,
        String apySeqn,
        String prtnrNo,
        String prtnrKnm,
        String wkGrpCd,
        String wkGrpCdNm,
        String egerRsbCd,
        String egerRsbCdNm,
        String rsbDvCd,
        String rsbDvCdNm,
        String wkcrCd,
        String wkcrCdNm,
        String cntrDt,
        String vlStrtDt,
        String vlEnddt,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String dtaDlYn,
        String telNumber
    ) {
        public FindJoeManagementRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
        }
    }

    /**
     * 서비스 조 관리 저장 Request dto
     */
    @ApiModel(value = "WogcPartnerEngineerDto-SaveJoeManagementReq")
    @Builder
    public record SaveJoeManagementReq(
        String ogTpCd,
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String dgr3LevlOgNm,
        String dgr4LevlOgNm,
        String dgr5LevlOgNm,
        String apySeqn,
        String prtnrNo,
        String prtnrKnm,
        String wkGrpCd,
        String wkGrpCdNm,
        String egerRsbCd,
        String egerRsbCdNm,
        String rsbDvCd,
        String rsbDvCdNm,
        String wkcrCd,
        String wkcrCdNm,
        String cntrDt,
        String vlStrtdt,
        String vlEnddt,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String dtaDlYn,
        String telNumber
    ) {
    }

    /**
     * 엔지니어 등급 관리 조회 Request dto
     */
    @ApiModel(value = "WogcPartnerEngineerDto-FindEngineerGradeReq")
    @Builder
    public record FindEngineerGradeReq(
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String rolDvCd,
        String searchYm,
        String chk
    ) {
    }

    /**
     * 엔지니어 등급 관리 조회 Request dto
     */
    @ApiModel(value = "WogcPartnerEngineerDto-FindEngineerGradeRes")
    @Builder
    public record FindEngineerGradeRes(

        String ogTpCd,
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String dgr3LevlOgNm,
        String dgr4LevlOgNm,
        String dgr5LevlOgNm,

        String prtnrNo,
        String prtnrKnm,
        String rolDvCd,
        String rolDvCdNm,
        String prtnrGdCd,
        String prtnrGdCdNm,
        String apyStrtDt,
        String apyEnddt,
        String rmkCn,
        String cntrDt,
        String cltnDt,
        String apySeqn,
        String dtaDlYn
    ) {
    }

    /**
     * 엔지니어 등급 관리 저장 Request dto
     */
    @ApiModel(value = "WogcPartnerEngineerDto-SaveEngineerGradeReq")
    @Builder
    public record SaveEngineerGradeReq(
        String ogTpCd,
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String dgr3LevlOgNm,
        String dgr4LevlOgNm,
        String dgr5LevlOgNm,

        String prtnrNo,
        String prtnrKnm,
        String rolDvCd,
        String rolDvCdNm,
        String prtnrGdCd,
        String prtnrGdCdNm,
        String apyStrtDt,
        String apyEnddt,
        String rmkCn,
        String cntrDt,
        String cltnDt,
        String apySeqn,
        String dtaDlYn
    ) {
    }
}
