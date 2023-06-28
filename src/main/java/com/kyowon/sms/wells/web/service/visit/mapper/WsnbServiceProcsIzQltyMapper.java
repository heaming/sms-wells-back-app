package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcsIzQltyDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcsIzQltyDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0099M01 서비스처리 내역(품질)
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.06.22
 */
@Mapper
public interface WsnbServiceProcsIzQltyMapper {

    PagingResult<WsnbServiceProcsIzQltyDvo> selectServiceProcsIzQltys(SearchReq dto, PageInfo pageInfo);

    List<WsnbServiceProcsIzQltyDvo> selectServiceProcsIzQltys(SearchReq dto);

}
