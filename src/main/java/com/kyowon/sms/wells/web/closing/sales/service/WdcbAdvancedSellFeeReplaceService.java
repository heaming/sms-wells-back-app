package com.kyowon.sms.wells.web.closing.sales.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.closing.sales.dvo.ZdcbPiaFeeReplaceSlipDvo;
import com.kyowon.sms.common.web.closing.sales.service.ZdcbPiaFeeReplaceSlipCreateService;
import com.kyowon.sms.wells.web.closing.sales.converter.WdcbAdvancedSellFeeReplaceConverter;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SaveReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchAggregateRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchAggregateSummaryRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchCodeRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchDivideRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchDtlSummaryRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchPopRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.CreateReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbAdvancedSellFeeReplaceDvo;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbAdvancedSellFeeReplaceMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbAdvancedSellFeeReplaceService {
    private final WdcbAdvancedSellFeeReplaceMapper mapper;
    private final WdcbAdvancedSellFeeReplaceConverter converter;

    private final ZdcbPiaFeeReplaceSlipCreateService createService;
    private final BatchCallService batchCallService;

    /**
    * 선급판매수수료 비용 대체 관리 - 조회
    * @param dto 검색조건
    * @return 선급판매수수료 비용 대체 관리 페이징 목록
    */
    public List<SearchAggregateRes> getAggregateLists(
        SearchReq dto
    ) {
        return mapper.selectAggregateLists(dto);
    }

    public List<SearchAggregateSummaryRes> getAggregateSummary(
        SearchReq dto
    ) {
        return mapper.selectAggregateSummary(dto);
    }

    public List<SearchRes> getDtlLists(
        SearchReq dto
    ) {
        return mapper.selectDtlLists(dto);
    }

    public List<SearchDtlSummaryRes> getDtlSummary(
        SearchReq dto
    ) {
        return mapper.selectDtlSummary(dto);
    }

    public List<SearchDivideRes> getDivideLists(
        SearchReq dto
    ) {
        return mapper.selectDivideLists(dto);
    }

    public List<SearchCodeRes> getFeeGubunCodes(

    ) {
        return mapper.selectFeeGubunCodes();
    }

    public SearchPopRes getPop(
        String kwGrpCoCd, String currentMonth
    ) {
        return mapper.selectPop(kwGrpCoCd, currentMonth);
    }

    /**
    * 선급판매수수료 비용 대체 관리 - 전표생성
    * @param dto 교원그룹회사코드
    * @return 생성 결과
    * @throws BizException 저장 실패할 경우 Exception 처리
    */
    @Transactional
    public int saveSlipCreate(SaveReq dto) throws ParseException {
        WdcbAdvancedSellFeeReplaceDvo dvo = converter.mapSaveReqToWdcbAdvancedSellFeeReplaceDvo(dto);
        ZdcbPiaFeeReplaceSlipDvo slip = new ZdcbPiaFeeReplaceSlipDvo();
        slip.setBaseYm(dvo.getBaseYm());
        slip.setKwGrpCoCd(dvo.getKwGrpCoCd());
        slip.setFeeTcnt(dvo.getFeeTcnt());
        createService.createPiaFeeReplaceSlipCreate(slip);

        log.info("saveSlipCreate:" + dvo);
        int result = mapper.updatePop(dvo);
        BizAssert.isTrue(result > 1, "MSG_ALT_SVE_ERR");
        return result;
    }

    @Transactional
    public String createCostReplace(CreateReq dto) throws Exception{
        String jobkey = "WSM_DC_OA0020";
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();
        batchCallReqDvo.setJobKey(jobkey);

        Map<String, String> params = new HashMap<>();
        params.put("beforeMonth", dto.beforeMonth());
        params.put("currentMonth", dto.currentMonth());
        params.put("feeTcnt", dto.feeTcnt());
        batchCallReqDvo.setParams(params);

        String oldBondBatchJobRunId = batchCallService.runJob(batchCallReqDvo); //결과값으로 Control-M 에서 run-id를 받는다.
        BizAssert.isTrue(StringUtils.isNotEmpty(oldBondBatchJobRunId), "MSG_ALT_SVE_ERR");

        return oldBondBatchJobRunId;
    }
}
