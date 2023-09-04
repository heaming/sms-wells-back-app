package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0248M01 총 A/S율 현황(품질)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.01
 */
@Setter
@Getter
public class WsnbTotalAsRateQltySearchDvo {
    String baseYear;
    String taskTypeCd;
    String badDvCd;
    String pdGrpCd;
    String pdCd;

    String gbNm;
    int totalCnt;
    int col01;
    int col02;
    int col03;
    int col04;
    int col05;
    int col06;
    int col07;
    int col08;
    int col09;
    int col10;
    int col11;
    int col12;
}
