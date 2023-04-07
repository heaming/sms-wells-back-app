package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListAgrgReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListAgrgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbEtcAtamRfndListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbEtcAtamRfndListService {

    private final WwdbEtcAtamRfndListMapper mapper;

    /**
     * 기타 선수금 환불 목록
     * @param pageInfo 
     * @param SearchEtcAtamRfndListReq
     * @return PagingResult<SearchEtcAtamRfndListRes>
     */
    public PagingResult<SearchEtcAtamRfndListRes> getEtcAtamRfndListPages(
        SearchEtcAtamRfndListReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectEtcAtamRfndList(req, pageInfo);
    }

    /**
     * 기타 선수금 환불 목록 엑셀다운로드
     * @param List 
     * @param SearchCntramRfndListReq
     * @return List<SearchCntramRfndListRes>
     */
    public List<SearchEtcAtamRfndListRes> getEtcAtamRfndListExcelDownload(
        SearchEtcAtamRfndListReq req
    ) {
        return mapper.selectEtcAtamRfndList(req);
    }

    /**
     * 기타 선수금 환불 목록 집계
     * @param SearchCntramRfndListAgrgReq
     * @return SearchCntramRfndListAgrgRes
     */
    public SearchEtcAtamRfndListAgrgRes getEtcAtamRfndListAgrg(
        SearchEtcAtamRfndListAgrgReq req
    ) {
        return mapper.selectEtcAtamRfndListAgrg(req);
    }
}
