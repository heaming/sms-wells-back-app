package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebEgerAllowanceDto;

/**
 * <pre>
 * 엔지니어 수당 생성관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Mapper
public interface WfebEgerAllowanceMapper {

    List<WfebEgerAllowanceDto.SearchEngineerRes> selectEngineerAllowances(WfebEgerAllowanceDto.SearchReq dto);

    List<WfebEgerAllowanceDto.SearchEngineerManagerRes> selectEngineerManagerAllowances(
        WfebEgerAllowanceDto.SearchReq dto
    );

}
