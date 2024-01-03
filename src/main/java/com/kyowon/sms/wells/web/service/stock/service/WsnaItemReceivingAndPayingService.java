package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemReceivingAndPayingMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0252M01 품목별 수불현황 서비스
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.05.17
 */
@Service
@RequiredArgsConstructor
public class WsnaItemReceivingAndPayingService {

    private final WsnaItemReceivingAndPayingMapper mapper;

    /**
     * 품목별 수불현황 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getReceiptsAndPaymentssForExcelDownload(SearchReq dto) {
        return mapper.selectReceiptsAndPaymentsPages(dto);
    }

    /**
     * 일자별 수불현황 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchDateRes> getDateReceivingAndPayingsForExcelDownload(SearchDateReq dto) {
        return mapper.selectDateReceivingAndPayings(dto);
    }
}
