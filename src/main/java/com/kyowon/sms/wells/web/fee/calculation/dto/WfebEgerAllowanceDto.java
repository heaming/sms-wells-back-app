package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 엔지니어 수당 생성관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfebEgerAllowanceDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 엔지니어 수당 생성관리 Search Request Dto
    @ApiModel(value = "WfebEngineerAwCrtMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm,
        String feeSchdTpCd,
        String ogLevlDvCd1,
        String rsbDvCd,
        String prtnrNo
    ) {}

    @ApiModel(value = "WfebEngineerAwCrtMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String ogId,
        String ogCd,
        String rsbDvCd,
        @NotBlank
        String prtnrNo,
        String feeCd,
        int feeAmt
    ) {}

    @ApiModel(value = "WfebEngineerAwCrtMgtDto-ConfirmReq")
    public record ConfirmReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String ogCd,
        @NotBlank
        String prtnrNo,
        @NotBlank
        String type,
        @NotBlank
        String confirm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 엔지니어 수당 생성관리 Search Result Dto
    @ApiModel(value = "WfebEngineerAwCrtMgtDto-SearchEngineerRes")
    public record SearchEngineerRes(
        String baseYm, /*실적년월*/
        String ogId, /*조직ID*/
        String ogNm, /*조직명*/
        String ogCd, /*조직코드*/
        String prtnrKnm, /*성명*/
        String prtnrNo, /*파트너번호*/
        String pstnDvCd, /*직급*/
        String rsbDvCd, /*직책*/
        Integer perfW06p00001, /*설치작업 건수*/
        Integer perfW06p00004, /*bs작업 건수*/
        Integer perfW06p00005, /*as작업 건수*/
        Integer perfW06p00006, /*동행작업 건수*/
        Integer totPerfVisit, /*방문처리실적 합계*/
        Integer perfW06p00007, /*책임지역 건수*/
        Integer perfW06p00008, /*스케일링 건수*/
        Integer perfW06p00009, /*일반수리 건수*/
        Integer perfW06p00010, /*경수리 건수*/
        Integer perfW06p00025, /*중수리 건수*/
        Integer perfW06p00015, /*아웃소싱 건수*/
        Integer perfW06P00018, /*토요근무 건수*/
        Integer totPerfRpr, /*입고수리실적 합계*/
        Integer feeW060001, /*설지작업 실적수당*/
        Integer feeW060002, /*설치작업 급지수당*/
        Integer feeW060003, /*b/s작업 실적수당*/
        Integer feeW060004, /*b/s작업 급지수당*/
        Integer feeW060005, /*a/s 실적수당*/
        Integer feeW060006, /*a/s 급지수당*/
        Integer feeW060007, /*동행처리 실적수당*/
        Integer feeW060008, /*동행처리 급지수당*/
        Integer totFeeVisit, /*방문처리실적 수당 합계*/
        Integer totRglvlFeeVisit, /*방문처리실적 급지수당 합계*/
        Integer feeW060009, /*생산성인센티브*/
        Integer feeW060010, /*스케일링 수당*/
        Integer feeW060011, /*일반수리 수당*/
        Integer feeW060012, /*경수리 수당*/
        Integer feeW060017, /*중수리 수당*/
        Integer feeW060018, /*아웃소싱 수당*/
        Integer totFeeRpr, /*입고수리수당 합계*/
        Integer feeW060019, /*지원 수당*/
        Integer feeW060020, /*기타 수당*/
        Integer feeW060021, /*철거/철회 수당*/
        Integer feeW060022, /*판매권유 수당*/
        Integer feeW060023, /*토요근무 수당*/
        Integer feeW060024, /*휴무당직 수당*/
        Integer feeW060025, /*강의 수당*/
        Integer feeW060026, /*도서방문수당*/
        Integer feeW060027, /*기술숙련수당*/
        Integer feeW060028, /*조장 수당*/
        Integer totFee, /*조정전수당합계*/
        Integer totAfFee, /*조정후수당합계*/
        Integer totFeeEtc, /*부가수당합계*/
        Integer dsbOjAmt, /*지급대상금액*/
        Integer feeW060022Cnt, /*판매권유수당 건수*/
        Integer feeW060024Cnt, /*휴무당직수당 건수*/
        Integer feeW060025Cnt, /*강의수당 건수*/
        Integer feeW060026Cnt, /*도서방문수당 건수*/
        Integer feeW060023Cnt, /*토요근무수당 건수*/
        String editYn, /*수정가능여부*/
        String cnrAwCnfmDtm, /*확정일자*/
        String note /*비고*/
    ) {}

    @ApiModel(value = "WfebEngineerAwCrtMgtDto-SearchEngineerManagerRes")
    public record SearchEngineerManagerRes(
        String ogId, /*조직id*/
        String ogNm,
        String ogCd,
        String baseYm,
        String prtnrKnm,
        String prtnrNo,
        String pstnDvCd,
        String rsbDvCd,
        int feeW060031, /*업적수당*/
        int feeW060032, /*자격수당*/
        int totFee
    ) {}

    @ApiModel("WfebEngineerAwCrtMgtDto - SearchConfirmRes")
    public record SearchConfirmRes(
        String baseYm,
        String ogId,
        String ogNm,
        String ogCd,
        String cnrAwCnfmDtm, /*센터확정일시*/
        String cnfmBtnYn, /*확정취소 버튼 활성화 여부*/
        int totCnt, /*전체 개수*/
        int cnfmBtnCnt /*확정된 센터 개수*/
    ) {}

    @ApiModel("WfebEngineerAwCrtMgtDto - SearchSchdRes")
    public record SearchSchdRes(
        String feeSchdLvCd /*현재일정단계*/
    ) {}

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 엔지니어 수당 생성관리 Save Request Dto
    @ApiModel(value = "WfebEgerAllowanceDto-SaveReq")
    public record SaveReq(
        String param1, /* 실적년월 */
        String param2 /* 직책유형 */
    ) {}
}
