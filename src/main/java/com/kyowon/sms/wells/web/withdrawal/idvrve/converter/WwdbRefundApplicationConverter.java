package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.*;

@Mapper(componentModel = "spring")
public interface WwdbRefundApplicationConverter {
    //    WwdbRefundApplicationDvo mapSaveWwdbRefundApplicationDvo(SaveRefundReq req);

    // 임시저장
    WwdbRefundBaseDvo mapTempSaveWwdbRefundBaseDvo(WwdbRefundApplicationDto.SaveBaseReq req);

    /* 환불요청계약상세 */
    WwdbRefundCntrDvo mapTempSaveWwdbRefundCntrDvo(WwdbRefundApplicationDto.SaveCntrReq req);

    WwdbRefundDtlDvo mapTempSaveWwdbRefundDtlDvo(WwdbRefundApplicationDto.SaveDtlReq req);

    WwdbRefundRemoveDvo mapRemoveWwdbRefundDvo(WwdbRefundApplicationDto.removeReq req);

    WwdbRefundBltfDvo mapTempSaveWwdbRefundBltfDvo(WwdbRefundApplicationDto.SaveBltfReq req);

    //        WwdbRefundApplicationInfoDvo mapEditWwdbRefundApplicationDvo(EditRefundReq req);
}
