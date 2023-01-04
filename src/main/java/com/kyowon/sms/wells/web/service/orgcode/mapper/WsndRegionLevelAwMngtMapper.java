package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAwMngtDto.Allowance;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAwMngtDto.BaseInfo;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelAwDvo;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.12.14
 */
@Mapper
public interface WsndRegionLevelAwMngtMapper {

    BaseInfo selectBaseInfo();

    List<Allowance> selectMovementAllowances(String applyDate);

    List<Allowance> selectBizAllowances(String applyDate);

    int updateAllowance(WsndRegionLevelAwDvo dvo);

    int insertAllowance(WsndRegionLevelAwDvo dvo);

}
