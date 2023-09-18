package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaPcsvSppBzsIvcExcelUploadDvo {

    String cntrNo; // 계약번호

    String cntrSn; // 계약일련번호

    String sppIvcNo; // 송장번호

    String sppBzsPdId; // SR번호

    String pcsvCompDv; // 택배사구분

    String reqDv; // 구분
}
