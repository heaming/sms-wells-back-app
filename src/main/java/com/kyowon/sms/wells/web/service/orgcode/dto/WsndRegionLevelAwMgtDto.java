package com.kyowon.sms.wells.web.service.orgcode.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.14
 */
public class WsndRegionLevelAwMgtDto {

    @ApiModel(value = "WsndRegionLevelAwMgtDto-SearchRes")
    public record SearchRes(
        List<Allowance> movementAllowances, // 이동급지 리스트
        List<Allowance> bizAllowances // 업무급지 리스트
    ) {}

    @ApiModel(value = "WsndRegionLevelAwMgtDto-Allowance")
    public record Allowance(
        BigDecimal chAwAmt, // 변경수당금액
        String apyStrtdt, // 유효시작일시
        String apyEnddt, // 유효종료일시
        String rglvlGdCd, // 급지등급코드
        BigDecimal rglvlAwAmt, // 급지수당금액
        String bizRglvlCd, // 업무급지코드
        String rglvlDvCd, // 급지구분코드
        Long mmtLdtm, // 이동소요시간
        String fstRgstDtm, // 최초등록일시
        String fstRgstUsrId, // 최초등록사용자ID
        String rgstNm, // 사용자명
        Integer izSn, // 내역일련번호
        Long mmtDstn, // 이동거리
        Integer minPerManho, // 분당공수
        Integer rglvlWeit, // 급지비중
        Integer avVe // 평균속도
    ) {}

    @ApiModel(value = "WsndRegionLevelAwMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rglvlDvCd, // 급지구분코드
        @NotBlank
        String bizRglvlCd, // 업무급지코드
        @NotBlank
        String apyStrtdt, // 유효시작일시
        @NotBlank
        String mmtLdtm, // 이동소요시간
        @NotBlank
        String mmtDstn, // 이동거리
        @NotBlank
        String rglvlGdCd, // 급지등급코드
        @NotNull
        BigDecimal rglvlAwAmt, // 급지수당금액
        @NotNull
        Integer minPerManho, // 분당공수
        @NotNull
        Integer rglvlWeit, // 급지비중
        Integer avVe // 평균속도
    ) {}

}
