package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

public class WctaOrderDetailDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //wells 1+1 접수 정보 조회(기존고객정보) - wells 1+1 접수 정보 조회 Search Request Dto
    @ApiModel(value = "WctaOrderDetailDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn
    ) {}

    //wells 주문 상세(거래명세서목록조회) - 거래명세서목록조회 Search Request Dto
    @ApiModel(value = "WctaOrderDetailDto-SearchTradeSpecificationSheetReq")
    public record SearchTradeSpecificationSheetReq(
        String cntrNo,
        String cntrSn,
        String cntrCstNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //wells 1+1 접수 정보 조회(기존고객정보) - wells 1+1 접수 정보 조회 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchPextCstInfoRes")
    public record SearchPextCstInfoRes(
        String cntrNoFull,
        String cstKnm,
        String basePdCd,
        String pdNm,
        String sellTpCd,
        String sellTpNm,
        String keepPtrm
    ) {}

    //wells 1+1 접수 정보 조회(신규고객정보) - wells 1+1 접수 정보 조회 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchNewCstInfoRes")
    public record SearchNewCstInfoRes(
        String cntrNoFull,
        String cstKnm,
        String basePdCd,
        String pdNm,
        String sellTpCd,
        String sellTpNm,
        String keepPtrm,
        String rentalNmn,
        String fnlVal,
        String dscAmt,
        String allDscAmt,
        String rcvryAmt,
        String mutuYn
    ) {}

    @ApiModel("WctaOrderDetailDto-SearchRes")
    public record SearchRes(
        List<SearchPextCstInfoRes> searchPextCstInfoResList,
        List<SearchNewCstInfoRes> searchNewCstInfoResList
    ) {}

    //wells 주문 상세 조회 - WELLS 주문 상세(고객기본정보) Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchCustomerBaseRes")
    public record SearchCustomerBaseRes(
        String cntrNoFull,
        String pdNm,
        String cstKnm,
        String cntrCstNo,
        String cntrNo,
        String cntrSn,
        String cstNo2,
        String cralTno,
        String cstGd,
        String sexDvNm,
        String aftnInfo,
        String sfkVal,
        String vacInfo,
        String cntrtAdr,
        String rcgvpKnm,
        String rcgvpTno,
        String rcgvpAdr
    ) {}

    //wells 주문 상세 조회 - WELLS 주문 상세(고객기본정보) - 상세계약목록 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchContractListsRes")
    public record SearchContractListsRes(
        String cntrNo,
        String cntrSn,
        String cntrNoFull,
        String basePdCd,
        String pdNm
    ) {}

    //wells 주문 상세(거래명세서목록조회) - 입금내역서 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchDepositItemizationsRes")
    public record SearchDepositItemizationsRes(
        String sellTpCd,
        String cntrNo,
        String cntrSn,
        String cntrNoFull,
        String rveNoFull,
        String rveDt,
        String perfDt,
        String pymDt,
        String rveDvNm,
        String rveAmt,
        String dpTpNm,
        String cdcoNm,
        String crcdno,
        String crdcdAprno,
        String crdcdIstmMcn,
        String prmYn,
        String ozgub1,
        String lcamt1vat,
        String lcamt1sply,
        String ozlcinst,
        String dpCprcnfYn,
        String fnlMdfcPrgId,
        String cwigub,
        String cwijub,
        String dtlview,
        String pdNm
    ) {}

    //wells 주문 상세(거래명세서목록조회) - 거래명세서 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchTradeSpecificationSheetRes")
    public record SearchTradeSpecificationSheetRes(
        String cntrNo,
        String cntrSn,
        String cntrNoFull,
        String sellTpNm,
        String pdNm,
        String lcrcnt,
        String baseYm,
        String lcsleymd,
        String lcam16,
        String w1Iamt,
        String lcam36,
        String lcdamt
    ) {}

    //wells 주문 상세(거래명세서목록조회) - 카드매출전표 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchCardSalesSlipsRes")
    public record SearchCardSalesSlipsRes(
        String cntrNo,
        String cntrSn,
        String cntrNoFull,
        String basePdCd,
        String sellTpCd,
        String rveOjDrmNo,
        String cardgbn,
        String edcgbn,
        String crdcdIstmMcn,
        String pdNm,
        String dpstdt,
        String dpsthm,
        String dpcndt,
        String dpcnhm,
        String cardvndr,
        String dpcdnoFull,
        String crdcdAprno,
        String dpAmt,
        String lcetccnt,
        String affno,
        String cntrCstNo
    ) {}

    //wells 주문 상세(거래명세서목록조회) - 계약사항 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchContractArticlesRes")
    public record SearchContractArticlesRes(
        String cntrNo,
        String cntrSn,
        String cntrNoFull,
        String sellTpCd,
        String pdNm,
        String pdMclsfNm,
        String lcsetymd,
        String lcamt1,
        String lcduty,
        String lcrcnt,
        String rcgvpAdr
    ) {}

    //wells 주문 상세(거래명세서목록조회) - 계약목록 Search Result Dto
    @ApiModel("WctaOrderDetailDto-SearchContractsRes")
    public record SearchContractsRes(
        String cntrNo,
        String cntrSn,
        String cntrNoFull,
        String cntrCstNo,
        String pdNm,
        String cntrDt,
        String canDt,
        String sellTpCd,
        String sellTpNm,
        String spayAmt,
        String rentalAmt,
        String rentalAmt2,
        String mshAmt,
        String rglrSppAmt,
        String cstKnm,
        String mchnKndCd,
        String dscApyDtlCd,
        String pdMclsfNm,
        String stplPtrm,
        String rcgvpAdr,
        String rentalNmnN
    ) {}
}
