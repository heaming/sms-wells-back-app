package com.kyowon.sms.wells.web.deduction.burden.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.burden.dto.WdeeRealTimeDlqAdamtDto;

@Mapper
public interface WdeeRealTimeDlqAdamtMapper {
    List<WdeeRealTimeDlqAdamtDto.SearchRes> selectRealTimeDlqAdamt(WdeeRealTimeDlqAdamtDto.SearchReq dto);
}
