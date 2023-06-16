package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.*;
import java.util.regex.Pattern;

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

    public PagingResult<WogcPartnerEngineerDto.FindJoeManagementRes> getJoeManagementPages(
        FindJoeManagementReq dto, PageInfo pageInfo
    ) {
        return this.mapper.selectJoeManagements(dto, pageInfo);
    }

    public List<WogcPartnerEngineerDto.FindJoeManagementRes> getJoeManagementForExcelDownload(
        FindJoeManagementReq dto
    ) {
        List<WogcPartnerEngineerDto.FindJoeManagementRes> result = null;
        List<WogcPartnerEngineerDvo> dvos = this.mapper.selectJoeManagementForExcelDownload(dto);
        for (WogcPartnerEngineerDvo dvo : dvos) {
            String cralLocaraTno = StringUtils.isNotEmpty(dvo.getCralLocaraTno()) ? dvo.getCralLocaraTno() : "";
            String mexnoEncr = StringUtils.isNotEmpty(dvo.getMexnoEncr()) ? dvo.getMexnoEncr() : "";
            String cralIdvTno = StringUtils.isNotEmpty(dvo.getCralIdvTno()) ? dvo.getCralIdvTno() : "";
            dvo.setCralLocaraTno(cralLocaraTno + "-" + mexnoEncr + "-" + cralIdvTno);
        }
        result = this.wogcPartnerEngineerConverter.mapWogcPartnerEngineerDvoToFindJoeManagementRes(dvos);
        return result;
    }

    @Transactional
    public int saveJoeManagement(List<WogcPartnerEngineerDto.SaveJoeManagementReq> dtos) {
        int processCnt = 0;

        for (WogcPartnerEngineerDto.SaveJoeManagementReq dto : dtos) {
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

    public PagingResult<WogcPartnerEngineerDto.FindEngineerGradeRes> getEngineerGradePages(
        FindEngineerGradeReq dto, PageInfo pageInfo
    ) {
        return this.mapper.selectEngineerGrades(dto, pageInfo);
    }

    public List<WogcPartnerEngineerDto.FindEngineerGradeRes> getEngineerGradeForExcelDownload(
        FindEngineerGradeReq dto
    ) {
        return this.mapper.selectEngineerGrades(dto);
    }

    @Transactional
    public int saveEngineerGrade(List<WogcPartnerEngineerDto.SaveEngineerGradeReq> dtos) {
        int processCnt = 0;

        for (WogcPartnerEngineerDto.SaveEngineerGradeReq dto : dtos) {
            WogcPartnerEngineerDvo dvo = this.wogcPartnerEngineerConverter
                .mapSaveEngineerGradeReqToWogcPartnerEngineerDvo(dto);
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
