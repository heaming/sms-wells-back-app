package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebMutualAidFeeMgtMapper {
    List<AidIndividual> selectMutualAidIndividual(AidReq req);
    List<AidGroup> selectMutualAidGroup(AidReq req);
    int updateMutualAid(String baseYm);
    List<AidOrder> selectMutualAidOrder(AidOrderReq req);
}
