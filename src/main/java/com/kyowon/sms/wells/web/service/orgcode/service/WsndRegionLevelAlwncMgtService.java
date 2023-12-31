package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndRegionLevelAlwncMgtConverter;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAlwncMgtDto.*;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelAlwncDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndRegionLevelAlwncMgtMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsndRegionLevelAlwncMgtService {

    private final WsndRegionLevelAlwncMgtMapper mapper;

    private final WsndRegionLevelAlwncMgtConverter converter;

    /**
     * 급지 기본 정보 조회
     *
     * @return
     */
    public SearchBaseRes getAllowanceBases() {
        AllowanceBase movementBases = this.mapper.selectBases(SnServiceConst.RglvlDvCd.REGION_LEVEL1.getCode());
        AllowanceBase bizBases = this.mapper.selectBases(SnServiceConst.RglvlDvCd.REGION_LEVEL2.getCode());
        return new SearchBaseRes(movementBases, bizBases);
    }

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
            WsndRegionLevelAlwncDvo regionLevelAw = this.converter.mapSaveReqToWsndRegionLevelAwDvo(dto);
            int updateCount = this.mapper.updateAllowance(regionLevelAw);
            int insertCount = this.mapper.insertAllowance(regionLevelAw);
            processCount += (updateCount & insertCount);
        }

        return processCount;
    }

}
