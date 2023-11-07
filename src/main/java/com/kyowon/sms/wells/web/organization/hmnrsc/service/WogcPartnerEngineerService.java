package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerEngineerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindEngineerGradeRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.RemoveReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveEngineerAttendReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveEngineerGradeReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchVacationReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchVacationRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerEngineerMapper;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

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

    private final static String KEY_PRTNR_NO = "prtnrNo";

    private final static String MSG_ALT_ONLY_NUMBER = "MSG_ALT_ONLY_NUMBER";
    private final static String MSG_ALT_EMPTY_REQUIRED_VAL = "MSG_ALT_EMPTY_REQUIRED_VAL";

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
    public int saveEngineerAttends(List<SaveEngineerAttendReq> dtos) {
        int processCount = 0;

        for (SaveEngineerAttendReq dto : dtos) {
            WogcPartnerEngineerDvo engineer = this.wogcPartnerEngineerConverter
                .mapSaveEngineerAttendReqToWogcPartnerEngineerDvo(dto);
            engineer.setDetail("N");
            //engineer.setPrtnrNo(prtnrNo);

            processCount += this.mapper.updateEngineer(engineer);
        }
        return processCount;
    }

    public PagingResult<SearchVacationRes> getVacations(
        SearchVacationReq dto, PageInfo pageInfo
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
    public int saveVacations(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WogcPartnerEngineerDvo vacations = this.wogcPartnerEngineerConverter
                .mapSaveReqToWogcPartnerEngineerDvo(dto);
            vacations.setDetail("Y");
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += this.mapper.insertVacation(vacations);
                    processCount += this.mapper.updateEngineer(vacations);

                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += this.mapper.updateVacation(vacations);
                    processCount += this.mapper.updateEngineer(vacations);

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
    public int removeVacations(List<RemoveReq> dtos) {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WogcPartnerEngineerDvo vacations = this.wogcPartnerEngineerConverter
                .mapRemoveReqToWogcPartnerEngineerDvo(dto);

            processCount += this.mapper.deleteVacation(vacations);

        }

        return processCount;
    }

    /**
     * 서비스센터 조 관리 조회
     * @param dto 조회정보
     * @param pageInfo 페이징정보
     * @return 서비스센터 조 조회
     */
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

    /**
     * 서비스센터 조 관리 엑셀다운로드
     * @param dto 조회정보
     * @return 서비스센터 조 조회
     */
    public List<FindJoeManagementRes> getJoeManagementForExcelDownload(
        FindJoeManagementReq dto
    ) {
        List<FindJoeManagementRes> result = null;
        List<WogcPartnerEngineerDvo> dvos = this.mapper.selectJoeManagementForExcelDownload(dto);

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

    /**
     * 서비스센터 조 저장
     * @param dtos 저장정보
     * @return 저장 건수
     */
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

    /**
     * 엔지니어 등급 관리 조회
     * @param dto 조회정보
     * @param pageInfo 페이징정보
     * @return 엔지니어 등급 관리 조회
     */
    public PagingResult<FindEngineerGradeRes> getEngineerGradePages(
        FindEngineerGradeReq dto, PageInfo pageInfo
    ) {
        return this.mapper.selectEngineerGrades(dto, pageInfo);
    }

    /**
     * 엔지니어 등급 관리 엑셀다운로드
     * @param dto 조회정보
     * @return 엔지니어 등급 관리 조회
     */
    public List<FindEngineerGradeRes> getEngineerGradeForExcelDownload(
        FindEngineerGradeReq dto
    ) {
        return this.mapper.selectEngineerGrades(dto);
    }

    /**
     * 엔지니어 등급관리 저장
     * @param dtos 저장정보
     * @return 저장 건수
     */
    @Transactional
    public int saveEngineerGrade(List<SaveEngineerGradeReq> dtos) {
        int processCnt = 0;

        for (SaveEngineerGradeReq dto : dtos) {
            WogcPartnerEngineerDvo dvo = this.wogcPartnerEngineerConverter
                .mapSaveEngineerGradeReqToWogcPartnerEngineerDvo(dto);
            dvo.setDtaDlYn("N");
            processCnt += this.mapper.insertEgerGdRgst(dvo);

        }
        return processCnt;
    }

    /**
     * 엔지니어 등급관리 엑셀업로드
     * @param file 엑셀데이터 정보
     * @return 저장 정보
     */
    public UploadRes saveEngineerGradeForDirectExcelUpload(MultipartFile file)
        throws Exception {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put(KEY_PRTNR_NO, messageService.getMessage("MSG_TXT_PRTNR_NUM"));
        headerTitle.put("prtnrGdCd", messageService.getMessage("MSG_TXT_GRD_DV"));
        headerTitle.put("apyStrtDt", messageService.getMessage("MSG_TXT_APY_STRTDT"));
        headerTitle.put("apyEnddt", messageService.getMessage("MSG_TXT_APY_ENDDT"));

        String pattern = "^[0-9]*$"; //숫자 정규식
        String status = "S";
        int row = 2;
        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);
        List<WogcPartnerEngineerDvo> lists = excelReadService.readExcel(file, meta, WogcPartnerEngineerDvo.class);
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        for (WogcPartnerEngineerDvo list : lists) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            int finalRow = row;
            list.setOgTpCd("W06");

            if (StringUtils.isEmpty(list.getPrtnrNo())) { //파트너번호 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get(KEY_PRTNR_NO));
                errorDvo.setErrorData(messageService.getMessage(MSG_ALT_EMPTY_REQUIRED_VAL)); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getPrtnrNo())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get(KEY_PRTNR_NO));
                    errorDvo.setErrorData(messageService.getMessage(MSG_ALT_ONLY_NUMBER)); // 숫자만 입력 가능합니다
                }
                String prtnrKnm = this.mapper.selectEngineerPartner(list);
                list.setPrtnrKnm(prtnrKnm);
                if (StringUtils.isEmpty(prtnrKnm)) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get(KEY_PRTNR_NO));
                    errorDvo.setErrorData(messageService.getMessage("MSG_ALT_CRSP_PRTNR_NO_INF_NEX"));// 해당 파트너번호에 대한 정보가 없습니다.
                }
            }
            if (StringUtils.isEmpty(list.getPrtnrGdCd())) { //등급코드 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("prtnrGdCd"));
                errorDvo.setErrorData(messageService.getMessage(MSG_ALT_EMPTY_REQUIRED_VAL)); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getPrtnrGdCd())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("prtnrGdCd"));
                    errorDvo.setErrorData(messageService.getMessage(MSG_ALT_ONLY_NUMBER)); // 숫자만 입력 가능합니다
                }
            }
            if (StringUtils.isEmpty(list.getApyStrtDt())) { //시작일자 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyStrtdt"));
                errorDvo.setErrorData(messageService.getMessage(MSG_ALT_EMPTY_REQUIRED_VAL)); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getApyStrtDt())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("apyStrtdt"));
                    errorDvo.setErrorData(messageService.getMessage(MSG_ALT_ONLY_NUMBER)); // 숫자만 입력 가능합니다
                }
            }
            if (StringUtils.isEmpty(list.getApyEnddt())) { //종료일자 유효성
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyEnddt"));
                errorDvo.setErrorData(messageService.getMessage(MSG_ALT_EMPTY_REQUIRED_VAL)); //필수값이 누락되어 있습니다.
            } else {
                if (!Pattern.matches(pattern, list.getApyEnddt())) {
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("apyEnddt"));
                    errorDvo.setErrorData(messageService.getMessage(MSG_ALT_ONLY_NUMBER)); // 숫자만 입력 가능합니다
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
            }
        }

        UploadRes result = UploadRes.builder()
            .status(status)
            .excelData(lists)
            .errorInfo(errorDvos)
            .build();
        return result;
    }

}
