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
        String schSlDtEnd

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
        String col14 /*업무해약월*/
    ) {}
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchHmstBsRes")
    public record SearchHmstBsRes(
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
        String col18 /*가전실적*/
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
    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchMngerBsRes")
    public record SearchMngerBsRes(
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
        String col21 /*상조당월*/
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarRes")
    public record SearchPlarRes(
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
        String col38 /*기타지원*/
    ) {}

    @ApiModel(value = "WfeaOrganizationNetOrderDto-SearchPlarSellFeeRes")
    public record SearchPlarSellFeeRes(
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
}
