package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbConsumablesRefundDto {

    @ApiModel(value = "WwdbConsumablesRefundDto-SearchConsumablesRefundReq")
    public record SearchConsumablesRefundReq(
        String rveDvCd, /* 업무구분 */
        String selectDay, // 일자 종류 01.처리일자, 02.실적일자
        String startDay, /* 처리일자 */
        String endDay, /* 처리일자 */
        String cntrNo, /* 계약상세번호 계약번호 */
        String cntrSn /* 계약상세번호 계약일련번호*/
    ) {

    }

    @ApiModel(value = "WwdbConsumablesRefundDto-SearchConsumablesRefundRes")
    public record SearchConsumablesRefundRes(
        String rfndRcpNo, // 일련번호. 환불접수번호
        String rfndRcpDtlSn, // 일련번호. 환불접수일련번호
        String kwGrpCoCd, // 법인구분
        String rfndRveDt, // 수납일자
        String rfndPerfDt, // 실적일자
        String cntrNo, // 계약상세번호
        String cntrSn, // 계약상세번호
        String cstKnm, // 고객명
        String tmp1, // 상세
        String ogNm, // 부서
        String prtnrKnm, // 파트너명
        String rfndRcpPrtnrNo, // 번호
        String rfndDsbDuedt, // 예정일자
        String rfndDsbDt, // 매출일자
        String rfndFshDt, // 환불일자
        String tmp2, // 유형
        String sellAmt, // 판매금액
        String cntrAmt, // 계약금액
        String rfndCshAmt, // 처리금액 현금
        String rfndCardAmt, //처리금액 카드
        String rfndBltfAmt, // 처리금액 전금
        String rfndDsbDvCd, // 지급구분
        String cardRfndCrcdnoEncr, // 카드번호
        String cshRfndAcnoEncr, // 계좌번호
        String cardCshRfndAcnoEncr, // 카드번호/계좌번호
        String cardRfndCrdcdAprno, // 승인번호
        String cardRfndCrdcdIstmMcn, // 할부개월
        String rfndEvidMtrFileId // 자료보관

    ) {
        public SearchConsumablesRefundRes {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cardCshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardCshRfndAcnoEncr = DbEncUtil.dec(cardRfndCrcdnoEncr); // 카드번호 복호화
            }
        }
    }

    @ApiModel(value = "WwdbConsumablesRefundDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String kwGrpCoCd, // KW_GRP_CO_CD 교원그룹회사코드
        @NotBlank
        String rfndRcpNo, //  RFND_RCP_NO 환불접수번호
        @NotBlank
        String rfndRcpDtlSn // 일련번호. 환불접수일련번호
    ) {}

    @ApiModel(value = "WwdbConsumablesRefundDto-SaveReq")
    public record SaveReq(
        String kwGrpCoCd, // 교원그룹회사코드
        String rfndRcpNo, // 환불접수번호
        String cstNo, // 고객번호
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String rfndRcpPrtnrNo, // 환불접수파트너번호 화면에서 입력하는 파트너번호
        String rfndPerfDt, // 실적일자 환불실적일자

        String rveNo, // 수납번호

        String rfndFshDt, // 처리일자
        String rfndRcpDtlSn, // 환불접수상세일련번호
        String pyAmt, // 금액
        String rfndDvCd, // 환불구분
        String rfndDpKndCd, // 입금종류
        String cardRfndFnitCd, // 카드사코드 (카드선택시)
        String cardRfndCrcdnoEncr, // 카드번호 (카드선택시)
        String cardRfndCrdcdAprno, // 승인번호 (카드선택시)
        String cardRfndCrdcdIstmMcn, // 할부개월(카드 선택시)
        String cardRfndCrcdonrNm, // 카드주명(카드선택시)
        String cardRfndFee, // 카드공제
        String cardRfndFer, // 카드공제율(카드선택시)
        String rfndDsbAmt, // 실지급액
        String rfndPspDc, // 지연일수
        String rfndDsbPspInt, // 지연이자
        String rfndRntfExstYn, // 손료존재
        String rfndDsbDvCd, // 지급유형
        String rfndRsonCd, // 환불유형
        String cshRfndFnitCd, // 은행구분(현금선택시)
        String cshRfndAcnoEncr, // 계좌번호(현금선택시)
        String cshRfndAcownNm, // 예금주명(현금선택시)
        String bltfRfndTpCd, // 전금유형
        String bltfOjCntrNo, // 전금고객 전금대상계약번호
        String bltfOjCntrSn // 전금고객 전금대상계약일련번호

    ) {
        public SaveReq {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.enc(cshRfndAcnoEncr); // 계좌번호 암호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.enc(cardRfndCrcdnoEncr); // 카드번호 암호화
            }
        }
    }

    @ApiModel(value = "WwdbConsumablesRefundDto-SearchContractInfoRes")
    public record SearchContractInfoRes(
        String cstKnm, // 고객명
        String cstNo, // 고객번호
        String rveNo, // 수납번호
        String rfndRcpNo, // 환불접수번호 (생성)
        String rfndRcpDtlSn, // 환불접수상세번호
        String tmp1 // 환불가능금액(확인필요)

    ) {}

    @ApiModel("WwdbConsumablesRefundDto-SearchCardRes")
    public record SearchCardRes(
        String fnitNm, // 금융기관명
        String fnitCd // 금융기관코드

    ) {}
    @ApiModel("WwdbConsumablesRefundDto-SearchBankRes")
    public record SearchBankRes(
        String fnitNm, // 금융기관명
        String fnitCd // 금융기관코드

    ) {}

}
