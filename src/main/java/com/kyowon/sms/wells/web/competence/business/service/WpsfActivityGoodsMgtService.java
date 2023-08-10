package com.kyowon.sms.wells.web.competence.business.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.common.web.deduction.common.dvo.ZdezPnpyamCreateDvo;
import com.kyowon.sms.common.web.deduction.common.service.ZdezPnpyamCreateService;
import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.business.converter.WpsfActivityGoodsMgtConverter;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfActivityGoodsMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.*;
import com.kyowon.sms.wells.web.competence.business.mapper.WpsfActivityGoodsMgtMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WpsfActivityGoodsMgtService {
    private final WpsfActivityGoodsMgtMapper mapper;
    private final WpsfActivityGoodsMgtConverter converter;
    private final ZdezPnpyamCreateService pnpyamCreateServe;
    // Dependency Injection
    private final EmailService emailService;
    private final ExcelReadService excelReadService;
    private final MessageResourceService messageResourceService;

    public WpsfActivityGoodsMgtDto.SearchRes getApplicationBase(WpsfActivityGoodsMgtDto.SearchReq dto) {
        return mapper.selectApplicationBase(dto);
    }

    /**
     * 활동물품신청기준기본 저장
     *
     * @param dto
     * @return processCount
     */
    @Transactional
    public int saveApplicationBase(WpsfActivityGoodsMgtDto.SaveReq dto) {
        int processCount = 0;
        WpsfActivityGoodsApplicationBaseDvo dvo = converter.mapSaveBaseReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
        processCount = mapper.mergeApplicationBase(dvo);
        return processCount;
    }

    public List<WpsfActivityGoodsMgtDto.SearchAcriGdsBasRes> getActivityGoodsBase(
        WpsfActivityGoodsMgtDto.SearchReq dto
    ) {
        return mapper.selectActivityGoodsBase(dto);
    }

    /**
     * 활동물품기본 등록,수정
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveActivityGoodsBase(List<WpsfActivityGoodsMgtDto.EditReq> dtos) {
        int processCount = 0;
        int oldActiGdsSn = 0;
        for (WpsfActivityGoodsMgtDto.EditReq dto : dtos) {
            int actiGdsSn = mapper.selectMaxActiGdsSn(dto);
            WpsfActivityGoodsBaseDvo dvo = converter.mapSaveActivityReq(dto);
            if (dvo.getActiGdsSn() != null) {
                oldActiGdsSn = dvo.getActiGdsSn();
            }
            dvo.setActiGdsSn(actiGdsSn);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            processCount = mapper.insertActivityGoodsBase(dvo);
            /*
            if (dvo.getActiGdsSn() != null) {
                WpsfActivityGoodsAplcIzDvo aDvo = new WpsfActivityGoodsAplcIzDvo();
                aDvo.setActiGdsCd(dvo.getActiGdsCd());
                aDvo.setOgTpCd(dvo.getOgTpCd());
                aDvo.setNewActiGdsSn(actiGdsSn);
                aDvo.setActiGdsSn(oldActiGdsSn);
                processCount = mapper.updateActiGdsAplcIzSn(aDvo);
            }
             */

        }

        return processCount;
    }

    public List<WpsfActivityGoodsMgtDto.SearchAcriGdsBasRes> getMaxActivityGoodsBase(
        WpsfActivityGoodsMgtDto.SearchReq dto
    ) {
        return mapper.selectMaxActivityGoodsBase(dto);
    }

    /**
     * 활동물품기본 삭제
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int removeGoodsBase(List<WpsfActivityGoodsMgtDto.RemoveReq> dtos) {
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.RemoveReq dto : dtos) {
            WpsfActivityGoodsBaseDvo dvo = converter.mapSaveRemoveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
            processCount = mapper.updateGoodsBase(dvo);
        }
        return processCount;
    }

    public PagingResult<WpsfActivityGoodsMgtDto.SearchStatRes> getActivityGoodsApplicationIzPages(
        WpsfActivityGoodsMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectActivityGoodsApplicationIzPages(dto, pageInfo);
    }

    public List<WpsfActivityGoodsMgtDto.SearchStatRes> getActivityGoodsApplicationIzExcelDownload(
        WpsfActivityGoodsMgtDto.SearchReq dto
    ) {
        return mapper.selectActivityGoodsApplicationIzPages(dto);
    }

    public List<WpsfActivityGoodsMgtDto.SearchBaseCodeRes> getGdsBaseCodes(WpsfActivityGoodsMgtDto.SearchReq dto) {
        return mapper.selectGdsBaseCodes(dto);
    }

    /**
     * 활동물품신청 등록,수정
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveApplication(List<WpsfActivityGoodsMgtDto.SaveApplicationReq> dtos) {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        String userBaseRleCd = userSession.getBaseRleCd();
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.SaveApplicationReq dto : dtos) {
            WpsfActivityGoodsAplcIzDvo dvo = converter.mapSaveApplicationReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            if (StringUtil.isEmpty(dto.actiGdsAplcId())) {
                String actiGdsAplcId = mapper.selectActiGdsAplcId();
                dvo.setActiGdsAplcId(actiGdsAplcId);
                processCount = mapper.insertActiGdsAplcIz(dvo);
                int actiGdsAplcSn = mapper.selectMaxActiGdsAplcSn();
                WpsfActiGdsAplcStatIzDvo sDvo = new WpsfActiGdsAplcStatIzDvo();
                sDvo.setActiGdsAplcId(dvo.getActiGdsAplcId());
                sDvo.setActiGdsAplcSn(actiGdsAplcSn);
                sDvo.setActiGdsAplcStatCd("01");
                sDvo.setDtaDlYn(DeDeductionConst.DELETE_N);
                processCount = mapper.insertActiGdsAplcStatIz(sDvo);
            } else {
                processCount = mapper.updateActiGdsAplcIz(dvo);
            }

        }

        return processCount;
    }

    /**
     * 활동물품신청 취소,반품등록
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int removeApplication(List<WpsfActivityGoodsMgtDto.RemovepplicationReq> dtos) throws Exception {

        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.RemovepplicationReq dto : dtos) {

            WpsfActivityGoodsMgtDto.SearchRes baseData = mapper.selectApplicationBase(dto);

            WpsfActiGdsAplcStatIzDvo dvo = converter.mapCencelApplicationReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);

            int cnt = mapper.selectCountactiGdsDdtnId(dto);

            BizAssert
                .isFalse("02".equals(dvo.getActiGdsAplcStatCd()) && cnt > 0, "공제신청 정보가 있습니다. 공제취소후 신청취소를 진행하여 주십시요.");

            if (dvo.getActiGdsAplcStatCd().equals(dto.oldActiGdsAplcStatCd())) {
                processCount = mapper.updateActiGdsAplcStatIz(dvo);
            } else {
                int actiGdsAplcSn = mapper.selectMaxActiGdsAplcSn();
                dvo.setActiGdsAplcSn(actiGdsAplcSn);
                processCount = mapper.insertActiGdsAplcStatIz(dvo);

            }
            if ("03".equals(dvo.getActiGdsAplcStatCd())) {
                // 공통함수 호출
                String title = "활동물품신청 반품등록";
                String content = dvo.getAplcRsonCn();
                String[] receiveEmails = baseData.rtngdShrnEmadr().split(",");
                for (String item : receiveEmails) {
                    String receiveEmail = item;
                    EmailSendReqDvo eDdvo = EmailSendReqDvo.builder()
                        .title(title)
                        .content(content)
                        .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(receiveEmail)))
                        .build();
                    String emailUid = emailService.sendEmail(eDdvo);
                    log.info("emailUid -------->", emailUid);
                }

            }
        }
        return processCount;
    }

    public List<WpsfActivityGoodsMgtDto.SearchCodeRes> getStddBases(WpsfActivityGoodsMgtDto.SearchReq dto) {
        return mapper.selectStddBases(dto);
    }

    public List<WpsfActivityGoodsMgtDto.SearchCodeRes> getStddDtls(WpsfActivityGoodsMgtDto.SearchReq dto) {
        return mapper.selectStddDtls(dto);
    }

    /**
     * 활동물품신청 수수료 공제 등록
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveDeductionItemization(List<WpsfActivityGoodsMgtDto.EditDeductionReq> dtos) {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        String userCompanyCode = userSession.getCompanyCode();
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.EditDeductionReq dto : dtos) {
            if (dto.patDdtnMcn() != dto.countMcn()) {
                String actiGdsDdtnId = mapper.selectMaxactiGdsDdtnId();
                WpsfActivityGoodsDeductionItemizationDvo dvo = converter.mapSaveDeductionItemizationReq(dto);
                dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
                dvo.setProcsYn("N");
                dvo.setActiGdsDdtnId(actiGdsDdtnId);
                dvo.setFeeDdtnDstAmt(dto.pnpyamOcAmt());
                processCount = mapper.insertActivityGoodsDeductionItemization(dvo);

                // 4. 가지급금 생성(공제/대체 서비스 호출)
                ZdezPnpyamCreateDvo saveReq = new ZdezPnpyamCreateDvo();
                saveReq.setPnpyOcDt(dvo.getFeeDdtnOcDt());//가지급발생일자
                saveReq.setPnpyamOcAmt(dto.pnpyamOcAmt()); // 가지급금발생금액
                saveReq.setCoCd(userCompanyCode); //회사코드
                saveReq.setOgTpCd(dto.ogTpCd());//조직유형코드
                saveReq.setPrtnrNo(dto.prtnrNo());//파트너번호
                saveReq.setPnpyamAtcCd("29");//가지급금항목코드
                saveReq.setPnpyOcRefDvCd("");//가지급발생참조구분코드
                saveReq.setPnpyOcTpCd("1");//가지급발생유형코드

                processCount = pnpyamCreateServe.savePnpyamCreate(saveReq);

                if (processCount == 1) {
                    dvo.setProcsYn("Y");
                    processCount = mapper.updateActivityGoodsDeductionItemization(dvo);
                }
            }

        }

        return processCount;

    }

    /**
     * 활동물품신청 공제취소
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int removeDeductionItemization(List<WpsfActivityGoodsMgtDto.RemoveDeductionReq> dtos) {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        String userCompanyCode = userSession.getCompanyCode();
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.RemoveDeductionReq dto : dtos) {
            WpsfActivityGoodsDeductionItemizationDvo dvo = converter.mapRemoveDeductionItemizationReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
            ZdezPnpyamCreateDvo req = new ZdezPnpyamCreateDvo();
            req.setOgTpCd(dto.ogTpCd());
            req.setPnpyOcDt(dto.maxFeeDdtnOcDt());
            req.setPnpyamAtcCd("29");//가지급금항목코드
            req.setPnpyOcRefDvCd("");//가지급발생참조구분코드
            req.setPnpyOcTpCd("1");//가지급발생유형코드
            processCount = pnpyamCreateServe.removePnpyamCreates(req);
            if (processCount == 1) {
                processCount = mapper.updateActivityGoodsDeductionDtlYn(dvo);
            }

        }

        return processCount;

    }

    /**
     * 활동물품기본 엑셀 업로드
     *
     * @param file
     * @return processCount
     */
    @Transactional
    public ExcelUploadDto.UploadRes saveExcelUpload(MultipartFile file) throws Exception {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        String ogTpCd = userSession.getOgTpCd();
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("prtnrNo", messageResourceService.getMessage("MSG_TXT_EPNO"));
        headerTitle.put("actiGdsCd", messageResourceService.getMessage("MSG_TXT_ITM"));
        headerTitle.put("aplcQty", messageResourceService.getMessage("MSG_TXT_QTY"));
        headerTitle.put("actiGdsStddCd", messageResourceService.getMessage("MSG_TXT_SZ"));
        // file
        List<WpsfActivityGoodsExcelDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WpsfActivityGoodsExcelDvo.class, true);
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();
        int row = 1;
        // 엑셀 데이터 검증 and 정규식
        for (WpsfActivityGoodsExcelDvo dvo : list) {

            // null check
            String[] nullColumnName = new String[4];
            if (StringUtil.isBlank(dvo.getPrtnrNo())) {
                nullColumnName[0] = "prtnrNo";
            }
            if (StringUtil.isBlank(dvo.getActiGdsCd())) {
                nullColumnName[1] = "actiGdsCd";
            }
            if (StringUtil.isBlank(dvo.getAplcQty())) {
                nullColumnName[2] = "aplcQty";
            }
            if (StringUtil.isBlank(dvo.getActiGdsStddCd())) {
                nullColumnName[3] = "actiGdsStddCd";
            }
            for (String column : nullColumnName) {
                if (StringUtil.isNotBlank(column)) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get(column));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
            }
            row++;
        }
        // insert
        if (excelUploadErrorDvos.size() == 0) {
            for (WpsfActivityGoodsExcelDvo exDvo : list) {
                int result = 0;
                //int result = this.mapper.insertMessageSendExcludeObject(insertData);
                WpsfActivityGoodsBaseDvo bDvo = new WpsfActivityGoodsBaseDvo();
                bDvo.setOgTpCd(ogTpCd);
                bDvo.setActiGdsCd(exDvo.getActiGdsCd());

                bDvo = mapper.selectGoodsBase(bDvo);
                WpsfActivityGoodsAplcIzDvo dvo = new WpsfActivityGoodsAplcIzDvo();
                dvo.setPrtnrNo(exDvo.getPrtnrNo());
                dvo.setOgTpCd(ogTpCd);
                dvo.setActiGdsCd(bDvo.getActiGdsCd());
                dvo.setActiGdsSn(bDvo.getActiGdsSn());
                dvo.setAplcQty(Long.parseLong(exDvo.getAplcQty()));
                dvo.setActiGdsStddCd(exDvo.getActiGdsStddCd());
                dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
                String actiGdsAplcId = mapper.selectActiGdsAplcId();
                dvo.setActiGdsAplcId(actiGdsAplcId);
                result = mapper.insertActiGdsAplcIz(dvo);
                int actiGdsAplcSn = mapper.selectMaxActiGdsAplcSn();
                WpsfActiGdsAplcStatIzDvo sDvo = new WpsfActiGdsAplcStatIzDvo();
                sDvo.setActiGdsAplcId(dvo.getActiGdsAplcId());
                sDvo.setActiGdsAplcSn(actiGdsAplcSn);
                sDvo.setActiGdsAplcStatCd("01");
                sDvo.setDtaDlYn(DeDeductionConst.DELETE_N);
                result = mapper.insertActiGdsAplcStatIz(sDvo);
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }
        }
        return ExcelUploadDto.UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();

    }

    public List<WpsfActivityGoodsMgtDto.SearchDeductionItemizationRes> getDeductionItemization(
        WpsfActivityGoodsMgtDto.SearchReq dto
    ) {
        return mapper.selectActivityGoodsDeductionItemization(dto);
    }

    /**
    *
     * 활동물품기본 사이즈 등록
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveActivityActivityGoodsSize(List<WpsfActivityGoodsMgtDto.EditSizeReq> dtos) {
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.EditSizeReq dto : dtos) {
            WpsfActivityGoodsSizeDvo dvo = converter.mapSaveSizeReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            if (dvo.getActiGdsStddDvId() == null) {
                String actiGdsStddDvId = mapper.selectMaxActiGdsStddDvId();
                dvo.setActiGdsStddDvId(actiGdsStddDvId);
                processCount = mapper.insertActivityGoodsSize(dvo);
            } else {
                processCount = mapper.updateActivityGoodsSize(dvo);
            }

        }
        return processCount;
    }

    public List<WpsfActivityGoodsMgtDto.SearchSizeRes> getActivityGoodsSize(WpsfActivityGoodsMgtDto.SearchReq dto) {
        return mapper.selectActivityGoodsSize(dto);
    }

    /**
    *
     * 활동물품기본 사이즈 삭제
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int removeActivityActivityGoodsSize(List<WpsfActivityGoodsMgtDto.RemoveSizenReq> dtos) {
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.RemoveSizenReq dto : dtos) {
            WpsfActivityGoodsSizeDvo dvo = converter.mapRemoveSizeReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
            processCount = mapper.updateActivityGoodsSizeDlayn(dvo);
            WpsfActivityGoodsSizeDetailDvo sDvo = new WpsfActivityGoodsSizeDetailDvo();
            sDvo.setActiGdsStddDvId(dvo.getActiGdsStddDvId());
            sDvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
            processCount = mapper.updateActivityGoodsSizeDetailDlayn(sDvo);

        }
        return processCount;
    }

    /**
    *
     * 활동물품기본 사이즈 상세 등록
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveActivityActivityGoodsSizeDetail(List<WpsfActivityGoodsMgtDto.EditSizeDetailReq> dtos) {
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.EditSizeDetailReq dto : dtos) {
            WpsfActivityGoodsSizeDetailDvo dvo = converter.mapSaveSizeDetailReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            int count = mapper.selectCountactiGdsStddCd(dvo);
            if (count == 0) {
                processCount = mapper.insertActivityGoodsSizeDetail(dvo);
            } else {
                processCount = mapper.updateActivityGoodsSizeDetail(dvo);
            }

        }
        return processCount;

    }

    /**
    *
     * 활동물품기본 사이즈 상세 삭제
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int removeActivityActivityGoodsSizeDetail(List<WpsfActivityGoodsMgtDto.RemoveSizenDetailReq> dtos) {
        int processCount = 0;
        for (WpsfActivityGoodsMgtDto.RemoveSizenDetailReq dto : dtos) {
            WpsfActivityGoodsSizeDetailDvo dvo = converter.mapRemoveSizeDetailReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
            processCount = mapper.updateActivityGoodsSizeDetailDlayn(dvo);
        }
        return processCount;
    }

    public List<WpsfActivityGoodsMgtDto.SearchSizeDetailRes> getActivityGoodsSizeDetail(
        WpsfActivityGoodsMgtDto.SearchReq dto
    ) {
        return mapper.selectActivityGoodsSizeDetail(dto);
    }
}
