
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
import com.sds.sflex.common.utils.StringUtil;
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
        if (CollectionUtils.isNotEmpty(dto.bases())) {
            List<WpdcRoutineBsWorkBaseDvo> bases = converter.mapAllBsWorkBaseDtoToBsWorkBaseDvo(dto.bases());
            for (WpdcRoutineBsWorkBaseDvo base : bases) {
                cnt += mapper.isnertRoutineBsWorkBase(base);
            }
        }
        if (CollectionUtils.isNotEmpty(dto.details())) {
            List<WpdcRoutineBsWorkDetailDvo> details = converter.mapAllBsWorkDetailDtoToBsWorkDetailDvo(dto.details());
            for (WpdcRoutineBsWorkDetailDvo detail : details) {
                cnt += mapper.isnertRoutineBsWorkDetail(detail);
            }
        }
        return cnt;
    }

    public int saveRoutineBsWorkDetails(WpdcRoutineBsWorkMgtDto.EditDetailReq dto) throws Exception {
        int cnt = 0;
        List<WpdcRoutineBsWorkDetailDvo> details = converter.mapAllBsWorkDetailDtoToBsWorkDetailDvo(dto.details());
        for (WpdcRoutineBsWorkDetailDvo detail : details) {
            cnt += mapper.updateRoutineBsWorkDetail(detail);
        }
        return cnt;
    }

    public int saveLifeFilters(WpdcRoutineBsWorkMgtDto.EditLifeFilterReq dto) throws Exception {
        int cnt = 0;
        List<WpdcLifeCustomFilterBaseDvo> filters = converter.mapAllLifeFltBaseDtoToLifeFltBaseDvo(dto.bases());
        for (WpdcLifeCustomFilterBaseDvo filter : filters) {
            cnt += mapper.mergeLifeCustomFilterBase(filter);
        }
        mapper.updateRoutineBsWorkDetailFilterYn(dto.svPdCd(), dto.pdctPdCd(), dto.partPdCd());
        return cnt;
    }

    public int removeRoutineBsWorkDetails(List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkDetail> dtos) {
        int cnt = 0;
        List<WpdcRoutineBsWorkDetailDvo> details = converter.mapAllBsWorkDetailDtoToBsWorkDetailDvo(dtos);
        for (WpdcRoutineBsWorkDetailDvo detail : details) {
            cnt += mapper.deleteRoutineBsWorkDetail(detail);
        }
        return cnt;
    }

    public int removeLifeFilters(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos) {
        int cnt = 0;
        List<WpdcLifeCustomFilterBaseDvo> filters = converter.mapAllRemoveLifeFltBaseDtoToLifeFltBaseDvo(dtos);
        String svPdCd = null;
        String pdctPdCd = null;
        String partPdCd = null;
        for (WpdcLifeCustomFilterBaseDvo filter : filters) {
            cnt += mapper.deleteLifeCustomFilterStd(filter);
            if (svPdCd == null) {
                svPdCd = filter.getSvPdCd();
                pdctPdCd = filter.getPdctPdCd();
                partPdCd = filter.getPartPdCd();
            }
        }
        mapper.updateRoutineBsWorkDetailFilterYn(svPdCd, pdctPdCd, partPdCd);
        return cnt;
    }

    public void removeRoutineBsWorksPdCd(String pdCd) {
        mapper.deleteRoutineBsWorkBase(pdCd, null);
        mapper.deleteRoutineBsWorkDetail(pdCd, null);
        mapper.deleteLifeCustomFilterStdByPdCd(pdCd);
    }

    public String checkLifeFilterDuplication(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos) {
        List<WpdcLifeCustomFilterBaseDvo> bases = converter.mapAllRemoveLifeFltBaseDtoToLifeFltBaseDvo(dtos);
        String duplicationKey = null;
        for (WpdcLifeCustomFilterBaseDvo base : bases) {
            duplicationKey = mapper.selectLifeFilterDuplication(base);
            if (StringUtil.isNotBlank(duplicationKey)) {
                break;
            }
        }
        return duplicationKey;
    }
}
