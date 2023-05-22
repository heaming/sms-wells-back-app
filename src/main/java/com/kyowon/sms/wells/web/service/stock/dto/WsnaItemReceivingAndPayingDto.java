package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import java.util.List;

public class WsnaItemReceivingAndPayingDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 품목별수불현황 조회 Search Request Dto
    @Builder
    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchReq")
    public record SearchReq(
        String strWareDvCd,
        String strWareNoM,
        String strWareNoD,
        String stFromYmd,
        String edToYmd,
        String itmKnd,
        String itmGdCd,
        String useYn,
        String itmPdCdFrom,
        String itmPdCdTo,
        String sapMatCdFrom,
        String sapMatCdTo,
        List<String> sapMatDpcts

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 품목별수불현황 조회 Search Result Dto
    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchRes")
    public record SearchRes(
        String itmPdCd,
        String sapMatCd,
        String itmPdNm,
        String basStocQty,
        String prchsStrQty,
        String nomStrQty,
        String qomAsnStrQty,
        String qomMmtStrQty,
        String rtngdStrInsdQty,
        String rtngdStrOtsdQty,
        String etcStrQty,
        String strCtrQty,
        String cnfmPitmStrGapQty,
        String nomOstrQty,
        String svcNomOstrQty,
        String sellNomOstrQty,
        String qomAsnOstrQty,
        String qomMmtOstrQty,
        String rtngdOstrInsdQty,
        String rtngdOstrOtsdQty,
        String useQty,
        String yearInUseQty,
        String yearOutUseQty,
        String refrOstrQty,
        String sellOstrQty,
        String dsuOstrQty,
        String etcOstrQty,
        String ostrCtrQty,
        String cnfmPitmOstrGapQty,
        String mmtStocQty

    ) {}
}
