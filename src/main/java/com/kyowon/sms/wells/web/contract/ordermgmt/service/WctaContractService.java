package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalPackageGrpMngtsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaSpaySlamtInqrDvo;
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

    public List<SearchSpaySlamtInqrRes> getSpaySlamtInqr(SearchSpaySlamtInqrReq dto) {
        WctaSpaySlamtInqrDvo dvo = converter.mapSearchSpaySlamtInqrReqToWctaSpaySlamtInqrDvo(dto);

        //GUBN : W-WEB(홈케어）H-KSS(홈케어）, K-KSS(일반상품)
        if ("W".equals(dvo.getPdGubn())) {
            if (StringUtils.isAnyEmpty(dvo.getPdCd(), dvo.getDscGubn(), dvo.getVlDtm())) {
                return null;
            }
            /*if (StringUtil.isNull(dvo.getPdCd()) || StringUtil.isNull(dvo.getDscGubn())
                || StringUtil.isNull(dvo.getVlDtm())) {
                return null;
            }*/
        }

        //상품구분/상품코드／접수일/할인구분 필수 체크
        if (StringUtils.isAnyEmpty(dvo.getPdGubn(), dvo.getPdCd(), dvo.getVlDtm(), dvo.getDscGubn())) {
            return null;
        }
        /*if (StringUtil.isNull(dvo.getPdGubn()) || StringUtil.isNull(dvo.getPdCd()) || StringUtil.isNull(dvo.getVlDtm())
            || StringUtil.isNull(dvo.getDscGubn())) {
            return null;
        }*/

        //상품구분 (홈케어/일반상품) 필수 체크
        if (!("W".equals(dvo.getPdGubn()) || "K".equals(dvo.getPdGubn()) || "H".equals(dvo.getPdGubn()))) {
            return null;
        }

        //쿠폰은 웹에서만 등록 가능
        if ("C".equals(dvo.getDscGubn()) || "D".equals(dvo.getDscGubn())) {
            if (!dvo.getPdGubn().equals("W")) {
                return null;
            }
        }

        //쿠폰일 경우 ETC3-ETC4는　필수조건
        if ("W".equals(dvo.getPdGubn()) && ("C".equals(dvo.getDscGubn()) || "D".equals(dvo.getDscGubn()))) {
            if (StringUtil.isNull(dvo.getDscType())) {
                return null;
            }
        }

        //상품코드별 필수 체크
        if (Arrays.asList(new String[] {"3720", "3730", "3810", "3820", "3830", "3840"}).contains(dvo.getPdCd())) {
            if (StringUtil.isNull(dvo.getPdClsfId())) {
                return null;
            }
        }

        //홈케어　상품구분１，２필수체크위한　변수
        if (("W".equals(dvo.getPdGubn()) || "H".equals(dvo.getPdGubn())) && (!"3710".equals(dvo.getPdCd()))) {
            dvo.setPdClsfId("Y");
        }

        if ("3710".equals(dvo.getPdCd())) {
            dvo.setPdClsfId("");
        }

        //홈케어 용도구분 강제세팅
        if (Arrays.asList(new String[] {"3720", "3730", "3810", "3820", "3830", "3840", "3710"})
            .contains(dvo.getPdCd())) {
            if (StringUtil.isNull(dvo.getUseGubn())) {
                dvo.setUseGubn("0");
            }
        }

        return mapper.selectSpaySlamtInqr(dvo);
    }

    public List<SearchHomeCareMshChecksRes> getHomeCareMshChecks(String cntrCstNo) {
        return mapper.selectHomeCareMshChecks(cntrCstNo);
    }

    @Transactional
    public String saveRentalPackageGrpMngts(WctaRentalPackageGrpMngtsDvo dvo) {
        //사용자구분이 1:현업 아닐 경우 종료
        if (!"1".equals(dvo.getUserGubn())) {
            BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_USR_TYPE"}); //사용자유형을 확인하세요.
        }

        //작업구분이 1:등록, 4:삭제가 아닐 경우 종료
        if (!Arrays.asList(new String[] {"1", "4"}).contains(dvo.getInpGubn())) {
            BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_WK_CLS"}); //작업구분을 확인하세요.
        }
        if ("1".equals(dvo.getInpGubn())) {
            if ("22P".equals(dvo.getCntrRelTpCd())) {
                //계약번호, 계약상세번호가 존재하는지 여부 확인 비어었으면 종료
                BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_NO"}); //계약번호를 확인하세요.
                BizAssert.hasText(dvo.getBaseDtlCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_SN"}); //계약일련번호를 확인하세요.

                //계약관계상세내용(CNTR_REL_DTL_CN) 에 할인코드(14,15,16)이 존재하는지 여부 체크
                if (!Arrays.asList(new String[] {"14", "15", "16"}).contains(dvo.getCntrRelDtlCn())) {
                    return null;
                }

                List<SearchRentalPackageGrpMngtsRes> rentalPackageGrpMngts = mapper
                    .selectRentalPackageGrpMngts(dvo);
                int checkCount = rentalPackageGrpMngts.size();
                BizAssert.isTrue(checkCount <= 0, "MSG_ALT_DUPLICATE_ROW_EXISTS");
            }
            // String startYn = rentalPackageGrpMngts.get(0).startYn();
            int result = mapper.insertRentalPackageGrpMngts(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); //저장에 실패 하였습니다.
        } else if ("4".equals(dvo.getInpGubn())) {
            if ("22P".equals(dvo.getCntrRelTpCd())) {
                BizAssert.hasText(dvo.getBaseCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_NO"}); //계약번호를 확인하세요.
                BizAssert.hasText(dvo.getBaseDtlCntrNo(), "MSG_ALT_CHK_CONFIRM", new String[] {"MSG_TXT_CNTR_SN"}); //계약일련번호를 확인하세요.

                List<SearchRentalPackageGrpMngtsRes> rentalPackageGrpMngts = mapper
                    .selectRentalPackageGrpMngts(dvo);
                int checkCount = rentalPackageGrpMngts.size();
                BizAssert.isTrue(checkCount <= 0, "MSG_ALT_DUPLICATE_ROW_EXISTS");

                //작업구분이 4 && 상품시작여부가 'Y' 이면 삭제 불가
                String startYn = rentalPackageGrpMngts.get(0).startYn();
                if ("y".equals(startYn)) {
                    return null;
                }
            }
            int result = mapper.deleteRentalPackageGrpMngts(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR"); //삭제에 실패 하였습니다.
        }
        //등록된(입력된) 그룹번호 리턴
        return dvo.getCntrCstGrpId();
    }
}
