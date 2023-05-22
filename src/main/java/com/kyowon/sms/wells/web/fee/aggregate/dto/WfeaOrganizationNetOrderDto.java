package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * 조직별 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
public class WfeaOrganizationNetOrderDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 조직별 실적 집계 Search Request Dto
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchReq")
    public record SearchReq(

    ) {}

    @Builder
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SaveOgNetOrderReq")
    public record SaveOgNetOrderReq(
        @NotBlank
        String perfYm,

        @NotBlank
        String ogTp
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SaveBsReq")
    public record SaveBsReq(
        @NotBlank
        String perfYm
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstReq")
    public record SearchHmstReq(
        @NotBlank
        String schTcnt,
        @NotBlank
        String schDv,
        String schPdctTp,
        String schPdCdStrt,
        String schPdCdEnd,
        String schSlDtStrt,
        String schSlDtEnd,
        String schRcpDtStrt,
        String schRcpDtEnd,
        String schPerfYm,
        String schVstDtStrt,
        String schVstDtEnd
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerReq")
    public record SearchMngerReq(
        @NotBlank
        String schBizDv,
        @NotBlank
        String schDv,
        String schPdctTp,
        String schPdCdStrt,
        String schPdCdEnd,
        String schSlDtStrt,
        String schSlDtEnd,
        String schRcpDtStrt,
        String schRcpDtEnd,
        String schPerfYm,
        String schVstDtStrt,
        String schVstDtEnd
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarReq")
    public record SearchPlarReq(
        @NotBlank
        String schDv,
        @NotBlank
        String schPdctTp,
        String schPdCdStrt,
        String schPdCdEnd,
        String schSlDtStrt,
        String schSlDtEnd,
        String schRcpDtStrt,
        String schRcpDtEnd,
        String schRsvDtStrt,
        String schRsvDtEnd,
        String perfYm

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 조직별 실적 집계 Search Result Dto
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchRes")
    public record SearchRes(

    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstRes")
    public record SearchHmstRes(
        String col1, /*소속*/
        String col2, /*번호*/
        String col3, /*성명*/
        String col4, /*직책*/
        String col5, /*미팅일수*/
        String col6, /*수수료 월*/
        String col7, /*M+1 수석플래너*/
        String col8, /*플래너 스타트업*/
        String col9, /*수석실전*/
        String col10, /*등록기준월*/
        String col11, /*최초업무등록월*/
        String col12, /*재등록월*/
        String col13, /*최종해약월*/
        String col14, /*업무해약월*/
        String col15, /*승진월*/
        String col16, /*웰스매니저 개시일*/
        String col17, /*정착수수료*/
        String col18, /*가전실적*/
        String col19, /*가전외 실적*/
        String col20, /*기변*/
        String col21, /*상조당월*/
        String col22, /*상조유지*/
        String col23, /*환경*/
        String col24, /*환경외*/
        String col25, /*라이브팩 건수*/
        String col26, /*홈케어 판매가*/
        String col27, /*관리상품 판매건수*/
        String col28, /*정액 수수료*/
        String col29, /*라이브팩*/
        String col30, /*홈케어*/
        String col31, /*가전개인비례*/
        String col32, /*가전외 개인비례*/
        String col33, /*판매장려*/
        String col34, /*상조수수료*/
        String col35, /*미팅수수료*/
        String col36, /*정착*/
        String col37, /*재지급*/
        String col38, /*기타지원*/
        String col39 /*과표합계 */
    ) {}
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstSellFeeRes")
    public record SearchHmstSellFeeRes(
        String og2Nm, /* 지역단 */
        String og3Nm, /* 지점 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 성명 */
        String cntrNo, /*계약상세번호*/
        String rcpDt, /*접수일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String prdtyp, /*제품유형*/
        String pdCd, /*상품코드*/
        String prdgrp, /*상품그룹*/
        String pdNm, /*상품명*/
        String ackmtPerfCt, /*인정건수*/
        String mchnChTpCd /*기변유형*/
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerRes")
    public record SearchMngerRes(
        String col1, /*소속*/
        String col2, /*번호*/
        String col3, /*성명*/
        String col4, /*직책*/
        String col5, /*미팅일수*/
        String col6, /*수수료 월*/
        String col7, /*M+1 수석플래너*/
        String col8, /*플래너 스타트업*/
        String col9, /*수석실전*/
        String col10, /*등록기준월*/
        String col11, /*최초업무등록월*/
        String col12, /*재등록월*/
        String col13, /*최종해약월*/
        String col14, /*업무해약월*/
        String col15, /*승진월*/
        String col16, /*웰스매니저 개시일*/
        String col17, /*정착수수료*/
        String col18, /*가전실적*/
        String col19, /*가전외 실적*/
        String col20, /*기변*/
        String col21, /*상조당월*/
        String col22, /*상조유지*/
        String col23, /*환경*/
        String col24, /*환경외*/
        String col25, /*라이브팩 건수*/
        String col26, /*홈케어 판매가*/
        String col27, /*관리상품 판매건수*/
        String col28, /*정액 수수료*/
        String col29, /*라이브팩*/
        String col30, /*홈케어*/
        String col31, /*가전개인비례*/
        String col32, /*가전외 개인비례*/
        String col33, /*판매장려*/
        String col34, /*상조수수료*/
        String col35, /*미팅수수료*/
        String col36, /*정착*/
        String col37, /*재지급*/
        String col38, /*기타지원*/
        String col39, /*과표합계 */
        String col40,
        String col41
    ) {}
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerSellFeeRes")
    public record SearchMngerSellFeeRes(
        String col1, /*소속*/
        String col2, /*번호*/
        String col3, /*성명*/
        String col4, /*직책*/
        String col5, /*미팅일수*/
        String col6, /*수수료 월*/
        String col7, /*M+1 수석플래너*/
        String col8, /*플래너 스타트업*/
        String col9, /*수석실전*/
        String col10, /*등록기준월*/
        String col11, /*최초업무등록월*/
        String col12, /*재등록월*/
        String col13, /*최종해약월*/
        String col14, /*업무해약월*/
        String col15, /*승진월*/
        String col16 /*웰스매니저 개시일*/
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarRes")
    public record SearchPlarRes(
        String og1Nm, /* 총괄단 */
        String og2Nm, /* 지역단 */
        String og3Nm, /* 지점 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 성명 */
        String gubn, /*판매유형*/
        String prdtType, /*제품유형*/
        String lcflg1, /*기변유형*/
        String cntrNo, /*계약상세번호*/
        String lccgub, /*고객구분*/
        String pdNm, /*상품명*/
        String pdCd, /*상품코드*/
        String lciuse, /*용도*/
        String lcetc3, /*할인구분*/
        String lcetc4, /*할인유형*/
        String lcst11, /*할인제도*/
        String lcst04, /*결합구분*/
        String fnlVal, /*입금실적*/
        String lcmont, /*할부*/
        String lcbamt, /*기준가격*/
        String lctamt, /*홈케어*/
        String lcgub1, /*홈케어멤버십3년*/
        String lcst13, /*정액여부*/
        String leaseYn, /*금융리스*/
        String lcpcnt, /*인정건수*/
        String rcntYn, /*재약정여부*/
        String lccrtt, /*계약일자*/
        String lcslet, /*매출일자*/
        String lccant, /*취소일자*/
        String akdbon, /*지점장번호*/
        String akdbnm, /*지점장성명*/
        String lcamt1, /*렌탈료*/
        String lcgub3, /*약정기간*/
        String lcvmon, /*관리주기*/
        String lcpamt, /*인정실적금액*/
        String lcck02, /*프로모션번호*/
        String lcgseq, /*패키지일련번호*/
        String lcpaym /*순주문월*/
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarSellFeeRes")
    public record SearchPlarSellFeeRes(
        String og1Nm, /* 총괄단 */
        String og2Nm, /* 지역단 */
        String og3Nm, /* 지점 */
        String prtnrNo, /* 번호 */
        String prtnrKnm, /* 성명 */
        String cntrNo, /*계약상세번호*/
        String rcpDt, /*접수일자*/
        String slDt, /*매출일자*/
        String canDt, /*취소일자*/
        String prdtyp, /*제품유형*/
        String pdCd, /*상품코드*/
        String prdgrp, /*상품그룹*/
        String pdNm, /*상품명*/
        String ackmtPerfCt, /*인정건수*/
        String bfsvcPrdCd, /*BS주기*/
        String mchnChTpCd /*기변유형*/
    ) {}
}
