package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindBreachOfPromiseRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindContractRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositDtlRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositInfoRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindSalesRes;

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
        String bndBizDvCd, String cntrNo, int cntrSn
    );

    List<FindDepositDtlRes> selectDeposits(
        String cntrNo, int cntrSn
    );
}
