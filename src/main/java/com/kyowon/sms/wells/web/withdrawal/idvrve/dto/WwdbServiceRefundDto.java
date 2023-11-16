package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WwdbServiceRefundDto {
    /**
     * 서비스 환불 저장 정보
     * @param serviceInfo
     * @param serviceRefundInfo
     */
    @ApiModel("WwdbServiceRefundDto-SaveReq")
    public record SaveReq(
        saveServiceReq serviceInfo, // 서비스 정보
        saveServiceRefundReq serviceRefundInfo // 서비스환불정보
    ) {}

    /**
     * 환불 서비스 정보
     * @param csBilNo
     * @param cstSvAsnNo
     * @param cntrNo
     * @param cntrSn
     */
    public record saveServiceReq(
        String csBilNo, /* 비용청구번호 */
        String cstSvAsnNo, /* 고객서비스배정번호 */
        String dpSn, /*입금일련번호*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cstNo, /* 고객번호 */
        String itgDpNo, /*통합입금번호*/
        String dpDtm, /*결제일*/
        String cardAprno /*승인번호*/

    ) {}

    /**
     * 환불 정보
     * @param rfndRqdt
     * @param rfndDsbDt
     * @param rfndDsbDvCd
     * @param rfndDdtnAmt
     * @param rfndAkAmt
     * @param cardRfndCrcdnoEncr
     * @param cardRfndFnitCd
     * @param crdcdFeeSumAmt
     * @param cshRfndFnitCd
     * @param cshRfndAcnoEncr
     * @param rfndRsonCd
     * @param rfndRsonCn
     * @param cstNm
     */
    public record saveServiceRefundReq(
        String cshRfndDvCd, /*현금환불구분코드*/
        String bilAmt, /*청구금액*/
        String rfndRqdt, /*환불일자*/
        String rfndDsbDt, /*지급일자*/
        String rfndDsbDvCd, /*지급구분*/
        String rfndDdtnAmt, /*공제금액*/
        String rfndAkAmt, /*실지급액*/
        String rfndCshAkSumAmt, /*현금환불금액*/
        String rfndCardAkSumAmt, /*카드환불금액*/
        String rfndBltfAkSumAmt, /*전금환불금액*/
        String cardRfndCrcdnoEncr, /*카드번호*/
        String cardRfndFnitCd, /*카드구분*/
        String crdcdFeeSumAmt, /*수수료액*/
        String crdcdFeeFer, /*수수료율*/
        String cshRfndFnitCd, /*지급은행*/
        String cshRfndAcnoEncr, /*계좌번호*/
        String rfndRsonCd, /*환불사유코드*/
        String rfndRsonCn, /*환불사유내영(기타일경우 입력)*/
        String cstNm, /*예금주*/
        String crdcdExpdtYm, /*crdcdExpdtYm*/
        String istmMcn, /*할부기간*/
        String crcdonrNm /*카드주명*/
    ) {
        public saveServiceRefundReq {
            if (!StringUtil.isEmpty(cshRfndAcnoEncr)) {
                cshRfndAcnoEncr = DbEncUtil.enc(cshRfndAcnoEncr); // 계좌번호 암호화
            }
            if (!StringUtil.isEmpty(cardRfndCrcdnoEncr)) {
                cardRfndCrcdnoEncr = DbEncUtil.enc(cardRfndCrcdnoEncr); // 카드번호 암호화
            }
        }
    }
}
