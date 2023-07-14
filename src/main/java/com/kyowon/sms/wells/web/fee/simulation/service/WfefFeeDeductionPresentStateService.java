package com.kyowon.sms.wells.web.fee.simulation.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefFeeDeductionPresentStateMapper;

import static com.kyowon.sms.wells.web.fee.simulation.dto.WfefFeeDeductionPresentStateDto.*;

import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfefFeeDeductionPresentStateService {

    private final WfefFeeDeductionPresentStateMapper mapper;

    public PagingResult<SearchRes> getFeeDeductionPresentStatePages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectFeeDeductionPresentStatePages(dto, pageInfo);
    }

    public List<SearchRes> getFeeDeductionPresentStatesForExcelDownload(SearchReq dto) {
        return mapper.selectFeeDeductionPresentStatePages(dto);
    }
}
