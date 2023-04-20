package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.BaseReq;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Fee;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.Performance;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebSoleDistributorFeeMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * 총판 수수료 생성관리
 * </pre>
 *
 * @author mj
 * @since 2023.04.20
 */
@Service
@RequiredArgsConstructor
public class WfebSoleDistributorFeeMgtService {

    private final WfebSoleDistributorFeeMgtMapper mapper;

    /**
     * 총판수수료 생성관리 조회 - 수수료 실적
     * @param req
     * @return
     */
    public PagingResult<Performance> getDistributorPerformance(BaseReq req, PageInfo pageInfo) {
         return mapper.selectDistributorPerformance(req, pageInfo);
    }

    /**
     * 총판수수료 생성관리 조회 - 수수료
     * @param req
     * @return
     */
    public PagingResult<Fee> getDistributorFee(BaseReq req, PageInfo pageInfo) {
         return mapper.selectDistributorFee(req, pageInfo);
    }

    /**
     * 총판수수료 생성관리 저장 - 수수료
     * @param listFees
     * @return
     */
    @Transactional
    public String editDistributorFee(List<Fee> listFees) {
        int processCount = 0;
        return "";
    }
}
