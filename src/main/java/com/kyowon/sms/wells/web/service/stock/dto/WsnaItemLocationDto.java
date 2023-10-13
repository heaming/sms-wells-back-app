package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0210P01 품목위치관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023.04.27
 */
public class WsnaItemLocationDto {
    @ApiModel("WsnaItemLocationDto-SearchReq")
    public record SearchReq(
        // 품목코드
        @NotBlank
        String itmPdCd,
        // 창고번호
        @NotBlank
        String wareNo

    ) {}
    @ApiModel("WsnaItemLocationDto-SearchRes")
    public record SearchRes(
        // 품목코드
        String itmPdCd,
        // 품목명
        String pdAbbrNm,
        // SAP코드
        String sapMatCd,
        // 품목종류
        String itmKndCd,
        // 창고번호
        String wareNo,
        // 창고명
        String wareNm,
        // 표준창고사용여부
        String stdWareUseYn,
        // 재고수량
        String pitmStocAGdQty,
        // 앵글
        String itmLctAngleVal,
        // 층수
        String itmLctCofVal,
        // 층번호
        String itmLctFlorNoVal,
        // 그룹
        String itmLctMatGrpCd,
        // 위치
        String itmLctNm
    ) {}

    @ApiModel("WsnaItemLocationDto-CreateReq")
    public record CreateReq(
        // 창고번호
        @NotBlank
        String wareNo,
        // 품목코드
        @NotBlank
        String itmPdCd,
        // 앵글
        String itmLctAngleVal,
        // 층수
        String itmLctCofVal,
        // 층번호
        String itmLctFlorNoVal,
        // 그룹
        String itmLctMatGrpCd,
        // 품목종류
        String itmKndCd

    ) {}

    @ApiModel("WsnaItemLocationDto-SearchStockCdRes")
    public record SearchStockCdRes(
        String codeId,
        String codeName
    ) {}

    @ApiModel("WsnaItemLocationDto-SearchLocationReq")
    public record SearchLocationReq(
        String itmPdCd,
        String itmKnd,
        String wareNo

    ) {}
    @ApiModel("WsnaItemLocationDto-SearchLocationRes")
    public record SearchLocationRes(
        String itmPdCd,
        String pdAbbrNm,
        String sapMatCd,
        String itmKndCd,
        String wareNo,
        String wareNm,
        String stdWareUseYn,
        String pitmStocAGdQty,
        String wareTpCd,
        String locationCd,
        String itmLctAngleVal,
        String itmLctCofVal,
        String itmLctFlorNoVal,
        String itmLctMatGrpCd,
        String itmLctNm
    ) {}

    @ApiModel("WsnaItemLocationDto-CreateLocationReq")
    public record CreateLocationReq(
        @NotBlank
        String wareNo,
        @NotBlank
        String itmPdCd,
        String wareTpCd,
        String itmLctAngleVal,
        String itmLctCofVal,
        String itmLctFlorNoVal,
        String itmLctMatGrpCd,
        String itemKnd

    ) {}

    @ApiModel(value = "WsnaItemLocationDto-CreateWareLocationReq")
    public record CreateWareLocationReq(
        String wareNo,
        @NotBlank
        String stckStdGb
    ) {}

}
