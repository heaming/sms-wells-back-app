package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRentalExpirationExcessiveAmountDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRentalExpirationExcessiveAmountMapper {

    /**
     * 렌탈만료초과금현황 조회
     * @param req
     * @return
     */
    PagingResult<SearchRes> selectRentalExpirationExcessiveAmount(
        SearchReq req, PageInfo pageInfo
    );

    /**
     * 렌탈만료초과금현황 엑셀다운로드
     * @param req
     * @return
     */
    PagingResult<SearchRes> selectRentalExpirationExcessiveAmount(
        SearchReq req
    );

    /**
     * 렌탈만료초과금현황 합계 조회
     * @param req
     * @return
     */
    SearchTotalSumRes selectRentalExpirationExcessiveAmountTotalSum(
        SearchReq req
    );
}
