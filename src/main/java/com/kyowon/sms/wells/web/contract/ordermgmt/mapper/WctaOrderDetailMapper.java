package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDto.*;

@Mapper
public interface WctaOrderDetailMapper {

    List<SearchPextCstInfoRes> selectRelatedContractsPextCstInfo(SearchReq dto);

    List<SearchNewCstInfoRes> selectRelatedContractsNewCstInfo(SearchReq dto);

    List<SearchCustomerBaseRes> selectOrderDetailCustomerBase(SearchReq dto);

    List<SearchContractListsRes> selectOrderDetailContractLists(SearchReq dto);
}
