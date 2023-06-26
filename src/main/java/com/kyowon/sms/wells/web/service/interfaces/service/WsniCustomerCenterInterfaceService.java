package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniCustomerCenterInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerCenterInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCustomerCenterInterfaceMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCustomerCenterInterfaceService {
    private final WsniCustomerCenterInterfaceMapper mapper;

    private final WsniCustomerCenterInterfaceConverter converter;

    public List<SearchContactRes> getEngineerContactPs(SearchReq dto) {
        List<SearchContactRes> searchRes = mapper.selectEngineerContactPs(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchPromChRes> getEngineerPromChHist(SearchReq dto) {
        List<SearchPromChRes> searchRes = mapper.selectEngineerPromChHist(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchCancelRes> getEngineerCancels(SearchReq dto) {
        List<SearchCancelRes> searchRes = mapper.selectEngineerCancels(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchSppPdctRes> getSeedingRegularShippingPdct(SearchReq dto) {
        List<SearchSppPdctRes> searchRes = mapper.selectSeedingRegularShippingPdct(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchSppVstRes> getSeedingRegularShippingVst(SearchReq dto) {
        List<SearchSppVstRes> searchRes = mapper.selectSeedingRegularShippingVst(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchAsRes> getAfterServiceBusinessInf(SearchReq dto) {
        List<SearchAsRes> searchRes = mapper.selectAfterServiceBusinessInf(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    @Transactional
    public CreateShpadrRes createFilterShippingAddress(CreateShpadrReq dto) {
        WsniCustomerCenterInterfaceDvo dvo = converter.mapCreateShpadrResToCenterInterfaceDvo(dto);

        int result = mapper.insertFilterShippingAddress(dvo);

        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // @TODO: TEMP_CODE :: 메세지 정상 출력되는지 확인 필요
        return new CreateShpadrRes("MSG_ALT_SAVE_DATA", "S001");
    }

    @Transactional
    public EditShpadrRes editFilterShippingAddress(EditShpadrReq dto) {
        WsniCustomerCenterInterfaceDvo dvo = converter.mapEditShpadrResToCenterInterfaceDvo(dto);

        int result = mapper.updateFilterShippingAddress(dvo);

        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // @TODO: TEMP_CODE :: 메세지 정상 출력되는지 확인 필요
        return new EditShpadrRes("MSG_ALT_SAVE_DATA", "S001");
    }

    public FindAdnInfRes getAdditional(FindAdnInfReq dto) {
        WsniCustomerCenterInterfaceDvo returnDvo = new WsniCustomerCenterInterfaceDvo();
        WsniCustomerCenterInterfaceDvo tempDvo = new WsniCustomerCenterInterfaceDvo();

        tempDvo = mapper.selectAllCleanYn(dto);

        returnDvo = mapper.selectFilterShippingAddressInfo(dto);
        returnDvo.setAllCleanMsg(tempDvo.getAllCleanMsg());
        returnDvo.setAllCleanYn(tempDvo.getAllCleanYn());
        returnDvo.setIstLctDtlCn(mapper.selectIstLctDtlCn(dto));
        returnDvo.setChangeCount(mapper.selectTotalMaterialUseYn(dto));
        returnDvo.setBsStopYn(mapper.selectBsStopYn(dto));
        returnDvo.setChangeYn(mapper.selectBespokePanelChangeYn(dto));
        returnDvo.setChangePossibleYn(mapper.selectTopPlateChangePossibleYn(dto));

        return converter.mapDvoToFindAdnInfResDto(returnDvo);
    }
}
