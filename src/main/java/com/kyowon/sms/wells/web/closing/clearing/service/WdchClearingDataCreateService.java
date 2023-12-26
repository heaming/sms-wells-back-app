package com.kyowon.sms.wells.web.closing.clearing.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.clearing.dvo.*;
import com.kyowon.sms.wells.web.closing.clearing.mapper.WdchClearingDataCreateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdchClearingDataCreateService {

    private final WdchClearingDataCreateMapper wdchClearingDataCreateMapper;

    /**
     * 채권 반제 계산 로직
     *
     * @param dvo
     * @param basDvo
     * @return
     */
    public WdchSlBndAlrpyBasDvo calculateBondReimbursement(WdchEduSlSalesDataDvo dvo, WdchBznsAtamBasDvo basDvo) {
        List<WdchEduSlCnfmBasDvo> cnfmBass = wdchClearingDataCreateMapper
            .selectWdchEduSlCnfmBass(dvo);

        WdchEduSlCnfmBasDvo tmpSlDvo = new WdchEduSlCnfmBasDvo();

        /* 매출 마지막건 가지고 있는 용도 */
        if (CollectionUtils.isNotEmpty(cnfmBass)) {
            tmpSlDvo = cnfmBass.get(cnfmBass.size() - 1);
        }

        /*이전월 데이터 : 계약상세의 전월자 선수금(입금잔액) , 미수금(매출잔액), 입금총액, 매출총액 을 조회해서 갖고 있는다. (채권반제로직에서 사용)*/
        WdchSlBndAlrpyBasBeforeMonthDvo banjeDvo = wdchClearingDataCreateMapper
            .selectSlBndAlrpyBasBeforeMonth(dvo);
        BigDecimal dpBlam = BigDecimal.ZERO; /*선수금(입금잔액)*/
        BigDecimal dpAcuAmt = BigDecimal.ZERO; /*입금누적금액(입금총액)*/
        BigDecimal totSlAmt = BigDecimal.ZERO; /*총매출금액(매출총액)*/
        BigDecimal ucAmt = BigDecimal.ZERO; /*미수금액(매출잔액)*/

        BigDecimal spmtUcOcAmt = ObjectUtils.defaultIfNull(tmpSlDvo.getThmOcDlqAddAmt(), BigDecimal.ZERO)
            .add(ObjectUtils.defaultIfNull(tmpSlDvo.getOcBorAmt(), BigDecimal.ZERO)); // 추가미수발생금액 = 발생연체가산금액 + 발생위약금액
        BigDecimal slCanAmt = ObjectUtils.defaultIfNull(tmpSlDvo.getSlCanAmt(), BigDecimal.ZERO); // 매출취소금액
        if (!ObjectUtils.isEmpty(banjeDvo)) {
            dpBlam = ObjectUtils.defaultIfNull(banjeDvo.getDpBlam(), BigDecimal.ZERO); // 선수금(입금잔액)
            dpAcuAmt = ObjectUtils.defaultIfNull(banjeDvo.getDpAcuAmt(), BigDecimal.ZERO); // 입금누적금액(입금총액)
            totSlAmt = ObjectUtils.defaultIfNull(banjeDvo.getTotSlAmt(), BigDecimal.ZERO); // 총매출금액(매출총액)
            ucAmt = ObjectUtils.defaultIfNull(banjeDvo.getUcAmt(), BigDecimal.ZERO); // 미수금액(매출잔액)
        }

        BigDecimal depositAmount = BigDecimal.ZERO; // 입금금액
        BigDecimal atamRplcProcsAmt = BigDecimal.ZERO; // 선수금대체처리금액

        /* 영업선수금기본 조회 */
        List<WdchDepositConfirmationDvo> wdchDepositConfirmationDvoList = wdchClearingDataCreateMapper
            .selectDepositConfirmation(dvo);
        for (WdchDepositConfirmationDvo wdchDepositConfirmationDvo : wdchDepositConfirmationDvoList) {
            String rveDvCd = wdchDepositConfirmationDvo.getRveDvCd();
            String sellTpCd = wdchDepositConfirmationDvo.getSellTpCd();
            String sellTpDtlCd = wdchDepositConfirmationDvo.getSellTpDtlCd();
            BigDecimal amt = wdchDepositConfirmationDvo.getAmt();

            switch (sellTpCd) {
                case "1" -> { // 일시불
                    switch (rveDvCd) {
                        case "03", "04", "05", "97", "98" -> depositAmount = depositAmount.add(amt); // 입금금액
                        case "02", "07", "19" -> atamRplcProcsAmt = atamRplcProcsAmt.add(amt); // 선수금대체처리금액
                    }
                }

                case "2" -> { // 렌탈, 리스
                    switch (sellTpDtlCd) {
                        case "21", "23" -> { // 렌탈
                            switch (rveDvCd) {
                                case "03", "04", "05", "97", "98" -> depositAmount = depositAmount.add(amt);
                                case "02", "07", "19" -> atamRplcProcsAmt = atamRplcProcsAmt.add(amt);
                            }
                        }

                        case "22", "24", "25", "26" -> { // 리스
                            switch (rveDvCd) {
                                case "01", "03", "04", "05", "97", "98" -> depositAmount = depositAmount.add(amt);
                                case "02", "07", "19" -> atamRplcProcsAmt = atamRplcProcsAmt.add(amt);
                            }
                        }
                    }
                }

                case "3", "6" -> { // 멤버쉽, 정기배송
                    switch (rveDvCd) {
                        case "01", "03", "04", "05", "97", "98" -> depositAmount = depositAmount.add(amt);
                        case "02", "07", "19" -> atamRplcProcsAmt = atamRplcProcsAmt.add(amt);
                    }
                }

                default -> {
                    switch (rveDvCd) {
                        case "01", "03", "04", "05" -> depositAmount = depositAmount.add(amt);
                        case "02", "07", "19" -> atamRplcProcsAmt = atamRplcProcsAmt.add(amt);
                    }
                }
            }
        }

        /* 반제처리 위해 사용 */
        BigDecimal salesAmount = ObjectUtils.defaultIfNull(tmpSlDvo.getSlAmtSum(), BigDecimal.ZERO); /* 매출금액 */
        BigDecimal allRepaymentAmount; /* 반제금액 */
        BigDecimal anticipation; /* 선수금 */
        BigDecimal uncollected; /* 미수금 */
        BigDecimal anticipationConversionAmount = BigDecimal.ZERO; /* 선수금전환금액 */

        BigDecimal depositAmountSum; /* 입금합계 */
        BigDecimal salesAmountSum; /* 매출합계 */

        depositAmountSum = dpBlam.add(depositAmount); /* 입금 = 전월선수금 + 입금 */
        salesAmountSum = ucAmt.add(salesAmount); /* 매출 = 전월미수금 + 매출 */

        /* 반제금액 = min( (전월선수 + 입금금액) , (전월미수 + 매출금액) )*/
        allRepaymentAmount = depositAmountSum.compareTo(salesAmountSum) < 0
            ? depositAmountSum : salesAmountSum;

        if (("2".equals(tmpSlDvo.getSellTpCd())
            && ("21".equals(tmpSlDvo.getSellTpDtlCd())
                || "23".equals(tmpSlDvo.getSellTpDtlCd())))
            || "3".equals(tmpSlDvo.getSellTpCd())) {
            /*렌탈, 멤버십*/
            /*입금금액이 전월미수보다같거나크면 전월미수만큼만 아니면 입금금액만큼만 전월미수가 없으면 안침*/
            if (ucAmt.compareTo(BigDecimal.ZERO) == 0) {
                allRepaymentAmount = BigDecimal.ZERO;
            } else {
                if (depositAmount.compareTo(ucAmt) >= 0) {
                    allRepaymentAmount = ucAmt;
                } else {
                    allRepaymentAmount = depositAmount;
                }
            }
        }

        if (allRepaymentAmount.compareTo(BigDecimal.ZERO) < 0) { // 반제금액이 마이너스
            if (totSlAmt.compareTo(salesAmount.abs()) < 0) { // 이전달까지의 매출총액 보다 매출금액(마이너스로 조정된 마이너스금액) 의 절대값이 더 큰지 확인 -> 매출금액이 더 클 경우
                allRepaymentAmount = BigDecimal.ZERO.equals(dpAcuAmt)
                    ? BigDecimal.ZERO
                    : dpAcuAmt.multiply(new BigDecimal(-1)); // 반제금액은 이전달까지의 입금총액의 마이너스금액이 됨
            }
        }

        anticipation = (dpBlam.add(depositAmount)).subtract(allRepaymentAmount); /* 선수금(입금잔액) = (전월선수금 + 입금) - 반제금액 */
        uncollected = (ucAmt.add(salesAmount)).subtract(allRepaymentAmount); /* 미수금(매출잔액) = (전월미수금 + 매출) - 반제금액 */

        if (uncollected.compareTo(BigDecimal.ZERO) < 0) { // 미수금이 마이너스가 된경우
            anticipation = anticipation.add(uncollected.abs());
            anticipationConversionAmount = anticipationConversionAmount.add(uncollected.abs());
            uncollected = BigDecimal.ZERO;
        }

        /*매출취소금액 SL_CAN_AMT => 있으면.  해당금액만큼 미수금에서 마이너스 처리. */
        if (slCanAmt.compareTo(BigDecimal.ZERO) != 0) {
            uncollected = uncollected.subtract(slCanAmt);
        }

        WdchSlBndAlrpyBasDvo returnDvo = new WdchSlBndAlrpyBasDvo();

        returnDvo.setCntrNo(dvo.getCntrNo());
        returnDvo.setCntrSn(dvo.getCntrSn());
        returnDvo.setBaseYm(dvo.getBaseYm());
        returnDvo.setKwGrpCoCd(tmpSlDvo.getKwGrpCoCd());
        returnDvo.setSellTpCd(tmpSlDvo.getSellTpCd());
        returnDvo.setSellTpDtlCd(tmpSlDvo.getSellTpDtlCd());
        returnDvo.setSellInflwChnlDtlCd(tmpSlDvo.getSellInflwChnlDtlCd());
        returnDvo.setSapPdDvCd(tmpSlDvo.getSapPdDvCd());
        returnDvo.setDgCstId(tmpSlDvo.getDgCstId());
        returnDvo.setCstNo(tmpSlDvo.getCstNo());
        returnDvo.setPdCd(tmpSlDvo.getPdCd());
        returnDvo.setSlBndAlrpyDt(null);
        returnDvo.setSlBndAlrpyAmt(ObjectUtils.defaultIfNull(allRepaymentAmount, BigDecimal.ZERO));
        returnDvo.setBndAlrpyAggAmt(BigDecimal.ZERO);
        returnDvo.setRveNo(basDvo.getRveNo());
        returnDvo.setRveSn(basDvo.getRveSn());
        returnDvo.setDpClDt(basDvo.getDpClDt());
        returnDvo.setRveAmt(ObjectUtils.defaultIfNull(depositAmount, BigDecimal.ZERO));
        returnDvo.setRveDvCd(basDvo.getRveDvCd());
        returnDvo.setSapDpSlpno(basDvo.getSapSlpno());
        returnDvo.setDpAcuAmt(BigDecimal.ZERO);
        returnDvo.setBtdAtam(ObjectUtils.defaultIfNull(dpBlam, BigDecimal.ZERO));
        returnDvo.setDpBlam(ObjectUtils.defaultIfNull(anticipation, BigDecimal.ZERO));
        returnDvo.setAtamRplcProcsAmt(ObjectUtils.defaultIfNull(atamRplcProcsAmt, BigDecimal.ZERO));
        returnDvo.setAtamCvAmt(ObjectUtils.defaultIfNull(anticipationConversionAmount, BigDecimal.ZERO));
        returnDvo.setSlRcogDt(tmpSlDvo.getSlRcogDt());
        returnDvo.setSlBndOcAmt(ObjectUtils.defaultIfNull(tmpSlDvo.getSlAmtSum(), BigDecimal.ZERO));
        returnDvo.setSlCanAmt(ObjectUtils.defaultIfNull(slCanAmt, BigDecimal.ZERO));
        returnDvo.setSpmtUcOcAmt(ObjectUtils.defaultIfNull(spmtUcOcAmt, BigDecimal.ZERO));
        returnDvo.setSapSlSlpno(tmpSlDvo.getSapSlpno());
        returnDvo.setTotSlAmt(ObjectUtils.defaultIfNull(totSlAmt.add(salesAmount), BigDecimal.ZERO));
        returnDvo.setBtdUcAmt(ObjectUtils.defaultIfNull(ucAmt, BigDecimal.ZERO));
        returnDvo.setUcAmt(ObjectUtils.defaultIfNull(uncollected, BigDecimal.ZERO));

        return returnDvo;
    }

    /**
     * 월마감 채권 반제 로직
     *
     * @param dvo
     * @return
     */
    public WdchSlBndAlrpyBasDvo getMonthClosingBondReimbursement(WdchEduSlSalesDataDvo dvo) {
        // s1-1 : selectWdchEduSlCnfmBasLists에 [계약번호, 계약일련번호] 조건을 추가하여 조회
        int wdchEduSlCnfmBasCount = wdchClearingDataCreateMapper.selectWdchEduSlCnfmBasCount(dvo);

        // s2-1 : selectNonWdchEduSlCnfmBasLists에 [계약번호, 계약일련번호] 조건을 추가하여 조회
        int wdchNonEduSlCnfmBasCount = wdchClearingDataCreateMapper.selectWdchNonEduSlCnfmBasCount(dvo);

        if (wdchEduSlCnfmBasCount > 0) {
            dvo.setFlagYm("3");

            // s1-2 : selectWdchEduSlCnfmBass로 매출 마지막건 조회
            List<WdchEduSlCnfmBasDvo> edchEduSlCnfmBases = wdchClearingDataCreateMapper
                .selectWdchEduSlCnfmBass(dvo);

            // s1-3 : selectBznsAtamBas로 영업 선수금 조회
            List<WdchBznsAtamBasDvo> bznsAtamBases = wdchClearingDataCreateMapper.selectBznsAtamBas(dvo);

            /* 입금 마지막건 가지고 있는 용도 */
            WdchBznsAtamBasDvo tmpDepositDvo = new WdchBznsAtamBasDvo();
            if (bznsAtamBases.size() > 0) {
                tmpDepositDvo = bznsAtamBases.get(bznsAtamBases.size() - 1);
            }

            if (CollectionUtils.isNotEmpty(edchEduSlCnfmBases) && CollectionUtils.isEmpty(bznsAtamBases)) {
                // 전월 채권반제기본 조회
                WdchSlBndAlrpyBasBeforeMonthDvo lstMmSlBndAlrpyBasDvo = wdchClearingDataCreateMapper
                    .selectSlBndAlrpyBasBeforeMonth(dvo);
                if (ObjectUtils.isEmpty(lstMmSlBndAlrpyBasDvo)) {
                    lstMmSlBndAlrpyBasDvo = new WdchSlBndAlrpyBasBeforeMonthDvo();
                }

                WdchEduSlCnfmBasDvo tmpSlDvo = edchEduSlCnfmBases.get(edchEduSlCnfmBases.size() - 1);

                WdchSlBndAlrpyBasDvo returnDvo = new WdchSlBndAlrpyBasDvo();

                returnDvo.setCntrNo(dvo.getCntrNo());
                returnDvo.setCntrSn(dvo.getCntrSn());
                returnDvo.setBaseYm(dvo.getBaseYm());
                returnDvo.setKwGrpCoCd(tmpSlDvo.getKwGrpCoCd());
                returnDvo.setSellTpCd(tmpSlDvo.getSellTpCd());
                returnDvo.setSellTpDtlCd(tmpSlDvo.getSellTpDtlCd());
                returnDvo.setSellInflwChnlDtlCd(tmpSlDvo.getSellInflwChnlDtlCd());
                returnDvo.setSapPdDvCd(tmpSlDvo.getSapPdDvCd());
                returnDvo.setDgCstId(tmpSlDvo.getDgCstId());
                returnDvo.setCstNo(tmpSlDvo.getCstNo());
                returnDvo.setPdCd(tmpSlDvo.getPdCd());
                returnDvo.setSlBndAlrpyDt(null);
                returnDvo.setSlBndAlrpyAmt(BigDecimal.ZERO);
                returnDvo.setBndAlrpyAggAmt(BigDecimal.ZERO);
                returnDvo.setRveNo(null);
                returnDvo.setRveSn(0);
                returnDvo.setDpClDt(null);
                returnDvo.setRveAmt(BigDecimal.ZERO);
                returnDvo.setRveDvCd(null);
                returnDvo.setSapDpSlpno(null);
                returnDvo.setDpAcuAmt(BigDecimal.ZERO);
                returnDvo.setBtdAtam(BigDecimal.ZERO);
                returnDvo.setDpBlam(BigDecimal.ZERO);
                returnDvo.setAtamRplcProcsAmt(BigDecimal.ZERO);
                returnDvo.setAtamCvAmt(BigDecimal.ZERO);
                returnDvo.setSlRcogDt(tmpSlDvo.getSlRcogDt());
                returnDvo.setSlBndOcAmt(ObjectUtils.defaultIfNull(tmpSlDvo.getSlAmtSum(), BigDecimal.ZERO));
                returnDvo.setSlCanAmt(BigDecimal.ZERO);
                // 추가미수발생금액 = 발생연체가산금액 + 발생위약금액
                returnDvo.setSpmtUcOcAmt(
                    ObjectUtils.defaultIfNull(tmpSlDvo.getThmOcDlqAddAmt(), BigDecimal.ZERO).add(
                        ObjectUtils.defaultIfNull(tmpSlDvo.getOcBorAmt(), BigDecimal.ZERO)
                    )
                );
                returnDvo.setSapSlSlpno(tmpSlDvo.getSapSlpno());
                // 총매출금액 = 전월 총매출금액 + 매출
                returnDvo.setTotSlAmt(
                    ObjectUtils.defaultIfNull(lstMmSlBndAlrpyBasDvo.getTotSlAmt(), BigDecimal.ZERO)
                        .add(tmpSlDvo.getSlAmtSum())
                );
                // 기초미수금액 = 전월 미수금
                returnDvo.setBtdUcAmt(ObjectUtils.defaultIfNull(lstMmSlBndAlrpyBasDvo.getUcAmt(), BigDecimal.ZERO));
                // 미수금액 = 전월 미수금 + 매출 - 반제금액(0:생략)
                returnDvo.setUcAmt(
                    ObjectUtils.defaultIfNull(lstMmSlBndAlrpyBasDvo.getUcAmt(), BigDecimal.ZERO)
                        .add(tmpSlDvo.getSlAmtSum())
                );

                return returnDvo;
            } else {
                // 채권반제계산 공통 서비스 호출
                return calculateBondReimbursement(dvo, tmpDepositDvo);
            }
        } else if (wdchNonEduSlCnfmBasCount > 0) { // s2-1
            // s2-2 : selectBznsAtamBas를 계약번호, 계약일련번호 조회
            List<WdchBznsAtamBasDvo> bznsAtamBases = wdchClearingDataCreateMapper.selectBznsAtamBas(dvo);
            if (CollectionUtils.isEmpty(bznsAtamBases)) {
                // 전월 채권반제를 조회
                WdchSlBndAlrpyBasDvo edchSlBndAlrpyBasDvo = wdchClearingDataCreateMapper
                    .selectSlBndAlrpyBasBeforeOneMonth(dvo);

                if (ObjectUtils.isEmpty(edchSlBndAlrpyBasDvo)) { // 없다면, return new VO
                    WdchSlBndAlrpyBasDvo returnEmptyDvo = new WdchSlBndAlrpyBasDvo();

                    returnEmptyDvo.setCntrNo(dvo.getCntrNo()); // 계약번호
                    returnEmptyDvo.setCntrSn(dvo.getCntrSn()); // 계약일련번호
                    returnEmptyDvo.setBaseYm(dvo.getBaseYm()); // 기준년월

                    returnEmptyDvo.setSlBndAlrpyAmt(BigDecimal.ZERO); // 매출채권반제금액
                    returnEmptyDvo.setBndAlrpyAggAmt(BigDecimal.ZERO); // 채권반제누계금액
                    returnEmptyDvo.setRveAmt(BigDecimal.ZERO); // 수납금액
                    returnEmptyDvo.setDpAcuAmt(BigDecimal.ZERO); // 입금누적금액
                    returnEmptyDvo.setBtdAtam(BigDecimal.ZERO); // 기초선수금액
                    returnEmptyDvo.setDpBlam(BigDecimal.ZERO); // 입금잔액
                    returnEmptyDvo.setAtamRplcProcsAmt(BigDecimal.ZERO); // 선수금대체처리금액
                    returnEmptyDvo.setAtamCvAmt(BigDecimal.ZERO); // 선수금전환금액
                    returnEmptyDvo.setSlBndOcAmt(BigDecimal.ZERO); // 매출채권발생금액
                    returnEmptyDvo.setSlCanAmt(BigDecimal.ZERO); // 매출취소금액
                    returnEmptyDvo.setSpmtUcOcAmt(BigDecimal.ZERO); // 추가미수발생금액
                    returnEmptyDvo.setTotSlAmt(BigDecimal.ZERO); // 총매출금액
                    returnEmptyDvo.setBtdUcAmt(BigDecimal.ZERO); // 기초미수금액
                    returnEmptyDvo.setUcAmt(BigDecimal.ZERO); // 미수금액

                    return returnEmptyDvo;
                } else { // 있다면, 조회한 VO리턴
                    return edchSlBndAlrpyBasDvo;
                }
            } else {
                /* 입금 마지막건 가지고 있는 용도 */
                WdchBznsAtamBasDvo tmpDepositDvo = new WdchBznsAtamBasDvo();
                if (bznsAtamBases.size() > 0) {
                    tmpDepositDvo = bznsAtamBases.get(bznsAtamBases.size() - 1);
                }

                // 채권반제계산 공통 서비스 호출
                dvo.setFlagYm("4");
                return calculateBondReimbursement(dvo, tmpDepositDvo);
            }
        }

        return new WdchSlBndAlrpyBasDvo();
    }
}
