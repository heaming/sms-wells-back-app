package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyBeforeServiceBizClConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyBeforeServiceBizClDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyBeforeServiceBizClDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyBeforeServiceBizClMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnyBeforeServiceBizClService {

    private final WsnyBeforeServiceBizClMapper wsnyBeforeServiceBizClMapper;

    private final WsnyBeforeServiceBizClConverter wsnyBeforeServiceBizClConverter;

    public List<WsnyBeforeServiceBizClDto.SearchRes> getBusinessCloses(
        WsnyBeforeServiceBizClDto.SearchReq dto
    ) {
        if ("0".equals(dto.managementItem())) {
            return wsnyBeforeServiceBizClMapper.selectBusinessClosesForBeforeService(dto);
        } else {
            return wsnyBeforeServiceBizClMapper.selectBusinessClosesForEtc(dto);
        }
    }

    @Transactional
    public int saveBusinessCloses(List<WsnyBeforeServiceBizClDto.SaveReq> dtos) throws Exception {
        int processCnt = 0;

        for (WsnyBeforeServiceBizClDto.SaveReq dto : dtos) {
            WsnyBeforeServiceBizClDvo dvo = wsnyBeforeServiceBizClConverter
                .mapBeforeServiceBizClReqToBeforeServiceBizClDvo(dto);

            switch (dto.mngtItm()) {
                case "0" -> wsnyBeforeServiceBizClMapper.saveBusinessClosesForBeforeService(dvo);
                default -> wsnyBeforeServiceBizClMapper.saveBusinessClosesForEtc(dvo);
            }
            processCnt++;
        }

        return processCnt;
    }
}
