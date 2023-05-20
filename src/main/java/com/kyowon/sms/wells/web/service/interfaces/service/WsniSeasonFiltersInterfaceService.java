package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
        try{
            /*
             * code review 가이드로 Optional 로 로직 변경
             * (추후 정상적으로 동작하지 않을 시, CollectionUtils.isEmpty() 로 체크로직 변경 필요)
             */
            return mapper.selectSeasonFilter(dto).orElseThrow(() -> new BizException(messageService.getMessage("MSG_TXT_NOT_EXIST_QR"))); //정보가 존재하지 않습니다
        } catch(Exception e) {
            e.printStackTrace();
            throw new BizException(messageService.getMessage("MSG_TXT_FILTER_SEARCH_ERROR")); //상품필터정보조회 오류
        }
    }
}
