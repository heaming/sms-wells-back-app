package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbRefundApplicationDto {

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationReq")
    public record SearchRefundApplicationReq(
        String startDay, // 접수일자 시작일
        String endDay, // 접수일자 종료일
        @NotBlank
        String rfndStatCd, // 환불상태
        @NotBlank
        String rveDvCd, // 입금유형
        String rfndDsbDvCdCshBltf, // 처리구분
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cstNo, // 고객번호
        String bzrno // 사업자등록번호
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationRes")
    public record SearchRefundApplicationRes(
        String rfndStatCd, // 환불상태
        //        String    상세 버튼, // 환불상세
        String fnlMdfcDtm, // 접수일자
        String cntrwTpCd, // 계약유형. 그리드 정의 없음. 리스, 렌탈, 멤버십, 정기배송
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cstKnm, // 계약자
        String cntrCnfmDtm, // 계약일자
        String rveDvCd, // 입금유형
        String rveDt, // 매출일자
        String cntrCanDtm, // 취소일자
        String pdNm, // 상품명
        String rfndAkAmt, // 환불요청금액(원)
        String fnm, // 신청자
        String fnlMdfcUsrId, // 번호
        String rfndFshDt, // 승인일자
        String exRfndRsonCn, // 처리내용
        String rfndFshDtTmp, // 지급일자가 승인일자와 동일함.
        String rfndDsbDvCdCshBltf, // 처리구분 화면표시. 1.카드전금, 2.현금전금, 3.카드환불, 4.현금환불, 5.선환불, 6.카드현금
        String rfndRsonCd, // 환불사유
        String rfndRsonCn, // 환불내용
        String rfndEvidMtrFileId, // 첨부파일
        String rfndRcpNo, // 환불접수번호
        String rfndRcpDtlSn // 환불접수일련번호
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundContractDetailReq")
    public record SearchRefundContractDetailReq(
        String cntrStartDay, // 계약/취소일자 시작일
        String cntrEndDay, // 계약/취소일자 종료일
        String copnDvCd, // 고객유형
        String stlmTpCd, // 계약유형
        String cntrNo, // 계약번호 
        String cntrSn, //계약일련번호 
        String cntrCstNo, // 고객번호
        String bzrno // 사업자번호
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundContractDetailRes")
    public record SearchRefundContractDetailRes(
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String stlmTpCd, // 계약유형(일시불, 할부)
        String copnDvCd, // 고객유형
        String bzrno, // 사업자번호
        String cntrCstNo, // 고객번호
        String bzrnoCntrCstNo, // 사업자 고객번호
        String cstKnm, // 계약자명
        String cntrCstNo2, // 번호. 설계서에 고객번호와 번호가 동일함
        String cntrCnfmDtm, // 계약일자
        String rveDt, // 매출일자
        String cntrCanDtm, // 취소일자
        String pdNm, // 상품명
        String fnlAmt, // 주문금액
        String dpAmt, // 입금액
        String tmp1, // 환불가능금액. 확인필요
        String dpMesCd, // 입금수단코드 01.현금, 02.신용카드, 05.전금
        String rveNo // 수납상세.수납번호

    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundPossibleAmountReq")
    public record SearchRefundPossibleAmountReq(
        String cntrNo, // 계약번호 
        String cntrSn //계약일련번호 
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundPossibleAmountRes")
    public record SearchRefundPossibleAmountRes(
        String orderCntrAmt, // 청약금액 = 계약상세.계약금액
        String orderCrpUcAmt, // 법인미수금액 = 계약상세.법인미수금액 
        String orderMmIstmAmt, // 월할부금액_금액 = 계약상세.월할부금액 
        String orderIstmMcn, // 월할부금액_개월 = 계약상세.할부개월수 
        String orderBilNo, // 현재차월수 
        String orderDlqMcn, // 연체개월 
        String orderDlqBilAmt, // 연체금액
        String depositSubscTkAmt, // 입금금액.청약인수금액 
        String depositCrpUcAmt, // 입금금액.법인미수금액
        String depositIstmAmt, // 입금금액.할부금액 
        String depositPrpdAmt, // 입금금액.선수금액
        String depositDpAmt, // 입금금액.입금액 
        String refundSubscTkAmt, // 환불가능금액.청약인수금액 
        String refundCrpUcAmt, // 환불가능금액.법인미수금액
        String refundIstmAmt, // 환불가능금액.할부금액 
        String refundPrpdAmt, // 환불가능금액.선수금액
        String refundRfndPsbAmt // 환불가능금액.환불가능금액 
    ) {}

    @ApiModel("WwdbRefundApplicationDto-SearchCardRes")
    public record SearchCardRes(
        String fnitNm, // 금융기관명
        String fnitCd // 금융기관코드

    ) {}
    @ApiModel("WwdbRefundApplicationDto-SearchBankRes")
    public record SearchBankRes(
        String fnitNm, // 금융기관명
        String fnitCd // 금융기관코드

    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SaveRefundReq")
    public record SaveRefundReq(
        String rfndRcpNo, // 환불접수번호 
        String cntrNo, // 계약번호 
        String cntrSn,
        String cstNo, // 고객번호
        String exRfndRsonCn, // 예외환불 사유
        String rfndCshAmt, // 현금 환불금액
        String rfndCardAmt, // 카드 환불금액
        String rfndBltfAmt, // 전금 금액
        String rfndPsbResAmt, // 환불가능 잔액
        String totRfndEtAmt, // 총환불가능 잔액
        List<SaveRefundDetail> details // 추가 버튼 누르면 추가로 생성되는 부분
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-RefundDetail")
    public record SaveRefundDetail(
        String rfndRcpDtlSn, /* 환불접수상세일련번호 */
        String rveNo, // 수납상세.수납번호
        String rfndDvCd, // 환불구분. 01.전체 환불, 02.부분 환불
        String rfndDsbDvCd, // 환불구분. 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
        String cshRfndDvCd, // 현금환불 구분. 현금환불구분코드 01.선환불, 02.일반환불, 03.카드현금
        String rfndAcDvCd, // 환불계좌 구분. 환불계좌구분코드 01.기입금 계좌, 02.신규 계좌
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String cshRfndAdrsDvCd, // 수취인
        String cshRfndDlgpsNm, // 대표자 확인
        String startDay, // 승인일 시작
        String endDay, // 승인일 종료
        String rfndAkAmt, // 환불신청금액, 전금 요청금액(원)
        String rfndDsbAmt, // 실지급액 (원)
        //        String rfndAkAmt2, // 전금 요청금액(원)
        String rfndRsonCd, // 환불사유코드

        String cardRfndFnitCd, // 카드구분
        String cardRfndCrcdnoEncr, // 카드번호
        String cardRfndFer, // 카드 공제액
        String rfndRsonCn, // 기타 환불사유 입력란

        String bltfRfndDvCd, // 전금구분
        String bltfRfndMbDvCd, // 회원구분
        String bltfOjCntrSn, // 고객번호
        String bltfBfVacNoEncr, // 전금전 가상계좌
        String bltfAfVacNoEncr, // 전금후 가상계좌
        String rfndEvidMtrFileId // 증빙자료 파일첨부

    ) {
        public SaveRefundDetail {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.enc(cshRfndAcnoEncr); // 계좌번호 암호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.enc(cardRfndCrcdnoEncr); // 카드번호 암호화
            }
        }

    }

    @ApiModel(value = "WwdbRefundApplicationDto-RefundBasic")
    public record RefundBasic(
        String rfndRcpNo, // 환불접수번호 
        String cntrNo, // 계약번호 
        String cntrSn,
        String cstNo, // 고객번호
        String exRfndRsonCn, // 예외환불 사유

        String rfndCshAmt, // 현금 환불금액
        String rfndCardAmt, // 카드 환불금액
        String rfndBltfAmt, // 전금 금액
        String rfndPsbResAmt, // 환불가능 잔액
        String totRfndEtAmt, // 총환불가능 잔액

        String rfndRveDt, // 수납일자
        String rfndPerfDt, // 실적일자
        String rfndDsbDt, // 지급일자
        String rfndStatCd, // 처리구분 (반려, 승인)
        String rfndProcsCn // 처리내용
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationInfoRes")
    public record SearchRefundApplicationInfoRes(
        RefundBasic basic,
        List<RefundDetail> details // 추가 버튼 누르면 추가로 생성되는 부분
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-RefundDetail")
    public record RefundDetail(
        String rfndRcpNo, // 환불접수번호 
        String rfndRcpDtlSn, /* 환불접수상세일련번호 */
        String rveNo, // 수납상세.수납번호
        String rfndDvCd, // 환불구분. 01.전체 환불, 02.부분 환불
        String rfndDsbDvCd, // 환불구분. 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
        String cshRfndDvCd, // 현금환불 구분. 현금환불구분코드 01.선환불, 02.일반환불, 03.카드현금
        String rfndAcDvCd, // 환불계좌 구분. 환불계좌구분코드 01.기입금 계좌, 02.신규 계좌
        String cshRfndFnitCd, // 지급은행
        String cshRfndAcnoEncr, // 계좌번호
        String cshRfndAcownNm, // 예금주
        String cshRfndAdrsDvCd, // 수취인
        String cshRfndDlgpsNm, // 대표자 확인
        String startDay, // 승인일 시작
        String endDay, // 승인일 종료
        String rfndAkAmt, // 환불신청금액, 전금 요청금액(원)
        String rfndDsbAmt, // 실지급액 (원)
        //        String rfndAkAmt2, // 전금 요청금액(원)
        String rfndRsonCd, // 환불사유코드

        String cardRfndFnitCd, // 카드구분
        String cardRfndCrcdnoEncr, // 카드번호
        String cardRfndFer, // 카드 공제액
        String rfndRsonCn, // 기타 환불사유 입력란

        String bltfRfndDvCd, // 전금구분
        String bltfRfndMbDvCd, // 회원구분
        String bltfOjCntrSn, // 고객번호
        String bltfBfVacNoEncr, // 전금전 가상계좌
        String bltfAfVacNoEncr, // 전금후 가상계좌
        String rfndEvidMtrFileId // 증빙자료 파일첨부

    ) {
        public RefundDetail {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.dec(cshRfndAcnoEncr); // 계좌번호 암호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.dec(cardRfndCrcdnoEncr); // 카드번호 암호화
            }
        }

    }

    @ApiModel(value = "WwdbRefundApplicationDto-EditRefundReq")
    public record EditRefundReq(
        RefundBasic basic,
        List<RefundDetail> details // 추가 버튼 누르면 추가로 생성되는 부분
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailReq")
    public record SearchRefundApplicationDetailReq(
        String rfndStatCd, // 환불상태코드
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String rfndRcpNo, // 환불접수번호
        String rfndRcpDtlSn // 환불접수일련번호
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailPartnerRes")
    public record SearchRefundApplicationDetailPartnerRes(
        String prtnrKnm, // 신청자
        String rfndRcpPrtnrNo, // 번호
        String rfndRqdt, // 신청일시
        String rfndStatCd // 처리상태
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-Ctract")
    public record Ctract(
        String cntrNo, // 계약번호
        String sellTpCd, // 주문유형
        String cstKnm, // 고객명
        String istllKnm, // 설치자명
        String pdNm, // 상품명
        String cntrCstNo, // 고객번호
        String bryyMmdd, // 생년월일
        String dpTpCd, // 이체구분
        String mpyBsdt, // 이체일
        String cttRsCd, // 컨텍코드
        String rentalAmt, // 렌탈(개월)
        String rentalPtrm, // 개월
        String alncmpCd, // 제휴
        String rentalRgstCost, // 등록비용
        String dutyUseMcn, // 의무약정
        String istDt, // 설치일자
        String slDt, // 매출일자 (EDU 시, WELLS 테이블 다름)
        String cntrCanDtm, // 취소일자
        String cntrCnfmDtm // 완료일자

    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailPossibleReq")
    public record SearchRefundApplicationDetailPossibleReq(
        String cntrNo, // 계약번호 
        String cntrSn
    ) {}
    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailPossibleRes")
    public record SearchRefundApplicationDetailPossibleRes(
        String dpAggAmt, // 총입금 누계금액, 
        String dpAggAmt2, // 입금누계금액
        String lstmmDlqAmt, // 전월 연체금액
        String thmPrpdAmt, // 당월 선수금액
        String thmDlqAmt, // 당월 연체금액
        String thmDpAmt, // 당월 입금액
        String rfndPsbAmt // 환불가능금액

    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailDepositReq")
    public record SearchRefundApplicationDetailDepositReq(
        String cntrNo, // 계약번호 
        String cntrSn
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailDepositRes")
    public record SearchRefundApplicationDetailDepositRes(
        String rveSn, // 입금일련번호
        String dpDt, // 입금일자
        String perfDt, // 실적일자
        String rfndFshDt, // 지급일자
        String dpDvCd, // 입금구분
        String dpAmt, // 처리금액
        String dpTpCd, // 입금유형
        String cshCrd, // 카드현금구분
        String fnitNm, // 카드사, 은행
        String crcdnoEncr, // 카드번호
        String crdcdAprno, // 승인번호
        String crdcdIstmMcn, // 할부개월수
        String acnoEncr, // 계좌번호
        String dprNm // 입금자

    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailPerformanceRes")
    public record SearchRefundApplicationDetailPerformanceRes(
        String cntrNo, // 계약번호
        String slStpYn, // 매출중지
        String slClYm, // 매출년월
        String tmp1, // 리스차월
        String nomSlAmt, // 청구금액
        String borAmt, // 위약금액
        String fnnLeasePcamTam, // 리스입금액
        String prpdSlAmt, // 선수금액
        String totPcamUcAmt, // 미수금액
        String thmDlqRfndSumAmt, // 연체금액
        String dlqMcn, // 연체개월
        String btdDlqAmt, // 기초금액
        String thmOcDlqAmt, // 발생금액
        String tmp2, // 공제금액
        String thmIstmTotDpAmt, // 입금액
        String thmIstmRfndAmt, // 환불금액
        String eotPcamBlam // 기말금액

    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailPerformanceReq")
    public record SearchRefundApplicationDetailPerformanceReq(
        String cntrNo, // 계약번호 
        String cntrSn
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-App")
    public record App(
        String rfndRcpNo, // 환불접수번호
        String rfndRcpDtlSn, // 환불접수일련번호
        String rfndDsbDvCd, // 환불구분
        String rfndDsbDvCdCshBltf, // 전금구분
        String bltfRfndMbDvCd, // 전금환불회원구분코드
        String pdNm, // 상품명
        String bltfBfVacNoEncr, // 전금이전가상계좌번호암호화
        String bltfAfVacNoEncr, // 전금이후가상계좌번호암호화
        String rfndAkAmt, // 환불요청금액
        String rfndRsonCn // 전금사유

    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailReceiptRes")
    public record SearchRefundApplicationDetailReceiptRes(
        // 환불접수총액
        String rfndCshAmt, // 현금 환불금액
        String rfndCardAmt, // 카드 환불금액
        String rfndBltfAmt, // 전금금액
        String rfndPsbResAmt, // 환불가능 잔액
        String totRfndEtAmt // 총 환불 예상 금액
    ) {}

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationDetailRes")
    public record SearchRefundApplicationDetailRes(
        // 계약정보
        Ctract ctract,
        App app,
        RefundBasic basic,
        List<RefundDetail> refundDetail // 추가 버튼 누르면 추가로 생성되는 부분
    ) {}
}
