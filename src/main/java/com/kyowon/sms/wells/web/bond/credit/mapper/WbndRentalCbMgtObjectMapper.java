package com.kyowon.sms.wells.web.bond.credit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WbndRentalCbMgtObjectMapper {

    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMessageObjectsByCustomer(
        WbndRentalCbDelinquentIzDvo dvo
    );

    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMessageObjectsByContract(
        WbndRentalCbDelinquentIzDvo dvo
    );

    int updateMessageObjectYn(
        WbndRentalCbDelinquentIzDvo dto
    );

    int insertMessageObjectHist(
        WbndRentalCbDelinquentIzDvo dto
    );

    PagingResult<WbndRentalCbDelinquentIzDvo> selectRentalCbMgtPaymentInfos(@Param("cstNo")
    String cstNo, PageInfo pageInfo);

    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMgtPaymentInfos(@Param("cstNo")
    String cstNo);

}
