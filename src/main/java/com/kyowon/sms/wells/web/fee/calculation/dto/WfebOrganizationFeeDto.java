package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WfebOrganizationFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WfebOrganizationFeeDto-SearchHmstReq")
    public record SearchHmstReq(
        String perfYm,
        String rsbDv,
        String ogLevl1,
        String ogLevl2,
        String ogLevl3,
        String no
    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SearchMngerReq")
    public record SearchMngerReq(
        String perfYm,
        String rsbTp,
        String no,
        String blgCd
    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SearchPlarReq")
    public record SearchPlarReq(
        String perfYm,
        String rsbTp,
        String no,
        String ogLevl1,
        String ogLevl2,
        String ogLevl3
    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SearchWmReq")
    public record SearchWmReq(
        @NotBlank
        String perfYm,
        String no

    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String perfYm
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfebOrganizationFeeDto-SearchHmstRes")
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

    @ApiModel(value = "WfebOrganizationFeeDto-SearchHmstBrmgrRes")
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

    @ApiModel(value = "WfebOrganizationFeeDto-SearchMngerRes")
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
        String col80
    ) {}
    @ApiModel(value = "WfebOrganizationFeeDto-SearchMngerBrmgrRes")
    public record SearchMngerBrmgrRes(
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
        String col79
    )

    {}
    @ApiModel(value = "WfebOrganizationFeeDto-SearchMngerTotalRes")
    public record SearchMngerTotalRes(
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
        String col88
    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SearchPlarRes")
    public record SearchPlarRes(
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String rsbDvCd,
        String akcuil,
        String jagyuk,
        String nJagyuk1,
        String is11edu,
        String is17edu,
        String cntrDt,
        String fstCntrDt,
        String rcntrDt,
        String fnlCltnDt,
        String cltnDt,
        String akstym,
        String lcecaymd,
        String bAksd05,
        String akcda0,
        String akcda1,
        String akcda2,
        String aksq01,
        String nAksq01,
        String akcda3,
        String akcda4,
        String akdeq5,
        String ec5amt,
        String mproduct,
        String aksd23,
        String aksd24,
        String aksd25,
        String aksd01,
        String aksd02,
        String aksd03,
        String aksd21,
        String aksd04,
        String aksd05,
        String aksd20,
        String aksd30,
        String aksd99
    ) {}
    @ApiModel(value = "WfebOrganizationFeeDto-SearchPlarBrmgrRes")
    public record SearchPlarBrmgrRes(
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String rsbDvCd,
        String jagyuk,
        String nJagyuk1,
        String cntrDt,
        String fstCntrDt,
        String fnlCltnDt,
        String cltnDt,
        String gadcnt,
        String is17edu,
        String akdriy,
        String akstym,
        String lcecaymd,
        String akcda0,
        String akcda1,
        String akcda2,
        String aksq01,
        String nAksq01,
        String akcdag0,
        String akcdag1,
        String aksqg02,
        String akcda3,
        String akcda4,
        String akdeq5,
        String ec5amt,
        String mproduct,
        String aksd23,
        String aksd24,
        String aksd25,
        String aksd01,
        String aksd02,
        String aksd03,
        String aksd11,
        String aksd12,
        String aksd13,
        String bAksd05,
        String aksd16,
        String aksd17,
        String aksd15,
        String aksd20,
        String aksd21,
        String aksd22,
        String aksd30,
        String aksd99
    ) {}
    @ApiModel(value = "WfebOrganizationFeeDto-SearchPlarTotalRes")
    public record SearchPlarTotalRes(
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String rsbDvCd,
        String akcuil,
        String jagyuk,
        String nJagyuk1,
        String is11edu,
        String is17edu,
        String cntrDt,
        String fstCntrDt,
        String rcntrDt,
        String fnlCltnDt,
        String cltnDt,
        String lcecaymd,
        String gadcnt,
        String akstym,
        String akdriy,
        String bAksd05,
        String akcda0,
        String akcda1,
        String akcda2,
        String aksq01,
        String nAksq01,
        String akcdag0,
        String akcdag1,
        String aksqg02,
        String akcda3,
        String akcda4,
        String akdeq5,
        String ec5amt,
        String mproduct,
        String aksd24,
        String aksd25,
        String aksd01,
        String aksd02,
        String aksd03,
        String aksd21,
        String aksd11,
        String aksd12,
        String aksd13,
        String aksd22,
        String aksd04,
        String aksd05,
        String aksd16,
        String aksd17,
        String aksd15,
        String aksd20,
        String aksd30,
        String aksd99,
        String ddctam,
        String dsbOjAmt
    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SearchWmRes")
    public record SearchWmRes(
        String col1, /*총괄단*/
        String col2, /*지역단*/
        String col3, /*지점*/
        String col4, /*번호*/
        String col5, /*성명*/
        String col6, /*직책*/
        int col7, /*차월*/
        String col8, /*개시월*/
        String col9, /*해약월*/
        String col10, /*최종해약월*/
        int col11, /*재개시차월*/
        String col12, /*정착수수료지급유형*/
        int col13, /*수수료계*/
        int col14, /*BS인정건수*/
        int col15, /*인정건수*/
        int col16, /*기변1건수*/
        int col17, /*대상건수*/
        int col18, /*방문완료건수*/
        int col19, /*W1급지건수*/
        int col20, /*W2급지건수*/
        int col21, /*방문실적수수료*/
        int col22, /*수수료율*/
        int col23, /*방문수수료*/
        int col24, /*판매장려수수료*/
        int col25, /*판매미팅수수료*/
        int col26, /*정착수수료*/
        int col27, /*정착보완수수료*/
        int col28, /*급지수수료*/
        int col29, /*자재실장수수료*/
        int col30, /*통신보조수수료*/
        int col31, /*기타수수료*/
        int col32, /*유니폼수수료*/
        int col33, /*사전방문수수료*/
        int col34, /*스타트업월*/
        int col35, /*OJT일수*/
        int col36, /*5일배정수*/
        String col37, /*중급수료여부*/
        String col38, /*매니저정착2 수료여부*/
        int col39, /*미팅일수*/
        String col40 /*보수교육여부*/
    ) {}
}
