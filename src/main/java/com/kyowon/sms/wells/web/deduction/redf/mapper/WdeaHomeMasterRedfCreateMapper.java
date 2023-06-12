package com.kyowon.sms.wells.web.deduction.redf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchDlqRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdeaHomeMasterRedfCreateMapper {
    /* 홈마스터 되물림 생성 목록조회 */
    PagingResult<SearchRes> selectHomeMasters(SearchReq dto, PageInfo pageInfo);

    /* 홈마스터 되물림 생성 목록조회 - 엑셀다운로드 */
    List<SearchRes> selectHomeMasters(SearchReq dto);

    /* 홈마스터 되물림 생성 목록조회 - 연체 */
    PagingResult<SearchDlqRes> selectDelinquentHomeMasters(SearchReq dto, PageInfo pageInfo);

    /* 홈마스터 되물림 생성 목록조회 - 연체 - 엑셀다운로드 */
    List<SearchDlqRes> selectDelinquentHomeMasters(SearchReq dto);

}
