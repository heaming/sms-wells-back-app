package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbCustomerBaseBulkChConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCustomerBaseBulkChDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCustomerBaseBulkChMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCustomerBaseBulkChService {
    private final WctbCustomerBaseBulkChMapper mapper;
    private final WctbCustomerBaseBulkChConverter converter;

    public List<WctbCustomerBaseBulkChDto.SearchCustomerRes> getBulkChangeObjects(
        WctbCustomerBaseBulkChDto.SearchReq searchReq
    ) {
        //고객기준 일괄변경 대상 조회(자동이체,설치자명,세금계산서발행여부)
        WctbCustomerBaseBulkChDvo paramDvo = converter.mapSearchReqToWctbCustomerBaseBulkChDvo(searchReq);

        List<WctbCustomerBaseBulkChDvo> resDvoList = mapper.selectBulkChangeObjects(paramDvo);

        List<WctbCustomerBaseBulkChDto.SearchCustomerRes> resDtoList = new ArrayList<>();

        if (resDvoList != null) {
            for (WctbCustomerBaseBulkChDvo dvoItem : resDvoList) {
                WctbCustomerBaseBulkChDto.SearchCustomerRes resDto = converter
                    .mapWctbCustomerBaseBulkChDvoToSearchCustomerRes(dvoItem);
                resDtoList.add(resDto);
            }
        }

        return resDtoList;
    }

    public List<WctbCustomerBaseBulkChDto.SearchPartnerRes> getPlannerChanges(
        WctbCustomerBaseBulkChDto.SearchReq searchReq
    ) {
        //고객기준 일괄변경 대상 조회(플래너변경)
        WctbCustomerBaseBulkChDvo paramDvo = converter.mapSearchReqToWctbCustomerBaseBulkChDvo(searchReq);

        List<WctbCustomerBaseBulkChDvo> resDvoList = mapper.selectPlannerChanges(paramDvo);

        List<WctbCustomerBaseBulkChDto.SearchPartnerRes> resDtoList = new ArrayList<>();

        if (resDvoList != null) {
            for (WctbCustomerBaseBulkChDvo dvoItem : resDvoList) {
                WctbCustomerBaseBulkChDto.SearchPartnerRes resDto = converter
                    .mapWctbCustomerBaseBulkChDvoToSearchPartnerRes(dvoItem);
                resDtoList.add(resDto);
            }
        }

        return resDtoList;
    }
}
