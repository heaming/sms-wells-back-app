package com.kyowon.sms.wells.web.closing.expense.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritieExceptionMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieExceptionDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdMarketableSecuritieExceptionMgtMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdcdMarketableSecuritieExceptionMgtService {

    private final WdcdMarketableSecuritieExceptionMgtMapper mapper;
    private final WdcdMarketableSecuritieExceptionMgtConverter converter;

    public List<FindCodeRes> getBuilDingCd(FindCodeReq req) {
        return mapper.selectBuilDingCd(req);
    }

    public List<SearchSubjectRes> getSubject(SearchSubjectReq req) {
        return mapper.selectSubject(req);
    }

    public List<SearchFinalSettlementRes> getFinalWithholdingTaxSettlement(SearchFinalSettlementReq req) {
        return mapper.selectFinalWithholdingTaxSettlement(req);
    }

    @Transactional
    public int saveSettlementWithholdingTax(List<SaveReq> reqs) {
        int count = 0;
        //1. 해당월 확정완료 여부 체크
        String cnfmYn = mapper.selectCheckWhetherMonthFinalized(reqs.get(0));

        //2. 1번에서 CNFM_YN 결과값이 'Y'이면 alert 띄운다.
        if ("Y".equals(cnfmYn)) {
            BizAssert.isTrue(false, "해당 월은 등록/수정 불가합니다."); // TODO 메세지 처리
        }

        //3. 카드 정보/금액 조회 (ASIS : getAccCardInfoDetail)
        AccCardInfoDetailRes res = mapper.selectAccCardInfoDetail(reqs.get(0));
        WdcdMarketableSecuritieExceptionDvo masterDvo = converter.mapAccCardInfoDetailResToWdcdMarketableSecuritieExceptionDvo(res);
        masterDvo.setAdjOgId(res.ogId());
        masterDvo.setOgTpCd(reqs.get(0).ogTpCd());
        masterDvo.setPdstOpt(reqs.get(0).pdstOpt() != null ? reqs.get(0).pdstOpt() : "03");
        Integer dstAmtTotal = 0;
        for (SaveReq saveReq : reqs) {
            dstAmtTotal += Integer.valueOf(saveReq.dstAmt());
        }
        masterDvo.setDstAmt(dstAmtTotal.toString());

        if (!StringUtils.isEmpty(res.opcsAdjNo())) { //4. 3번에서 OPCS_ADJ_NO(운영비정산번호)가 값이 있으면 원천세 정산 수정 진행
            count += mapper.deleteAccDetail(masterDvo);
            count += mapper.updateAccMst(masterDvo);
        } else { // 5. 3번에서 OPCS_ADJ_NO(운영비정산번호)가 값이 없으면 원천세 정산 신규 등록 진행
            masterDvo.setOpcsAdjNo(mapper.selectOpcsAdjNo(masterDvo));
            mapper.insertAccMst(masterDvo);
        }

        for (SaveReq req : reqs) {
            WdcdMarketableSecuritieExceptionDvo dvo = converter.mapSaveReqToWdcdMarketableSecuritieExceptionDvo(req);
            dvo.setOpcsAdjNo(masterDvo.getOpcsAdjNo());
            if (Integer.valueOf(dvo.getDstAmt()) > 0) {
                mapper.insertAccDetail(dvo);
            }
        }

        mapper.updateOpcsCard(masterDvo); // 6. 카드정보 업데이트 (ASIS : updateOpcsCard). 운영비 정산카드내역
        masterDvo.setOpcsCardUseIzId(reqs.get(0).opcsCardUseIzId());
        mapper.insertAccMap(masterDvo); //7. 매핑정보 등록 (ASIS : insertAccMap). 정산번호와 운영비사용카드내역 아이디를 매핑
        return count;
    }
}
