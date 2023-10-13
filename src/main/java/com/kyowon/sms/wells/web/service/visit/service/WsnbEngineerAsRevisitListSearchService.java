package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbEngineerAsRevisitListSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbEngineerAsRevisitListSearchDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbEngineerAsRevisitListSearchConverter;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbEngineerAsRevisitListSearchMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0233M01 A/S 재방문현황(엔지니어)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.10.11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbEngineerAsRevisitListSearchService {

    private final WsnbEngineerAsRevisitListSearchMapper mapper;
    private final WsnbEngineerAsRevisitListSearchConverter converter;

    /**
     * A/S 재방문현황(엔지니어) 조회(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getEngineerAsRevisitListSearchPages(
        SearchReq searchReq, PageInfo pageInfo
    ) {

        PagingResult<SearchRes> pagingResult = converter.mapWsnbEngineerAsRevisitListSearchRes(
            mapper.selectEngineerAsRevisitListSearchPages(searchReq, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * A/S 재방문현황(엔지니어) 조회(엑셀다운로드)
     * @param searchReq 조회조건
     * @return 조회결과
     */
    public List<SearchRes> getEngineerAsRevisitListSearchExcelDownload(SearchReq searchReq) {

        return converter
            .mapWsnbEngineerAsRevisitListSearchRes(mapper.selectEngineerAsRevisitListSearchPages(searchReq));
    }

}
