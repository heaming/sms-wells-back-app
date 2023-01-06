package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.25
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
        String wareDvCd, // 출고요청 접수창고
        String wareLocaraCd // 창고지역코드
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
        String wareNm // 창고명
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskMngtDto-Warehouse")
    public record Warehouse(
        String code,
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

}
