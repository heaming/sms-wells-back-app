package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterFilterShpadrInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterFilterShpadrInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterFilterShpadrInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCenterFilterShpadrInterfaceService {

    private final WsniCenterFilterShpadrInterfaceMapper mapper;

    private final MessageResourceService messageService;

    public List<SearchRes> getFilterShpadrs(SearchReq dto) {
        List<SearchRes> searchRes = mapper.selectFilterShpadrs(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException(messageService.getMessage("MSG_TXT_NO_DATA_RM"));
        }

        return searchRes;
    }
}
