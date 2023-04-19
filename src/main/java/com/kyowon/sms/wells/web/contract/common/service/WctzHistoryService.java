package com.kyowon.sms.wells.web.contract.common.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.converter.WctzHistoryConverter;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrChRcchStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzTxinvRcpBaseChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzHistoryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzHistoryService {
    private final String HIST_STRT_DTM = "00000101000000";
    private final String HIST_END_DTM = "99991231235959";
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

        newHist.setHistEndDtm(HIST_END_DTM);
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
        dvo.setHistEndDtm(HIST_END_DTM);
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
        dvo.setHistEndDtm(HIST_END_DTM);
        mapper.insertCntrChRcchStatChangeHist(dvo);
    }
}
