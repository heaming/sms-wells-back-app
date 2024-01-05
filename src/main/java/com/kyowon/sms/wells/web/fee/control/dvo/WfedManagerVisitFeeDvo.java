package com.kyowon.sms.wells.web.fee.control.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WfedManagerVisitFeeDvo {
    private String baseYm; /*기준년월*/
    private String inqrDv; /*조회구분*/
    private String ogLevlDvCd1; /*조직레벨구분코드1*/
    private String ogLevlDvCd2; /*조직레벨구분코드2*/
    private String ogLevlDvCd3; /*조직레벨구분코드3*/
    private String prtnrNo; /*파트너번호*/
    private String tableName; /*테이블명*/
}
