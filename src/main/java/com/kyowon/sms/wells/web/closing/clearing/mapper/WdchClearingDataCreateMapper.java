package com.kyowon.sms.wells.web.closing.clearing.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.clearing.dvo.*;

@Mapper
public interface WdchClearingDataCreateMapper {
    List<WdchEduSlCnfmBasDvo> selectWdchEduSlCnfmBass(WdchEduSlSalesDataDvo slSalesDataDvo);

    List<WdchBznsAtamBasDvo> selectBznsAtamBas(WdchEduSlSalesDataDvo slSalesDataDvo);

    WdchSlBndAlrpyBasBeforeMonthDvo selectSlBndAlrpyBasBeforeMonth(WdchEduSlSalesDataDvo slSalesDataDvo);

    List<WdchDepositConfirmationDvo> selectDepositConfirmation(WdchEduSlSalesDataDvo slSalesDataDvo);

    int selectWdchEduSlCnfmBasCount(WdchEduSlSalesDataDvo dvo);

    int selectWdchNonEduSlCnfmBasCount(WdchEduSlSalesDataDvo dvo);

    WdchSlBndAlrpyBasDvo selectSlBndAlrpyBasBeforeOneMonth(WdchEduSlSalesDataDvo dvo);
}
