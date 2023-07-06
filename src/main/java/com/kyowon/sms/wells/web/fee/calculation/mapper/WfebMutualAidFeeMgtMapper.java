package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebMutualAidFeeMgtMapper {
    List<AidIndividual> selectMutualAidIndividualFee(SearchAidReq req);
    List<AidIndividual> selectMutualAidIndividualRedf(SearchAidReq req);
    List<AidIndividual> selectMutualAidIndividualEtc(SearchAidReq req);
    List<AidGroup> selectMutualAidGroupFee(SearchAidReq req);
    List<AidGroup> selectMutualAidGroupRedf(SearchAidReq req);
    List<AidGroup> selectMutualAidGroupEtc(SearchAidReq req);
    int updateMutualAidFee(CreateAidReq req);
    int updateMutualAidNpaid(CreateAidReq req);

    int updateRedfMutualAidFee(CreateAidReq req);
    int updateRedfMutualAidDlq(CreateAidReq req);
    int updateRedfMutualAidAdsb(CreateAidReq req);
    List<AidOrder> selectMutualAidOrder(SearchAidOrderReq req);
}
