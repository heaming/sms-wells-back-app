package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * K-W-SV-U-0120M01 AS자재 출고현황
 * </pre>
 *
 * @author jungheejin
 * @since 2023.10.30
 */
@Getter
@Setter
public class WsnaAsMaterialOutOfStoragePsDvo {

    String sapMatCd;

    String cntrNo;

    String cntrSn;

    String newAdrZip;

    String rnadr; //주소

    String rdadr; //주소 상세

    String locaraTno; //지역전화번호 (전화번호)

    @DBDecField
    String exnoEncr; //전화국번호암호화(전화번호)

    String idvTno; //개별전화번호(전화번호)
}
