package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdCleanerReqeustMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.CodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.SaveReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdCleanerReqeustDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdCleanerReqeustMgtMapper;
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
public class WdcdCleanerReqeustMgtService {

    private final WdcdCleanerReqeustMgtMapper mapper;
    private final WdcdCleanerReqeustMgtConverter converter;
    private final AttachFileService attachFileService;
    private final String groupId = "ATG_DCD_CLING_COST";

    public List<CodeRes> getBuilDingCd() {
        return mapper.selectBuilDingCd();
    }

    @Transactional
    public int saveCleanerReqeust(SaveReq req) throws Exception {

        int count = 0;

        WdcdCleanerReqeustDvo dvo = converter.mapSaveReqToWdcdCleanerReqeustDvo(req);

        if (StringUtil.isEmpty(req.clinrRgno())) {

            String clinrRgno = mapper.selectClinrRgno(req);
            dvo.setClinrRgno(clinrRgno);
            String fileId = groupId + clinrRgno;
            dvo.setDtaDlYn("N");
            dvo.setIdfApnFileId(fileId);
            dvo.setBnkbApnFileId(fileId);
            dvo.setCntrwApnFileId(fileId);
            dvo.setCntrLroreApnFileId(fileId);

            count += mapper.insertCleanerReqeust(dvo);
            if (Objects.nonNull(dvo.getAttachFiles1())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles1());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
            if (Objects.nonNull(dvo.getAttachFiles2())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles2());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
            if (Objects.nonNull(dvo.getAttachFiles3())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles3());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
            if (Objects.nonNull(dvo.getAttachFiles4())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles4());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
        } else {

            String clinrRgno = dvo.getClinrRgno();
            String fileId = groupId + clinrRgno;

            count += mapper.updateCleanerReqeust(dvo);
            if (Objects.nonNull(dvo.getAttachFiles1())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles1());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
            if (Objects.nonNull(dvo.getAttachFiles2())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles2());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
            if (Objects.nonNull(dvo.getAttachFiles3())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles3());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
            if (Objects.nonNull(dvo.getAttachFiles4())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles4());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
            }
        }

        return count;
    }

    public FindRes getCleanerReqeust(String clinrRgno) {
        return mapper.selectCleanerReqeust(clinrRgno);
    }
}
