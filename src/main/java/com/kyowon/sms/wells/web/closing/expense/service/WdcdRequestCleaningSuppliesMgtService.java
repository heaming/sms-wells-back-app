package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdRequestCleaningSuppliesMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.CodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdRequestCleaningSuppliesDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdRequestCleaningSuppliesMgtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WdcdRequestCleaningSuppliesMgtService {

    private final WdcdRequestCleaningSuppliesMgtMapper mapper;
    private final WdcdRequestCleaningSuppliesMgtConverter converter;
    private final AttachFileService attachFileService;
    private final String groupId = "ATG_DCD_CLING_COST_MGT";

    public List<CodeRes> getBuilDingCd() {
        return mapper.selectBuilDingCd();
    }

    @Transactional
    public int saveRequestCleaningSupplies(SaveReq req) throws Exception {

        int count = 0;

        WdcdRequestCleaningSuppliesDvo dvo = converter.mapSaveReqToWdcdRequestCleaningSuppliesDvo(req);

        if (StringUtil.isEmpty(req.clingCostAdjRcpNo())) {

            String clingCostAdjRcpNo = mapper.selectClingCostAdjRcpNo(req);
            dvo.setClingCostAdjRcpNo(clingCostAdjRcpNo);
            String clingCostSignApnFileId = groupId + clingCostAdjRcpNo;
            dvo.setClingCostSignApnFileId(clingCostSignApnFileId);
            dvo.setDtaDlYn("N");

            if (Objects.nonNull(dvo.getAttachFiles())) {
                count += mapper.insertRequestCleaningSupplies(dvo);
                attachFileService.saveAttachFiles(groupId, clingCostSignApnFileId, dvo.getAttachFiles());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
        } else {

            if (Objects.nonNull(dvo.getAttachFiles())) {
                attachFileService.saveAttachFiles(groupId, dvo.getClingCostAdjRcpNo(), dvo.getAttachFiles());
                count += mapper.insertRequestCleaningSupplies(dvo);
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
        }

        return count;
    }

    public FindRes getRequestCleaningSupplies(FindReq req) {
        return mapper.selectRequestCleaningSupplies(req);
    }
}
