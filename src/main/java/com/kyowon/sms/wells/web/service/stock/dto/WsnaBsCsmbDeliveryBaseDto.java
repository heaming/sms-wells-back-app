package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0295M01 BS소모품 배부기준 관리 Dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-29
 */

public class WsnaBsCsmbDeliveryBaseDto {
    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-SearchReq")
    public record SearchReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 발주구분
        String goDvCd,
        // 품목코드
        String csmbPdCd,
        // 시작 품목코드
        String csmbPdCdStrt,
        // 종료 품목코드
        String csmbPdCdEnd,
        // 시작 SAP코드
        String sapMatCdStrt,
        // 종료 SAP코드
        String sapMatCdEnd,
        // 품목코드 리스트
        List<String> itmKnms
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-SearchRes")
    public record SearchRes(
        // 관리년월
        String mngtYm,
        // 소모품상품코드
        String csmbPdCd,
        // 품목한글명
        String itmKnm,
        // 관리단위코드
        String mngtUnitCd,
        // 관리단위
        String mngtUnitNm,
        // 발주구분코드
        String goDvCd,
        // 발주구분
        String goDvNm,
        // 발주단가
        BigDecimal goUprc,
        // 박스단위수량
        BigDecimal boxUnitQty,
        // SAP코드
        String sapMatCd,
        // 비고
        String rmkCn,
        // 운영여부(신입)
        String nwcmrOrtYn,
        // 유형코드(신입)
        String nwcmrTpCd,
        // 유형(신입)
        String nwcmrTpNm,
        // 산출기준코드(신입)
        String nwcmrCmptBaseCd,
        // 산출기준(신입)
        String nwcmrCmptBaseNm,
        // 상품그룹코드(신입)
        String nwcmrPdGrpCd,
        // 상품그룹(신입)
        String nwcmrPdGrpNm,
        // 계정유형코드(신입)
        String nwcmrAccTpCd,
        // 계정유형(신입)
        String nwcmrAccTpNm,
        // 단위계정수(신입)
        BigDecimal nwcmrUnitAccN,
        // 단위수량(신입)
        BigDecimal nwcmrUnitQty,
        // 신청제한수량(신입)
        BigDecimal nwcmrLmQty,
        // 정렬순서(신입)
        BigDecimal nwcmrSortOdr,
        // 운영여부(개인)
        String indvOrtYn,
        // 유형코드(개인)
        String indvTpCd,
        // 유형(개인)
        String indvTpNm,
        // 산출기준코드(개인)
        String indvCmptBaseCd,
        // 산출기준(개인)
        String indvCmptBaseNm,
        // 상품그룹코드(개인)
        String indvPdGrpCd,
        // 상품그룹(개인)
        String indvPdGrpNm,
        // 계정유형코드(개인)
        String indvAccTpCd,
        // 계정유형(개인)
        String indvAccTpNm,
        // 단위계정수(개인)
        BigDecimal indvUnitAccN,
        // 단위수량(개인)
        BigDecimal indvUnitQty,
        // 신청제한수량(개인)
        BigDecimal indvLmQty,
        // 정렬순서(개인)
        BigDecimal indvSortOdr,
        // 운영여부(빌딩)
        String bldOrtYn,
        // 유형코드(빌딩)
        String bldTpCd,
        // 유형(빌딩)
        String bldTpNm,
        // 산출기준코드(빌딩)
        String bldCmptBaseCd,
        // 산출기준(빌딩)
        String bldCmptBaseNm,
        // 상품그룹코드(빌딩)
        String bldPdGrpCd,
        // 상품그룹(빌딩)
        String bldPdGrpNm,
        // 계정유형코드(빌딩)
        String bldAccTpCd,
        // 계정유형(빌딩)
        String bldAccTpNm,
        // 단위계정수(빌딩)
        BigDecimal bldUnitAccN,
        // 단위수량(빌딩)
        BigDecimal bldUnitQty,
        // 신청제한수량(빌딩)
        BigDecimal bldLmQty,
        // 정렬순서(빌딩)
        BigDecimal bldSortOdr
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-CreateReq")
    public record CreateReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 품목명
        String itmKnm,
        // 소모품상품코드
        @NotBlank
        String csmbPdCd,
        // 관리단위코드
        String mngtUnitCd,
        // 발주구분
        String goDvCd,
        // 박스단위수량
        BigDecimal boxUnitQty,
        // 발주단가
        BigDecimal goUprc,
        // 비고
        String rmkCn,
        // 배부대상코드
        String bfsvcCsmbDdlvOjCd,
        // 운영여부
        String bfsvcCsmbDdlvOrtYn,
        // 유형코드
        String bfsvcCsmbDdlvTpCd,
        // 산출기준코드
        String bfsvcCsmbDdlvCmptBaseCd,
        // 상품그룹코드
        String bfsvcCsmbDdlvOjPdGrpCd,
        // 계정유형코드
        String bfsvcCsmbDdlvOjAccTpCd,
        // 단위계정수
        BigDecimal bfsvcCsmbDdlvUnitAccN,
        // 단위수량
        BigDecimal bfsvcCsmbDdlvUnitQty,
        // 신청제한수량
        BigDecimal bfsvcCsmbAplcLmQty,
        // 정렬순서
        BigDecimal sortOdr
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-SearchItemsRes")
    public record SearchItemsRes(
        // 품목코드
        String pdCd,
        // 품목명
        String pdNm,
        // 약어명
        String pdAbbrNm
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-CreateReq")
    public record EditReq(
        // 관리년월
        String mngtYm,
        // 소모품상품코드
        String csmbPdCd,
        // 관리단위코드
        String mngtUnitCd,
        // 발주구분
        String goDvCd,
        // 박스단위수량
        BigDecimal boxUnitQty,
        // 발주단가
        BigDecimal goUprc,
        // 비고
        String rmkCn,
        // 배부대상코드
        String bfsvcCsmbDdlvOjCd,
        // 운영여부
        String bfsvcCsmbDdlvOrtYn,
        // 유형코드
        String bfsvcCsmbDdlvTpCd,
        // 산출기준코드
        String bfsvcCsmbDdlvCmptBaseCd,
        // 상품그룹코드
        String bfsvcCsmbDdlvOjPdGrpCd,
        // 계정유형코드
        String bfsvcCsmbDdlvOjAccTpCd,
        // 단위계정수
        BigDecimal bfsvcCsmbDdlvUnitAccN,
        // 단위수량
        BigDecimal bfsvcCsmbDdlvUnitQty,
        // 신청제한수량
        BigDecimal bfsvcCsmbAplcLmQty,
        // 정렬순서
        BigDecimal sortOdr
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-CreateCrdovrReq")
    public record CreateCrdovrReq(
        // 이월년월
        @NotBlank
        String carriedOverFrom,
        // 대상년월
        @NotBlank
        String carriedOverTo
    ) {}
}
