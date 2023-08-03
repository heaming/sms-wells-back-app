package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchAfterRes;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaMembershipCheckMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdcaMembershipCheckService {
    private final WdcaMembershipCheckMapper mapper;

    /**
    * 멤버십확정 체크리스트 - 체크리스트(전) - 조회
    * @param deptGubun 조직선택
    * @param pageInfo 페이징 처리 정보
    * @return
    */
    public PagingResult<SearchRes> getBeforePages(
        String deptGubun, PageInfo pageInfo
    ) {
        return mapper.selectBeforePages(deptGubun, pageInfo);
    }

    /**
    * 멤버십확정 체크리스트 - 체크리스트(전) - 엑셀다운로드
    * @param deptGubun 조직선택
    * @return
    */
    public List<SearchRes> getBeforeForExcelDownload(
        String deptGubun
    ) {
        return mapper.selectBeforePages(deptGubun);
    }

    /**
    * 멤버십확정 체크리스트 - 체크리스트(후) - 조회
    * @param deptGubun 조직선택
    * @param pageInfo 페이징 처리 정보
    * @return
    */
    public PagingResult<SearchAfterRes> getAfterPages(
        String deptGubun, PageInfo pageInfo
    ) {
        return mapper.selectAfterPages(deptGubun, pageInfo);
    }

    /**
    * 멤버십확정 체크리스트 - 체크리스트(후) - 엑셀다운로드
    * @param deptGubun 조직선택
    * @return
    */
    public List<SearchAfterRes> getAfterForExcelDownload(
        String deptGubun
    ) {
        return mapper.selectAfterPages(deptGubun);
    }
}
