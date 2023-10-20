package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbAdvanceRefundAccountMapper {

    /**
     * 선환불현황 조회
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchAdvanceRefundAccountRes>
     */
    PagingResult<SearchAdvanceRefundAccountRes> selectAdvanceRefundAccount(
        SearchAdvanceRefundAccountReq req,
        PageInfo pageInfo
    );

    /**
     * 선환불현황 조회 엑셀 다운로드용
     * @param req
     * @return PagingResult<SearchAdvanceRefundAccountRes>
     */
    List<SearchAdvanceRefundAccountRes> selectAdvanceRefundAccount(
        SearchAdvanceRefundAccountReq req
    );

}
