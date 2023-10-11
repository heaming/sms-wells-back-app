package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemReceivingAndPayingConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReceiptsAndPaymentsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemReceivingAndPayingMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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

    private final WsnaItemReceivingAndPayingConverter converter;
    private final WsnaItemReceivingAndPayingMapper mapper;

    /**
     * 품목별 수불현황 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getReceiptsAndPaymentsPages(SearchReq dto, PageInfo pageInfo) {
        WsnaReceiptsAndPaymentsDvo dvo = this.converter.mapSearchReqToWsnaReceiptsAndPaymentsDvo(dto);

        return this.setPaging(dvo, pageInfo);

    }

    /**
     * 페이징 처리
     * @param dvo
     * @param pageInfo
     * @return
     */
    private PagingResult<SearchRes> setPaging(WsnaReceiptsAndPaymentsDvo dvo, PageInfo pageInfo) {

        boolean isNeed = pageInfo.isNeedTotalCount();
        if (isNeed) {
            long totalCount = this.mapper.selectReceiptsAndPaymentCount(dvo);
            pageInfo.setTotalCount(totalCount);
            pageInfo.setNeedTotalCount(Boolean.FALSE);
        }

        int pageIndex = pageInfo.getPageIndex();
        int pageSize = pageInfo.getPageSize();

        int startRow = (pageIndex - 1) * pageSize;
        dvo.setOffset(startRow);
        dvo.setSize(pageSize);

        List<SearchRes> pages = mapper.selectReceiptsAndPaymentsPages(dvo);
        return new PagingResult<>(pages, pageInfo);

    }

    /**
     * 품목별 수불현황 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getReceiptsAndPaymentssForExcelDownload(SearchReq dto) {
        return mapper.selectReceiptsAndPaymentsPages(dto);
    }

    /**
     * 일자별수불현황 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchDateRes> getDateReceivingAndPayings(
        SearchDateReq dto, PageInfo pageInfo
    ) {
        return mapper.selectDateReceivingAndPayings(dto, pageInfo);
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
