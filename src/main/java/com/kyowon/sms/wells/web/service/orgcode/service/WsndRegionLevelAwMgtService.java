package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndRegionLevelAwMgtConverter;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAwMgtDto.Allowance;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAwMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAwMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelAwDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndRegionLevelAwMgtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsndRegionLevelAwMgtService {

    private final WsndRegionLevelAwMgtMapper mapper;

    private final WsndRegionLevelAwMgtConverter converter;

    /**
     * 급지 수당 조회
     *
     * @param applyDate 적용일자
     * @return
     */
    public SearchRes getAllowances(String applyDate) {
        List<Allowance> movementAllowances = this.mapper.selectMovementAllowances(applyDate);
        List<Allowance> bizAllowances = this.mapper.selectBizAllowances(applyDate);
        return new SearchRes(movementAllowances, bizAllowances);
    }

    /**
     * 급지 수당 관리 - 저장
     *
     * @param dtos : [{ rglvlDvCd: 급지구분코드, bizRglvlCd: 업무급지코드, vlStrtDtm: 유효시작일시, mmtLdtm: 이동소요시간, mmtDstn: 이동거리, rglvlGdCd: 급지등급코드, rglvlAwAmt: 급지수당금액, chAwAmt:변경수당금액 }]
     * @return
     */
    @Transactional
    public int saveAllowances(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WsndRegionLevelAwDvo regionLevelAw = this.converter.mapSaveReqToWsndRegionLevelAwDvo(dto);
            int updateCount = this.mapper.updateAllowance(regionLevelAw);
            int insertCount = this.mapper.insertAllowance(regionLevelAw);
            processCount += (updateCount & insertCount);
        }

        return processCount;
    }
}
