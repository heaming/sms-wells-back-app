package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncCenterLocalAreaTfConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncCenterLocalAreaTfDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncCenterLocalAreaTfDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncCenterLocalAreaTfMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncCenterLocalAreaTfService {
    private final WsncCenterLocalAreaTfMapper wsncCenterLocalAreaTfMapper;

    private final WsncCenterLocalAreaTfConverter wsncCenterLocalAreaTfConverter;

    public PagingResult<WsncCenterLocalAreaTfDto.SearchRes> getCenterAreas(
        WsncCenterLocalAreaTfDto.SearchReq dto, PageInfo pageInfo
    ) {
        return wsncCenterLocalAreaTfMapper.selectCenterAreas(dto, pageInfo);
    }

    @Transactional
    public int insertCenterAreas(List<WsncCenterLocalAreaTfDto.SaveReq> dtos) throws Exception {
        int processCnt = 0;
        for (WsncCenterLocalAreaTfDto.SaveReq dto : dtos) {
            WsncCenterLocalAreaTfDvo dvo = wsncCenterLocalAreaTfConverter.mapSaveReqToCenterLocalAreaTfDvo(dto);

            //insert (CH_SN 채번 후 insert)
            processCnt += wsncCenterLocalAreaTfMapper.insertCenterArea(dvo);
        }
        return processCnt;
    }

}
