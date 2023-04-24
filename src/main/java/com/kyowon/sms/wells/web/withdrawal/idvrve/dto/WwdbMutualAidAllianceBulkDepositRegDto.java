package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

public class WwdbMutualAidAllianceBulkDepositRegDto {

    public record SearchReq(
        String lifSpptYm, /*라이프지원년월*/
        String lifAlncDvCd /*라이프제휴구분코드*/
    ) {

    }
    public record SearchRes(
        String lifSpptYm, /*라이프지원년월*/
        String lifCntrNo, /* 라이프계약번호*/
        String lifCntrSn, /*라이프일련번호*/
        String amt, /*지원금액*/
        String lifRepAmt, /*라이프환수금액*/
        String lifSpptAggAmt, /*라이프지원누계금액*/
        String welsCntrNo, /*웰스계약번호*/
        String welsCntrSn, /*웰스계약일련번호*/
        String lifAlncDvCd, /*라이프제휴구분코드*/
        String fstRgstDtm, /*입력일자*/
        String fstRgstUsrId, /*입력담당자id*/
        String fstRgstUsrNm, /*입력담당자id*/
        long sumAmt /*합계*/
    ) {

    }

    public record SearchSumRes(
        String countLif, /*대상건수*/
        String amtSum /*대상금액*/
    ) {

    }

    public record SearchSumReq(
        String lifSpptYm, /*라이프지원년월*/
        String lifAlncDvCd /*라이프제휴구분코드*/
    ) {

    }

    public record SaveReq(
        String lifSpptYm, /*라이프지원년월*/
        String lifAlncDvCd, /*라이프제휴구분코드*/
        String perfDt, /*실적일자*/
        String rveDt, /*수납일자*/
        String rveCd, /*수납코드*/
        String itgDpNo /*통합입금번호*/

    ) {

    }

    public record SaveUploadReq(
        String lifSpptYm, /* 지원년월 */
        String welsCntrNo, /*웰스계약번호*/
        String welsCntrSn, /*웰스계약일련번호*/
        String lifAlncPdCd, /*상품*/
        String lifAlncPdNm, /*상품명*/
        String lifSpptAmt, /*지원금액*/
        String lifCntrNo, /*상조계약번호*/
        String lifCntrSn, /*상조계약번호*/
        String lifAlncDvCd, /*제휴코드*/
        String lifRepAmt, /*라이프환수금액*/
        String lifSpptAggAmt, /*라이프지원누계금액*/
        String amt /*합계*/
    ) {

    }

    public record SearchDepositRes(
        String itgDpNo, /*통합입금번호*/
        String rveCd, /*수납코드*/
        String prtnrClsfCd, /*판매자구분*/
        String prtnrNo, /*파트너번호*/
        String dpTpCd, /*입금유형코드*/
        String dpDtm, /*입금일시*/
        String dprNm, /*입금자명*/
        String dpAmt, /*입금금액*/
        long dpBlam, /*입금잔액*/
        String pextBnkCd, /*은행코드*/
        String bankNm, /*은행명*/
        String acnoEncr, /*계좌번호암호화*/
        /* 카드구분 */
        String crcdnoEncr, /*신용카드번호암호화 - 카드번호*/
        String cardNm, /*카드명*/
        String aprDtm, /*승인일시*/
        String cardAprno, /*카드승인번호*/
        /* 대사번호 */
        /* 대사금액 */
        /*입금대사일자*/
        String dpCprcnfAmt, /*기대사금액(입금대사금액)*/
        String pchsCdcoCd, /*매입카드사코드*/
        String istmMcn, /* 할부개월 */
        String alncmpDvCd /* 제휴가구분코드*/
    ) {

    }

}
