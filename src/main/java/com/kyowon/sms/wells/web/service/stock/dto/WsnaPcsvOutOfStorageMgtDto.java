package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveProductDvo;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 택배설치상품 출고관리
 * </pre>
 *
 * @author junggheejin
 * @since 2023.05.24
 */
public class WsnaPcsvOutOfStorageMgtDto {

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-SearchReq")
    public record SearchReq(
        String startDt, // 계약시작일자
        String endDt, // 계약종료일자
        String lgstWkMthdCd, // 물류작업코드
        String svBizDclsfCd, // 출고구분
        String wkWareNo, // 창고번호
        String vstFshDt, // 출고확정일자
        String findGb, // 조회구분
        String selCnt// 조회제한건수
    ) {}

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-SearchRes")
    public record SearchRes(
        String cntrNo,  // 계약번호

        String cntrSn,  // 계약일련번호

        String cntrCstNo, // 계약자 고객번호

        String sellTpCd,  // 판매유형코드

        String sellTpNm,  // 판매유형명

        String sellTpDtlCd,  // 판매유형상세코드

        String sellTpDtlNm,  // 판매유형상세코드명

        String cntrDtlStatCd,  // 계약상세상태코드

        String cntrDtlStatNm,  // 멤버십상태

        String rcgvpKnm,     // 고객한글명

        String basePdCd,   // 판매상품코드

        String basePdNm,   // 상품명

        String cntrRcpFshDtm, //CONT_DT 계약일자

        String adrId,   // 주소ID

        String newAdrZip,  // 우편번호

        String rnadr, //주소

        String rdadr, //주소 상세

        String cralLocaraTno, //휴대지역전화번호(휴대폰번호)

        String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)

        String cralIdvTno, //휴대개별전화번호(휴대폰번호)

        String locaraTno, //지역전화번호 (전화번호)

        String exnoEncr, //전화국번호암호화(전화번호)

        String idvTno, //개별전화번호(전화번호)

        String rsgAplcDt, //해지신청일자

        String rsgFshDt, // 취소일자

        String cstSvAsnNo,  // 고객서비스배정번호

        String pdctPdCd,  // 제품코드

        String pdctPdNm, // 제품명

        String svPdCd,     // 서비스상품코드

        String svPdNm,     // 서비스상품코드명

        String pdGdCd,     // 상품등급코드

        String svBizHclsfCd,  // 서비스업무대분류코드

        String svBizDclsfCd,  // 서비스업무세분류코드

        String svBizDclsfNm, // 서비스업무세분류명

        String wkPrgsStatCd,  // 작업진행상태코드

        String wkPrgsStatNm, // 작업진행상태코드명

        String istDt,        // 설치일

        String reqdDt, // 철거일자

        String ogId,     // 조직ID

        String ogTpCd,  // 조직유형코드

        String prtnrNo,        // 파트너번호

        String prtnrKnm,       // 판매자명

        String vstFshDt,       // 출고확정일자

        String wkWareNo,       // 작업창고번호

        String wareMngtPrtnrNo,   // 창고관리파트너번호

        String wareMngtPrtnrOgTpCd,  // 창고관리파트너조직유형코드

        String rpbLocaraCd, //VST_LOCARA_CD 방문지역코드

        String siteAwSvTpCd,  // 현장수당항목코드

        String siteAwAtcCd, // 현장수당항목코드

        String pdUswyCd, // 최초상품용도코드

        String asRefriDvCd,  // AS유무상구분코드

        String bfsvcRefriDvCd,  // BS유무상구분코드

        String urgtDvCd,  // 긴급구분코드 (1: 고객센터 당일 접수 건, NULL: 그외)

        String ostrAkNo,  // 출고요청번호

        String lgstOstrAkNo,  // 물류요청번호

        String ostrNo, // 물류출고번호

        /* 작업결과 필수 코드   */
        String pdGrpCd, // 상품그룹코드

