package com.kyowon.sms.wells.web.service.adrwork.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfAsEnrgMatMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnfAsEnrgMatMngtMapper {
    PagingResult<WsnfAsEnrgMatMngtDvo> selectAsEncourageMaterials(SearchReq dto, PageInfo pageInfo);

    List<WsnfAsEnrgMatMngtDvo> selectAsEncourageMaterials(SearchReq dto);
}
