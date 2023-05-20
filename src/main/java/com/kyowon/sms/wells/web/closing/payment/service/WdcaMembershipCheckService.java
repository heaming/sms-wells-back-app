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
    * @param deptCd 세션 부서 ID
    * @param pageInfo 페이징 처리 정보
    * @return
    */
    public PagingResult<SearchRes> getBeforePages(
        String deptCd, PageInfo pageInfo
    ) {
        return mapper.selectBeforePages(deptCd, pageInfo);
    }

    /**
    * 멤버십확정 체크리스트 - 체크리스트(전) - 엑셀다운로드
    * @param deptCd 세션 부서 ID
    * @return
    */
    public List<SearchRes> getBeforeForExcelDownload(
        String deptCd
    ) {
        return mapper.selectBeforePages(deptCd);
    }

    /**
    * 멤버십확정 체크리스트 - 체크리스트(후) - 조회
    * @param deptCd 세션 부서 ID
    * @param pageInfo 페이징 처리 정보
    * @return
    */
    public PagingResult<SearchAfterRes> getAfterPages(
        String deptCd, PageInfo pageInfo
    ) {
        return mapper.selectAfterPages(deptCd, pageInfo);
    }

    /**
    * 멤버십확정 체크리스트 - 체크리스트(후) - 엑셀다운로드
    * @param deptCd 세션 부서 ID
    * @return
    */
    public List<SearchAfterRes> getAfterForExcelDownload(
        String deptCd
    ) {
        return mapper.selectAfterPages(deptCd);
    }
}
