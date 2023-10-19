package com.kyowon.sms.wells.web.service.allocate.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlAgrgDto;

import java.util.List;

@Mapper
public interface WsncVisitCustomerRglvlAgrgMapper {
    List<WsncVisitCustomerRglvlAgrgDto.SearchRes> selectVisitCustomerRglvlAgrgs(WsncVisitCustomerRglvlAgrgDto.SearchReq dto);
}
