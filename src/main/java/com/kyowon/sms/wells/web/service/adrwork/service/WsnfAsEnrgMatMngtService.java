package com.kyowon.sms.wells.web.service.adrwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfAsEnrgMatMngtDvo;
import com.kyowon.sms.wells.web.service.adrwork.mapper.WsnfAsEnrgMatMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnfAsEnrgMatMngtService {
    private final WsnfAsEnrgMatMngtMapper mapper;

    /**
     * AS 유형별 필요자재관리
     * @param dto
     * @return 조회결과
     */
    public PagingResult<WsnfAsEnrgMatMngtDvo> getAsEncourageMaterials(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAsEncourageMaterials(dto, pageInfo);

    }

    /**
     * AS 유형별 필요자재관리 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<WsnfAsEnrgMatMngtDvo> getAsEncourageMaterialsForExcelDowload(SearchReq dto) {
        return mapper.selectAsEncourageMaterials(dto);
    }

}
