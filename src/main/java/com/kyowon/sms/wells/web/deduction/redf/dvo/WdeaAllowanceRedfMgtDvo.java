package com.kyowon.sms.wells.web.deduction.redf.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdeaAllowanceRedfMgtDvo {
    /*영업부 되물림 생성 목록조회 converter용 전체 record*/

    String ogTpCd;
    String ogTpNm;
    String perfYm; /*실적년월*/
    String baseYm; /*발생년월*/
    String ogCd; /*소속코드*/
    String prtnrKnm; /*성명*/
    String prtnrNo; /*파트너번호*/
    String pstnDvCd; /*직급*/
    String qlfDvNm; /*자격*/
    String qlfDvNm1; /*자격(M+1)*/
    String dstrcGd; /*지구등급*/
    String atdcDc; /*기본정보.미팅*/
    String perfDvCd; /*구분(0: 개인; 2: 지점)*/
    //        String perfDvNm; /*실적구분(0: 개인; 2: 지점)*/
    String brchDec; /*지점순감*/
    String marchDstrcYn; /*3월지구장*/
    String chYm; /*수석승급기준월*/
    String wpChYm; /*승급년월*/
    String educCpcMm; /*수석정착수료월*/
    String aksd48Yn; /*정착 지급여부*/
    String edu17Yn; /*플래너실전 수료여부*/
    String sellAmt; /*귀속월매출*/
    String ackmtPerfRt; /*지급율*/
    String oneteamYn; /*단독지구*/
    String aprilQlfDvNm; /*4월 자격*/

    /*=============실적===============*/
    String homeCare; /*홈케어실적*/
    String mcPerf; /*기변실적*/

    /*환경가전*/
    // 환경가전 /* WELLS-P + 지점장 이상; 귀속전월실적 추가 */
    String envrElhmTb; /*환경가전.귀속전월실적*/
    String envrElhmT; /*환경가전.귀속월실적*/
    String envrElhmB; /*환경가전.되물림전실적*/
    String envrElhm; /*환경가전.되물림실적*/
    String envrElhmA; /*환경가전.되물림후실적*/

    /*환경가전(건수)*/
    String envrElhmCntTb; /*환경가전(건수).귀속전월실적*/
    String envrElhmCntT; /*환경가전(건수).귀속월실적*/
    String envrElhmCntB; /*환경가전(건수).되물림전실적*/
    String envrElhmCnt; /*환경가전(건수).되물림실적*/
    String envrElhmCntA; /*환경가전(건수).되물림후실적*/

    /*환경가전 건수(조직)*/
    String envrElhmCntJoTb; /*환경가전(조직).귀속전월실적*/
    String envrElhmCntJoT; /*환경가전(조직).귀속월실적*/
    String envrElhmCntJoB; /*환경가전(조직).되물림전실적*/
    String envrElhmCntJo; /*환경가전(조직).되물림실적*/
    String envrElhmCntJoA; /*환경가전(조직).되물림후실적*/

    /*환경가전 건수(개인)*/
    String envrElhmCntPrTb; /*환경가전(개인).귀속전월실적*/
    String envrElhmCntPrT; /*환경가전(개인).귀속월실적*/
    String envrElhmCntPrB; /*환경가전(개인).되물림전실적*/
    String envrElhmCntPr; /*환경가전(개인).되물림실적*/
    String envrElhmCntPrA; /*환경가전(개인).되물림후실적*/

    /*환경가전(기준가격)*/
    String envrElhmBaseAmtT; /*환경가전(기준가격).귀속월실적*/
    String envrElhmBaseAmtB; /*환경가전(기준가격).되물림전실적*/
    String envrElhmBaseAmt; /*환경가전(기준가격).되물림실적*/
    String envrElhmBaseAmtA; /*환경가전(기준가격).되물림후실적*/

    /*환경가전 렌탈*/
    String envrElhmRentalTb; /*환경가전 렌탈.귀속전월실적*/
    String envrElhmRentalT; /*환경가전 렌탈.귀속월실적*/
    String envrElhmRentalB; /*환경가전 렌탈.되물림전실적*/
    String envrElhmRental; /*환경가전 렌탈.되물림실적*/
    String envrElhmRentalA; /*환경가전 렌탈.되물림후실적*/

    /*환경가전 렌탈(조직)*/
    String envrElhmRentalJoTb; /*환경가전 렌탈(조직).귀속전월실적*/
    String envrElhmRentalJoT; /*환경가전 렌탈(조직).귀속월실적*/
    String envrElhmRentalJoB; /*환경가전 렌탈(조직).되물림전실적*/
    String envrElhmRentalJo; /*환경가전 렌탈(조직).되물림실적*/
    String envrElhmRentalJoA; /*환경가전 렌탈(조직).되물림후실적*/

    /*환경가전 렌탈(개인)*/
    String envrElhmRentalPrTb; /*환경가전 렌탈(개인).귀속전월실적*/
    String envrElhmRentalPrT; /*환경가전 렌탈(개인).귀속월실적*/
    String envrElhmRentalPrB; /*환경가전 렌탈(개인).되물림전실적*/
    String envrElhmRentalPr; /*환경가전 렌탈(개인).되물림실적*/
    String envrElhmRentalPrA; /*환경가전 렌탈(개인).되물림후실적*/

    /*환경가전 일시불*/
    String envrElhmSpayT; /*환경가전 일시불.귀속월실적*/
    String envrElhmSpayB; /*환경가전 일시불.되물림전실적*/
    String envrElhmSpay; /*환경가전 일시불.되물림실적*/
    String envrElhmSpayA; /*환경가전 일시불.되물림후실적*/

    /*환경가전 일시불(조직)*/
    String envrElhmSpayJoTb; /*환경가전 일시불(조직).귀속전월실적*/
    String envrElhmSpayJoT; /*환경가전 일시불(조직).귀속월실적*/
    String envrElhmSpayJoB; /*환경가전 일시불(조직).되물림전실적*/
    String envrElhmSpayJo; /*환경가전 일시불(조직).되물림실적*/
    String envrElhmSpayJoA; /*환경가전 일시불(조직).되물림후실적*/

    /*환경가전 일시불(개인)*/
    String envrElhmSpayPrTb; /*환경가전 일시불(개인).귀속전월실적*/
    String envrElhmSpayPrT; /*환경가전 일시불(개인).귀속월실적*/
    String envrElhmSpayPrB; /*환경가전 일시불(개인).되물림전실적*/
    String envrElhmSpayPr; /*환경가전 일시불(개인).되물림실적*/
    String envrElhmSpayPrA; /*환경가전 일시불(개인).되물림후실적*/

    /*환경가전 정액*/
    String envrElhmFxamT; /*환경가전 정액.귀속월실적*/
    String envrElhmFxamB; /*환경가전 정액.되물림전실적*/
    String envrElhmFxam; /*환경가전 정액.되물림실적*/
    String envrElhmFxamA; /*환경가전 정액.되물림후실적*/

    /*환경가전외*/
    String envrElhmExcpTb; /*환경가전외.귀속전월실적*/
    String envrElhmExcpT; /*환경가전외.귀속월실적*/
    String envrElhmExcpB; /*환경가전외.되물림전실적*/
    String envrElhmExcp; /*환경가전외.되물림실적*/
    String envrElhmExcpA; /*환경가전외.되물림후실적*/

    /*환경가전외 일시불*/
    String envrElhmExcpSpayT; /*환경가전외 일시불.귀속월실적*/
    String envrElhmExcpSpayB; /*환경가전외 일시불.되물림전실적*/
    String envrElhmExcpSpay; /*환경가전외 일시불.되물림실적*/
    String envrElhmExcpSpayA; /*환경가전외 일시불.되물림후실적*/

    /*환경가전외 일시불(조직)*/
    String envrElhmExcpJoTb; /*환경가전외 일시불(조직).귀속전월실적*/
    String envrElhmExcpJoT; /*환경가전외 일시불(조직).귀속월실적*/
    String envrElhmExcpJoB; /*환경가전외 일시불(조직).되물림전실적*/
    String envrElhmExcpJo; /*환경가전외 일시불(조직).되물림실적*/
    String envrElhmExcpJoA; /*환경가전외 일시불(조직).되물림후실적*/

    /*환경가전외 일시불(개인)*/
    String envrElhmExcpPrTb; /*환경가전외 일시불(개인).귀속전월실적*/
    String envrElhmExcpPrT; /*환경가전외 일시불(개인).귀속월실적*/
    String envrElhmExcpPrB; /*환경가전외 일시불(개인).되물림전실적*/
    String envrElhmExcpPr; /*환경가전외 일시불(개인).되물림실적*/
    String envrElhmExcpPrA; /*환경가전외 일시불(개인).되물림후실적*/

    /*환경가전외 정액*/
    String envrElhmExcpFxamT; /*환경가전외 정액.귀속월실적*/
    String envrElhmExcpFxamB; /*환경가전외 정액.되물림전실적*/
    String envrElhmExcpFxam; /*환경가전외 정액.되물림실적*/
    String envrElhmExcpFxamA; /*환경가전외 정액.되물림후실적*/

    /*환경가전외 정액(개인)*/
    String envrElhmExcpFxamPrTb; /*환경가전외 정액(개인).귀속전월실적*/
    String envrElhmExcpFxamPrT; /*환경가전외 정액(개인).귀속월실적*/
    String envrElhmExcpFxamPrB; /*환경가전외 정액(개인).되물림전실적*/
    String envrElhmExcpFxamPr; /*환경가전외 정액(개인).되물림실적*/
    String envrElhmExcpFxamPrA; /*환경가전외 정액(개인).되물림후실적*/

    /*신규판매 건수(조직)*/
    String nwSellCntJoTb; /*신규판매 건수(조직).귀속전월실적*/
    String nwSellCntJoT; /*신규판매 건수(조직).귀속월실적*/
    String nwSellCntJoB; /*신규판매 건수(조직).되물림전실적*/
    String nwSellCntJo; /*신규판매 건수(조직).되물림실적*/
    String nwSellCntJoA; /*신규판매 건수(조직).되물림후실적*/

    /*신규판매 건수(개인)*/
    String nwSellCntPrTb; /*신규판매 건수(개인).귀속전월실적*/
    String nwSellCntPrT; /*신규판매 건수(개인).귀속월실적*/
    String nwSellCntPrB; /*신규판매 건수(개인).되물림전실적*/
    String nwSellCntPr; /*신규판매 건수(개인).되물림실적*/
    String nwSellCntPrA; /*신규판매 건수(개인).되물림후실적*/

    /*환경가전 정액(개인)*/
    String envrElhmFxamPrTb; /*환경가전 정액(개인).귀속전월실적*/
    String envrElhmFxamPrT; /*환경가전 정액(개인).귀속월실적*/
    String envrElhmFxamPrB; /*환경가전 정액(개인).되물림전실적*/
    String envrElhmFxamPr; /*환경가전 정액(개인).되물림실적*/
    String envrElhmFxamPrA; /*환경가전 정액(개인).되물림후실적*/

    /*조직관리 실활동(명)*/
    String ogMgtPplTb; /*조직관리 실활동(명).전월대비*/
    String ogMgtPplT; /*조직관리 실활동(명).귀속월실적*/
    String ogMgtPplB; /*조직관리 실활동(명).되물림전실적*/
    String ogMgtPpl; /*조직관리 실활동(명).되물림실적*/
    String ogMgtPplA; /*조직관리 실활동(명).되물림후실적*/

    /*기변(WELLS-P)*/
    String mcTb; /*기변(WELLS-P).귀속전월실적*/
    String mcT; /*기변(WELLS-P).귀속월실적*/
    String mcB; /*기변(WELLS-P).되물림전실적*/
    String mc; /*기변(WELLS-P).되물림실적*/
    String mcA; /*기변(WELLS-P).되물림후실적*/

    /*정액(WELLS-P)*/
    String fxamTb; /*정액(WELLS-P).귀속전월실적*/
    String fxamT; /*정액(WELLS-P).귀속월실적*/
    String fxamB; /*정액(WELLS-P).되물림전실적*/
    String fxam; /*정액(WELLS-P).되물림실적*/
    String fxamA; /*정액(WELLS-P).되물림후실적*/

    /*실활동(WELLS-P; 지점장만)*/
    String actiTb; /*정액(WELLS-P).귀속전월실적*/
    String actiT; /*정액(WELLS-P).귀속월실적*/
    String actiB; /*정액(WELLS-P).되물림전실적*/
    String acti; /*정액(WELLS-P).되물림실적*/
    String actiA; /*정액(WELLS-P).되물림후실적*/

    /*===============되물림 수수료==============*/
    String mchnChRedf201903; /*기변수당*/
    String mchnChRedf201904; /*기기변경*/
    String spRedf; /*수석플래너*/
    String homeCareRedf; /*홈케어*/
    String sellEncrgRedf; /*판매장려*/
    String eduRedf; /*교육*/
    String ogEduc; /*조직교육*/
    String settleRedf; /*정착*/
    String sellEncrgRettRedf; /*판매장려 소급*/
    String envrTrgRedf; /*환경가전 목표달성*/
    String envrBznsRedf201903; /*환경가전 영업*/
    String envrBznsRedf201904; /*환경가전 비례*/
    String elhmPrpn; /*가전 비례*/
    String envrExcpBznsRedf201903; /*환경가전외 영업*/
    String envrExcpBznsRedf201904; /*환경가전외 비례*/
    String elhmExcpPrpn; /*환경가전외 비례*/
    String envrMetgRedf; /*환경가전 미팅*/
    String envrExcpMetgRedf; /*환경가전외 미팅*/
    String envrOgRedf; /*환경가전 조직*/
    String elhmOgPrpn; /*가전조직비례*/
    String envrExcpOgRedf201903; /*환경가전외 조직*/
    String envrExcpOgRedf201904; /*환경가전외 조직비례*/
    String elhmExcpOgPrpn; /*가전외 조직비례*/
    String envrOgMetg; /*환경가전 조직미팅*/
    String envrExcpOgMetgRedf; /*환경가전외 조직미팅*/
    String ogSellEncrgRedf; /*조직판매 장려*/
    String nwSellRedf; /*신규판매*/
    String ogMgtRedf; /*조직관리*/
    String rstlPerf; /*재약정실적*/
    String rstl; /*재약정*/
    String metg; /*미팅*/
    String elhmSpmtEnrg; /*가전추가장려*/
    String indvMutu; /*개인상조*/
    String ogMutu; /*조직상조*/

    /*WELLS-P 되물림수수료*/
    String wpFxamRedfDstrc; /*정액(지구장이하)*/
    String wpFxamRedfBrch; /*정액(지점장이상)*/
    String wpEnvrExcpPrRedf; /*가전외 개인비례*/
    String wpEnvrPrRedf; /*가전 개인비례*/
    String wpPrSellEncrgRedf; /*판매장려(지점장 미만)*/
    String wpBrchPrSellEncrgRedf; /*개인 판매장려(지점장 이상)*/
    String wpMetgRedf; /*미팅*/
    String wpSettleRedfDstrc; /*정착(지구장이하)*/
    String wpSettleRedfBrch; /*정착(지점장이상)*/
    String wpEnvrOgRedf; /*가전 조직비례*/
    String wpEnvrExcpOgRedf; /*가전외 조직비례*/
    String wpOgSellEncrgRedf; /*조직 판매장려*/
    String dlqRedfAmt; /*연체되물림*/
    String redfPerfRental; /*되물림 구분 연체일 때, 되물림실적.렌탈*/
    String redfPerfExcp; /*되물림 구분 연체일 때, 되물림실적.가전외일시불실적*/
    String dlqRedfPerfRental; /*되물림 구분 연체일 때, 연체되물림실적.렌탈*/
    String dlqRedfPerfExcp; /*되물림 구분 연체일 때, 연체되물림실적.가전외일시불*/
    String dlqRedfPerfTot; /*되물림 구분 연체일 때, 연체되물림실적.계*/
    String wpLifRedfAmt; /*상조되물림 취소*/
    String wpLif0203RedfAmt; /*상조되물림 연체*/

    /*되물림 합계금액(그리드별)*/
    String canRedfAmt; /*되물림 취소 전체합계*/
}
