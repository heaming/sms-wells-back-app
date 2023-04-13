package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.FindBaseRcpRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.SearchRcpRes;

@Mapper
public interface WctaOrderDetailCssrMapper {

    FindBaseRcpRes selectContractBaseInformation(String cntrNo, String cntrSn);

    List<SearchRcpRes> selectCashSalesReceipts(String cntrNo, String cntrSn);
}
