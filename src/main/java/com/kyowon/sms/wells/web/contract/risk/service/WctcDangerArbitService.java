package com.kyowon.sms.wells.web.contract.risk.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
    public int removeDangerArbitManagerial(List<SaveReq> dtos) {
        int processCount = 0;
        int result = 0;
        for (Iterator<SaveReq> iterator = dtos.iterator(); iterator.hasNext(); processCount += result) {
            SaveReq dto = iterator.next();
            String dangChkId = "";
            if (StringUtils.isNotEmpty(dto.dgr1HgrDgPrtnrNo())) {
                dangChkId = mapper.selectDangChkId(dto.dangOjPrtnrNo(), dto.dangOcStrtmm(), "2");
                mapper.updateDangerCheckIzDlYn(dangChkId);
                mapper.updateDangerCheckChHist(dangChkId);
                mapper.insertDangerCheckChHistY(dangChkId);
            }
            if (StringUtils.isNotEmpty(dto.dgr2HgrDgPrtnrNo())) {
                dangChkId = mapper.selectDangChkId(dto.dangOjPrtnrNo(), dto.dangOcStrtmm(), "4");
                mapper.updateDangerCheckIzDlYn(dangChkId);
                mapper.updateDangerCheckChHist(dangChkId);
                mapper.insertDangerCheckChHistY(dangChkId);
            }
            if (StringUtils.isNotEmpty(dto.dgr3HgrDgPrtnrNo())) {
                dangChkId = mapper.selectDangChkId(dto.dangOjPrtnrNo(), dto.dangOcStrtmm(), "7");
                mapper.updateDangerCheckIzDlYn(dangChkId);
                mapper.updateDangerCheckChHist(dangChkId);
                mapper.insertDangerCheckChHistY(dangChkId);
            }
            dangChkId = mapper.selectDangChkId(dto.dangOjPrtnrNo(), dto.dangOcStrtmm(), "15");
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
                    if (StringUtils.isNotEmpty(dto.dgr1HgrDgPrtnrNo())) {
                        dangerArbitManagerial.setDangMngtPstnDvCd("2");
                        dangerArbitManagerial.setDangMngtPrtnrNo(dto.dgr1HgrDgPrtnrNo());
                        mapper.insertDangerCheckIz(dangerArbitManagerial);
                        mapper.insertDangerCheckChHistN(dangerArbitManagerial.getDangChkId());
                    }
                    if (StringUtils.isNotEmpty(dto.dgr2HgrDgPrtnrNo())) {
                        dangerArbitManagerial.setDangMngtPstnDvCd("4");
                        dangerArbitManagerial.setDangMngtPrtnrNo(dto.dgr2HgrDgPrtnrNo());
                        mapper.insertDangerCheckIz(dangerArbitManagerial);
                        mapper.insertDangerCheckChHistN(dangerArbitManagerial.getDangChkId());
                    }
                    if (StringUtils.isNotEmpty(dto.dgr3HgrDgPrtnrNo())) {
                        dangerArbitManagerial.setDangMngtPstnDvCd("7");
                        dangerArbitManagerial.setDangMngtPrtnrNo(dto.dgr1HgrDgPrtnrNo());
                        mapper.insertDangerCheckIz(dangerArbitManagerial);
                        mapper.insertDangerCheckChHistN(dangerArbitManagerial.getDangChkId());
                    }
                    dangerArbitManagerial.setDangMngtPstnDvCd("15");
                    dangerArbitManagerial.setDangMngtPrtnrNo(dangerArbitManagerial.getDangOjPstnDvCd());
                    mapper.insertDangerCheckIz(dangerArbitManagerial);
                    int result = mapper.insertDangerCheckChHistN(dangerArbitManagerial.getDangChkId());
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    yield result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    if (StringUtils.isNotEmpty(dto.dgr1HgrDgPrtnrNo())) {
                        String dangChkId = mapper.selectDangChkId(dto.dgr1HgrDgPrtnrNo(), dto.dangOcStrtmm(), "2");
                        mapper.insertDangerCheckChHistN(dangChkId);
                    }
                    if (StringUtils.isNotEmpty(dto.dgr2HgrDgPrtnrNo())) {
                        String dangChkId = mapper.selectDangChkId(dto.dgr2HgrDgPrtnrNo(), dto.dangOcStrtmm(), "4");
                        mapper.insertDangerCheckChHistN(dangChkId);
                    }
                    if (StringUtils.isNotEmpty(dto.dgr3HgrDgPrtnrNo())) {
                        String dangChkId = mapper.selectDangChkId(dto.dgr3HgrDgPrtnrNo(), dto.dangOcStrtmm(), "7");
                        mapper.insertDangerCheckChHistN(dangChkId);
                    }
                    mapper.updateDangerCheckIz(dangerArbitManagerial);
                    String dangChkId = mapper.selectDangChkId(dto.dangOjPrtnrNo(), dto.dangOcStrtmm(), "15");
                    int result = mapper.insertDangerCheckChHistN(dangChkId);
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
