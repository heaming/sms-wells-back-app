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
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0295M01 BS소모품 배부기준 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-29
 */

@Service
@RequiredArgsConstructor
public class WsnaBsCsmbDeliveryBaseService {

    private final WsnaBsCsmbDeliveryBaseMapper mapper;
    private final WsnaBsCsmbDeliveryBaseConverter converter;

    /**
     * 품목 조회
     * @return
     */
    public List<SearchItemsRes> getAllItemInformation() {
        return mapper.selectAllItemInformation();
    }

    /**
     * BS소모품 배부기준 관리 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getDeliveryBasesPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectDeliveryBases(dto, pageInfo);
    }

    /**
     * BS소모품 배부기준 관리 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getDeliveryBases(SearchReq dto) {
        return mapper.selectDeliveryBases(dto);
    }

    /**
     * BS소모품 배부기준 이월
     * @param dto
     * @return
     */
    @Transactional
    public int createDeliveryBasesNextMonth(CreateCrdovrReq dto) {
        int processCount = 0;

        String fromYm = dto.carriedOverFrom();
        String toYm = dto.carriedOverTo();

        // 이월 대상 월 데이터 존재 여부
        int fromMonthBas = mapper.selectExistNowMonthDeliveryBase(fromYm);
        int fromMonthDtl = mapper.selectExistNowMonthDeliveryDtl(fromYm);

        // 이월 대상 월 데이터가 없으면
        if (fromMonthBas == 0 && fromMonthDtl == 0) {
            String fromYear = fromYm.substring(0, 4);
            String fromMonth = fromYm.substring(4);
            fromMonth = fromMonth.startsWith("0") ? " " + fromMonth.substring(1) : fromMonth;

            // {0}년 {1}월 배부기준이 없습니다.
            BizAssert.isFalse(true, "MSG_ALT_THM_DATA_NOT_EXST", new String[] {fromYear, fromMonth});
        }

        // 적용 대상 월 데이터 존재 여부
        int toMonthBas = mapper.selectExistNowMonthDeliveryBase(toYm);
        int toMonthDtl = mapper.selectExistNowMonthDeliveryDtl(toYm);

        // 적용 대상 월 데이터가 있으면
        if (toMonthBas > 0 && toMonthDtl > 0) {
            String toYear = toYm.substring(0, 4);
            String toMonth = toYm.substring(4);
            toMonth = toMonth.startsWith("0") ? " " + toMonth.substring(1) : toMonth;

            // {0}년 {1}월 배부기준은 이미 등록되어 있습니다.
            BizAssert.isFalse(true, "MSG_TXT_THM_DTA_EXST", new String[] {toYear, toMonth});
        }

        // BS소모품기준기본 이월
        processCount += mapper.insertDeliveryBasesNowMonth(fromYm, toYm);

        // BS소모품기준상세 이월
        processCount += mapper.insertDeliveryBaseDtlsNowMonth(fromYm, toYm);

        return processCount;
    }

    /**
     * BS소모품 배부기준 등록
     * @param dtos
     * @return
     */
    @Transactional
    public int createDeliveryBase(List<CreateReq> dtos) {
        int processCount = 0;

        for (CreateReq dto : dtos) {
            WsnaBsCsmbDeliveryBaseDvo dvo = converter.mapCreateReqToDeliveryBaseDvo(dto);

            String dutYn = mapper.selectDeliveryBaseDuplicateYn(dvo);
            BizAssert.isNull(dutYn, "MSG_ALT_DUPLICATE_EXISTS");

            int result = mapper.insertDeliveryBase(dvo);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }

    /**
     * 배부기준 단건 조회
     * @param mngtYm
     * @param csmbPdCd
     * @return
     */
    public SearchRes getDeliveryBase(String mngtYm, String csmbPdCd) {
        ValidAssert.hasText(mngtYm);
        ValidAssert.hasText(csmbPdCd);

        return mapper.selectDeliveryBases(mngtYm, csmbPdCd).orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));
    }

    /**
     * BS소모품 배부기준 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int editDeliveryBase(List<EditReq> dtos) {
        int processCount = 0;

        for (EditReq dto : dtos) {
            WsnaBsCsmbDeliveryBaseDvo dvo = converter.mapEditReqToDeliveryBaseDvo(dto);
            int result = mapper.updateDeliveryBase(dvo);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }
}
