package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOrganizationNetOrderDvo;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto.*;

import java.util.List;

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

    int deleteBsPerformances(WfeaOrganizationNetOrderDvo dvo);

    int insertBsPerformances(WfeaOrganizationNetOrderDvo dvo);

    List<SearchHmstRes> selectHomeMasters(
        SearchHmstReq dto
    );

    List<SearchHmstSellFeeRes> selectHomeMasterSellFees(
        SearchHmstReq dto
    );

    List<SearchHmstBsRes> selectHomeMasterBeforeServices(
        SearchHmstReq dto
    );

    List<SearchMngerRes> selectManagers(
        SearchMngerReq dto
    );

    List<SearchMngerSellFeeRes> selectManagerSellFees(
        SearchMngerReq dto
    );

    List<SearchMngerBsRes> selectManagerBeforeServices(
        SearchMngerReq dto
    );

    List<SearchPlarRes> selectPlanners(
        SearchPlarReq dto
    );

    List<SearchPlarSellFeeRes> selectPlannerSellFees(
        SearchPlarReq dto
    );

}
