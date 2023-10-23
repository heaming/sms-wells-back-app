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
    public int createDeliveryBasesNextMonth(CreateCrdovrReq dto) {
        WsnaBsCsmbDeliveryBaseDvo dvo = converter.mapCreateCrdovrReqToDeliveryBaseDto(dto);
        int processCount = 0;

        String fromYm = dvo.getCarriedOverFrom();
        String toYm = dvo.getCarriedOverTo();

        // 이월 대상 월 데이터 존재 여부
        int fromMonthBas = mapper.selectExistNowMonthDeliveryBase(fromYm);
        int fromMonthDtl = mapper.selectExistNowMonthDeliveryDtl(fromYm);

        // 적용 대상 월 데이터 존재 여부
        int toMonthBas = mapper.selectExistNowMonthDeliveryBase(toYm);
        int toMonthDtl = mapper.selectExistNowMonthDeliveryDtl(toYm);

        String fromYear = dvo.getCarriedOverFrom().substring(0, 4);
        String fromMonth = "";
        fromMonth = dvo.getCarriedOverFrom().substring(4);
        fromMonth = fromMonth.startsWith("0") ? " " + fromMonth.substring(1) : fromMonth;

        if (fromMonthBas == 0 && fromMonthDtl == 0) { // 이월 대상 월 데이터가 없으면
            throw new BizException("MSG_ALT_THM_DATA_NOT_EXST", new String[] {fromYear, fromMonth});
        }

        String toYear = dvo.getCarriedOverTo().substring(0, 4);
        String toMonth = "";
        toMonth = dvo.getCarriedOverTo().substring(4);
        toMonth = toMonth.startsWith("0") ? " " + toMonth.substring(1) : toMonth;

        if (toMonthBas > 0 && toMonthDtl > 0) { // 적용 대상 월 데이터가 있으면
            throw new BizException("MSG_TXT_THM_DTA_EXST", new String[] {toYear, toMonth});
        }

        int result1 = mapper.insertDeliveryBasesNowMonth(dvo);
        processCount += result1;

        int result2 = mapper.insertDeliveryBaseDtlsNowMonth(dvo);
        processCount += result2;

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
