package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveMainDtlReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveMainReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtSubDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbBillDepositMgtConvert {
    WwdbBillDepositMgtDvo mapSaveWwdbRegistrationListDvo(SaveMainReq dto);

    WwdbBillDepositMgtSubDvo mapSaveWwdbRegistrationSubListDvo(SaveMainDtlReq dto);

}
