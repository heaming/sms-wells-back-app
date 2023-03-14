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
}
