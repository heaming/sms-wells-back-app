package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto.SaveReq;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRpbAreaCodeMgtConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaCodeDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRpbAreaCodeMgtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2022.11.22
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncRpbAreaCodeMgtService {

    private final WsncRpbAreaCodeMgtMapper mapper;
    private final WsncRpbAreaCodeMgtConverter converter;

    /**
     * 책임지역 지역코드 관리 - 조회
     */
    public List<WsncRpbAreaCodeMgtDto.SearchRes> getAreaCodes(WsncRpbAreaCodeMgtDto.SearchReq dto) {
        List<WsncRpbAreaCodeDvo> res1 = mapper.selectAreaCodes(dto);
        return converter.mapWsncRpbAreaCodeDvoToSearchRes(res1);
    }

    /**
     * 책임지역 지역코드 관리 - 저장
     */
    @Transactional
    public int createAreaCodes(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WsncRpbAreaCodeDvo dvo = converter.mapSaveReqToWsncRpbAreaCodeDvo(dto);

            int result = mapper.insertAreaCode(dvo);
            int psicCount = mapper.selectCountAreaCodePsic(dvo);
            if (psicCount == 0) { // 변경한 책임지역코드의 책임지역담당자가 존재하지 않으면,
                mapper.insertAreaCodePsic(dvo);
            }
            processCount += result;
        }
        return processCount;
    }

}
