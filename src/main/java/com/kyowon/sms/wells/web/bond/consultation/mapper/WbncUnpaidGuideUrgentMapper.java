package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentDto.*;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncUncollectedAdviceNoteOjIzDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WbncUnpaidGuideUrgentMapper {

    PagingResult<SearchRes> selectUnpaidGuideUrgentPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectUnpaidGuideUrgentPages(
        SearchReq dto
    );

    List<WbncUncollectedAdviceNoteOjIzDvo> selectCheckUnpaidGuideUrgentObjects(CheckReq dto);

    int selectCheckUnpaidGuideUrgentCustomers(CheckReq dto);

    int updateUnpaidGuideUrgentObjects(SaveObjectReq dto);

    int deleteAllUnpaidGuideUrgentObjects(CreateObjectReq dto);

    int insertUnpaidGuideUrgentObjects(CreateObjectReq dto);

    int deleteAllUnpaidGuideUrgentCustomers(CreateCustomerReq dto);

    int insertUnpaidGuideUrgentCustomers(CreateCustomerReq dto);
}
