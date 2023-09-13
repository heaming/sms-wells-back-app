package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappyCallAgrgDto.*;

import java.util.List;

@Mapper
public interface WsnbMobileHappyCallAgrgMapper {
    List<SearchRes> selectMobileHappyCallAgrgs(SearchReq searchReq);
}
