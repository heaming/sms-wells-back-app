package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 입금집계현황-서비스센터 목록 DTO
 * </pre>
 *
 * @author eunsun.ha
 * @since 2023-10-31
 */
public class WwdbAggregateServiceCenterDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 입금집계현황-서비스센터 목록 조회 Search Request Dto
    @ApiModel(value = "WwdbAggregateServiceCenterDto-SearchAggregateServiceCenterReq")
    public record SearchAggregateServiceCenterReq(
        String strtSvDt, //서비스일자시작
        String endSvDt, //서비스일자종료
        String svCnr, //서비스 센터 아이디
        String prtnrKnm //엔지니어 이름
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금집계현황-서비스센터 목록 조회 Search Result Dto
    @ApiModel(value = "WwdbAggregateServiceCenterDto-SearchAggregateServiceCenterRes")
    public record SearchAggregateServiceCenterRes(
        String ogNm, //센터명
        String prtnrNo, //번호
        String prtnrKnm, //엔지니어
        String recapAmt, //유상금액
        String ctrAmt, //조정금액
        String cshBilSlAmt, /*현금 매출금액*/
        String cshBilDpAmt, /*현금 입금액*/
        String adpBilSlAmt, //합산청구-매출금액
        String adpBilDpAmt, //합산청구-입금액
        String vacSlAmt, //가상계좌-매출금액
        String vacDpAmt, //가상계좌-입금액
        String cardSlAmt, //카드-매출금액
        String cardDpAmt, //카드-입금액
        String slAmtSum, //매출금액 합계
        String dpAmtSum //입금액 합계
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금집계현황-서비스센터 목록 - 합계 Search Result Dto
    @ApiModel(value = "WwdbAggregateServiceCenterDto-SearchAggregateServiceCenterTotalRes")
    public record SearchAggregateServiceCenterTotalRes(
        String recapAmtTt, //유상금액 총합계
        String ctrAmtTt, //조정금액 총합계
        String adpBilSlAmtTt, //합산청구-매출금액 총합계
        String adpBilDpAmtTt, //합산청구-입금액 총합계
        String vacSlAmtTt, //가상계좌-매출금액 총합계
        String vacDpAmtTt, //가상계좌-입금액 총합계
        String cardSlAmtTt, //카드-매출금액 총합계
        String cardDpAmtTt, //카드-입금액 총합계
        String cashSlAmtTt, //현금-매출금액 총합계
        String cashDpAmtTt, //현금-입금액 총합계
        String slAmtSumTt, //매출금액 총합계
        String dpAmtSumTt //입금액 총합계
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 엔지니어 조직 센터 조회 Search Result Dto
    @ApiModel(value = "WwdbAggregateServiceCenterDto-SearchAggregateEngineerOgRes")
    public record SearchAggregateEngineerOgRes(
        String ogId, // 서비스센터 value
        String ogTpCd,
        String ogCd,
        String ogNm, // 센터명
        String ogCdNm,
        String hgrOgId
    ) {}

}
