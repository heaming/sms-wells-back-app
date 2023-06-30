package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingOstrQtyDto.*;
import static com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.common.web.bond.zcommon.utils.BnBondUtils;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaSeedingOstrQtyConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyExcelDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingOstrQtyValidDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedingOstrQtyMapper;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0129M01 모종 출고가능수량 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-27
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedingOstrQtyService {

    private final WsnaSeedingOstrQtyMapper mapper;

    private final WsnaSeedingOstrQtyConverter converter;

    // 메시지 서비스
    private final MessageResourceService messageService;

    // 엑셀 서비스
    private final ExcelReadService excelService;

    private static final long MAX_VALUE = 999999999999L;

    /**
     * 모종 출고가능수량 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getSeedingOstrQtysPaging(SearchReq dto, PageInfo pageInfo) {

        return this.mapper.selectDcbySdingRcpLim(dto, pageInfo);
    }

    /**
     * 모종 출고가능수량 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedingOstrQtysExcelDownload(SearchReq dto) {
        return this.mapper.selectDcbySdingRcpLim(dto);
    }

    /**
     * 모종 출고가능수량 수정
     * @param dtos
     * @return
     */
    @Transactional
    public int editSeedingOstrQtys(List<EditReq> dtos) {

        int count = 0;

        for (EditReq dto : dtos) {
            WsnaSeedingOstrQtyDvo dvo = this.converter.mapEditReqToWsnaSeedingOstrQtyDvo(dto);

            int result = this.mapper.updateDcbySdingRcpLimQty(dvo);
            // 저장에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            count += result;
        }

        return count;
    }

    /**
     * 모종 출고가능수량 엑셀 업로드
     * @param file
     * @return
     */
    @Transactional
    public UploadRes createSeedingOstrQtysExcelUpload(MultipartFile file) throws Exception {

        int count = 0;

        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();

        // 방문일자
        headerTitle.put("vstDt", this.messageService.getMessage("MSG_TXT_VST_DT"));
        // 업무유형
        headerTitle.put("svBizHclsfCd", this.messageService.getMessage("MSG_TXT_TASK_TYPE"));
        // 패키지구분
        headerTitle.put("sdingPkgGrpCd", this.messageService.getMessage("MSG_TXT_PKG_DV"));
        // 수량
        headerTitle.put("limQty", this.messageService.getMessage("MSG_TXT_QTY"));

        // file 복호화

        List<WsnaSeedingOstrQtyExcelDvo> list = this.excelService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WsnaSeedingOstrQtyExcelDvo.class, Boolean.TRUE);

        // 모종패키지코드 조회
        List<String> pkgDvCds = this.mapper.selectCmzPkgDvCds();
        // 업무유형코드 조회
        List<String> svBizHclsfCds = this.mapper.selectCmzSvBizHclsfCds();
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {

            int row = 2;
            WsnaSeedingOstrQtyValidDvo validDvo = new WsnaSeedingOstrQtyValidDvo();
            validDvo.setPkgDvCds(pkgDvCds);
            validDvo.setSvBizHclsfCds(svBizHclsfCds);
            validDvo.setHeaderTitle(headerTitle);
            validDvo.setErrorDvos(errorDvos);

            for (WsnaSeedingOstrQtyExcelDvo dvo : list) {
                validDvo.setDvo(dvo);
                validDvo.setRow(row);

                // 유효성 체크
                this.checkExcelUploadValid(validDvo);
                row++;
            }

            // 오류가 없을 경우
            if (CollectionUtils.isEmpty(errorDvos)) {
                for (WsnaSeedingOstrQtyExcelDvo dvo : list) {
                    WsnaSeedingOstrQtyDvo createDvo = this.converter
                        .mapWsnaSeedingOstrQtyExcelDvoToWsnaSeedingOstrQtyDvo(dvo);
                    count += this.mapper.insertDcbySdingRcpLimQty(createDvo);
                }

                // 생성에 실패 하였습니다.
                BizAssert.isTrue(count == list.size(), "MSG_ALT_CRT_FAIL");
            }
        }

        String status = CollectionUtils.isEmpty(errorDvos) ? PdProductConst.EXCEL_UPLOAD_SUCCESS
            : PdProductConst.EXCEL_UPLOAD_ERROR;

        return UploadRes.builder()
            .status(status)
            .excelData(list)
            .errorInfo(errorDvos)
            .build();
    }

    /**
     * 엑셀업로드 유효성 체크
     * @param validDvo
     */
    private void checkExcelUploadValid(WsnaSeedingOstrQtyValidDvo validDvo) {

        WsnaSeedingOstrQtyExcelDvo dvo = validDvo.getDvo();
        int row = validDvo.getRow();
        List<String> pkgDvCds = validDvo.getPkgDvCds();
        List<String> svBizHclsfCds = validDvo.getSvBizHclsfCds();
        Map<String, String> headerTitle = validDvo.getHeaderTitle();
        List<ExcelUploadErrorDvo> errorDvos = validDvo.getErrorDvos();

        // 방문일자
        String vstDt = dvo.getVstDt();
        // 업무유형코드
        String svBizHclsfCd = dvo.getSvBizHclsfCd();
        // 패키지코드
        String sdingPkgGrpCd = dvo.getSdingPkgGrpCd();
        // 가능수량
        String limQty = dvo.getLimQty();

        // null check
        String[] nullColumnName = new String[4];
        if (StringUtils.isBlank(vstDt)) {
            nullColumnName[0] = "vstDt";
        }

        if (StringUtils.isBlank(svBizHclsfCd)) {
            nullColumnName[1] = "svBizHclsfCd";
        }

        if (StringUtils.isBlank(sdingPkgGrpCd)) {
            nullColumnName[2] = "sdingPkgGrpCd";
        }

        if (StringUtils.isBlank(limQty)) {
            nullColumnName[3] = "limQty";
        }

        if (StringUtils.isBlank(vstDt) || StringUtils.isBlank(svBizHclsfCd) || StringUtils.isBlank(sdingPkgGrpCd)
            || StringUtils.isBlank(limQty)) {
            for (String column : nullColumnName) {
                if (StringUtil.isNotBlank(column)) {
                    String headerTit = headerTitle.get(column);

                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTit);
                    // {0} 은(는) 필수값 입니다.
                    errorDvo.setErrorData(
                        this.messageService.getMessage("MSG_ALT_NCELL_REQUIRED_VAL", new String[] {headerTit})
                    );
                    errorDvos.add(errorDvo);
                }
            }
        }

        // 방문일자 검증
        if (StringUtils.isNotBlank(vstDt) && !BnBondUtils.checkDate(vstDt)) {
            String headerTit = headerTitle.get("vstDt");
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            errorDvo.setErrorRow(row);
            errorDvo.setHeaderName(headerTit);
            // {0}이/가 올바르지 않은 날짜형식입니다.
            errorDvo.setErrorData(this.messageService.getMessage("MSG_ALT_ERROR_DT", new String[] {headerTit}));
            errorDvos.add(errorDvo);
        }

        // 업무유형코드 검증
        if (StringUtils.isNotBlank(svBizHclsfCd) && !svBizHclsfCds.contains(svBizHclsfCd)) {
            String headerTit = headerTitle.get("svBizHclsfCd");
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            errorDvo.setErrorRow(row);
            errorDvo.setHeaderName(headerTit);
            // 잘못된 업무유형 입니다.
            errorDvo.setErrorData(this.messageService.getMessage("MSG_ALT_INVALID_TASK_TYPE"));
            errorDvos.add(errorDvo);
        }

        // 패키지코드 검증
        if (StringUtils.isNotBlank(sdingPkgGrpCd) && !pkgDvCds.contains(sdingPkgGrpCd)) {
            String headerTit = headerTitle.get("sdingPkgCd");
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            errorDvo.setErrorRow(row);
            errorDvo.setHeaderName(headerTit);
            // 잘못된 패키지구분 입니다.
            errorDvo.setErrorData(this.messageService.getMessage("MSG_ALT_INVALID_PKG_DV"));
            errorDvos.add(errorDvo);
        }

        // 가능수량 검증
        if (StringUtils.isNotBlank(limQty)) {
            BigDecimal qty = new BigDecimal(limQty);
            String headerTit = headerTitle.get("limQty");

            // qty < 1
            if (qty.compareTo(BigDecimal.ONE) < 0) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTit);
                // 값이 0이거나 마이너스입니다.
                errorDvo.setErrorData(this.messageService.getMessage("MSG_ALT_MINUS_ZR_VAL_INC"));
                errorDvos.add(errorDvo);
                // qty > 999,999,999,999
            } else if (qty.compareTo(BigDecimal.valueOf(MAX_VALUE)) > 0) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(headerTit);
                // {0} 항목의 값은 {1} 이하여야 합니다.
                errorDvo.setErrorData(
                    this.messageService
                        .getMessage("MSG_ALT_VALUE_OVER", new String[] {headerTit, String.format("%,d", MAX_VALUE)})
                );
                errorDvos.add(errorDvo);
            }
        }
    }
}
