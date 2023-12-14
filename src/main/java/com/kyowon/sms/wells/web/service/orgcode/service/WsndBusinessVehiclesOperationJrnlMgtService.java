package com.kyowon.sms.wells.web.service.orgcode.service;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesOperationJrnlMgtDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndBusinessVehiclesOperationJrnlMgtConverter;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesOperationJrnlMgtDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndBusinessVehiclesOperationJrnlMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndBusinessVehiclesOperationJrnlMgtService {
    private final WsndBusinessVehiclesOperationJrnlMgtMapper mapper;
    private final WsndBusinessVehiclesOperationJrnlMgtConverter converter;

    public List<SearchRes> getBusinessVehiclesOperationJrnl(
        SearchReq dto
    ) {
        return mapper.selectBusinessVehiclesOperationJrnl(dto);
    }

    public PagingResult<SearchRes> getBusinessVehiclesOperationJrnl(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectBusinessVehiclesOperationJrnl(dto, pageInfo);
    }

    /**
     * 주유량, 주유금액 수정
     * @param dvos
     * @return
     */
    @Transactional
    public int editBusinessVehiclesOperationJrnlMgt(List<WsndBusinessVehiclesOperationJrnlMgtDvo> dvos) {

        ValidAssert.notEmpty(dvos);

        int count = 0;

        for (WsndBusinessVehiclesOperationJrnlMgtDvo dvo : dvos) {
            WsndBusinessVehiclesOperationJrnlMgtDvo editDvo = converter.mapEditBusinessVehiclesOperationJrnlMgt(dvo);
            count += mapper.updateBusinessVehiclesOperationJrnl(editDvo);
        }

        return count;

    }
}
