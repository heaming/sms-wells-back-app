package com.kyowon.sms.wells.web.bond.consultation.mapper;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WbncSameCustomerContractMapper {
    List<FindContractRes> selectContracts(
        String cstNo, String safeKey, String clctamPrtnrNo
    );

    List<FindDepositRes> selectContractDeposits(
        String bndCntrRefId
    );

    FindDepositDtlRes selectContractDeposit(
        String bndCntrRefId
    );
}
