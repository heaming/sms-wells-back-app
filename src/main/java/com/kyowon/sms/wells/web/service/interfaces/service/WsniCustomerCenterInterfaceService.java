package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCustomerCenterInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCustomerCenterInterfaceService {
    private final WsniCustomerCenterInterfaceMapper mapper;
    private final MessageResourceService messageService;

    public List<SearchContactRes> getEngineerContactPs(SearchReq dto) {
        List<SearchContactRes> searchRes = mapper.selectEngineerContactPs(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            //throw new BizException(messageService.getMessage("MSG_TXT_NO_DATA_RM"));
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchPromChRes> getEngineerPromChHist(SearchReq dto) {
        List<SearchPromChRes> searchRes = mapper.selectEngineerPromChHist(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            //throw new BizException(messageService.getMessage("MSG_TXT_NO_DATA_RM"));
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchCancelRes> getEngineerCancels(SearchReq dto) {
        List<SearchCancelRes> searchRes = mapper.selectEngineerCancels(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
//            throw new BizException(messageService.getMessage("MSG_TXT_NO_DATA_RM"));
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchSppPdctRes> getSeedingRegularShippingPdct(SearchReq dto) {
        List<SearchSppPdctRes> searchRes = mapper.selectSeedingRegularShippingPdct(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
//            throw new BizException(messageService.getMessage("MSG_TXT_NO_DATA_RM"));
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchSppVstRes> getSeedingRegularShippingVst(SearchReq dto) {
        List<SearchSppVstRes> searchRes = mapper.selectSeedingRegularShippingVst(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
//            throw new BizException(messageService.getMessage("MSG_TXT_NO_DATA_RM"));
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchAsRes> getAfterServiceBusinessInf(SearchReq dto) {
        List<SearchAsRes> searchRes = mapper.selectAfterServiceBusinessInf(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
//            throw new BizException(messageService.getMessage("MSG_TXT_NO_DATA_RM"));
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }
}
