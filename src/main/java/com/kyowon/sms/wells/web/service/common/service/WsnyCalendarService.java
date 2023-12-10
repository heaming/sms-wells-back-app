package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyCalendarConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyCalendarDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyCalendarDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyCalendarMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * [WSNY] Calendar 관리
 * </pre>
 *
 * @author  juno.cha
 * @since 2023-01-10
 */
@Service
@RequiredArgsConstructor
public class WsnyCalendarService {
    private final WsnyCalendarMapper wsnyCalendarMapper;

    private final WsnyCalendarConverter wsnyCalendarConverter;

    public List<WsnyCalendarDto.SearchRes> getCalendars(
        WsnyCalendarDto.SearchReq dto
    ) {
        return wsnyCalendarMapper.selectCalendar(dto);
    }

    public List<WsnyCalendarDto.SearchRes> getHomeCardCalendars(
        WsnyCalendarDto.SearchReq dto
    ) {
        String ogId = wsnyCalendarMapper.selectServiceCenterOgId(dto);
        WsnyCalendarDto.SearchReq newDto = new WsnyCalendarDto.SearchReq(dto.baseYm(), dto.serviceCenterCd(), ogId);
        return wsnyCalendarMapper.selectCalendar(newDto);
    }

    @Transactional
    public int saveCalendar(WsnyCalendarDto.SaveRegReq dto) throws Exception {
        WsnyCalendarDvo dvo = wsnyCalendarConverter.mapCalendarReqToCalendarDvo(dto);

        wsnyCalendarMapper.updateCalendar(dvo); //Calendar 저장
        if(!"holiday".equals(dvo.getSvCnrOgId())){
            wsnyCalendarMapper.saveCalendar(dvo); //당직자 저장
        }
        return 1;
    }

    public WsnyCalendarDto.FindRegRes getCalendarDay(
        WsnyCalendarDto.FineRegReq dto
    ) {
        return wsnyCalendarMapper.selectCalendarDay(dto);
    }
}
