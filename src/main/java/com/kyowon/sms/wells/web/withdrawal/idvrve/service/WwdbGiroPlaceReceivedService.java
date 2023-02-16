package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbGiroPlaceReceivedConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.FindReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.FindRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroPlaceReceivedDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbGiroPlaceReceivedMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbGiroPlaceReceivedService {
    private final WwdbGiroPlaceReceivedMapper mapper;

    private final WwdbGiroPlaceReceivedConverter convert;

    public FindRes getGiroPlaceReceived(FindReq dto) {
        return mapper.selectGiroPlaceReceived(dto);
    }

    public int saveGiroPlaceReceived(SaveReq dto) throws Exception {
        int processCount = 0;
        WwdbGiroPlaceReceivedDvo dvo = convert.mapSaveWwdbGiroPlaceReceivedDvo(dto);

        switch (dto.state()) {
            case CommConst.ROW_STATE_CREATED -> {
                processCount += mapper.insertGiroPlaceReceived(dvo);
                processCount += mapper.insertGiroPlaceReceivedHistory(dvo);
            }
            case CommConst.ROW_STATE_UPDATED -> {
                processCount += mapper.insertGiroPlaceReceivedHistory(dvo);
                processCount += mapper.updateGiroPlaceReceived(dvo);
            }
            //                case CommConst.ROW_STATE_DELETED -> processCount += mapper.deleteDivReceiveCd(dvo);
            default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
        }

        return processCount;
    }
}
