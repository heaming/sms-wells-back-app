package com.kyowon.sms.wells.web.fee.aggregate.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeaOrganizationNetOrderDvo {

    private String perfYm; //실적년월
    private String ogTpCd; //조직유형코드
    private String feeTcntDvCd; //수수료차수구분코드
    private String perfAgrgCrtDvCd; //실적집계생성구분코드
    private String dv; //구분

}
