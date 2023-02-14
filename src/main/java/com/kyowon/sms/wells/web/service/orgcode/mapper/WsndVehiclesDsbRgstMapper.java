package com.kyowon.sms.wells.web.service.orgcode.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndVehiclesDsbRgstDto.FindRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndVehiclesDsbRgstDto.SearchVehiclesRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndVehiclesDsbRgstDvo;

@Mapper
public interface WsndVehiclesDsbRgstMapper {
    Optional<FindRes> selectBusinessVehicle(String vhcMngtNo, String vhcMngtSn);

    int mergeBusinessVehicle(WsndVehiclesDsbRgstDvo dvo);

    List<SearchVehiclesRes> selectAllVehicles();
}
