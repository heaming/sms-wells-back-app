package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbEdiCardAprovalRefundInterfaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbEdiCardAprovalRefundInterfaceMapper {

    List<WwdbEdiCardAprovalRefundInterfaceDto.SearchRes> selectEdiCardAprovalRefunds(WwdbEdiCardAprovalRefundInterfaceDto.SearchReq dto);
}
