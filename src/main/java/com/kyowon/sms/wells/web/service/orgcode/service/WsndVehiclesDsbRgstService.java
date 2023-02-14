package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndVehiclesDsbRgstConverter;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndVehiclesDsbRgstDto.CreateReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndVehiclesDsbRgstDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndVehiclesDsbRgstDto.FindRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndVehiclesDsbRgstDto.SearchVehiclesRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndVehiclesDsbRgstDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndVehiclesDsbRgstMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndVehiclesDsbRgstService {
    private final WsndVehiclesDsbRgstMapper mapper;
    private final WsndVehiclesDsbRgstConverter converter;

    public FindRes getBusinessVehicle(String vhcMngtNo, String vhcMngtSn) {
        return mapper.selectBusinessVehicle(vhcMngtNo, vhcMngtSn).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );
    }

    @Transactional
    public int createBusinessVehicle(CreateReq dto) {
        WsndVehiclesDsbRgstDvo dvo = converter.mapCreateReqToVehiclesDsbDvo(dto);
        /*return mapper.mergeBusinessVehicle(dvo);*/
        return 1;
    }

    @Transactional
    public int editBusinessVehicle(EditReq dto) {
        WsndVehiclesDsbRgstDvo dvo = converter.mapEditReqToVehiclesDsbDvo(dto);
        /*return mapper.mergeBusinessVehicle(dvo);*/
        return 1;
    }

    public List<SearchVehiclesRes> getAllVehicles() {
        return mapper.selectAllVehicles();
    }
}
