package com.kyowon.sms.wells.web.service.orgcode.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndBusinessVehiclesMgtConverter;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.*;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesMgtDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndBusinessVehiclesMgtMapper;
import com.sds.sflex.common.utils.ObjectUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndBusinessVehiclesMgtService {
    private final WsndBusinessVehiclesMgtMapper mapper;
    private final WsndBusinessVehiclesMgtConverter converter;

    public List<SearchRes> getBusinessVehicles(SearchReq dto) {
        return mapper.selectBusinessVehicles(dto);
    }

    public PagingResult<SearchRes> getBusinessVehiclesPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectBusinessVehicles(dto, pageInfo);
    }

    public FindRes getBusinessVehicle(String vhcMngtNo, String vhcMngtSn) {
        return mapper.selectBusinessVehicle(vhcMngtNo, vhcMngtSn).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );
    }

    @Transactional
    public int createBusinessVehicle(CreateReq dto) {
        Map<String, Object> validateReq = ObjectUtil.convertObjectToMap(dto);

        String vehicleDupYn = mapper.selectVehicleDupYn(validateReq);
        BizAssert.isNull(vehicleDupYn, "MSG_ALT_SMD_PSIC_VHC_DSB");

        WsndBusinessVehiclesMgtDvo dvo = converter.mapCreateReqToBusinessVehiclesMgtDvo(dto);
        return mapper.mergeBusinessVehicle(dvo);
    }

    @Transactional
    public int editBusinessVehicle(EditReq dto) {
        Map<String, Object> validateReq = ObjectUtil.convertObjectToMap(dto);

        String vehicleDupYn = mapper.selectVehicleDupYn(validateReq);
        BizAssert.isNull(vehicleDupYn, "MSG_ALT_SMD_PSIC_VHC_DSB");

        WsndBusinessVehiclesMgtDvo dvo = converter.mapEditReqToBusinessVehiclesMgtDvo(dto);
        return mapper.mergeBusinessVehicle(dvo);
    }

    public List<SearchVehiclesRes> getAllVehicles() {
        return mapper.selectAllVehicles();
    }
}
