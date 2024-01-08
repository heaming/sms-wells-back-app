package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCapsulepkgChSppinfDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCapsulepkgChSppinfDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniCapsulepkgChSppinfMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-I-0020 Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsniCapsulepkgChSppinfInterfaceService {

    private final WsniCapsulepkgChSppinfMapper mapper;

    /**
     * Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트 조회
     * @param dto { cntrNo: 계약번호, cntrSn: 계약일련번호, pageIndex: 페이지인덱스, pageSize: 페이지크기}
     * @return 조회결과
     */
    public PagingResult<SearchRes> getCapsulepkgChSppinfs(SearchReq dto) {

        PageInfo pageinfo = new PageInfo();
        pageinfo.setPageIndex(dto.pageIndex());
        pageinfo.setPageSize(dto.pageSize());

        return this.mapper.selectCapsulepkgChSppinfs(dto, pageinfo);
    }

}
