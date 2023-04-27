package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdCleanerReqeustMgtConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindCodeReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanerReqeustMgtDto.FindCodeRes;
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
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class WdcdCleanerReqeustMgtService {

    private final WdcdCleanerReqeustMgtMapper mapper;
    private final WdcdCleanerReqeustMgtConverter converter;
    private final AttachFileService attachFileService;
    private final String groupId = "ATG_DCD_CLING_COST";

    public List<FindCodeRes> getBuilDingCd(FindCodeReq req) {
        return mapper.selectBuilDingCd(req);
    }

    @Transactional
    public int saveCleanerReqeust(SaveReq req) throws Exception {

        int count = 0;

        WdcdCleanerReqeustDvo dvo = converter.mapSaveReqToWdcdCleanerReqeustDvo(req);

        if (StringUtil.isEmpty(req.clinrRgno())) {

            String clinrRgno = mapper.selectClinrRgno(req);
            dvo.setClinrRgno(clinrRgno);
            String fileId = groupId + "_BAS" + clinrRgno;
            dvo.setDtaDlYn("N");

            if (Objects.nonNull(dvo.getAttachFiles1())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles1());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setIdfApnFileId(fileId);
            }
            if (Objects.nonNull(dvo.getAttachFiles2())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles2());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setBnkbApnFileId(fileId);
            }
            if (Objects.nonNull(dvo.getAttachFiles3())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles3());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setCntrwApnFileId(fileId);
            }
            if (Objects.nonNull(dvo.getAttachFiles4())) {

                attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles4());
                BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setCntrLroreApnFileId(fileId);
            }

            count += mapper.insertCleanerReqeust(dvo);
        } else {

            String clinrRgno = dvo.getClinrRgno();
            String fileId = groupId + "_BAS" + clinrRgno;

            Map<String, Object> fileIdList = mapper.selectFileId(clinrRgno);

            String clinrNmS = dvo.getClinrNm();
            boolean clinrNm = Pattern.matches("^[*$%^]*$", clinrNmS);
            boolean rrnoEncr = Pattern.matches("^[*]*$", dvo.getRrnoEncr());
            boolean exnoEncr = Pattern.matches("^[*]*$", dvo.getExnoEncr());
            boolean dtlAdr = Pattern.matches("^[*]*$", dvo.getDtlAdr());

            if (clinrNm) {
                dvo.setClinrNm("");
            }
            if (rrnoEncr) {
                dvo.setRrnoEncr("");
            }
            if (exnoEncr) {
                dvo.setExnoEncr("");
            }
            if (dtlAdr) {
                dvo.setDtlAdr("");
            }

            if (Objects.nonNull(fileIdList.get("bnkbApnFileId"))) {
                if (Objects.nonNull(dvo.getAttachFiles1())) {

                    attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles1());
                    BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                    dvo.setIdfApnFileId(fileId);
                }
            }

            if (Objects.nonNull(fileIdList.get("idfApnFileId"))) {
                if (Objects.nonNull(dvo.getAttachFiles2())) {

                    attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles2());
                    BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                    dvo.setBnkbApnFileId(fileId);
                }
            }

            if (Objects.nonNull(fileIdList.get("cntrwApnFileId"))) {
                if (Objects.nonNull(dvo.getAttachFiles3())) {

                    attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles3());
                    BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                    dvo.setCntrwApnFileId(fileId);
                }
            }


            if (Objects.nonNull(fileIdList.get("cntrLroreApnFileId"))) {
                if (Objects.nonNull(dvo.getAttachFiles4())) {

                    attachFileService.saveAttachFiles(groupId, fileId, dvo.getAttachFiles4());
                    BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                    dvo.setCntrLroreApnFileId(fileId);
                }
            }

            count += mapper.updateCleanerReqeust(dvo);
        }

        return count;
    }

    public FindRes getCleanerReqeust(String clinrRgno) {

        WdcdCleanerReqeustDvo dvo = mapper.selectCleanerReqeust(clinrRgno);
        FindRes findRes = converter.mapFindResToWdcdCleanerReqeustDvo(dvo);
        return findRes;
    }
}
