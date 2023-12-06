package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchConfirmManagementReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchConfirmManagementRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchSalesBaseReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchSalesBaseRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccPressurePumpDvo;

@Mapper
public interface WdccPressurePumpMapper {

    List<SearchConfirmManagementRes> selectConfirmManagement(SearchConfirmManagementReq req);

    List<SearchSalesBaseRes> selectSalesBase(SearchSalesBaseReq req);

    int insertConfirmManagement(WdccPressurePumpDvo dvo);

    int deleteConfirmManagement(WdccPressurePumpDvo dvo);

}
