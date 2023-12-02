package com.kyowon.sms.wells.web.fee.simulation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * BS 예상실적 조회
 * </pre>
 *
 * @author seoin.jeon
 * @since 2023.09.25
 */
public class WfefBfsvcEtPerfDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // BS 예상 실적 Search Request Dto
    @ApiModel(value = "WfefBfsvcEtPerfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String strtPerfDt,
        @NotBlank
        String endPerfDt,
        String baseYm,
        String prtnrNo,
        @NotBlank
        String rsbDvCd,
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String ogLevlDvCd3
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfefBfsvcEtPerfDto-SearchRes")
    public record SearchRes(
        String ogCd, /*조직코드*/
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*성명*/
        String opngMm, /*개시월*/
        String educCpcAckmtYn, /*정착교육*/
        int bfsvcAckmtCnt, /*가전인정건수*/
        Double procsRt, /*처리율*/
        int w1ProcCnt, /*w1처리건수*/
        int w2ProcCnt, /*w2처리건수*/
        int wrfr1MgtCnt, /*정수기1 관리건수*/
        int wrfr2MgtCnt, /*정수기2 관리건수*/
        int wrfr3MgtCnt, /*정수기3 관리건수*/
        int wrfr4MgtCnt, /*정수기4 관리건수*/
        int unWrfrMgtCnt, /*비정수기 관리건수*/
        int puf1MgtCnt, /*청정기1 관리건수*/
        int puf2MgtCnt, /*청정기2 관리건수*/
        int otscMgtCnt, /*아웃소싱 관리건수*/
        int bdtMgtCnt, /*비데연수기 관리건수*/
        int etcMgtCnt, /*기타 관리건수*/
        int sumFxamMgtCnt, /*합계 정액관리건수*/
        int sumMgtCnt, /*합계 관리건수*/
        int wrfr1FhsCnt, /*정수기1 완료건수*/
        int wrfr2FhsCnt, /*정수기2 완료건수*/
        int wrfr3FhsCnt, /*정수기3 완료건수*/
        int wrfr4FhsCnt, /*정수기4 완료건수*/
        int unWrfrFhsCnt, /*비정수기 완료건수*/
        int puf1FhsCnt, /*청정기1 완료건수*/
        int puf2FhsCnt, /*청정기2 완료건수*/
        int otscFhsCnt, /*아웃소싱 완료건수*/
        int bdtFhsCnt, /*비데연수기 완료건수*/
        int etcFhsCnt, /*기타 완료건수*/
        int sumFxamFhsCnt, /*완료 정액건수*/
        int sumFhsCnt, /*합계 완료건수*/
        int bsFee /*BS관리수수료*/
    ) {}

}
