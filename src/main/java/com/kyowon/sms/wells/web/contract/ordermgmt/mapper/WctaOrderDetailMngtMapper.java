package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailMembershipPagesDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailRentalPagesDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailRglrDlvrPagesDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctaOrderDetailMngtMapper {
    PagingResult<WctaOrderDetailRentalPagesDvo> selectOrderDetailRentalPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WctaOrderDetailRentalPagesDvo> selectOrderDetailRentalPages(
        SearchReq dto
    );

    PagingResult<WctaOrderDetailMembershipPagesDvo> selectOrderDetailMshPages(
        SearchOrderDetailMshPagesReq dto,
        PageInfo pageInfo
    );

    List<WctaOrderDetailMembershipPagesDvo> selectOrderDetailMshPages(
        SearchOrderDetailMshPagesReq dto
    );

    PagingResult<WctaOrderDetailRglrDlvrPagesDvo> selectOrderRegularShippingsPages(
        SearchOrderDetailRglrDlvrPagesReq dto,
        PageInfo pageInfo
    );

    List<WctaOrderDetailRglrDlvrPagesDvo> selectOrderRegularShippingsPages(
        SearchOrderDetailRglrDlvrPagesReq dto
    );
}
