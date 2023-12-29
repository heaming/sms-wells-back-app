package com.kyowon.sms.wells.web.closing.performance.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.closing.clearing.dvo.WdchEduSlSalesDataDvo;
import com.kyowon.sms.wells.web.closing.clearing.dvo.WdchSlBndAlrpyBasDvo;
import com.kyowon.sms.wells.web.closing.clearing.service.WdchClearingDataCreateService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.bond.zcommon.constants.BnBondConst;
import com.kyowon.sms.common.web.closing.performance.dvo.ZdccMonthlySalesClosingAggregationWellsDvo;
import com.kyowon.sms.common.web.closing.performance.mapper.ZdccMonthlySalesClosingAggregationMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static java.lang.Math.abs;

/**
 * <pre>
 * 매출월마감내역집계 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-12-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdccMonthlySalesClosingAggregationService {
    private final ZdccMonthlySalesClosingAggregationMapper mapper;
    private final WdchClearingDataCreateService wdchClearingDataCreateService;

    /**
     * WELLS 매출 월마감 내역 집계(select -> merge(update,insert))
     * @param excnDt 실행일(ex>20231013)
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     */
    @Transactional
    public int createSalesMonthlyClosingDetailsTallyForWells(String excnDt, String cntrNo, String cntrSn) {
        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("excnDt", excnDt);
        parameterValues.put("cntrNo", cntrNo);
        parameterValues.put("cntrSn", cntrSn);

        int result = 0;
        List<ZdccMonthlySalesClosingAggregationWellsDvo> dvos = mapper.selectSalesMonthEndForWells(parameterValues);
        for (ZdccMonthlySalesClosingAggregationWellsDvo dvo : dvos) {

            salesClosingDataSettings(dvo);

            result = mapper.mergeMonthlySalesClosingAggregationForWells(dvo);
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR);

            // 초과매출조정분에 대한 연체잔액 처리(WELLS)
            editOvrSlCtrDlqBlamProcs(dvo);
        }
        return result;
    }

    /**
     * 초과매출조정분에 대한 연체잔액 처리(WELLS)
     * @param dvo
     * @return
     */
    @Transactional
    public int editOvrSlCtrDlqBlamProcs(ZdccMonthlySalesClosingAggregationWellsDvo dvo) {
        int result = 0;
        int thmSlSumAmt = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getThmSlSumAmt(), "0")); //당월매출합계금액
        if (thmSlSumAmt < 0) { // 당월매출합계금액 < 0 일때 수행
            // 초과조정금액 갱신
            result = mapper.updateDlqBas(dvo.getThmSlSumAmt(), dvo.getCntrNo(), dvo.getCntrSn());
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR);
            String sellTpCd = dvo.getSellTpCd();
            String sellTpDtlCd = dvo.getSellTpDtlCd();
            // 연체개월수 변경
            if ((StringUtils.equals(sellTpCd, "2") && Arrays.asList("21", "23").contains(sellTpDtlCd))
                || StringUtils.equals(sellTpCd, "3")) { // 렌탈 또는 멤버십
                result = mapper.updateDlqMcnType01(dvo.getCntrNo(), dvo.getCntrSn());
            }
            if (StringUtils.equals(sellTpCd, "2") && Arrays.asList("22", "24", "25", "26").contains(sellTpDtlCd)) { // 리스
                result = mapper.updateDlqMcnType02(dvo.getCntrNo(), dvo.getCntrSn());
            }
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR);
            // 연체기본이력 등록
            result = mapper.insertDlqBasHist(dvo.getCntrNo(), dvo.getCntrSn());
            BizAssert.isTrue(result == 1, BnBondConst.MSG_ALT_SVE_ERR);
        }
        return result;
    }

    /**
     * 월매출 매출채권반제금액 -> 선수매출금액 으로 셋팅
     * @param dvo 매출마감 객체
     */
    public void salesClosingDataSettings(ZdccMonthlySalesClosingAggregationWellsDvo dvo) {
        // int prpdSlAmt = 0; //선수매출금액
        // int slBndAlrpyAmt = 0; //매출채권반제금액
        int eotAtam = 0; //기말선수금
        int eotUcAmt = 0; //기말미수금
        int ovrCtrDpAmt = 0; //초과조정입금금액

        int btdAtam = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getBtdAtam(), "0")); //기초선수금
        int thmAtamDpAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmAtamDpAmt(), "0")); //당월선수금입금금액
        int thmAtamRfndAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmAtamRfndAmt(), "0")); //당월선수금환불금액
        int btdUcAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getBtdUcAmt(), "0")); //기초미수금
        int thmSlSumAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmSlSumAmt(), "0")); //당월매출합계금액
        int slDpAggAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getSlDpAggAmt(), "0")); //매출입금누계금액
        // int mmIstmAmt = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getMmIstmAmt(), "0")); //월할부금액
        String sellTpCd = dvo.getSellTpCd(); //판매유형코드
        String sellTpDtlCd = dvo.getSellTpDtlCd(); //판매유형상세코드

        int slClYm = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getSlClYm(), "0")); //매출마감년월
        int prmStrtYm = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getPrmStrtYm(), "0")); //선납시작년월
        int prmEndYm = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getPrmEndYm(), "0")); //선납종료년월
        int prmBlamBtdAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getPrmBlamBtdAmt(), "0")); //선납잔액기초금액
        int prmDpAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getPrmDpAmt(), "0")); //선납입금금액
        int prmRfndAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getPrmRfndAmt(), "0")); //선납환불금액
        int prmDscr = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getPrmDscr(), "0")); //선납할인율
        int nomSlAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getNomSlAmt(), "0")); //정상매출금액
        int spmtSlAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getSpmtSlAmt(), "0")); //추가매출금액
        // int prmSlAmt = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getPrmSlAmt(), "0")); //선납매출금액
        String slRcogDvCd = dvo.getSlRcogDvCd(); //매출인식구분코드
        int nomDscAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getNomDscAmt(), "0")); //정상할인금액
        int spmtDscAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getSpmtDscAmt(), "0")); //추가할인금액
        int slCtrAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getSlCtrAmt(), "0")); //매출조정금액
        int slChAmt = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getSlChAmt(), "0")); //매출변경금액
        int slCanAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getSlCanAmt(), "0")); //매출취소금액
        int slAggAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getSlAggAmt(), "0")); //매출누계금액
        int dscAggAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getDscAggAmt(), "0")); //할인누계금액

        int leaseSlCtrAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getLeaseSlCtrAmt(), "0")); //리스매출조정금액
        int leaseSlCanAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getLeaseSlCanAmt(), "0")); //리스매출취소금액

        int btdBilUcAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getBtdBilUcAmt(), "0")); //기초청구미수금액
        int thmBilOcAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmBilOcAmt(), "0")); //당월청구발생금액
        int thmBilCtrAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmBilCtrAmt(), "0")); //당월청구조정금액
        int thmBilSpmtAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmBilSpmtAmt(), "0")); //당월청구추가금액

        if (StringUtils.equals(sellTpCd, "1")) { //일시불인 경우
            //당월매출합계금액 = 정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액 + 매출변경금액 - 매출취소금액
            dvo.setThmSlSumAmt(
                String.valueOf(nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt + slChAmt - slCanAmt)
            );
            //매출합계금액부가가치세 = (정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액 + 매출변경금액 - 매출취소금액) * 0.0909
            dvo.setSlSumVat(
                String.valueOf(
                    (nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt + slChAmt - slCanAmt) * 0.0909
                )
            );
            //매출누계금액 = 전월매출누계금액 + 정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액 + 매출변경금액 - 매출취소금액
            dvo.setSlAggAmt(
                String.valueOf(
                    slAggAmt + nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt + slChAmt - slCanAmt
                )
            );
        } else {
            //당월매출합계금액 = 정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액
            dvo.setThmSlSumAmt(
                String.valueOf(nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt)
            );
            //매출합계금액부가가치세 = (정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액) * 0.0909
            dvo.setSlSumVat(
                String.valueOf((nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt) * 0.0909)
            );
            //매출누계금액 = 전월매출누계금액 + 정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액
            dvo.setSlAggAmt(
                String.valueOf(slAggAmt + nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt)
            );
        }

        //할인누계금액 = 전월할인누계금액 + 정상할인금액 + 추가할인금액
        dvo.setDscAggAmt(String.valueOf(dscAggAmt + nomDscAmt + spmtDscAmt));

        // 반제서비스 호출
        WdchEduSlSalesDataDvo inputDvo = new WdchEduSlSalesDataDvo();
        inputDvo.setCntrNo(dvo.getCntrNo()); //계약번호
        inputDvo.setCntrSn(Integer.parseInt(dvo.getCntrSn())); //계약일련번호
        inputDvo.setBaseYm(dvo.getSlClYm()); //매출마감년월

        WdchSlBndAlrpyBasDvo rtnDvo = wdchClearingDataCreateService.getMonthClosingBondReimbursement(inputDvo);

        int alrpyAmt = Integer
            .parseInt(String.valueOf(ObjectUtils.defaultIfNull(rtnDvo.getSlBndAlrpyAmt(), BigDecimal.ZERO))); //반제금액
        // int dpAmt = btdAtam + thmAtamDpAmt - thmAtamRfndAmt; //입금금액
        int slAmt = btdUcAmt + thmSlSumAmt; //매출금액

        // eotAtam = dpAmt - alrpyAmt; //기말선수금 = 입금금액 - 반제금액
        eotUcAmt = slAmt - alrpyAmt; //기말미수금 = 매출금액 - 반제금액

        /* 초과조정입금금액 = 전월미수 + 당월매출합계 가 0보다 작은 경우 전월미수+당월매출합계 의 절대값, 아닌경우 0 */
        if (btdUcAmt + thmSlSumAmt < 0) {
            ovrCtrDpAmt = abs(btdUcAmt + thmSlSumAmt);
        } else {
            ovrCtrDpAmt = 0;
        }
        /* 기말선수금 = 기초선수 + 선수입금 - 선수환불 + 초과조정금액 - 매출반제 (단, 매출반제 < 0 인 경우 반제금액은 0 으로 계산) */
        if (alrpyAmt < 0) {
            eotAtam = btdAtam + thmAtamDpAmt - thmAtamRfndAmt + ovrCtrDpAmt;
        } else {
            eotAtam = btdAtam + thmAtamDpAmt - thmAtamRfndAmt + ovrCtrDpAmt - alrpyAmt;
        }

        dvo.setAtamRplcProcsAmt(
            String.valueOf(ObjectUtils.defaultIfNull(rtnDvo.getAtamRplcProcsAmt(), BigDecimal.ZERO))
        ); //선수금대체처리금액

        /* *
        SL_BND_ALRPY_AMT (매출채권반제금액) = 반제금액
        EOT_ATAM (기말선수금) = 기말선수금
        EOT_UC_AMT (기말미수금) = 기말미수금
        PRPD_SL_AMT (선수매출금액) = 반제금액
        SL_DP_AGG_AMT (매출입금누계금액) = SL_DP_AGG_AMT (매출입금누계금액) + 반제금액  (매출입금누계금액은 반제금액의 누계금액 임)
        * */
        dvo.setSlBndAlrpyAmt(String.valueOf(alrpyAmt));
        dvo.setEotAtam(String.valueOf(eotAtam));
        dvo.setEotUcAmt(String.valueOf(eotUcAmt));
        dvo.setPrpdSlAmt(String.valueOf(alrpyAmt));
        dvo.setSlDpAggAmt(String.valueOf(slDpAggAmt + alrpyAmt));

        /*
        해당건이 선납기간 인 경우
        매출채권반제금액 - 선납금액 이 0보다 크면 매출채권반제금액을 선납매출에 넣어주고, 매출채권반제 - 선납 금액을 선수매출금액에 넣어줌
        매출채권반제금액 - 선납금액 이 0보다 작으면 매출채권반제금액을 선납매출 넣어줌
        int 선납금액 = PRM_BLAM_BTD_AMT(선납잔액기초금액) + PRM_DP_AMT(선납입금금액) - PRM_RFND_AMT(선납환불)
        선납입금금액1 = 정상매출 * 선납할인율
        기초선수 = 0
        선납잔액기말금액 = 선납잔액기초금액 + 선납입금금액 - 선납매출금액
         */
        int prmAmt = prmBlamBtdAmt + prmDpAmt - prmRfndAmt;
        int prmDpAmt1 = 0;

        if (slClYm >= prmStrtYm && slClYm <= prmEndYm) {
            if (alrpyAmt - prmAmt > 0) {
                dvo.setPrmSlAmt(String.valueOf(alrpyAmt));
                dvo.setPrpdSlAmt(String.valueOf(alrpyAmt - prmAmt));
            } else {
                dvo.setPrmSlAmt(String.valueOf(alrpyAmt));
                dvo.setPrpdSlAmt("0");
            }
            prmDpAmt1 = ((nomSlAmt * (100 - prmDscr)) / 1000) * 10;
            dvo.setPrmDpAmt1(String.valueOf(prmDpAmt1));
            dvo.setBtdAtam("0");

            int prmBlamEotAmt = prmBlamBtdAmt + prmDpAmt
                - Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getPrmSlAmt(), "0"));
            dvo.setPrmBlamEotAmt(String.valueOf(prmBlamEotAmt));
        }

        /*
        리스이고 조정인 경우
        추가할인금액, 매출조정금액 은 0 으로 처리
        리스매출조정금액, 당월청구조정금액 은 추가할인금액 + 매출조정금액 을 넣어줌
        리스이고 취소인 경우
        정상매출금액, 매출취소금액을 비교해서 금액이 같으면 매출취소금액 그대로 유지, 리스매출취소금액 0
        정상매출금액, 매출취소금액이 다르면 매출취소금액은 0 으로 넣어주고, 리스매출취소금액에 매출취소금액을 넣어줌
        */
        if (StringUtils.equals(sellTpDtlCd, "22")
            || StringUtils.equals(sellTpDtlCd, "24")
            || StringUtils.equals(sellTpDtlCd, "25")
            || StringUtils.equals(sellTpDtlCd, "26")) {
            if (StringUtils.equals(slRcogDvCd, "04")) { //조정
                dvo.setSpmtDscAmt("0"); //추가할인금액
                dvo.setSlCtrAmt("0"); //매출조정금액
                dvo.setLeaseSlCtrAmt(String.valueOf(spmtDscAmt + slCtrAmt));
                dvo.setThmBilCtrAmt(String.valueOf(spmtDscAmt + slCtrAmt));
                //당월매출합계금액 = 정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액:0 - 매출조정금액:0
                dvo.setThmSlSumAmt(String.valueOf(nomSlAmt + spmtSlAmt - nomDscAmt));
                //매출합계금액부가가치세 = (정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액:0 - 매출조정금액:0) * 0.0909
                dvo.setSlSumVat(String.valueOf((nomSlAmt + spmtSlAmt - nomDscAmt) * 0.0909));
                //매출누계금액 = 전월매출누계금액 + 정상매출금액 + 추가매출금액 - 정상할인금액
                dvo.setSlAggAmt(String.valueOf(slAggAmt + nomSlAmt + spmtSlAmt - nomDscAmt));
                //리스매출조정총금액 = 리스매출조정금액 + 리스매출취소금액
                dvo.setLeaseSlCtrTotAmt(String.valueOf(leaseSlCtrAmt + leaseSlCanAmt));
            } else if (StringUtils.equals(slRcogDvCd, "02")) { //취소
                if (nomSlAmt != slCanAmt) {
                    dvo.setLeaseSlCanAmt(String.valueOf(slCanAmt)); //리스매출취소금액
                    dvo.setSlCanAmt("0"); // 매출취소금액
                    //당월매출합계금액 = 정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액
                    dvo.setThmSlSumAmt(
                        String.valueOf(nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt)
                    );
                    //매출합계금액부가가치세 = (정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액) * 0.0909
                    dvo.setSlSumVat(
                        String
                            .valueOf((nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt) * 0.0909)
                    );
                    //매출누계금액 = 전월매출누계금액 + 정상매출금액 + 추가매출금액 - 정상할인금액 - 추가할인금액 - 매출조정금액
                    dvo.setSlAggAmt(
                        String
                            .valueOf(slAggAmt + nomSlAmt + spmtSlAmt - nomDscAmt - spmtDscAmt - slCtrAmt)
                    );
                    //리스매출조정총금액 = 리스매출조정금액 + 리스매출취소금액
                    dvo.setLeaseSlCtrTotAmt(String.valueOf(leaseSlCtrAmt + slCanAmt));
                } else {
                    dvo.setLeaseSlCanAmt("0"); //리스매출취소금액
                    dvo.setLeaseSlCtrTotAmt("0"); //리스매출조정총금액
                }
            }
        }

        /*
        IF(정기배송 or 리스) {

          당월납 or 리스인 경우
          IF(기초청구미수금액 + 당월청구발생금액 < 선수기초 + 선수입금) {
            당월청구입금금액 = 기초청구미수금액 + 당월청구발생금액
          } ELSE {
            당월청구입금금액 = 선수기초 + 선수입금
          }

          후납인 경우
          IF(기초청구미수금액 < 선수기초 + 선수입금) {
            당월청구입금금액 = 기초청구미수금액
          } ELSE {
            당월청구입금금액 = 선수기초 + 선수입금
          }

        선수기말 = (선수기초 + 선수입금) - 당월청구입금금액
        기말청구미수금액 = 기초청구미수 + 당월청구발생 - 당월청구입금 - 당월청구조정 + 당월청구추가
        }
        */
        if (StringUtils.equals(sellTpCd, "6")
            || StringUtils.equals(sellTpDtlCd, "22")
            || StringUtils.equals(sellTpDtlCd, "24")
            || StringUtils.equals(sellTpDtlCd, "25")
            || StringUtils.equals(sellTpDtlCd, "26")) { //정기배송 or 리스
            if (StringUtils.equals(sellTpDtlCd, "61")
                || StringUtils.equals(sellTpDtlCd, "22")
                || StringUtils.equals(sellTpDtlCd, "24")
                || StringUtils.equals(sellTpDtlCd, "25")
                || StringUtils.equals(sellTpDtlCd, "26")) { //당월납 or 리스
                if ((btdBilUcAmt + thmBilOcAmt) < (btdAtam + thmAtamDpAmt)) {
                    dvo.setThmBilDpAmt(String.valueOf(btdBilUcAmt + thmBilOcAmt));
                } else {
                    dvo.setThmBilDpAmt(String.valueOf(btdAtam + thmAtamDpAmt));
                }
            } else { //후납
                if (btdBilUcAmt < (btdAtam + thmAtamDpAmt)) {
                    dvo.setThmBilDpAmt(String.valueOf(btdBilUcAmt));
                } else {
                    dvo.setThmBilDpAmt(String.valueOf(btdAtam + thmAtamDpAmt));
                }
            }
            dvo.setEotBilUcAmt(
                String.valueOf(
                    btdBilUcAmt + thmBilOcAmt
                        - Integer.parseInt(
                            StringUtils.defaultIfEmpty(dvo.getThmBilDpAmt(), "0")
                        ) - thmBilCtrAmt
                        + thmBilSpmtAmt
                )
            );
        }

        /*
        정기배송

        IF 당월청구조정금액 = 0
        IF 당월납
        IF 기초청구미수금액 + 당월청구발생금액 <= 선수기초 + 선수입금
        당월청구입금금액 = 기초청구미수금액 + 당월청구발생금액
        기말청구미수금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
        기말선수금액 = 선수기초 + 선수입금 - 기초청구미수금액 + 당월청구발생금액
        ELSE
        당월청구입금금액 = 선수기초 + 선수입금
        기말청구미수금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
        기말선수금액 = 0
        ELSE --후납
        IF 기초청구미수금액 <= 선수기초 + 선수입금
        당월청구입금금액 = 기초청구미수금액
        기말청구미수금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
        기말선수금액 = 선수기초 + 선수입금 - 기초청구미수금액
        ELSE
        당월청구입금금액 = 선수기초 + 선수입금
        기말청구미수금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
        기말선수금액 = 0

        IF 당월청구조정금액 > 0
        IF 당월납
        IF 기초청구미수금액 + 당월청구발생금액 <= 선수기초 + 선수입금
        당월청구입금금액 = 기초청구미수금액 + 당월청구발생금액
        당월청구조정금액 = 0
        IF 차월청구미수예정금액 > 0
        차월청구미수예정금액 = 차월청구미수예정금액 - 당월청구조정금액
        IF 차월청구미수예정금액 > 0
        기말선수금액 0
        ELSE IF 차차월청구미수예정금액 >0
             차차월청구미수예정금액 = 차차월청구미수예정금액 - (차월청구미수예정금액 - 당월청구조정금액)
              IF 차차월청구미수예정금액 > 0
                 차월청구예정금액 = 0
                 기말선수금 = 0
              ELSE
                  차월청구예정금액 = 0
                  차차월청구예정금액 = 0
                  초과조정입금금액 = 당월청구조정금액 - 차월청구미수예정금액 - 차차월청구미수예정금액
                  기말선수금 = 당월청구조정금액 - 차월청구미수예정금액 - 차차월청구미수예정금액
        ELSE
        초과조정입금금액 = 매출조정금액
        기말선수금 = 매출조정금액
        ELSE
        당월청구입금금액 = 선수기초금액 + 선수입금금액
        IF 당월청구조정금액 > 기초청구미수금액 + 당월청구발생금액 - 선수기초금액 + 선수입금금액
        초과조정입금금액 = 기초청구미수금액 + 당월청구발생금액 - 선수기초금액 + 선수입금금액
        기말선수금 = 기초청구미수금액 + 당월청구발생금액 - 선수기초금액 + 선수입금금액
        당월청구조정금액 = 기초청구미수금액 + 당월청구발생금액 - 선수기초금액 + 선수입금금액
        기말청구미수금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
        ELSE  --후납
        IF 기초청구미수금액 <= 선수기초 + 선수입금
        당월청구입금금액 = 기초청구미수금액
        당월청구조정금액 = 0
        IF 차월청구미수예정금액 > 0
        차월청구미수예정금액 = 차월청구미수예정금액 - 당월청구조정금액
        IF 차월청구미수예정금액 > 0
        기말선수금액 0
        ELSE IF 차차월청구미수예정금액 >0
             차차월청구미수예정금액 = 차차월청구미수예정금액 - (차월청구미수예정금액 - 당월청구조정금액)
              IF 차차월청구미수예정금액 > 0
                 차월청구예정금액 = 0
                 기말선수금 = 0
              ELSE
                  차월청구예정금액 = 0
                  차차월청구예정금액 = 0
                  초과조정입금금액 = 당월청구조정금액 - 차월청구미수예정금액 - 차차월청구미수예정금액
                  기말선수금 = 당월청구조정금액 - 차월청구미수예정금액 - 차차월청구미수예정금액
        ELSE
        초과조정입금금액 = 매출조정금액
        기말선수금 = 매출조정금액
        ELSE
        당월청구입금금액 = 선수기초금액 + 선수입금금액
        IF 당월청구조정금액 > 기초청구미수금액 - 선수기초금액 + 선수입금금액
        초과조정입금금액 = 기초청구미수금액 - 선수기초금액 + 선수입금금액
        기말선수금 = 기초청구미수금액 - 선수기초금액 + 선수입금금액
        당월청구조정금액 = 기초청구미수금액 - 선수기초금액 + 선수입금금액
        기말청구미수금액 = 기초청구미수금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
         */
        thmBilCtrAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmBilCtrAmt(), "0")); //당월청구조정금액
        btdBilUcAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getBtdBilUcAmt(), "0")); //기초청구미수금액
        thmBilOcAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmBilOcAmt(), "0")); //당월청구발생금액
        btdAtam = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getBtdAtam(), "0")); //기초선수금
        thmAtamDpAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmAtamDpAmt(), "0")); //당월선수금입금금액
        // thmBilDpAmt = Integer.parseInt(StringUtils.defaultIfEmpty(dvo.getThmBilDpAmt(), "0")); //당월청구입금금액
        thmBilSpmtAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getThmBilSpmtAmt(), "0")); //당월청구추가금액
        int nmnBilUcExpAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getNmnBilUcExpAmt(), "0")); //차월청구미수예정금액
        int tsmBilUcExpAmt = Integer
            .parseInt(StringUtils.defaultIfEmpty(dvo.getTsmBilUcExpAmt(), "0")); //차차월청구미수예정금액

        int thmBilCtrAmtTmp = thmBilCtrAmt; //당월청구조정금액tmp
        int nmnBilUcExpAmtTmp = nmnBilUcExpAmt; //차월청구예정금액tmp
        int tsmBilUcExpAmtTmp = tsmBilUcExpAmt; //차차월청구예정금액tmp
        int eotBilUcAmt = 0; //기말청구미수금액
        int thmBilDpAmt = 0; //당월청구입금금액
        // eotAtam = 0; //기말선수금
        // int ovrCtrDpAmt = 0; //초과조정입금금액

        if (StringUtils.equals(sellTpCd, "6")) {
            eotAtam = 0; //기말선수금
            if (thmBilCtrAmt == 0) { //당월청구조정금액 = 0
                if (StringUtils.equals(sellTpDtlCd, "61")) { //당월납
                    if (btdBilUcAmt + thmBilOcAmt <= btdAtam + thmAtamDpAmt) {
                        thmBilDpAmt = btdBilUcAmt + thmBilOcAmt; //당월청구입금금액
                        eotAtam = btdAtam + thmAtamDpAmt - btdBilUcAmt + thmBilOcAmt; //기말선수금액
                    } else {
                        thmBilDpAmt = btdAtam + thmAtamDpAmt; //당월청구입금금액
                        eotAtam = 0; //기말선수금액
                    }
                    eotBilUcAmt = btdBilUcAmt + thmBilOcAmt - thmAtamDpAmt - thmBilCtrAmt + thmBilSpmtAmt; //기말청구미수금액
                } else { //후납
                    if (btdBilUcAmt <= btdAtam + thmAtamDpAmt) {
                        thmBilDpAmt = btdBilUcAmt; //당월청구입금금액
                        eotAtam = btdAtam + thmAtamDpAmt - btdBilUcAmt + thmBilOcAmt; //기말선수금액
                    } else {
                        thmBilDpAmt = btdBilUcAmt + thmAtamDpAmt; //당월청구입금금액
                        eotAtam = 0; //기말선수금액
                    }
                    eotBilUcAmt = btdBilUcAmt + thmBilOcAmt - thmAtamDpAmt - thmBilCtrAmt + thmBilSpmtAmt; //기말청구미수금액
                }
                dvo.setThmBilDpAmt(String.valueOf(thmBilDpAmt));
                dvo.setEotAtam(String.valueOf(eotAtam));
                dvo.setEotBilUcAmt(String.valueOf(eotBilUcAmt));
            }
            if (thmBilCtrAmt > 0) { //당월청구조정금액 > 0
                if (StringUtils.equals(sellTpDtlCd, "61")) { //당월납
                    if (btdBilUcAmt + thmBilOcAmt <= btdAtam + thmAtamDpAmt) {
                        thmBilDpAmt = btdBilUcAmt + thmBilOcAmt; //당월청구입금금액

                        if (btdBilUcAmt + thmBilOcAmt <= slCtrAmt) { // 기초청구미수금액 + 당월청구발생금액 <= 당월청구조정금액
                            thmBilCtrAmtTmp = btdBilUcAmt + thmBilOcAmt; //당월청구조정금액
                        } else {
                            thmBilCtrAmtTmp = slCtrAmt;
                            if (btdBilUcAmt + thmBilOcAmt - thmBilCtrAmtTmp <= thmAtamDpAmt) { //기초청구미수금액 + 당월청구발생금액 - 당월청구조정금액 <= 당월선수금입금금액
                                thmBilDpAmt = btdBilUcAmt + thmBilOcAmt - thmBilCtrAmtTmp;//당월청구입금금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구조정금액
                            } else {
                                thmBilDpAmt = thmAtamDpAmt;//당월청구입금금액 = 당월선수금입금금액
                            }
                        }

                        if (nmnBilUcExpAmt > 0) { //차월청구미수예정금액 > 0
                            nmnBilUcExpAmtTmp = nmnBilUcExpAmt - thmBilCtrAmt;
                            if (nmnBilUcExpAmtTmp > 0) {
                                eotAtam = 0; //기말선수금액
                            } else if (tsmBilUcExpAmt > 0) { //차차월청구미수예정금액
                                tsmBilUcExpAmtTmp = tsmBilUcExpAmt - abs(nmnBilUcExpAmtTmp);
                                if (tsmBilUcExpAmtTmp > 0) {
                                    nmnBilUcExpAmtTmp = 0;
                                    eotAtam = 0; //기말선수금액
                                } else {
                                    nmnBilUcExpAmtTmp = 0;
                                    tsmBilUcExpAmtTmp = 0;
                                    eotUcAmt = 0;
                                    ovrCtrDpAmt = slCtrAmt - thmBilOcAmt - nmnBilUcExpAmt - tsmBilUcExpAmt; //초과조정입금금액
                                    eotAtam = slCtrAmt - thmBilOcAmt - nmnBilUcExpAmt - tsmBilUcExpAmt; //기말선수금액
                                }
                            } else {
                                ovrCtrDpAmt = slCtrAmt; //초과조정입금금액
                                eotAtam = thmBilCtrAmt; //기말선수금액
                            }
                        }
                    } else {
                        thmBilDpAmt = btdAtam + thmAtamDpAmt; //당월청구입금금액 = 선수기초금액 + 선수입금금액
                        if (thmBilCtrAmt > btdBilUcAmt + thmBilOcAmt - btdAtam + thmAtamDpAmt) {
                            ovrCtrDpAmt = btdBilUcAmt + thmBilOcAmt - btdAtam + thmAtamDpAmt; //초과조정입금금액 = 기초청구미수금액 + 당월청구발생금액 - 선수기초금액 + 선수입금금액
                            eotAtam = btdBilUcAmt + thmBilOcAmt - btdAtam + thmAtamDpAmt; //기말선수금 = 기초청구미수금액 + 당월청구발생금액 - 선수기초금액 + 선수입금금액
                            thmBilCtrAmtTmp = btdBilUcAmt + thmBilOcAmt - btdAtam + thmAtamDpAmt; //당월청구조정금액 = 기초청구미수금액 + 당월청구발생금액 - 선수기초금액 + 선수입금금액
                            eotBilUcAmt = btdBilUcAmt + thmBilOcAmt - thmBilDpAmt - thmBilCtrAmtTmp + thmBilSpmtAmt; //기말청구미수금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
                        } else {
                            thmBilCtrAmtTmp = thmBilCtrAmt;
                            eotBilUcAmt = btdBilUcAmt + thmBilOcAmt - thmBilDpAmt - thmBilCtrAmt + thmBilSpmtAmt; //기말청구미수금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
                        }
                    }
                } else { //후납
                    if (btdBilUcAmt <= btdAtam + thmAtamDpAmt) {
                        thmBilDpAmt = btdBilUcAmt; //당월청구입금금액
                        if (btdBilUcAmt + thmBilOcAmt <= slCtrAmt) { // 기초청구미수금액 + 당월청구발생금액 <= 당월청구조정금액
                            thmBilCtrAmtTmp = btdBilUcAmt + thmBilOcAmt; //당월청구조정금액 = 기초청구미수금액 + 당월청구발생금액
                        } else {
                            thmBilCtrAmtTmp = slCtrAmt; //당월청구조정금액 = 매출조정금액
                            if (btdBilUcAmt + thmBilOcAmt - thmBilCtrAmtTmp <= thmAtamDpAmt) { //기초청구미수금액 + 당월청구발생금액 - 당월청구조정금액 <= 당월선수금입금금액
                                thmBilDpAmt = btdBilUcAmt + thmBilOcAmt - thmBilCtrAmtTmp;//당월청구입금금액 = 기초청구미수금액 + 당월청구발생금액 - 당월청구조정금액
                            } else {
                                thmBilDpAmt = thmAtamDpAmt;//당월청구입금금액 = 당월선수금입금금액
                            }
                        }
                        if (nmnBilUcExpAmt > 0) { //차월청구미수예정금액 > 0
                            nmnBilUcExpAmtTmp = nmnBilUcExpAmt - slCtrAmt;
                            if (nmnBilUcExpAmtTmp > 0) {
                                eotAtam = 0; //기말선수금액
                            } else if (tsmBilUcExpAmt > 0) { //차차월청구미수예정금액
                                tsmBilUcExpAmtTmp = tsmBilUcExpAmt - abs(nmnBilUcExpAmtTmp);
                                if (tsmBilUcExpAmtTmp > 0) {
                                    nmnBilUcExpAmtTmp = 0;
                                    eotAtam = 0; //기말선수금액
                                } else {
                                    nmnBilUcExpAmtTmp = 0;
                                    tsmBilUcExpAmtTmp = 0;
                                    eotUcAmt = 0;
                                    ovrCtrDpAmt = slCtrAmt - thmBilOcAmt - nmnBilUcExpAmt - tsmBilUcExpAmt; //초과조정입금금액
                                    eotAtam = slCtrAmt - thmBilOcAmt - nmnBilUcExpAmt - tsmBilUcExpAmt; //기말선수금액
                                }
                            } else {
                                ovrCtrDpAmt = slCtrAmt; //초과조정입금금액
                                eotAtam = slCtrAmt; //기말선수금액
                            }
                        }
                    } else {
                        thmBilDpAmt = btdAtam + thmAtamDpAmt; //당월청구입금금액 = 선수기초금액 + 선수입금금액
                        if (slCtrAmt > btdBilUcAmt - btdAtam + thmAtamDpAmt) {
                            ovrCtrDpAmt = btdBilUcAmt - btdAtam + thmAtamDpAmt; //초과조정입금금액 = 기초청구미수금액 - 선수기초금액 + 선수입금금액
                            eotAtam = btdBilUcAmt - btdAtam + thmAtamDpAmt; //기말선수금 = 기초청구미수금액 - 선수기초금액 + 선수입금금액
                            thmBilCtrAmtTmp = btdBilUcAmt - btdAtam + thmAtamDpAmt; //당월청구조정금액 = 기초청구미수금액 - 선수기초금액 + 선수입금금액
                            eotBilUcAmt = btdBilUcAmt + thmBilOcAmt - thmAtamDpAmt - slCtrAmt + thmBilSpmtAmt; //기말청구미수금액 = 기초청구미수금액 - 당월청구입금금액 - 당월청구조정금액 + 당월청구추가금액
                        }
                    }
                }
                dvo.setThmBilDpAmt(String.valueOf(thmBilDpAmt));
                dvo.setEotAtam(String.valueOf(eotAtam));
                dvo.setEotBilUcAmt(String.valueOf(eotBilUcAmt));
                dvo.setThmBilCtrAmt(String.valueOf(thmBilCtrAmtTmp));
                dvo.setTsmBilUcExpAmt(String.valueOf(tsmBilUcExpAmtTmp));
                dvo.setNmnBilUcExpAmt(String.valueOf(nmnBilUcExpAmtTmp));
                dvo.setEotUcAmt(String.valueOf(eotUcAmt));
                // dvo.setOvrCtrDpAmt(String.valueOf(ovrCtrDpAmt));
            }
        }
        dvo.setOvrCtrDpAmt(String.valueOf(ovrCtrDpAmt));
    }

}
