package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dvo.WdccDelinquentAdditionalChargesDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WdccDelinquentAdditionalChargesMapper {

    List<WdccDelinquentAdditionalChargesDvo> selectDelinquentAdditionalCharges(Map<String, Object> param);
}
