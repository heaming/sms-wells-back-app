package com.kyowon.sms.wells.web.closing.sales.service;

import java.text.ParseException;
import java.util.List;

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
        String kwGrpCoCd
    ) {
        return mapper.selectPop(kwGrpCoCd);
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
        log.info("saveSlipCreate:" + dvo);

        String baseYm = DateUtil.addMonths(DateUtil.getNowDayString(), -1).substring(0, 6);
        ZdcbPiaFeeReplaceSlipDvo slip = new ZdcbPiaFeeReplaceSlipDvo();
        slip.setBaseYm(baseYm);
        slip.setKwGrpCoCd(dvo.getKwGrpCoCd());
        createService.createPiaFeeReplaceSlipCreate(slip);

        int result = mapper.updatePop(dvo);
        BizAssert.isTrue(result > 1, "MSG_ALT_SVE_ERR");
        return result;
    }
}
