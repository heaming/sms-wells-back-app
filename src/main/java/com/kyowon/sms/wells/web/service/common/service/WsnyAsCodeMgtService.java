package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyAsCodeMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsCodeMgtDto;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyAsCodeMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * <pre>
 * W-SV-U-0016M01 AS 코드관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-11-08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyAsCodeMgtService {

    private final WsnyAsCodeMgtMapper mapper;
    private final WsnyAsCodeMgtConverter converter;

    public PagingResult<WsnyAsCodeMgtDto.SearchRes> selectAfterServiceCode(
        WsnyAsCodeMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            converter.mapAllSearchResToDvo(mapper.selectAfterServiceCode(dto, pageInfo)), pageInfo
        );
    }

}
