package com.kyowon.sms.wells.web.service.visit.service;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappycallSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappycallSearchDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbMobileHappycallSearchConverter;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbMobileHappycallSearchMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0267M01 모바일 해피콜 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbMobileHappycallSearchService {

    private final WsnbMobileHappycallSearchMapper mapper;
    private final WsnbMobileHappycallSearchConverter converter;

    /**
     * 상품별 에러코드 관리 조회(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getMobileHappycallSearchPages(
        SearchReq searchReq, PageInfo pageInfo
    ) {

        PagingResult<SearchRes> pagingResult = converter.mapWsnbMobileHappycallSearchSearchRes(
            mapper.selectMobileHappycallSearchPages(searchReq, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 상품별 에러코드 관리 조회(페이징)
     * @param searchReq 조회조건
     * @return 조회결과
     */
    public List<SearchRes> getMobileHappycallSearchExcelDownload(SearchReq searchReq) {

        return converter.mapWsnbMobileHappycallSearchSearchRes(mapper.selectMobileHappycallSearchPages(searchReq));
    }

}
