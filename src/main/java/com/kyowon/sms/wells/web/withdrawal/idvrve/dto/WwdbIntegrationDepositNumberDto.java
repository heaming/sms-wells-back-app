package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

/**
 * <pre>
 * 통합입금번호 조회 DTO
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-04-03
 */
public class WwdbIntegrationDepositNumberDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 통합입금번호 목록 조회 Request Dto
    public record SearchReq(
        String rveCd, /*수납코드*/
        String dpStartDtm, // 임급일시-시작
        String dpEndDtm, // 임급일시-종
        String dpTpCd, // 입금유형
        String acnoEncr, // 계좌번호
        String sellPrtnrNo, // 판매자번호
        String crcdnoEncr, // 카드번호
        String crdcdAprno // 승인번호
    ) {
        public SearchReq {
            acnoEncr = StringUtils.isNotEmpty(acnoEncr) ? DbEncUtil.enc(acnoEncr) : acnoEncr; // 계좌번호
            crcdnoEncr = StringUtils.isNotEmpty(crcdnoEncr) ? DbEncUtil.enc(crcdnoEncr) : crcdnoEncr; // 카드번호
        }
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 통합입금번호 목록 조회 Result Dto
    public record SearchRes(
        String itgDpNo, /*통합입금번호*/
        String rveCd, /*수납코드*/
        String sellTpCd, /*판매유형*/
        String sellPrtnrNo, /*판매자번호*/
        String dpTpCd, /*입금유형*/
        String dpDtm, /*입금일자*/
        String dprNm, /*입금자명*/
        String dpAmt, /*입금금액*/
        String dpBlam, /*입금잔액*/
        String bankCd, /*은행코드*/
        String bankNm, /*은행명*/
        String acnoEncr, /*계좌번호*/
        String cardKndCd, /*카드구분*/
        String crcdnoEncr, /*카드번호*/
        String cardNm, /*은행명*/
        String crdcdAprDtm, /*승인일자*/
        String crdcdAprno, /*승인번호*/
        String dpCprcnfNo, /*대사번호*/
        String dpCprcnfAmt, /*대사금액*/
        String dpCprcnfDtm, /*입금대사일자*/
        String dpGicprcnfAmt, /*기대사금액*/
        String pchsCdcoCd, /*매입카드사*/
        String crdcdIstmMcn, /*할부개월*/
        String vncoDvCd /*제휴사구분코드*/
    ) {
        public SearchRes {
            acnoEncr = StringUtils.isEmpty(acnoEncr) ? acnoEncr : DbEncUtil.dec(acnoEncr); // 계좌번호
            crcdnoEncr = StringUtils.isEmpty(crcdnoEncr) ? crcdnoEncr : DbEncUtil.dec(crcdnoEncr); // 카드번호
        }
    }
}
