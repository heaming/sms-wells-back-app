package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdOperatingCostMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.EditReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchAmountRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchSummaryRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdOperatingCostDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdOperatingCostMgtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WdcdOperatingCostMgtService {

    private final WdcdOperatingCostMgtMapper mapper;
    private final WdcdOperatingCostMgtConverter converter;

    private final AttachFileService attachFileService;
    private final String groupId = "ATG_DCD_OPCS_WHTX_CFDC";

    public SearchAmountRes getAmount(SearchReq req) {
        //운영비 금액현황
        //운영비 적요 현황
        return mapper.selectAmount(req);
    }

    public SearchSummaryRes getSummary(SearchReq req) {
        //운영비 금액현황
        //운영비 적요 현황
        return mapper.selectSummary(req);
    }

    public int editFile(EditReq req) throws Exception {

        WdcdOperatingCostDvo dvo = converter.mapEditReqToWdcdOperatingCostDvo(req);
        String fileId = groupId + dvo.getOpcsCardId();

        if (CollectionUtils.isNotEmpty(dvo.getAttachOpcsWhtxCfdcApnFileId())) {

            attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachOpcsWhtxCfdcApnFileId());
            dvo.setOpcsWhtxCfdcApnFileId(fileId);
        }

        return mapper.updateFile(dvo);
    }

}
