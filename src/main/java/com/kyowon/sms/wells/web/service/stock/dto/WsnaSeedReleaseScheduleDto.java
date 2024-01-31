package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

public class WsnaSeedReleaseScheduleDto {

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-SearchReq")
    public record SearchReq(
        // 조회구분
        String svBizHclsfCd,
        // 일자유형
        @NotBlank
        String dtTpCd,
        // 요일
        String dayOfWeek,
        // 시작일자
        @NotBlank
        @ValidDate
        String strtDt,
        // 종료일자
        @NotBlank
        @ValidDate
        String endDt,
        // 유무상구분
        String refriDivCd,
        // 배송구분
        String sppDvCd,
        // 완료처리
        String fshProcsCd,
        // 패키지구분
        String pkgDvCd,
        // 출고여부
        String ostrYn
    ) {}

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-SearchRes")
    public record SearchRes(
        // 입금
        String dpYn,
        // 출고
        String ostrYn,
        // 유무상
        String refriDiv,
        // 배송구분
        String shipDiv,
        // 접수구분
        String receiptDiv,
        // 계약번호
        String cntrNo,
        // 고객명
        String cstNm,
        // 배송번호
        String sppOrdNo,
        // 기기모델
        String mchnModel,
        // 기기고객번호
        String mchnCstNo,
        // 기기고객명
        String mchnCstNm,
        // 현재패키지
        String ctrlPkg,
        // 배송패키지
        String shipPkg,
        // 모종1
        String sding1,
        // 수량1
        BigDecimal qty1,
        // 모종2
        String sding2,
        // 수량2
        BigDecimal qty2,
        // 모종3
        String sding3,
        // 수량3
        BigDecimal qty3,
        // 모종4
        String sding4,
        // 수량4
        BigDecimal qty4,
        // 모종5
        String sding5,
        // 수량5
        BigDecimal qty5,
        // 모종6
        String sding6,
        // 수량6
        BigDecimal qty6,
        // 모종7
        String sding7,
        // 수량7
        BigDecimal qty7,
        // 모종8
        String sding8,
        // 수량8
        BigDecimal qty8,
        // 모종9
        String sding9,
        // 수량9
        BigDecimal qty9,
        // 모종10
        String sding10,
        // 수량10
        BigDecimal qty10,

        // 기기철거일자
        String mchnDemDt,
        // 접수일자
        String receiptDt,
        // 방문일자
        String vstDt,
        // 출고예정일자
        String ostrScheDt,
        // BS완료일자
        String bsFshDt,
        // 입금일자
        String dpDt,
        // 출고확정일자
        String ostrCnfmDt,
        // 모종수령창고
        String sdingRcgWareNm,
        // 방문센터
        String vstCenter,
        // 방문담당
        String vstIchr,
        // 담당연락처
        String ichrCtnt,
        // 고객연락처
        String cstCtnt,
        // 고객우편번호
        String cstZip,
        // 고객주소
        String cstAdr,
        // 유무상구분코드
        String refriDvCd,
        // 계약일련번호
        int cntrSn,
        // 서비스업무대분류코드
        String svBizHclsfCd,
        // 서비스업무세분류코드
        String svBizDclsfCd,
        // 배송일련번호
        int sppPlanSn,
        // 모종1 품목코드
        String sdingPdCd1,
        // 모종1 파종일자
        String sowDt1,
        // 모종2 품목코드
        String sdingPdCd2,
        // 모종2 파종일자
        String sowDt2,
        // 모종3 품목코드
        String sdingPdCd3,
        // 모종3 파종일자
        String sowDt3,
        // 모종4 품목코드
        String sdingPdCd4,
        // 모종4 파종일자
        String sowDt4,
        // 모종5 품목코드
        String sdingPdCd5,
        // 모종5 파종일자
        String sowDt5,
        // 모종6 품목코드
        String sdingPdCd6,
        // 모종6 파종일자
        String sowDt6,
        // 모종7 품목코드
        String sdingPdCd7,
        // 모종7 파종일자
        String sowDt7,
        // 모종8 품목코드
        String sdingPdCd8,
        // 모종8 파종일자
        String sowDt8,
        // 모종9 품목코드
        String sdingPdCd9,
        // 모종9 파종일자
        String sowDt9,
        // 모종10 품목코드
        String sdingPdCd10,
        // 모종10 파종일자
        String sowDt10,

        // 모종패키지품목코드
        String sdingPkgPdCd,
        // 관리자구분코드
        String mngrDvCd,
        // 입금예정자명
        String dpEpttNm,
        // 조직유형코드
        String ogTpCd,
        // 파트너번호
        String prtnrNo,
        // 계약주소지ID
        String cntrAdrpcId,
        // 유상비용금액
        BigDecimal recapCsAmt,
        // 모종기계상품코드
        String sdingMcnrPdCd,
        // 배송구분코드
        String sppDvCd,
        // 고객서비스배정번호
        String cstSvAsnNo,
        // 계약상세번호
        String cntrDtlNo,
        // 기기계약상세번호
        String mchnCstDtlNo,
        // 방문예정일자
        String vstDuedt,
        // 고객휴대지역전화번호
        String cstCralLocaraTno,
        // 고객전화국번호
        String cstMexnoEncr,
        // 고객휴대개별전화번호
        String cstCralIdvTno,
        // 서비스상품코드
        String svPdCd
    ) {}

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-EditReq")
    public record EditReq(
        // 계약번호
        @NotBlank
        String cntrNo,
        // 계약일련번호
        @Positive
        int cntrSn,
        // 서비스업무대분류코드
        @NotBlank
        String svBizHclsfCd,
        // 서비스업무세분류코드
        @NotBlank
        String svBizDclsfCd,
        // 배송주문번호
        @NotBlank
        String sppOrdNo,
        // 배송일련번호
        @Positive
        int sppPlanSn,
        // 입금일자
        @NotBlank
        @ValidDate
        String dpDt

    ) {}

