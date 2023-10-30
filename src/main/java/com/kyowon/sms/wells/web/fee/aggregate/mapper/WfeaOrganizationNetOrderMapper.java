package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOrganizationNetOrderDvo;

/**
 * <pre>
 * 조직별 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Mapper
public interface WfeaOrganizationNetOrderMapper {

    List<SearchHmstRes> selectHomeMasters(
        SearchHmstReq dto
    );

    List<SearchHmstFeeRes> selectHomeMasterFees(
        SearchHmstReq dto
    );

    List<SearchHmstFeeRes2> selectHomeMasterFees2(
        SearchHmstReq dto
    );

    List<SearchMngerDetailRes> selectManagerDetailOrders(
        SearchMngerDetailReq dto
    );

    List<SearchMngerAggregateRes> selectManagerAggregateOrders(
        SearchMngerAggregateReq dto
    );

    List<SearchMngerStatusRes> selectManagerStatusOrders(
        SearchMngerStatusReq dto
    );

    List<SearchPlarRes> selectPlanners(
        SearchPlarReq dto
    );

    List<SearchPlarSellFeeRes> selectPlannerFees(
        SearchPlarReq dto
    );

    List<SearchPlarAgrgRes> selectPlannerAggregation(
        SearchPlarReq dto
    );

    int updateNtorMmClConfirm(WfeaOrganizationNetOrderDvo dvo);

    int updateNtorMmClCancel(WfeaOrganizationNetOrderDvo dvo);

    int selectFeeNetOrderPdCnt(SaveOgNetOrderReq dto);

}
