package com.kyowon.sms.wells.web.contract.orderstatus.service;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.kyowon.sms.wells.web.contract.orderstatus.converter.WctdExpiredRetentionCntrConverter;
import com.kyowon.sms.wells.web.contract.orderstatus.mapper.WctdExpiredRetentionCntrMapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctdExpiredRetentionCntrService {
    private final WctdExpiredRetentionCntrMapper mapper;
    private final WctdExpiredRetentionCntrConverter converter;

    @Transactional
    public List<SearchRes> setMshCntrInfo(List<SearchCntrRes> cntrs) {
        List<SearchRes> results = Lists.newArrayList();
        for (SearchCntrRes cntr : cntrs) {
            FindMshCntrRes mshCntrRes = mapper.getMembershipCntrInfo(
                cntr.cntrNo(), cntr.cntrSn(), Arrays.asList(CtContractConst.CntrStatCd.CANCELLATION.getDtlCds())
            );
            results.add(converter.mapCntrResAndMshCntrResToSearchRes(cntr, mshCntrRes));
        }
        return results;
    }

    @Transactional
    public PagingResult<SearchRes> getExpiredRetentionCntrPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchCntrRes> result = mapper.selectExpiredRetentionCntrPages(dto, pageInfo);
        return new PagingResult(setMshCntrInfo(result.getList()), result.getPageInfo());
    }

    @Transactional
    public List<SearchRes> getExpiredRetentionCntrsForExcelDownload(SearchReq dto) {
        List<SearchCntrRes> result = mapper.selectExpiredRetentionCntrPages(dto);
        return setMshCntrInfo(result);
    }
}
