package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChDvo;

@Mapper
public interface WctbCustomerBaseBulkChMapper {

    List<WctbCustomerBaseBulkChDvo> selectBulkChangeObjects(WctbCustomerBaseBulkChDvo dvo);

    List<WctbCustomerBaseBulkChDvo> selectPlannerChanges(WctbCustomerBaseBulkChDvo dvo);

}
