package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WwfeaAccountNetIncreaseDto;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WwfeaAccountNetIncreaseMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * M조직 계정순증 관리
 * </pre>
 *
 * @author haejin lee.
 * @since 2023.07.10
 */
@Service
@RequiredArgsConstructor
public class WwfeaAccountNetIncreaseService {

    private final WwfeaAccountNetIncreaseMapper mapper;

    private final BatchCallService batchCallService;

    /**
     * M조직 계정순증 관리 - 검색 조건 조회
     * @param req
     * @return
     */
    public List<WwfeaAccountNetIncreaseDto.SearchRes> getAccountNetIncrease(
        WwfeaAccountNetIncreaseDto.SearchReq dto
    ) {
        List<WwfeaAccountNetIncreaseDto.SearchRes> result = new ArrayList<WwfeaAccountNetIncreaseDto.SearchRes>();

        if (dto.inqrDvCd().equals("01")) {
            result = mapper.selectLstmmCancels(dto);
        } else if (dto.inqrDvCd().equals("02")) {
            result = mapper.selectNewSells(dto);
        } else if (dto.inqrDvCd().equals("03")) {
            result = mapper.selectAggregateChecks(dto);
        }
        return result;
    }

    /**
     * M조직 계정순증 관리 - 계정순증 집계
     * @param SaveReq
     * @return 저장 결과
     */
    @Transactional
    public String aggregateAccountNetIncrease(WwfeaAccountNetIncreaseDto.SaveReq dto) throws Exception {
        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("perfYm", dto.perfYm());
        params.put("feeTcntDvCd", dto.feeTcntDvCd());

        batchCallReqDvo.setJobKey("WSM_FE_OA0004");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        return StringUtils.isNotBlank(runId) ? "S" : "E";
    }
}
