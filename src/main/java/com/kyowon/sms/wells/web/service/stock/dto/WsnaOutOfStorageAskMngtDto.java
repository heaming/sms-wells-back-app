package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author songtaesung
 * @since 2022.12.26
 */
public class WsnaOutOfStorageAskMngtDto {

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String strOjWareNo, // 출고요청창고 = 입고대상창고번호
        String ostrAkTpCd, // 출고요청유형코드
        @NotBlank
        String startStrHopDt, // 입고희망일자 시작일
        @NotBlank
        String endStrHopDt, // 입고희망일자 종료일
        @NotBlank
        String wareDvCd// 출고요청 접수창고
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-WarehouseReq")
    public record WarehouseReq(
        String apyYm
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-SearchRes")
    public record SearchRes(
        String ostrAkNo, // 출고요청번호
        String ostrAkTpCd, // 출고요청유형코드
        String strHopDt, // 입고희망일자
        String rectOstrDt, // 최근출고일자
        String wareNm, // 창고명
        String ostrOjWareNo, //출고대상창고번호
        String ostrAkRgstDt, //출고요청등록일자
        String strOjWareNo //입고대상창고번호
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-OutOfRes")
    public record OutOfRes(
        String sapMatCd,
        String ostrAkTpCd,
        String strOjWareNo,
        String ostrAkRgstDt,
        String ostrAkNo,
        String ostrAkSn,
        String fstRgstDtm,
        String strHopDt,
        String itmPdCd,
        String itmNm,
        String ostrAkWareDvCd,
        String wareMngtPrtnrNo,
        String ostrOjWareDvCd,
        String ostrOjWareNo,
        String ostrWareMngtPrtnrNo,
        String mngtUnitCd,
        String boxUnitQty,
        String itmGdCd,
        String ostrAkQty,
        String ostrCnfmQty,
        String rmkCn,
        String rectOstrDt,
        String ostrAggQty,
        String warehouseQty,
        String baseStocQty,
        String sftStocQty,
        String useQty,
        String centerQty,
        String indiQty,
        String imgApnFileId

    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-Warehouse")
    public record Warehouse(
        String codeId,
        String codeName,
        String wareMngtPrtnrNo,
        String wareIchrNo,
        String wareDvCd,
        String hgrWareNo,
        String wareNmUp,
        String wareMngtPrtnrNoUp,
        String wareLocaraCdUp,
        String wareDvCdUp
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-FindReq")
    public record FindReq(
        String ostrAkNo, // 출고요청번호
        String ostrAkTpCd, // 출고요청유형코드
        String strHopDt, // 입고희망일자
        String rectOstrDt, // 최근출고일자
        String wareNm, // 창고명
        String ostrOjWareNo, //출고대상창고번호
        String ostrAkRgstDt, //출고요청등록일자
        String strOjWareNo //입고대상창고번호
    ) {}
    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-FindRes")
    public record FindRes(
        String ostrAkNo, //출고요청번호
        String ostrAkTpCd, // 출고요청유형
        String strOjWareNo, // 입고대상창고번호
        String strOjWareNm, // 입고대상창고명
        String strOjStckMgr, // 입고대상창고파트너
        String ostrAkRgstDt, //출고요청등록일자
        String strHopDt, //입고희망일자
        String ostrOjWareNo, //출고대상창고번호
        String ostrOjWareNm, //출고대상창고명
        String ostrOjStckMgr, //출고대상창고파트너
        String ostrWareLocaraCd //출고창고지역코드
    ) {}

}
