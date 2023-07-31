package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.fee.common.dto.ZfezFeeNetOrderStatusDto;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeNetOrderStatusService;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaBsFeeMgtDto;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaBsFeeMgtMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * BS 실적 및 수수료
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.22
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfeaBsFeeMgtService {

    private final WfeaBsFeeMgtMapper mapper;

    private final BatchCallService batchCallService;

    private final ZfezFeeNetOrderStatusService zfezFeeNetOrderStatusService;

    /**
     * BS 수수료 내역 조회
     * @param dto
     * @return 조회결과
     */
    public List<WfeaBsFeeMgtDto.SearchRes> getBsFees(
        WfeaBsFeeMgtDto.SearchReq dto
    ) {
        return mapper.selectBsFees(dto);
    }

    public String saveBsAggregates(WfeaBsFeeMgtDto.SaveReq dto) throws Exception {
        // 주문별집계 확정 체크 추가
        ZfezFeeNetOrderStatusDto.SearchRes searchRes = zfezFeeNetOrderStatusService
            .getFeeNetOrderCntrStat(dto.perfYm(), dto.feeTcntDvCd(), "02");

        BizAssert
            .isTrue(
                searchRes != null && "02".equals(searchRes.ntorCnfmStatCd()),
                "MSG_ALT_CNFM_AFT_AGRG"
            ); // 해당 차수의 주문별 집계 확정 후 집계가 가능합니다.

        // 조직별집계 확정 체크
        ZfezFeeNetOrderStatusDto.SearchRes netOrderStat = zfezFeeNetOrderStatusService
            .getFeeNetOrderStat(dto.perfYm(), dto.feeTcntDvCd(), dto.perfAgrgCrtDvCd(), "02");

        BizAssert
            .isTrue(
                !(netOrderStat != null && "02".equals(netOrderStat.ntorCnfmStatCd())),
                "MSG_ALT_ALREADY_TCNT_ORD_AGRG_CNFM_BYO_AGRG_PSB"
            ); // 이미 해당 차수의 조직별 집계가 확정되어 실적 생성이 불가합니다.

        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("perfYm", dto.perfYm());
        params.put("ogTpCd", dto.ogTpCd());
        params.put("clDvCd", dto.feeTcntDvCd());

        batchCallReqDvo.setJobKey("WSM_FE_OA0002");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        return StringUtils.isNotBlank(runId) ? "S" : "E";
    }

}
