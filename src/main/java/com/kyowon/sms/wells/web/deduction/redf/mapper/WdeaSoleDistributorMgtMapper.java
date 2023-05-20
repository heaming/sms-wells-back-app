package com.kyowon.sms.wells.web.deduction.redf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchBusinessToBusinessPrtnrRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorContractRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorCreateReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorMgtReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorMgtRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SearchSoleDistributorPrtnrRes;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaSoleDistributorMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdeaSoleDistributorMgtMapper {

    /* 총판/B2B 되물림 생성 목록 조회 - 그리드 1번 */
    PagingResult<SearchSoleDistributorPrtnrRes> selectSoleDistributorPrtnrs(
        SearchSoleDistributorCreateReq dto, PageInfo pageInfo
    );

    /* 총판/B2B 되물림 생성 목록 조회 - 그리드 1번 엑셀다운로드 */
    List<SearchSoleDistributorPrtnrRes> selectSoleDistributorPrtnrs(SearchSoleDistributorCreateReq dto);

    /* 총판/B2B 되물림 생성 목록 조회 - 그리드 2번 */
    PagingResult<SearchSoleDistributorContractRes> selectSoleDistributorContracts(
        SearchSoleDistributorCreateReq dto, PageInfo pageInfo
    );

    /* 총판/B2B 되물림 생성 목록 조회 - 그리드 2번 엑셀다운로드*/
    List<SearchSoleDistributorContractRes> selectSoleDistributorContracts(SearchSoleDistributorCreateReq dto);

    /* 총판/B2B 되물림 생성 목록 조회 - 그리드 3번 */
    PagingResult<SearchBusinessToBusinessPrtnrRes> selectBusinessToBusinessPrtnrs(
        SearchSoleDistributorCreateReq dto, PageInfo pageInfo
    );

    /* 총판/B2B 되물림 생성 목록 조회 - 그리드 3번 엑셀다운로드*/
    List<SearchBusinessToBusinessPrtnrRes> selectBusinessToBusinessPrtnrs(SearchSoleDistributorCreateReq dto);

    /* 총판/B2B 되물림 관리 조회 */
    PagingResult<SearchSoleDistributorMgtRes> selectSoleDistributorB2bs(
        SearchSoleDistributorMgtReq dto, PageInfo pageInfo
    );

    /* 총판/B2B 되물림 관리 조회 - 엑셀다운로드 */
    List<SearchSoleDistributorMgtRes> selectSoleDistributorB2bs(SearchSoleDistributorMgtReq dto);

    /* 총판/B2B 되물림 관리 수정 */
    int updateRedfDdtnAmt(WdeaSoleDistributorMgtDvo dvo);

}
