package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbCashReceiptApprovalInterfaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbCashReceiptApprovalInterfaceMapper {

    List<WwdbCashReceiptApprovalInterfaceDto.SearchRes> selectCashReceiptApprovalItemizations(WwdbCashReceiptApprovalInterfaceDto.SearchReq dto);

}
