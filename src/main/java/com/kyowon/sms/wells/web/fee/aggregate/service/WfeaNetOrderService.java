package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sms.common.web.fee.common.dto.ZfezFeeNetOrderStatusDto;
import com.kyowon.sms.common.web.fee.common.dvo.ZfezFeeBatchStatusDetailsDvo;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeBatchStatusDetailsService;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeNetOrderStatusService;
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
    private final ZfezFeeNetOrderStatusService zfezFeeNetOrderStatusService;

    /**
     * WELLS 월순주문 집계 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchDetailRes> getNetDetailOrders(SearchDetailReq dto) {
        return this.mapper.selectNetDetailOrders(dto);
    }

    /**
     * WELLS 월순주문 집계 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchAggregateRes> getAggregateNetOrders(SearchAggregateReq dto) {
        return this.mapper.selectAggregateNetOrders(dto);
    }

    /**
     * WELLS 월순주문 수수료실적 집계대상 목록 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchStatusRes> getStatusNetOrders(SearchStatusReq dto) {
        return this.mapper.selectStatusNetOrders(dto);
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

        String jobKey = "WSM_FE_OA0005";

        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();

        ZfezFeeNetOrderStatusDto.SearchRes searchRes = zfezFeeNetOrderStatusService
            .getFeeNetOrderCntrStat(dto.perfYm(), dto.feeTcntDvCd(), "02");

        BizAssert
            .isTrue(!(searchRes != null && "02".equals(searchRes.ntorCnfmStatCd())), "MSG_ALT_CRSP_TCNT_ORD_AGRG_CNFM");

        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("perfYm", dto.perfYm());
        params.put("tcntDvCd", dto.feeTcntDvCd());
        params.put("userId", userSession.getEmployeeIDNumber());
        params.put("deptId", userSession.getDepartmentId());

        batchCallReqDvo.setJobKey(jobKey);
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        //        /*수수료배치상태내역 저장*/
        //        ZfezFeeBatchStatusDetailsDvo zfezFeeBatchStatusDetailsDvo = new ZfezFeeBatchStatusDetailsDvo();
        //        zfezFeeBatchStatusDetailsDvo.setBaseYm(dto.perfYm());
        //        zfezFeeBatchStatusDetailsDvo.setFeeTcntDvCd(dto.feeTcntDvCd());
        //        zfezFeeBatchStatusDetailsDvo.setFeeBatWkId(batchCallReqDvo.getJobKey());
        //        zfezFeeBatchStatusDetailsDvo.setFeeBatPrtcId(runId);
        //        zfezFeeBatchStatusDetailsDvo.setOgTpCd("W01"); //전체주문별배치라 의미x
        //        zfezFeeBatchStatusDetailsDvo.setFeeBatTpCd("01"); //수수료배치유형코드 = 01 : 주문별배치-생성
        //        zfezFeeBatchStatusDetailsDvo.setFeeBatStatCd("01"); //수수료배치상태코드 = 01 : 시작
        //
        //        zfezFeeBatchStatusDetailsService.createFeeBatchStatusDetails(zfezFeeBatchStatusDetailsDvo);

        return StringUtils.isNotBlank(runId) ? "S" : "E";
    }

    /**
     * WELLS 월순주문 집계 확정
     * @param 'SaveReq' 확정정보 수정 조건 정보
     * @return 저장 결과
     */

    @Transactional
    public int updateByNetOrders(SaveReq dto) throws Exception {

        String jobKey = "WSM_FE_OA0005";

        int processCount = 0;

        ZfezFeeNetOrderStatusDto.SearchRes searchRes = zfezFeeNetOrderStatusService
            .getFeeNetOrderCntrStat(dto.perfYm(), dto.feeTcntDvCd(), "02");

        BizAssert
            .isTrue(!(searchRes != null && "02".equals(searchRes.ntorCnfmStatCd())), "MSG_ALT_CRSP_TCNT_ORD_AGRG_CNFM");

        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("perfYm", dto.perfYm());
        params.put("tcntDvCd", dto.feeTcntDvCd());

        batchCallReqDvo.setJobKey(jobKey);
        batchCallReqDvo.setParams(params);

        String jobStatus = batchCallService.getLastestJobStatusByQuery(batchCallReqDvo);

        BizAssert.isTrue(("COMPLETED").equals(jobStatus), "MSG_ALT_ONDEMAND_ALREAY_EXECUTING");

        WfeaNetOrderDvo dvo = converter.mapSaveReqToWfeaNetOrderDvo(dto);

        processCount = mapper.updateNetOrders(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_CNFM_FAIL");

        return processCount;
    }

    /**
     * WELLS 월순주문 집계 미등록 유형 상품 데이터 조회
     * @param 'SearchReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchProductRes> getNetAggregateProducts(SearchProductReq dto) {
        return this.mapper.selectNetAggregateProducts(dto);
    }

    //    /**
    //     * WELLS 월순주문 집계 배치 진행상태 조회
    //     * @param 'SearchReq' 검색조건 정보
    //     * @return 조회된 데이터
    //     */
    //
    //    public String getEndOfBatch(SearchReq dto) {
    //        String jobStatus;
    //        try {
    //            String jobId = this.mapper.selectNetAggregateJobId(dto);
    //            jobStatus = batchCallService.getLastestJobStatus(jobId);
    //        } catch (Exception e) {
    //            return "Fail";
    //        }
    //        return jobStatus;
    //    }
}
