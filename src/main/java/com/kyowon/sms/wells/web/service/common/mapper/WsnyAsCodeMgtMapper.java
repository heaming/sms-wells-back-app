package com.kyowon.sms.wells.web.service.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsCodeMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * W-SV-U-0016M01 AS 코드관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-11-08
 */
@Mapper
public interface WsnyAsCodeMgtMapper {

    PagingResult<WsnyAsCodeMgtDvo> selectAsCodes(SearchReq dto, PageInfo pageInfo);

    String selectPdCdValid(String pdCd);

    int saveAsCode(WsnyAsCodeMgtDvo dvo);

}
