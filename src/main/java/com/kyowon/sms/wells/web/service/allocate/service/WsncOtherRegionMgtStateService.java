package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOtherRegionMgtStateDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncOtherRegionMgtStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncOtherRegionMgtStateService {

    private final WsncOtherRegionMgtStateMapper mapper;

    /**
     * 타지역단 관리현황 - 조회
     *
     * @programId : K-W-SV-U-0308M01
     * @param dto : { mgtYnm: 관리년월, mgtDept: 총괄단, rgnlGrp: 지역단, branch: 지점, adrZip: 우편번호 }
     * @return 조회결과
     */
    public PagingResult<WsncOtherRegionMgtStateDto.SearchRes> getOtherRegionMgtState(
        WsncOtherRegionMgtStateDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectOtherRegionMgtState(dto, pageInfo);
    }

    /**
     * 타지역단 관리현황 - 조회(엑셀다운로드)
     *
     * @programId : K-W-SV-U-0308M01
     * @param dto : { mgtYnm: 관리년월, mgtDept: 총괄단, rgnlGrp: 지역단, branch: 지점, adrZip: 우편번호 }
     * @return 엑셀 다운로드
     */
    public List<WsncOtherRegionMgtStateDto.SearchRes> getOtherRegionMgtStateExcelDownload(
        WsncOtherRegionMgtStateDto.SearchReq dto
    ) {
        return mapper.selectOtherRegionMgtState(dto);
    }
}
