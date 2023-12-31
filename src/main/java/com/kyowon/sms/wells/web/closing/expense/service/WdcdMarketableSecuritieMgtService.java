package com.kyowon.sms.wells.web.closing.expense.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdMarketableSecuritieMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdMarketableSecuritieDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdMarketableSecuritieMgtMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdcdMarketableSecuritieMgtService {

    private final WdcdMarketableSecuritieMgtMapper mapper;
    private final WdcdMarketableSecuritieMgtConverter converter;

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
    public Map<String, Object> saveSettlementWithholdingTax(List<SaveReq> reqs) {
        int count = 0;
        SaveReq firstReq = reqs.get(0);
        //1. 해당월 확정완료 여부 체크
//        String cnfmYn = mapper.selectCheckWhetherMonthFinalized(firstReq);
//        //2. 1번에서 CNFM_YN 결과값이 'Y'이면 alert 띄운다.
//        if ("Y".equals(cnfmYn)) {
//            BizAssert.isTrue(false, "해당 월은 등록/수정 불가합니다.");
//        }
        //3. 카드 정보/금액 조회 (ASIS : getAccCardInfoDetail)
        AccCardInfoDetailRes res = mapper.selectAccCardInfoDetail(firstReq);
        if(res == null) { throw new BizException("data error : selectAccCardInfoDetail"); }
        WdcdMarketableSecuritieDvo masterDvo = converter.mapAccCardInfoDetailResToWdcdMarketableSecuritieDvo(res);
        masterDvo.setAdjOgId(res.ogId());
        masterDvo.setOgTpCd(firstReq.ogTpCd());
        masterDvo.setPdstOpt(firstReq.pdstOpt() != null ? firstReq.pdstOpt() : "03");
        Integer dstAmtTotal = 0;
        for (SaveReq saveReq : reqs) {
            dstAmtTotal += Integer.valueOf(saveReq.dstAmt());
        }
        masterDvo.setDstAmt(dstAmtTotal.toString());

        if (!StringUtils.isEmpty(firstReq.opcsAdjNo())) { //4. 부모에서 전달받은 OPCS_ADJ_NO(운영비정산번호)가 값이 있으면 원천세 정산 수정 진행
            masterDvo.setOpcsAdjNo(firstReq.opcsAdjNo());
            mapper.deleteAccDetail(masterDvo);
            mapper.updateAccMst(masterDvo);
        } else { // 5. 3번에서 OPCS_ADJ_NO(운영비정산번호)가 값이 없으면 원천세 정산 신규 등록 진행
            masterDvo.setOpcsAdjNo(mapper.selectOpcsAdjNo(masterDvo));
            mapper.insertAccMst(masterDvo);
        }

        //3-2. 상세 저장
        for (SaveReq req : reqs) {
            WdcdMarketableSecuritieDvo dvo = converter.mapSaveReqToWdcdMarketableSecuritieDvo(req);
            dvo.setOpcsAdjNo(masterDvo.getOpcsAdjNo());
            if (Integer.valueOf(dvo.getDstAmt()) != 0) {
                mapper.insertAccDetail(dvo);
            }
        }

        masterDvo.setOpcsCardUseIzId(firstReq.opcsCardUseIzId());
        mapper.insertAccMap(masterDvo); //7. 매핑정보 등록 (ASIS : insertAccMap). 정산번호와 운영비사용카드내역 아이디를 매핑

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("opcsAdjNo", masterDvo.getOpcsAdjNo()); //운영비정산번호
        resultMap.put("count", count);
        return resultMap;
    }
}
