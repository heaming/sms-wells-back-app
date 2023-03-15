package com.kyowon.sms.wells.web.product.manage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcRoutineBsWorkMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcRoutineBsWorkMgtService {

    private final WpdcRoutineBsWorkMgtMapper mapper;

    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkRes> getRoutineBsWorks(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectRoutineBsWorks(dto);
    }

    public List<WpdcRoutineBsWorkMgtDto.SearchLifeCustomFiltersRes> getLifeCustomFilters(WpdcRoutineBsWorkMgtDto.SearchReq dto) {
        return mapper.selectLifeCustomFilters(dto);
    }

    public void saveRoutineBsWorks(WpdcRoutineBsWorkMgtDto.SaveReq dto, boolean isCreate) throws Exception {
        // TODO
        mapper.isnertRoutineBsWorkBase(null);
        mapper.isnertRoutineBsWorkDetail(null);
    }

    public void saveLifeCustomFilters(WpdcRoutineBsWorkMgtDto.SaveLifeCustomFilterReq dto, boolean isCreate) throws Exception {
        // TODO
        mapper.insertLifeCustomFilterBase(null);
    }

    public void removeRoutineBsWorksPdCd(String pdCd) {
        mapper.deleteRoutineBsWorkBaseByPdCd(pdCd);
        mapper.deleteRoutineBsWorkDtlByPdCd(pdCd);
        mapper.deleteLifeCustomFilterStdByPdCd(pdCd);
    }
}
