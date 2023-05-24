package com.kyowon.sms.wells.web.fee.aggregate.dto;

import io.swagger.annotations.ApiModel;

import lombok.Builder;

public class WfeaNetOrderDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Request Dto

    @Builder
    @ApiModel(value = "WfeaNetOrderDto-SearchReq")
    public record SearchReq(
        //구분
        String schDv,
        //제품유형
        String schPdctTp,
        //상품코드 시작
        String schPdCdStrt,
        //상품코드 종료
        String schPdCdEnd,
        //매출일자 시작
        String schSlDtStrt,
        //매출일자 종료
        String schSlDtEnd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Result Dto
    @ApiModel(value = "WfeaNetOrderDto-SearchRes")
    public record SearchRes(
        String og1Nm, /* 총괄단 */
        String og2Nm, /* 지역단 */
        String og3Nm, /* 지점 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 성명 */
        String gubn, /*판매유형*/
        String prdtType, /*제품유형*/
        String lcflg1, /*기변유형*/
        String cntrNo, /*계약상세번호*/
        String lccgub, /*고객구분*/
        String pdNm, /*상품명*/
        String pdCd, /*상품코드*/
        String useGubn, /*용도*/
        String lcetc3, /*할인구분*/
        String lcetc4, /*할인유형*/
        String lcst11, /*할인제도*/
        String lcst04, /*결합구분*/
        String lcmont, /*할부*/
        String lcbamt, /*기준가격*/
        String lctamt, /*홈케어*/
        String lcgub1, /*홈케어멤버십3년*/
        String lcst13, /*정액여부*/
        String leaseYn, /*금융리스*/
        String lcpcnt, /*인정건수*/
        String rcntYn, /*재약정여부*/
        String lccrtt, /*계약일자*/
        String lcslet, /*매출일자*/
        String lccant, /*취소일자*/
        String akdbon, /*지점장번호*/
        String akdbnm, /*지점장성명*/
        String lcamt1, /*렌탈료*/
        String lcgub3, /*약정기간*/
        String lcvmon, /*관리주기*/
        String lcpamt, /*인정실적금액*/
        String lcck02, /*프로모션번호*/
        String lcgseq, /*패키지일련번호*/
        String lcpaym /*순주문월*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 월 순주문 집계 Search Result Dto
    @ApiModel(value = "WfeaNetOrderDto-SearchFeeRes")
    public record SearchFeeRes(
        String og1Nm, /* 총괄단 */
        String og2Nm, /* 지역단 */
        String og3Nm, /* 지점 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 성명 */
        String gubn, /*판매유형*/
        String prdtType, /*제품유형*/
        String lcflg1, /*기변유형*/
        String cntrNo, /*계약상세번호*/
        String lccgub, /*고객구분*/
        String pdNm, /*상품명*/
        String pdCd, /*상품코드*/
        String useGubn, /*용도*/
        String lcetc3, /*할인구분*/
        String lcetc4, /*할인유형*/
        String lcst11, /*할인제도*/
        String lcst04, /*결합구분*/
        String lcmont, /*할부*/
        String lcbamt, /*기준가격*/
        String lctamt, /*홈케어*/
        String lcgub1, /*홈케어멤버십3년*/
        String lcst13, /*정액여부*/
        String leaseYn, /*금융리스*/
        String lcpcnt, /*인정건수*/
        String rcntYn, /*재약정여부*/
        String lccrtt, /*계약일자*/
        String lcslet, /*매출일자*/
        String lccant, /*취소일자*/
        String akdbon, /*지점장번호*/
        String akdbnm, /*지점장성명*/
        String lcamt1, /*렌탈료*/
        String lcgub3, /*약정기간*/
        String lcvmon, /*관리주기*/
        String lcpamt, /*인정실적금액*/
        String lcck02, /*프로모션번호*/
        String lcgseq, /*패키지일련번호*/
        String lcpaym /*순주문월*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfeaNetOrderDto-SaveReq")
    public record SaveReq(
        String col1,
        String col2,
        String col3,
        String col4,
        String col5,
        String col6
    ) {}
}
