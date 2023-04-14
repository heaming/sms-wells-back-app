package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.*;

@Mapper
public interface WbncSameCustomerContractMapper {
    List<FindContractRes> selectContracts(
        String cstNo
    );

    List<FindDepositRes> selectContractDeposits(
        String bndBizDvCd, String cntrNo, int cntrSn
    );

    FindDepositDtlRes selectContractDeposit(
        String bndBizDvCd, String cntrNo, int cntrSn
    );

    FindBreachOfPromiseRes selectBreachOfPromise(
        String bndCntrRefId
    );

    FindSalesRes selectContractSales(
        String bndCntrRefId
    );
}
