
package com.kyowon.sms.wells.web.product.standard.service;

import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.product.standard.converter.WpdyHealthAllianceMgtConverter;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyHealthAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;
import com.kyowon.sms.wells.web.product.standard.mapper.WpdyHealthAllianceMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdyHealthAllianceMgtService {

    private final WpdyHealthAllianceMgtConverter converter;
    private final WpdyHealthAllianceMgtMapper mapper;

    public List<WpdyHealthAllianceMgtDto.SearchRes> getHealthAlliances(WpdyHealthAllianceMgtDto.SearchReq dto) {
        return mapper.selectHealthAlliances(dto);
    }

    public PagingResult<WpdyHealthAllianceMgtDto.SearchRes> getHealthAlliancePages(WpdyHealthAllianceMgtDto.SearchReq dto, PageInfo pageInfo) {
        return mapper.selectHealthAlliancePages(dto, pageInfo);
    }

    public int saveHealthAlliances(WpdyHealthAllianceMgtDto.SaveReq dto) throws Exception {
        int cnt = 0;
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dto.bases());
        for (WpdyAllianceBaseDvo base : bases) {
            if (StringUtil.isEmpty(base.getPdAlncmpBaseId())) {
                base.setPdAlncmpBaseId(mapper.selectHealthAllianceId());
            }
            cnt += mapper.mergeHealthAllianceBase(base);
        }
        return cnt;
    }

    public int removeHealthAlliances(List<WpdyHealthAllianceMgtDto.AllianceBase> dtos) {
        int cnt = 0;
        List<WpdyAllianceBaseDvo> bases = converter.mapAllAllianceBaseDtoToAllianceBaseDvo(dtos);
        for (WpdyAllianceBaseDvo base : bases) {
            cnt += mapper.deleteHealthAllianceBase(base);
        }
        return cnt;
    }

}
