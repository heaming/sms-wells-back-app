package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.*;
import java.util.regex.Pattern;

import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerEngineerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveEngineerGradeReq;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerEngineerMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Service
@RequiredArgsConstructor
public class WogcPartnerEngineerService {
    private final WogcPartnerEngineerMapper mapper;
    private final WogcPartnerEngineerConverter wogcPartnerEngineerConverter;
    private final MessageResourceService messageService;
    private final ExcelReadService excelReadService;

    public PagingResult<SearchEngineerRes> getEngineerAttends(SearchEngineerReq dto, PageInfo pageInfo) {
        return mapper.selectEngineerAttends(dto, pageInfo);
    }

    public List<SearchEngineerRes> getEngineerAttendsForExcelDownload(SearchEngineerReq dto) {
        return mapper.selectEngineerAttends(dto);
    }

    /**
     * 엔지니어 출근 관리 목록 저장
     *
     * @param dtos
     * @return
     */
    @Transactional
    public int saveEngineerAttends(List<WogcPartnerEngineerDto.SaveEngineerAttendReq> dtos, String prtnrNo) {
        int processCount = 0;

        for (WogcPartnerEngineerDto.SaveEngineerAttendReq dto : dtos) {
            WogcPartnerEngineerDvo engineer = this.wogcPartnerEngineerConverter
                .mapSaveEngineerAttendReqToWogcPartnerEngineerDvo(dto);
            engineer.setPrtnrNo(prtnrNo);

            processCount += this.mapper.updateEngineer(engineer);
        }
        return processCount;
    }

    public PagingResult<WogcPartnerEngineerDto.SearchVacationRes> getVacations(
        WogcPartnerEngineerDto.SearchVacationReq dto, PageInfo pageInfo
    ) {
        return mapper.selectVacations(dto, pageInfo);
    }

