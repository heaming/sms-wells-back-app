package com.kyowon.sms.wells.web.fee.calculation.dto;

import io.swagger.annotations.ApiModel;

public class WfebOgFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WfebOgFeeDto-SearchHmstReq")
    public record SearchHmstReq(
        String perfYm,
        String rsbDv,
        String ogLevl1,
        String ogLevl2,
        String ogLevl3,
        String no
    ) {}

    @ApiModel(value = "WfebOgFeeDto-SearchMngerReq")
    public record SearchMngerReq(
        String perfYm,
        String rsbTp,
        String no,
        String blgCd
    ) {}

    @ApiModel(value = "WfebOgFeeDto-SearchPlarReq")
    public record SearchPlarReq(
        String perfYm,
        String rsbTp,
        String dv,
        String ogLevl1,
        String ogLevl2,
        String ogLevl3
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfebOgFeeDto-SearchHmstRes")
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
        String col39, /*과표합계 */
        String col40, /*과표합계 */
        String col41, /*과표합계 */
        String col42, /*과표합계 */
        String col43, /*과표합계 */
        String col44 /*과표합계 */
    ) {}

    @ApiModel(value = "WfebOgFeeDto-SearchHmstBrmgrRes")
    public record SearchHmstBrmgrRes(
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
        String col40, /*과표합계 */
        String col41, /*과표합계 */
        String col42, /*과표합계 */
        String col43, /*과표합계 */
        String col44, /*과표합계 */
        String col45, /*미팅수수료*/
        String col46, /*정착*/
        String col47, /*재지급*/
        String col48, /*기타지원*/
        String col49, /*과표합계 */
        String col50, /*과표합계 */
        String col51, /*과표합계 */
        String col52 /*과표합계 */
    ) {}

    @ApiModel(value = "WfebOgFeeDto-SearchMngerRes")
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
        String col39,
        String col40,
        String col41,
        String col42,
        String col43,
        String col44,
        String col45,
        String col46,
        String col47,
        String col48,
        String col49,
        String col50,
        String col51,
        String col52,
        String col53,
        String col54,
        String col55,
        String col56,
        String col57,
        String col58,
        String col59,
        String col60,
        String col61,
        String col62,
        String col63,
        String col64,
        String col65,
        String col66,
        String col67,
        String col68,
        String col69,
        String col70,
        String col71,
        String col72,
        String col73,
        String col74,
        String col75,
        String col76,
        String col77,
        String col78,
        String col79,
        String col80,
        String col81,
        String col82,
        String col83,
        String col84,
        String col85,
        String col86,
        String col87,
        String col88,
        String col89,
        String col90,
        String col91,
        String col92,
        String col93
    ) {}
    @ApiModel(value = "WfebOgFeeDto-SearchPlarRes")
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
        String col38, /*기타지원*/
        String col39 /*과표합계 */
    ) {}
}
