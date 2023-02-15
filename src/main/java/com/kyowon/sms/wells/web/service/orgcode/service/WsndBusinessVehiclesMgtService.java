package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndBusinessVehiclesMgtConverter;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.FindRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchVehiclesRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesMgtDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndBusinessVehiclesMgtMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndBusinessVehiclesMgtService {
    private final WsndBusinessVehiclesMgtMapper mapper;
    private final WsndBusinessVehiclesMgtConverter converter;

    public FindRes getBusinessVehicle(String vhcMngtNo, String vhcMngtSn) {
        return mapper.selectBusinessVehicle(vhcMngtNo, vhcMngtSn).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );
    }

    @Transactional
    public int createBusinessVehicle(CreateReq dto) {
        WsndBusinessVehiclesMgtDvo dvo = converter.mapCreateReqToBusinessVehiclesMgtDvo(dto);
        /*return mapper.mergeBusinessVehicle(dvo);*/
        return 1;
    }

    @Transactional
    public int editBusinessVehicle(EditReq dto) {
        WsndBusinessVehiclesMgtDvo dvo = converter.mapEditReqToBusinessVehiclesMgtDvo(dto);
        /*return mapper.mergeBusinessVehicle(dvo);*/
        return 1;
    }

    public List<SearchVehiclesRes> getAllVehicles() {
        return mapper.selectAllVehicles();
    }
}
