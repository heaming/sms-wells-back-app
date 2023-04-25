package com.kyowon.sms.wells.web.fee.calculation.service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebMutualAidFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebMutualAidFeeMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<AidIndividual> getMutualAidIndividual(AidReq req) {
        return mapper.selectMutualAidIndividual(req);
    }
    public List<AidGroup> getMutualAidGroup(AidReq req) {
        return mapper.selectMutualAidGroup(req);
    }
    /**
     * 상조 수수료 - 생성
     * @param baseYm
     * @return
     */
    @Transactional
    public int editMutualAid(String baseYm) {
        return mapper.updateMutualAid(baseYm);
    }
    /**
     * 상조 수수료 제휴주문 - 조회
     * @param req
     * @return
     */
    public List<AidOrder> getMutualAidOrder(AidOrderReq req) {
         return mapper.selectMutualAidOrder(req);
    }

}
