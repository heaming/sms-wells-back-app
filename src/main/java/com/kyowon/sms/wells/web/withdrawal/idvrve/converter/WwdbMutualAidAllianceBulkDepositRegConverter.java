package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SaveUploadReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbMutualAidAllianceBulkDepositRegDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbMutualAidAllianceBulkDepositRegConverter {
    WwdbMutualAidAllianceBulkDepositRegDvo mapSaveWwdbMutualAidAllianceBulkDepositRegDvo(SaveUploadReq dvo);
}
