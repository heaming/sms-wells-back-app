package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFalseVisitRgstInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFalseVisitRgstInterfaceDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniFalseVisitRgstInterfaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniFalseVisitRgstInterfaceService {

    private final WsniFalseVisitRgstInterfaceMapper mapper;

    @Transactional
    public SaveRes createFalseVisit(SaveReq dto) {
        if (mapper.insertFalseVisit(dto) == 1) {
            return new SaveRes("Y");
        }
        return new SaveRes("N");
    }
}
