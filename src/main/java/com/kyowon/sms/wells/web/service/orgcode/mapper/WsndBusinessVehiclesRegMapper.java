package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesRegDto.FindRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesRegDto.SearchVehiclesRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesRegDvo;

@Mapper
public interface WsndBusinessVehiclesRegMapper {
    Optional<FindRes> selectBusinessVehicle(String vhcMngtNo, String vhcMngtSn);

    int mergeBusinessVehicle(WsndBusinessVehiclesRegDvo dvo);

    List<SearchVehiclesRes> selectAllVehicles();

}
