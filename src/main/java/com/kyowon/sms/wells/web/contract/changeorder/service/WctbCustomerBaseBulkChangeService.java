package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.ArrayList;
import java.util.List;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbCustomerBaseBulkChangeConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCustomerBaseBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChangeDvo;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCustomerBaseBulkChangeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCustomerBaseBulkChangeService {
    private final WctbCustomerBaseBulkChangeMapper mapper;
    private final WctbCustomerBaseBulkChangeConverter converter;

    public List<WctbCustomerBaseBulkChangeDto.SearchCustomerRes> getBulkChangeObjects(
        WctbCustomerBaseBulkChangeDto.SearchReq searchReq
    ) {
        //고객기준 일괄변경 대상 조회(자동이체,설치자명,세금계산서발행여부)
        WctbCustomerBaseBulkChangeDvo paramDvo = converter.mapSearchReqToWctbCustomerBaseBulkChangeDvo(searchReq);

        List<WctbCustomerBaseBulkChangeDvo> resDvos = mapper.selectBulkChangeObjects(paramDvo);

        List<WctbCustomerBaseBulkChangeDto.SearchCustomerRes> resDtos = new ArrayList<>();

        for (WctbCustomerBaseBulkChangeDvo dvoItem : resDvos) {
            WctbCustomerBaseBulkChangeDto.SearchCustomerRes resDto = converter
                .mapWctbCustomerBaseBulkChangeDvoToSearchCustomerRes(dvoItem);
            resDtos.add(resDto);
        }

        return resDtos;
    }

    public List<WctbCustomerBaseBulkChangeDto.SearchPartnerRes> getPlannerChanges(
        WctbCustomerBaseBulkChangeDto.SearchReq searchReq
    ) {
        //고객기준 일괄변경 대상 조회(플래너변경)
        WctbCustomerBaseBulkChangeDvo paramDvo = converter.mapSearchReqToWctbCustomerBaseBulkChangeDvo(searchReq);

        List<WctbCustomerBaseBulkChangeDvo> resDvos = mapper.selectPlannerChanges(paramDvo);

        List<WctbCustomerBaseBulkChangeDto.SearchPartnerRes> resDtos = new ArrayList<>();

        for (WctbCustomerBaseBulkChangeDvo dvoItem : resDvos) {
            WctbCustomerBaseBulkChangeDto.SearchPartnerRes resDto = converter
                .mapWctbCustomerBaseBulkChangeDvoToSearchPartnerRes(dvoItem);
            resDtos.add(resDto);
        }

        return resDtos;
    }
}
