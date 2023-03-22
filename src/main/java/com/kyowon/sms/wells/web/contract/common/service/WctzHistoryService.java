package com.kyowon.sms.wells.web.contract.common.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.converter.WctzHistoryConverter;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzHistoryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;

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
}
