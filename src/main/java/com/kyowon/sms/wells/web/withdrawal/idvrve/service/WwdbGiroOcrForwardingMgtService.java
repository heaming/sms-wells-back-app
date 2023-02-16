package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbGiroOcrForwardingMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SavePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchPrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchPrintRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.removePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDeleteDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbGiroOcrForwardingMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbGiroOcrForwardingMgtService {
    private final WwdbGiroOcrForwardingMgtMapper mapper;

    private final WwdbGiroOcrForwardingMgtConverter convert;

    /**
     * 지로OCR발송관리 목록조회
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public PagingResult<SearchRes> getGiroOcrForwardingPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectGiroOcrForwardings(dto, pageInfo);
    }

    /**
     * 지로OCR발송관리 대상 조회
     *
     * @return List<SearchRes>
     */
    @Transactional
    public List<SearchRes> getGiroOcrForwardingObjects() {
        return mapper.selectGiroOcrForwardingObjects();
    }

    @Transactional
    public int saveGiroOcrForwardings(List<SaveReq> dtos) throws Exception {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WwdbGiroOcrForwardingMgtDvo dvo = convert.mapSaveGiroOcrForwardingDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    //                    int duplicateCount = mapper.selectReceiveCodesCount(dvo);
                    //                    BizAssert.isTrue(duplicateCount == 0, "중복된 수납코드가 존재합니다.");
                    processCount += mapper.insertGiroOcrForwardings(dvo);
                }
                //                case CommConst.ROW_STATE_UPDATED -> {
                //                    processCount += mapper.updateGiroOcrForwardings(dvo);
                //                }
                //                case CommConst.ROW_STATE_DELETED -> processCount += mapper.deleteDivReceiveCd(dvo);
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }

        return processCount;
    }

    /**
     * 지로OCR발송관리 출력 조회
     *
     * @param dto
     * @return List<SearchPrintRes>
     */
    @Transactional
    public List<SearchPrintRes> getGiroOcrForwardingPrints(SearchPrintReq dto) {
        return mapper.selectGiroOcrForwardingPrints(dto);
    }

    /**
     * 지로OCR발송관리 출력 조회
     *
     * @param dto
     * @return List<SearchPrintRes>
     */
    @Transactional
    public int saveGiroOcrForwardingPrints(SavePrintReq dtos) throws Exception {
        int processCount = 0;
        int giroOcrForwardingDetailCount = 0;
        WwdbGiroOcrForwardingPrintDvo dvo = convert.mapSaveGiroOcrForwardingPrintDvo(dtos);
        switch (dtos.state()) {
            case CommConst.ROW_STATE_CREATED -> {
                int selectGiroOcrPk = mapper.selectGiroOcrPk();
                dvo.setGiroOcrPblSeqn(selectGiroOcrPk);
                giroOcrForwardingDetailCount = mapper.insertGiroOcrForwardingDetailPrints(dvo);
                if (giroOcrForwardingDetailCount == 0) {
                    BizAssert.isTrue(giroOcrForwardingDetailCount == 0, "생성할 자료가 없습니다. 작업일을 확인하세요.");
                }
                dvo.setGiroOcrPblTotQty(giroOcrForwardingDetailCount);
                processCount += mapper.insertGiroOcrForwardingPrints(dvo);
            }
            default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
        }
        return processCount;
    }

    @Transactional
    public int removeGiroOcrForwardingPrints(List<removePrintReq> dtos) {
        int processCount = 0;

        for (removePrintReq dto : dtos) {
            WwdbGiroOcrForwardingPrintDeleteDvo dvo = convert.mapDeleteGiroOcrForwardingPrintDvo(dto);
            processCount += mapper.deleteGiroOcrForwardingPrints(dvo);
        }

        return processCount;
    }
}
