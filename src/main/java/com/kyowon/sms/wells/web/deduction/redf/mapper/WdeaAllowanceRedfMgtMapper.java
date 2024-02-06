package com.kyowon.sms.wells.web.deduction.redf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.*;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaAllowanceRedfMgtDvo;
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
    /*P조직*/
    PagingResult<SearchRedfBizdRes> selectRedfBizdMgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    //PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdMgtsTest(SearchRedfBizdReq dto, PageInfo pageInfo);

    /*M조직*/
    /*지구장 이하*/
    // M조직 2019년 03월 이전, 직책구분 - 지구장 이하
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizd201903Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지구장 이하
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizd202012Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지구장 이하
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizd202106Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지구장 이하
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizd202303Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2023년 04월 이후, 직책구분 - 지구장 이하
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizd202304Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지구장 이하
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdAllMgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    /*지점장 이상*/
    // M조직 2019년 03월 이전, 직책구분 - 지점장 이상
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr201903Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지점장 이상
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202012Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지점장 이상
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202106Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지점장 이상
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202303Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 2023년 04월 이후, 직책구분 - 지점장 이상
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202304Mgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지점장 이상
    PagingResult<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgrAllMgts(SearchRedfBizdReq dto, PageInfo pageInfo);

    /* 수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 조회 - 엑셀다운로드*/
    /*P조직*/
    List<SearchRedfBizdRes> selectRedfBizdMgts(SearchRedfBizdReq dto);
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdMgtsTest(SearchRedfBizdReq dto);

    //    /*M조직*/
    //    /*지구장 이하*/
    //    // M조직 2019년 03월 이전, 직책구분 - 지구장 이하
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizd201903Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지구장 이하
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizd202012Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지구장 이하
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizd202106Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지구장 이하
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizd202303Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2023년 04월 이후, 직책구분 - 지구장 이하
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizd202304Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지구장 이하
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdAllMgts(SearchRedfBizdReq dto);
    //
    //    /*지점장 이상*/
    //    // M조직 2019년 03월 이전, 직책구분 - 지점장 이상
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr201903Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2019년 04월 ~ 2020년 12월, 직책구분 - 지점장 이상
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202012Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2021년 01월 ~ 2021년 06월, 직책구분 - 지점장 이상
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202106Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2021년 07월 ~ 2023년 03월, 직책구분 - 지점장 이상
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202303Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 2023년 04월 이후, 직책구분 - 지점장 이상
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgr202304Mgts(SearchRedfBizdReq dto);
    //
    //    // M조직 전 기간(위 해당사항에 걸리지 않는 조건), 직책구분 - 지점장 이상
    //    List<WdeaAllowanceRedfMgtDvo> selectRedfBizdBrmgrAllMgts(SearchRedfBizdReq dto);
}
