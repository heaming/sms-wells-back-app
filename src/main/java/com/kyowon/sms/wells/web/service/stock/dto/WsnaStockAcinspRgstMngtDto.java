package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaStockAcinspRgstMngtDto {

    @Builder
    @ApiModel("WsnaStockAcinspRgstMngtDto-SearchWareReq")
    public record SearchWareReq(
        @NotBlank
        String baseYm,
        // 창고구분코드
        @NotBlank
        String wareDvCd,

        // 창고세부구분코드
        String wareDtlDvCd
    ) {}
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 월별 재고실사 등록 관리 Search Request Dto
    @Builder
    @ApiModel("WsnaStockAcinspRgstMngtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String wareDvCd,
        String wareDtlDvCd,
        String searchWareNo,
        String useYn
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 월별 재고실사 등록 관리 Search Result Dto
    @ApiModel("WsnaStockAcinspRgstMngtDto-SearchRes")
    public record SearchRes(
        String apyYm,
        String wareNo,
        String wareNm,
        String itmKnd,
        String sapCd,
        String itmPdCd,
        String pdAbbrNm,
        int acinspQty,
        int eotStoc,
        int minusQty,
        String acinspRmkCn,
        String statusT,
        String cnfmdt,
        int cnfmPitmEotStocQty,
        int cnfmPitmStrGapQty,
        int cnfmPitmOstrGapQty,
        int diffQty,
        String iostRfdt

    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveAcinspReq")
    public record SaveAcinspReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String wareNo,
        String wareNm,
        String itmKnd,
        String sapCd,
        @NotBlank
        String itmPdCd,
        String pdAbbrNm,
        int acinspQty,
        int eotStoc,
        int minusQty,
        String acinspRmkCn,
        String cnfmdt,
        int cnfmPitmEotStocQty,
        int cnfmPitmStrGapQty,
        int cnfmPitmOstrGapQty,
        int diffQty,
        String iostRfdt,
        String acinspAkId
    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveAcinspCnfmReq")
    public record SaveAcinspCnfmReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String wareNo,
        String wareNm,
        String itmKnd,
        String sapCd,
        String itmPdCd,
        String pdAbbrNm,
        int acinspQty,
        int eotStoc,
        int minusQty,
        String acinspRmkCn,
        String cnfmdt,
        int cnfmPitmEotStocQty,
        int cnfmPitmStrGapQty,
        int cnfmPitmOstrGapQty,
        int diffQty,
        String iostRfdt,
        String acinspAkId,
        //조회조건 데이터 매핑
        String baseYm,
        String wareDvCd,
        String wareDtlDvCd,
        String searchWareNo,
        String useYn
    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveCancelReq")
    public record SaveCancelReq(
        @NotBlank
        String apyYm,
        @NotBlank
        String wareNo,
        String wareNm,
        String itmKnd,
        String sapCd,
        String itmPdCd,
        String pdAbbrNm,
        int acinspQty,
        int eotStoc,
        int minusQty,
        String acinspRmkCn,
        String cnfmdt,
        int cnfmPitmEotStocQty,
        int cnfmPitmStrGapQty,
        int cnfmPitmOstrGapQty,
        int diffQty,
        String iostRfdt,
        String acinspAkId,
        //조회조건 데이터 매핑
        String baseYm,
        String wareDvCd,
        String wareDtlDvCd,
        String searchWareNo,
        String useYn
    ) {}

    @ApiModel("WsnaStockAcinspRgstMngtDto-SaveReq")
    public record SaveReq(
        String apyYm,
        String wareNo,
        String wareNm,
        String itmKnd,
        String sapCd,
        String itmPdCd,
        String pdAbbrNm,
        int acinspQty,
        int eotStoc,
        int minusQty,
        String acinspRmkCn,
        String cnfmdt,
        int cnfmPitmEotStocQty,
        int cnfmPitmStrGapQty,
        int cnfmPitmOstrGapQty,
        int diffQty,
        String iostRfdt,
        String acinspAkId,
        //조회조건 데이터 매핑
        String baseYm,
        String wareDvCd,
        String wareDtlDvCd,
        String searchWareNo,
        String useYn
    ) {}
}
