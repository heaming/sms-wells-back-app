package com.kyowon.sms.wells.web.service.common.service;

import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dto.WsnyBsProcessStatusDto;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyBsProcessStatusMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnyBsProcessStatusService {

    private final WsnyBsProcessStatusMapper mapper;

    public WsnyBsProcessStatusDto.SearchRes getBsProcessStatus(
        WsnyBsProcessStatusDto.SearchReq dto
    ) {
        SFLEXContext context = SFLEXContextHolder.getContext();
        UserSessionDvo userSession = context.getUserSession();
        String hcdCd = mapper.selectBsProcessStatusHomeCardCode(dto);

        //엔지니어, 매니저가 아닌 경우 Empty Dto return
        if(!"HW100".equals(hcdCd) && !"HW300".equals(hcdCd) && !"HW400".equals(hcdCd)){
            return new WsnyBsProcessStatusDto.SearchRes("0", "0", "0", "0");
        }

        if("W6010".equals(userSession.getBaseRleCd())
            || "W6020".equals(userSession.getBaseRleCd())
                || "W4020".equals(userSession.getBaseRleCd())
                || "W3010".equals(userSession.getBaseRleCd())
                || "W3030".equals(userSession.getBaseRleCd())
                || "W3040".equals(userSession.getBaseRleCd())
                || "W3050".equals(userSession.getBaseRleCd())
        ){
            return mapper.selectBsProcessStatusForOg(dto);
        } else if("HW300".equals(hcdCd)){
            return mapper.selectBsProcessStatusForEngineer(dto);
        } else if("HW100".equals(hcdCd) || "HW400".equals(hcdCd)){
            return mapper.selectBsProcessStatusForManager(dto);
        } else {
            return new WsnyBsProcessStatusDto.SearchRes("0", "0", "0", "0");
        }
    }
}
