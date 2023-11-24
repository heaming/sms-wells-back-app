package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.dto.WsnyProcPsDto.*;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyProcPsMapper;

import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * [W-SV-U-1301M01] 홈카드 - 처리 현황 Service
 * <pre>
 *
 * @author 김동엽
 * @since 2023.11.16
 */
@Service
@RequiredArgsConstructor
public class WsnyProcPsService {
    private final WsnyProcPsMapper mapper;

    /**
     * 홈카드 - 처리 현황 조회
     * @param dto
     * @return
     */
    public SearchRes selectProcPs(SearchReq dto) {
        // 파라미터로 받던 직책코드(rsbCd)가 HR1(임직원)코드를 가져와서 기준역할코드(baseRleCd)를 사용.
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String baseRleCd = session.getBaseRleCd();
        String rsbCd = "";

        if("W6020".equals(baseRleCd) || "W3010".equals(baseRleCd)) rsbCd = "W0603"; // 매니저
        else if("W6010".equals(baseRleCd)) rsbCd = "W0604";                         // 센터장
        else if("W3030".equals(baseRleCd)) rsbCd = "W0301";                         // 지점장

        SearchReq newDto = new SearchReq(rsbCd, dto.searchType());
        return mapper.selectProcPs(newDto);
    }

    /**
     * 홈카드 - 오늘/내일 배정건수 조회
     * @param dto
     * @return
     */
    public SearchCnt selectTodayTomorrowCnt(SearchReq dto){
        // 파라미터로 받던 직책코드(rsbCd)가 HR1(임직원)코드를 가져와서 기준역할코드(baseRleCd)를 사용.
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        String baseRleCd = session.getBaseRleCd();
        String rsbCd = "";

        if("W6020".equals(baseRleCd) || "W3010".equals(baseRleCd)) rsbCd = "W0603"; // 매니저
        else if("W6010".equals(baseRleCd)) rsbCd = "W0604";                         // 센터장
        else if("W3030".equals(baseRleCd)) rsbCd = "W0301";                         // 지점장

        SearchReq newDto = new SearchReq(rsbCd, "");
        return mapper.selectTodayTomorrowCnt(newDto);
    }
}
