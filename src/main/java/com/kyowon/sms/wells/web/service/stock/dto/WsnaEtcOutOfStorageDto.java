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
        // 출고일자
        @NotBlank
        String ostrDt,
        // 출고순번
        String ostrSn,
        // 출고창고번호
        String ostrWareNo,
        // 품목출고번호
        String itmOstrNo,
        // 입고대상창고번호
        String strOjWareNo
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-SearchRes")
    public record SearchRes(
        // 품목출고번호
        String itmOstrNo,
        // 출고유형코드
        String ostrTpCd,
        // 출고순번
        String ostrSn,
        // 출고창고번호
        String ostrWareNo,
        // SAP자재코드
        String sapMatCd,
        // 품목상품코드
        String itmPdCd,
        // 품목약어명
        String pdAbbrNm,
        // 품목등급코드
        String itmGdCd,
        // 시점재고수량
        String onQty,
        // 관리단위코드
        String mngtUnitCd,
        // 출고수량
        String ostrQty,
        // 출고사유코드
        String ostrRsonCd,
        // 비고
        String rmkCn,
        // 창고명
        String wareNm,
        // 창고관리파트너번호
        String wareMngtPrtnrNo,
        // 출고일자
        String ostrDt
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-SearchDeptRes")
    public record SearchDeptRes(
        // CODEID
        String codeId,
        // CODENAME
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
        String mngtUnitCd, //관리단위코드
        int ostrQty, // 출고수량
        String ostrRsonCd, // 출고사유코드
        String rmkCn, // 비고
        String wareNm, // 창고명
        String wareMngtPrtnrNo, // 창고관리파트너번호
        String ostrDt, // 출고일자
        String strOjWareNo // 입고대상창고번호
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
        String mngtUnitCd, // 관리단위코드
        int ostrQty, // 출고수량
        String ostrRsonCd, // 출고사유코드
        String rmkCn, // 비고
        String wareNm, //창고명
        String wareMngtPrtnrNo, // 창고관리파트너번호
        String ostrDt, // 출고일자
        String strOjWareNo // 입고대상창고번호
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-SearchCodeReq")
    public record SearchCodeReq(
        // 적용년월
        String apyYm
    ) {}
    @ApiModel("WsnaEtcOutOfStorageDto-SearchCodeRes")
    public record SearchCodeRes(
        // CODEID
        String codeId,
        // CODENAME
        String codeName,
        // 창고구분코드
        String wareDvCd
    ) {}
}
