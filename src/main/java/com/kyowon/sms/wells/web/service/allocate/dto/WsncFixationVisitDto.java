package com.kyowon.sms.wells.web.service.allocate.dto;

import io.swagger.annotations.ApiModel;

public class WsncFixationVisitDto {
    // *********************************************************
    // Request Dto
    // *********************************************************

    @ApiModel(value = "WsncFixationVisitMgntDto-SearchFixationVisitReq")
    public record SearchFixationVisitReq(
        String fxnPrtnrDvCd,
        String sellTpCd,
        String fxnPrtnrNo
    ) {}

    @ApiModel(value = "WsncFixationVisitMgntDto-SearchFixationVisitRegReq")
    public record SearchFixationVisitRegReq(
        String cntrNo,
        String cntrSn
    ) {}

    @ApiModel(value = "WsncFixationVisitMgntDto-SaveFixationVisitRegReq")
    public record SaveFixationVisitRegReq(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String chSn, /* 변경일련번호 */
        String cstKnm, /* 계약정보-고객명 */
        String cralLocaraTno, /* 계약정보-휴대지역전화번호 */
        String mexnoEncr, /* 계약정보-휴대전화국번호암호화 */
        String cralIdvTno, /* 계약정보-휴대개별전화번호 */
        String rnadr, /* 계약정보-도로명주소 */
        String rdadr, /* 계약정보-도로명상세주소 */
        String rcgvpKnm, /* 설치정보-고객명 */
        String cralLocaraTnoInstall, /* 설치정보-휴대지역전화번호 */
        String mexnoEncrInstall, /* 설치정보-휴대전화국번호암호화 */
        String cralIdvTnoInstall, /* 설치정보-휴대개별전화번호 */
        String rnadrInstall, /* 설치정보-도로명주소 */
        String rdadrInstall, /* 설치정보-도로명상세주소 */
        String pdNm, /* 상품정보-상품 */
        String pdPrpVal01, /* 상품정보-용도 */
        String pdPrpVal01Nm, /* 상품정보-용도명 */
        String apyStrtYm, /* 적용기간 */
        String chRqrDvCd, /* 변경구분 */
        String fnlMdfcDtm, /* 변경등록일자 */
        String fxnPrtnrDvCd, /* 지정대상 */
        String fxnPrtnrNo, /* 방문담당 */
        String chRsonCn, /* 변경사유 */
        String dtaDlYn, /* 삭제여부 */
        String prtnrKnm, /* 기존담당-담당자 */
        String rsgnDt /* 기존담당-활동중지일 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WsncFixationVisitMgntDto-SearchFixationVisitRes")
    public record SearchFixationVisitRes(
        String cntrNo, /* 신주소우편번호 */
        String cntrSn, /* 시도명 */
        String chSn, /* 시군구명 */
        String rcgvpKnm, /* 읍면동명 */
        String newAdrZip, /* 지번주소 */
        String rnadr, /* 법정동코드 */
        String rdadr, /* 법정동명 */
        String cralLocaraTno, /* 지역전화번호 */
        String cralIdvTno, /* 도서지역여부 */
        String locaraTno, /* 구주소우편번호 */
        String idvTno, /* 데이터삭제여부 */
        String sellTpCd, /* 변경일련번호 */
        String fxnPrtnrDvCd, /* 방문주기값 */
        String fxnPrtnrNo, /* 매니저급지구분코드 */
        String apyStrtYm, /* 관리자구분코드 */
        String chRsonCn, /* 지점조직ID */
        String fnlMdfcDtm, /* BS담당파트너번호 */
        String fnlMdfcUsrId /* 사용여부 */
    ) {}

    @ApiModel(value = "WsncFixationVisitMgntDto-SearchFixationVisitRegRes")
    public record SearchFixationVisitRegRes(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String chSn, /* 변경일련번호 */
        String cstKnm, /* 계약정보-고객명 */
        String cralLocaraTno, /* 계약정보-휴대지역전화번호 */
        String mexnoEncr, /* 계약정보-휴대전화국번호암호화 */
        String cralIdvTno, /* 계약정보-휴대개별전화번호 */
        String rnadr, /* 계약정보-도로명주소 */
        String rdadr, /* 계약정보-도로명상세주소 */
        String rcgvpKnm, /* 설치정보-고객명 */
        String cralLocaraTnoInstall, /* 설치정보-휴대지역전화번호 */
        String mexnoEncrInstall, /* 설치정보-휴대전화국번호암호화 */
        String cralIdvTnoInstall, /* 설치정보-휴대개별전화번호 */
        String rnadrInstall, /* 설치정보-도로명주소 */
        String rdadrInstall, /* 설치정보-도로명상세주소 */
        String pdNm, /* 상품정보-상품 */
        String pdPrpVal01, /* 상품정보-용도 */
        String pdPrpVal01Nm, /* 상품정보-용도명 */
        String apyStrtYm, /* 적용기간 */
        String chRqrDvCd, /* 변경구분 */
        String fnlMdfcDtm, /* 변경등록일자 */
        String fxnPrtnrDvCd, /* 지정대상 */
        String fxnPrtnrNo, /* 방문담당 */
        String chRsonCn, /* 변경사유 */
        String dtaDlYn, /* 삭제여부 */
        String prtnrKnm, /* 기존담당-담당자 */
        String rsgnDt /* 기존담당-활동중지일 */
    ) {}
}
