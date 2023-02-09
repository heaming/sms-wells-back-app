package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbBusinessAsBiztalkDvo;

@Mapper
public interface WsnbBusinessAsBiztalkMapper {

    List<WsnbBusinessAsBiztalkDvo> selectBiztalkTargets(String mexnoEncr);

    WsnbBusinessAsBiztalkDvo selectBiztalkTarget(WsnbBusinessAsBiztalkDvo dvo);

    int selectCountBiztalkItemization();

}
