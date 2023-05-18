package com.kyowon.sms.wells.web.withdrawal.pchssl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcPrepaymentEstimateDto.SearchPrepaymentEstimateReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcPrepaymentEstimateDto.SearchPrepaymentEstimateRes;

@Mapper
public interface WwdcPrepaymentEstimateAmountMapper {

    /**
     * 선납예상조회
     * @param dto
     * @return List
     */
    List<SearchPrepaymentEstimateRes> selectPrepaymentEstimateAmount(SearchPrepaymentEstimateReq req);
}
