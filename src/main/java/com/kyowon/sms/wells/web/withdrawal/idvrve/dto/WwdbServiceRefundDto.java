package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbServiceRefundDto {

    @ApiModel(value = "WwdbServiceRefundDto-SearchServiceRefundReq")
    public record SearchServiceRefundReq(
        // MAIN에서 어떻게 파라미터 던저줄지 몰라서 둘다 만듬
        //        String cntrNo, // 계약번호
        //        String cntrSn, // 계약일련번호
        String cntrNoSn // 계약상세번호 
    ) {}

    @ApiModel(value = "WwdbServiceRefundDto-SearchServiceRefundRes")
    public record SearchServiceRefundRes(
        String cstNo, // 고객번호
        String cstKnm, // 고객성명
        String cralLocaraTno, // 휴대전화번호앞자리
        String mexnoEncr, // 휴대전화번호중간자리
        String cralIdvTno, // 휴대전화번호끝자리
        String mpno, // 휴대전화번호
        String tno, // 전화번호
        String adrId, // 설치주소
        String pdNm, // 계약상품
        String sellTpCd, // 유형용도
        String cntrCnfmDtm, // 계약일자 
        String istDt, // 설치일자
        String frisuAsPtrmN, // 무상A/S 개월수
        String frisuBfsvcPtrmN, // 무상B/S 개월수 
        String tmp1, // 무상A/S일자
        String tmp2, // 무상B/S일자

        String rfndRqdt, // 환불일자
        String rfndDsbDt, // 지급일
        String dpAmt, // 결제금액
        String rfndSlpno, // 전표
        String rfndDdtnAmt, // 공제금액
        String rfndAkAmt, // 실지급액
        String rfndDsbDvCd, // 지급구분
        String cardRfndFnitCd, // 카드구분
        String cardRfndCrcdonrNm, // 카드주명
        String cardRfndFee, // 수수료액
        String cardRfndCrcdnoEncr, // 카드번호
        String cardRfndCrdcdAprno, // 승인번호
        String tmp3, // 유효기간(년월)(RRRD.환불카드유효기간필드추가필요)
        String cardRfndCrdcdIstmMcn, // 할부(개월)
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String rfndRsonCn // 환불사유

    ) {
        public SearchServiceRefundRes {
            if (!StringUtil.isEmpty(mexnoEncr)) { // 전화번호 중간자리 복호화
                mexnoEncr = DbEncUtil.dec(mexnoEncr);
            }
            if (!StringUtil.isEmpty(cralLocaraTno) || !StringUtil.isEmpty(mexnoEncr)
                || !StringUtil.isEmpty(cralIdvTno)) {
                mpno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno; // 휴대전화번호
                tno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno; // 전화번호
            }
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 복호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.dec(cardRfndCrcdnoEncr); // 카드번호 복호화
            }
        }
    }

    @ApiModel("WwdbServiceRefundDto-SearchCardRes")
    public record SearchCardRes(
        String fnitNm, // 금융기관명
        String fnitCd // 금융기관코드

    ) {}
    @ApiModel("WwdbServiceRefundDto-SearchBankRes")
    public record SearchBankRes(
        String fnitNm, // 금융기관명
        String fnitCd // 금융기관코드

    ) {}
    @ApiModel("WwdbServiceRefundDto-SaveReq")
    public record SaveReq(
        // String cntrNo, // 계약번호
        // String cntrSn, // 계약일련번호
        String cntrNoSn, // 계약상세번호 
        String rfndDdtnAmt, // 공제금액
        String rfndAkAmt, // 실지급액
        String rfndDsbDvCd, // 지급구분
        String cardRfndFnitCd, // 카드구분
        String cardRfndCrcdonrNm, // 카드주명
        String cardRfndFee, // 수수료액
        String cardRfndCrcdnoEncr, // 카드번호
        String cardRfndCrdcdAprno, // 승인번호
        // String tmp3, // 유효기간(년월)(RRRD.환불카드유효기간필드추가필요)
        String cardRfndCrdcdIstmMcn, // 할부(개월)
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String rfndRsonCn // 환불사유

    ) {
        public SaveReq {
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.enc(cardRfndCrcdnoEncr); // 카드번호 암호화
            }
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.enc(cshRfndAcnoEncr); // 계좌번호 암호화
            }
        }
    }
}