    @Builder
    @ApiModel("WsnaSeedReleaseScheduleDto-CreateReq")
    public record CreateReq(
        // 계약번호
        @NotBlank
        String cntrNo,
        // 계약일련번호
        @Positive
        int cntrSn,
        // 서비스업무대분류코드
        @NotBlank
        String svBizHclsfCd,
        // 서비스업무세분류코드
        @NotBlank
        String svBizDclsfCd,
        // 배송주문번호
        @NotBlank
        String sppOrdNo,
        // 배송일련번호
        @Positive
        int sppPlanSn,

        // 고객명
        String cstNm,
        // 기기고객번호
        String mchnCstNo,
        // 모종기계상품코드
        String sdingMcnrPdCd,
        // 모종패키지상품코드
        String sdingPkgPdCd,
        // 모종1
        String sdingPdCd1,
        // 수량1
        BigDecimal qty1,
        // 파종일자1
        String sowDt1,
        // 모종2
        String sdingPdCd2,
        // 수량2
        BigDecimal qty2,
        // 파종일자2
        String sowDt2,
        // 모종3
        String sdingPdCd3,
        // 수량3
        BigDecimal qty3,
        // 파종일자3
        String sowDt3,
        // 모종4
        String sdingPdCd4,
        // 수량4
        BigDecimal qty4,
        // 파종일자4
        String sowDt4,
        // 모종5
        String sdingPdCd5,
        // 수량5
        BigDecimal qty5,
        // 파종일자5
        String sowDt5,
        // 모종6
        String sdingPdCd6,
        // 수량6
        BigDecimal qty6,
        // 파종일자6
        String sowDt6,
        // 모종7
        String sdingPdCd7,
        // 수량7
        BigDecimal qty7,
        // 파종일자7
        String sowDt7,
        // 모종8
        String sdingPdCd8,
        // 수량8
        BigDecimal qty8,
        // 파종일자8
        String sowDt8,
        // 모종9
        String sdingPdCd9,
        // 수량9
        BigDecimal qty9,
        // 파종일자9
        String sowDt9,
        // 모종10
        String sdingPdCd10,
        // 수량10
        BigDecimal qty10,
        // 파종일자10
        String sowDt10,

        // 접수일자
        @ValidDate
        String receiptDt,
        // 방문일자
        @ValidDate
        String vstDt,
        // 출고예정일자
        @ValidDate
        String ostrScheDt,
        // 출고확정일자
        @ValidDate
        String ostrCnfmDt,

        // 관리자구분코드
        String mngrDvCd,
        // 조직유형코드
        String ogTpCd,
        // 파트너번호
        String prtnrNo,
        // 계약주소지ID
        String cntrAdrpcId,
        // 유무상구분
        String refriDvCd,
        // 유상비용금액
        BigDecimal recapCsAmt,
        // 입금예정자명
        String dpEpttNm,
        // 입금일자
        String dpDt,
        // 배송구분
        String sppDvCd,
        // 고객서비스배정번호
        String cstSvAsnNo,

        // 방문예정일자
        @ValidDate
        String vstDuedt,
        // 고객휴대지역전화번호
        String cstCralLocaraTno,
        // 고객전화국번호
        String cstMexnoEncr,
        // 고객휴대개별전화번호
        String cstCralIdvTno,
        // 서비스상품코드
        String svPdCd
    ) {}

}
