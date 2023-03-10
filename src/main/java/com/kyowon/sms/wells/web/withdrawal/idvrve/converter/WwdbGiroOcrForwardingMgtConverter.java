package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SavePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.removePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDeleteDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbGiroOcrForwardingMgtConverter {
    WwdbGiroOcrForwardingMgtDvo mapSaveGiroOcrForwardingDvo(SaveReq dto);

    WwdbGiroOcrForwardingMgtDvo mapRemoveGiroOcrForwardingDvo(RemoveReq dto);

    WwdbGiroOcrForwardingPrintDvo mapSaveGiroOcrForwardingPrintDvo(SavePrintReq dto);

    WwdbGiroOcrForwardingPrintDeleteDvo mapDeleteGiroOcrForwardingPrintDvo(removePrintReq dto);
}
