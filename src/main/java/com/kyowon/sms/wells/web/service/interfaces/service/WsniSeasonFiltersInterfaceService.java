package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeasonFiltersInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniSeasonFiltersInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniSeasonFiltersInterfaceService {
    private final WsniSeasonFiltersInterfaceMapper mapper;

    private final MessageResourceService messageService;

    public List<WsniSeasonFiltersInterfaceDto.SearchRes> selectSeasonFilter(
        WsniSeasonFiltersInterfaceDto.SearchReq dto
    ) {
        List<WsniSeasonFiltersInterfaceDto.SearchRes> filterList = mapper.selectSeasonFilter(dto);

        if(CollectionUtils.isEmpty(filterList)){
            throw new BizException(messageService.getMessage("MSG_TXT_NOT_EXIST_QR")); //정보가 존재하지 않습니다
        }

        return filterList;
    }
}
