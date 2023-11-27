package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0261M01 필터 종류별 출고내역 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-09
 */

public class WsnaShippingByFilterTypeDto {

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-SearchPdRes")
    public record SearchPdRes(
        // 품목코드
        String pdCd,
        // 품목명
        String pdNm,
        // 품목그룹
        String itmGrCd

    ) {}

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-SearchReq")
    public record SearchReq(
        // 방문시작일자
        @NotBlank
        @ValidDate
        String strtDt,
        // 방문종료일자
        @NotBlank
        @ValidDate
        String endDt,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 반납여부
        String gbYn,
        // 업무유형
        String svBizHclsfCd,
        // 상위창고
        String hgrWareNo,
        // 창고번호
        String wareNo,
        // 품목그룹
        String itmGrCd,
        // 품목코드
        String itmPdCd,
        // 수거일자
        @ValidDate
        String ostrConfDt

    ) {}

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-SearchRes")
    public record SearchRes(
        // 고객서비스배정번호
        String cstSvAsnNo,
        // 작업출고일련번호
        int wkOstrSn,
        // 소속
        String ogNm,
        // 사번
        String prtnrNo,
        // 성명
        String prtnrKnm,
        // 반납여부
        String stkrPrntYn,
        // 특이사항
        String rmkCn,
        // 계약번호
        String cntrNo,
        // 계약일련번호
        int cntrSn,
        // 계약상세번호
        String cntrDtlNo,
        // 고객명
        String cstKnm,
        // 상품명
        String basePdNm,
        // 수거일자
        String ostrConfDt,
        // 방문일자
        String fnlVstFshDt,
        // 품목코드
        String itmPdCd,
        // 필터종류
        String itmPdNm,
        // 고객유형
        String sellTpNm,
        // 업무유형
        String svBizHclsfNm,
        // 유형상세
        String svBizDclsfNm,
        // 유무상
        String refriDvNm,
        // 우편번호
        String adrZip,
        // 고객주소
        String cstAdr

    ) {}

    @Builder
    @ApiModel("WsnaShippingByFilterTypeDto-EditReq")
    public record EditReq(
        // 고객서비스배정번호
        @NotBlank
        String cstSvAsnNo,
        // 작업출고일련번호
        @Positive
        int wkOstrSn,
        // 반납여부
        String stkrPrntYn,
        // 특이사항
        @Size(max = 4000)
        String rmkCn,
        // 수거일자
        @ValidDate
        String ostrConfDt
    ) {}

}
