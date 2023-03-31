package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;


import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbSinglePaymentPerformanceInfoInterfaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbSinglePaymentPerformanceInfoInterfaceMapper {

    List<WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchRes> selectSinglePaymentPerformanceInfos(WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchReq dto);
}
