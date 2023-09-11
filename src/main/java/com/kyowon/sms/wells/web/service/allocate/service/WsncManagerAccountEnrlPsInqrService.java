package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagerAccountEnrlPsInqrDto.*;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncManagerAccountEnrlPsInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * <pre>
 * [K-W-SV-U-0300M01] 매니저 계정 및 재적현황 조회
 * </pre>
 *
 * @author gs.piit231
 * @since 2023.09.11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncManagerAccountEnrlPsInqrService {
    private final WsncManagerAccountEnrlPsInqrMapper mapper;

    public List<SearchGnrdvAgrgRes> getManagerAccountEnrlPss(SearchReq searchReq){
        return mapper.selectManagerAccountEnrlPss(searchReq);
    }

    public PagingResult<SearchMngerAccEnrlPsRes> getMngerAccEnrlPss(SearchReq searchReq, PageInfo pageInfo){
        PagingResult<SearchMngerAccEnrlPsRes> dtos = mapper.selectMngerAccEnrlPss(searchReq, pageInfo);
        return dtos;
    }

    public List<SearchMngerAccEnrlPsRes> getMngerAccEnrlPsExcelDownload(SearchReq searchReq){
        return mapper.selectMngerAccEnrlPss(searchReq);
    }
}
