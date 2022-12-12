package com.kyowon.sms.wells.web.service.orgcode.service;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndWorkNoticeDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.orgcode.converter.WsndWorkNoticeConverter;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndWorkNoticeDvo;
import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndWorkNoticeMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsndWorkNoticeService {
    private final WsndWorkNoticeConverter wsndWorkNoticeConverter;
    private final WsndWorkNoticeMapper wsndWorkNoticeMapper;

    public PagingResult<SearchRes> getWorkNoticePages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return wsndWorkNoticeMapper.selectWorkNotices(dto, pageInfo);
    }

    public List<SearchRes> getWorkNotices(SearchReq dto) {
        return wsndWorkNoticeMapper.selectWorkNotices(dto);
    }

    public FindRes getWorkNoticeDetail(FindReq dto) {
        return wsndWorkNoticeMapper.selectWorkNoticeDetail(dto).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );
    }

    public List<SearchProductRes> getProductsByProductGroup(String pdGrpCd) {
        return wsndWorkNoticeMapper.selectProductsByProductGroup(pdGrpCd);
    }

    @Transactional
    public int createWorkNotice(CreateReq dto) {
        WsndWorkNoticeDvo dvo = wsndWorkNoticeConverter.mapCreateReqToWorkNoticeDvo(dto);
        return wsndWorkNoticeMapper.insertWorkNotice(dvo);
    }

    @Transactional
    public int editWorkNotice(EditReq dto) {
        WsndWorkNoticeDvo dvo = wsndWorkNoticeConverter.mapEditReqToWorkNoticeDvo(dto);
        return wsndWorkNoticeMapper.updateWorkNotice(dvo);
    }
}
