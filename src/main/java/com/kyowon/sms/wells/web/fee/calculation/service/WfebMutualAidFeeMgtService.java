package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebMutualAidFeeMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 상조 수수료, 상조수수료 제휴 서비스
 * </pre>
 *
 * @author mj
 * @since 2023.04.25
 */
@Service
@RequiredArgsConstructor
public class WfebMutualAidFeeMgtService {

    private final WfebMutualAidFeeMgtMapper mapper;

    /**
     * 상조 수수료 - 조회
     * @param req
     * @return
     */
    public List<AidIndividual> getMutualAidIndividual(SearchAidReq req) {
        List<AidIndividual> list = new ArrayList<>();
        if ("0".equals(req.clasfctnFee())) {
            // 0 수수료
            list = mapper.selectMutualAidIndividualFee(req);
        } else if ("1".equals(req.clasfctnFee())) {
            // 1 되물림
            list = mapper.selectMutualAidIndividualRedf(req);
        } else {
            // 2연체 3재지급
            list = mapper.selectMutualAidIndividualEtc(req);
        }
        return list;
    }
    public List<AidGroup> getMutualAidGroup(SearchAidReq req) {
        List<AidGroup> list = new ArrayList<>();
        if ("0".equals(req.clasfctnFee())) {
            list = mapper.selectMutualAidGroupFee(req);
        } else if ("1".equals(req.clasfctnFee())) {
            list = mapper.selectMutualAidGroupRedf(req);
        } else { // 2, 3
            list = mapper.selectMutualAidGroupEtc(req);
        }
        return list;
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
