package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WsnaShippingManagementDto {

    @ApiModel("WsnaAsConsumablesStoreDto-SearchProductReq")
    public record SearchProductReq(
        @NotBlank
        String asnYm, // 배정년월
        @NotBlank
        String sppDvCd // 프로그램ID
    ) {}

    @ApiModel("WsnaAsConsumablesStoreDto-SearchProductRes")
    public record SearchProductRes(
        String pdCd, // 상품코드
        String pdNm, // 상품명
        String pdGroupCd, // 상품그룹(콤보박스)
        String sppThmYn // 배송0차월여부
    ) {}

    @ApiModel("WsnaAsConsumablesStoreDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String asnYm, // 배정년월
        @NotBlank
        String sppDvCd, // 프로그램ID
        @NotBlank
        String pdCd, // 상품코드
        String sppThmYn, // 배송0차월여부
        String procsDvCd, // 처리구분
        String rownum // 조회제한건수
    ) {}

    @ApiModel("WsnaAsConsumablesStoreDto-SearchRes")
    public record SearchRes(
        String asnOjYm, // 배정년월
        String cntrNo, // 계약번호
        String cntrSn, // 계약일련번호
        String cstSvAsnNo, // 고객서비스배정번호
        String cstKnm, // 고객명
        String zip, // 배송우편번호
        String sppAkSpfDt, // 특정일자배송
        //자재정보
        String partCd01,
        String partNm01,
        int partQty01,
        String partCd02,
        String partNm02,
        int partQty02,
        String partCd03,
        String partNm03,
        int partQty03,
        String partCd04,
        String partNm04,
        int partQty04,
        String partCd05,
        String partNm05,
        int partQty05,
        String partCd06,
        String partNm06,
        int partQty06,
        String partCd07,
        String partNm07,
        int partQty07,
        String partCd08,
        String partNm08,
        int partQty08,
        String partCd09,
        String partNm09,
        int partQty09,
        String partCd10,
        String partNm10,
        int partQty10,
        String istDt, //설치일자
        String reqdDt, //철거일자
        String cpsDt, //보상일자 (취소일자?)
        String thReqdDt,
        String sellTpCd, //판매코드
        String sellTpNm, // 판매유형
        String istNmnN, //설치차월
        String vstNmnN, //방문차월
        String pdctPdCd, //기준상품코드
        String basePdCd,
        String pdNm, //상품명
        String svBizDclsfCd, // 작업구분코드
        String svBizDclsfNm, // 작업구분명
        String vstDuedt, //방문예정일자
        String pVstPrgsStatCd,
        String pVstPrgsStatNm,
        String vstPrgsStatCd,
        String vstPrgsStatNm, //방문진행상태명
        String wkExcnDt, // 작업수행일자(작업완료일자)
        String asLctCd, // AS위치코드
        String asLctNm, // AS위치명
        String asPhnCd, // AS현상코드
        String asPhnNm, // AS현상명
        String asCausCd, // AS원인코드
        String asCausNm, // AS원인명
        String wareNo, // 배송창고(창고번호)
        String wareIchrNo, // 배송창고(번호)
        String wareMngtPrtnrno, // 배송창고(파트너번호)
        String vstOjLocaraCd, // 방문대상지역코드
        String vstOjLocaraNm, // 방문대상지역명
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String tno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno,
        String basAdr, // 배송기본주소
        String dtlAdr, // 상세주소
        String cnfmPsicPrtnrNo,
        String siteAwPdGrpCd,
        int partCntTotal,
        int partSumTotal,
        String frisuRcvryTpCd,
        String bzrno,
        String bfsvcBzsDvCd,
        String sppDvCd,
        String sppPdCd,
        String procsDvCd,
        String sppPdNm,
        String partNmQty01,
        String partNmQty02,
        String partNmQty03,
        String partNmQty04,
        String partNmQty05,
        String partNmQty06,
        String partNmQty07,
        String partNmQty08,
        String partNmQty09,
        String partNmQty10,
        String sppThmYn,
        String cstNo
    ) {
        public SearchRes {
            if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(exnoEncr)
                && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + exnoEncr + "-" + idvTno;
            } else if (StringUtil.isNotBlank(locaraTno) && StringUtil.isNotBlank(idvTno)) {
                tno = locaraTno + "-" + idvTno;
            }
            mpno = cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno;
        }
    }

    @ApiModel("WsnaAsConsumablesStoreDto-SaveReq")
    public record SaveReq(
        String asnOjYm, // 배정년월
        @NotBlank
        String cntrNo, // 계약번호
        @NotBlank
        String cntrSn, // 계약일련번호
        @NotBlank
        String cstSvAsnNo, // 고객서비스배정번호
        String cstKnm, // 고객명
        String zip, // 배송우편번호
        String sppAkSpfDt, // 특정일자배송
        //자재정보
        String partCd01,
        String partNm01,
        int partQty01,
        String partCd02,
        String partNm02,
        int partQty02,
        String partCd03,
        String partNm03,
        int partQty03,
        String partCd04,
        String partNm04,
        int partQty04,
        String partCd05,
        String partNm05,
        int partQty05,
        String partCd06,
        String partNm06,
        int partQty06,
        String partCd07,
        String partNm07,
        int partQty07,
        String partCd08,
        String partNm08,
        int partQty08,
        String partCd09,
        String partNm09,
        int partQty09,
        String partCd10,
        String partNm10,
        int partQty10,
        String istDt, //설치일자
        String reqdDt, //철거일자
        String cpsDt, //보상일자 (취소일자?)
        String thReqdDt,
        String sellTpCd, //판매코드
        String sellTpNm, // 판매유형
        String istNmnN, //설치차월
        String vstNmnN, //방문차월
        String pdctPdCd, //기준상품코드
        String basePdCd,
        String pdNm, //상품명
        String svBizDclsfCd, // 작업구분코드
        String svBizDclsfNm, // 작업구분명
        String vstDuedt, //방문예정일자
        String pVstPrgsStatCd,
        String pVstPrgsStatNm,
        String vstPrgsStatCd,
        String vstPrgsStatNm, //방문진행상태명
        String wkExcnDt, // 작업수행일자(작업완료일자)
        String asLctCd, // AS위치코드
        String asLctNm, // AS위치명
        String asPhnCd, // AS현상코드
        String asPhnNm, // AS현상명
        String asCausCd, // AS원인코드
        String asCausNm, // AS원인명
        String wareNo, // 배송창고(창고번호)
        String wareIchrNo, // 배송창고(번호)
        String wareMngtPrtnrno, // 배송창고(파트너번호)
        String vstOjLocaraCd, // 방문대상지역코드
        String vstOjLocaraNm, // 방문대상지역명
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String tno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String mpno,
        String basAdr, // 배송기본주소
        String dtlAdr, // 상세주소
        String cnfmPsicPrtnrNo,
        String siteAwPdGrpCd,
        int partCntTotal,
        int partSumTotal,
        String frisuRcvryTpCd,
        String bzrno,
        String bfsvcBzsDvCd,
        String sppDvCd,
        String sppPdCd,
        String procsDvCd,
        String sppPdNm,
        String partNmQty01,
        String partNmQty02,
        String partNmQty03,
        String partNmQty04,
        String partNmQty05,
        String partNmQty06,
        String partNmQty07,
        String partNmQty08,
        String partNmQty09,
        String partNmQty10,
        String sppThmYn,
        String cstNo
    ) {}
}
