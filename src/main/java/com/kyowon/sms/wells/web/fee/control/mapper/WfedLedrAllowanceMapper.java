package com.kyowon.sms.wells.web.fee.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.control.dto.WfedLedrAllowanceDto;

/**
 * <pre>
 * 단장 수당 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Mapper
public interface WfedLedrAllowanceMapper {

    List<WfedLedrAllowanceDto.SearchRes> selelctLeaderAllowances(WfedLedrAllowanceDto.SearchReq dto);

}
