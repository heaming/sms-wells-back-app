package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbServiceCancelPsDto {
    @ApiModel("WsnbServiceCancelPsDto-SearchReq")
    public record SearchReq(
        String serviceType,
        String serviceCenter,
        String engineer,
        String pdGrpCd,
        String pdCd,
        String wkPrgsStatCd,
        String canCaus,
        String canRson,
        String selectDiv,
        String fromDate,
        String toDate,
        String lgstProcsCtYn,
        String pcsvFwYn,
        String dtlIzFltring
    ){}

    @ApiModel("WsnbServiceCancelPsDto-SearchRes")
    public record SearchRes(
        String cntrNo,                      // 계약번호
        String cntrSn,                      // 계약일련번호
        String cntrNoSn,                    // 계약상세번호
        String cstKnm,                      // 설치자명
        String pdGrpNm,                     // 상품군
        String pdNm,                        // 제품명
        String cralLocaraTno,               // 휴대지역전화번호
        String mexnoEncr,                   // 휴대전화국번호암호화
        String cralIdvTno,                  // 휴대전화국번호암호화
        String newAdrZip,                   // 우편번호
        String radr,                        // 주소
        String gnrdv,                       // 총괄단
        String rgrp,                        // 지역단
        String alncBzsCd,                   // 서비스유형
        String svBizDclsfNm,                // 서비스유형상세
        String cnslMoCn,                    // 접수내역
        String svCnrOgId,                   // 서비스센터
        String rpbLocaraCd,                 // 책임지역
        String prtnr,                       // 담당엔지니어
        String arvDtm,                      // 도착시간
        String vstFshDtm,                   // 완료시간
        String wkPrgsStatNm,                // 작업상태
        String wkCanCaus,                 // 취소원인
        String rcpCanRson,                 // 취소사유
        String wkCanMoCn,                   // 상세내역
        String unuitm,                      // 특이사항
        String istImpPhoApnFileUid1,     // 설치불가환경 이미지 1
        String istImpPhoApnFileUid2,     // 설치불가환경 이미지 2
        String istImpPhoApnFileUid3,     // 설치불가환경 이미지 3
        String cstSvAsnNo,                   // 고객서비스배정번호
        String wkPrgsStatCdGubun
    ){}
}
