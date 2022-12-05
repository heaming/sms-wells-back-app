package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0035M01 책임지역 지역코드 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.11.22
 */
@Mapper
public interface WsncRpbLocaraCdMngtMapper {

    PagingResult<SearchRes> getRpbLocaraCdMngtPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> getRpbLocaraCdMngtPages(
        SearchReq dto
    );
}
