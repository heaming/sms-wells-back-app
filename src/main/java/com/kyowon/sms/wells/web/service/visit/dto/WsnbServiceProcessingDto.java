package com.kyowon.sms.wells.web.service.visit.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0054M01 서비스처리 내역
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.03.20
 */
public class WsnbServiceProcessingDto {

    @ApiModel("WsnbServiceProcessingIzDto-FindProductRes")
    public record FindProductRes(
        String codeId,
        String codeName
    ) {}

    @ApiModel("WsnbServiceProcessingIzDto-SearchReq")
    public record SearchReq(
        String serviceType, // 서비스유형
        String serviceCenter, // 서비스센터
        String engineer, // 엔지니어
        String refriType, // 유무상구분
        String pdGrpCd, // 상품그룹코드
        String pdCd, // 상품코드
        String svBizDclsfCd, // 업무유형(서비스업무세분류코드)
        String inquiryBase, // 조회기준
        String baseDateFrom, // 기준일자From
        String baseDateTo, // 기준일자To
        String wkPrgsStatCd, // 작업상태
        String installBase // 설치기준
    ) {}

    @ApiModel("WsnbServiceProcessingIzDto-SearchRes")
    public record SearchRes(
        String cntrNo, // 계약번호
        String cstKnm, // 고객명
        String copnDvCd, // 개인법인구분
        String alncmpCd, // 컨택담당
        String sellPrpNuval, // 홈쇼핑
        String alncBzsCd, // 제휴업체코드
        String frisuYn, // 무료체험(무상여부)
        String sellTpCd, // 고객유형(판매유형코드)
        String wtholIstYn, // 조리수설치
        String cralLocaraTno, // 휴대지역전화번호
        String mexnoEncr, // 휴대전화국번호암호화
        String cralIdvTno, // 휴대개별전화번호
        String newAdrZip, // 우편번호
        String radr, // 주소
        String svPrd, // 방문주기타입
        String cntrCnfmDtm, // 계약일자
        String pdNm, // 상품명
        String pdGrpCd, // 상품군
        String pdCd, // 품목코드
        String modelNm, // 모델명
        String scnYn, // 스캔
        String bfMnftCoId, //
        String bcNo, //
        String prdtYm, //
        String channelNm, //
        String gnrdv, //
        String rgrp, //
        String ogNm, //
        String prtnrKnm, //
        String cnslDtlpTpCd, //
        String istDt, //
        String useMcn, //
        String svBizHclsfCd, //
        String svBizDclsfCd, //
        String ptyCstNo, //
        String cstmwFiltPdCd, //
        String asRefriDvNm, //
        String bfsvcRefriDvNm, //
        String refriDvNm, //
        String egerCnrNm, // 담당센터
        String rpbLocaraCd, //
        String ichrCnrSdingDidy, // 담당센터(모종직배)
        String prtnr, // 담당엔지니어
        String prtnrPstnDv, // 직급구분
        String siteAwDvNm, // 현장수당(항목명)
        String siteAwAmt, // 현장수당(금액)
        String rglvlGdCd, // 현장수당(급지)
        String awAmt, //
        String locaraChaosYn, //
        String locaraChaosAwAmt, //
        String siteAwTot, //
        String contactCralLocaraTno, // 연락처(휴대지역전화번호)
        String contactMexnoEncr, // 연락처(휴대전화국번호암호화)
        String contactCralIdvTno, // 연락처(휴대개별전화번호)
        String rcpdt, //
        String rcpHh, //
        String vstDuedt, //
        String vstExpHh, //
        String vstRqdt, //
        String vstCnfmdt, // 확정일자
        String vstCnfmHh, // 확정일시
        String dtmChCausCd, // 변경원인
        String dtmChRsonCd, // 변경사유
        String dtmChRsonDtlCn, // 변경사유상세
        String mmsYn, // MMS
        String istCtfMtmsFwYn, //
        String promHh, // 약속시간
        String arvDtm, // 작업시간(도착)
        String vstFshDtm, // 작업시간(완료)
        String instDelay, // 설치지연일
        String wkLdtm, // 소요시간
        String wkPrgsStatCd, // 작업상태
        String wkCanRsonCd, // 취소사유
        String wkCanMoCn, // 상세내역
        String pmotDvVal, // 사은품
        String badDvCd, // 불량구분
        String asLctCdNm, // 고장위치
        String asPhnCdNm, // 고장현상
        String asCausCd, // 위치상세
        String svProcsFomCd, // 처리형태
        String imptaRsonCd, // 귀책
        String asCdEyn, // AS코드없음
        String sftAcdnYn, // 안전사고
        String plsSvYn, // 플러스서비스
        String peslArtcDfrnYn, // 인적사항다름
        String cwtrWprsVal, // 수압(냉)
        String wwtWprsVal, // 수압(온)
        String per1mOlqVal, // 유량
        String istPlcTpCd, // 설치유형
        String rcvryOpt, // 복구옵션
        String resultDtl, // 결과입력 상세
        String svProcsCn, // 결과입력(상세내역)
        String pu1Part, // 부품1
        String pu2Part, // 부품2
        String pu3Part, // 부품3
        String pu4Part, // 부품4
        String pu5Part, // 부품5
        String pu6Part, // 부품6
        String pu7Part, // 부품7
        String pu8Part, // 부품8
        String pu9Part, // 부품9
        String pu10Part, // 부품10
        String partCs, // 부품비
        String tcfee, // 기술료
        String bstrCs, // 출장료
        String etcCs, // 기타비용
        String rveCsTot, // 수납비용 전체
        String cashStlm, // 현금
        String cardStlm, // 카드
        String elcStlm, // 전자결제
        String crcdcoCd, // 카드사
        String cstSignHsYn, // 고객서명
        String istEnvrPhoPhCn, // 사진
        String acpnPrtnr, // 동행작업자(동행자명)
        String pstnNm, // 동행작업자(직급구분)
        String cmpaSiteAwAtcNm, // 동행자 현장수당(항목명)
        String cmpaSiteAwAmt // 동행자 현장수당(금액)
    ) {
        public SearchRes {
            if (!StringUtil.isEmpty(mexnoEncr)) {
                mexnoEncr = DbEncUtil.dec(mexnoEncr);
            }
            cralLocaraTno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;

            if (!StringUtil.isEmpty(contactMexnoEncr)) {
                contactMexnoEncr = DbEncUtil.dec(contactMexnoEncr);
            }
            contactCralLocaraTno = contactCralLocaraTno + "-" + contactMexnoEncr + "-" + contactCralIdvTno;
        }
    }

}
