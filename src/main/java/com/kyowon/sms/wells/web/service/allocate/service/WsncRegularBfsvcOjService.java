package com.kyowon.sms.wells.web.service.allocate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRegularBfsvcOjConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcOjDto;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncRegularBfsvcOjService {

    private final WsncSpecCustAsnService wsncSpecCustAsnService;    //[W-SV-S-0027] 특정고객 배정 인서트

    private final WsncSpecCustPlanMatService wsncSpecCustPlanMatService;    //[W-SV-S-0029] 특정고객 예정자재 인서트

    private final WsncSpecCustMngrAsnService wsncSpecCustMngrAsnService;    //[W-SV-S-0031] 특정고객 담당자 지정 BS 오더 생성

    private final WsncRegularBfsvcOjConverter converter;

    @Transactional
    public int createRegularBfsvcOj(WsncRegularBfsvcOjDto.CreateReq dto) throws Exception {

        switch (dto.createTarget()) {
            case "A" -> {
                //Step 1. [W-SV-S-0027] ::: 특정고객 배정 인서트를 수행한다.
                wsncSpecCustAsnService.processSpecCustAsn(converter.mapSaveProcessReqToSpecCustAsnDvo(dto));
            }
            case "B" -> {
                //Step 2. [W-SV-S-0029] ::: 특정고객 예정자재 인서트 서비스를 수행한다.
                wsncSpecCustPlanMatService.processSpecCustPlanMat(converter.mapSaveProcessReqToSpecCustPlanMatDvo(dto));
            }
            case "H" -> {
                //Step 3. [W-SV-S-0031] ::: 특정고객 담당자 지정 BS 오더 생성 서비스를 수행한다.
                wsncSpecCustMngrAsnService.processSpecCustAsn(converter.mapSaveProcessReqToSpecCustMngrAsnDvo(dto));
            }
            default -> throw new BizException("MSG_TXT_ERROR_INFO");    //오류정보
        }
        return 1;
    }
}
