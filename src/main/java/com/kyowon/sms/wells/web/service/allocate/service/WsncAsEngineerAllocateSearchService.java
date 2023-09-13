package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncAsEngineerAllocateSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncAsEngineerAllocateSearchDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncAsEngineerAllocateSearchConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsEngineerAllocateSearchDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncAsEngineerAllocateSearchMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0021M01 엔지니어 배정현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.06
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncAsEngineerAllocateSearchService {

    private final WsncAsEngineerAllocateSearchMapper mapper;
    private final WsncAsEngineerAllocateSearchConverter converter;

    /**
     * 엔지니어 배정현황 조회(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getAsEngineerAllocateSearchPages(
        SearchReq searchReq, PageInfo pageInfo
    ) {
        PagingResult<WsncAsEngineerAllocateSearchDto.SearchRes> pagingResult = converter
            .mapAllAsEngineerAllocateSearchSearchRes(
                mapper.selectAsEngineerAllocateSearchPages(searchReq, pageInfo)
            );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 엔지니어 배정현황 관리 조회
     * @param searchReq 조회조건
     * @return 조회결과
     */
    public List<SearchRes> getAsEngineerAllocateSearchExcelDownload(SearchReq searchReq) {

        return converter
            .mapAllAsEngineerAllocateSearchSearchRes(mapper.selectAsEngineerAllocateSearchPages(searchReq));
    }

}
