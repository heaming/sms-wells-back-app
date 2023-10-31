package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.mapper.WsnzHistoryMapper;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbGoodsChangeAcceptingStateConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbGoodsChangeAcceptingStateDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbGoodsChangeAcceptingStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbGoodsChangeAcceptingStateService {

    private final WsnbGoodsChangeAcceptingStateMapper mapper;
    private final WsnzHistoryMapper historyMapper;
    private final WsnbGoodsChangeAcceptingStateConverter converter;

    public PagingResult<SearchRes> getGoodsChangeAcceptingStatePages(SearchReq req, PageInfo pageInfo) {
        return mapper.selectGoodsChangeAcceptingState(req, pageInfo);
    }

    public List<SearchRes> getGoodsChangeAcceptingStateExcelDownload(SearchReq req) {
        return mapper.selectGoodsChangeAcceptingState(req);
    }

    public int saveGoodsChangeAcceptingStateReject(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WsnbGoodsChangeAcceptingStateDvo dvo = converter.mapSaveReqToGoodsChangeAcceptingStateDvo(dto);
            dvo.setAprAkStatCd("04"); // 반려
            // 상품교체승인요청내역 반려 업데이트
            int result = mapper.updateSvpdPdChngAprAkIz(dvo);

            processCount += result;
        }

        return processCount;
    }

    @Transactional
    public int saveGoodsChangeAcceptingStateApprove(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WsnbGoodsChangeAcceptingStateDvo dvo = converter.mapSaveReqToGoodsChangeAcceptingStateDvo(dto);
            dvo.setAprAkStatCd("03"); // 승인

            BizAssert
                .isTrue(StringUtils.isEmpty(dvo.getOldSvBizDclsfCd()), "변경전 서비스업무세분류코드가 없습니다.");
            BizAssert
                .isTrue(StringUtils.isEmpty(dvo.getNewSvBizDclsfCd()), "변경후 서비스업무세분류코드가 없습니다.");

            // 고객서비스AS설치대상내역 변경
            mapper.updateCstSvasIstOjIz(dvo);
            // 고객서비스AS설치대상이력 생성
            mapper.insertCstSvasIstOjHist(dvo);
            // 고객서비스AS설치배정내역 변경
            mapper.updateCstSvAsIstAsnIz(dvo);
            // 고객서비스AS설치배정이력 생성
            historyMapper.insertCstSvasIstAsnHistByPk(dvo.getCstSvAsnNo());
            // 서비스작업상세변경내역 변경
            mapper.insertSvWkDchIz(dvo);
            // 상품교체승인요청내역 승인 업데이트
            int result = mapper.updateSvpdPdChngAprAkIz(dvo);
            processCount += result;
        }

        return processCount;
    }

}
