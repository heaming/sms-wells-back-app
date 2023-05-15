package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncAssignPsicTfConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAssignPsicTfDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAssignPsicTfDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncAssignPsicTfMapper;
import com.kyowon.sms.wells.web.service.common.service.WsnzHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncAssignPsicTfService {

    private final WsncAssignPsicTfMapper mapper;

    private final WsnzHistoryService wsnzHistoryService;

    private final WsncAssignPsicTfConverter converter;

    @Transactional
    public int processAssignPsicTf(WsncAssignPsicTfDvo dto) throws Exception {
        List<WsncAssignPsicTfDvo> assignPsicTfList = mapper.selectAssignPsicTf(dto);

        //기준 : 1. 방문 일자 기준으로 책임지역담당(업무대행)으로 업데이트
        for(WsncAssignPsicTfDvo dvo : assignPsicTfList){
            if(valueOfEmptyStr(dvo.getCstSvAsnNo()).startsWith("2")){
                mapper.updateAssignPsicTfBs(dvo);       //cherro ::: AC261_BIZ_CD_A 컬럼 매핑이 안됨. OG_ID 매핑해야되는데...
                wsnzHistoryService.insertCstSvBfsvcAsnHistByPk(dvo.getCstSvAsnNo()); //Logging
            } else {
                mapper.updateAssignPsicTfAs(dvo);
            }
            //이관 로그 저장
            mapper.insertAssignPsicTf(dvo);
        }
        return 1;
    }

    @Transactional
    public int processAssignPsicTfByPk(WsncAssignPsicTfDto.SearchPkReq dto) throws Exception {
        return this.processAssignPsicTf(converter.mapPkReqToAssignPsicTfDvo(dto));
    }

    @Transactional
    public int processAssignPsicTf(WsncAssignPsicTfDto.SearchReq dto) throws Exception {
        WsncAssignPsicTfDvo dvo = converter.mapReqToAssignPsicTfDvo(dto);
        dvo.setCstSvAsnNo(""); // 고객서비스배정번호 없이 전체 대상
        return this.processAssignPsicTf(dvo);
    }

    /*
     * String.valueOf() - Null일 경우 Empty String return
     */
    public String valueOfEmptyStr(Object obj){
        if(obj == null){
            return "";
        }
        return String.valueOf(obj);
    }
}
