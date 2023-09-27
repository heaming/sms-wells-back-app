package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaByDayMaterialInOutSearchDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaByDayMaterialInOutSearchConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaByDayMaterialInOutSearchDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaByDayMaterialInOutSearchMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0258M01 일자별 자재 입출고 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.19
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaByDayMaterialInOutSearchService {

    private final WsnaByDayMaterialInOutSearchMapper mapper;
    private final WsnaByDayMaterialInOutSearchConverter converter;

    /**
     * 일자별 자재 입출고 관리(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getByDayMaterialInOutSearchPages(
        SearchReq searchReq, PageInfo pageInfo
    ) {
        PagingResult<SearchRes> pagingResult = converter
            .mapAllByDayMaterialInOutSearchRes(
                mapper.selectByDayMaterialInOutSearchPages(searchReq, pageInfo)
            );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * 일자별 자재 입출고 관리 조회
     * @param searchReq 조회조건
     * @return 조회결과
     */
    public List<SearchRes> getByDayMaterialInOutSearchExcelDownload(SearchReq searchReq) {

        return converter
            .mapAllByDayMaterialInOutSearchRes(mapper.selectByDayMaterialInOutSearchPages(searchReq));
    }

    @Transactional
    public int deleteOutOfStorageAskItemization(List<DeleteReq> dtos) throws Exception {
        int processCnt = 0;

        for (DeleteReq dto : dtos) {
            WsnaByDayMaterialInOutSearchDvo dvo = this.converter.mapByDayMaterialInOutDeleteReq(dto);
            processCnt += this.mapper.deleteOutOfStorageAskItemization(dvo);
        }
        return processCnt;
    }
}
