package com.kyowon.sms.wells.web.bond.credit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;

@Mapper
public interface WbndRentalCbMgtDelinquentHistoryMapper {

    List<WbndRentalCbDelinquentIzDvo> selectRentalCbMgtDelinquentHistories(
        WbndRentalCbDelinquentIzDvo dvo
    );

    int updateRentalCbDlqIz(
        WbndRentalCbDelinquentIzDvo dvo
    );

    int insertRentalCbDlqHistory(
        WbndRentalCbDelinquentIzDvo dvo
    );

    int updateMessageSendDate(
        @Param("cstNo")
        String cstNo,
        @Param("baseYm")
        String baseYm
    );

}
