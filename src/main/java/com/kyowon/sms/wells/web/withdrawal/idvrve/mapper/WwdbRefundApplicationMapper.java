package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRefundApplicationMapper {

    PagingResult<SearchRefundApplicationRes> selectRefundApplication(
        SearchRefundApplicationReq req,
        PageInfo pageInfo
    );

    List<SearchRefundApplicationRes> selectRefundApplication(
        SearchRefundApplicationReq req
    );

    PagingResult<SearchRefundContractDetailRes> selectRefundContractDetail(
        SearchRefundContractDetailReq req,
        PageInfo pageInfo
    );

    List<SearchRefundContractDetailRes> selectRefundContractDetail(
        SearchRefundContractDetailReq req
    );

    SearchRefundRes selectRefund(SearchRefundReq req);

    /* 환불상세-팝업에서 선택시 */
    PagingResult<SearchRefundDetailRes> selectRefundDetail(
        SearchRefundDetailReq req,
        PageInfo pageInfo
    );

    /*  환불상세 - 메인->그리드->팝업 */
    PagingResult<SearchRefundDetailRes> selectRefundDetailPage(
        SearchRefundDetailReq req,
        PageInfo pageInfo
    );

    /* 전금상세 */
    PagingResult<SearchRefundBalanceTransferRes> selectRefundBalanceTransfer(
        SearchRefundBalanceTransferReq req,
        PageInfo pageInfo
    );

    /* TODO: 임시저장 START */
    String selectRefundPk();

    int insertRefundTempSave(WwdbRefundBaseDvo dvo);

    int insertRefundTempSaveReqDetail(WwdbRefundCntrDvo dvo);

    int insertRefundTempSaveDetail(WwdbRefundDtlDvo dvo);

    int insertBalanceTempSaveDetail(WwdbRefundBltfDvo dvo);

    int deleteBalanceTempSaveDetail(WwdbRefundBltfDvo dvo);

    /* TODO:  임시저장 END*/

    /* TODO: 삭제시  */
    int deleteRefundBase(WwdbRefundRemoveDvo dvo);

    int deleteRefundCntrDetail(WwdbRefundRemoveDvo dvo);

    int deleteRefundDetail(WwdbRefundRemoveDvo dvo);

    int deleteRefundBalanceDetail(WwdbRefundRemoveDvo dvo);

    /*TODO: 그리드에서 선택하여 조회시 */
    PagingResult<SearchRefundBaseRes> selectRefundBasePages(
        SearchRefundBaseReq req,
        PageInfo pageInfo
    );

    /* TODO: 상세(P03) -> 컨텍이력조회 선택시 */
    PagingResult<SearchRefundApplicationConnectHistoryRes> selectRefundApplicationConnectHistory(
        String cntrNo,
        PageInfo pageInfo
    );

    List<SearchRefundApplicationConnectHistoryRes> selectRefundApplicationConnectHistory(
        String cntrNo
    );

}
