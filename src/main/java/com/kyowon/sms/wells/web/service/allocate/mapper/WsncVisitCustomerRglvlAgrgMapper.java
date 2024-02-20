package com.kyowon.sms.wells.web.service.allocate.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlAgrgDto.*;

import java.util.List;

@Mapper
public interface WsncVisitCustomerRglvlAgrgMapper {
    List<SearchRgrpRes> selectVisitCustomerRglvlAgrgRgrps(SearchReq dto);
    List<SearchPsicRes> selectVisitCustomerRglvlAgrgPsics(SearchReq dto);
}
