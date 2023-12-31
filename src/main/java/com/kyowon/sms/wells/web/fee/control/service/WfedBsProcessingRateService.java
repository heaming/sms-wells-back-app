package com.kyowon.sms.wells.web.fee.control.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.fee.common.dto.ZfezFeeNetOrderStatusDto;
import com.kyowon.sms.common.web.fee.common.service.ZfezFeeNetOrderStatusService;
import com.kyowon.sms.wells.web.fee.control.converter.WfedBsProcessingRateConverter;
import com.kyowon.sms.wells.web.fee.control.dto.WfedBsProcessingRateDto.SaveReq;
import com.kyowon.sms.wells.web.fee.control.dto.WfedBsProcessingRateDto.SearchReq;
import com.kyowon.sms.wells.web.fee.control.dto.WfedBsProcessingRateDto.SearchRes;
import com.kyowon.sms.wells.web.fee.control.dvo.WfedBsProcessingRateDvo;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedBsProcessingRateMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * BS처리율 조정 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.02
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfedBsProcessingRateService {

    private final WfedBsProcessingRateMapper mapper;

    private final WfedBsProcessingRateConverter converter;

    private final ZfezFeeNetOrderStatusService zfezFeeNetOrderStatusService;

    /**
     * BS처리율 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * prtnrNo : 번호 }
     * @return 조회결과
     */
    public List<SearchRes> getBsProcessingRates(
        SearchReq dto
    ) {
        return mapper.selectBsProcessingRates(dto);
    }

    /**
     * BS처리율 저장
     * @param dtos
     * @return 처리결과
     */
    @Transactional
    public int saveBsProcessingRates(
        List<SaveReq> dtos
    ) {
        int processCnt = 0;
        String msgId = "";

        for (SaveReq dto : dtos) {
            WfedBsProcessingRateDvo dvo = converter.mapSaveReqToWfedBsProcessingRateDvo(dto);

            // 순주문이 확정되었는지 체크한다.
            ZfezFeeNetOrderStatusDto.SearchRes searchRes = zfezFeeNetOrderStatusService
                .getFeeNetOrderStat(dto.baseYm(), "02", "201", "02");
            BizAssert.isTrue(
                !(searchRes != null && "02".equals(searchRes.ntorCnfmStatCd())),
                "MSG_ALT_BF_CNFM_MDFC_IMP"
            ); //이미 순주문이 확정되어 수정이 불가합니다.

            // 수정하기 전, BS 실적집계가 완료된 상태인지 체크한다.
            int cnt = mapper.selectBsAgrgCheck(dvo);
            BizAssert.isTrue(cnt > 0, "MSG_ALT_BFSVC_PERF_AGRG_AFT_PRGS"); //BS실적집계 후 진행하세요.

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED, CommConst.ROW_STATE_UPDATED -> {
                    dvo.setPerfVal(dvo.getSv01999910());
                    msgId = "MSG_ALT_SVE_ERR";
                }
                case CommConst.ROW_STATE_DELETED -> {
                    // 수정한 처리율을 원래 처리율로 원복한다.
                    dvo.setPerfVal(dvo.getSv01999909());
                    msgId = "MSG_ALT_DEL_ERR";
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE"); //처리할 수 없는 rowState값입니다.
            }
            processCnt = mapper.updatePartnerBsProcessingRate(dvo);
            if (processCnt > 0) {
                // 순주문파트너실적월마감 데이터도 함께 수정
                processCnt = mapper.updateMonthBsProcessingRate(dvo);
            }
            BizAssert.isTrue(processCnt == 1, msgId);
        }

        return processCnt;
    }

}
