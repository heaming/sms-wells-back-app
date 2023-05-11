package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindClctamRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindMembershipRes;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailInfoDto.FindStlmRes;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailInfoDvo;

@Mapper
public interface WctiContractDetailInfoMapper {
    WctiContractDetailInfoDvo selectContractDetail(FindReq dto);

    Optional<FindStlmRes> selectContractDetailStlm(FindReq dto);

    Optional<FindClctamRes> selectContractDetailClctam(FindReq dto);

    Optional<FindMembershipRes> selectContractDetailMembership(FindReq dto);

    String selectContractDetailRglrSpp(FindReq dto);
}
