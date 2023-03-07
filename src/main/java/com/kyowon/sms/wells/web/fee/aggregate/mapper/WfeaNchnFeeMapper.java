package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaNchnFeeDvo;

/**
 * <pre>
 * 신채널 수수료 생성관리 Mapper
 * </pre>
 *
 * @author haejin.lee
 * @since 2023-03-02
 */
@Mapper
public interface WfeaNchnFeeMapper {

    int deleteNchnPerformances(WfeaNchnFeeDvo dvo);

    int insertNchnPerformances(WfeaNchnFeeDvo dvo);
}
