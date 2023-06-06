package com.kyowon.sms.wells.web.deduction.redf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchDlqRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaHomeMasterRedfCreateDto.SearchRes;
import com.kyowon.sms.wells.web.deduction.redf.mapper.WdeaHomeMasterRedfCreateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdeaHomeMasterRedfCreateService {

    private final WdeaHomeMasterRedfCreateMapper mapper;

    /**
     * 홈마스터 되물림 생성
     * @param dto
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getHomeMasterRedfPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectHomeMasters(dto, pageInfo);
    }

    /**
     * 홈마스터 되물림 생성 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchRes> getHomeMasterRedfForExcelDownload(SearchReq dto) throws Exception {
        return mapper.selectHomeMasters(dto);
    }

    /**
     * 홈마스터 되물림 생성 - 연체
     * @param dto
     * @return PagingResult<SearchRedfRes>
     */
    public PagingResult<SearchDlqRes> getHomeMasterDelinquentRedfPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectDelinquentHomeMasters(dto, pageInfo);
    }

    /**
     * 홈마스터 되물림 생성 - 연체 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchDlqRes> getHomeMasterDelinquentRedfForExcelDownload(SearchReq dto) throws Exception {
        return mapper.selectDelinquentHomeMasters(dto);
    }
}
