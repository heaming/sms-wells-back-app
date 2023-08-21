package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

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
        BigDecimal basStocQty,
        BigDecimal prchsStrQty,
        BigDecimal nomStrQty,
        BigDecimal qomAsnStrQty,
        BigDecimal qomMmtStrQty,
        BigDecimal rtngdStrInsdQty,
        BigDecimal rtngdStrOtsdQty,
        BigDecimal etcStrQty,
        BigDecimal strCtrQty,
        BigDecimal cnfmPitmStrGapQty,
        BigDecimal strQty,
        BigDecimal nomOstrQty,
        BigDecimal svcNomOstrQty,
        BigDecimal sellNomOstrQty,
        BigDecimal qomAsnOstrQty,
        BigDecimal qomMmtOstrQty,
        BigDecimal rtngdOstrInsdQty,
        BigDecimal rtngdOstrOtsdQty,
        BigDecimal useQty,
        BigDecimal yearInUseQty,
        BigDecimal yearOutUseQty,
        BigDecimal refrOstrQty,
        BigDecimal sellOstrQty,
        BigDecimal dsuOstrQty,
        BigDecimal etcOstrQty,
        BigDecimal ostrCtrQty,
        BigDecimal cnfmPitmOstrGapQty,
        BigDecimal ostrQty,
        BigDecimal eotStocQty,
        BigDecimal mmtStocQty

    ) {}

    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchDateReq")
    public record SearchDateReq(
        String itmPdCd,
        String wareNo,
        String strRgstFrom,
        String strRgstTo
    ) {}

    @ApiModel("WsnaReceiptsAndPaymentsDto-SearchDateRes")
    public record SearchDateRes(
        String ymd,
        BigDecimal basStocQty,
        BigDecimal prchsStrQty,
        BigDecimal nomStrQty,
        BigDecimal qomAsnStrQty,
        BigDecimal qomMmtStrQty,
        BigDecimal rtngdStrInsdQty,
        BigDecimal rtngdStrOtsdQty,
        BigDecimal etcStrQty,
        BigDecimal strCtrQty,
        BigDecimal cnfmPitmStrGapQty,
        BigDecimal nomOstrQty,
        BigDecimal svcNomOstrQty,
        BigDecimal sellNomOstrQty,
        BigDecimal qomAsnOstrQty,
        BigDecimal qomMmtOstrQty,
        BigDecimal rtngdOstrInsdQty,
        BigDecimal rtngdOstrOtsdQty,
        BigDecimal useQty,
        BigDecimal refrOstrQty,
        BigDecimal sellOstrQty,
        BigDecimal dsuOstrQty,
        BigDecimal etcOstrQty,
        BigDecimal ostrCtrQty,
        BigDecimal cnfmPitmOstrGapQty,
        BigDecimal eotStocQty,
        BigDecimal mmtStocQty

    ) {}
}
