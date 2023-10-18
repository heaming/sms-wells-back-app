package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0144M01 출고내역상세 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-02-14
 */
public class WsnaOutOfStorageIzDtlDto {

    @Builder
    @ApiModel("WsnaOutOfStorageIzDtlDto-SearchPdRes")
    public record SearchPdRes(
        // 품목코드
        String pdCd,
        // 품목명
        String pdNm,
        // 품목종류
        String itmKndCd
    ) {}

    @ApiModel(value = "WsnaOutOfStorageIzDtlDto-SearchReq")
    public record SearchReq(
        // 출고시작일자
        @NotBlank
        @ValidDate
        String stOstrDt,
        // 출고종료일자
        @NotBlank
        @ValidDate
        String edOstrDt,
        // 출고유형
        String ostrTpCd,
        // 출고창고구분
        @NotBlank
        String ostrWareDvCd,
        // 출고상위창고번호
        String ostrHgrWareNo,
        // 출고창고번호
        String ostrWareNo,
        // 입고창고구분
        @NotBlank
        String strWareDvCd,
        // 입고상위창고번호
        String strHgrWareNo,
        // 입고창고번호
        String strWareNo,
        // 품목코드
        String itmPdCd,
        // 등급
        String itmGdCd,
        // 품목종류
        String itmKndCd,
        // 사용여부
        String useYn,
        // 입고창고상세구분
        String strWareDtlDvCd,
        // 출고창고상세구분
        String ostrWareDtlDvCd
    ) {}

    @ApiModel(value = "WsnaOutOfStorageIzDtlDto-SearchRes")
    public record SearchRes(
        // 입고창고
        String strWareNm,
        // 입고창고파트너번호
        String strPrtnrNo,
        // 출고일자
        String ostrDt,
        // SAP코드
        String sapMatCd,
        // 품목코드
        String itmPdCd,
        // 품목명
        String pdAbbrNm,
        // 출고유형
        String ostrTpCd,
        // 관리단위
        String mngtUnitNm,
        // 등급
        String itmGdNm,
        // 출고수량
        BigDecimal ostrQty,
        // 박스수량
        BigDecimal boxQty,
        // 배송지코스
        String llshcs,
        // 출고창고
        String ostrWareNm,
        // 입고등록일자
        String strRgstDt,
        // 출고요청번호
        String ostrAkDtlNo,
        // 입고관리번호
        String strDtlNo,
        // 출고관리번호
        String ostrDtlNo
    ) {}
}
