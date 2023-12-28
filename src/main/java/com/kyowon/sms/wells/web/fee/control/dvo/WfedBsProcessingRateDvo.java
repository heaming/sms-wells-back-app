package com.kyowon.sms.wells.web.fee.control.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WfedBsProcessingRateDvo {
    private String baseYm; /*기준년월*/
    private String ogTpCd; /*조직유형코드*/
    private String prtnrNo; /*파트너번호*/
    private double sv01999909; /*처리율*/
    private double sv01999910; /*수정비율*/
    private double perfVal; /*실적값*/
}
