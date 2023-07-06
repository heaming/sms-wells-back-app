package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChangeDvo;

@Mapper
public interface WctbCustomerBaseBulkChangeMapper {

    List<WctbCustomerBaseBulkChangeDvo> selectBulkChangeObjects(WctbCustomerBaseBulkChangeDvo dvo);

    List<WctbCustomerBaseBulkChangeDvo> selectPlannerChanges(WctbCustomerBaseBulkChangeDvo dvo);

}
