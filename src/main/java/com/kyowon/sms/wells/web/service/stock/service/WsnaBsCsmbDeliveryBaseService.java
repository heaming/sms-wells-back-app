package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBsCsmbDeliveryBaseConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryBaseDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBsCsmbDeliveryBaseMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaBsCsmbDeliveryBaseService {
    private final WsnaBsCsmbDeliveryBaseMapper mapper;
    private final WsnaBsCsmbDeliveryBaseConverter converter;

    public List<SearchRes> getDeliveryBases(SearchReq dto) {
        return mapper.selectDeliveryBases(dto);
    }

    public PagingResult<SearchRes> getDeliveryBasesPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectDeliveryBases(dto, pageInfo);
    }

    @Transactional
    public int createDeliveryBasesNextMonth() {
        int processCount = 0;

        // 당월 적용 대상 데이터 존재 여부 확인
        int nowMonthBas = mapper.selectExistNowMonthDeliveryBase();
        int nowMonthDtl = mapper.selectExistNowMonthDeliveryDtl();

        if (nowMonthBas > 0 && nowMonthDtl > 0) { // 당월 적용 대상 데이터가 있을경우 당월에만 insert
            int result1 = mapper.insertDeliveryBasesNowMonth();
            processCount += result1;

            int result2 = mapper.insertDeliveryBaseDtlsNowMonth();
            processCount += result2;
        } else { // 없을 경우 당월 이후 월에 계속하여 insert
            int result1 = mapper.insertDeliveryBasesNextMonth();
            processCount += result1;

            int result2 = mapper.insertDeliveryBaseDtlsNextMonth();
            processCount += result2;
        }

        return processCount;
    }

    public List<SearchItemsRes> getAllItemInformation() {
        return mapper.selectAllItemInformation();
    }

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

    public FindRes getDeliveryBase(String mngtYm, String csmbPdCd) {
        return mapper.selectDeliveryBase(mngtYm, csmbPdCd).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

    @Transactional
    public int editDeliveryBase(List<EditReq> dtos) {
        int processCount = 0;

        for (EditReq dto : dtos) {
            WsnaBsCsmbDeliveryBaseDvo dvo = converter.mapEditReqToDeliveryBaseDto(dto);
            int result = mapper.updateDeliveryBase(dvo);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }
}
