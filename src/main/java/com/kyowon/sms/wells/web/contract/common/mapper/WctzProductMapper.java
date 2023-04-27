package com.kyowon.sms.wells.web.contract.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dto.WctzProductDto;
import com.kyowon.sms.wells.web.contract.common.dto.WctzProductDto.SearchMiddleClassesRes;

@Mapper
public interface WctzProductMapper {
    List<WctzProductDto.SearchHighClassesRes> selectHighClasses();

    List<SearchMiddleClassesRes> selectMiddleClasses();
}
