package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.common.web.fee.schedule.dto.ZfeyFeeScheduleMgtDto;
import com.kyowon.sms.common.web.fee.schedule.service.ZfeyFeeScheduleMgtService;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebB2bFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebB2bDtlFeeDvo;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebB2bFeeDvo;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebB2bFeeMgtMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apiguardian.api.API;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * B2B 수수료 생성관리
 * </pre>
 *
 * @author mj
 * @since 2023.05.08
 */
@Service
@RequiredArgsConstructor
public class WfebB2bFeeMgtService {

    private final WfebB2bFeeMgtMapper mapper;

    private final ZfeyFeeScheduleMgtService service;

    /**
     * B2b수수료 생성관리 조회 - 수수료 실적
     * @param req
     * @return
     */
    public List<Performance> getB2bPerformance(SearchPerformanceReq req) {
        return mapper.selectB2bPerformance(req);
    }

    /**
     * B2b수수료 생성관리 조회 - 수수료
     * @param req
     * @return
     */
    public List<Fee> getB2bFee(SearchFeeReq req) {
         return mapper.selectB2bFee(req);
    }

    /**
     * B2b수수료 생성관리 저장(수수료)
     * @param dtos
     * @return
     */
    @Transactional
    public int editB2bFee(SaveReq dtos) {
        int processCount = 0;
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        for (Fee row : dtos.changedRows()) {
            WfebB2bFeeDvo dvo = new WfebB2bFeeDvo();
            dvo.setBaseYm(row.baseYm());
            dvo.setPerfYm(row.baseYm());
            dvo.setOjDsbYm(row.baseYm());
            dvo.setCoCd(row.coCd());
            dvo.setOgTpCd("W04"); // 수수료 B2B
            dvo.setPrtnrNo(row.prtnrNo());
            dvo.setFeeTcntDvCd(row.feeTcntDvCd()); // 2차
            dvo.setSpmtDsbDvCd("01"); // 정상지급
            dvo.setFeeCalcTpCd("01"); // 수수료 계산
            dvo.setDtaDlYn("N");
            /* W040005:알선수수료, W040004:프로모션, W040020:재지급, W040003:인센티브 */
            if (row.amtW040005() != null) { // 알선수수료
                 dvo.setFeeCalcAmt(row.amtW040005());
                dvo.setFeeCtrCnfmAmt(row.amtW040005());
                dvo.setFeeCd("W040005");
                dvo.setDtaCrtFeeCd("W040005");
                processCount += mapper.updateCalcFee(dvo);
            }
            if (row.amtW040004() != null) { // 프로모션
                dvo.setFeeCalcAmt(row.amtW040004());
                dvo.setFeeCtrCnfmAmt(row.amtW040004());
                dvo.setFeeCd("W040004");
                dvo.setDtaCrtFeeCd("W040004");
                processCount += mapper.updateCalcFee(dvo);
            }
            if (row.amtW040020() != null) { // 재지급
                dvo.setFeeCalcAmt(row.amtW040020());
                dvo.setFeeCtrCnfmAmt(row.amtW040020());
                dvo.setFeeCd("W040020");
                dvo.setDtaCrtFeeCd("W040020");
                processCount += mapper.updateCalcFee(dvo);
            }
            if (row.amtW040003() != null) { // 인센티브
                dvo.setFeeCalcAmt(row.amtW040003());
                dvo.setFeeCtrCnfmAmt(row.amtW040003());
                dvo.setFeeCd("W040003");
                dvo.setDtaCrtFeeCd("W040003");
                processCount += mapper.updateCalcFee(dvo);
            }
            // 공제
            if (row.amt01() != null) { // 공제 보증예치금
                WfebB2bDtlFeeDvo dtlDvo = new WfebB2bDtlFeeDvo();
                dtlDvo.setDdtnYm(row.baseYm());
                dtlDvo.setCoCd(row.coCd());
                dtlDvo.setOgTpCd("W04");
                dtlDvo.setFeeDdtnTpCd("01"); // 수수료공제유형코드 : 공제-보증예치금
                dtlDvo.setFeeTcntDvCd("02"); // 2차
                dtlDvo.setSpmtDsbDvCd("01"); // 정상지급
                dtlDvo.setFeeDdtnCrtCd("01"); // 수수료공제유형코드 : 공제-보증예치금
                dtlDvo.setFeeCtrOgTpCd(session.getOgTpCd());
                dtlDvo.setFeeCtrPrtnrNo(row.prtnrNo());
                dtlDvo.setFeeDdctam(row.amt01());
                dtlDvo.setFeeDdtnCnfmAmt(row.amt01());
                dtlDvo.setFeeCtrRsonCn(row.amt01Cn());
                processCount += mapper.updateCalcDtlFee(dtlDvo);
            }
        }
        return processCount;
    }

    /**
     * B2b수수료 생성관리 - 집계
     * @param req
     * @return
     */
    @Transactional
    public int aggregateB2bPerformance(CreateReq req) throws Exception {
        int processCount = 0;
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        // 00. B2B 순주문집계 가능여부 체크
        // 00-1. 수수료일정 갱신 API 체크 > 화면에서해서 일단 페스
        // 00-2. 순주문파트너월마감 확정여부 체크
        int checkCount = mapper.selectCheckB2bConfrim(req);
        BizAssert.isFalse(checkCount > 0, "MSG_ALT_CNFM_NO_RENEW_DATA"); // 확정된 DATA는 갱신이 불가능합니다.

        // 01. B2B 기존 순주문집계 삭제
        // 01-1. 순주문파트너월마감 삭제
        processCount += mapper.deleteAggregateNtorMmCl(req);
        // 01-2. 순주문파트너실적월마감 삭제 (개인, 조직)
        processCount += mapper.deleteAggregateNtorPerfMmCl(req);
        // 01-3. 순주문파트너계약월마감 삭제
        processCount += mapper.deleteAggregateNtorCntrMmCl(req);

        // 02. B2B 순주문파트너월마감 생성
        processCount += mapper.insertAggregateNtorMmCl(req);

        // 03. B2B 순주문파트너계약월마감 생성
        processCount += mapper.insertAggregateNtorCntrMmCl(req);

        // 04. B2B 순주문파트너실적월마감 생성
        // 04-1. 순주문파트너실적월마감 - 개인판매 생성
        processCount += mapper.insertAggregateNtorPerfMmCl(req);
        // 04-2. 순주문파트너실적월마감 - 조직 생성
        processCount += mapper.insertOgAggregateNtorPerfMmCl(req);

        // 06. 수수료일정 갱신 API 호출 공통모듈
        String feeSchdId = req.perfYm() + "401" + (StringUtil.isEmpty(req.feeTcntDvCd()) ? "02" : req.feeTcntDvCd()) + "2000"; // 기준일+B2b+2차수+회사코드
        service.editStepLevelStatus(feeSchdId, "W0401", "03");
        return processCount;
    }

}
