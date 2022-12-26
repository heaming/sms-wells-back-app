package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncFixationVisitConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncFixationVisitDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncFixationVisitMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncFixationVisitService {
    private final WsncFixationVisitMapper wwsncFixationVisitMgntMapper;

    private final WsncFixationVisitConverter wsncFixationVisitConverter;

    public PagingResult<WsncFixationVisitDto.SearchFixationVisitRes> getFixationVisits(
        WsncFixationVisitDto.SearchFixationVisitReq dto, PageInfo pageInfo
    ) {
        return wwsncFixationVisitMgntMapper.selectFixationVisits(dto, pageInfo);
    }

    public WsncFixationVisitDto.SearchFixationVisitRegRes getFixationVisit(
        WsncFixationVisitDto.SearchFixationVisitRegReq dto
    ) {
        return wwsncFixationVisitMgntMapper.selectFixationVisit(dto);
    }

    @Transactional
    public int saveFixationVisit(WsncFixationVisitDto.SaveFixationVisitRegReq dto) throws Exception {
        int processCnt = 0;
        WsncFixationVisitDvo dvo = wsncFixationVisitConverter.mapFixationVisitReqToFixationVisitDvo(dto);

        //update (USE_YN = 'N' setting)
        if (StringUtils.isNotEmpty(dvo.getChSn())) {
            wwsncFixationVisitMgntMapper.updateFixationVisit(dvo);
        }

        //insert (CH_SN 채번 후 insert)
        return wwsncFixationVisitMgntMapper.insertFixationVisit(dvo);
    }
}
