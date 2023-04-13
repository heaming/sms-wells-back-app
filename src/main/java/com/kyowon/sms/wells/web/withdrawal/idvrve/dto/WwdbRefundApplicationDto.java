package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

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
        String cntrNoSn, // 계약상세번호
        String cstNo, // 고객번호
        String bzrno // 사업자등록번호
    ) {

    }

    @ApiModel(value = "WwdbRefundApplicationDto-SearchRefundApplicationRes")
    public record SearchRefundApplicationRes(
        String rfndStatCd, // 환불상태
        //        String    상세 버튼, // 환불상세
        String fnlMdfcDtm, // 접수일자
        String cntrwTpCd, // 계약유형. 그리드 정의 없음. 리스, 렌탈, 멤버십, 정기배송
        String cntrNoSn, // 계약상세번호
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
        String rfndEvidMtrFileId // 첨부파일

    ) {}
}
