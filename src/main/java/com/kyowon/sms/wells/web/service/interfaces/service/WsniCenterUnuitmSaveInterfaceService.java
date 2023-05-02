package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.sds.sflex.system.config.validation.BizAssert.isTrue;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniCenterUnuitmSaveInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnuitmSaveInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnuitmSaveInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCenterUnuitmSaveInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterUnuitmSaveInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCenterUnuitmSaveInterfaceService {
    private final WsniCenterUnuitmSaveInterfaceMapper mapper;
    private final WsniCenterUnuitmSaveInterfaceConverter converter;
    private final MessageResourceService messageService;

    @Transactional
    public CreateRes createUnusualItem(CreateReq createReq) {
        WsniCenterUnuitmSaveInterfaceDvo dvo = converter.mapCreatReqToUnuitmSaveInterfaceDvo(createReq);
        dvo.setOgTpCd(mapper.selectOgTpCd(dvo.getWkPrtnrNo()));

        try {
            int result = mapper.insertUnusualItem(dvo);

            isTrue(result == 1, "MSG_ALT_SVE_ERR");

            dvo.setResultCode("S001");
            dvo.setResultMessage(messageService.getMessage("MSG_ALT_SAVE_DATA"));
        } catch (Exception e) {
            throw new BizException(messageService.getMessage("MSG_ALT_SVE_ERR"));
        }

        return converter.mapCreateResToUnuitmSaveInterfaceDto(dvo);
    }
}
