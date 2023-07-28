package com.kyowon.sms.wells.web.fee.standard.service;

import com.kyowon.sms.wells.web.fee.standard.converter.WfeyNewChannelBaseConverter;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SaveNewChannelBaseReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SearchNewChannelBaseReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SearchNewChannelBaseRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyNewChannelBaseDvo;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyNewChannelBaseMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * 상품별 신채널수수료 기준관리
 * </pre>
 *
 * @author mj
 * @since 2023.07.28
 */
@Service
@RequiredArgsConstructor
public class WfeyNewChannelBaseService {

    private final WfeyNewChannelBaseMapper mapper;
    private final WfeyNewChannelBaseConverter converter;

    /**
     * 조회
     * @param req
     * @return
     */
    public List<SearchNewChannelBaseRes> getNewChannelBaseMgtList(SearchNewChannelBaseReq req) {
        return mapper.selectNewChannelBaseList(req);
    }
    public PagingResult<SearchNewChannelBaseRes> getNewChannelBaseMgtList(SearchNewChannelBaseReq req, PageInfo pageInfo) {
        return mapper.selectNewChannelBaseList(req, pageInfo);
    }

    /**
     * 저장
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveNewChannelBaseMgt(List<SaveNewChannelBaseReq> req) throws Exception {
        int processCount = 0;
        for (SaveNewChannelBaseReq dto : req) {
            WfeyNewChannelBaseDvo dvo = converter.mapSaveReqWfeyNewChannelBaseDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int validCount = mapper.selectValidNewChannelBase(dvo);
                    BizAssert.isTrue(validCount == 0, "MSG_ALT_DUPLICATE_EXISTS");
                    processCount += mapper.insertNewChannelBase(dvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateNewChannelBase(dvo);
                }
                case CommConst.ROW_STATE_DELETED -> {
                    processCount += mapper.deleteNewChannelBase(dvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

}
