package com.kyowon.sms.wells.web.closing.standard.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdcyCloseHourBulkRegDvo {

    private String kwGrpCoCd;
    private String clBizTpCd;
    private String clPsicNo;// 마감담당자번호
    private String prtnrNo;

    private String clDt; // 마감일자

    private String strtdt; // 시작일자
    private String strtHh; // 시작시간
    private String enddt; // 종료일자
    private String endHh; // 종료시간
    private String perfDt; // 실적일자

    private String dtaDlYn;
}
