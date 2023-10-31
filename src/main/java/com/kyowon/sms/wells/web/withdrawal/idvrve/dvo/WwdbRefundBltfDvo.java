package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 환불 전금 상세 DVO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-10-31
 */
@Getter
@Setter
public class WwdbRefundBltfDvo {
    private String kwGrpCoCd; // 법인 구분
    private String rfndAkNo; // 환불번호
    private String aftRfndAkNo; /* 채번용 키값 */
    private String cntrNo; // 계약번호
    private String cntrSn; // 계약일련번호
    private String rveNo; // 수납상세.수납번호
    private String rveSn; // 수납일련번호
    private String bltfOjCntrNo; // 전금계약번호
    private String bltfOjCntrSn; // 전금일련번호
    private String cstNo; // 고객번호
    private String rfndBltfAkAmt; // 전금금액
    private String bltfRfndDvCd; // 전금구분
    private String bltfRfndTpCd; // 전금환불유형코드
    private String bltfRfndMbDvCd; // 회원구분
    private String rfndEvidMtrFileId; // 증빙자료 파일첨부
    private String dtaDlYn; // 데이터 삭제여부
}
