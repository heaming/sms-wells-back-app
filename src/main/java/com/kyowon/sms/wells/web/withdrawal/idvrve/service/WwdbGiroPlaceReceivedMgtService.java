package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbGiroPlaceReceivedMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedMgtDto.FindReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedMgtDto.FindRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroPlaceReceivedMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbGiroPlaceReceivedMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 지로 수신처 관리 서비스
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-05-19
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbGiroPlaceReceivedMgtService {
    private final WwdbGiroPlaceReceivedMgtMapper mapper;

    private final WwdbGiroPlaceReceivedMgtConverter convert;

    /**
     * 지로 수신처 조회
     * @param dto
     * @return FindRes
     */
    public FindRes getGiroPlaceReceived(FindReq dto) {
        return mapper.selectGiroPlaceReceived(dto);
    }

    /**
     * 지로 수신처 저장
     * @param dto
     * @return int processCount
     * @throws Exception
     */
    public int saveGiroPlaceReceived(SaveReq dto) throws Exception {
        int processCount = 0;
        WwdbGiroPlaceReceivedMgtDvo dvo = convert.mapSaveWwdbGiroPlaceReceivedDvo(dto);

        processCount += mapper.updateGiroPlaceReceived(dvo);
        processCount += mapper.insertGiroPlaceReceivedHistory(dvo);

//        switch (dto.state()) {
//            case CommConst.ROW_STATE_CREATED -> {
//                processCount += mapper.insertGiroPlaceReceived(dvo);
//                processCount += mapper.insertGiroPlaceReceivedHistory(dvo);
//            }
//            case CommConst.ROW_STATE_UPDATED -> {
//                processCount += mapper.insertGiroPlaceReceivedHistory(dvo);
//                processCount += mapper.updateGiroPlaceReceived(dvo);
//            }
//            //                case CommConst.ROW_STATE_DELETED -> processCount += mapper.deleteDivReceiveCd(dvo);
//            default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
//        }
        return processCount;
    }

    /**
     * 계약정보 중복 체크
     * @param dto
     * @return int
     */
    public int getGiroPlaceDupliCationReceived(FindReq dto) {
        return mapper.selectGiroPlaceDupliCationReceived(dto);
    }
}
