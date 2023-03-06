package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaActivityPeopleDto.*;

@Mapper
public interface WfeaActivityPeopleMapper {

    List<SearchRes> selectActivityPeoples(
        SearchReq dto
    );

}
