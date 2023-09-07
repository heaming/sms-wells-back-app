package com.kyowon.sms.wells.web.service.standard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.standard.converter.WsnyBusinessTypeWorkHourMgtConverter;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.standard.dvo.WsnyBusinessTypeWorkHourMgtSaveDvo;
import com.kyowon.sms.wells.web.service.standard.mapper.WsnyBusinessTypeWorkHourMgtMapper;
import com.kyowon.sms.wells.web.service.standard.mapper.WsnyBusinessTypeWorkHourSaveMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyBusinessTypeWorkHourMgtService {
    private final WsnyBusinessTypeWorkHourMgtMapper mapper;
    private final WsnyBusinessTypeWorkHourSaveMapper saveMapper;
    private final WsnyBusinessTypeWorkHourMgtConverter converter;

    public List<SearchRes> getBusinessTypeWorkHour(SearchReq dto) {
        return mapper.selectBusinessTypeWorkHour(dto);
    }

    public List<SearchRes> getBusinessTypeWorkHourExcelDownload(SearchReq dto) {
        return mapper.selectBusinessTypeWorkHour(dto);
    }

    public int saveSvpdBpdTpWkGrpIz(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WsnyBusinessTypeWorkHourMgtSaveDvo dvo = converter.mapSaveReqToBusinessTypeWorkHourDvo(dto);
            /// TODO 저장 로직
            int result = saveMapper.insertSvpdBpdTpWkGrpIz(dvo);

            processCount += result;
        }

        return processCount;
    }

}
