package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerCenterInterfaceDvo;

@Mapper
public interface WsniCustomerCenterInterfaceMapper {

    List<SearchContactRes> selectEngineerContactPs(SearchReq dto);

    List<SearchPromChRes> selectEngineerPromChHist(SearchReq dto);

    List<SearchCancelRes> selectEngineerCancels(SearchReq dto);

    List<SearchSppPdctRes> selectSeedingRegularShippingPdct(SearchReq dto);

    List<SearchSppVstRes> selectSeedingRegularShippingVst(SearchReq dto);

    List<SearchAsRes> selectAfterServiceBusinessInf(SearchReq dto);

    int insertFilterShippingAddress(WsniCustomerCenterInterfaceDvo dvo);

    int updateFilterShippingAddress(WsniCustomerCenterInterfaceDvo dvo);

    String selectIstLctDtlCn(FindAdnInfReq dto);

    WsniCustomerCenterInterfaceDvo selectAllCleanYn(String cntrNo, String cntrSn);

    int selectTotalMaterialUseYn(FindAdnInfReq dto);

    WsniCustomerCenterInterfaceDvo selectFilterShippingAddressInfo(FindAdnInfReq dto);

    String selectBsStopYn(FindAdnInfReq dto);

    String selectBespokePanelChangeYn(FindAdnInfReq dto);

    String selectTopPlateChangePossibleYn(FindAdnInfReq dto);

    List<SearchPkgChRes> selectPackageChangeHistory(FindAdnInfReq dto);

    List<SearchFiltShpadrRes> selectFilterShippingAddress(SearchFiltShpadrReq dto);
}
