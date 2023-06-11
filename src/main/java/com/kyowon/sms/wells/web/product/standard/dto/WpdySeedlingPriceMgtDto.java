package com.kyowon.sms.wells.web.product.standard.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

public class WpdySeedlingPriceMgtDto {

    @ApiModel(value = "WpdySeedlingPriceMgtDto-SearchReq")
    public record SearchReq(
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        String pdctPdNm, /* 상품명 */
        String pdctPdCd, /* 상품코드 */
        String rglrSppMchnTpCd, /* 정기배송기기유형코드 */
        String rglrSppPrcDvCd /* 정기배송가격구분코드 */
    ) {}

    @ApiModel(value = "WpdySeedlingPriceMgtDto-SearchRes")
    public record SearchRes(
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,
        String pdctPdNm, /* 제품상품코드 */
        String pdTpDtlCd, /* 상품유형 */
        String pdClsfNm, /* 상품분류 */

        String rglrSppSdingPrcId, /* 정기배송모종가격ID */
        String pdctPdCd, /* 제품상품코드 */
        String rglrSppMchnKndCd, /* 정기배송기기종류코드 */
        String rglrSppMchnTpCd, /* 정기배송기기유형코드 */
        String rglrSppPrcDvCd, /* 정기배송가격구분코드 */
        Long pdPrcTcnt, /* 상품가격차수 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        Long sdingQty, /* 모종수량 */
        Long sellAmt, /* 판매금액 */
        Long splAmt, /* 공급금액 */
        Long vat, /* 부가가치세 */
        Long asSellAmt, /* AS판매금액 */
        String useYn, /* 사용여부 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    @ApiModel(value = "WpdySeedlingPriceMgtDto-SaveReq")
    public record SaveReq(
        @NotEmpty
        List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> bases
    ) {}

    @ApiModel(value = "WpdySeedlingPriceMgtDto-SeedlingPriceBase")
    public record SeedlingPriceBase(
        String fnlMdfcDtm,
        String fnlMdfcUsrNm,

        String rglrSppSdingPrcId, /* 정기배송모종가격ID */
        String pdctPdCd, /* 제품상품코드 */
        String rglrSppMchnKndCd, /* 정기배송기기종류코드 */
        String rglrSppMchnTpCd, /* 정기배송기기유형코드 */
        String rglrSppPrcDvCd, /* 정기배송가격구분코드 */
        Long pdPrcTcnt, /* 상품가격차수 */
        String apyStrtdt, /* 적용시작일자 */
        String apyEnddt, /* 적용종료일자 */
        Long sdingQty, /* 모종수량 */
        Long sellAmt, /* 판매금액 */
        Long splAmt, /* 공급금액 */
        Long vat, /* 부가가치세 */
        Long asSellAmt, /* AS판매금액 */
        String useYn, /* 사용여부 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}
}
