package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountDepositInterfaceDto.SearchReq;

@Mapper
public interface WwdbVirtualAccountDepositInterfaceMapper {

    /**
     * 가상계좌KICC입금원장내역 수정
     * @param dto
     * @return
     */
    int updateVirtualAccountKiccDepositLedgerItemization(SearchReq dto);

    /**
     * 가상계좌세틀뱅크 입금원장내역 수정
     * @param dto
     * @return
     */
    int updateVirtualAccountSettleBankDepositLedgerItemization(SearchReq dto);

    /**
     * 세틀뱅크 가상계좌 입금 완료 후 가상계좌 해지 처리
     * @param dto
     * @return
     */
    int updateVirtualAccountSettleBankDepositFinish(SearchReq dto);
}
