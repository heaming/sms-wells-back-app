package com.kyowon.sms.wells.web.deduction.redf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchAwRedfRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdeaAllowanceRedfMgtMapper {
    /* 수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록조회 */
    PagingResult<SearchAwRedfRes> selectAwRedfMgts(SearchReq dto, PageInfo pageInfo);

    /* 수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록조회 - 엑셀다운로드 */
    List<SearchAwRedfRes> selectAwRedfMgts(SearchReq dto);

    /* 수당(실적) 되물림 관리 - B2B/총판 목록 조회 */
    PagingResult<SearchRedfRes> selectRedfMgts(SearchReq dto, PageInfo pageInfo);

    /* 수당(실적) 되물림 관리 - B2B/총판 목록 조회 - 엑셀다운로드 */
    List<SearchRedfRes> selectRedfMgts(SearchReq dto);

    /* 수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 조회 */
    PagingResult<SearchRedfBizdRes> selectRedfBizdMgts(SearchRedfBizdReq dto, PageInfo pageInfo);
}
