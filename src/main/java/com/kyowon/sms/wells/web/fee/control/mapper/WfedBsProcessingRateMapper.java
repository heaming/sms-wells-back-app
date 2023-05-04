package com.kyowon.sms.wells.web.fee.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.control.dto.WfedBsProcessingRateDto;
import com.kyowon.sms.wells.web.fee.control.dvo.WfedBsProcessingRateDvo;

/**
 * <pre>
 * BS처리율 조정 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.02
 */
@Mapper
public interface WfedBsProcessingRateMapper {

    List<WfedBsProcessingRateDto.SearchRes> selectBsProcessingRates(WfedBsProcessingRateDto.SearchReq dto);

    int updatePartnerBsProcessingRate(WfedBsProcessingRateDvo dvo);

    int updateMonthBsProcessingRate(WfedBsProcessingRateDvo dvo);

}
