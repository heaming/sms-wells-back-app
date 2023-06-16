package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractRestipulationCntrRegDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchRes;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.BasInfoRes;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.ContractRes;

@Mapper
public interface WctaReStipulationMapper {

    PagingResult<SearchRes> selectReStipulationCustomers(
        SearchReq dto,
        PageInfo pageInfo
    );

    Integer selectReStipulationCustomerCounts(
        SearchReq dto
    );

    List<BasInfoRes> selectReStipulationStandardInfo(String cntrNo, Integer cntrSn);

    ContractRes selectRestipulationContractInfo(String cntrNo, Integer cntrSn);

    int insertRestipulationCntrReg(WctaContractRestipulationCntrRegDvo dvo);
}
