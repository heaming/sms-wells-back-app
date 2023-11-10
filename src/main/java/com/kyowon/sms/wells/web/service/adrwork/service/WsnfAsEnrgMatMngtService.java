package com.kyowon.sms.wells.web.service.adrwork.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.adrwork.converter.WsnfAsEnrgMatMngtConverter;
import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.*;
import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfAsEnrgMatMngtDvo;
import com.kyowon.sms.wells.web.service.adrwork.mapper.WsnfAsEnrgMatMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnfAsEnrgMatMngtService {
    private final WsnfAsEnrgMatMngtMapper mapper;
    private final WsnfAsEnrgMatMngtConverter converter;

    /**
     * AS 유형별 필요자재관리
     * @param dto
     * @return 조회결과
     */
    public PagingResult<SearchRes> getAsEncourageMaterials(SearchReq dto, PageInfo pageInfo) {

        log.debug(dto.pdCd());
        log.debug(dto.pdGrpCd());
        log.debug(dto.classA());
        log.debug(dto.classB());
        log.debug(dto.classC());

        PagingResult<SearchRes> pagingResult = converter.mapWsnfAsEnrgMatMngtDvoToSearchRes(
            mapper.selectAsEncourageMaterials(dto, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;

    }

    /**
     * AS 유형별 필요자재관리 엑셀 다운로드
     * @param dto
     * @return 배정정보 조회 엑셀 다운로드
     */
    public List<WsnfAsEnrgMatMngtDvo> getAsEncourageMaterialsForExcelDowload(SearchReq dto) {
        return mapper.selectAsEncourageMaterials(dto);
    }

}
