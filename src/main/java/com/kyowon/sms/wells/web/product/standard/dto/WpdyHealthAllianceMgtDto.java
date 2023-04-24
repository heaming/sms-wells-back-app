package com.kyowon.sms.wells.web.product.standard.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

public class WpdyHealthAllianceMgtDto {

    @ApiModel(value = "WpdyHealthAllianceMgtDto-SearchReq")
    public record SearchReq(
        String alncmpCd, /* 제휴코드 */
        String sellTpCd, /* 판매유형 */
        String pdNm, /* 상품명 */
        String pdCd, /* 상품코드 */
        String prdtCateHigh, /* 대분류 */
        String prdtCateMid, /* 중분류 */
        String svcStartDt, /* 적용 시작일 */
        String svcEndDt /* 적용 종료일 */
    ) {}

    @ApiModel(value = "WpdyHealthAllianceMgtDto-SearchRes")
    public record SearchRes(
        String pdNm, /* 상품명 */
        String svcDurtion, /* 약정기간 */
        String sellChnlCds, /* 판매채널 */

        String fstRgstUsrId,
        String fnlMdfcUsrId,
        String fstRgstDtm,
        String fstRgstUsrNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,

        String pdAlncmpBaseId, /* 상품제휴사기준ID */
        String alncmpCd, /* 제휴사코드 */
        String sellTpCd, /* 판매유형코드 */
        String pdCd, /* 상품코드 */
        String svPdCd, /* 서비스상품코드 */
        String stplPrdCd, /* 약정주기코드 */
        String rentalDscDvCd, /* 렌탈할인구분코드 */
        String rentalDscTpCd, /* 렌탈할인유형코드 */
        String rentalCrpDscrCd, /* 렌탈법인할인율코드 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        String ogTpCd, /* 조직유형코드 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    @ApiModel(value = "WpdyHealthAllianceMgtDto-SaveReq")
    public record SaveReq(
        @NotEmpty
        List<WpdyHealthAllianceMgtDto.AllianceBase> bases
    ) {}

    @ApiModel(value = "WpdyHealthAllianceMgtDto-AllianceBase")
    public record AllianceBase(
        String pdAlncmpBaseId, /* 상품제휴사기준ID */
        @NotBlank
        String alncmpCd, /* 제휴사코드 */
        @NotBlank
        String sellTpCd, /* 판매유형코드 */
        String pdCd, /* 상품코드 */
        String svPdCd, /* 서비스상품코드 */
        String stplPrdCd, /* 약정주기코드 */
        String rentalDscDvCd, /* 렌탈할인구분코드 */
        String rentalDscTpCd, /* 렌탈할인유형코드 */
        String rentalCrpDscrCd, /* 렌탈법인할인율코드 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        String ogTpCd, /* 조직유형코드 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}
}
