package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaItemBaseInformationSearchDvo {
    private String itmKndCd;
    private String itmPdCd;
    private String itmPdNm;
    private String wareNo;
    private String ostrWareNo;
    private String wareDvCd;
    private String wareDtlDvCd;
    // OFFSET
    private Integer offSet;
    // FETCH SIZE
    private Integer size;
}
