package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAlwncMgtDto.Allowance;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAlwncMgtDto.AllowanceBase;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelAlwncDvo;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.14
 */
@Mapper
public interface WsndRegionLevelAlwncMgtMapper {

    AllowanceBase selectBases(String rglvlDvCd);

    List<Allowance> selectMovementAllowances(String applyDate);

    List<Allowance> selectBizAllowances(String applyDate);

    int updateAllowance(WsndRegionLevelAlwncDvo dvo);

    int insertAllowance(WsndRegionLevelAlwncDvo dvo);

}
