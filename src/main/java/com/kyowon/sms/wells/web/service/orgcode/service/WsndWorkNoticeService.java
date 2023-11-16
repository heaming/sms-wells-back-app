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

/**
 * @author Anonymous
 * @since 2023-12-31
 */
@Service
@RequiredArgsConstructor
public class WsndWorkNoticeService {
    private final WsndWorkNoticeConverter converter;
    private final WsndWorkNoticeMapper mapper;

    /**
     * 작업공지사항 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getWorkNoticePages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectWorkNotices(dto, pageInfo);
    }

    /**
     * 작업공지사항 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getWorkNotices(SearchReq dto) {
        return mapper.selectWorkNotices(dto);
    }

    /**
     * 작업공지사항 상세 조회
     * @param dto
     * @return
     */
    public FindRes getWorkNoticeDetail(FindReq dto) {
        return mapper.selectWorkNoticeDetail(dto).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );
    }

    /**
     * 상품코드 조회
     * @param pdGrpCd
     * @return
     */
    public List<SearchProductRes> getProductsByProductGroup(String pdGrpCd) {
        return mapper.selectProductsByProductGroup(pdGrpCd);
    }

    /**
     * 작업공지사항 등록
     * @param dto
     * @return
     */
    @Transactional
    public int createWorkNotice(CreateReq dto) {
        WsndWorkNoticeDvo dvo = converter.mapCreateReqToWorkNoticeDvo(dto);
        return mapper.insertWorkNotice(dvo);
    }

    /**
     * 작업공지사항 수정
     * @param dto
     * @return
     */
    @Transactional
    public int editWorkNotice(EditReq dto) {
        WsndWorkNoticeDvo dvo = converter.mapEditReqToWorkNoticeDvo(dto);
        return mapper.updateWorkNotice(dvo);
    }
}
