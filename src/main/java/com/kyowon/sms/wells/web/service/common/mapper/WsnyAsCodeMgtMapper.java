package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsCodeMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    List<WsnyAsCodeMgtDvo> selectAfterServiceCode(
        WsnyAsCodeMgtDto.SearchReq dto, PageInfo pageInfo
    );
}
