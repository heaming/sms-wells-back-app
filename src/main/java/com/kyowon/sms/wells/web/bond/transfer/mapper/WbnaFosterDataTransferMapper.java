package com.kyowon.sms.wells.web.bond.transfer.mapper;

import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaFosterDataSearchReqDvo;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaFosterDataTransferDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WbnaFosterDataTransferMapper {
    WbnaFosterDataTransferDvo selectFosterData(WbnaFosterDataSearchReqDvo dvo);
}
