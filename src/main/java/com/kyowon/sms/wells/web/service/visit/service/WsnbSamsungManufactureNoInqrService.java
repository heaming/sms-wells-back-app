package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSamsungManufactureNoInqrDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSamsungManufactureNoInqrDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbSamsungManufactureNoInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbSamsungManufactureNoInqrService {
    private final WsnbSamsungManufactureNoInqrMapper mapper;

    /**
     * 삼성 제조번호 페이징 조회
     * @param dto
     * @param pageInfo
     * @return PagingResult
     */
    public PagingResult<SearchRes> getSamsungManufactureNoInqrPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSamsungManufactureNoInqrs(dto, pageInfo);
    }

    /**
     * 삼성 제조번호 조회(엑셀 다운로드)
     * @param dto
     * @return List
     */
    public List<SearchRes> getSamsungManufactureNoInqrs(SearchReq dto) {
        return mapper.selectSamsungManufactureNoInqrs(dto);
    }
}
