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
        String wareNo,
        // 기준년월
        @NotBlank
        String apyYm

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
        // 앵글명
        String angleValNm,
        // 층수
        String itmLctCofVal,
        // 층수명
        String cofValNm,
        // 층번호
        String itmLctFlorNoVal,
        // 층번호명
        String florNoValNm,
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
        // 코드 ID
        String codeId,
        // 코드명
        String codeName
    ) {}

    @ApiModel("WsnaItemLocationDto-SearchLocationReq")
    public record SearchLocationReq(
        // 품목상품코드
        String itmPdCd,
        // 품목구분
        String itmKnd,
        // 창고번호
        String wareNo,
        // 품목그룹
        String itmGrpCd,
        // 자재그룹
        String svMatGrpCd

    ) {}
    @ApiModel("WsnaItemLocationDto-SearchLocationRes")
    public record SearchLocationRes(
        // 품목상품코드
        String itmPdCd,
        // 품목약어명
        String pdAbbrNm,
        // SAP자재코드
        String sapMatCd,
        // 품목구분코드
        String itmKndCd,
        // 창고번호
        String wareNo,
        // 창고명
        String wareNm,
        //표준창고사용여부
        String stdWareUseYn,
        // 시점재고A등급수량
        String pitmStocAGdQty,
        // 창고유형코드
        String wareTpCd,
        // 위치코드
        String locationCd,
        // 품목앵글값
        String itmLctAngleVal,
        // 앵글명
        String angleValNm,
        // 층수
        String itmLctCofVal,
        // 층수명
        String cofValNm,
        // 층번호
        String itmLctFlorNoVal,
        // 층번호명
        String florNoValNm,
        // 품목위치자재그룹코드
        String itmLctMatGrpCd,
        // 품목위치명
        String itmLctNm
    ) {}

    @ApiModel("WsnaItemLocationDto-CreateLocationReq")
    public record CreateLocationReq(
        // 창고번호
        @NotBlank
        String wareNo,
        // 품목상품코드
        @NotBlank
        String itmPdCd,
        // 창고유형코드
        String wareTpCd,
        // 품목위치앵글값
        String itmLctAngleVal,
        // 품목위치층수값
        String itmLctCofVal,
        // 품목위치층번호값
        String itmLctFlorNoVal,
        // 품목위치자재그룹코드
        String itmLctMatGrpCd,
        // 품목구분
        String itmKndCd,
        // 표준미적용 체크
        String stckStdGb

    ) {}

    @ApiModel(value = "WsnaItemLocationDto-CreateWareLocationReq")
    public record CreateWareLocationReq(
        // 창고번호
        String wareNo,
        // 창고표준구분
        @NotBlank
        String stckStdGb
    ) {}

}
