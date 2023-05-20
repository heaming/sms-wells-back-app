package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBsServiceHistInterfaceDto;

@Mapper
public interface WsniBsServiceHistInterfaceMapper {
    Optional<List<WsniBsServiceHistInterfaceDto.SearchRes>> selectBsServiceHistory(WsniBsServiceHistInterfaceDto.SearchReq dto);
}
