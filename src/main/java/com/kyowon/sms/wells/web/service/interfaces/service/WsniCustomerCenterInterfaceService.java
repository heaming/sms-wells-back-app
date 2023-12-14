package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniCustomerCenterInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerCenterInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCustomerCenterInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsniCustomerCenterInterfaceService {
    private final WsniCustomerCenterInterfaceMapper mapper;

    private final WsniCustomerCenterInterfaceConverter converter;
    private final MessageResourceService messageService;

    public List<SearchContactRes> getEngineerContactPs(SearchReq dto) {
        //        List<SearchContactRes> searchRes = mapper.selectEngineerContactPs(dto);

        //        if (ObjectUtils.isEmpty(searchRes)) {
        //            throw new BizException("MSG_TXT_NO_DATA_RM");
        //        }

        return mapper.selectEngineerContactPs(dto);
    }

    public List<SearchPromChRes> getEngineerPromChHist(SearchReq dto) {
        List<SearchPromChRes> searchRes = mapper.selectEngineerPromChHist(dto);

        // if (ObjectUtils.isEmpty(searchRes)) {
        //     throw new BizException("MSG_TXT_NO_DATA_RM");
        // }

        return searchRes;
    }

    public List<SearchCancelRes> getEngineerCancels(SearchReq dto) {
        List<SearchCancelRes> searchRes = mapper.selectEngineerCancels(dto);

        // if (ObjectUtils.isEmpty(searchRes)) {
        //     throw new BizException("MSG_TXT_NO_DATA_RM");
        // }

        return searchRes;
    }

    public List<SearchSppPdctRes> getSeedingRegularShippingPdct(SearchReq dto) {
        //        List<SearchSppPdctRes> searchRes = mapper.selectSeedingRegularShippingPdct(dto);

        //        if (ObjectUtils.isEmpty(searchRes)) {
        //            throw new BizException("MSG_TXT_NO_DATA_RM");
        //        }

        return mapper.selectSeedingRegularShippingPdct(dto);
    }

    public List<SearchSppVstRes> getSeedingRegularShippingVst(SearchReq dto) {
        List<SearchSppVstRes> searchRes = mapper.selectSeedingRegularShippingVst(dto);

        //        if (ObjectUtils.isEmpty(searchRes)) {
        //            throw new BizException("MSG_TXT_NO_DATA_RM");
        //        }

        return searchRes;
    }

    public List<SearchAsRes> getAfterServiceBusinessInf(SearchReq dto) {
        List<SearchAsRes> searchRes = mapper.selectAfterServiceBusinessInf(dto);

        //        if (ObjectUtils.isEmpty(searchRes)) {
        //            throw new BizException("MSG_TXT_NO_DATA_RM");
        //        }

        return searchRes;
    }

    @Transactional
    public CreateShpadrRes createFilterShippingAddress(CreateShpadrReq dto) {
        WsniCustomerCenterInterfaceDvo dvo = converter.mapCreateShpadrResToCenterInterfaceDvo(dto);

        // 이전 배송차수 종료일 UPDATE
        mapper.updateFilterShippingAddress(dvo);

        int result = mapper.insertFilterShippingAddress(dvo);

        BizAssert.isTrue(result == 1, messageService.getMessage("MSG_ALT_SVE_ERR"));

        // @TODO: TEMP_CODE :: 메세지 정상 출력되는지 확인 필요
        return new CreateShpadrRes(messageService.getMessage("MSG_ALT_SAVE_DATA"), "S001");
    }

    @Transactional
    public EditShpadrRes editFilterShippingAddress(EditShpadrReq dto) {
        WsniCustomerCenterInterfaceDvo dvo = converter.mapEditShpadrResToCenterInterfaceDvo(dto);

        mapper.updateFilterShippingAddress(dvo);

        // @TODO: TEMP_CODE :: 메세지 정상 출력되는지 확인 필요
        return new EditShpadrRes("MSG_ALT_SAVE_DATA", "S001");
    }

    public FindAdnInfRes getAdditional(FindAdnInfReq dto) {
        WsniCustomerCenterInterfaceDvo returnDvo;
        WsniCustomerCenterInterfaceDvo tempDvo;

        tempDvo = mapper.selectAllCleanYn(dto.cntrNo(), dto.cntrSn());

        returnDvo = mapper.selectFilterShippingAddressInfo(dto);
        returnDvo.setIstLctDtlCn(mapper.selectIstLctDtlCn(dto));
        returnDvo.setChangeCount(mapper.selectTotalMaterialUseYn(dto));
        returnDvo.setBsStopYn(mapper.selectBsStopYn(dto));
        returnDvo.setChangeYn(mapper.selectBespokePanelChangeYn(dto));
        returnDvo.setChangePossibleYn(mapper.selectTopPlateChangePossibleYn(dto));

        if (!ObjectUtils.isEmpty(tempDvo)) {
            returnDvo.setAllCleanMsg(tempDvo.getAllCleanMsg());
            returnDvo.setAllCleanYn(tempDvo.getAllCleanYn());
        } else {
            returnDvo.setAllCleanMsg("");
            returnDvo.setAllCleanYn("");
        }

        return converter.mapDvoToFindAdnInfResDto(returnDvo);
    }

    public List<SearchPkgChRes> getPackageChangeHistory(FindAdnInfReq dto) {
        List<SearchPkgChRes> searchRes = mapper.selectPackageChangeHistory(dto);

        if (ObjectUtils.isEmpty(searchRes)) {
            throw new BizException("MSG_TXT_NO_DATA_RM");
        }

        return searchRes;
    }

    public List<SearchFiltShpadrRes> getFilterShippingAddress(SearchFiltShpadrReq dto) {
        return mapper.selectFilterShippingAddress(dto);
    }
}
