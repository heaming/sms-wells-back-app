package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaInstAssignSetConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstAssignSetDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstAssignSetDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaInstAssignSetMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaInstAssignSetService {
    private final WsnaInstAssignSetMapper mapper;
    private final WsnaInstAssignSetConverter converter;

    public PagingResult<WsnaInstAssignSetDto.SearchRes> getInstAssignSetListPages(
        WsnaInstAssignSetDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectInstAssignSet(dto, pageInfo);
    }

    public int createInstAssignSetDatas(List<WsnaInstAssignSetDto.CreateReq> dtos) {
        int cnt = 0;
        for (WsnaInstAssignSetDto.CreateReq dto : dtos) {
            WsnaInstAssignSetDvo dvo = this.converter.mapCreateReqToInstAssignSetDvo(dto);
            cnt += mapper.insertInstAssignSet(dvo);

        }

        return cnt;
    }

    public int removeInstAssignSetDatas(List<WsnaInstAssignSetDto.RemoveReq> dtos) {
        int processCount = 0;

        for (WsnaInstAssignSetDto.RemoveReq dto : dtos) {
            WsnaInstAssignSetDvo dvo = converter.mapRemoveReqToInstAssignSetDvo(dto);
            int result = mapper.deleteInstAssignSet(dvo);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }
}
