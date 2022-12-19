/*
 ****************************************************************************************************
 * 프로그램 개요
 ****************************************************************************************************
 1. 모듈 : SNY (기준정보)
 2. 프로그램 ID : W-SV-U-0016M01 AS 코드관리
 3. 작성자 : gs.piit122
 4. 작성일 : 2022.11.08
 ****************************************************************************************************
 * 프로그램 설명
 ****************************************************************************************************
 - AS코드관리 (http://localhost:3000/#/service/wwsny-after-service-code-mgt)
 ****************************************************************************************************
 */
package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyAfterServiceCodeConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAfterServiceCodeDto;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyAfterServiceCodeMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyAfterServiceCodeService {

    private final WsnyAfterServiceCodeMapper mapper;
    private final WsnyAfterServiceCodeConverter converter;

    public PagingResult<WsnyAfterServiceCodeDto.SearchRes> selectAfterServiceCode(
        WsnyAfterServiceCodeDto.SearchReq dto, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            converter.mapAllSearchResToDvo(mapper.selectAfterServiceCode(dto, pageInfo)), pageInfo
        );
    }

}
