package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchServiceRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SearchServiceRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbServiceRefundDvo;

@Mapper
public interface WwdbServiceRefundMapper {

    SearchServiceRefundRes selectServiceRefundCustomer(
        SearchServiceRefundReq req
    );

    List<SearchCardRes> selectServiceRefundCard();

    List<SearchBankRes> selectServiceRefundBank();

    int updateServiceRefundCustomer(WwdbServiceRefundDvo dvo);

}
