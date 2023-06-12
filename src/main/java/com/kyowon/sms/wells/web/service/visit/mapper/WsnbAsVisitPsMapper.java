package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsVisitPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsVisitPsDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0031M01 상품별 서비스 처리 집계 현황
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.30
 */
@Mapper
public interface WsnbAsVisitPsMapper {

    PagingResult<SearchRes> selectProductServices(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectProductServices(SearchReq dto);

}
