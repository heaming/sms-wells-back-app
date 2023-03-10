package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WctaWellsVlCntrEynInqrDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String copnDvCd; /*법인격구분코드*/
    private int istmMcn; /*할부개월수*/
    private int frisuBfsvcPtrmN; /*무상BS기간수*/
    private String istDt; /*설치일자*/
    private String bryyMmdd; /*생년월일*/
    private String sexDvCd; /*성별구분코드*/
    private String ogId; /*조직ID*/
    private String cntrCnfmDtm; /*계약확정일시*/
    private String cntrCanDtm; /*계약취소일시*/
    private String slDt; /*매출일자*/
    private String canDt; /*취소일자*/
    private String cntrPdEnddt; /*탈퇴일자*/
}
