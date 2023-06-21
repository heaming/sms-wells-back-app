package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdRequestCleaningSuppliesMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRequestCleaningSuppliesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdRequestCleaningSuppliesDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdRequestCleaningSuppliesMgtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdRequestCleaningSuppliesMgtService {

    private final WdcdRequestCleaningSuppliesMgtMapper mapper;
    private final WdcdRequestCleaningSuppliesMgtConverter converter;
    private final AttachFileService attachFileService;
    private final String groupId = "ATG_DCD_CLING_COST";

    public SearchRsbDvCdRes getRsbDvCd(CodeReq req) {
        return mapper.selectRsbDvCd(req);
    }

    public List<CodeRes> getBuilDingCd(CodeReq req) {
        return mapper.selectBuilDingCd(req);
    }

    @Transactional
    public int saveRequestCleaningSupplies(SaveReq req) throws Exception {

        int count = 0;

        if (StringUtil.isEmpty(req.clingCostAdjRcpNo())) {

            WdcdRequestCleaningSuppliesDvo dvo = converter.mapSaveReqToWdcdRequestCleaningSuppliesDvo(req);
            String clingCostAdjRcpNo = mapper.selectClingCostAdjRcpNo(req);
            dvo.setClingCostAdjRcpNo(clingCostAdjRcpNo);
            String clingCostSignApnFileId = groupId + "_ADJ" + clingCostAdjRcpNo;

            dvo.setDtaDlYn("N");

            BizAssert.isTrue(dvo.getAttachFiles().size() > 0, "MSG_ALT_APN_FILE_RGST");

            attachFileService.saveAttachFiles(groupId, clingCostSignApnFileId, dvo.getAttachFiles());
            dvo.setClingCostSrcpApnFileId(clingCostSignApnFileId);
            count += mapper.insertRequestCleaningSupplies(dvo);
            BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");

        } else {

            FindRes res = mapper.selectRequestCleaningSupplies(req.clingCostAdjRcpNo());

            boolean isMaskedClaimNm = req.claimNm().matches("^[가-힣]*$");
            boolean isMaskedCardPsrNm = req.cardPsrNm().matches("^[가-힣]*$");
            boolean isMaskedexnoEncr = req.exnoEncr().matches("^[*]*$");

            WdcdRequestCleaningSuppliesDvo saveDvo = converter.mapSaveReqToWdcdRequestCleaningSuppliesDvo(req);
            BizAssert.isTrue(saveDvo.getAttachFiles().size() > 0, "MSG_ALT_APN_FILE_RGST");

            if (isMaskedClaimNm) {
                saveDvo.setClaimNm(String.valueOf(res.claimNm()));
            }

            if (!isMaskedCardPsrNm) {
                saveDvo.setCardPsrNm(String.valueOf(res.cardPsrNm()));
            }

            if (!isMaskedexnoEncr) {
                saveDvo.setExnoEncr(String.valueOf(res.exnoEncr()));
            }

            attachFileService.saveAttachFiles(groupId, saveDvo.getClingCostAdjRcpNo(), saveDvo.getAttachFiles());
            count += mapper.updateRequestCleaningSupplies(saveDvo);
            BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");

        }

        return count;
    }

    public FindRes getRequestCleaningSupplies(String clingCostAdjRcpNo) {
        WdcdRequestCleaningSuppliesDvo saveDvo = mapper.selectRequestCleaningSuppliesDetail(clingCostAdjRcpNo);
        FindRes res = converter.mapWdcdRequestCleaningSuppliesDvoToFindRes(saveDvo);
        return res;
    }
}
