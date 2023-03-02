package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Mapper
public interface WfeeIndividualFeeMapper {
    List<WfeeIndividualFeeDto.SearchRes> selectIndividualPerformanceDetails(
        WfeeIndividualFeeDto.SearchReq dto
    );
}
