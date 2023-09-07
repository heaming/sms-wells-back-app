package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageIzDtlMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0144M01 출고내역 상세 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-28
 */

@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageIzDtlService {

    private final WsnaOutOfStorageIzDtlMapper mapper;

    /**
     * 품목 조회
     * @return
     */
    public List<SearchPdRes> getProducts() {
        return this.mapper.selectProducts();
    }

    /**
     * 출고내역 상세 관리 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getOutOfStorageIzDtlsPaging(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectOutOfStorageIzDtlsPaging(dto, pageInfo);
    }

    /**
     * 출고내역 상세 관리 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getOutOfStorageIzDtlsExcelDownload(SearchReq dto) {
        return this.mapper.selectOutOfStorageIzDtlsPaging(dto);
    }
}
