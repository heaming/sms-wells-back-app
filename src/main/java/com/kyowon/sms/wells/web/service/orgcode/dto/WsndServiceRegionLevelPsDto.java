package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

public class WsndServiceRegionLevelPsDto {
    @ApiModel("WsndServiceRegionLevelPsDto-SearchReq")
    public record SearchReq(
        String svTpCd,    // 서비스유형 : 01.전체, 02.설치, 03.A/S, 04.홈케어
        String ogCd,            // 서비스센터
        String prtnrNo,         // 담당자
        String searchDateType,  // 조회기준 : 01.접수일자, 02.예정일자, 03.처리일자, 04.방문확정일
        String fromDate,        // 조회기준에 따른 시작일자
        String toDate           // 조회기준에 따른 종료일자
    ) {}

    @ApiModel("WsndServiceRegionLevelPsDto-SearchRes")
    public record SearchRes(
        String bsdt,            /* 기준일자 */
        String sapMatCd,        /* SAP */
        String regNm,           /* 접수자 */
        String regOgNm,         /* 접수자소속 */
        String regId,
        String dgr1LevlOgNm,    /* 상위 조직명 */
        String dgr1LevlOgCd,    /* 상위 조직코드 */
        String dgr1LevlOgId,    /* 상위 조직 ID  */
        String ogCd,            /* 서비스센터 코드 */
        String ogNm,            /* 서비스센터명 */
        String ogId,            /* 서비스센터 조직 ID  참조용 */
        String ichrPrtnrNo,     /* 담당자 사번 */
        String rglvlSn,         /* 급지일련번호 */
        String prtnrKnm,        /* 담당자 명 */
        String ac025EmpOr,      /* 직책 */
        String ogTpCd,          /* 조직유형코드 W06  */
        String cntrNo,          /* 계약번호 */
        String cntrSn,          /* 계약일련번호 */
        String custCd,          /* 계약번호+일련번호 화면표시용 */
        String rcgvpKnm,        /* 고객명 */
        String procStusNm,      /* 작업상태   작업진행상태 */
        String newAdrZip,       /* 신우편번호 */
        String ctpvNm,          /* 시도명 */
        String ctctyNm,         /* 시군구명 */
        String ac112EmdKorNm,   /* 읍면동명 */
        String amtdNm,          /* 행정동명 */
        String co410FeeGb,      /* 수당항목 명 */
        String itemNm,          /* 상품명 */
        String arrDttm,         /* 작업도착 */
        String wkFshDt,         /* 작업완료일자 */
        String wkFshHh,         /* 작업완료시간 */
        String timeStand,       /* 시간대 (작업) */
        String al170BasePdlvNo, /* 기본출고지번호 */
        String dptuPdlvNo,      /* 출장출고지번호 */
        String arvPdlvNo,       /*  도착출고지번호 */
        String mvSisock,        /* 시속(경로) */
        String al170MvDistance, /* 경로 - 이동거리 */
        String al170MvTime,     /* 경로 - 이동시간 */
        String al170MvFee,      /* 경로  - 요금 */
        String orgShpNm,        /* 기본출고지명 */
        String orgShpAdd,       /* 기본출고지 주소 */
        String chuljangNm,      /* 출장출고지 명 */
        String chuljangAdd,     /* 출장출고지 주소 */
        String strShpNm,        /* 출발출고지명 */
        String strShpAdd,       /* 출발주소 */
        String endShpNm,        /*  도착출고지명 */
        String endShpAdd,       /*  도착출고지주소  */
        String mvDistance,      /* 이동합계 */
        String mvGrd,           /* 이동급지 - 등급 */
        String mvGrdAmt,        /* 이동급지 - 급지수당 */
        String mvTime,          /* 이동시간 (계산시 필요함) */
        String wrkGrd,          /* 업무급지 - 등급 */
        String wrkGrdAmt,       /* 업무급지 - 급지수당 */
        String strIslandYn,     /* 출발 섬구분 */
        String endIslandYn      /* 도착 섬구분 */
    ) {}
}
