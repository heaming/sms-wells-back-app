package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sms.common.web.fee.common.dvo.ZfezFeeBatchStatusDetailsDvo;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeBatchStatusDetailsService;
import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaNetOrderConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaNetOrderDvo;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNetOrderDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaNetOrderMapper;
import com.kyowon.sflex.common.common.service.BatchCallService;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

/**
 * <pre>
 * WELLS 월순주문 집계 서비스
 * </pre>
 *
 * @author gs.piit150
 * @since 2023-02-03
 */

@Service
@RequiredArgsConstructor
public class WfeaNetOrderService {
    private final WfeaNetOrderMapper mapper;
    private final WfeaNetOrderConverter converter;;
    private final BatchCallService batchCallService;

    private final ZfezFeeBatchStatusDetailsService zfezFeeBatchStatusDetailsService;

    /**
     * WELLS 월순주문 집계 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchRes> getNetOrders(SearchReq dto) {
        return this.mapper.selectNetOrders(dto);
    }

    /**
     * WELLS 월순주문 집계 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchRes> getNetAggreateOrders(SearchReq dto) {
        return this.mapper.selectAggreateNetOrders(dto);
    }

    /**
     * WELLS 월순주문 수수료실적 집계대상 목록 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchFeeRes> getNetOrderFees(SearchReq dto) {
        return this.mapper.selectNetOrderFees(dto);
    }

    /**
     * WELLS 월순주문 수수료실적 집계 확정여부 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public SearchConfirmRes getNetAggregateConfirm(SearchReq dto) {
        return this.mapper.selectNetAggregateConfirm(dto);
    }

    /**
     * WELLS 월순주문 집계 생성
     * @param 'SaveReq' 삭제 및 저장 조건 정보
     *        1.계약별집계정보 삭제
     *        2.매니저 계약별 집계
     *        3.플래너 계약별 집계
     *        4.홈마스터 계약별 집계
     * @return 저장 결과
     */

    @Transactional
    public String saveByNetOrders(SaveReq dto) throws Exception {
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("perfYm", dto.perfYm());
        params.put("tcntDvCd", dto.feeTcntDvCd());
        params.put("userId", userSession.getEmployeeIDNumber());
        params.put("deptId", userSession.getDepartmentId());

        batchCallReqDvo.setJobKey("WSM_FE_OA0005");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        /*수수료배치상태내역 저장*/
        ZfezFeeBatchStatusDetailsDvo zfezFeeBatchStatusDetailsDvo = new ZfezFeeBatchStatusDetailsDvo();
        zfezFeeBatchStatusDetailsDvo.setBaseYm(dto.perfYm());
        zfezFeeBatchStatusDetailsDvo.setFeeTcntDvCd(dto.feeTcntDvCd());
        zfezFeeBatchStatusDetailsDvo.setFeeBatWkId(batchCallReqDvo.getJobKey());
        zfezFeeBatchStatusDetailsDvo.setFeeBatPrtcId(runId);
        zfezFeeBatchStatusDetailsDvo.setOgTpCd("W01"); //전체주문별배치라 의미x
        zfezFeeBatchStatusDetailsDvo.setFeeBatTpCd("01"); //수수료배치유형코드 = 01 : 주문별배치-생성
        zfezFeeBatchStatusDetailsDvo.setFeeBatStatCd("01"); //수수료배치상태코드 = 01 : 시작

        zfezFeeBatchStatusDetailsService.createFeeBatchStatusDetails(zfezFeeBatchStatusDetailsDvo);

        return StringUtils.isNotBlank(runId) ? "S" : "E";
    }

    /**
     * WELLS 월순주문 집계 확정
     * @param 'SaveReq' 확정정보 수정 조건 정보
     * @return 저장 결과
     */

    @Transactional
    public int updateByNetOrders(SaveReq dto) {

        int processCount = 0;

        WfeaNetOrderDvo dvo = converter.mapSaveReqToWfeaNetOrderDvo(dto);

        processCount = mapper.updateNetOrders(dvo);

        return processCount;
    }

    /**
     * WELLS 월순주문 집계 미등록 유형 상품 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchProductRes> getNetAggregateProducts(SearchReq dto) {
        return this.mapper.selectNetAggregateProducts(dto);
    }

    /**
     * WELLS 월순주문 집계 배치 진행상태 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public String getEndOfBatch(SearchReq dto) {
        String jobStatus;
        try {
            String jobId = this.mapper.selectNetAggregateJobId(dto);
            jobStatus = batchCallService.getLastestJobStatus(jobId);
        } catch (Exception e) {
            return "Fail";
        }
        return jobStatus;
    }
}