        String asLctCd, //AS위치코드

        String asPhnCd, //AS현상코드

        String asCausCd, //AS원인코드

        /* 물류인터페이스 필수 코드  */

        String lgstWkMthdCd,  // 물류작업방식코드

        int mpacSn,   // 합포장 일련번호

        /* 상품 */

        int partCnt, // 투입부품모델수

        String partCd1,

        String partNm1,

        String partQty1,

        String partCd2,

        String partNm2,

        String partQty2,

        String partCd3,

        String partNm3,

        String partQty3,

        String partCd4,

        String partNm4,

        String partQty4,

        String partCd5,

        String partNm5,

        String partQty5,

        String partCd6,

        String partNm6,

        String partQty6,

        String partCd7,

        String partNm7,

        String partQty7,

        String partCd8,

        String partNm8,

        String partQty8,

        String partCd9,

        String partNm9,

        String partQty9,

        String partCd10,

        String partNm10,

        String partQty10,

        String wlcmBfsvcYn  // 웰컴BS 생성여부

    ) {}

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cstSvAsnNo,   // 고객서비스배정번호

        @NotBlank
        String svBizDclsfCd,  // 서비스업무세분류코드

        @NotBlank
        String svBizHclsfCd,  // 서비스업무대분류코드

        @NotBlank
        String cntrNo,  // 계약번호

        @NotBlank
        String pdGrpCd,  // 상품그룹코드

        @NotBlank
        String prtnrNo,  // 파트너번호

        @NotBlank
        String ogTpCd,  // 조직유형코드

        @NotBlank
        String cntrSn,  // 계약일련번호

        @NotBlank
        String urgtDvCd,  // 긴급구분코드 (1: 고객센터 당일 접수 건, NULL: 그외)

        @NotBlank
        String ogId,  // 조직ID

        @NotBlank
        String lgstWkMthdCd, //물류작업방식코드

        int mpacSn, // 합포장 일련번호

        String pdctPdCd,              // 제품코드

        String pdGdCd,                // 상품등급코드

        String rpbLocaraCd,           // 책임지역코드

        String asLctCd,               // AS위치코드

        String asPhnCd,               // AS현상코드

        String asCausCd,              // AS원인코드

        String pdUswyCd,              // 최초상품용도코드

        String sellTpCd,              // 판매유형코드

        String asRefriDvCd,           // AS유무상구분코드

        String bfsvcRefriDvCd,        // BS유무상구분코드

        String wkWareNo,              // 작업창고번호

        String wareMngtPrtnrNo,       // 창고관리파트너번호

        String siteAwSvTpCd,          // 현장수당서비스유형코드

        String siteAwAtcCd,           // 현장수당항목코드

        String istDt,                 // 설치일

        /* 물류 인터페이스 필수 */
        String rcgvpKnm,    // 설치자명

        String cralLocaraTno, // 핸드폰1
        String mexnoEncr, // 핸드폰2
        String cralIdvTno, // 핸드폰3

        String locaraTno, // 전화번호1
        String exnoEncr, // 전화번호2
        String idvTno, // 전화번호3

        String newAdrZip, // 우편번호

        String rnadr, // 기본 주소

        String rdadr, // 상세 주소

        String cntrCstNo, // 계약자 고객번호

        String wareMngtPrtnrOgTpCd, // 창고관리파트너조직유형코드

        String wlcmBfsvcYn, // 웰컴BS 생성여부

        List<WsnaPcsvOutOfStorageSaveProductDvo> products //상품 목록
    ) {}

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-FindProductsRes")
    public record FindProductsRes(
        String lgstWkMthdCd, // 물류작업코드
        String lgstWkMthdPdNm // 물류작업코드 매핑 상품명
    ) {}

    @ApiModel(value = "WsnaPcsvOutOfStorageMgtDto-FindLogisticsCentersRes")
    public record FindLogisticsCentersRes(
        String codeId,
        String codeName
    ) {}
}
