package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbVirtualAccountDto;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbVirtualAccountMapper {

    /**
     * 가상계좌조회 (모바일)
     * @param req
     * @return
     */
    List<WwdbVirtualAccountDto.SearchRes> selectVirtualAccounts(
        WwdbVirtualAccountDto.SearchReq req
    );

    /**
     * 가상계좌입금내역조회 (모바일)
     * @param req
     * @return
     */
    List<WwdbVirtualAccountDto.SearchDtlRes> selectVirtualAccountDepositItemizations(
        WwdbVirtualAccountDto.SearchReq req
    );

}
