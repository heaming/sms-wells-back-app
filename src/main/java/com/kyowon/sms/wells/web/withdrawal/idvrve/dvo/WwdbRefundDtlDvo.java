package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 환불 요청 상세 DVO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-10-31
 */
@Getter
@Setter
public class WwdbRefundDtlDvo {


    private String aftRfndAkNo; /* 채번용 키값 */

    private String cstNo; // 고객번호
    private String rfndDvCd; // 환불구분. 01.전체 환불, 02.부분 환불
    private String rfndDsbDvCd; // 환불구분. 환불지급구분코드 01.현금환불, 02.카드환불, 03.전금
    private String cshRfndDvCd; // 현금환불 구분. 현금환불구분코드 01.선환불, 02.일반환불, 03.카드현금
    private String rfndCshAkAmt; //  환불요청금액 (현금)
    private String rfndCardAkAmt; //  환불요청금액(카드)
    private String crdcdFeeAmt; // 카드수수료(전액)
    private String crdcdFer; // 수수료율 ( 저장 )
    private String rfndBltfAkAmt; // 전금금액
    private String rfndRsonCd; // 환불사유
    private String rfndRsonCn; // 환불내용
    private String dtaDlYn; // 데이터삭제여부

    private String rfndRcpNo; /*환불접수번호*/
    private String rfndRcpPhCd; /*환불접수경로코드*/
    private String rfndRveDt; /*환불수납일자*/
    private String rfndPerfDt; /*환불실적일자*/
    private String rfndDsbDt; /*환불지급일자*/
    private String rfndProcsCn; /*환불처리내용*/
    private String rfndDsbDuedt; /*환불예정일자*/
    private String kwGrpCoCd; /*법인교원*/
    private String rfndAkNo;   /*환불요청번호*/
    private String cntrNo;      /*계약번호*/
    private String cntrSn;     /*계약일련번호*/
    private String rveNo;   /*수납번호*/
    private String rveSn;  /*수납일련번호*/
}
