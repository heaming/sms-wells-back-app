package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBsServiceHistInterfaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsniBsServiceHistInterfaceMapper {
    List<WsniBsServiceHistInterfaceDto.SearchRes> selectBsServiceHistory(WsniBsServiceHistInterfaceDto.SearchReq dto);
}
