package com.kyowon.sms.wells.web.closing.expense.service;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@RequiredArgsConstructor
public class WdcdCleanerReqeustMgtService {

    private final WdcdCleanerReqeustMgtMapper mapper;
    private final WdcdCleanerReqeustMgtConverter converter;
    private final AttachFileService attachFileService;
    private String groupId = "ATG_DCD_CLING_COST";

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


            int byteCount = String.valueOf(dvo.getAplcnsNm()).getBytes("UTF-8").length;
            BizAssert.isFalse(byteCount > 50, "MSG_ALT_MANY_CLEANERS_NAME");

            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles1())) {

                attachFileService.saveAttachFiles(groupId, fileId + "1", dvo.getAttachFiles1());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setIdfApnFileId(fileId + "1");
            }
            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles2())) {

                attachFileService.saveAttachFiles(groupId, fileId + "2", dvo.getAttachFiles2());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setBnkbApnFileId(fileId + "2");
            }
            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles3())) {

                attachFileService.saveAttachFiles(groupId, fileId + "3", dvo.getAttachFiles3());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setCntrwApnFileId(fileId + "3");
            }
            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles4())) {

                attachFileService.saveAttachFiles(groupId, fileId + "4", dvo.getAttachFiles4());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setCntrLroreApnFileId(fileId + "4");
            }

            count += mapper.insertCleanerReqeust(dvo);
        } else {

            String clinrRgno = dvo.getClinrRgno();
            String fileId = groupId + "_BAS" + clinrRgno;

//            Map<String, Object> fileIdList = mapper.selectFileId(clinrRgno);

            WdcdCleanerReqeustDvo saveDvo = mapper.selectCleanerReqeust(clinrRgno);
            FindRes res = converter.mapFindResToWdcdCleanerReqeustDvo(saveDvo);

            String clinrNmS = dvo.getClinrNm();
            boolean clinrNm = Pattern.matches("^[가-힣]*$", clinrNmS);
            boolean rrnoEncr = Pattern.matches("^[0-9]+$", dvo.getRrnoEncr());
            boolean exnoEncr = Pattern.matches("^[*]*$", dvo.getExnoEncr());
            boolean dtlAdr = Pattern.matches("^[*]*$", dvo.getDtlAdr());
            boolean acnoEncr = Pattern.matches("^[0-9]+$", dvo.getAcnoEncr());

            if (clinrNm) {
                dvo.setClinrNm(String.valueOf(res.clinrNm()));
            }
            if (rrnoEncr) {
                dvo.setRrnoEncr(String.valueOf(res.rrnoEncr()));
            }
            if (exnoEncr) {
                dvo.setExnoEncr(String.valueOf(res.exnoEncr()));
            }
            if (dtlAdr) {
                dvo.setDtlAdr(String.valueOf(res.dtlAdr()));
            }
            if (acnoEncr) {
                dvo.setAcnoEncr(String.valueOf(res.acnoEncr()));
            }

            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles1())) {

                attachFileService.saveAttachFiles(groupId, fileId + "1", dvo.getAttachFiles1());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setIdfApnFileId(fileId + "1");
            }

            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles2())) {

                attachFileService.saveAttachFiles(groupId, fileId + "2", dvo.getAttachFiles2());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setBnkbApnFileId(fileId + "2");
            }

            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles3())) {

                attachFileService.saveAttachFiles(groupId, fileId + "3", dvo.getAttachFiles3());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setCntrwApnFileId(fileId + "3");
            }


            if (CollectionUtils.isNotEmpty(dvo.getAttachFiles4())) {

                attachFileService.saveAttachFiles(groupId, fileId + "4", dvo.getAttachFiles4());
                //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
                dvo.setCntrLroreApnFileId(fileId + "4");
            }

            count += mapper.updateCleanerReqeust(dvo);
        }
        BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
        return count;
    }

    public FindRes getCleanerReqeust(String clinrRgno) {

        WdcdCleanerReqeustDvo dvo = mapper.selectCleanerReqeust(clinrRgno);
        FindRes findRes = converter.mapFindResToWdcdCleanerReqeustDvo(dvo);


        return findRes;
    }
}
