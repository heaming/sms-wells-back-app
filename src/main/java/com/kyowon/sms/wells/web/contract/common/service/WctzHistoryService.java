package com.kyowon.sms.wells.web.contract.common.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.contract.zcommon.constants.CtContractConst;
import com.kyowon.sms.wells.web.contract.common.converter.WctzHistoryConverter;
import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzHistoryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzHistoryService {
    private final WctzHistoryMapper mapper;
    private final WctzHistoryConverter converter;

    /**
     * 계약상세변경이력(최신) 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return
     */
    public WctzCntrDetailChangeHistDvo getContractDetailChangeHistory(String cntrNo, int cntrSn) {
        return mapper.selectCntrDetailChangeHist(cntrNo, cntrSn);
    }

    /**
     * 계약상세변경이력 생성
     * @param dvo 이력정보
     */
    public void createContractDetailChangeHistory(WctzCntrDetailChangeHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzCntrDetailChangeHistDvo newHist = converter.convertCntrDetailToChangeHist(
            dvo,
            mapper.selectCntrDetailForHist(dvo.getCntrNo(), dvo.getCntrSn())
        );

        WctzCntrDetailChangeHistDvo befHist = getContractDetailChangeHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrDetailChangeHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrDetailChangeHist(newHist);
    }

    /**
     * 계약상세상태변경이력(최신) 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return
     */
    public WctzCntrDtlStatChangeHistDvo getContractDetailStatChangeHistory(String cntrNo, int cntrSn) {
        return mapper.selectCntrDetailStatChangeHist(cntrNo, cntrSn);
    }

    /**
     * 계약상세상태변경이력 생성
     * @param dvo 이력정보 (코드, 사유코드, 메모내용)
     */
    public void createContractDetailStatChangeHistory(WctzCntrDtlStatChangeHistDvo dvo) {
        BizAssert.hasText(dvo.getCntrNo(), "MSG_ALT_CHK_CNTR_NO");
        BizAssert.hasText(dvo.getCntrDtlStatCd(), "MSG_ALT_CHK_CNTR_DTL_STAT_CD");
        if (0 == dvo.getCntrSn()) {
            throw new BizException("MSG_ALT_CHK_CNTR_SN");
        }

        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }
        WctzCntrDtlStatChangeHistDvo befHist = getContractDetailStatChangeHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrDetailStatChangeHist(befHist);
        }
        dvo.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrDetailStatChangeHist(dvo);
    }

    /**
    * 세금계산서접수기준변경이력 생성
    * @param dvo 계약정보 (계약번호, 계약일련번호)
    */
    public void createTaxInvoiceReceiptBaseChangeHistory(WctzTxinvRcpBaseChangeHistDvo dvo) {
        BizAssert.hasText(dvo.getCntrNo(), "MSG_ALT_CHK_CNTR_NO");
        if (0 == dvo.getCntrSn()) {
            throw new BizException("MSG_ALT_CHK_CNTR_SN");
        }

        WctzTxinvRcpBaseChangeHistDvo newHist = converter
            .convertTaxInvoiceReceiptBaseToChangeHist(
                dvo, mapper.selectTaxInvoiceReceiptBase(dvo.getCntrNo(), dvo.getCntrSn())
            );
        mapper.insertTaxInvoiceReceiptBaseHist(newHist);
    }

    /**
     * 계약변경접수변경이력(최신) 조회
     * @param cntrChRcpId 계약변경접수ID
     * @return
     */
    public WctzCntrChRcchStatChangeHistDvo getContractChangeRcchStatChangeHistory(String cntrChRcpId) {
        return mapper.selectCntrChRcchStatChangeHist(cntrChRcpId);
    }

    /**
     * 계약변경접수변경이력 생성
     * @param dvo 이력정보 (계약변경요청내용, 계약변경진행상태코드, 계약변경진행메모내용)
     */
    public void createContractChangeRcchStatChangeHistory(WctzCntrChRcchStatChangeHistDvo dvo) {
        BizAssert.hasText(dvo.getCntrChRcpId(), "MSG_ALT_CHK_CNTR_CH_RCP_ID");
        BizAssert.hasText(dvo.getCntrChPrgsStatCd(), "MSG_ALT_CHK_CNTR_CH_PRGS_STAT_CD");

        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }
        WctzCntrChRcchStatChangeHistDvo befHist = getContractChangeRcchStatChangeHistory(dvo.getCntrChRcpId());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrChRcchStatChangeHist(befHist);
        }
        dvo.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrChRcchStatChangeHist(dvo);
    }

    /**
     * 계약기본변경이력(최신) 조회
     * @param cntrNo 계약번호
     * @return
     */
    public WctzCntrBasicChangeHistDvo getContractBasicChangeHistory(String cntrNo) {
        return mapper.selectCntrBasicChangeHist(cntrNo);
    }

    /**
     * 계약기본변경이력 생성
     * @param dvo 이력정보
     */
    public void createContractBasicChangeHistory(WctzCntrBasicChangeHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzCntrBasicChangeHistDvo newHist = converter.convertCntrBasicToChangeHist(
            dvo,
            mapper.selectCntrBasicChangeHist(dvo.getCntrNo())
        );

        WctzCntrBasicChangeHistDvo befHist = getContractBasicChangeHistory(dvo.getCntrNo());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrBasicChangeHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrBasicChangeHist(newHist);
    }

    /**
    * 관계사제휴계약(최신) 조회
    * @param acmpalCntrId 관계사제휴계약ID
    * @return
    */
    public WctzAcmpalContractIzHistDvo getAcmpalContractIzChangeHistory(String acmpalCntrId) {
        return mapper.selectAcmpalContractIzHist(acmpalCntrId);
    }

    /**
     * 관계사제휴계약내역 생성
     * @param dvo 이력정보
     */
    public void createAcmpalContractIzChangeHistory(WctzAcmpalContractIzHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzAcmpalContractIzHistDvo newHist = converter.convertAcmpalCntrIzToChangeHist(
            dvo,
            mapper.selectAcmpalContractIzForHist(dvo.getAcmpalCntrId())
        );

        WctzAcmpalContractIzHistDvo befHist = getAcmpalContractIzChangeHistory(dvo.getAcmpalCntrId());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateAcmpalContractIzChangeHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertAcmpalContractIzChangeHist(newHist);
    }

    /**
    * 계약WELLS상세(최신) 조회
    * @param cntrNo 계약번호
    * @param cntrSn 계약일련번호
    * @return
    */
    public WctzContractWellsDetailHistDvo getContractWellsDetailChangeHistory(String cntrNo, int cntrSn) {
        return mapper.selectContractWellsDetailHist(cntrNo, cntrSn);
    }

    /**
     * 계약WELLS상세이력 생성
     * @param dvo 이력정보
     */
    public void createContractWellsDetailChangeHistory(WctzContractWellsDetailHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzContractWellsDetailHistDvo newHist = converter.convertCntrWellsDetailToChangeHist(
            dvo,
            mapper.selectContractWellsDetailForHist(dvo.getCntrNo(), dvo.getCntrSn())
        );

        WctzContractWellsDetailHistDvo befHist = getContractWellsDetailChangeHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            // 최초변경
            newHist.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateContractWellsDetailHist(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertContractWellsDetailHist(newHist);
    }

    /**
     * 계약가격산출변경이력 조회
     *
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return dvo
     */
    public WctzCntrPrccchHistDvo getCntrPrccchHistory(String cntrNo, int cntrSn) {
        return mapper.selectCntrPrccchHistory(cntrNo, cntrSn);
    }

    /**
     * 계약가격산출변경이력 생성
     *
     * @param dvo
     */
    @Transactional
    public void createCntrPrccchHistory(WctzCntrPrccchHistDvo dvo) {
        String now = DateUtil.todayNnow();
        if (StringUtil.isEmpty(dvo.getHistStrtDtm())) {
            dvo.setHistStrtDtm(now);
        }

        WctzCntrPrccchHistDvo newHist = converter.convertPrcCmptToHist(
            dvo,
            mapper.selectCntrPrcCmptForHist(dvo.getCntrNo(), dvo.getCntrSn())
        );

        WctzCntrPrccchHistDvo befHist = getCntrPrccchHistory(dvo.getCntrNo(), dvo.getCntrSn());
        if (ObjectUtils.isEmpty(befHist)) {
            dvo.setHistStrtDtm(now);
        } else {
            befHist.setHistEndDtm(dvo.getHistStrtDtm());
            mapper.updateCntrPrccchHistory(befHist);
        }

        newHist.setHistEndDtm(CtContractConst.END_DTM);
        mapper.insertCntrPrccchHistory(newHist);
    }
}
