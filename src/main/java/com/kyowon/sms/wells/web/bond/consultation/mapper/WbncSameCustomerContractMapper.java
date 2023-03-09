package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindBreachOfPromiseRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindContractRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositDtlRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindSalesRes;

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

    FindBreachOfPromiseRes selectBreachOfPromise(
        String bndCntrRefId
    );

    FindSalesRes selectContractSales(
        String bndCntrRefId
    );
}
