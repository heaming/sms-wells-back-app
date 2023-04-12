package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationPresentStateDto.SearchRefundApplicationPresentStateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationPresentStateDto.SearchRefundApplicationPresentStateRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundApplicationPresentStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbRefundApplicationPresentStateService {

    private final WwdbRefundApplicationPresentStateMapper mapper;

    /**
     * 환불 신청 현황 목록
     * @param pageInfo 
     * @param SearchRefundApplicationPresentStateReq
     * @return PagingResult<SearchRefundApplicationPresentStateRes>
     */
    public PagingResult<SearchRefundApplicationPresentStateRes> getRefundApplicationPresentStatePages(
        SearchRefundApplicationPresentStateReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundApplicationPresentState(req, pageInfo);
    }

    /**
     * 환불 신청 현황 목록 엑셀 다운로드
     * @param List 
     * @param SearchRefundApplicationPresentStateReq
     * @return List<SearchRefundApplicationPresentStateRes>
     */
    public List<SearchRefundApplicationPresentStateRes> getRefundApplicationPresentStateExcels(
        SearchRefundApplicationPresentStateReq req
    ) {
        return mapper.selectRefundApplicationPresentState(req);
    }
}
