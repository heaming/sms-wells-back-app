package com.kyowon.sms.wells.web.service.allocate.service;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsAccountByProductDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsAccountByProductDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsAccountByProductMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncBsAccountByProductService {

    private final WsncBsAccountByProductMapper mapper;

    /**
     * 조회
     *
     * @programId : K-W-SV-U-0230M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public PagingResult<SearchRes> getBsAccountByProduct(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectBsAccountByProduct(dto, pageInfo);
    }

    /**
    * 엑셀 다운로드
    *
    * @programId : K-W-SV-U-0230M01
    * @param dto : 조회파라메터
    * @return 조회결과
    */
    public List<SearchRes> getBsAccountByProduct(
        SearchReq dto
    ) {
        return mapper.selectBsAccountByProduct(dto);
    }
}
