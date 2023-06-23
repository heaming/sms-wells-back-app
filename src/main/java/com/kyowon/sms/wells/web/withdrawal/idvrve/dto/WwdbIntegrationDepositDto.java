package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

public class WwdbIntegrationDepositDto {
    public record SearchReq(
        String rveCd, // 수납코드
        String dpStartDtm, // 입금일시
        String dpEndDtm, // 입금일시
        String dpTpCd, // 입금유형
        String prtnrNo, // 파트너번호
        String cardAprno, // 승인번호
        String acnoEncr, /*계좌번호암호화*/
        String crcdnoEncr, /*신용카드번호암호화 - 카드번호*/
        String itgDpNo /*통합입금번호*/
    ) {
        public SearchReq {
            if (!StringUtil.isEmpty(acnoEncr)) {
                acnoEncr = DbEncUtil.enc(acnoEncr); // 계좌번호 복호화
            }
            if (!StringUtil.isEmpty(crcdnoEncr)) {
                crcdnoEncr = DbEncUtil.enc(crcdnoEncr); // 카드번호 복호화
            }
        }
    }

    public record SearchRes(
        String itgDpNo, /*통합입금번호*/
        String rveCd, /*수납코드*/
        String rveNm, //수납명
        String prtnrClsfCd, /*판매자구분*/
        String prtnrNo, /*파트너번호*/
        String dpTpCd, /*입금유형코드*/
        String dpDtm, /*입금일시*/
        String dprNm, /*입금자명*/
        String dpAmt, /*입금금액*/
        String dpBlam, /*입금잔액*/
        String pextBnkCd, /*은행코드*/
        String bankNm, /*은행명*/
        String acnoEncr, /*계좌번호암호화*/
        String cardKndCd, /* 카드구분 */
        String crcdnoEncr, /*신용카드번호암호화 - 카드번호*/
        String cardNm, /*카드명*/
        String aprDtm, /*승인일시*/
        String cardAprno, /*카드승인번호*/
        String dpCprcnfNo, /* 대사번호 */
        String dpCprcnfProcsAmt, /* 대사금액 */
        String dpCprcnfDtm, /*입금대사일자*/
        String dpCprcnfAmt, /*기대사금액(입금대사금액)*/
        String pchsCdcoCd, /*매입카드사코드*/
        String istmMcn, /* 할부개월 */
        String alncmpDvCd /* 제휴가구분코드*/
    ) {
        public SearchRes {
            if (!StringUtil.isEmpty(acnoEncr)) {
                acnoEncr = DbEncUtil.dec(acnoEncr); // 계좌번호 복호화
            }
            if (!StringUtil.isEmpty(crcdnoEncr)) {
                crcdnoEncr = DbEncUtil.dec(crcdnoEncr); // 카드번호 복호화
            }
        }
    }
}
