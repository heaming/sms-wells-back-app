package com.kyowon.sms.wells.web.fee.calculation.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfebHomeMasterGradeDvo {

    private String prtnrNo; /*번호*/
    private String mngtYm; /*년월*/
    private String sellPVal; /*판매*/
    private String svPVal; /*서비스*/
    private String educPVal; /*교육*/
    private String etcPVal1; /*인원모집*/
    private String etcPVal2; /*VOC*/
    private String etcPVal3; /*안전사고*/
    private String clDvCd; /*등급*/
    private String emplNm; /*성명*/

}
