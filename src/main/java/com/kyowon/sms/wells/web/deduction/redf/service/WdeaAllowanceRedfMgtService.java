package com.kyowon.sms.wells.web.deduction.redf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchAwRedfRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfBizdRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchRedfRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceRedfMgtDto.SearchReq;
import com.kyowon.sms.wells.web.deduction.redf.mapper.WdeaAllowanceRedfMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdeaAllowanceRedfMgtService {

    private final WdeaAllowanceRedfMgtMapper mapper;

    /**
     * 수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록조회
     * @param dto
     * @return PagingResult<SearchAwRedfRes>
     */
    public PagingResult<SearchAwRedfRes> getAwRedfMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAwRedfMgts(dto, pageInfo);
    }

    /**
     * 수당(실적) 되물림 관리 - M, P, 직원판매, 홈마스터 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchAwRedfRes> getAwRedfMgtForExcelDownload(SearchReq dto) throws Exception {
        return mapper.selectAwRedfMgts(dto);
    }

    /**
     * 수당(실적) 되물림 관리 - B2B/총판 목록 조회
     * @param dto
     * @return PagingResult<SearchRedfRes>
     */
    public PagingResult<SearchRedfRes> getRedfMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRedfMgts(dto, pageInfo);
    }

    /**
     * 수당(실적) 되물림 관리 - B2B/총판 목록 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchRedfRes> getRedfMgtForExcelDownload(SearchReq dto) throws Exception {
        return mapper.selectRedfMgts(dto);
    }

    /**
     * 수당(실적) 되물림 관리 - 영업부 되물림 생성 목록 조회
     * @param dto
     * @return PagingResult<SearchRedfBizdRes>
     */
    public PagingResult<SearchRedfBizdRes> getRedfBizdMgt(SearchRedfBizdReq dto, PageInfo pageInfo) {
        return mapper.selectRedfBizdMgts(dto, pageInfo);
    }

}
