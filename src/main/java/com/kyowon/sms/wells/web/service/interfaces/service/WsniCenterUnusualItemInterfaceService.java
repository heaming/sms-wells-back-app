package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.sds.sflex.system.config.validation.BizAssert.isTrue;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniCenterUnusualItemInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnusualItemInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterUnusualItemInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCenterUnusualItemInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCenterUnusualItemInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCenterUnusualItemInterfaceService {
    private final WsniCenterUnusualItemInterfaceMapper mapper;
    private final WsniCenterUnusualItemInterfaceConverter converter;
    private final MessageResourceService messageService;

    @Transactional
    public CreateRes createUnusualItem(CreateReq createReq) {
        WsniCenterUnusualItemInterfaceDvo dvo = converter.mapCreatReqToUnuitmSaveInterfaceDvo(createReq);
        dvo.setOgTpCd(mapper.selectOgTpCd(dvo.getWkPrtnrNo()));

        int result = mapper.insertUnusualItem(dvo);
        isTrue(result == 1, "MSG_ALT_SVE_ERR");

        return new CreateRes("S001", messageService.getMessage("MSG_ALT_SAVE_DATA"));
    }
}
