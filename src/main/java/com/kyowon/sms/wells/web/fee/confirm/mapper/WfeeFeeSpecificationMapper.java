package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.fee.confirm.dto.WfeeFeeSpecificationDto.*;

@Mapper
public interface WfeeFeeSpecificationMapper {

    List<SearchRes> selectFeeSpecifications(
        SearchReq dto
    );

}
