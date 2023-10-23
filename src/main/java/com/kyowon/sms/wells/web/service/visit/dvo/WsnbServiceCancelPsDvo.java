package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsnbServiceCancelPsDvo {
    String cntrNo;                      // 계약번호
    String cntrSn;                      // 계약일련번호
    String cntrNoSn;                    // 계약상세번호
    String cstKnm;                      // 설치자명
    String pdGrpNm;                     // 상품군
    String pdNm;                        // 제품명
    String cralLocaraTno;               // 휴대지역전화번호
    @DBDecField
    String mexnoEncr;                   // 휴대전화국번호암호화
    String cralIdvTno;                  // 휴대개별전화번호
    String newAdrZip;                   // 우편번호
    String radr;                        // 주소
    String gnrdv;                       // 총괄단
    String rgrp;                        // 지역단
    String alncBzsCd;                   // 서비스유형
    String svBizDclsfNm;                // 서비스유형상세
    String cnslMoCn;                    // 접수내역
    String svCnrOgId;                   // 서비스센터
    String rpbLocaraCd;                 // 책임지역
    String prtnr;                       // 담당엔지니어
    String arvDtm;                      // 도착시간
    String vstFshDtm;                   // 완료시간
    String wkPrgsStatNm;                // 작업상태
    String wkCanRsonCd;                 // 취소원인
    String wkCanRsonNm;                 // 취소사유
    String wkCanMoCn;                   // 상세내역
    String unuitm;                      // 특이사항
    String istImpEnvr1stImgFileUid;     // 설치불가환경 이미지 1
    String istImpEnvr2ndImgFileUid;     // 설치불가환경 이미지 2
    String istImpEnvr3rdImgFileUid;     // 설치불가환경 이미지 3
    String cstSvAsnNo;                  // 고객서비스배정번호
}
