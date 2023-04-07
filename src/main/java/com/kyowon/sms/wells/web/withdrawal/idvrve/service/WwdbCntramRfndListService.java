package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListAgrgReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListAgrgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbCntramRfndListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbCntramRfndListService {

    private final WwdbCntramRfndListMapper mapper;

    /**
     * 환불내역 목록
     * @param pageInfo 
     * @param SearchCntramRfndListReq
     * @return PagingResult<SearchCntramRfndListRes>
     */
    public PagingResult<SearchCntramRfndListRes> getCntramRfndListPages(
        SearchCntramRfndListReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectCntramRfndList(req, pageInfo);
    }

    /**
     * 환불내역 엑셀다운로드
     * @param List 
     * @param SearchCntramRfndListReq
     * @return List<SearchCntramRfndListRes>
     */
    public List<SearchCntramRfndListRes> getCntramRfndListExcelDownload(
        SearchCntramRfndListReq req
    ) {
        return mapper.selectCntramRfndList(req);
    }

    /**
     * 환불내역 집계
     * @param SearchCntramRfndListAgrgReq
     * @return SearchCntramRfndListAgrgRes
     */
    public SearchCntramRfndListAgrgRes getCntramRfndListAgrg(
        SearchCntramRfndListAgrgReq req
    ) {
        return mapper.selectCntramRfndListAgrg(req);
    }
}
