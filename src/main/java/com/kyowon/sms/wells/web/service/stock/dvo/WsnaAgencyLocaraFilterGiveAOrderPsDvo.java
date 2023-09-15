package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * K-W-SV-U-0148M01 대리점 지역별 필터발주 현황 조회조건 dvo
 * </pre>
 *
 * @author jungheejin
 * @since 2023-09-14
 */

@Getter
@Setter
public class WsnaAgencyLocaraFilterGiveAOrderPsDvo {

    // 기준년월
    String yymm;

    String cstKnm;

    String cntrNo;

    String cntrSn;

    String rcgvpKnm;

    String basePdCd;

    String pdctPcCd;

    String sapMatCd;

    String pdNm;

    String cntrAdrpcId;

    String adrpcTpCd;

    String adrId;

    String adrDvCd;

    String newAdrZip;

    String rnadr;

    String rdadr;

    String cralLocaraTno;

    @DBDecField
    String mexnoEncr;

    String cralIdvTno;

    String locaraTno;

    @DBDecField
    String exnoEncr;

    String idvTno;

    String sppZip;

    String sppBasAdr;

    String sppDtlAdr;

    String partPdCd;

    String filtNm;

    String partUseQty;

    String locaraDvNm;

    String adrpcTpCdRnk;

    String tn; // 발주 회차

    String sumPartUseQty; //합계수량
}
