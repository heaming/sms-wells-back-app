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
    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
    ) {}
    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerReq")
    public record SearchMngerReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String no
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

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindHmstFeeRes")
    public record FindHmstFeeRes(
        String aksd01,
        String aksd02,
        String aksd03,
        String aksd04,
        String aksd05,
        String aksd06,
        String aksd07,
        String aksd08,
        String aksd09,
        String aksd10,
        String aksd13,
        String aksd14,
        String aksd11,
        String aksd15,
        String aksd51,
        String aksd52,
        String aksd53,
        String aksd54,
        String aksd55,
        String aksd56
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

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarEntrpRes")
    public record FindPlarEntrpRes(
        String emplNm,
        String blg,
        String rsb,
        String metg,
        String qlf,
        String indvElhm,
        String indvElhmExcp,
        String indvAdp,
        String ogElhm,
        String ogElhmExcp,
        String ogAdp,
        String indvChng,
        String indvMutu,
        String ogMutu,
        String intbsSum,
        String ddtnSum,
        String aclDsbAmt
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarFeeRes")
    public record FindPlarFeeRes(
        String w01,
        String w02,
        String w03,
        String w04,
        String w05,
        String w21,
        String w23,
        String w24,
        String w25,
        String w11,
        String w12,
        String w13,
        String w15,
        String w16,
        String w17,
        String w22,
        String w20,
        String w30
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindPlarDeductionRes")
    public record FindPlarDeductionRes(
        String rds,
        String erntx,
        String rsdntx,
        String hirInsr,
        String buDdtn,
        String pnpyam
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchPlarControlRes")
    public record SearchPlarControlRes(
        String div,
        String item,
        String ctrBf,
        String ctrAf,
        String rsn,
        String ctrDtm,
        String ctrr
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerEntrpRes")
    public record FindMngerEntrpRes(
        String emplNm,
        String mngDpt,
        String rgnlGrp,
        String branch,
        String rsb,
        String rgsBaseMm,
        String prfmtMon,
        String bizCltnMon,
        String qlf,
        String opngMm,
        String opngNmm,
        String mngCltnMm,
        String mgtCnt,
        String vstCnt,
        String procsRt,
        String intbsSum,
        String ddtnSum,
        String aclDsbAmt

    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerBaseInfoRes")
    public record SearchMngerBaseInfoRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3,
        String item4,
        String fval4
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerBeforeServiceRes")
    public record SearchMngerBeforeServiceRes(
        String cdNm,
        String cnt1,
        String cnt2,
        String amt1,
        String cnt3,
        String cnt4,
        String amt2,
        String sumAmt
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerFeeRes")
    public record SearchMngerFeeRes(
        String item1,
        String fval1,
        String item2,
        String fval2,
        String item3,
        String fval3,
        String item4,
        String fval4
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-FindMngerDeductionRes")
    public record FindMngerDeductionRes(
        String rds,
        String erntx,
        String rsdntx,
        String hirInsr,
        String buDdtn,
        String pnpyam
    ) {}

    @ApiModel(value = "WfedIndividualFeeMgtDto-SearchMngerControlRes")
    public record SearchMngerControlRes(
        String div,
        String item,
        String ctrBf,
        String ctrAf,
        String rsn,
        String ctrDtm,
        String ctrr
    ) {}
}
