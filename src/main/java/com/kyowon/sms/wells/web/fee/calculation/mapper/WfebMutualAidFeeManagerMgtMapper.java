package com.kyowon.sms.wells.web.fee.calculation.mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeManagerMgtDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfebMutualAidFeeManagerMgtMapper {
    /* 상조 수수료 - 개인 조회 */
    // 1.수수료
    List<AidIndividual> selectMutualAidIndividualFee(SearchAidReq req);
    // 2. 되물림
    List<AidIndividual> selectMutualAidIndividualRedf(SearchAidReq req);
    // 3. 연체 재지급
    List<AidIndividual> selectMutualAidIndividualEtc(SearchAidReq req);

    /* 상조 수수료 - 조직 조회 */
    // 1.수수료
    List<AidGroup> selectMutualAidGroupFee(SearchAidReq req);
    // 2. 되물림
    List<AidGroup> selectMutualAidGroupRedf(SearchAidReq req);
    // 3. 연체 재지급
    List<AidGroup> selectMutualAidGroupEtc(SearchAidReq req);

    // 상조 수수료 - 생성
    int updateMutualAidFee(CreateAidReq req);
    int updateMutualAidNpaid(CreateAidReq req);

    // 상조 수수료 - 되물림 생성
    int updateRedfMutualAidFee(CreateAidReq req);
    int updateRedfMutualAidDlq(CreateAidReq req);
    int updateRedfMutualAidAdsb(CreateAidReq req);

    /* 상조 수수료 제휴주문 - 조회 */
    List<AidOrder> selectMutualAidOrder(SearchAidOrderReq req);
}
