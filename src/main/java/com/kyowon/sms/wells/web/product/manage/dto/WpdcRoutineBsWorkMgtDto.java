package com.kyowon.sms.wells.web.product.manage.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 서비스 상품 - B/S 투입정보 관리 DTO
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
public class WpdcRoutineBsWorkMgtDto {

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String svPdCd, /* 서비스상품코드 */
        @NotBlank
        String pdctPdCd, /* 제품상품코드 */
        String partPdCd /* 제품코드 */

    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-SearchRes")
    public record SearchRes(
        String svPdCd, /* 서비스상품코드 */
        String pdctPdCd, /* 제품상품코드 */
        Long dtlSn, /* 상세일련번호 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String vstDvCd, /* 방문구분코드 */
        String filtChngLvCd, /* 필터교체단계코드 */
        String partPdCd, /* 부품상품코드 */
        Long partUseQty, /* 부품사용수량 */
        Long svPrdMmN, /* 서비스주기월수 */
        Long svStrtmmN, /* 서비스시작월수 */
        Long svTms, /* 서비스횟수 */
        Long totStplMcn, /* 총약정개월수 */
        String excdMmVal, /* 제외월값 */
        String istMm, /* 설치월 */
        String strtWkYVal, /* 시작작업년도값 */
        String wkMm /* 작업월 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-SearchStdBaseReq")
    public record SearchStdBaseReq(
        String svPdCd, /* 서비스 코드 */
        String svPdNm, /* 서비스명 */
        String pdctPdNm, /* 교재자재 상품명 */
        String pdctPdCd /* 교재자재 상품코드 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-SearchRoutineBsWorkBaseRes")
    public record SearchRoutineBsWorkBaseRes(
        String partPdNm, /* 제품상품명 */
        String dtlHasYn, /* B/S 투입 상세 데이터 존재 여부 */
        String svPdCd, /* 서비스상품코드 */
        String svPdNm, /* 서비스상품명 */
        String pdctPdCd, /* 제품상품코드 */
        String pdctPdNm, /* 제품상품명 */
        Long dtlSn, /* 상세일련번호 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String vstDvCd, /* 방문구분코드 */
        String filtChngLvCd, /* 필터교체단계코드 */
        String partPdCd, /* 부품상품코드 */
        Long partUseQty, /* 부품사용수량 */
        Long svPrdMmN, /* 서비스주기월수 */
        Long svStrtmmN, /* 서비스시작월수 */
        Long svTms, /* 서비스횟수 */
        Long totStplMcn, /* 총약정개월수 */
        String excdMmVal, /* 제외월값 */
        String istMm, /* 설치월 */
        String strtWkYVal, /* 시작작업년도값 */
        String wkMm /* 작업월 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-SearchRoutineBsWorkDetailRes")
    public record SearchRoutineBsWorkDetailRes(
        String partPdNm, /* 제품상품명 */
        String svPdCd, /* 서비스상품코드 */
        String pdctPdCd, /* 제품상품코드 */
        Long dtlSn, /* 상세일련번호 */
        Long vstNmnN, /* 방문차월수 */
        String vstDvCd, /* 방문구분코드 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String filtChngLvCd, /* 필터교체단계코드 */
        String partPdCd, /* 부품상품코드 */
        Long partUseQty, /* 부품사용수량 */
        String vstNmnPrdVal, /* 방문차월주기값 */
        String istMm, /* 설치월 */
        String strtWkYVal, /* 시작작업년도값 */
        String wkMm, /* 작업월 */
        Long totStplMcn, /* 총약정개월수 */
        String cstmwPdctYn /* 맞춤형제품여부 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-SearchLifeCustomFiltersRes")
    public record SearchLifeCustomFiltersRes(
        String fstRgstUsrId, /* 등록자 */
        String fnlMdfcUsrId, /* 수정자 */
        String fstRgstDtm, /* 등록일 */
        String fstRgstUsrNm, /* 등록자명 */
        String fnlMdfcDtm, /* 수정일 */
        String fnlMdfcUsrNm, /* 수정자명 */

        String chPdctPdNm, /* 변경제품상품명 */
        String svPdCd, /* 서비스상품코드 */
        String pdctPdCd, /* 제품상품코드 */
        String partPdCd, /* 부품상품코드 */
        Long dtlSn, /* 상세일련번호 */
        String vstDvCd, /* 방문구분코드 */
        String prdMmVal, /* 주기월값 */
        String chPdctPdCd, /* 변경제품상품코드 */
        String filtCmuCdv, /* 필터공용코드값 */
        String filtCmuNm, /* 필터공용명 */
        String filtCmuEpl /* 필터공용설명 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String svPdCd, /* 서비스상품코드 */
        @NotBlank
        String pdctPdCd, /* 제품상품코드 */
        List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkBase> bases, /* 기본정보 */
        List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkDetail> details /* 상세정보 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-EditDetailReq")
    public record EditDetailReq(
        @NotEmpty
        List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkDetail> details /* 상세정보 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-EditLifeCustomFilterReq")
    public record EditLifeFilterReq(
        @NotBlank
        String svPdCd, /* 서비스상품코드 */
        @NotBlank
        String pdctPdCd, /* 제품상품코드 */
        @NotBlank
        String partPdCd, /* 부품코드 */
        List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> bases /* 기본정보 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-RemoveLifeFilterReq")
    public record RemoveLifeFilterReq(
        @NotEmpty
        List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> bases /* 기본정보 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-RoutineBsWorkBase")
    public record RoutineBsWorkBase(
        String svPdCd, /* 서비스상품코드 */
        String pdctPdCd, /* 제품상품코드 */
        Long dtlSn, /* 상세일련번호 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String vstDvCd, /* 방문구분코드 */
        String filtChngLvCd, /* 필터교체단계코드 */
        String partPdCd, /* 부품상품코드 */
        Long partUseQty, /* 부품사용수량 */
        Long svPrdMmN, /* 서비스주기월수 */
        Long svStrtmmN, /* 서비스시작월수 */
        Long svTms, /* 서비스횟수 */
        Long totStplMcn, /* 총약정개월수 */
        String excdMmVal, /* 제외월값 */
        String istMm, /* 설치월 */
        String strtWkYVal, /* 시작작업년도값 */
        String wkMm, /* 작업월 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-RoutineBsWorkDetail")
    public record RoutineBsWorkDetail(
        String svPdCd, /* 서비스상품코드 */
        String pdctPdCd, /* 제품상품코드 */
        Long dtlSn, /* 상세일련번호 */
        Long vstNmnN, /* 방문차월수 */
        String vstDvCd, /* 방문구분코드 */
        String svBizDclsfCd, /* 서비스업무세분류코드 */
        String filtChngLvCd, /* 필터교체단계코드 */
        String partPdCd, /* 부품상품코드 */
        Long partUseQty, /* 부품사용수량 */
        String vstNmnPrdVal, /* 방문차월주기값 */
        String istMm, /* 설치월 */
        String strtWkYVal, /* 시작작업년도값 */
        String wkMm, /* 작업월 */
        Long totStplMcn, /* 총약정개월수 */
        String cstmwPdctYn, /* 맞춤형제품여부 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}

    @ApiModel(value = "WpdcRoutineBsWorkMgtDto-LifeCustomFilterBase")
    public record LifeCustomFilterBase(
        String svPdCd, /* 서비스상품코드 */
        String pdctPdCd, /* 제품상품코드 */
        String partPdCd, /* 부품상품코드 */
        Long dtlSn, /* 상세일련번호 */
        String vstDvCd, /* 방문구분코드 */
        String prdMmVal, /* 주기월값 */
        String chPdctPdCd, /* 변경제품상품코드 */
        String filtCmuCdv, /* 필터공용코드값 */
        String filtCmuNm, /* 필터공용명 */
        String filtCmuEpl, /* 필터공용설명 */
        String dtaDlYn /* 데이터삭제여부 */
    ) {}
}
