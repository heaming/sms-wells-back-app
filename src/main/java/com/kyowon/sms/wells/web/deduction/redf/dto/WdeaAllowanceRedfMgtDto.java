package com.kyowon.sms.wells.web.deduction.redf.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdeaAllowanceRedfMgtDto {
    @Builder
    @ApiModel("WdeaAllowanceRedfMgtDto-SearchReq")
    public record SearchReq(
        String redfAdsbOcYmFrom, // 발생년월(from)
        String redfAdsbOcYmTo, // 발생년월(to)
        String slYmFrom, // 매출년월(from)
        String slYmTo, // 매출년월(to)
        String ogTpCd, // 조직유형
        String sellTpCd, // 상품구분
        String envrYn, // 환경여부
        String redfAdsbTpCd, // 처리유형
        String prtnrNoFrom, // 파트너번호(from)
        String prtnrNoTo, // 파트너번호(to)

        /*조직유형 총판, B2B의 경우 소속코드로 검색조건 변경*/
        String ogCdFrom, // 소속코드(from)
        String ogCdTo // 소속코드(to)
    ) {}

    @ApiModel("WdeaAllowanceRedfMgtDto-SearchRedfRecordRes")
    public record SearchAwRedfRes(
        String redfAdsbOcYm, /* 발생년월 */
        String slYm, /*매출년월     */
        String pdGub, /* 상품구분   */
        String envrYn, /* 환경여부  */
        String pdCd, /*상품코드*/
        String pdClsfNm, /*상품명*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrNoSn, /*계약상세번호*/
        String cstKnm, /*고객명*/
        String prtnrKnm, /*판매자성명*/
        String prtnrNo, /*파트너번호*/
        String gnlrLedr, /*총괄단장, TODO: 조직쪽에서 정리되면 추가*/
        String localAreaManager, /*지역단장, TODO: 조직쪽에서 정리되면 추가*/
        String branchManager, /*지점장, TODO: 조직쪽에서 정리되면 추가*/
        String dstrcManager, /*지구장, TODO: 조직쪽에서 정리되면 추가*/
        String perfTpCd, /*처리유형(실적유형코드) 01: 취소, 10: 연체*/
        String thmOcDlqAmt, /*연체금액, 당월발생연체금액*/
        String cntrDtlStatChRsonNm, /*취소유형*/
        String dlqYm, /*연체되물림년월*/
        String rdsbYm, /*재지급년월*/
        String rentalAmt, /*렌탈료*/
        String mchnChTpCd, /*기변유형*/
        String feeFxamYn, /*정액여부*/
        String feeAckmtCt, /*인정건수*/
        String perfVal1, /*BS인정건수*/
        String perfVal2, /*BS신규건수*/
        String sellDscDvCd, /*할인구분*/
        String sellDscrCd, /*할인유형*/
        String sellDscTpCd, /*할인제도*/
        String pakSn, /*패키지 일련번호*/
        String feeAckmtBaseAmt, /*수수료 인정기준금액 - wells M용*/
        String ackmtPerfAmt, /*인정실적금액 - wells P 용*/
        String booYn, /*예약여부*/
        String ogId, /*지점*/
        String cntrPdStrtdt, /*설치일자*/
        String canDt, /*취소일자*/
        String cpsnRedfYn /*강제되물림여부*/
    ) {}

    @ApiModel("WdeaAllowanceRedfMgtDto-SearchRedfDetailRes")
    public record SearchRedfRes(
        String baseYm, /*발생년월*/
        String slYm, /*매출년월*/
        String ogNm, /*업체명*/
        String ogCd, /*소속코드*/
        String prtnrKnm, /*판매자명*/
        String prtnrNo, /*파트너번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrNoSn, /*계약상세번호*/
        String cstKnm, /*고객명*/
        String pdCd, /*제품코드*/
        String pdClsfNm, /*제품명*/
        String modelNo, /*모델명*/
        String sellDscDvCd, /*할인구분*/
        String sellDscTpCd, /*할인유형*/
        String pmotUswyDvCd, /*업소*/
        String bfsvcPrdCd, /*주기*/
        String mchnCh1, /*기변영*/
        String mchnCh2, /*기변센*/
        String rcpDt, /*계약일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String redfAmt /*되물림액*/
    ) {}

    @Builder
    @ApiModel("WdeaAllowanceRedfMgtDto-SearchRedfBizdReq")
    public record SearchRedfBizdReq(
        String redfAdsbOcYmFrom, // 발생년월(from)
        String redfAdsbOcYmTo, // 발생년월(to)
        String perfYmFrom, // 실적년월(from)
        String perfYmTo, // 실적년월(to)
        String ogTpCd, // 조직유형
        String ogCd, // 조직코드
        String rsbDvCd, // 직책구분
        String redfAdsbTpCd, // 처리유형
        String prtnrNo // 파트너번호
    ) {}

    @ApiModel("WdeaAllowanceRedfMgtDto-SearchRedfBizdRes")
    public record SearchRedfBizdRes(
        /*기본정보*/
        String ogTpCd,
        String ogTpNm,
        String perfYm, /*실적년월*/
        String baseYm, /*발생년월*/
        String ogCd, /*소속코드*/
        String prtnrKnm, /*성명*/
        String prtnrNo, /*파트너번호*/
        String pstnDvCd, /*직급*/
        String qlfDvNm, /*자격*/
        String qlfDvNm1, /*자격(M+1)*/
        String dstrcGd, /*지구등급*/
        String atdcDc, /*기본정보.미팅*/
        String perfDvCd, /*구분(0: 개인, 2: 지점)*/
        String perfDvNm, /*실적구분(0: 개인, 2: 지점)*/
        String brchDec, /*지점순감*/
        String marchDstrcYn, /*3월지구장*/
        String chYm, /*수석승급기준월*/
        String wpChYm, /*승급년월*/
        String educCpcMm, /*수석정착수료월*/
        String aksd48Yn, /*정착 지급여부*/
        String edu17Yn, /*플래너실전 수료여부*/
        String sellAmt, /*귀속월매출*/
        String ackmtPerfRt, /*지급율*/
        String oneteamYn, /*단독지구*/

        /*=============실적===============*/
        String homeCare, /*홈케어실적*/
        String mcPerf, /*기변실적*/

        /*환경가전*/
        // 환경가전 /* WELLS-P + 지점장 이상, 귀속전월실적 추가 */
        String tbEnvrElhm, /*환경가전.귀속전월실적*/
        String tEnvrElhm, /*환경가전.귀속월실적*/
        String bEnvrElhm, /*환경가전.되물림전실적*/
        String envrElhm, /*환경가전.되물림실적*/
        String aEnvrElhm, /*환경가전.되물림후실적*/

        /*환경가전(건수)*/
        String tbEnvrElhmCnt, /*환경가전(건수).귀속전월실적*/
        String tEnvrElhmCnt, /*환경가전(건수).귀속월실적*/
        String bEnvrElhmCnt, /*환경가전(건수).되물림전실적*/
        String envrElhmCnt, /*환경가전(건수).되물림실적*/
        String aEnvrElhmCnt, /*환경가전(건수).되물림후실적*/

        /*환경가전 건수(조직)*/
        String tbEnvrElhmCntJo, /*환경가전(조직).귀속전월실적*/
        String tEnvrElhmCntJo, /*환경가전(조직).귀속월실적*/
        String bEnvrElhmCntJo, /*환경가전(조직).되물림전실적*/
        String envrElhmCntJo, /*환경가전(조직).되물림실적*/
        String aEnvrElhmCntJo, /*환경가전(조직).되물림후실적*/

        /*환경가전 건수(개인)*/
        String tbEnvrElhmCntPr, /*환경가전(개인).귀속전월실적*/
        String tEnvrElhmCntPr, /*환경가전(개인).귀속월실적*/
        String bEnvrElhmCntPr, /*환경가전(개인).되물림전실적*/
        String envrElhmCntPr, /*환경가전(개인).되물림실적*/
        String aEnvrElhmCntPr, /*환경가전(개인).되물림후실적*/

        /*환경가전(기준가격)*/
        String tEnvrElhmBaseAmt, /*환경가전(기준가격).귀속월실적*/
        String bEnvrElhmBaseAmt, /*환경가전(기준가격).되물림전실적*/
        String envrElhmBaseAmt, /*환경가전(기준가격).되물림실적*/
        String aEnvrElhmBaseAmt, /*환경가전(기준가격).되물림후실적*/

        /*환경가전 렌탈*/
        String tbEnvrElhmRental, /*환경가전 렌탈.귀속전월실적*/
        String tEnvrElhmRental, /*환경가전 렌탈.귀속월실적*/
        String bEnvrElhmRental, /*환경가전 렌탈.되물림전실적*/
        String envrElhmRental, /*환경가전 렌탈.되물림실적*/
        String aEnvrElhmRental, /*환경가전 렌탈.되물림후실적*/

        /*환경가전 렌탈(조직)*/
        String tbEnvrElhmRentalJo, /*환경가전 렌탈(조직).귀속전월실적*/
        String tEnvrElhmRentalJo, /*환경가전 렌탈(조직).귀속월실적*/
        String bEnvrElhmRentalJo, /*환경가전 렌탈(조직).되물림전실적*/
        String envrElhmRentalJo, /*환경가전 렌탈(조직).되물림실적*/
        String aEnvrElhmRentalJo, /*환경가전 렌탈(조직).되물림후실적*/

        /*환경가전 렌탈(개인)*/
        String tbEnvrElhmRentalPr, /*환경가전 렌탈(개인).귀속전월실적*/
        String tEnvrElhmRentalPr, /*환경가전 렌탈(개인).귀속월실적*/
        String bEnvrElhmRentalPr, /*환경가전 렌탈(개인).되물림전실적*/
        String envrElhmRentalPr, /*환경가전 렌탈(개인).되물림실적*/
        String aEnvrElhmRentalPr, /*환경가전 렌탈(개인).되물림후실적*/

        /*환경가전 일시불*/
        String tEnvrElhmSpay, /*환경가전 일시불.귀속월실적*/
        String bEnvrElhmSpay, /*환경가전 일시불.되물림전실적*/
        String envrElhmSpay, /*환경가전 일시불.되물림실적*/
        String aEnvrElhmSpay, /*환경가전 일시불.되물림후실적*/

        /*환경가전 일시불(조직)*/
        String tbEnvrElhmSpayJo, /*환경가전 일시불(조직).귀속전월실적*/
        String tEnvrElhmSpayJo, /*환경가전 일시불(조직).귀속월실적*/
        String bEnvrElhmSpayJo, /*환경가전 일시불(조직).되물림전실적*/
        String envrElhmSpayJo, /*환경가전 일시불(조직).되물림실적*/
        String aEnvrElhmSpayJo, /*환경가전 일시불(조직).되물림후실적*/

        /*환경가전 일시불(개인)*/
        String tbEnvrElhmSpayPr, /*환경가전 일시불(개인).귀속전월실적*/
        String tEnvrElhmSpayPr, /*환경가전 일시불(개인).귀속월실적*/
        String bEnvrElhmSpayPr, /*환경가전 일시불(개인).되물림전실적*/
        String envrElhmSpayPr, /*환경가전 일시불(개인).되물림실적*/
        String aEnvrElhmSpayPr, /*환경가전 일시불(개인).되물림후실적*/

        /*환경가전외*/
        String tbEnvrElhmExcp, /*환경가전외.귀속전월실적*/
        String tEnvrElhmExcp, /*환경가전외.귀속월실적*/
        String bEnvrElhmExcp, /*환경가전외.되물림전실적*/
        String envrElhmExcp, /*환경가전외.되물림실적*/
        String aEnvrElhmExcp, /*환경가전외.되물림후실적*/

        /*환경가전외 일시불(조직)*/
        String tbEnvrElhmExcpJo, /*환경가전외 일시불(조직).귀속전월실적*/
        String tEnvrElhmExcpJo, /*환경가전외 일시불(조직).귀속월실적*/
        String bEnvrElhmExcpJo, /*환경가전외 일시불(조직).되물림전실적*/
        String envrElhmExcpJo, /*환경가전외 일시불(조직).되물림실적*/
        String aEnvrElhmExcpJo, /*환경가전외 일시불(조직).되물림후실적*/

        /*환경가전외 일시불(개인)*/
        String tbEnvrElhmExcpPr, /*환경가전외 일시불(개인).귀속전월실적*/
        String tEnvrElhmExcpPr, /*환경가전외 일시불(개인).귀속월실적*/
        String bEnvrElhmExcpPr, /*환경가전외 일시불(개인).되물림전실적*/
        String envrElhmExcpPr, /*환경가전외 일시불(개인).되물림실적*/
        String aEnvrElhmExcpPr, /*환경가전외 일시불(개인).되물림후실적*/

        /*환경가전외 정액*/
        String tEnvrElhmExcpFxam, /*환경가전외 정액.귀속월실적*/
        String bEnvrElhmExcpFxam, /*환경가전외 정액.되물림전실적*/
        String envrElhmExcpFxam, /*환경가전외 정액.되물림실적*/
        String aEnvrElhmExcpFxam, /*환경가전외 정액.되물림후실적*/

        /*환경가전외 정액(개인)*/
        String tbEnvrElhmExcpFxamPr, /*환경가전외 정액(개인).귀속전월실적*/
        String tEnvrElhmExcpFxamPr, /*환경가전외 정액(개인).귀속월실적*/
        String bEnvrElhmExcpFxamPr, /*환경가전외 정액(개인).되물림전실적*/
        String envrElhmExcpFxamPr, /*환경가전외 정액(개인).되물림실적*/
        String aEnvrElhmExcpFxamPr, /*환경가전외 정액(개인).되물림후실적*/

        /*신규판매 건수(조직)*/
        String tbNwSellCntJo, /*신규판매 건수(조직).귀속전월실적*/
        String tNwSellCntJo, /*신규판매 건수(조직).귀속월실적*/
        String bNwSellCntJo, /*신규판매 건수(조직).되물림전실적*/
        String nwSellCntJo, /*신규판매 건수(조직).되물림실적*/
        String aNwSellCntJo, /*신규판매 건수(조직).되물림후실적*/

        /*신규판매 건수(개인)*/
        String tbNwSellCntPr, /*신규판매 건수(개인).귀속전월실적*/
        String tNwSellCntPr, /*신규판매 건수(개인).귀속월실적*/
        String bNwSellCntPr, /*신규판매 건수(개인).되물림전실적*/
        String nwSellCntPr, /*신규판매 건수(개인).되물림실적*/
        String aNwSellCntPr, /*신규판매 건수(개인).되물림후실적*/

        /*환경가전 정액(개인)*/
        String tbEnvrElhmFxamPr, /*환경가전 정액(개인).귀속전월실적*/
        String tEnvrElhmFxamPr, /*환경가전 정액(개인).귀속월실적*/
        String bEnvrElhmFxamPr, /*환경가전 정액(개인).되물림전실적*/
        String envrElhmFxamPr, /*환경가전 정액(개인).되물림실적*/
        String aEnvrElhmFxamPr, /*환경가전 정액(개인).되물림후실적*/

        /*조직관리 실활동(명)*/
        String tbOgMgtPpl, /*조직관리 실활동(명).전월대비*/
        String tOgMgtPpl, /*조직관리 실활동(명).귀속월실적*/
        String bOgMgtPpl, /*조직관리 실활동(명).되물림전실적*/
        String ogMgtPpl, /*조직관리 실활동(명).되물림실적*/
        String aOgMgtPpl, /*조직관리 실활동(명).되물림후실적*/

        /*기변(WELLS-P)*/
        String tbMc, /*기변(WELLS-P).귀속전월실적*/
        String tMc, /*기변(WELLS-P).귀속월실적*/
        String bMc, /*기변(WELLS-P).되물림전실적*/
        String mc, /*기변(WELLS-P).되물림실적*/
        String aMc, /*기변(WELLS-P).되물림후실적*/

        /*정액(WELLS-P)*/
        String tbFxam, /*정액(WELLS-P).귀속전월실적*/
        String tFxam, /*정액(WELLS-P).귀속월실적*/
        String bFxam, /*정액(WELLS-P).되물림전실적*/
        String fxam, /*정액(WELLS-P).되물림실적*/
        String aFxam, /*정액(WELLS-P).되물림후실적*/

        /*===============되물림 수수료==============*/
        String mchnChRedf201903, /*기변수당*/
        String mchnChRedf201904, /*기기변경*/
        String spRedf, /*수석플래너*/
        String homeCareRedf, /*홈케어*/
        String sellEncrgRedf, /*판매장려*/
        String eduRedf, /*교육*/
        String settleRedf, /*정착*/
        String sellEncrgRettRedf, /*판매장려 소급*/
        String envrTrgRedf, /*환경가전 목표달성*/
        String envrBznsRedf201903, /*환경가전 영업*/
        String envrBznsRedf201904, /*환경가전 비례*/
        String envrExcpBznsRedf201903, /*환경가전외 영업*/
        String envrExcpBznsRedf201904, /*환경가전외 비례*/
        String envrMetgRedf, /*환경가전 미팅*/
        String envrExcpMetgRedf, /*환경가전외 미팅*/
        String envrOgRedf, /*환경가전 조직*/
        String envrExcpOgRedf201903, /*환경가전외 조직*/
        String envrExcpOgRedf201904, /*환경가전외 조직비례*/
        String envrOgMetg, /*환경가전 조직미팅*/
        String envrExcpOgMetgRedf, /*환경가전외 조직미팅*/
        String ogSellEncrgRedf, /*조직판매 장려*/
        String nwSellRedf, /*신규판매*/
        String ogMgtRedf, /*조직관리*/

        /*WELLS-P 되물림수수료*/
        String wpFxamRedfDstrc, /*정액(지구장이하)*/
        String wpFxamRedfBrch, /*정액(지점장이상)*/
        String wpEnvrExcpPrRedf, /*가전외 개인비례*/
        String wpEnvrPrRedf, /*가전 개인비례*/
        String wpPrSellEncrgRedf, /*개인 판매장려*/
        String wpMetgRedf, /*미팅*/
        String wpSettleRedfDstrc, /*정착(지구장이하)*/
        String wpSettleRedfBrch, /*정착(지점장이상)*/
        String wpEnvrOgRedf, /*가전 조직비례*/
        String wpEnvrExcpOgRedf, /*가전외 조직비례*/
        String wpOgSellEncrgRedf, /*조직 판매장려*/
        String dlqRedfAmt, /*연체되물림*/

        /*되물림 합계금액(그리드별)*/
        String sumPerfLast201903RedfDstrc, /*2019.03 이전 + 지구장이하 그리드 되물림 합계금액*/
        String sumPerf201904Between202012RedfDstrc, /*2019.04 ~ 2020.12 + 지구장이하 그리드 되물림 합계금액*/
        String sumPerf202101RedfDstrc, /*2021.01 이후 + 지구장이하 그리드 되물림 합계금액*/
        String sumPerfAllRedfDstrc, /*실적년월 전기간 + 지구장이하 그리드 되물림 합계금액*/
        String sumPerfLast201903RedfBrch, /* 2019.03 이전 + 지점장 이상 그리드 되물림 합계금액*/
        String sumPerf201904Between202012RedfBrch, /* 2019.04 ~ 2020.12 + 지점장 이상 그리드 되물림 합계금액*/
        String sumPerf202101RedfBrch, /* 2021.01 이후 + 지점장 이상 그리드 되물림 합계 금액*/
        String sumPerfAllRedfBrch, /*실적년월 전기간 + 지점장 이상 그리드 되물림 합계금액*/
        String sumWpRedfDstrc, /*WELLS-P + 지구장 이하 되물림 합계금액*/
        String sumWpRedfBrch, /*WELLS-P + 지점장 이상 되물림 합계 금액*/
        /*=================================*/

        String tRedfPpl, /*적채용.귀속월인원*/
        String bRedfPpl, /*적채용.되물림전 인원*/
        String redfPpl, /*적채용.되물림인원*/
        String aRedfPpl, /*적채용.되물림후 인원*/
        String cpsnRedfYn /*강제되물림포함여부*/

    ) {}

}
