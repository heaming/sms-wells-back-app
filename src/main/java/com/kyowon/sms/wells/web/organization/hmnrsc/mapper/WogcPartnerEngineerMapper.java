package com.kyowon.sms.wells.web.organization.hmnrsc.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerReq;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Mapper
public interface WogcPartnerEngineerMapper {

    PagingResult<SearchEngineerRes> selectEngineerAttends(SearchEngineerReq dto, PageInfo pageInfo);

    List<SearchEngineerRes> selectEngineerAttends(SearchEngineerReq dto);
}
