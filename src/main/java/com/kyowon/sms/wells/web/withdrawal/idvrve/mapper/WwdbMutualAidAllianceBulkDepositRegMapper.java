package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalDepositCprDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbMutualAidAllianceBulkDepositDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbMutualAidAllianceBulkDepositRegDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbMutualAidAllianceBulkDepositRegMapper {

    PagingResult<SearchRes> selectMutualAidAllianceBulkDepositRegs(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectMutualAidAllianceBulkDepositRegs(SearchReq dto);

    SearchSumRes selectMutualAidAllianceBulkDepositRegsSum(SearchSumReq dto);

    int insertMutualAidAllianceBulkDepositReg(WwdbMutualAidAllianceBulkDepositRegDvo dvo);

    int updateIntegrationDeposit(WwdbMutualAidAllianceBulkDepositDvo dvo);

    ZwdzWithdrawalReceiveAskDvo selectIntegrationDeposit(ZwdzWithdrawalDepositCprDvo dvo);

    WwdbMutualAidAllianceBulkDepositRegDto.SearchIntegrationDepositRes selectIntegrationDepositInfo(String itgDpNo);
}
