package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBsCsmbDeliveryBaseConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryBaseDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBsCsmbDeliveryBaseMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaBsCsmbDeliveryBaseService {
    private final WsnaBsCsmbDeliveryBaseMapper mapper;
    private final WsnaBsCsmbDeliveryBaseConverter converter;

    @Transactional
    public int createDeliveryBase(List<CreateReq> dtos) {
        int processCount = 0;

        for (CreateReq dto : dtos) {
            String dutYn = mapper.selectDeliveryBaseDuplicateYn(dto);
            BizAssert.isNull(dutYn, "MSG_ALT_DUPLICATE_EXISTS");

            WsnaBsCsmbDeliveryBaseDvo dvo = converter.mapCreateReqToDeliveryBaseDto(dto);
            int result = mapper.insertDeliveryBase(dvo);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }
}