    /**
     * 휴가상세 관리 수정, 저장
     *
     * @param dtos
     * @return
     */
    @Transactional
    public int saveVacations(List<WogcPartnerEngineerDto.SaveReq> dtos) {
        int processCount = 0;

        for (WogcPartnerEngineerDto.SaveReq dto : dtos) {
            WogcPartnerEngineerDvo vacations = this.wogcPartnerEngineerConverter
                .mapSaveReqToWogcPartnerEngineerDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += this.mapper.insertVacation(vacations);
                    processCount += this.mapper.updateEngineer(vacations);

                    //                    processCount = this.mapper.selectVacationsCnt(dto);
                    //                    if (processCount != 0) {
                    //                        throw new BizException("MSG_ALT_VCN_INFO_EX");
                    //                    } else {
                    //
                    //                    }

                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += this.mapper.updateVacation(vacations);
                    processCount += this.mapper.updateEngineer(vacations);
                    // processCount += this.mapper.updateEngineer(vacations);

                    //                    processCount = this.mapper.selectVacationsCnt(dto);
                    //                    if (processCount != 0) {
                    //                        throw new BizException("MSG_ALT_VCN_INFO_EX");
                    //                    } else {
                    //
                    //                    }

                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    /**
     * 휴가상세 관리 목록 삭제
     *
     * @param dtos
     * @return
     */
    @Transactional
    public int removeVacations(List<WogcPartnerEngineerDto.RemoveReq> dtos) {
        int processCount = 0;

        for (WogcPartnerEngineerDto.RemoveReq dto : dtos) {
            WogcPartnerEngineerDvo vacations = this.wogcPartnerEngineerConverter
                .mapRemoveReqToWogcPartnerEngineerDvo(dto);

            processCount += this.mapper.deleteVacation(vacations);

        }

        return processCount;
    }

    public PagingResult<FindJoeManagementRes> getJoeManagementPages(
        FindJoeManagementReq dto, PageInfo pageInfo
    ) {

        PagingResult<WogcPartnerEngineerDvo> dvos = mapper.selectJoeManagements(dto, pageInfo);
        PageInfo newPage = dvos.getPageInfo();
        PagingResult<FindJoeManagementRes> results = null;

        if (CollectionUtils.isNotEmpty(dvos)) {
            dvos.forEach(
                dvo -> {
                    dvo.setCralLocaraTno(StringUtils.isNotEmpty(dvo.getCralLocaraTno()) ? dvo.getCralLocaraTno() : "");
                    dvo.setMexnoEncr(StringUtils.isNotEmpty(dvo.getMexnoEncr()) ? dvo.getMexnoEncr() : "");
                    dvo.setCralIdvTno(StringUtils.isNotEmpty(dvo.getCralIdvTno()) ? dvo.getCralIdvTno() : "");
                    dvo.setCralLocaraTno(
                        String.format("%s-%s-%s", dvo.getCralLocaraTno(), dvo.getMexnoEncr(), dvo.getCralIdvTno())
                    );
                }
            );
        }
        results = this.wogcPartnerEngineerConverter.mapAllWogcPartnerEngineerDvoToFindJoeManagementRes(dvos);
        results.setPageInfo(newPage);
        return results;
    }

    public List<FindJoeManagementRes> getJoeManagementForExcelDownload(
        FindJoeManagementReq dto
    ) {
        List<WogcPartnerEngineerDto.FindJoeManagementRes> result = null;
        List<WogcPartnerEngineerDvo> dvos = this.mapper.selectJoeManagementForExcelDownload(dto);

        /*
        for (WogcPartnerEngineerDvo dvo : dvos) {
            String cralLocaraTno = StringUtils.isNotEmpty(dvo.getCralLocaraTno()) ? dvo.getCralLocaraTno() : "";
            String mexnoEncr = StringUtils.isNotEmpty(dvo.getMexnoEncr()) ? dvo.getMexnoEncr() : "";
            String cralIdvTno = StringUtils.isNotEmpty(dvo.getCralIdvTno()) ? dvo.getCralIdvTno() : "";
            dvo.setCralLocaraTno(cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno);
        }
        */

        if (CollectionUtils.isNotEmpty(dvos)) {
            dvos.forEach(
                dvo -> {
                    dvo.setCralLocaraTno(StringUtils.isNotEmpty(dvo.getCralLocaraTno()) ? dvo.getCralLocaraTno() : "");
                    dvo.setMexnoEncr(StringUtils.isNotEmpty(dvo.getMexnoEncr()) ? dvo.getMexnoEncr() : "");
                    dvo.setCralIdvTno(StringUtils.isNotEmpty(dvo.getCralIdvTno()) ? dvo.getCralIdvTno() : "");
                    dvo.setCralLocaraTno(
                        String.format("%s-%s-%s", dvo.getCralLocaraTno(), dvo.getMexnoEncr(), dvo.getCralIdvTno())
                    );
                }
            );
        }
        result = this.wogcPartnerEngineerConverter.mapWogcPartnerEngineerDvoToFindJoeManagementRes(dvos);
        return result;
    }

    @Transactional
    public int saveJoeManagement(List<SaveJoeManagementReq> dtos) {
        int processCnt = 0;

        for (SaveJoeManagementReq dto : dtos) {
            WogcPartnerEngineerDvo dvo = this.wogcPartnerEngineerConverter
                .mapSaveJoeManagementReqToWogcPartnerEngineerDvo(dto);

            String mexnoEncr = dvo.getMexnoEncr();
            processCnt += this.mapper.insertWkGrpBlgDtl(dvo);
            if (dvo.getVlStrtdt().substring(0, 6).equals(DateUtil.getNowDayString().substring(0, 6))) {
                this.mapper.updatePrtnrGrpCd(dvo); //직책업데이트
            }
            dvo.setMexnoEncr(mexnoEncr);
            this.mapper.updatePrtnrBusiness(dvo); //업무용전화번호업데이트
        }

        return processCnt;
    }

    public PagingResult<FindEngineerGradeRes> getEngineerGradePages(
        FindEngineerGradeReq dto, PageInfo pageInfo
    ) {
        return this.mapper.selectEngineerGrades(dto, pageInfo);
    }

    public List<FindEngineerGradeRes> getEngineerGradeForExcelDownload(
        FindEngineerGradeReq dto
    ) {
        return this.mapper.selectEngineerGrades(dto);
    }

    @Transactional
    public int saveEngineerGrade(List<SaveEngineerGradeReq> dtos) {
        int processCnt = 0;

        for (SaveEngineerGradeReq dto : dtos) {
            WogcPartnerEngineerDvo dvo = this.wogcPartnerEngineerConverter
                .mapSaveEngineerGradeReqToWogcPartnerEngineerDvo(dto);
            dvo.setDtaDlYn("N");
            processCnt += this.mapper.insertEgerGdRgst(dvo);
            /* 배치에서 해야 된다고
            if (dvo.getApyStrtDt().substring(0, 6).equals(DateUtil.getNowDayString().substring(0, 6))) {
                this.mapper.updateMonthPrtnrRolDvCd(dvo); //월파트너직무업데이트
                this.mapper.updatePrtnrRolDvCd(dvo); //직무업데이트
                this.mapper.insertPrtnrHist(dvo); //파트너상세이력인서트
            }
            */
        }
        return processCnt;
    }

    public ExcelUploadDto.UploadRes saveEngineerGradeForDirectExcelUpload(MultipartFile file, String baseYm)
        throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("prtnrNo", messageService.getMessage("MSG_TXT_PRTNR_NUM"));
        headerTitle.put("prtnrGdCd", messageService.getMessage("MSG_TXT_GRD_DV"));
        headerTitle.put("apyStrtDt", messageService.getMessage("MSG_TXT_APY_STRTDT"));
        headerTitle.put("apyEnddt", messageService.getMessage("MSG_TXT_APY_ENDDT"));

        String pattern = "^[0-9]*$"; //숫자 정규식
        String status = "S";
        int row = 2;
        List<Map<String, Object>> checks = new ArrayList<>();
        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);
        List<WogcPartnerEngineerDvo> lists = excelReadService.readExcel(file, meta, WogcPartnerEngineerDvo.class);
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        for (WogcPartnerEngineerDvo list : lists) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            int finalRow = row;
            int differentRsbCnt = 0;
            Map<String, Object> check = new HashMap<>();
            list.setOgTpCd("W06");

            if (StringUtils.isEmpty(list.getPrtnrNo())) { //파트너번호 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getPrtnrNo())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                }
                String prtnrKnm = this.mapper.selectEngineerPartner(list);
                list.setPrtnrKnm(prtnrKnm);
                if (StringUtils.isEmpty(prtnrKnm)) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("prtnrNo"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CRSP_PRTNR_NO_INF_NEX"));// 해당 파트너번호에 대한 정보가 없습니다.
                }
            }
            if (StringUtils.isEmpty(list.getPrtnrGdCd())) { //등급코드 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("prtnrGdCd"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getPrtnrGdCd())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("prtnrGdCd"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                }
            }
            if (StringUtils.isEmpty(list.getApyStrtDt())) { //시작일자 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyStrtdt"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getApyStrtDt())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("apyStrtdt"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                }
            }
            if (StringUtils.isEmpty(list.getApyEnddt())) { //종료일자 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyEnddt"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getApyEnddt())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("apyEnddt"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_ONLY_NUMBER")); // 숫자만 입력 가능합니다
                }
            }

            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            }
            row++;
        }

        if (CollectionUtils.isNotEmpty(errorDvos)) {
            status = "E";
        }

        if (status.equals("S")) {
            for (WogcPartnerEngineerDvo dvo : lists) {
                dvo.setDtaDlYn("N");
                this.mapper.insertEgerGdRgst(dvo);
                /* 배치에서 해야 된다고
                if (dvo.getApyStrtDt().substring(0, 6).equals(DateUtil.getNowDayString().substring(0, 6))) {
                    this.mapper.updateMonthPrtnrRolDvCd(dvo); //월파트너직무업데이트
                    this.mapper.updatePrtnrRolDvCd(dvo); //직무업데이트
                    this.mapper.insertPrtnrHist(dvo); //파트너상세이력인서트
                }
                */
            }
        }

        ExcelUploadDto.UploadRes result = ExcelUploadDto.UploadRes.builder()
            .status(status)
            .excelData(lists)
            .errorInfo(errorDvos)
            .build();
        return result;
    }

}
