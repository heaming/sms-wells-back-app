package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebMutualAidFeeMgtMapper {
    List<AidIndividual> selectMutualAidIndividual(SearchAidReq req);
    List<AidGroup> selectMutualAidGroup(SearchAidReq req);
    int updateMutualAid(CreateAidReq req);
    List<AidOrder> selectMutualAidOrder(SearchAidOrderReq req);
}
