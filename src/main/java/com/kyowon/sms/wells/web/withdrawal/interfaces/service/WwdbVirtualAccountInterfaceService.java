package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbVirtualAccountInterfaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbVirtualAccountInterfaceService {

    private final WwdbVirtualAccountInterfaceMapper mapper;

    public List<SearchRes> getVirtualAccountIssues(SearchReq dto) {
        return mapper.selectVirtualAccountIssues(dto);
    }
}
