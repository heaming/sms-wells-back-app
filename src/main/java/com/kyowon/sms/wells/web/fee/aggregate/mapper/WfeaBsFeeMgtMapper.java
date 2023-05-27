package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaBsFeeMgtDto;

/**
 * <pre>
 * BS 실적 및 수수료
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.22
 */
@Mapper
public interface WfeaBsFeeMgtMapper {

    List<WfeaBsFeeMgtDto.SearchRes> selectBsFees(WfeaBsFeeMgtDto.SearchReq dto);

}
