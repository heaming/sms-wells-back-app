package com.kyowon.sms.wells.web.closing.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.*;

@Mapper
public interface WdcaCancellationFeeComputationMapper {
    /* 위약금액 산출 대상의 판매유형을 확인 */
    WdcaSellTypeDvo selectSellType(WdcaCancellationFeeComputationDvo dvo);

    /* 위약금 산출 대상 계약정보 조회 */
    WdcaComputationObjectContractDvo selectComputationObjectContract(WdcaCancellationFeeComputationDvo dvo);

    /* 위약금 산출 대상 매출정보 조회 */
    WdcaComputationObjectSalesDvo selectComputationObjectSales(WdcaCancellationFeeComputationDvo dvo);

    /* 웰스서비스실적내역 조회 */
    int selectWellsServicePerformanceCount(String baseYm, WdcaCancellationFeeComputationDvo dvo);

    /* 위약금액기본 저장 */
    int insertWellsBorAmtBas(WdcaCancellationFeeComputationResultDvo resultDvo);

    /* 위약금액기본이력 저장 */
    int insertWellsBorAmtBasHist(WdcaCancellationFeeComputationResultDvo resultDvo);

    /* 설치월 렌탈일수 조회 */
    Integer selectRentalDc(WdcaCancellationFeeComputationDvo dvo);

    /* 렌탈료할인_재약정위약금 조회 */
    WdcaRentalFeeDiscountRstlCcamDvo selectRentalFeeDiscountRstlCcam(WdcaCancellationFeeComputationDvo dvo);

    /* 사은품접수내역 테이블 조회 */
    int selectFreeGiftReceipt(WdcaCancellationFeeComputationDvo dvo);

    /* 위약금 산출 대상 계약정보 조회 */
    WdcaRegularShippingDvo selectRegularShippingBreachOfPromiseAmount(WdcaCancellationFeeComputationDvo dvo);
}
