package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchProductReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchProductRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.WareMngtRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMaterialDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsRegularShippingMgtDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleCnfmDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

;

@Mapper
public interface WsnaBsRegularShippingMgtMapper {

    List<SearchProductRes> selectProducts(SearchProductReq dto);

    List<WsnaBsRegularShippingMgtDvo> selectShippingItems(SearchReq dto);

    PagingResult<WsnaBsRegularShippingMgtDvo> selectShippingItems(SearchReq dto, PageInfo pageInfo);

    int insertParcelFwInformation(WsnaBsRegularShippingMaterialDvo materialDvo);

    String selectOstAkNo(WsnaBsRegularShippingMgtDvo dvo);

    int insertOutOfStorage(WsnaBsRegularShippingMaterialDvo materialDvo);

    String selectMngtUnit(String partCd);

    int insertWorkResult(WsnaBsRegularShippingMgtDvo dvo);

    int updateBsPeriod(WsnaBsRegularShippingMgtDvo dvo);

    int updateBsAssign(WsnaBsRegularShippingMgtDvo dvo);

    int updateExecution(WsnaBsRegularShippingMgtDvo dvo);

    String selectNewLgstOstrAkNo();

    WareMngtRes selectWareMngtInfo(String wareNo);

    int insertSdingOutOfStorage(WsnaSeedReleaseScheduleCnfmDvo seedDvo);

    WsnaBsRegularShippingMgtDvo selectSdingInfo(WsnaSeedReleaseScheduleCnfmDvo seedDvo);

    WsnaBsRegularShippingMgtDvo selectWellsfarmMachine(WsnaBsRegularShippingMgtDvo sdingDvo);
}
