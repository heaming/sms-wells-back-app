package com.kyowon.sms.wells.web.product.standard.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WpdyRestipulationMgtDto {

    @ApiModel("WpdyRestipulationMgtDto-SearchReq")
    public record SearchReq(
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String startDate, /* 적용기간 시작일 */
        String endDate /* 적용기간 종료일 */
    ) {
        public SearchReq {
            if (StringUtil.isNotEmpty(startDate))
                startDate = startDate.replaceAll("-", "");
            if (StringUtil.isNotEmpty(endDate))
                endDate = endDate.replaceAll("-", "");
        }
    }

    @ApiModel("WpdyRestipulationMgtDto-SearchRes")
    public record SearchRes(
        String pdCd, /* 상품코드 */
        String pdNm, /* 상품코드 */
        String rstlBaseTpCd, /* 재약정기준유형코드 */
        Integer stplTn, /* 약정회차 */
        String rstlSellChnlDvCd, /* 재약정판매채널구분코드 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        Integer rstlMcn, /* 재약정개월수 */
        Long minRentalAmt, /* 최소렌탈금액 */
        Long stplDscAmt, /* 약정할인금액 */
        Integer rcpStrtMcn, /* 접수시작개월수 */
        Integer rcpEndMcn, /* 접수종료개월수 */
        Integer rstlDutyMcn, /* 재약정의무개월수 */

        String ackmtAmt, /* 인정금액 */
        Integer ackmtCt, /* 인정건수 */
        String feeAckmtBaseAmt, /* 수수료인정기준금액 */
        String feeFxamYn, /* 수수료정액여부 */
        String rid,

        // Default UserInfo Suit
        String fstRgstDtm, /* 최초등록일시 */
        String fstRgstUsrId, /* 최초등록사용자id */
        String fstRgstUsrNm, /* 최초등록사용자명 */
        String fnlMdfcDtm, /* 최종수정일시 */
        String fnlMdfcUsrId, /* 최종수정사용자id  */
        String fnlMdfcUsrNm /* 최종수정사용자명  */
    ) {}

    @ApiModel("WpdyRestipulationMgtDto-SaveReq")
    public record SaveReq(
        String rowState, /* 그리드 Row 상태 */
        @NotBlank
        String pdCd, /* 상품코드 */
        @NotBlank
        String rstlBaseTpCd, /* 재약정기준유형코드 */
        @Positive
        Integer stplTn, /* 약정회차 */
        @NotBlank
        String rstlSellChnlDvCd, /* 재약정판매채널구분코드 */
        @NotBlank
        String apyStrtdt, /* 적용시작일자 */
        @NotBlank
        String apyEnddt, /* 적용종료일자 */
        Integer rstlMcn, /* 재약정개월수 */
        Long minRentalAmt, /* 최소렌탈금액 */
        Long stplDscAmt, /* 약정할인금액 */
        Integer rcpStrtMcn, /* 접수시작개월수 */
        Integer rcpEndMcn, /* 접수종료개월수 */
        Integer rstlDutyMcn, /* 재약정의무개월수 - 의무기간 */

        String ackmtAmt, /* 인정금액 */
        Integer ackmtCt, /* 인정건수 */
        String feeAckmtBaseAmt, /* 수수료인정기준금액 */
        String feeFxamYn, /* 수수료정액여부 */
        String rid,

        String pdNm, /* 상품코드 - 중복데이터가 있을시 기준 출력값 */
        String fnlMdfcDtm
    ) {
        public SaveReq {
            if (StringUtil.isNotEmpty(apyStrtdt))
                apyStrtdt = apyStrtdt.replaceAll("-", "");
            if (StringUtil.isNotEmpty(apyEnddt))
                apyEnddt = apyEnddt.replaceAll("-", "");
        }
    }

    @ApiModel("WpdyRestipulationMgtDto-DuplicationRes")
    public record DuplicationRes(
        String pdNm, /* 상품명 */
        String dupliYn /* 중복여부 */
    ) {}

    @ApiModel("WpdyRestipulationMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String pdCd, /* 상품코드 */
        @NotBlank
        String rstlBaseTpCd, /* 재약정기준유형코드 */
        @NotBlank
        String rstlSellChnlDvCd, /* 재약정판매채널구분코드 */
        @NotBlank
        String apyStrtdt, /* 적용시작일자 */
        @Positive
        Integer stplTn, /* 약정회차 */
        String fnlMdfcDtm
    ) {
        public RemoveReq {
            apyStrtdt = apyStrtdt.replaceAll("-", "");
        }
    }

}
