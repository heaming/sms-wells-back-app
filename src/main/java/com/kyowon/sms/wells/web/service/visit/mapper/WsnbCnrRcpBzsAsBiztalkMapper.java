package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCnrRcpBzsAsBiztalkDvo;

@Mapper
public interface WsnbCnrRcpBzsAsBiztalkMapper {

    List<WsnbCnrRcpBzsAsBiztalkDvo> selectBiztalkTargets(String mexnoEncr);

    WsnbCnrRcpBzsAsBiztalkDvo selectBiztalkTarget(WsnbCnrRcpBzsAsBiztalkDvo dvo);

    int selectCountBiztalkItemization();

}
