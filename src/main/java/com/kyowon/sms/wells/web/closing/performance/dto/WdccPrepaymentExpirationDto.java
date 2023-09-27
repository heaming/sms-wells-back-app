package com.kyowon.sms.wells.web.closing.performance.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WdccPrepaymentExpirationDto {

    @ApiModel(value = "WdccPrepaymentExpirationDto-SearchReq")
    public record SearchReq(
        String slClYm, /*관리년월*/
        String pdHclsfId, /*상품대분류*/
        String pdMclsfId, /*상품중분류*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String dpStDt, /*입금시작월*/
        String dpEdDt, /*입금종료월*/
        String ogcd1, /*총괄단*/
        String ogcd2, /*지역단*/
        String ogcd3, /*지점*/
        String bzrNo, /*사업자등록번호*/
        String dpYn, /*입금여부*/
        String canYn, /*취소여부*/
        String rentalStn, /*렌탈회차_from*/
        String rentalEtn, /*렌탈회차_to*/
        String upYn, /*조회구분*/
        String upNo /*업무담당 사번*/
    ) {}

    @ApiModel(value = "WdccPrepaymentExpirationDto-SearchRes")
    public record SearchRes(
        String cntrNo, /*계약번호*/
        String cstNo, /* 고객번호*/
        String cntrDtlNo, /*계약상세번호*/
        String cstKnm, /* 고객명*/
        String rcgvpKnm, /* 설치자명*/
        String pdCd, /* 상품코드*/
        String pdNm, /* 상품명*/
        String rentalTn, /* 렐탈차월*/
        String mpyBsdt, /* 이체일자 @to-do asis의 경우 매출상세팝업의 이체일자와 다르게 나옴*/
        String lcsleDt, /* 매출일자*/
        String cntrCanDt, /* 취소일자*/
        String prmMcn, /* 선납개월*/
        String prmDscr, /* 할인율*/
        String prmEndYm, /* 선납종료년월*/
        String prmPeriod, /* 선납시작/종료*/
        String totPrmAmt, /* 선납총액*/
        String prmExcpt, /* 선납제외*/
        String prmReAplcYn, /* 선납재신청*/
        String cntrLocalTno1, /* 계약자전화번호1*/
        String cntrLocalTno2, /* 계약자전화번호2*/
        String cntrLocalTno3, /* 계약자전화번호3*/
        String cntrCralTno1, /* 계약자휴대전화번호1*/
        String cntrCralTno2, /* 계약자휴대전화번호2*/
        String cntrCralTno3, /* 계약자휴대전화번호3*/
        String rcgvpLocalTno1, /* 설치자전화번호1*/
        String rcgvpLocalTno2, /* 설치자전화번호2*/
        String rcgvpLocalTno3, /* 설치자전화번호3*/
        String rcgvpCralTno1, /* 설치자휴대전화번호1*/
        String rcgvpCralTno2, /* 설치자휴대전화번호2*/
        String rcgvpCralTno3, /* 설치자휴대전화번호3*/
        String dpClDt, /* 입금일자*/
        String dpTpCdNm, /* 입금유형*/
        String dpAmt, /* 입금액*/
        String sellOgTpCd, /* 판매조직유형코드*/
        String ogCd, /* 조직코드*/
        String ogId, /* 조직id*/
        String ogNm, /* 조직명*/
        String prtnrKnm, /* 성명*/
        String sellPrtnrNo, /* 판매파트너번호*/
        String cltnDt, /* 업무해약일*/
        String prtnrLocalTno1, /* 판매자전화번호1*/
        String prtnrLocalTno2, /* 판매자전화번호2*/
        String prtnrLocalTno3, /* 판매자전화번호3*/
        String prtnrCralTno1, /* 판매자휴대전화번호1*/
        String prtnrCralTno2, /* 판매자휴대전화번호2*/
        String prtnrCralTno3, /* 판매자휴대전화번호3*/
        String hooPrtnrNm, /* 성명*/
        String hooPrtnrNo, /* 번호*/
        String hooPrtnrCltnDt, /* 지점장해약일*/
        String ogLocalTno1, /* 지점전화번호1*/
        String ogLocalTno2, /* 지점전화번호2*/
        String ogLocalTno3, /* 지점전화번호3*/
        String hooPrtnrCralTno1, /* 지점장휴대전화번호1*/
        String hooPrtnrCralTno2, /* 지점장휴대전화번호2*/
        String hooPrtnrCralTno3, /* 지점장휴대전화번호3*/
        String pdHclsfId, /* 상품대분류id*/
        String pdMclsfId, /* 상품중분류id*/
        String basePdCd, /* 기준상품코드*/
        String dgr1LevlOgCd, /* 1차레벨조직코드*/
        String dgr2LevlOgCd, /* 2차레벨조직코드*/
        String dgr3LevlOgCd, /* 3차레벨조직코드*/
        String bzrno /* 사업자등록번호*/
    ) {
        public SearchRes {
            cntrLocalTno2 = StringUtils.isNotEmpty(cntrLocalTno2) ? DbEncUtil.dec(cntrLocalTno2) : cntrLocalTno2;
            cntrCralTno2 = StringUtils.isNotEmpty(cntrCralTno2) ? DbEncUtil.dec(cntrCralTno2) : cntrCralTno2;
            rcgvpLocalTno2 = StringUtils.isNotEmpty(rcgvpLocalTno2) ? DbEncUtil.dec(rcgvpLocalTno2) : rcgvpLocalTno2;
            rcgvpCralTno2 = StringUtils.isNotEmpty(rcgvpCralTno2) ? DbEncUtil.dec(rcgvpCralTno2) : rcgvpCralTno2;
            prtnrLocalTno2 = StringUtils.isNotEmpty(prtnrLocalTno2) ? DbEncUtil.dec(prtnrLocalTno2) : prtnrLocalTno2;
            prtnrCralTno2 = StringUtils.isNotEmpty(prtnrCralTno2) ? DbEncUtil.dec(prtnrCralTno2) : prtnrCralTno2;
            ogLocalTno2 = StringUtils.isNotEmpty(ogLocalTno2) ? DbEncUtil.dec(ogLocalTno2) : ogLocalTno2;
            hooPrtnrCralTno2 = StringUtils.isNotEmpty(hooPrtnrCralTno2) ? DbEncUtil.dec(hooPrtnrCralTno2)
                : hooPrtnrCralTno2;
        }
    }

    @ApiModel(value = "WdccPrepaymentExpirationDto-SearchCharacterFwUldReq")
    public record SearchCharacterFwUldReq(
        String slClYm, /*관리년월*/
        String pdHclsfId, /*상품대분류*/
        String pdMclsfId, /*상품중분류*/
        String pdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String dpStDt, /*입금시작월*/
        String dpEdDt, /*입금종료월*/
        String ogcd1, /*총괄단*/
        String ogcd2, /*지역단*/
        String ogcd3, /*지점*/
        String bzrNo, /*사업자등록번호*/
        String dpYn, /*입금여부*/
        String canYn, /*취소여부*/
        String rentalStn, /*렌탈회차_from*/
        String rentalEtn, /*렌탈회차_to*/
        String upYn, /*조회구분*/
        String upNo /*업무담당 사번*/
    ) {}

    @ApiModel(value = "WdccPrepaymentExpirationDto-SearchCharacterFwUldRes")
    public record SearchCharacterFwUldRes(
        String cntrCralTno1, /*계약자휴대전화번호1*/
        String cntrCralTno2, /*계약자휴대전화번호2*/
        String cntrCralTno3, /*계약자휴대전화번호3*/
        String cstKnm, /*고객명*/
        String cstNo, /*고객번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약번호*/
        String cntrDtlNo, /*계약상세번호*/
        String cntrInfo, /*상품정보*/
        String prmEndYm, /*선납종료년월*/
        String mmpmYm, /*선납종료익월년월*/
        String prmReAplcYn, /*선납재신청*/
        String orgPrmReAplcYn, /*선납재신청*/
        String prmEndMm, /* 선납종료월 */
        String pdCd, /*상품코드*/
        String pdNm, /* 상품명 */
        String cnt, /* 건수 */
        String currMm, /* 당월 */
        String postYy, /* 익월년도 */
        String postMm /* 익월 */
    ) {
        public SearchCharacterFwUldRes {
            cntrCralTno2 = StringUtils.isNotEmpty(cntrCralTno2) ? DbEncUtil.dec(cntrCralTno2) : cntrCralTno2;
        }
    }

    @ApiModel(value = "WdccPrepaymentExpirationDto-SearchCharacterFwIzReq")
    public record SearchCharacterFwIzReq(
        String sndgDt, /*발송일*/
        String cellNo, /*전화번호*/
        String scsYn /*성공여부*/
    ) {}

    @ApiModel(value = "WdccPrepaymentExpirationDto-SearchCharacterFwIzRes")
    public record SearchCharacterFwIzRes(
        String bndMsgRelyId, /*채권메시지중계id(삭제시 key)*/
        String dateClientReq, /*예약발송일시*/
        String cstNo, /*고객번호*/
        String cstNm, /*고객명*/
        String recipientNum, /*전화번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*일련번호*/
        String bndMsgTpVal1, /*templatecode(삭제시 key)*/
        String bndMsgTpVal2, /*선납만료년월(삭제시 key)*/
        String bndMsgTpVal3, /*공란(삭제시 key)*/
        String bndMsgTpVal4, /*상품정보*/
        String msgStatus, /*메시지 상태(1-전송대기, 2-결과대기, 3-완료)*/
        String sucYn, /*성공여부*/
        String fnlMdfcDtm, /*입력일시*/
        String usrNm, /*입력자명*/
        String epno, /*사번*/
        String fwBizNm /*발송업무명*/
    ) {}

    @ApiModel("WbndRentalCbMgtDelinquentHistoryDto-SendReq")
    public record SendReq(
        String cntrCralTno1, /*계약자휴대전화번호1*/
        String cntrCralTno2, /*계약자휴대전화번호2*/
        String cntrCralTno3, /*계약자휴대전화번호3*/
        String cstKnm, /*고객명*/
        String cstNo, /*고객번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrDtlNo, /*계약상세번호*/
        String cntrInfo, /*상품정보*/
        String prmEndYm, /*선납종료년월*/
        String mmpmYm, /*선납종료익월년월*/
        String prmEndMm, /* 선납종료월 */
        String pdCd, /*상품코드*/
        String pdNm, /* 상품명 */
        String cnt, /* 건수 */
        String currMm, /* 당월 */
        String postYy, /* 익월년도 */
        String postMm, /* 익월 */
        String fwbooDate, /*발송예약일*/
        String fwbooTime/*발송예약시간*/
    ) {}

}
