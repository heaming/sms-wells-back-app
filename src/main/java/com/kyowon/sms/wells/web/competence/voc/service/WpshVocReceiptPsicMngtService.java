package com.kyowon.sms.wells.web.competence.voc.service;

import com.kyowon.sms.wells.web.competence.voc.converter.WpshVocReceiptPsicMngtConverter;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SaveReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptPsicMngtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshVocReceiptPsicMngtDvo;
import com.kyowon.sms.wells.web.competence.voc.mapper.WpshVocReceiptPsicMngtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpshVocReceiptPsicMngtService {

    private final WpshVocReceiptPsicMngtMapper mapper;
    private final WpshVocReceiptPsicMngtConverter converter;

    public PagingResult<SearchRes> getVocReceiptPsicMngtPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> pagingSearchRes =
            converter.dvoToSearchRes(mapper.selectVocReceiptPsicMngtPages(dto, pageInfo));
        pagingSearchRes.setPageInfo(pageInfo);
        return pagingSearchRes;
    }

    @Transactional
    public int saveVocReceiptPsic(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WpshVocReceiptPsicMngtDvo dvo = converter.mapSaveReq(dto);
            switch (dvo.getRowState()) {
                case CommConst.ROW_STATE_CREATED:
                    processCount += mapper.insertVocReceiptPsic(dvo);
                    break;
                case CommConst.ROW_STATE_UPDATED:
                    processCount += mapper.updateVocReceiptPsic(dvo);
                    break;
                case CommConst.ROW_STATE_DELETED:
                    processCount += mapper.deleteVocReceiptPsic(dvo);
                    break;
            }
        }
        return processCount;
    }
}
