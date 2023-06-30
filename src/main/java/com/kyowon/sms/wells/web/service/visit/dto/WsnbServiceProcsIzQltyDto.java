package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0099M01 서비스처리 내역(품질)
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.22
 */
public class WsnbServiceProcsIzQltyDto {

    @ApiModel("WsnbServiceProcsIzQltyDto-SearchReq")
    public record SearchReq(
        String serviceType, // 서비스유형
        String ogId, // 서비스센터
        String prtnrNo, // 엔지니어
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

    @ApiModel("WsnbServiceProcsIzQltyDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cntrNoSn,
        String cstKnm,
        String copnDvNm,
        String cttIchr,
        String hsp,
        String alncBzsNm,
        String frisuYn,
        String sellTpNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String newAdrZip,
        String radr,
        String cntrCnfmDtm,
        String pdNm,
        String modelNm,
        String scnYn,
        String bfMnftCoId,
        String bcNo,
        String prdtYm,
        String ogNm,
        String prtnrKnm,
        String cnslMoCn,
        String istDt,
        String useMcn,
        String svBizHclsfNm,
        String svBizDclsfNm,
        String cntrCstNo,
        String asRefriDvNm,
        String bfsvcRefriDvNm,
        String refriDvNm,
        String egerCnrNm,
        String prtnr,
        String prtnrPstnDv,
        String siteAwDvNm,
        String siteAwAmt,
        String awAmt,
        String contactCralLocaraTno,
        String contactMexnoEncr,
        String contactCralIdvTno,
        String fnlRcpdt,
        String vstDuedt,
        String vstChYn,
        String vstCnfmdt,
        String vstCnfmHh,
        String dtmChCausNm,
        String dtmChRsonNm,
        String dtmChRsonDtlCn,
        String smsFwYn,
        String promHh,
        String arvDtm,
        String vstFshDtm,
        String wkLdtm,
        String wkPrgsStatNm,
        String wkCanRsonNm,
        String wkCanMoCn,
        String fgptDsbYn,
        String badDvNm,
        String asLctNm,
        String asPhnNm,
        String asLctCdNm,
        String svProcsFomNm,
        String imptaRsonNm,
        String asCdEyn,
        String sftAcdnYn,
        String plsSvYn,
        String peslArtcDfrnYn,
        String cwtrWprsVal,
        String wwtWprsVal,
        String svProcs,
        String svProcsCn,
        String pu1Part,
        String pu2Part,
        String pu3Part,
        String pu4Part,
        String pu5Part,
        String pu6Part,
        String pu7Part,
        String pu8Part,
        String pu9Part,
        String pu10Part,
        String pu1Uprc,
        String pu2Uprc,
        String pu3Uprc,
        String pu4Uprc,
        String pu5Uprc,
        String pu6Uprc,
        String pu7Uprc,
        String pu8Uprc,
        String pu9Uprc,
        String pu10Uprc,
        String totalPuUprc,
        String partCs,
        String tcfee,
        String bstrCs,
        String etcCs,
        String rveCsTot,
        String cashStlm,
        String cardStlm,
        String elcStlm,
        String cstSignHsYn
    ) {}

}
