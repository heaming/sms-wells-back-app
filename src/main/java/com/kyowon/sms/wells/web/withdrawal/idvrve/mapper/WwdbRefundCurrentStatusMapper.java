package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchBalanceTransferRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchBalanceTransferRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchCardRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchCardRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRefundCurrentStatusMapper {

    PagingResult<SearchRefundHistoryRes> selectRefundHistory(
        SearchRefundHistoryReq req,
        PageInfo pageInfo
    );

    List<SearchRefundHistoryRes> selectRefundHistory(
        SearchRefundHistoryReq req
    );

    PagingResult<SearchCardRefundHistoryRes> selectCardRefundHistory(
        SearchCardRefundHistoryReq req,
        PageInfo pageInfo
    );

    List<SearchCardRefundHistoryRes> selectCardRefundHistory(
        SearchCardRefundHistoryReq req
    );

    PagingResult<SearchBalanceTransferRefundHistoryRes> selectBalanceTransferRefundHistory(
        SearchBalanceTransferRefundHistoryReq req,
        PageInfo pageInfo
    );

    List<SearchBalanceTransferRefundHistoryRes> selectBalanceTransferRefundHistory(
        SearchBalanceTransferRefundHistoryReq req
    );
}
