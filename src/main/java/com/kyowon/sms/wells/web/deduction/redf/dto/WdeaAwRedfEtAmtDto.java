package com.kyowon.sms.wells.web.deduction.redf.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdeaAwRedfEtAmtDto {

    @Builder
    @ApiModel("WdeaAwRedfEtAmtDto-SearchRedfIzReq")
    public record SearchRedfIzReq(
        String perfYm, //실적년월
        String ogTpCd, //조직
        String perfDvCd, //실적구분
        String prtnrNo, //파트너번호
        String cstNo, //회원번호
        String cstNm //회원명

    ) {}

    @ApiModel("WdeaAwRedfEtAmtDto-SearchRedfIzRes")
    public record SearchRedfIzRes(

        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrNoSn, /*계약상세번호*/
        String cstKnm, /*고객명*/
        String istPlcTpCd, /*설치처(설치장소유형코드)*/
        String istPlc, /*설치처(설치장소유형코드)*/
        String pdNm, /*상품명*/
        String mchnChTpCd, /*기변유형(기기변경유형코드)*/
        String chdvcTp, /*기변유형(기기변경유형코드)*/
        String prtnrKnm, /*판매자(파트너성명)*/
        String prtnrNo, /*파트너번호*/
        String pstnDvNm, /*직책(직급)*/
        String canRedfMm, /*취소되물림월, 0202: 취소, 0203: 연체*/
        String dlqRedfMm, /*연체되물림월, 0202: 취소, 0203: 연체*/
        String cpsnRedf, /*강제되물림*/
        String ackmtPerfAmt, /*인정실적(인정실적금액)*/
        String ackmtCnt, /*인정건수*/
        String basePrc /*기준가격*/

    ) {}

    @ApiModel("WdeaAwRedfEtAmtDto-SearchRedfIzPerfPsRes")
    public record SearchRedfIzPerfPsRes(
        String perfDv, /*실적구분*/
        String indvAckmtCt, /* 개인 인정건수*/
        String indvElhmPerf, /*개인 가전실적*/
        String indvElhmExcpPerf, /*개인 가전외실적*/
        String brchAckmtCt, /*지점 인정건수*/
        String brchElhmPerf, /*지점 가전실적*/
        String brchElhmExcpPerf, /*지점 가전외실적*/
        String brchNwSellCt, /*지점 신규판매건수*/
        String mnger, /* 매니저*/
        String plar, /*플래너*/
        String indvElhmBaseAmt, /*개인 가전기준가*/
        String indvElhmExcpBaseAmt, /*개인 가전외기준가*/
        String brchElhmBaseAmt, /*지점 가전기준가*/
        String brchElhmExcpBaseAmt /*지점 가전외기준가*/

    ) {}

    @Builder
    @ApiModel("WdeaAwRedfEtAmtDto-SaveReq")
    public record SaveReq(
        String cntrNo,
        String cntrSn,
        String perfYm,
        String prtnrNo,
        String pagingChk
    ) {}

    @Builder
    @ApiModel("WdeaAwRedfEtAmtDto-SearchRedfEtRes")
    public record SearchRedfEtRes(
        String dv, /*구분*/
        String indvElhmPerf, /*개인 가전비례*/
        String indvElhmExcpPrpn, /*개인 가전외비례*/
        String indvSellEncrg, /*개인 판매장려*/
        String indvMetg, /*개인 미팅*/
        String indvMchnCh, /*개인 기기변경*/
        String indvSettle, /*개인 정착*/
        String elhmOgPrpn, /*지점 가전조직비례*/
        String elhmExcpOgPrpn, /*지점 가전외조직비례*/
        String ogSellEncrg, /*지점 조직판매장려*/
        String redfEtSum, /*합계*/
        String indvElhmPrpnMetg, /*개인 가전비례+미팅*/
        String indvElhmExcpPrpnMetg, /*개인 가전외비례+미팅*/
        String educ, /*교육 */
        String nwSell, /*신규 판매*/
        String ogMngt /*조직 관리*/

    ) {}
}
