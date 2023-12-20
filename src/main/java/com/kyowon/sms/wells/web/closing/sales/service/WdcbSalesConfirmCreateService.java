package com.kyowon.sms.wells.web.closing.sales.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmCreateDvo;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmReceivingAndPayingDvo;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmSapMatDvo;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSlCnfmBasDvo;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbSalesConfirmCreateMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 매출확정생성 서비스(W-CL-S-0009)
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-04-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdcbSalesConfirmCreateService {

    private final WdcbSalesConfirmCreateMapper mapper;

    /**
     * 매출확정생성 서비스
     * @return processCount
     * @throws BizException 조회 결과가 없는 경우 Exception 처리
     */
    @Transactional
    public int createSalesConfirm(WdcbSalesConfirmCreateDvo dvo) throws BizException {
        if (StringUtils.isEmpty(dvo.getSlRcogClsfCd())) {
            throw new BizException("매출인식분류코드가 존재하지 않습니다.");
        }
        int processCount = 0;

        WdcbSlCnfmBasDvo inputDvo = new WdcbSlCnfmBasDvo();

        /* 1.매출확정일련번호 채번 */
        int slCnfmSn = mapper.selectSalesConfirmSerialNumber(dvo);
        /* 2.대표고객 매핑 */
        String dgCstId = mapper.selectDgCstId(dvo);
        /* 3.SAP상품구분코드 매핑 */
        String sapPdDvCd = StringUtils.isNotEmpty(mapper.selectSapPdDvCd(dvo)) ? mapper.selectSapPdDvCd(dvo) : "";
        /* 4. 렌탈등록비부가가치세(RENTAL_RGST_COST_VAT)  */
        int rentalRgstCostVat = (int)Math.floor(dvo.getRentalRgstCost() * 0.0909);
        /* 5. 이자부가가치세 (INT_VAT) : VO에 있는 정상이자금액 NON_INT_AMT * 0.0909 값을 넣어줌.  (소수점 TRUNC) */
        int intVat = (int)Math.floor(dvo.getNomIntAmt() * 0.0909);
        /* 6. SAP자재평가클래스값(SAP_MAT_EVL_CLSS_VAL) / SAP자재코드(SAP_MAT_CD) */
        String sapMatEvlClssVal = "";
        String sapMatCd = "";
        String saveGdsYn = "";
        WdcbSalesConfirmSapMatDvo edcbSapMatDvo = mapper.selectSapMat(dvo);
        if (!ObjectUtils.isEmpty(edcbSapMatDvo)) {
            sapMatEvlClssVal = edcbSapMatDvo.getSapMatEvlClssVal();
            sapMatCd = edcbSapMatDvo.getSapMatCd();
            /* 6-1.저장물품여부(SAVE_GDS_YN) */
            /*saveGdsYn = StringUtils.isNotEmpty(sapMatEvlClssVal)
                ? sapMatEvlClssVal.substring(0, 2).equals("Z7") ? "Y" : "N" : "";*/
            if (StringUtils.isNotEmpty(sapMatEvlClssVal)) {
                if (sapMatEvlClssVal.substring(0, 2).equals("Z7")) {
                    // SAP 자재평가클래스값의 앞의 두자리가 Z7 이면 저장품. 이때는 저장물품여부(SAVE_GDS_YN) 값을 Y로 셋팅
                    saveGdsYn = "Y";
                } else {
                    // 아니면 N 으로 셋팅
                    saveGdsYn = "N";
                }
            } else {
                // 아니면 N 으로 셋팅
                saveGdsYn = "";
            }
        }

        /* 7. SAP사업본부정보코드(SAP_BZ_HDQ_INF_CD) */
        String sapBzHdqInfCd = "0003";
        /* 8-0 . 추가할인금액 : 매출중지 시 매출의 30프로만 점유비매출로 매출금엑에 떠야하는 상황이라,  추가할인금액에 정상매출의 70프로를 띄워놓음.  */
        int spmtDscAmt = 0;
        /* W01 : 렌탈 2023-12-18 정승현P */
        if ("W01".equals(dvo.getSlRcogClsfCd()) && StringUtils.isNotEmpty(dvo.getSlStpYn())
            && "Y".equals(dvo.getSlStpYn())) {
            spmtDscAmt = (int)(Math.floor(((dvo.getNomSlAmt() - dvo.getNomDscAmt()) * 0.7) / 10) * 10)
                + dvo.getSpmtDscAmt();
        } else {
            spmtDscAmt = dvo.getSpmtDscAmt();
        }
        // log.info("spmtDscAmt:" + spmtDscAmt);
        /* 8. 매출금액 (SL_AMT) */
        int slAmt = dvo.getNomSlAmt() + dvo.getSpmtSlAmt() - dvo.getNomDscAmt() - spmtDscAmt
            - dvo.getSlCtrAmt(); // 정상매출금액 + 추가매출금액- 정상할인금액 - 추가할인금액 - 매출조정금액
        // log.info("slAmt:" + slAmt);
        /* 9. 부가가치세(VAT) */
        int vat = 0;
        String vatTpCd = mapper.selectVatTpCd(dvo.getPdCd());
        if (StringUtils.isNotEmpty(vatTpCd) && "10".equals(vatTpCd)) {
            vat = (int)Math.floor(slAmt * 0.0909);
        }

        /* 10. CO주문유형 */
        String ctrlOrdTpCd = StringUtils
            .isNotEmpty(mapper.selectCtrlOrdTpCd(sapPdDvCd, dvo.getSellInflwChnlDtlCd(), dvo.getOgTpCd()))
                ? mapper.selectCtrlOrdTpCd(sapPdDvCd, dvo.getSellInflwChnlDtlCd(), dvo.getOgTpCd()) : "";
        /* 11. 코스트센터코드, WBS코드, SAP목적자재코드 */
        /* ASIS의 ZS2200P 테이블에도 모든 값이 공백임. 공백 넣을것 */
        /* 12. SAP과세면세구분코드 */
        //String sapTxnDtfrCd = dvo.getPvdaAmt() > 0 ? "3" : vat > 0 ? "1" : "0";
        String sapTxnDtfrCd = "";
        if (dvo.getPvdaAmt() > 0) {
            // 현할차금액(PVDA_AMT) 있으면 3
            sapTxnDtfrCd = "3";
        } else {
            if (vat > 0) {
                // VAT있으면 1
                sapTxnDtfrCd = "1";
            } else {
                // 없으면 0
                sapTxnDtfrCd = "0";
            }
        }
        /* 13. SAP세금계산서발행기준코드 */
        String sapTxinvPblBaseCd = "";
        if (StringUtils.isNotEmpty(sapPdDvCd) && ("B1".equals(sapPdDvCd) || "B2".equals(sapPdDvCd))) {
            // SAP상품구분코드가 B1, B2 로 매핑되어있으면 빈칸
            sapTxinvPblBaseCd = "";
        } else {
            // 아니면 모두 4
            sapTxinvPblBaseCd = "4";
        }
        if ((StringUtils.isNotEmpty(sapPdDvCd) && "B3".equals(sapPdDvCd)) || dvo.getPvdaAmt() > 0) {
            // SAP상품구분코드가 B3 이면서 현찰차금액 PVDA_AMT > 0 이면 3
            sapTxinvPblBaseCd = "3";
        }
        /* 14. 물류배송방식코드, SAP플랜트코드, SAP저장위치값. */
        String lgstSppMthdCd = "";
        String sapPlntCd = "";
        String sapSaveLctCd = "";
        if (StringUtils.isNotEmpty(dvo.getRvpyYn()) && "Y".equals(dvo.getRvpyYn())) {
            WdcbSalesConfirmReceivingAndPayingDvo wdcbSalesConfirmReceivingAndPayingDvo = mapper
                .selectReceivingAndPaying(dvo);
            if (!ObjectUtils.isEmpty(wdcbSalesConfirmReceivingAndPayingDvo)) {
                lgstSppMthdCd = wdcbSalesConfirmReceivingAndPayingDvo.getSppMthdTpCd();
                sapPlntCd = wdcbSalesConfirmReceivingAndPayingDvo.getSapPlntCd();
                sapSaveLctCd = wdcbSalesConfirmReceivingAndPayingDvo.getSapSaveLctCd();
            }
        }

        /* 15. SAP매출유형코드 */
        String tempSellTpDtlCd = ""; /*판매유형상세코드*/
        String tempSlRcogClsfCd = ""; /*매출인식분류코드*/
        String sapSlTpCd = "";
        String slTpDvCd = "";
        String clssVal = "";
        String addConditionSlTp = "0";
        String addConditionBizDv = "0";
        String slpMapngCdv = "";

        // log.info("dvo.getSlRcogClsfCd().substring(1):" + dvo.getSlRcogClsfCd().substring(0, 1));
        tempSellTpDtlCd = dvo.getSlRcogClsfCd().substring(0, 1).equals("S") ? "ANY" : dvo.getSellTpDtlCd();

        tempSlRcogClsfCd = dvo.getSlRcogClsfCd().substring(0, 1).equals("W") ? "W" : dvo.getSlRcogClsfCd();

        if ("Y".equals(dvo.getRtngdYn())) {
            slTpDvCd = "2";
        } else {
            slTpDvCd = "1";
        }

        if (StringUtils.isNotEmpty(sapMatEvlClssVal)) {
            if ("Z1".equals(sapMatEvlClssVal.substring(0, 2))) {
                // SAP저장위치값 앞2자리가 Z1 이면 1
                clssVal = "1";
            } else if ("Z2".equals(sapMatEvlClssVal.substring(0, 2))) {
                // SAP저장위치값 앞2자리가 Z2 이면 2
                clssVal = "2";
            } else if ("Z7".equals(sapMatEvlClssVal.substring(0, 2))) {
                // SAP저장위치값 앞2자리가 Z7 이면 3
                clssVal = "3";
            } else {
                // SAP저장위치가 없으면 2로 셋팅
                clssVal = "2";
            }
        } else {
            // SAP저장위치가 없으면 1로 셋팅
            /* 1로 설정해야 값이 맞는것 같은데?? 2023-12-15 정승현P */
            clssVal = "1";
        }

        if ("N".equals(mapper.selectProductEnvrYn(dvo.getPdCd()))) {
            /* 환경가전이 아니면 모두 BH상품 적용. 2023-12-15 정승현P */
            addConditionSlTp = "3";
            addConditionBizDv = "0";
        } else if (dvo.getRentalRgstCost() > 0) {
            // 렌탈등록비(RENTAL_RGST_COST) 가 0보다 크면 1
            addConditionSlTp = "1";
            addConditionBizDv = "1";
        } else if (StringUtils.isNotEmpty(dvo.getLgstItmGdCd())
            && "E".equals(dvo.getLgstItmGdCd()) || "R".equals(dvo.getLgstItmGdCd())) {
            // 14일이내 취소건 : 물류품목등급코드(LGST_ITM_GD_CD) 가 E 또는 R 이면 2
            addConditionSlTp = "2";
            addConditionBizDv = "2";
        } else if (StringUtils.isNotEmpty(dvo.getCanDt()) || dvo.getSlCanAmt() != 0) {
            // VO의 취소일자(CAN_DT) 가 널이 아니거나,  또는 취소금액(SL_CAN_AMT) 이 0이 아닌경우.  4
            addConditionSlTp = "4";
            addConditionBizDv = "4";
        } else {
            // 모두에 해당하지 않으면 0
            addConditionSlTp = "0";
            addConditionBizDv = "0";
        }

        /* 렌탈 상품소분류가 원두(PDC000000000131) 인 경우, 정기구매(커피원두)로 강제 설정, 2023-12-15 정승현P */
        if ("W01".equals(dvo.getSlRcogClsfCd()) && "PDC000000000131".equals(dvo.getPdLclsfId())) {
            tempSellTpDtlCd = "63";
        }
        slpMapngCdv = mapper.selectSlpMapngCdv(tempSellTpDtlCd, tempSlRcogClsfCd, clssVal, slTpDvCd, addConditionSlTp);
        sapSlTpCd = StringUtil.isEmpty(slpMapngCdv) ? "ERR" : slpMapngCdv;

        String tmpSapBizDvCd = mapper.selectSapBizDvCd(tempSellTpDtlCd, tempSlRcogClsfCd, addConditionBizDv);
        /* 렌탈 상품소분류가 원두(PDC000000000131) 인 경우, 정기구매(커피원두)로 강제 설정, 2023-12-15 정승현P */
        if ("W01".equals(dvo.getSlRcogClsfCd()) && "PDC000000000131".equals(dvo.getPdLclsfId())) {
            tmpSapBizDvCd = "LNC49";
        }
        String sapBizDvCd = StringUtil.isEmpty(tmpSapBizDvCd) ? "ERR" : tmpSapBizDvCd;

        /* 매핑 값 셋팅 */
        inputDvo.setCntrNo(dvo.getCntrNo());
        inputDvo.setCntrSn(dvo.getCntrSn());
        inputDvo.setSlRcogDt(dvo.getSlRcogDt());
        inputDvo.setSlCnfmSn(slCnfmSn);
        inputDvo.setKwGrpCoCd(dvo.getKwGrpCoCd());
        inputDvo.setBzHdqDvCd(dvo.getBzHdqDvCd());
        inputDvo.setOgTpCd(dvo.getOgTpCd());
        inputDvo.setPrtnrNo(dvo.getPrtnrNo());
        inputDvo.setPdHclsfId(dvo.getPdHclsfId());
        inputDvo.setPdMclsfId(dvo.getPdMclsfId());
        inputDvo.setPdLclsfId(dvo.getPdLclsfId());
        inputDvo.setPdCd(dvo.getPdCd());
        inputDvo.setDgCstId(dgCstId);
        inputDvo.setCstNo(dvo.getCstNo());
        inputDvo.setCopnDvCd(dvo.getCopnDvCd());
        inputDvo.setBzrno(dvo.getBzrno());
        inputDvo.setSellTpCd(dvo.getSellTpCd());
        inputDvo.setSellTpDtlCd(dvo.getSellTpDtlCd());
        inputDvo.setSellInflwChnlDtlCd(dvo.getSellInflwChnlDtlCd());
        inputDvo.setSapPdDvCd(sapPdDvCd);
        inputDvo.setSellQty(dvo.getSellQty());
        inputDvo.setSellAmt(dvo.getSellAmt());
        inputDvo.setSellAmtVat(dvo.getSellAmtVat());
        inputDvo.setSellSplAmt(dvo.getSellSplAmt());
        inputDvo.setCntrTam(dvo.getCntrTam());
        inputDvo.setSubscAmt(dvo.getSubscAmt());
        inputDvo.setRentalRgstCost(dvo.getRentalRgstCost());
        inputDvo.setRentalRgstCostVat(rentalRgstCostVat);
        inputDvo.setRentalAmt(dvo.getRentalAmt());
        inputDvo.setRentalDscAmt(dvo.getRentalDscAmt());
        inputDvo.setRentalPtrm(dvo.getRentalPtrm());
        inputDvo.setRentalTn(dvo.getRentalTn());
        inputDvo.setIstmPcamAmt(dvo.getIstmPcamAmt());
        inputDvo.setIstmFeeLvyAmt(dvo.getIstmFeeLvyAmt());
        inputDvo.setIstmAmt(dvo.getIstmAmt());
        inputDvo.setIstmMcn(dvo.getIstmMcn());
        inputDvo.setMmIstmAmt(dvo.getMmIstmAmt());
        inputDvo.setDscAmt(dvo.getDscAmt());
        inputDvo.setCntramDpAmt(dvo.getCntramDpAmt());
        inputDvo.setBilDscAmt(dvo.getBilDscAmt());
        inputDvo.setOvrCtrDpAmt(dvo.getOvrCtrDpAmt());
        inputDvo.setPrmTn(dvo.getPrmTn());
        inputDvo.setTotPrmAmt(dvo.getTotPrmAmt());
        inputDvo.setPrmExpAmt(dvo.getPrmExpAmt());
        inputDvo.setPrmStrtY(dvo.getPrmStrtY());
        inputDvo.setPrmStrtMm(dvo.getPrmStrtMm());
        inputDvo.setPrmEndY(dvo.getPrmEndY());
        inputDvo.setPrmEndMm(dvo.getPrmEndMm());
        inputDvo.setPrmMcn(dvo.getPrmMcn());
        inputDvo.setPrmDscr(dvo.getPrmDscr());
        inputDvo.setPrmDscAmt(dvo.getPrmDscAmt());
        inputDvo.setPrmDpAmt(dvo.getPrmDpAmt());
        inputDvo.setPrmRfndAmt(dvo.getPrmRfndAmt());
        inputDvo.setPrmRplcAmt(dvo.getPrmRplcAmt());
        inputDvo.setPrmSlAmt(dvo.getPrmSlAmt());
        inputDvo.setNomSlAmt(dvo.getNomSlAmt());
        inputDvo.setSpmtSlAmt(dvo.getSpmtSlAmt());
        inputDvo.setNomDscAmt(dvo.getNomDscAmt());
        inputDvo.setSpmtDscAmt(spmtDscAmt);
        inputDvo.setSlCtrAmt(dvo.getSlCtrAmt());
        inputDvo.setSlCanAmt(dvo.getSlCanAmt());
        inputDvo.setSlStpYn(dvo.getSlStpYn());
        inputDvo.setCntrStlmFshDtm(dvo.getCntrStlmFshDtm());
        inputDvo.setCntrStrtdt(dvo.getCntrStrtdt());
        inputDvo.setCanDt(dvo.getCanDt());
        inputDvo.setSlDc(dvo.getSlDc());
        inputDvo.setSvAmt(dvo.getSvAmt());
        inputDvo.setNomIntAmt(dvo.getNomIntAmt());
        inputDvo.setIntVat(intVat);
        inputDvo.setMlgSlAmt(dvo.getMlgSlAmt());
        inputDvo.setOstrDtm(dvo.getOstrDtm());
        inputDvo.setSppDtm(dvo.getSppDtm());
        inputDvo.setIstDtm(dvo.getIstDtm());
        inputDvo.setReqdDtm(dvo.getReqdDtm());
        inputDvo.setSvDt(dvo.getSvDt());
        inputDvo.setPvdaOjPcam(dvo.getPvdaOjPcam());
        inputDvo.setPvdaAmt(dvo.getPvdaAmt());
        inputDvo.setSlRcogClsfCd(dvo.getSlRcogClsfCd());
        inputDvo.setSlRcogDvCd(dvo.getSlRcogDvCd());
        inputDvo.setLgstSppMthdCd(lgstSppMthdCd);
        inputDvo.setIostWareCd("");
        inputDvo.setLgstItmGdCd(dvo.getLgstItmGdCd());
        inputDvo.setReimPcsvCs(dvo.getReimPcsvCs());
        inputDvo.setPcsvReimAmt(dvo.getPcsvReimAmt());
        inputDvo.setSapMatEvlClssVal(sapMatEvlClssVal);
        inputDvo.setSapSlTpCd(sapSlTpCd);
        inputDvo.setSapBizDvCd(sapBizDvCd);
        inputDvo.setSapBzHdqInfCd(sapBzHdqInfCd);
        inputDvo.setSlAmt(slAmt);
        inputDvo.setVat(vat);
        inputDvo.setIostDt(dvo.getIostDt());
        inputDvo.setCtrlOrdTpCd(ctrlOrdTpCd);
        inputDvo.setSapMatCd(sapMatCd);
        inputDvo.setSlQty(dvo.getSlQty());
        inputDvo.setRtngdYn(dvo.getRtngdYn());
        inputDvo.setFrisuYn(dvo.getFrisuYn());
        inputDvo.setCscnCd("");
        inputDvo.setWbsCd("");
        inputDvo.setSapPurpMatCd("");
        inputDvo.setFgptYn(dvo.getFgptYn());
        inputDvo.setSapTxnDtfrCd(sapTxnDtfrCd);
        inputDvo.setSapTxinvPblBaseCd(sapTxinvPblBaseCd);
        inputDvo.setRvpyYn(dvo.getRvpyYn());
        inputDvo.setSaveGdsYn(saveGdsYn);
        inputDvo.setSapPlntCd(sapPlntCd);
        inputDvo.setSapSaveLctVal(sapSaveLctCd);

        processCount += mapper.insertSalesConfirm(inputDvo);

        return processCount;
    }
}
