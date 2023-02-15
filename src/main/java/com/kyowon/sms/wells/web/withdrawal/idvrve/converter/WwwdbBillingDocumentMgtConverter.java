package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveMainReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentForwardingDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwwdbBillingDocumentMgtConverter {
    WwwdbBillingDocumentMgtDvo mapDeleteWwwdbBillingDocumentMgtDvo(RemoveReq dto);

    WwwdbBillingDocumentDvo mapSaveWwwdbBillingDocumentDvo(SaveMainReq dto);

    WwwdbBillingDocumentDetailDvo mapSaveWwwdbBillingDocumentDetailDvo(SaveDtlsReq dto);

    WwwdbBillingDocumentForwardingDvo mapSaveWwwBillingDocumentForwardingDvo(SaveFwReq dto);
}
