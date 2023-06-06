package com.kyowon.sms.wells.web.deduction.redf.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdeaHomeMasterRedfCreateDto {
    @Builder
    @ApiModel("WdeaHomeMasterRedfCreateDto-SearchReq")
    public record SearchReq(
        String redfAdsbOcYm, // 발생년월
        String perfYmFrom, // 실적년월(from) 
        String perfYmTo, // 실적년월(to)
        String rsbDvCd, // 직책구분
        String redfAdsbTpCd, // 처리유형
        String prtnrNo, // 파트너번호
        String ogCd // 조직코드
    ) {}

    @ApiModel("WdeaHomeMasterRedfCreateDto-SearchRes")
    public record SearchRes(
        String perfYm,
        String ogCd,
        String prtnrKnm,
        String prtnrNo,
        String pstnDvCd,
        String rsbDvNm,
        /*t~ : 귀속월실적, a~ : 되물림전실적, b~ : 되물림후실적, ~ : 되물림실적*/
        /*환경가전 - 개인*/
        String tEnvrElhmCntPr, /*귀속월실적.환경가전 - 개인*/
        String aEnvrElhmCntPr, /*되물림전실적.환경가전 - 개인*/
        String bEnvrElhmCntPr, /*되물림후실적.환경가전 - 개인*/
        String envrElhmCntPr, /*되물림실적.환경가전 - 개인*/
        /*환경가전 렌탈(매트리스) - 개인*/
        String tEnvrElhmRentalMattPr,
        String aEnvrElhmRentalMattPr,
        String bEnvrElhmRentalMattPr,
        String envrElhmRentalMattPr,
        /*환경가전 렌탈(매트리스 외) - 개인*/
        String tEnvrElhmRentalMattExcpPr,
        String aEnvrElhmRentalMattExcpPr,
        String bEnvrElhmRentalMattExcpPr,
        String envrElhmRentalMattExcpPr,
        /*환경가전 일시불 - 개인*/
        String tEnvrElhmSpayPr,
        String aEnvrElhmSpayPr,
        String bEnvrElhmSpayPr,
        String envrElhmSpayPr,
        /*환경가전 외 일시불  - 개인*/
        String tEnvrElhmExcpSpayPr,
        String aEnvrElhmExcpSpayPr,
        String bEnvrElhmExcpSpayPr,
        String envrElhmExcpSpayPr,
        /*환경가전 - 조직*/
        String tEnvrElhmCntJo,
        String aEnvrElhmCntJo,
        String bEnvrElhmCntJo,
        String envrElhmCntJo,
        /*환경가전 렌탈(매트리스) - 조직*/
        String tEnvrElhmRentalMattJo,
        String aEnvrElhmRentalMattJo,
        String bEnvrElhmRentalMattJo,
        String envrElhmRentalMattJo,
        /*환경가전 렌탈(매트리스 외) - 조직*/
        String tEnvrElhmRentalMattExcpJo,
        String aEnvrElhmRentalMattExcpJo,
        String bEnvrElhmRentalMattExcpJo,
        String envrElhmRentalMattExcpJo,
        /*환경가전 일시불 - 조직*/
        String tEnvrElhmSpayJo,
        String aEnvrElhmSpayJo,
        String bEnvrElhmSpayJo,
        String envrElhmSpayJo,
        /*환경가전 외 일시불  - 조직*/
        String tEnvrElhmExcpSpayJo,
        String aEnvrElhmExcpSpayJo,
        String bEnvrElhmExcpSpayJo,
        String envrElhmExcpSpayJo,
        /* 되물림 수수료 - 공통*/
        String encrgRedf1, /*장려1(생산장려/목표달성)*/
        /* 되물림 수수료 - 개인*/
        String envrBznsRedfPr, /*환경가전 비례(개인)*/
        String encrgRedf2Pr, /*장려2(개인교차판매수당)*/
        String spayRedfPr, /*일시불(환경가전기기변경)*/
        String sumRedfAmtPr, /*되물림합계금액(개인)*/
        /* 되물림 수수료 - 조직*/
        String envrBznsRedfJo, /*환경가전 비례(조직)*/
        String encrgRedf2Jo, /*장려2(지점비례수당)*/
        String spayRedfJo, /*일시불(환경가전기기변경)*/
        String ogSellPrpn, /*조직판매비례(환경가전조직비례)*/
        String ogSellLkRedf, /*조직판매연계(조직판매장려)*/
        String ogMgtRedf, /*조직관리(조직관리)*/
        String nwSellRedf, /*신규판매(조직신규판매)*/
        String ogSellPrpnRedf, /*조직판매일시불(환경가전외조직비례)*/
        String sumRedfAmtJo /*되물림합계금액(조직)*/
    ) {}

    @ApiModel("WdeaHomeMasterRedfCreateDto-SearchDlqRes")
    public record SearchDlqRes(
        String baseYm, /*발생년월*/
        String perfYm, /*실적년월*/
        String prtnrNo, /*파트너번호*/
        String prtnrKnm, /*성명*/
        String ogTpNm, /*조직유형*/
        String perfDvNm, /*실적구분*/
        String rentalRedfPerf, /*되물림 실적.렌탈*/
        String envrElhmExcpSpayRedfPerf, /*되물림 실적.가전외 일시불 실적*/
        String rentalDlqRedfPerf, /*연체되물림 실적.렌탈*/
        String envrElhmExcpSpayDlqRedfPerf, /*연체되물림 실적.가전외 일시불 실적*/
        String dlqSum /*연체되물림 실적.계*/
    ) {}

}
