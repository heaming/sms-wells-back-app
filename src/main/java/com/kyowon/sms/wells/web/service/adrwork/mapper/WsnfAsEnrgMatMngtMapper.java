package com.kyowon.sms.wells.web.service.adrwork.mapper;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.*;
import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfAsEnrgMatMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnfAsEnrgMatMngtMapper {
    List<WsnfAsEnrgMatMngtDvo> selectAsEncourageMaterials(SearchReq dto, PageInfo pageInfo);
}
