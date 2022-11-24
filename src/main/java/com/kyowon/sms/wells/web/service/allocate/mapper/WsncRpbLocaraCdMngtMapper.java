package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraCdMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;

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

    List<WsncRpbLocaraCdMngtDvo> getRpbLocaraCdMngtPages(
        WsncRpbLocaraCdMngtDto.SearchReq dto, PageInfo pageInfo
    );
}
