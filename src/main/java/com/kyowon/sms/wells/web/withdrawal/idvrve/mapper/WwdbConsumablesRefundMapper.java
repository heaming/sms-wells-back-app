package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchConsumablesRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchConsumablesRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchContractInfoRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbConsumablesRefundDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbConsumablesRefundMapper {
    PagingResult<SearchConsumablesRefundRes> selectConsumablesRefund(
        SearchConsumablesRefundReq req,
        PageInfo pageInfo
    );

    List<SearchConsumablesRefundRes> selectConsumablesRefund(
        SearchConsumablesRefundReq req
    );

    int insertConsumablesRefundHistory(WwdbConsumablesRefundDvo dvo);

    int deleteConsumablesRefund(WwdbConsumablesRefundDvo dvo);

    int insertConsumablesRefundDetailHistory(WwdbConsumablesRefundDvo dvo);

    int deleteConsumablesRefundDetail(WwdbConsumablesRefundDvo dvo);

    int insertConsumablesRefund(WwdbConsumablesRefundDvo dvo);

    int insertConsumablesRefundDetail(WwdbConsumablesRefundDvo dvo);

    SearchContractInfoRes selectContractInfo(String cntrNo);

    List<SearchCardRes> selectConsumablesRefundCard();

    List<SearchBankRes> selectConsumablesRefundBank();

}
