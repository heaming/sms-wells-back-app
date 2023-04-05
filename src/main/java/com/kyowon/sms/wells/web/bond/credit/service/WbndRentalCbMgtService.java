package com.kyowon.sms.wells.web.bond.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.bond.credit.converter.WbndRentalCbMgtConverter;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SaveTalkReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SearchTalkReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDto.SearchTalkRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndBondContactExcludeIzDvo;
import com.kyowon.sms.wells.web.bond.credit.mapper.WbndRentalCbMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbndRentalCbMgtService {

    private final WbndRentalCbMgtMapper mapper;
    private final WbndRentalCbMgtConverter converter;

    public PagingResult<SearchTalkRes> getNotificationTalkPages(
        SearchTalkReq dto, PageInfo pageInfo
    ) {
        return mapper.selectNotificationTalks(dto, pageInfo);
    }

    public List<SearchTalkRes> getNotificationTalksForExcelDownload(SearchTalkReq dto) {
        return mapper.selectNotificationTalks(dto);
    }

    @Transactional
    public int saveNotificationTalks(List<SaveTalkReq> dtos) {
        int processCount = 0;
        for (SaveTalkReq dto : dtos) {
            WbndBondContactExcludeIzDvo dvo = this.converter.mapSaveTalkReqToContactDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int result = this.mapper.insertNotificationTalk(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = this.mapper.updateNotificationTalk(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    @Transactional
    public int removeNotificationTalks(List<String> bndCntcExcdOjIds) {
        int processCount = 0;

        for (String bndCntcExcdOjId : bndCntcExcdOjIds) {
            int result = this.mapper.deleteNotificationTalk(bndCntcExcdOjId);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }
        return processCount;
    }
}
