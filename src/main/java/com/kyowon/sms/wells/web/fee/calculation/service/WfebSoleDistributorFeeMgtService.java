package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.common.web.fee.schedule.service.ZfeyFeeScheduleMgtService;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebSoleDistributorFeeDvo;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebSoleDistributorFeeMgtMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * 총판 수수료 생성관리
 * </pre>
 *
 * @author mj
 * @since 2023.04.20
 */
@Service
@RequiredArgsConstructor
public class WfebSoleDistributorFeeMgtService {

    private final WfebSoleDistributorFeeMgtMapper mapper;

    private final ZfeyFeeScheduleMgtService service;

    /**
     * 총판수수료 생성관리 조회 - 수수료 실적
     * @param req
     * @return
     */
    public List<Performance> getDistributorPerformance(SearchPerformanceReq req) {
        // todo 실적테이블로 변경후 쿼리 수정
        return mapper.selectDistributorPerformance(req);
    }

    /**
     * 총판수수료 생성관리 조회 - 수수료
     * @param req
     * @return
     */
    public List<Fee> getDistributorFee(SearchFeeReq req) {
         return mapper.selectDistributorFee(req);
    }

    /**
     * 총판수수료 생성관리 저장(수수료)
     * @param dtos
     * @return
     */
    @Transactional
    public int editDistributorFee(SaveReq dtos) {
        int processCount = 0;
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        for (Fee row : dtos.changedRows()) {
            WfebSoleDistributorFeeDvo dvo = new WfebSoleDistributorFeeDvo();
            dvo.setBaseYm(row.baseYm());
            dvo.setPerfYm(row.baseYm());
            dvo.setOjDsbYm(row.baseYm());
            dvo.setCoCd(row.coCd());
            dvo.setOgTpCd("W05"); // 수수료 총판
            dvo.setPrtnrNo(row.prtnrNo());
            dvo.setFeeTcntDvCd(row.feeTcntDvCd()); // 2차
            dvo.setSpmtDsbDvCd("01"); // 정상지급
            dvo.setFeeCalcTpCd("01"); // 수수료 계산
            dvo.setDtaDlYn("N");
            /* W050002:장려수수료, W050003:인센티브, W050004:분기성과수수료, W050020:재지급수수료 */
            if (row.amtW050002() != null) { // 장려수수료
                dvo.setFeeCalcAmt(row.amtW050002());
                dvo.setFeeCtrCnfmAmt(row.amtW050002());
                dvo.setFeeCd("W050002");
                dvo.setDtaCrtFeeCd("W050002");
                processCount += mapper.updateCalcFee(dvo);
            }
            if (row.amtW050003() != null) { // 인센티브
                dvo.setFeeCalcAmt(row.amtW050003());
                dvo.setFeeCtrCnfmAmt(row.amtW050003());
                dvo.setFeeCd("W050003");
                dvo.setDtaCrtFeeCd("W050003");
                processCount += mapper.updateCalcFee(dvo);
            }
            if (row.amtW050004() != null) { // 분기성과
                dvo.setFeeCalcAmt(row.amtW050004());
                dvo.setFeeCtrCnfmAmt(row.amtW050004());
                dvo.setFeeCd("W050004");
                dvo.setDtaCrtFeeCd("W050004");
                processCount += mapper.updateCalcFee(dvo);
            }
            if (row.amtW050020() != null) { // 재지급
                dvo.setFeeCalcAmt(row.amtW050020());
                dvo.setFeeCtrCnfmAmt(row.amtW050020());
                dvo.setFeeCd("W050020");
                dvo.setDtaCrtFeeCd("W050020");
                processCount += mapper.updateCalcFee(dvo);
            }
        }
        return processCount;
    }

    /**
     * 총판수수료 생성관리 - 집계
     * @param req
     * @return
     */
    @Transactional
    public int aggregateDistributorPerformance(CreateReq req) throws Exception {
        int processCount = 0;
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        // 01. 기존 월마감 삭제
        // 01-1. 순주문파트너 월마감 삭제            : TB_FEAM_NTOR_MM_CL          -- 순주문파트너월마감
        processCount += mapper.deleteAggregateNtorMmCl(req);
        // 01-2. 순주문파트너 계약월마감 삭제         : TB_FEAM_NTORP_CNTR_MM_CL    -- 순주문파트너계약월마감
        processCount += mapper.deleteAggregateNtorCntrMmCl(req);
        // 01-3. 순주문파트너 실적월마감 삭제         : TB_FEAM_NTORP_PERF_MM_CL    -- 순주문파트너실적월마감
        processCount += mapper.deleteAggregateNtorPerfMmCl(req);

        // 02. 월마감 생성
        // 02-1. 순주문파트너 월마감 생성            : TB_FEAM_NTOR_MM_CL          -- 순주문파트너월마감
        processCount += mapper.insertAggregateNtorMmCl(req);

        // 03. 개인 월마감 생성
        // 03-1. 순주문파트너 계약월마감 개인판매 생성 : TB_FEAM_NTORP_CNTR_MM_CL    -- 순주문파트너계약월마감
        processCount += mapper.insertAggregateNtorCntrMmCl(req);
        // 03-2. 순주문파트너 실적월마감 개인판매 생성 : TB_FEAM_NTORP_PERF_MM_CL    -- 순주문파트너실적월마감
        processCount += mapper.insertAggregateNtorPerfMmCl(req);

        // 04. 순주문파트너 계약월마감 지점 생성      : TB_FEAM_NTORP_PERF_MM_CL    -- 순주문파트너실적월마감
        processCount += mapper.insertAggregateNtorPerfPointMmCl(req);

        // 05. 순주문파트너 월마감 상태 변경         : TB_FEAM_NTOR_MM_CL          -- 순주문파트너월마감
        processCount += mapper.updateAggregateNtorMmCl(req);

        // 06. 수수료일정 갱신 API 호출 공통모듈
        // @TODO 세션 coCd[session.getCompanyCode()] 관련해서 업무별로 말이 다달라서 하드코딩함 -_-;
        String feeSchdId = req.perfYm() + "501" + (StringUtil.isEmpty(req.feeTcntDvCd()) ? "02" : req.feeTcntDvCd()) + "2000"; // 기준일+총판+2차수+회사코드
        service.editStepLevelStatus(feeSchdId, "W0501", "03");
        return processCount;
    }

}
