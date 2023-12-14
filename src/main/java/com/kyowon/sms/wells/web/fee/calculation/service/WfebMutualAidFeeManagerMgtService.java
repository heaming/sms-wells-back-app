package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeManagerMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebMutualAidFeeManagerMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * 상조 수수료, 상조수수료 제휴 서비스(M조직)
 * </pre>
 *
 * @author 37612
 * @since 2023.12.07
 */
@Service
@RequiredArgsConstructor
public class WfebMutualAidFeeManagerMgtService {

    private final WfebMutualAidFeeManagerMgtMapper mapper;

    /**
     * 상조 수수료 - 조회
     * @param req
     * @return
     */
    public List<AidIndividual> getMutualAidIndividual(SearchAidReq req) {
        if ("0".equals(req.clasfctnFee())) {
            // 0 수수료
            return mapper.selectMutualAidIndividualFee(req);
        } else if ("1".equals(req.clasfctnFee())) {
            // 1 되물림
            return mapper.selectMutualAidIndividualRedf(req);
        } else {
            // 2연체 3재지급
            return mapper.selectMutualAidIndividualEtc(req);
        }
    }
    public List<AidGroup> getMutualAidGroup(SearchAidReq req) {
        if ("0".equals(req.clasfctnFee())) {
            return mapper.selectMutualAidGroupFee(req);
        } else if ("1".equals(req.clasfctnFee())) {
            return mapper.selectMutualAidGroupRedf(req);
        } else { // 2, 3
            return mapper.selectMutualAidGroupEtc(req);
        }
    }

    /**
     * 상조 수수료 - 생성
     * @param req
     * @return
     */
    @Transactional
    public int createMutualAid(CreateAidReq req) {
        int processCount = 0;
        processCount += mapper.updateMutualAidFee(req);
        processCount += mapper.updateMutualAidNpaid(req);
        return processCount;
    }

    /**
     * 상조 수수료 되물림 - 생성
     * @param req
     * @return
     */
    @Transactional
    public int createRedfMutualAid(CreateAidReq req) {
        int processCount = 0;
        processCount += mapper.updateRedfMutualAidFee(req);
        processCount += mapper.updateRedfMutualAidDlq(req);
        processCount += mapper.updateRedfMutualAidAdsb(req);
        return processCount;
    }

    /**
     * 상조 수수료 제휴주문 - 조회
     * @param req
     * @return
     */
    public List<AidOrder> getMutualAidOrder(SearchAidOrderReq req) {
         return mapper.selectMutualAidOrder(req);
    }

}
