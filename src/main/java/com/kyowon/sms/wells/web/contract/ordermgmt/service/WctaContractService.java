package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprBaseBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractMapper;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractService {

    private final WctaContractMapper mapper;
    private final WctaContractConverter converter;
    private final WctzHistoryService historyService;
    private final TemplateService templateService;
    private final EmailService emailService;

    public PagingResult<SearchCntrNoRes> getContractNumberInqrPages(
        SearchCntrNoReq dto, PageInfo pageInfo
    ) {
        return mapper.selectContractNumberInqrPages(dto, pageInfo);
    }

    public String sendContractEmail(SaveSendEmailsReq dto) throws Exception {
        String templateId = "TMP_CTA_WELLS_ELCN_GUD";
        String pdfUrl = ""; // TODO 계약서 pdf 생성 로직 추가

        return emailService.sendEmail(
            EmailSendReqDvo.builder()
                .title(templateService.getTemplateByTemplateId(templateId).getSendTemplateTitle())
                .content(
                    templateService.getTemplateContent(
                        templateId, Map.of(
                            "cnrtNm", dto.cntrNm(),
                            "pdfUrl", pdfUrl
                        )
                    )
                )
                .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dto.emadr())))
                .build()
        );
    }

    public List<SearchHomecareContractsRes> getHomecareContracts(List<SearchHomecareContractsReq> dtos) {
        return mapper.selectHomecareContracts(dtos);
    }

    @Transactional
    public int saveHomecareContracts(List<SaveHomecareContractsReq> dtos) {
        int processCount = 0;
        Iterator<SaveHomecareContractsReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveHomecareContractsReq dto = iterator.next();
            String histStrtDtm = DateUtil.getNowString();
            if (StringUtil.isNotEmpty(dto.duedt())) {
                mapper.updateHomecareContractsDuedt(dto);
            }
            if (StringUtil.isNotEmpty(dto.candt())) {
                mapper.updateHomecareContractsCandt(dto);
                historyService.createContractDetailStatChangeHistory(
                    WctzCntrDtlStatChangeHistDvo.builder()
                        .cntrNo(dto.cntrNo())
                        .cntrSn(dto.cntrSn())
                        .histStrtDtm(histStrtDtm)
                        .build()
                );
            }
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(dto.cntrNo())
                    .cntrSn(dto.cntrSn())
                    .histStrtDtm(histStrtDtm)
                    .build()
            );
        }
        return processCount;
    }

    public List<SearchRes> getApprovalAskDivides(String standardDt) {
        return mapper.selectApprovalAskDivides(standardDt);
    }

    public List<SearchConfirmAprPsicAksRes> getConfirmAprPsicAks(String cntrNo) {
        return mapper.selectConfirmAprPsicAks(cntrNo);
    }

    public List<SearchConfirmAprPsicPrchssRes> getConfirmAprPsicPrchss(String cntrNo) {
        return mapper.selectConfirmAprPsicPrchss(cntrNo);
    }

    public PagingResult<SearchConfirmApprovalBaseRes> getConfirmApprovalBasePages(
        SearchConfirmApprovalBaseReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectConfirmApprovalBases(dto, pageInfo);
    }

    public List<SearchConfirmApprovalBaseRes> getConfirmApprovalBasesExcelDownload(
        SearchConfirmApprovalBaseReq dto
    ) {
        return mapper.selectConfirmApprovalBases(dto);
    }

    @Transactional
    public int removeApprovalAskDivides(List<RemoveReq> dtos) {
        int processCount = 0;
        Iterator<RemoveReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            RemoveReq dto = iterator.next();
            WctaCntrAprAkDvCdDvo dvo = converter.mapRemoveReqToWctaCntrAprAkDvCdDvo(dto);
            int result = mapper.deleteApprovalAskDivides(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public int saveConfirmApprovalBases(List<SaveConfirmApprovalBaseReq> dtos) {
        int processCount = 0;
        Iterator<SaveConfirmApprovalBaseReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveConfirmApprovalBaseReq dto = iterator.next();
            WctaCntrAprBaseBasDvo dvo = converter.mapSaveReqpToWctaCntrAprBaseBasDvo(dto);
            dvo.setCheckType("A");
            int checkCount = mapper.selectCountConfirmApprovalBases(dvo);
            BizAssert.isTrue(checkCount <= 0, "MSG_ALT_DUPLICATE_ROW_EXISTS");
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> mapper.insertConfirmApprovalBases(dvo);
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateConfirmApprovalBases(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeConfirmApprovalBases(List<RemoveConfirmApprovalBaseReq> dtos) {
        int processCount = 0;
        Iterator<RemoveConfirmApprovalBaseReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            RemoveConfirmApprovalBaseReq dto = iterator.next();
            WctaCntrAprBaseBasDvo dvo = converter.mapRemoveReqpToWctaCntrAprBaseBasDvo(dto);
            dvo.setCheckType("U");
            int checkCount = mapper.selectCountConfirmApprovalBases(dvo);
            String[] param = {dvo.getCntrAprAkDvCdNm()};
            BizAssert.isTrue(checkCount > 0, "MSG_ALT_PIC_NO_DEL_OBJ", param);
            int result = mapper.deleteConfirmApprovalBases(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }
        return processCount;
    }
}
