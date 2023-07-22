package com.kyowon.sms.wells.web.service.adrwork.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.adrwork.converter.WsnfAsEnrgMatMngtConverter;
import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.*;
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
     * 배정정보 조회
     * @param dto
     * @return 조회결과
     */
    public PagingResult<SearchRes> getAsEncourageMaterials(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> pagingResult = converter.mapWsnfAsEnrgMatMngtDvoToSearchRes(
            mapper.selectAsEncourageMaterials(dto, pageInfo)
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;

    }

}
