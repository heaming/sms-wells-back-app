package com.kyowon.sms.wells.web.closing.clearing.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * EDU 대상이 되는 매출확정 데이터 조회
 */
@Getter
@Setter
public class WdchEduSlSalesDataDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String baseYm; /*기준년월*/
    private String flagYm; /*기준년월 구분*/
}
