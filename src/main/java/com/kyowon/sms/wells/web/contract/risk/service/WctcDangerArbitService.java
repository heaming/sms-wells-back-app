package com.kyowon.sms.wells.web.contract.risk.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.risk.converter.WctcDangerArbitConverter;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcDangerArbitDvo;
import com.kyowon.sms.wells.web.contract.risk.mapper.WctcDangerArbitMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctcDangerArbitService {
    private final WctcDangerArbitMapper mapper;
    private final WctcDangerArbitConverter converter;

    public List<SearchRes> getDangerArbitManagerial(SearchReq dto) {
        return mapper.selectDangerArbitManagerial(dto);
    }

    @Transactional
    public int removeDangerArbitManagerial(List<String> dangChkIds) {
        int processCount = 0;
        int result = 0;
        for (Iterator<String> iterator = dangChkIds.iterator(); iterator.hasNext(); processCount += result) {
            String dangChkId = iterator.next();
            mapper.updateDangerCheckIzDlYn(dangChkId);
            mapper.updateDangerCheckChHist(dangChkId);
            result = mapper.insertDangerCheckChHistY(dangChkId);
        }
        return processCount;
    }

    @Transactional
    public int saveDangerArbitManagerial(List<SaveReq> dtos) {
        int processCount = 0;
        Iterator<SaveReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveReq dto = iterator.next();
            WctcDangerArbitDvo dangerArbitManagerial = converter.mapSaveReqWctcDangerArbitDvo(dto);
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    dangerArbitManagerial.setDangMngtPrtnrNo(dangerArbitManagerial.getDangOjPrtnrNo());
                    dangerArbitManagerial.setDangMngtPstnDvCd(dangerArbitManagerial.getDangOjPstnDvCd());
                    mapper.insertDangerCheckIz(dangerArbitManagerial);
                    int result = mapper.insertDangerCheckChHistN(dangerArbitManagerial.getDangChkId());
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    mapper.updateDangerCheckIz(dangerArbitManagerial);
                    int result = mapper.insertDangerCheckChHistN(dto.dangChkId());
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };

            //
        }
        return processCount;
    }
}
