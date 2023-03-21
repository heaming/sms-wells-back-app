package com.kyowon.sms.wells.web.product.manage.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.manage.converter.WpdcRoutineBsWorkMgtConverter;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcLifeCustomFilterBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkDetailDvo;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcRoutineBsWorkMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcRoutineBsWorkMgtService {

    private final WpdcRoutineBsWorkMgtConverter converter;
    private final WpdcRoutineBsWorkMgtMapper mapper;

    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> getRoutineBsWorkStandards(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectRoutineBsWorkBases(dto);
    }

    public PagingResult<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> getRoutineBsWorkStandardPages(WpdcRoutineBsWorkMgtDto.SearchStdBaseReq dto, PageInfo pageInfo) {
        return mapper.selectRoutineBsWorkBasePages(dto, pageInfo);
    }

    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkDetailRes> getRoutineBsWorkTasks(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectRoutineBsWorkDetails(dto);
    }

    public List<WpdcRoutineBsWorkMgtDto.SearchLifeCustomFiltersRes> getLifeCustomFilters(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectLifeCustomFilters(dto);
    }

    public int saveRoutineBsWorks(WpdcRoutineBsWorkMgtDto.EditReq dto) throws Exception {
        int cnt = 0;
        cnt += mapper.deleteRoutineBsWorkBase(dto.svPdCd(), dto.pdctPdCd());
        cnt += mapper.deleteRoutineBsWorkDetail(dto.svPdCd(), dto.pdctPdCd());
        //cnt += mapper.deleteLifeCustomFilterStd(dto.svPdCd(), dto.pdctPdCd(), null);
        List<WpdcRoutineBsWorkBaseDvo> bases = converter.mapAllBsWorkBaseDtoToBsWorkBaseDvo(dto.bases());
        if (CollectionUtils.isNotEmpty(bases)) {
            for (WpdcRoutineBsWorkBaseDvo base : bases) {
                cnt += mapper.isnertRoutineBsWorkBase(base);
            }
        }
        List<WpdcRoutineBsWorkDetailDvo> details = converter.mapAllBsWorkDetailDtoToBsWorkDetailDvo(dto.details());
        if (CollectionUtils.isNotEmpty(details)) {
            for (WpdcRoutineBsWorkDetailDvo detail : details) {
                cnt += mapper.isnertRoutineBsWorkDetail(detail);
            }
        }
        return cnt;
    }

    public int saveLifeFilters(WpdcRoutineBsWorkMgtDto.EditLifeFilterReq dto) throws Exception {
        int cnt = 0;
        List<WpdcLifeCustomFilterBaseDvo> filters = converter.mapAllLifeFltBaseDtoToLifeFltBaseDvo(dto.bases());
        for (WpdcLifeCustomFilterBaseDvo filter : filters) {
            cnt += mapper.mergeLifeCustomFilterBase(filter);
        }
        return cnt;
    }

    public int removeLifeFilters(List<WpdcRoutineBsWorkMgtDto.RemoveLifeFilterReq> dtos) {
        int cnt = 0;
        List<WpdcLifeCustomFilterBaseDvo> filters = converter.mapAllRemoveLifeFltBaseDtoToLifeFltBaseDvo(dtos);
        for (WpdcLifeCustomFilterBaseDvo filter : filters) {
            cnt += mapper.deleteLifeCustomFilterStd(filter);
        }
        return cnt;
    }

    public void removeRoutineBsWorksPdCd(String pdCd) {
        mapper.deleteRoutineBsWorkBase(pdCd, null);
        mapper.deleteRoutineBsWorkDetail(pdCd, null);
        mapper.deleteLifeCustomFilterStdByPdCd(pdCd);
    }
}
