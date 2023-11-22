package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcPartnerEngineerDto {

    @ApiModel(value = "WogcPartnerEngineerDto-SearchEngineerReq")
    @Builder
    public record SearchEngineerReq(
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        @NotBlank
        String baseYm,
        @NotBlank
        String baseDt,
        @NotBlank
        String ogTpCd
    ) {}

    @ApiModel(value = "WogcPartnerEngineerDto-SearchEngineerRes")
    public record SearchEngineerRes(
        String ogCd,
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String pstnDvNm,
        String rsbDvNm,
        String wkGrpNm,
        String bizAgntYn,
        String wrkDt,
        String dowDv,
        String egerWrkStatCd,
        String egerWrkStatNm,
        String rmkCn,
        String vcnStrtDt,
        String vcnEndDt,
        String bizAgntPrtnrNo,
        String bizAgntNm,
        String pcpPrtnrNo,
        String pcpPrtnrNm,
        String procsDtm

    ) {}

    @ApiModel(value = "WogcPartnerEngineerDto-SearchVacationRes")
    public record SearchVacationRes(
        String egerWrkStatCd,
        String egerWrkStatNm,
        String prtnrNo,
        String vcnStrtDt,
        String vcnEndDt,
        String rmkCn,
        String prtnrKnm,
        String bizAgntPrtnrNo

    ) {}

    @ApiModel(value = "WogcPartnerEngineerDto-SearchVacationReq")
    public record SearchVacationReq(
        String prtnrNo

    ) {}

    @ApiModel(value = "WogcPartnerEngineerDto-SearchVacationCntRes")
    public record SearchVacationCntRes(
        String cnt

    ) {}

    @ApiModel(value = "WogcPartnerEngineerDto-SaveReq")
    public record SaveReq(
        String egerWrkStatCd,
        String oriVcnStrtDt,
        String vcnStrtDt,
        String vcnEndDt,
        String rmkCn,
        String bizAgntPrtnrNo,
        String prtnrNo,
        String wrkDt,
        String rowState

    ) {}

    @ApiModel(value = "WogcPartnerEngineerDto-RemoveReq")
    public record RemoveReq(
        String prtnrNo,
        String oriVcnStrtDt,
        String vcnStrtDt,
        String rowState

    ) {}

    @ApiModel(value = "WogcPartnerEngineerDto-SaveEngineerAttendReq")
    public record SaveEngineerAttendReq(
        String ogCd,
        String ogNm,
        String prtnrNo,
        String rolDvNm,
        String prtnrKnm,
        String wkGrpNm,
        String bizAgntYn,
        String wrkDt,
        String wrkNm,
        String egerWrkStatCd,
        String rmkCn,
        String vcnStrtDt,
        String vcnEndDt,
        String bizAgntPrtnrNo,
        String agntPrtnrKnm,
        String pcpPrtnrNo,
        String pcpPrtnrKnm,
        String employeeIDNumber,
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String procsDtm

    ) {}

    /**
     * 서비스 조 관리 조회 Request dto
     * @param ogLevlDvCd1 1레벨조직코드
     * @param ogLevlDvCd2 2레벨조직코드
     * @param wkGrpCd 작업그룹코드
     * @param rsbDvCd 직책구분코드
     * @param prtnrNo 파트너번호
     * @param baseYm 기준년월
     * @param vlDt 적용일자
     */
    @ApiModel(value = "WogcPartnerEngineerDto-FindJoeManagementReq")
    @Builder
    public record FindJoeManagementReq(
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String wkGrpCd,
        String rsbDvCd,
        String prtnrNo,
        String baseYm,
        String vlDt
    ) {
        public FindJoeManagementReq {
            if (StringUtils.isNotEmpty(vlDt)) {
                baseYm = vlDt.substring(0, 6);
            }
        }
    }

    /**
     * 서비스 조 관리 조회 Response dto
     * @param ogTpCd 조직유형코드
     * @param dgr1LevlOgNm 1레벨조직명
     * @param dgr2LevlOgNm 2레벨조직명
     * @param dgr3LevlOgNm 3레벨조직명
     * @param dgr4LevlOgNm 4레벨조직명
     * @param dgr5LevlOgNm 5레벨조직명
     * @param apySeqn 순번
     * @param prtnrNo 파트너번호
     * @param prtnrKnm 파트너명
     * @param wkGrpCd 작업그룹코드
     * @param wkGrpCdNm 작업그룹코드명
     * @param egerRsbCd 등급코드
     * @param egerRsbCdNm 등급코드명
     * @param rsbDvCd 직책구분코드
     * @param rsbDvCdNm 직책구분코드명
     * @param pstnDvNm 직급구분코드명
     * @param wkcrCd 조코드
     * @param wkcrCdNm 조명
     * @param cntrDt 계약일자
     * @param vlStrtdt 적용시작일자
     * @param vlEnddt 적용종료일자
     * @param cralLocaraTno 업무용전화번호1
     * @param mexnoEncr 업무용전롸번호2
     * @param cralIdvTno 업무용전화번호3
     * @param dtaDlYn 데이터삭제여부
     * @param telNumber 전화번호
     * @param ogCd 조직코드
     * @param usrId 사용자ID
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
        String pstnDvNm,
        String wkcrCd,
        String wkcrCdNm,
        String cntrDt,
        String vlStrtdt,
        String vlEnddt,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String dtaDlYn,
        String telNumber,
        String ogCd,
        String usrId
    ) {}

    /**
     * 서비스 조 관리 저장 Request dto
     * @param ogTpCd 조직유형코드
     * @param dgr1LevlOgNm 1레벨조직명
     * @param dgr2LevlOgNm 2레벨조직명
     * @param dgr3LevlOgNm 3레벨조직명
     * @param dgr4LevlOgNm 4레벨조직명
     * @param dgr5LevlOgNm 5레벨조직명
     * @param apySeqn 순번
     * @param prtnrNo 파트너번호
     * @param prtnrKnm 파트너명
     * @param wkGrpCd 작업그룹코드
     * @param wkGrpCdNm 작업그룹코드명
     * @param egerRsbCd 등급코드
     * @param egerRsbCdNm 등급코드명
     * @param rsbDvCd 직책구분코드
     * @param rsbDvCdNm 직책구분코드명
     * @param wkcrCd 조코드
     * @param wkcrCdNm 조명
     * @param cntrDt 계약일자
     * @param vlStrtdt 적용시작일자
     * @param vlEnddt 적용종료일자
     * @param cralLocaraTno 업무용전화번호1
     * @param mexnoEncr 업무용전롸번호2
     * @param cralIdvTno 업무용전화번호3
     * @param dtaDlYn 데이터삭제여부
     * @param telNumber 전화번호
     * @param usrId 사용자ID
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
        String telNumber,
        String usrId
    ) {}

    /**
     * 엔지니어 등급 관리 조회 Request dto
     * @param ogLevlDvCd1 1레벨조직코드
     * @param ogLevlDvCd2 2레벨조직코드
     * @param rsbDvCd 직책
     * @param chk 미등록
     * @param baseYm 기준년월
     */
    @ApiModel(value = "WogcPartnerEngineerDto-FindEngineerGradeReq")
    @Builder
    public record FindEngineerGradeReq(
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String rsbDvCd,
        String chk,
        String baseYm
    ) {}

    /**
     * 엔지니어 등급 관리 조회 Response dto
     * @param ogTpCd 조직유형코드
     * @param dgr1LevlOgNm 1레벨조직명
     * @param dgr2LevlOgNm 2레벨조직명
     * @param dgr3LevlOgNm 3레벨조직명
     * @param dgr4LevlOgNm 4레벨조직명
     * @param dgr5LevlOgNm 5레벨조직명
     * @param prtnrNo 파트너번호
     * @param prtnrKnm 파트너명
     * @param rsbDvCd 직책구분코드
     * @param rsbDvCdNm 직책명
     * @param rolDvCd 직무구분코드
     * @param rolDvCdNm 직무구분코드명
     * @param pstnDvNm 직급구분코드명
     * @param prtnrGdCd 등급코드
     * @param prtnrGdCdNm 등급코드명
     * @param apyStrtDt 적용시작일자
     * @param apyEnddt 적용종료일자
     * @param rmkCn 비고내용
     * @param cntrDt 계약일자
     * @param cltnDt 해지일자
     * @param apySeqn 순번
     * @param dtaDlYn 데이터삭제여부
     * @param ogCd 조직코드
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
        String rsbDvCd,
        String rsbDvCdNm,
        String rolDvCd,
        String rolDvCdNm,
        String pstnDvNm,
        String prtnrGdCd,
        String prtnrGdCdNm,
        String apyStrtDt,
        String apyEnddt,
        String rmkCn,
        String cntrDt,
        String cltnDt,
        String apySeqn,
        String dtaDlYn,
        String ogCd
    ) {}

    /**
     * 엔지니어 등급 관리 저장 Request dto
     * @param ogTpCd 조직유형코드
     * @param dgr1LevlOgNm 1레벨조직명
     * @param dgr2LevlOgNm 2레벨조직명
     * @param dgr3LevlOgNm 3레벨조직명
     * @param dgr4LevlOgNm 4레벨조직명
     * @param dgr5LevlOgNm 5레벨조직명
     * @param prtnrNo 파트너번호
     * @param prtnrKnm 파트너명
     * @param rolDvCd 직무구분코드
     * @param rolDvCdNm 직무구분코드명
     * @param prtnrGdCd 등급
     * @param prtnrGdCdNm 등급명
     * @param apyStrtDt 적용시작일자
     * @param apyEnddt 적용종료일자
     * @param rmkCn 비고
     * @param cntrDt 계약일자
     * @param cltnDt 해지일자
     * @param apySeqn 순번
     * @param dtaDlYn 데이터삭제여부
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
    ) {}
}
