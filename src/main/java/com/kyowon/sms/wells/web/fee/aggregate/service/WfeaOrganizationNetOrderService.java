package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOrganizationNetOrderDvo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.fee.common.dto.ZfezFeeNetOrderStatusDto.SearchRes;
import com.kyowon.sms.common.web.fee.common.dvo.ZfezFeeBatchStatusDetailsDvo;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeBatchStatusDetailsService;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeNetOrderStatusService;
import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaOrganizationNetOrderConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaOrganizationNetOrderMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 조직별 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfeaOrganizationNetOrderService {

    private final WfeaOrganizationNetOrderMapper mapper;

    private final ZfezFeeNetOrderStatusService zfezFeeNetOrderStatusService;

    private final WfeaOrganizationNetOrderConverter converter;

    private final BatchCallService batchCallService;

    private final ZfezFeeBatchStatusDetailsService zfezFeeBatchStatusDetailsService;

    /**
     * 조직별 실적 집계
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public String saveOrganizationAggregates(SaveOgNetOrderReq dto) throws Exception {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        // 주문별집계 확정 체크 추가
        SearchRes searchRes = zfezFeeNetOrderStatusService
            .getFeeNetOrderCntrStat(dto.perfYm(), dto.feeTcntDvCd(), "02");

        BizAssert
            .isTrue(
                searchRes != null && "02".equals(searchRes.ntorCnfmStatCd()),
                "MSG_ALT_CNFM_AFT_AGRG"
            ); // 해당 차수의 주문별 집계 확정 후 집계가 가능합니다.

        // 조직별집계 확정 체크
        SearchRes netOrderStat = zfezFeeNetOrderStatusService
            .getFeeNetOrderStat(dto.perfYm(), dto.feeTcntDvCd(), dto.perfAgrgCrtDvCd(), "02");

        BizAssert
            .isTrue(
                !(netOrderStat != null && "02".equals(netOrderStat.ntorCnfmStatCd())),
                "MSG_ALT_ALREADY_TCNT_ORD_AGRG_CNFM_BYO_AGRG_PSB"
            ); // 이미 해당 차수의 조직별 집계가 확정되어 실적 생성이 불가합니다.

        // 순주문 제품유형 체크(순주문월마감)
        int cnt = mapper.selectFeeNetOrderPdCnt(dto);
        BizAssert.isTrue(cnt == 0, "MSG_ALT_NO_PD_CD"); //유효하지 않은 제품유형이 포함되어 있습니다.

        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("perfYm", dto.perfYm());
        params.put("ogTpCd", dto.ogTpCd());
        params.put("feeTcntDvCd", dto.feeTcntDvCd());
        params.put("perfAgrgCrtDvCd", dto.perfAgrgCrtDvCd());
        params.put("userId", userSession.getEmployeeIDNumber());
        params.put("deptId", userSession.getDepartmentId());

        batchCallReqDvo.setJobKey("WSM_FE_OA0003");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        /*수수료배치상태내역 저장*/
        ZfezFeeBatchStatusDetailsDvo zfezFeeBatchStatusDetailsDvo = new ZfezFeeBatchStatusDetailsDvo();
        zfezFeeBatchStatusDetailsDvo.setBaseYm(dto.perfYm());
        zfezFeeBatchStatusDetailsDvo.setFeeTcntDvCd(dto.feeTcntDvCd());
        zfezFeeBatchStatusDetailsDvo.setFeeBatWkId(batchCallReqDvo.getJobKey());
        zfezFeeBatchStatusDetailsDvo.setFeeBatPrtcId(runId);
        zfezFeeBatchStatusDetailsDvo.setOgTpCd(dto.ogTpCd()); //조직유형
        zfezFeeBatchStatusDetailsDvo.setFeeBatTpCd("01"); //수수료배치유형코드 = 01 : 주문별배치-생성
        zfezFeeBatchStatusDetailsDvo.setFeeBatStatCd("01"); //수수료배치상태코드 = 01 : 시작

        zfezFeeBatchStatusDetailsService.createFeeBatchStatusDetails(zfezFeeBatchStatusDetailsDvo);

        return StringUtils.isNotBlank(runId) ? "S" : "E";
    }

    /**
     * 조직별 실적 확정
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int editOrganizationAggregates(SaveOgNetOrderReq dto) {
        int processCnt = 0;

        WfeaOrganizationNetOrderDvo dvo = converter.mapSaveOgNetOrderReqToWfeaOrganizationNetOrderDvo(dto);
        if ("CO".equals(dvo.getDv())) { // 확정
            SearchRes netOrderStat = zfezFeeNetOrderStatusService
                .getFeeNetOrderStat(dto.perfYm(), dto.feeTcntDvCd(), dto.perfAgrgCrtDvCd(), "02");
            BizAssert
                .isTrue(
                    !(netOrderStat != null && "02".equals(netOrderStat.ntorCnfmStatCd())), "MSG_ALT_BF_CNFM_CONF"
                ); // 이미 확정되었습니다.

            processCnt = mapper.updateNtorMmClConfirm(dvo);
        } else if ("CC".equals(dvo.getDv())) { // 확정취소
            processCnt = mapper.updateNtorMmClCancel(dvo);
        }

        return processCnt;
    }

    /**
     * WELLS 홈마스터 수수료 순주문 관리 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchHmstRes> getHomeMasters(
        SearchHmstReq dto
    ) {
        return this.mapper.selectHomeMasters(dto);
    }

    /**
     * WELLS 홈마스터 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchHmstFeeRes> getHomeMasterFees(
        SearchHmstReq dto
    ) {
        return this.mapper.selectHomeMasterFees(dto);
    }

    public List<SearchHmstFeeRes2> getHomeMasterFees2(
        SearchHmstReq dto
    ) {
        return this.mapper.selectHomeMasterFees2(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchMngerDetailRes> getManagerDetailOrders(
        SearchMngerDetailReq dto
    ) {
        return this.mapper.selectManagerDetailOrders(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchMngerAggregateRes> getManagerAggregateOrders(
        SearchMngerAggregateReq dto
    ) {
        return this.mapper.selectManagerAggregateOrders(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리 집계정보 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchMngerAgrgRes> getManagerStatusOrders(
        SearchMngerReq dto
    ) {
        return this.mapper.selectManagerStatusOrders(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchPlarRes> getPlanners(SearchPlarReq dto) {
        return this.mapper.selectPlanners(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchPlarSellFeeRes> getPlannerSellFees(
        SearchPlarReq dto
    ) {
        return this.mapper.selectPlannerFees(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리 집계정보 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<SearchPlarAgrgRes> getPlannerAggregation(
        SearchPlarReq dto
    ) {
        return this.mapper.selectPlannerAggregation(dto);
    }

}
