package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.SearchOrderDetailMshPagesRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.SearchOrderDetailRglrDlvrPagesRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailMembershipPagesDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailRentalPagesDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailRglrDlvrPagesDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WctaOrderDetailConverter {
    PagingResult<SearchRes> mapWctaOrderDetailRentalPagesDvoToSearchRes(
        List<WctaOrderDetailRentalPagesDvo> dvos
    );

    List<SearchRes> mapWctaOrderDetailRentalPagesExcelDvoToSearchRes(List<WctaOrderDetailRentalPagesDvo> dvos);

    PagingResult<SearchOrderDetailMshPagesRes> mapWctaOrderDetailMembershipPagesDvoToSearchOrderDetailMshPagesRes(
        List<WctaOrderDetailMembershipPagesDvo> dvos
    );

    List<SearchOrderDetailMshPagesRes> mapWctaOrderDetailMembershipPagesExcelDvoToSearchOrderDetailMshPagesRes(
        List<WctaOrderDetailMembershipPagesDvo> dvos
    );

    PagingResult<SearchOrderDetailRglrDlvrPagesRes> mapWctaOrderDetailRglrDlvrPagesDvoToSearchOrderDetailRglrDlvrPagesRes(
        List<WctaOrderDetailRglrDlvrPagesDvo> dvos
    );

    List<SearchOrderDetailRglrDlvrPagesRes> mapWctaOrderDetailRglrDlvrPagesExcelDvoToSearchOrderDetailRglrDlvrPagesRes(
        List<WctaOrderDetailRglrDlvrPagesDvo> dvos
    );
}
