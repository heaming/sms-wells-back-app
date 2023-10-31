package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * K-W-SV-U-0058M01 렌탈/멤버십 취소 현황
 * </pre>
 *
 * @author jungheejin
 * @since 2023.10.30
 */
@Setter
@Getter
public class WsnbRentalMembershipCancelPsDvo {

    String sapMatCd;

    String cntrNo;

    String cntrSn;

    String rnadr; //주소

    String rdadr; //주소 상세

    String locaraTno; //지역전화번호 (전화번호)

    @DBDecField
    String exnoEncr; //전화국번호암호화(전화번호)

    String idvTno; //개별전화번호(전화번호)

    String newAdrZip;

    String sellOgId;

    String sellOgCd;

    String sellOgNm;

    String ogId;

    String ogCd;

    String ogNm;

    String rcgvpKnm;
}
