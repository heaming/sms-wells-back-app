package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.Iterator;
import java.util.List;

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

/**
 * <pre>
 * 매출확정생성 서비스(W-CL-S-0009)
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-04-13
 */
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
    public int createSalesConfirm(List<WdcbSalesConfirmCreateDvo> dvos) throws BizException {
        int processCount = 0;
        Iterator<WdcbSalesConfirmCreateDvo> iterator = dvos.iterator();

        while (iterator.hasNext()) {
            WdcbSalesConfirmCreateDvo dvo = iterator.next();

            WdcbSlCnfmBasDvo inputDvo = new WdcbSlCnfmBasDvo();

            /* 1.매출확정일련번호 채번 */
            int slCnfmSn = mapper.selectSalesConfirmSerialNumber(dvo);
            /* 2.대표고객 매핑 */
            String dgCstId = mapper.selectDgCstId(dvo);
            /* 3.SAP상품구분코드 매핑 */
            /* 3-1. 판매유형에 필터가 들어온경우 (SELL_TP_CD = 9 )
            판매유형상세에 빈값이 들어올예정으로 이때 SELL_TP_DTL_CD (판매유형상세) 에 9 셋팅
             */
            if ("9".equals(dvo.getSellTpCd()))
                dvo.setSellTpDtlCd("9");
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
                saveGdsYn = StringUtils.isNotEmpty(sapMatEvlClssVal)
                    ? sapMatEvlClssVal.substring(0, 2).equals("Z7") ? "Y" : "N" : "";
            }

            /* 7. SAP사업본부정보코드(SAP_BZ_HDQ_INF_CD) */
            String sapBzHdqInfCd = "0003";
            /* 8. 매출금액 (SL_AMT) */
            int slAmt = dvo.getNomSlAmt() + dvo.getSpmtSlAmt() - dvo.getNomDscAmt() - dvo.getSpmtDscAmt()
                - dvo.getSlCtrAmt(); // 정상매출금액 + 추가매출금액- 정상할인금액 - 추가할인금액 - 매출조정금액
            /* 9. 부가가치세(VAT) */
            int vat = (int)Math.floor(slAmt * 0.0909);
            /* 10. CO주문유형 */
            String ctrlOrdTpCd = StringUtils
                .isNotEmpty(mapper.selectCtrlOrdTpCd(sapPdDvCd, dvo.getSellInflwChnlDtlCd(), dvo.getOgTpCd()))
                    ? mapper.selectCtrlOrdTpCd(sapPdDvCd, dvo.getSellInflwChnlDtlCd(), dvo.getOgTpCd()) : "";
            /* 11. 코스트센터코드, WBS코드, SAP목적자재코드 */
            /* ASIS의 ZS2200P 테이블에도 모든 값이 공백임. 공백 넣을것 */
            /* 12. SAP과세면세구분코드 */
            String sapTxnDtfrCd = dvo.getPvdaAmt() > 0 ? "3" : vat > 0 ? "1" : "0";
            /* 13. SAP세금계산서발행기준코드 */
            String sapTxinvPblBaseCd = "";
            if ("B1".equals(sapPdDvCd) || "B2".equals(sapPdDvCd)) {
                sapTxinvPblBaseCd = "";
            } else {
                sapTxinvPblBaseCd = "4";
            }
            if ("B1".equals(sapPdDvCd) || dvo.getPvdaAmt() > 0) {
                sapTxinvPblBaseCd = "3";
            }
            /* 14. 물류배송방식코드, SAP플랜트코드, SAP저장위치값. */
            String lgstSppMthdCd = "";
            String sapPlntCd = "";
            String sapSaveLctCd = "";
            String rvpyYn = "";
            WdcbSalesConfirmReceivingAndPayingDvo wdcbSalesConfirmReceivingAndPayingDvo = mapper
                .selectReceivingAndPaying(dvo);
            if (!ObjectUtils.isEmpty(wdcbSalesConfirmReceivingAndPayingDvo)) {
                lgstSppMthdCd = wdcbSalesConfirmReceivingAndPayingDvo.getSppMthdTpCd();
                sapPlntCd = wdcbSalesConfirmReceivingAndPayingDvo.getSapPlntCd();
                sapSaveLctCd = wdcbSalesConfirmReceivingAndPayingDvo.getSapSaveLctCd();
                rvpyYn = wdcbSalesConfirmReceivingAndPayingDvo.getCnt() > 0 ? "Y" : "N";
            }

            /* 15. SAP매출유형코드 */
            String sapSlTpCd = "";
            String slTpDvCd = "";
            String clssVal = "";
            String addCondition = "";
            String slpMapngCdv = "";
            if (StringUtils.isNotEmpty(dvo.getRtngdYn()) && "N".equals(dvo.getRtngdYn())) {
                slTpDvCd = "1";
            } else if (StringUtils.isNotEmpty(dvo.getRtngdYn()) && "Y".equals(dvo.getRtngdYn())) {
                slTpDvCd = "2";
            } else if (dvo.getPcsvReimAmt() > 0) {
                slTpDvCd = "3";
            } else if (dvo.getSlCanAmt() > 0) {
                slTpDvCd = "7";
            }
            if (StringUtils.isNotEmpty(sapSaveLctCd)) {
                if ("Z1".equals(sapSaveLctCd.substring(0, 2))) {
                    clssVal = "1";
                } else if ("Z2".equals(sapSaveLctCd.substring(0, 2))) {
                    clssVal = "2";
                } else if ("Z7".equals(sapSaveLctCd.substring(0, 2))) {
                    clssVal = "3";
                }
            }

            if (dvo.getRentalRgstCost() > 0) {
                addCondition = "1";
            } else if ((StringUtils.isNotEmpty(dvo.getLgstItmGdCd()) && "E".equals(dvo.getLgstItmGdCd()))
                || (StringUtils.isNotEmpty(dvo.getLgstItmGdCd()) && "R".equals(dvo.getLgstItmGdCd()))) {
                addCondition = "2";
            } else if ("6".equals(dvo.getSellTpCd()) && (StringUtils.isNotEmpty(rvpyYn) && "Y".equals(rvpyYn))) {
                addCondition = "3";
            } else {
                addCondition = "0";
            }

            slpMapngCdv = mapper.selectSlpMapngCdv(dvo.getSellTpDtlCd(), clssVal, slTpDvCd, addCondition);
            sapSlTpCd = StringUtil.isEmpty(slpMapngCdv) ? "ERR" : slpMapngCdv;

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
            inputDvo.setSpmtDscAmt(dvo.getSpmtDscAmt());
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
            inputDvo.setLgstSppMthdCd(lgstSppMthdCd);
            inputDvo.setIostWareCd("");
            inputDvo.setSapMatEvlClssVal(sapMatEvlClssVal);
            inputDvo.setSapSlTpCd(sapSlTpCd);
            inputDvo.setSapBizDvCd("");
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
            inputDvo.setRvpyYn(rvpyYn);
            inputDvo.setSaveGdsYn(saveGdsYn);
            inputDvo.setSapPlntCd(sapPlntCd);
            inputDvo.setSapSaveLctVal(sapSaveLctCd);

            processCount += mapper.insertSalesConfirm(inputDvo);

        }
        return processCount;
    }
}
