package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 개인별 수수료 관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.03
 */
public class WfedIndividualFeeMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerReq")
    public record SearchMngerReq(
        @NotBlank
        String perfYm, /* 실적년월 */
        @NotBlank
        String prtnrNo /* 파트너번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 개인별 수수료 관리 Search Result Dto

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstEntrpRes")
    public record FindHmstEntrpRes(
        String emplNm, /* 성명 */
        String blg, /* 소속 */
        String rsb, /* 직책 */
        String akdcha, /* 차월 */
        String prtnrGdCd, /* 급수 */
        String atcnt1, /* 집합교육참여일수 */
        String atcnt2, /* 동행교육참여일수 */
        String akdsym, /* 업무등록월 */
        String akdeq0, /* 총판매건수 */
        String sercnt, /* 서비스건수 */
        String serryl, /* 서비스처리율 */
        String akcda19, /* 홈케어멤버십 */
        String akcda10, /* 매트리스 렌탈료 */
        String akcda12, /* 매트리스 외 렌탈료 */
        String akcda13, /* 환경가전 일시불 */
        String akcda15, /* 환경가전 외 일시불 */
        String intbsSum, /* 과표계 */
        String ddtnSum, /* 공제계 */
        String aclDsbAmt /* 실지급액 */
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchHmstFeeRes")
    public record SearchHmstFeeRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3,
        String item4,
        String fval4
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstDeductionRes")
    public record FindHmstDeductionRes(
        String erntx,
        String rsdntx,
        String rds,
        String hirInsr,
        String inddInsr,
        String buDdtn,
        String pnpyam,
        String ddtnSum

    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchHmstControlRes")
    public record SearchHmstControlRes(
        String div,
        String item,
        String ctrBf,
        String ctrAf,
        String rsn,
        String ctrDtm,
        String ctrr
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerBasicRes")
    public record FindMngerBasicRes(
        String perfYm, /* 실적년월 */
        String ogCd, /* 조직코드 */
        String dgr1LevlOgNm, /* 1차레벨조직명 */
        String dgr2LevlOgNm, /* 2차레벨조직명 */
        String dgr3LevlOgNm, /* 3차레벨조직명 */
        String dgr4LevlOgNm, /* 4차레벨조직명 */
        String dgr5LevlOgNm, /* 5차레벨조직명 */
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String rsbDvCd, /* 직책구분코드 */
        String qlfDvCd, /* 자격구분코드 */
        String cntrDt, /* 계약일자 */
        String prfmtDt, /* 승진일자 */
        String cltnDt, /* 해약일자 */
        String intbsAmt, /* 소득과표금액 */
        String ddctam, /* 공제금액 */
        String dsbOjAmt, /* 지급대상금액 */
        String mngtCt, /* 관리건수 */
        String vstCt, /* 방문건수 */
        String procsRt, /* 처리율 */
        String mngerOpngMm, /* 매니저개시-개시월 */
        String mngerBizCltnMm, /* 매니저개시-업무해약월 */
        String mngerBltnNmn /* 매니저 게시차월 */
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerSellEtcsRes")
    public record SearchMngerSellEtcsRes(
        String perfItem1, /* 실적항목1 */
        String perfItem2, /* 실적항목2 */
        String perfItem3, /* 실적항목3 */
        String perfItem4, /* 실적항목4 */
        String perfVal1, /* 개인실적 */
        String perfVal2, /* 조직실적 */
        String perfVal3, /* BS실적 */
        String perfVal4 /* 기타 */
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerBeforeServiceRes")
    public record SearchMngerBeforeServiceRes(
        String cdNm, /* 상품명 */
        String geMngtCt, /* 일반-관리건수 */
        String geVstCt, /* 일반-방문건수 */
        String geAmt, /* 일반-금액 */
        String fxamMngtCt, /* 정액-관리건수 */
        String fxamVstCt, /* 정액-방문건수 */
        String fxamAmt, /* 정액-금액 */
        String sumAmt /* 합계 */
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerFeeRes")
    public record SearchMngerFeeRes(
        String srnMarkFeeNm, /* 화면표시수수료명 */
        String feeAtcVal /* 수수료항목값 */
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerDeductionRes")
    public record SearchMngerDeductionRes(
        String feeAtcItem, /* 공제코드명 */
        String feeAtcVal /* 수수료항목값 */
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerControlRes")
    public record SearchMngerControlRes(
        String div, /* 구분 */
        String item, /* 항목명 */
        String ctrBf, /* 조정 전 */
        String ctrAf, /* 조정 후 */
        String rsn, /* 사유 */
        String ctrDtm, /* 조정일시 */
        String ctrr /* 조정자 */
    ) {}
}
