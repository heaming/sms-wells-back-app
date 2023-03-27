package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbRegistrationListConvert;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SaveMainDtlReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SaveMainReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchElectronicReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchElectronicRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRegistrationListDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRegistrationSubListDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRegistrationListMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbRegistrationListService {

    private final WwdbRegistrationListMapper mapper;

    private final WwdbRegistrationListConvert convert;

    @Transactional
    public PagingResult<SearchRes> getRegistrationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationPages(dto, pageInfo);
    }

    @Transactional
    public List<SearchRes> getRegistrationExcels(SearchReq dto) {
        return mapper.selectRegistrationPages(dto);
    }

    @Transactional
    public PagingResult<SearchDetailRes> getRegistrationElectronicPages(SearchDetailReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationElectronics(dto, pageInfo);
    }

    @Transactional
    public List<SearchDetailRes> getRegistrationElectronicExcels(SearchDetailReq dto) {
        return mapper.selectRegistrationElectronics(dto);
    }

    @Transactional
    public String getRegistrationPk() {
        return mapper.selectRegistrationPk();
    }

    @Transactional
    public int saveRegistrationElectronics(SaveReq dtos) throws Exception {

        int processCount = 0;

        SaveMainReq mainDto = dtos.saveMainReq();
        List<SaveMainDtlReq> subDto = dtos.SaveMainDtlReq();

        log.info("==============service");
        log.info(mainDto.toString());
        log.info("==============service");

        WwdbRegistrationListDvo dvo = convert.mapSaveWwdbRegistrationListDvo(mainDto);

        switch (mainDto.state()) {
            case CommConst.ROW_STATE_CREATED -> {
                processCount += mapper.insertRegistrationMainElectronics(dvo);
            }
            case CommConst.ROW_STATE_UPDATED -> {
                processCount += mapper.updateRegistrationMainElectronics(dvo);
            }
            default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");

        }

        if (subDto.size() > 0) {
            if (CommConst.ROW_STATE_CREATED.equals(subDto.get(0).rowState()))
                processCount += mapper.deleteRegistrationSubElectronics(dvo);

            for (SaveMainDtlReq dto : subDto) {
                WwdbRegistrationSubListDvo subDvo = convert.mapSaveWwdbRegistrationSubListDvo(dto);
                switch (dto.rowState()) {
                    case CommConst.ROW_STATE_CREATED -> {
                        processCount += mapper.insertRegistrationSubElectronics(subDvo);
                    }
                    case CommConst.ROW_STATE_UPDATED -> {
                        processCount += mapper.updateRegistrationSubElectronics(subDvo);
                    }
                    default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
                }

            }
        }

        return processCount;
    }

    @Transactional
    public PagingResult<SearchElectronicRes> getRegistrationElectronicDetailPages(
        SearchElectronicReq dto, PageInfo pageInfo
    ) {
        return mapper.selectRegistrationElectronicDetails(dto, pageInfo);
    }

    @Transactional
    public List<SearchElectronicRes> getRegistrationElectronicDetailExcels(
        SearchElectronicReq dto
    ) {
        return mapper.selectRegistrationElectronicDetails(dto);
    }

}
