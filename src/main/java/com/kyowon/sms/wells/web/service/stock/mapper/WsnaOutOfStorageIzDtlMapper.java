package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageIzDtlDto.*;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaOutOfStorageIzDtlMapper {

    List<SearchPdRes> selectProducts();

    PagingResult<SearchRes> selectOutOfStorageIzDtlsPaging(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectOutOfStorageIzDtlsPaging(SearchReq dto);
}
