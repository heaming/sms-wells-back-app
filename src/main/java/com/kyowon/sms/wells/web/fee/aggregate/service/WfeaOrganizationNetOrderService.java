package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.fee.common.dto.ZfezFeeNetOrderStatusDto.SearchRes;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeNetOrderStatusService;
import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaOrganizationNetOrderConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOrganizationNetOrderDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaOrganizationNetOrderMapper;
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

    /**
     * 조직별 실적 집계
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public String saveOrganizationAggregates(WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto) throws Exception {
        // 주문별집계 확정 체크 추가
        SearchRes searchRes = zfezFeeNetOrderStatusService
            .getFeeNetOrderCntrStat(dto.perfYm(), dto.feeTcntDvCd(), "02");

        BizAssert
            .isTrue(
                searchRes != null && "02".equals(searchRes.ntorCnfmStatCd()),
                "MSG_ALT_CNFM_AFT_AGRG"
            ); // 해당 차수의 주문별 집계 확정 후 집계가 가능합니다.

        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("perfYm", dto.perfYm());
        params.put("ogTpCd", dto.ogTpCd());
        params.put("feeTcntDvCd", dto.feeTcntDvCd());
        params.put("perfAgrgCrtDvCd", dto.perfAgrgCrtDvCd());

        batchCallReqDvo.setJobKey("WSM_FE_OA0003");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        return StringUtils.isNotBlank(runId) ? "S" : "E";
    }

    /**
     * 조직별 실적 확정
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int editOrganizationAggregates(WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto) {
        int processCnt = 0;

        // 조직별집계 생성 체크
        SearchRes netOrderStat = zfezFeeNetOrderStatusService
            .getFeeNetOrderStat(dto.perfYm(), dto.feeTcntDvCd(), dto.perfAgrgCrtDvCd(), "01");
        BizAssert.isTrue(netOrderStat != null, "MSG_ALT_OG_CNFM_AFT_AGRG"); // 해당 차수의 조직별 집계 후 진행해주세요.

        WfeaOrganizationNetOrderDvo dvo = converter.mapSaveOgNetOrderReqToWfeaOrganizationNetOrderDvo(dto);
        if ("CO".equals(dvo.getDv())) { // 확정
            BizAssert
                .isTrue(
                    !(netOrderStat != null && "01".equals(netOrderStat.ntorCnfmStatCd())), "MSG_ALT_BF_CNFM_CONF"
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
    public List<WfeaOrganizationNetOrderDto.SearchHmstRes> getHomeMasters(
        WfeaOrganizationNetOrderDto.SearchHmstReq dto
    ) {
        return this.mapper.selectHomeMasters(dto);
    }

    /**
     * WELLS 홈마스터 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchHmstFeeRes> getHomeMasterFees(
        WfeaOrganizationNetOrderDto.SearchHmstReq dto
    ) {
        return this.mapper.selectHomeMasterFees(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchMngerRes> getManagers(
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.mapper.selectManagers(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchMngerSellFeeRes> getManagerFees(
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.mapper.selectManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리 집계정보 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchMngerAgrgRes> getManagerAggregation(
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.mapper.selectManagerAggregation(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchPlarRes> getPlanners(WfeaOrganizationNetOrderDto.SearchPlarReq dto) {
        return this.mapper.selectPlanners(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchPlarSellFeeRes> getPlannerSellFees(
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.mapper.selectPlannerFees(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리 집계정보 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchPlarAgrgRes> getPlannerAggregation(
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.mapper.selectPlannerAggregation(dto);
    }

}
