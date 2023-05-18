package com.kyowon.sms.wells.web.withdrawal.pchssl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcPrepaymentEstimateDto.SearchPrepaymentEstimateReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcPrepaymentEstimateDto.SearchPrepaymentEstimateRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.mapper.WwdcPrepaymentEstimateAmountMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwdcPrepaymentEstimateAmountService {

    private final WwdcPrepaymentEstimateAmountMapper mapper;

    /**
     * 선납예상금액조회
     * @param req
     * @return
     */
    public List<SearchPrepaymentEstimateRes> getPrepaymentEstimateAmount(
        SearchPrepaymentEstimateReq req
    ) {
        return mapper.selectPrepaymentEstimateAmount(req);
    }

}
