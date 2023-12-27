package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.sales.converter.WdcbBusinessAtamAdjustMgtConverter;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.*;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbBusinessAtamAdjustDvo;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbBusinessAtamAdjustMgtMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbBusinessAtamAdjustMgtService {
    private final WdcbBusinessAtamAdjustMgtMapper mapper;
    private final WdcbBusinessAtamAdjustMgtConverter converter;

    /**
     * 대표고객코드 조회
     * @return
     */
    public List<SearchSapPdDvCdRes> getSapPdDvCds() {
        return mapper.selectSapPdDvCds();
    }

    /**
     * 영업선수금 정산 관리(집계)
     * @param dto
     * @return
     */
    public List<SearchTotalRes> getBusinessAtamTotals(SearchReq dto) {
        return mapper.selectBusinessAtamTotals(dto);
    }

    /**
     * 영업선수금 정산 관리(집계) 페이징
     * @param dto
     * @return
     */
    public PagingResult<SearchTotalRes> getBusinessAtamTotalsPaging(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectBusinessAtamTotals(dto, pageInfo);
    }

    /**
     * 영업선수금 정산 관리(상세)
     * @param dto
     * @return
     */
    public List<SearchDetailRes> getBusinessAtamDetails(SearchReq dto) {
        return mapper.selectBusinessAtamDetails(dto);
    }

    /**
     * 영업선수금 정산 관리(상세) 페이징
     * @param dto
     * @return
     */
    public PagingResult<SearchDetailRes> getBusinessAtamDetailsPaging(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectBusinessAtamDetails(dto, pageInfo);
    }

    /**
     * 채권반제 조회
     * @param sapAlrpySlpno
     * @return
     */
    public List<SearchSlpnoRes> getSapAlrpySlpnos(String sapAlrpySlpno) {
        return mapper.selectSapAlrpySlpnos(sapAlrpySlpno);
    }

    /**
     * 전표 초기화
     * @param dtos
     * @return
     */
    @Transactional
    public int saveSlpnoInitializes(List<SaveSlpnoReq> dtos) {
        int processCount = 0;
        SaveSlpnoReq dto = dtos.get(0);
        WdcbBusinessAtamAdjustDvo dvo = converter.mapSaveSlpnoReqToWdcbBusinessAtamAdjustDvo(dto);
        int result = mapper.updateAllRepaymentBase(dvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        mapper.updateDepositSlip(dvo);

        processCount += result;
        return processCount;
    }
}
