package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0143M01 기타출고 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.03
 */
public class WsnaEtcOutOfStorageDto {

    @Builder
    @ApiModel("WsnaEtcOutOfStorageDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ostrDt,
        String ostrSn,
        String ostrWareNo,
        String itmOstrNo,
        String strOjWareNo
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-SearchRes")
    public record SearchRes(
        String itmOstrNo,
        String ostrTpCd,
        String ostrSn,
        String ostrWareNo,
        String sapMatCd,
        String itmPdCd,
        String pdAbbrNm,
        String itmGdCd,
        String onQty,
        String mngtUnitCd,
        String ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo,
        String ostrDt
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-SearchDeptRes")
    public record SearchDeptRes(
        String codeId,
        String codeName

    ) {}

    @Builder
    @ApiModel("WsnaEtcOutOfStorageDto-SaveReq")
    public record SaveReq(
        String itmOstrNo, //품목출고번호
        String ostrSn, //출고순번
        String ostrTpCd, //출고유형코드
        String ostrWareNo, //출고창고번호
        String itmKndCd, //품목종류코드
        String sapMatCd, //sap코드
        String itmPdCd, //품목상품코드
        String pdAbbrNm, //상품명
        String itmGdCd, //상품등급코드
        String bilDept, //청구부서
        int onQty, //재고수량
        String mngtUnitCd,
        int ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo,
        String ostrDt,
        String strOjWareNo
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-FindItmOstrNoReq")
    public record FindItmOstrNoReq(
        String ostrDt // 출고일자
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-FindWareMngtPrtnrNoReq")
    public record FindWareMngtPrtnrNoReq(
        String ostrWareNo //출고창고번호
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-DeleteReq")
    public record DeleteReq(
        @NotBlank
        String itmOstrNo, //품목출고번호
        @NotBlank
        String ostrSn, //출고순번
        String ostrTpCd, //출고유형코드
        @NotBlank
        String ostrWareNo, //출고창고번호
        String itmKndCd, //품목종류코드
        String sapMatCd, //sap코드
        String itmPdCd, //품목상품코드
        String pdAbbrNm, //상품명
        String itmGdCd, //상품등급코드
        String bilDept, //청구부서
        int onQty, //재고수량
        String mngtUnitCd,
        int ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo,
        String ostrDt,
        String strOjWareNo
    ) {}
    @ApiModel("WsnaEtcOutOfStorageDto-DeleteRes")
    public record DeleteRes(
        @NotBlank
        String itmOstrNo, //품목출고번호
        @NotBlank
        String ostrSn, //출고순번
        String ostrTpCd, //출고유형코드
        String ostrWareNo, //출고창고번호
        String itmKndCd, //품목종류코드
        String sapMatCd, //sap코드
        String itmPdCd, //품목상품코드
        String pdAbbrNm, //상품명
        String itmGdCd, //상품등급코드
        String bilDept, //청구부서
        int onQty, //재고수량
        String mngtUnitCd,
        int ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo,
        String ostrDt,
        String strOjWareNo
    ) {}
}
