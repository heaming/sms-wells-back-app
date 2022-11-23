/*
 ****************************************************************************************************
 * 프로그램 개요
 ****************************************************************************************************
 1. 모듈 : SNC (배정관리)
 2. 프로그램 ID : W-SV-U-0036M01 AS 책임지역 우편번호 관리
 3. 작성자 : gs.piit130
 4. 작성일 : 2022.11.17
 ****************************************************************************************************
 * 프로그램 설명
 ****************************************************************************************************
 - 책임지역 우편번호 관리 (http://localhost:3000/#/service/wwsnc-responsibility-local-area-zip-mgt)
 ****************************************************************************************************
 */
package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRpbLocaraZipMngtConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRpbLocaraZipMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncRpbLocaraZipMngtService {

    private final WsncRpbLocaraZipMngtMapper mapper;

    private final WsncRpbLocaraZipMngtConverter converter;

    public PagingResult<WsncRpbLocaraZipMngtDto.SearchRes> getRpbLocaraZipMngtPages(
        WsncRpbLocaraZipMngtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return new PagingResult<>(
            this.converter.mapCreateResToListDvo(this.mapper.selectRpbLocaraZips(dto, pageInfo)), pageInfo
        );
    }

    public List<WsncRpbLocaraZipMngtDto.SearchRes> getRpbLocaraZipsForExcelDownload(
        WsncRpbLocaraZipMngtDto.SearchReq dto
    ) {
        return this.converter.mapCreateResToListDvo(this.mapper.selectRpbLocaraZips(dto));
    }

}
