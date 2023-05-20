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
        String schOrdr,
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
        String dgr1LevlOgNm, /*지역단*/
        String dgr2LevlOgNm, /*지점*/
        String prtnrNo, /*번호*/
        String prtnrKnm, /*성명*/
        String rsbDvCd, /*직책*/
        String akdsym, /* 업무 등록월 */
        String akdcha, /* 차월 */
        String prtnrGdCd, /* 급수 */
        String edu100, /*신입입문교육*/
        String akcda10, /* 매트리스 */
        String akcda12, /* 매트리스 외*/
        String akcda14, /* 정액 */
        String akcda13, /* 환경가전 */
        String akcda15, /* 환경가전 외 */
        String akcda11, /* 홈케어패키지 */
        String akcda19, /* 홈케어 멤버십*/
        String dedeq3, /* 기변(1)건수 */
        String akdeq0, /* 총 판매건수 */
        String lccnt1, /* 배정건수 */
        String cLccnt1, /* 취소건수 */
        String rLccnt1, /* 실배정건 */
        String gdSercnt, /* 세탁기/에어컨/건조기 건수 */
        String elecnt, /* 가전 건수 */
        String sercnt, /* 총 서비스건수 */
        String serryl, /* 처리율 */
        String aksd01, /* 비례 */
        String aksd03, /* 장려 */
        String aksd04, /* 일시불 */
        String aksd15, /* 기변 */
        String aksd05, /* 홈케어멤버십 */
        String aksd06, /* 서비스현장 */
        String aksd07, /* 서비스활동 */
        String aksd09, /* 서비스누적 */
        String aksd10, /* 교육 */
        String aksd11, /* 급지 */
        String aksd13, /* 기타지원 */
        String aksd14, /* 재지급 */
        String intbsSum, /*과표합계*/
        String ddtnSum, /*총공제액*/
        String aclDdbAmt /*실지급액*/
    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SearchHmstBrmgrRes")
    public record SearchHmstBrmgrRes(
        String dgr1LevlOgNm, /*지역단*/
        String dgr2LevlOgNm, /*지점*/
        String prtnrNo, /*번호*/
        String prtnrKnm, /*성명*/
        String rsbDvCd, /*직책*/
        String akdsym, /* 업무 등록월 */
        String akdcha, /* 차월 */
        String prtnrGdCd, /* 급수 */
        String atcnt1, /*교육참여일수 집합교육*/
        String atcnt2, /*교육참여일수 동행교육*/
        String is124edu, /*교육수료일수 집합교육*/
        String is125edu, /*교육수료일수 동행교육*/
        String akcda10, /* 매트리스 */
        String akcda12, /* 매트리스 외*/
        String akcda14, /* 정액 */
        String akcda13, /* 환경가전 */
        String akcda15, /* 환경가전 외 */
        String akcda11, /* 홈케어패키지 */
        String akcda19, /* 홈케어 멤버십*/
        String dedeq3, /* 기변(1)건수 */
        String akdeq0, /* 총 판매건수 */
        String lccnt1, /* 배정건수 */
        String cLccnt1, /* 취소건수 */
        String rLccnt1, /* 실배정건 */
        String gdSercnt, /* 세탁기/에어컨/건조기 건수 */
        String elecnt, /* 가전 건수 */
        String sercnt, /* 총 서비스건수 */
        String serryl, /* 처리율 */
        String aksd01, /* 비례 */
        String aksd03, /* 장려 */
        String aksd04, /* 일시불 */
        String aksd15, /* 기변 */
        String aksd05, /* 홈케어멤버십 */
        String aksd06, /* 서비스현장 */
        String aksd07, /* 서비스활동 */
        String aksd09, /* 서비스누적 */
        String aksd10, /* 교육 */
        String aksd11, /* 급지 */
        String aksd13, /* 기타지원 */
        String aksd14, /* 재지급 */
        String gadcnt, /* 실활동인원 */
        String jAkdeq0, /* 인정건수 */
        String jSercnt, /* 서비스건수 */
        String aksd51, /* 렌탈 */
        String aksd52, /* 일시불/할부 */
        String aksd53, /* 판매연계 */
        String aksd54, /* 조직관리 */
        String aksd55, /* 신규판매 */
        String aksd56, /* 서비스연계 */
        String intbsSum, /*과표합계*/
        String ddtnSum, /*총공제액*/
        String aclDdbAmt /*실지급액*/
    ) {}

    @ApiModel(value = "WfebOrganizationFeeDto-SearchMngerRes")
    public record SearchMngerRes(
        String ogNm, /*소속*/
        String prtnrNo, /*번호*/
        String prtnrKnm, /*성명*/
        String rsbDvCd, /*직책*/
        String akcuil, /*미팅일수*/
        String akdmi3, /*자격수수료월*/
        String akdmi32, /*자격M+1*/
        String edustr, /*스타트업매니저*/
        String edu011, /*스타트업플래너*/
        String edu129, /*보수교육*/
        String edu106, /*수석플래너교육정착*/
        String akdcha, /*수석플래너교육차월*/
        String edu017, /*수석플래너교육실전*/
        String ojtcnt, /*OJT일수*/
        String akdsym, /*등록기준월*/
        String akdenm, /*최초업무등록월*/
        String akdjem, /*재등록월*/
        String akdlym, /*최종해약월*/
        String akdcym, /*업무해약월*/
        String akdrym, /*승진월*/
        String sustym, /*수석플래너등록월*/
        String suscym, /*수석플래너해약월*/
        String lccoym, /*WM개시*/
        String lcccym, /*WM해약*/
        String dalsyn, /*실활동달성*/
        String bondalsyn, /*최소기준달성*/
        String sungnm, /*전월대비구분*/
        String wpscnt, /*WP*/
        String wmscnt, /*WM*/
        String spscnt, /*SP*/
        String dancnt, /*총인원*/
        String akcda17, /*인정실적렌탈*/
        String akcda18, /*인정실적일시불*/
        String akcda1, /*인정실적환경가전외*/
        String akcdq1, /*가전순주문건수*/
        String akcdq0, /*개인가전인정건수*/
        String akcdq7, /*개인BS인정건수*/
        String akdet3, /*가전기변건수*/
        String jasamt, /*가전기변금액*/
        String akcda12, /*환경가전렌탈*/
        String akcda13, /*환경가전일시불*/
        String akcda14, /*환경가전정액*/
        String akcda15, /*환경가전외일시불*/
        String akcda16, /*환경가전외정액*/
        String akcdq5, /*라이브팩건수*/
        String akcdq6, /*개인신규판매건수*/
        String akcda19, /*홈케어판매가*/
        String jakcdq0, /*조직가전인정건수*/
        String jakcdq6, /*조직신규판매건수*/
        String jakcda12, /*조직환경가전렌탈*/
        String jakcda13, /*조직환경가전일시불*/
        String jakcda15, /*조직환경가전외일시불*/
        String aksd49, /*홈케어*/
        String aksd43, /*라이브팩*/
        String aksd26, /*가전비례*/
        String aksd29, /*가전외비례*/
        String sd26m1, /*가전미팅*/
        String sd26m2, /*일시불미팅*/
        String sd26mt, /*가전외미팅*/
        String aksd17, /*판매장려*/
        String aksd50, /*교육*/
        String aksd48, /*정착*/
        String aksd04, /*기기변경*/
        String aksd15, /*BS관리*/
        String aksd23, /*급지*/
        String aksd42, /*판매장려소급*/
        String aksd31, /*가전조직비례*/
        String aksd34, /*가전조직외비례*/
        String aksd11, /*조직판매장려*/
        String aksd08, /*신규판매*/
        String aksd22, /*조직관리*/
        String aksd13, /*조직배출1*/
        String aksd09, /*조직배출2*/
        String aksd36, /*신설지점*/
        String aksd05, /*멤버십*/
        String aksd14, /*재지급*/
        String aksd39, /*기타지원*/
        String aksd18, /*WM통신*/
        String aksd19, /*WM기타*/
        String aksd40, /*사전방문*/
        String aksd16, /*유니폼*/
        String aksd10, /*자재실장*/
        String lccntt, /*BS완료계정*/
        String intbsSum, /*과표합계*/
        String ddtnSum, /*총공제액*/
        String aclDsbAmt /*실지급액*/
    ) {}
    @ApiModel(value = "WfebOrganizationFeeDto-SearchMngerBrmgrRes")
    public record SearchMngerBrmgrRes(
        String ogNm, /*소속*/
        String prtnrNo, /*번호*/
        String prtnrKnm, /*성명*/
        String rsbDvCd, /*직책*/
        String akcuil, /*미팅일수*/
        String akdmi3, /*자격수수료월*/
        String akdmi32, /*자격M+1*/
        String edustr, /*스타트업매니저*/
        String edu011, /*스타트업플래너*/
        String edu129, /*보수교육*/
        String edu106, /*수석플래너교육정착*/
        String akdcha, /*수석플래너교육차월*/
        String edu017, /*수석플래너교육실전*/
        String edu135, /*지점장온라인*/
        String akdsym, /*등록기준월*/
        String akdenm, /*최초업무등록월*/
        String akdjem, /*재등록월*/
        String akdlym, /*최종해약월*/
        String akdcym, /*업무해약월*/
        String akdrym, /*승진월*/
        String sustym, /*수석플래너등록월*/
        String suscym, /*수석플래너해약월*/
        String lccoym, /*WM개시*/
        String lcccym, /*WM해약*/
        String bondalsyn, /*최소기준달성*/
        String sungnm, /*전월대비구분*/
        String wpscnt, /*WP*/
        String wmscnt, /*WM*/
        String spscnt, /*SP*/
        String dancnt, /*총인원*/
        String akcda17, /*인정실적렌탈*/
        String akcda18, /*인정실적일시불*/
        String akcda1, /*인정실적환경가전외*/
        String akcdq1, /*가전순주문건수*/
        String akcdq0, /*개인가전인정건수*/
        String akcdq7, /*개인BS인정건수*/
        String akdet3, /*가전기변건수*/
        String jasamt, /*가전기변금액*/
        String akcda12, /*환경가전렌탈*/
        String akcda13, /*환경가전일시불*/
        String akcda14, /*환경가전정액*/
        String akcda15, /*환경가전외일시불*/
        String akcda16, /*환경가전외정액*/
        String akcdq5, /*라이브팩건수*/
        String akcdq6, /*개인신규판매건수*/
        String akcda19, /*홈케어판매가*/
        String jakcdq0, /*조직가전인정건수*/
        String jakcdq6, /*조직신규판매건수*/
        String jakcda12, /*조직환경가전렌탈*/
        String jakcda13, /*조직환경가전일시불*/
        String jakcda15, /*조직환경가전외일시불*/
        String aksd49, /*홈케어*/
        String aksd43, /*라이브팩*/
        String aksd26, /*가전비례*/
        String aksd29, /*가전외비례*/
        String aksd17, /*판매장려*/
        String aksd50, /*교육*/
        String aksd48, /*정착*/
        String aksd04, /*기기변경*/
        String aksd15, /*BS관리*/
        String aksd23, /*급지*/
        String aksd42, /*판매장려소급*/
        String aksd31, /*가전조직비례*/
        String aksd34, /*가전조직외비례*/
        String aksd11, /*조직판매장려*/
        String aksd08, /*신규판매*/
        String aksd22, /*조직관리*/
        String aksd06, /*추가장려*/
        String aksd13, /*조직배출1*/
        String aksd09, /*조직배출2*/
        String aksd36, /*신설지점*/
        String aksd05, /*멤버십*/
        String aksd14, /*재지급*/
        String aksd39, /*기타지원*/
        String aksd18, /*WM통신*/
        String aksd19, /*WM기타*/
        String aksd40, /*사전방문*/
        String aksd16, /*유니폼*/
        String aksd10, /*자재실장*/
        String lccntt, /*BS완료계정*/
        String intbsSum, /*과표합계*/
        String ddtnSum, /*총공제액*/
        String aclDsbAmt /*실지급액*/
    )

    {}
    @ApiModel(value = "WfebOrganizationFeeDto-SearchMngerTotalRes")
    public record SearchMngerTotalRes(
        String ogNm, /*소속*/
        String prtnrNo, /*번호*/
        String prtnrKnm, /*성명*/
        String rsbDvCd, /*직책*/
        String akcuil, /*미팅일수*/
        String akdmi3, /*자격수수료월*/
        String akdmi32, /*자격M+1*/
        String edustr, /*스타트업매니저*/
        String edu011, /*스타트업플래너*/
        String edu129, /*보수교육*/
        String edu106, /*수석플래너교육정착*/
        String akdcha, /*수석플래너교육차월*/
        String edu017, /*수석플래너교육실전*/
        String edu135, /*지점장온라인*/
        String ojtcnt, /*OJT일수*/
        String akdsym, /*등록기준월*/
        String akdenm, /*최초업무등록월*/
        String akdjem, /*재등록월*/
        String akdlym, /*최종해약월*/
        String akdcym, /*업무해약월*/
        String akdrym, /*승진월*/
        String sustym, /*수석플래너등록월*/
        String suscym, /*수석플래너해약월*/
        String lccoym, /*WM개시*/
        String lcccym, /*WM해약*/
        String dalsyn, /*실활동달성*/
        String bondalsyn, /*최소기준달성*/
        String sungnm, /*전월대비구분*/
        String wpscnt, /*WP*/
        String wmscnt, /*WM*/
        String spscnt, /*SP*/
        String dancnt, /*총인원*/
        String akcda17, /*인정실적렌탈*/
        String akcda18, /*인정실적일시불*/
        String akcda1, /*인정실적환경가전외*/
        String akcdq1, /*가전순주문건수*/
        String akcdq0, /*개인가전인정건수*/
        String akcdq7, /*개인BS인정건수*/
        String akdet3, /*가전기변건수*/
        String jasamt, /*가전기변금액*/
        String akcda12, /*환경가전렌탈*/
        String akcda13, /*환경가전일시불*/
        String akcda14, /*환경가전정액*/
        String akcda15, /*환경가전외일시불*/
        String akcda16, /*환경가전외정액*/
        String akcdq5, /*라이브팩건수*/
        String akcdq6, /*개인신규판매건수*/
        String akcda19, /*홈케어판매가*/
        String jakcdq0, /*조직가전인정건수*/
        String jakcdq6, /*조직신규판매건수*/
        String jakcda12, /*조직환경가전렌탈*/
        String jakcda13, /*조직환경가전일시불*/
        String jakcda15, /*조직환경가전외일시불*/
        String aksd49, /*홈케어*/
        String aksd43, /*라이브팩*/
        String aksd26, /*가전비례*/
        String aksd29, /*가전외비례*/
        String sd26m1, /*가전미팅*/
        String sd26m2, /*일시불미팅*/
        String sd26mt, /*가전외미팅*/
        String aksd17, /*판매장려*/
        String aksd50, /*교육*/
        String aksd48, /*정착*/
        String aksd04, /*기기변경*/
        String aksd15, /*BS관리*/
        String aksd23, /*급지*/
        String aksd42, /*판매장려소급*/
        String aksd31, /*가전조직비례*/
        String aksd34, /*가전조직외비례*/
        String aksd11, /*조직판매장려*/
        String aksd08, /*신규판매*/
        String aksd22, /*조직관리*/
        String aksd06, /*추가장려*/
        String aksd13, /*조직배출1*/
        String aksd09, /*조직배출2*/
        String aksd36, /*신설지점*/
        String aksd05, /*멤버십*/
        String aksd14, /*재지급*/
        String aksd39, /*기타지원*/
        String aksd18, /*WM통신*/
        String aksd19, /*WM기타*/
        String aksd40, /*사전방문*/
        String aksd16, /*유니폼*/
        String aksd10, /*자재실장*/
        String lccntt, /*BS완료계정*/
        String intbsSum, /*과표합계*/
        String ddtnSum, /*총공제액*/
        String aclDsbAmt /*실지급액*/
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
