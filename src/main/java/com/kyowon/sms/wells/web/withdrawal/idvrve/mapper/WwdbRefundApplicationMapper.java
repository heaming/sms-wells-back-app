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

    List<SearchRefundContractDetailRes> selectRefundContractDetail(
        SearchRefundContractDetailReq req
    );

    SearchRefundRes selectRefund(SearchRefundReq req);

    /* 환불상세-팝업에서 선택시 */
    List<SearchRefundDetailRes> selectRefundDetail(
        SearchRefundDetailReq req
    );

    /*  환불상세 - 메인->그리드->팝업 */
    List<SearchRefundDetailRes> selectRefundDetailPage(
        SearchRefundDetailReq req
    );

    /* 전금상세 */
    List<SearchRefundBalanceTransferRes> selectRefundBalanceTransfer(
        SearchRefundBalanceTransferReq req
    );

    /* 임시저장 START */
    String selectRefundPk();

    int insertRefundTempSave(WwdbRefundBaseDvo dvo);

    int insertRefundTempSaveReqDetail(WwdbRefundCntrDvo dvo);

    int insertRefundTempSaveDetail(WwdbRefundDtlDvo dvo);

    int insertBalanceTempSaveDetail(WwdbRefundBltfDvo dvo);

    int deleteBalanceTempSaveDetail(WwdbRefundBltfDvo dvo);

    /* 임시저장 END*/

    /* 삭제시  */
    int deleteRefundBase(WwdbRefundRemoveDvo dvo);

    int deleteRefundCntrDetail(WwdbRefundRemoveDvo dvo);

    int deleteRefundDetail(WwdbRefundRemoveDvo dvo);

    int deleteRefundBalanceDetail(WwdbRefundRemoveDvo dvo);

    /* 그리드에서 선택하여 조회시 */
    PagingResult<SearchRefundBaseRes> selectRefundBasePages(
        SearchRefundBaseReq req,
        PageInfo pageInfo
    );

    /*  상세(P03) -> 컨텍이력조회 선택시 */
    PagingResult<SearchRefundApplicationConnectHistoryRes> selectRefundApplicationConnectHistory(
        String cntrNo,
        PageInfo pageInfo
    );

    List<SearchRefundApplicationConnectHistoryRes> selectRefundApplicationConnectHistory(
        String cntrNo
    );

    /* 승인(현금) - 환불접수기본*/
    int insertRefundReceiptBaseCash(WwdbRefundDtlDvo dvo);

    /* 승인(카드) - 환불접수기본*/
    int insertRefundReceiptBaseCard(WwdbRefundDtlDvo dvo);

    /* 승인(전금) - 환불접수기본*/
    int insertRefundReceiptBaseBltf(WwdbRefundDtlDvo dvo);

    /* 승인(현금) - 환불접수상세*/
    int insertRefundReceiptDtlCash(WwdbRefundDtlDvo dvo);

    /* 승인(카드) - 환불접수상세*/
    int insertRefundReceiptDtlCard(WwdbRefundDtlDvo dvo);

    /* 승인(전문) - 환불접수상세*/
    /* 환불접수기본 상태값 변경*/

    int updateRefundStatus(WwdbRefundBaseDvo dvo);

    int insertRefundReceiptDtlBltf(WwdbRefundDtlDvo dvo);

    /* 승인 - 환불접수기본 이력*/
    int insertRefundReceiptBaseHistory(WwdbRefundDtlDvo dvo);

    /* 승인 - 환불접수상세 이력*/
    int insertRefundReceiptDtlHistory(WwdbRefundDtlDvo dvo);

    /* 승인 - 환불삭제*/
    int deleteRefunds(String rfndAkNo);

    int deleteDtlRefunds(String rfndAkNo);

    int deleteAskDtlRefunds(String rfndAkNo);

    int deleteAskBalanceTransferRefunds(String rfndAkNo);

}
