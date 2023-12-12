package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchBalanceTransferRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchCardRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryTotalRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRefundCurrentStatusMapper {

    /**
     * 환불내역 목록
     *
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchRefundHistoryRes>
     */
    PagingResult<SearchRefundHistoryRes> selectRefundHistory(
        SearchRefundHistoryReq req,
        PageInfo pageInfo
    );

    /**
     * 환불내역 엑셀다운로드
     *
     * @param req
     * @return List<SearchRefundHistoryRes>
     */
    List<SearchRefundHistoryRes> selectRefundHistory(
        SearchRefundHistoryReq req
    );

    /**
     * 환불내역 합계
     *
     * @param req
     * @return List<SearchRefundHistoryRes>
     */
    SearchRefundHistoryTotalRes selectRefundHistorySum(
        SearchRefundHistoryReq req
    );

    /**
     * 카드사별 환불내역 목록
     *
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchCardRefundHistoryRes>
     */
    PagingResult<SearchCardRefundHistoryRes> selectCardRefundHistory(
        SearchRefundHistoryReq req,
        PageInfo pageInfo
    );

    /**
     * 카드사별 환불내역 엑셀다운로드
     *
     * @param req
     * @return List<SearchCardRefundHistoryRes>
     */
    List<SearchCardRefundHistoryRes> selectCardRefundHistory(
        SearchRefundHistoryReq req
    );

    /**
     * 전금내역 목록
     *
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchBalanceTransferRefundHistoryRes>
     */
    PagingResult<SearchBalanceTransferRefundHistoryRes> selectBalanceTransferRefundHistory(
        SearchRefundHistoryReq req,
        PageInfo pageInfo
    );

    /**
     * 전금내역 목록 엑셀다운로드
     *
     * @param req
     * @return List<SearchBalanceTransferRefundHistoryRes>
     */
    List<SearchBalanceTransferRefundHistoryRes> selectBalanceTransferRefundHistory(
        SearchRefundHistoryReq req
    );
}
