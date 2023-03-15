package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbMutualAidAllianceBulkDepositRegDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbMutualAidAllianceBulkDepositRegMapper {

    PagingResult<SearchRes> selectMutualAidAllianceBulkDepositRegs(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectMutualAidAllianceBulkDepositRegs(SearchReq dto);

    SearchSumRes selectMutualAidAllianceBulkDepositRegsSum(SearchSumReq dto);

    int insertMutualAidAllianceBulkDepositReg(WwdbMutualAidAllianceBulkDepositRegDvo dvo);
}
