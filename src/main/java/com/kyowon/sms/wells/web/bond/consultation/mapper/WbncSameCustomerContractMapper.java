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
        String cntrNo, int cntrSn
    );

    FindDepositInfoRes selectContractDeposit(
        String bndBizDvCd, String cntrNo, int cntrSn
    );

    FindBreachOfPromiseRes selectBreachOfPromise(
        String bndBizDvCd, String cntrNo, int cntrSn
    );

    FindSalesRes selectContractSales(
        String bndCntrRefId
    );

    List<FindDepositDtlRes> selectDeposits(
        String cntrNo, int cntrSn
    );
}
