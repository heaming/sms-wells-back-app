package com.kyowon.sms.wells.web.fee.standard.service;

import com.kyowon.sms.wells.web.fee.standard.converter.WfeySellProductTypeConverter;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SaveSellProductReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SearchSellProductReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SearchSellProductRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeySellProductTypeDvo;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeySellProductTypeMapper;

import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <pre>
 * 판매상품별 제품유형
 * </pre>
 *
 * @author mj
 * @since 2023.07.13
 */
@Service
@RequiredArgsConstructor
public class WfeySellProductTypeService {

    private final WfeySellProductTypeMapper mapper;
    private final WfeySellProductTypeConverter converter;
    private final MessageResourceService messageService;
    private final ExcelReadService excelReadService;


    /**
     * 조회
     * @param req
     * @return
     */
    public List<SearchSellProductRes> getSellProductTypeList(SearchSellProductReq req) {
        return mapper.selectSellProductTypeList(req);
    }
    public PagingResult<SearchSellProductRes> getSellProductTypeList(SearchSellProductReq req, PageInfo pageInfo) {
        return mapper.selectSellProductTypeList(req, pageInfo);
    }

    /**
     * 저장
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveSellProductType(List<SaveSellProductReq> req) throws Exception {
        int processCount = 0;
         for (SaveSellProductReq dto : req) {
            WfeySellProductTypeDvo dvo = converter.mapSaveReqWfeySellProductTypeDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += mapper.insertSellProductType(dvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateSellProductType(dvo);
                }
                case CommConst.ROW_STATE_DELETED -> {
                    processCount += mapper.deleteSellProductType(dvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }


    /**
     * 엑셀업로드
     * @param file
     * @return
     * @throws Exception
     */
    @Transactional
    public ExcelUploadDto.UploadRes saveSellProductTypeExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("basePdCd", messageService.getMessage("MSG_TXT_PRDT_CODE"));
        headerTitle.put("feePdctTpCd1", messageService.getMessage("MSG_TXT_PDCT_TP")+"(M)");
        headerTitle.put("feePdctTpCd2", messageService.getMessage("MSG_TXT_PDCT_TP")+"(P)");
        headerTitle.put("apyStrtYm", messageService.getMessage("MSG_TXT_APY_STRT_YM"));
        headerTitle.put("apyEndYm", messageService.getMessage("MSG_TXT_APY_END_YM"));

        String status = "S";
        int row = 2;
        List<Map<String, Object>> checks = new ArrayList<>();
        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);
        List<WfeySellProductTypeDvo> lists = excelReadService.readExcel(file, meta, WfeySellProductTypeDvo.class);
        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();

        for (WfeySellProductTypeDvo list : lists) {
            ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
            Map<String, Object> check = new HashMap<>();
            int finalRow = row;
            int cntrExistCnt = 0;

            // 필수 유효성
            if (StringUtils.isEmpty(list.getBasePdCd())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("basePdCd"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (StringUtils.isEmpty(list.getApyStrtYm())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyStrtYm"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            if (StringUtils.isEmpty(list.getApyEndYm())) {
                errorDvo.setErrorRow(finalRow);
                errorDvo.setHeaderName(headerTitle.get("apyEndYm"));
                errorDvo.setErrorData(messageService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL")); //필수값이 누락되어 있습니다.
            }
            // 필수 유효성 통과시 데이터 정합성 체크
            if (StringUtils.isNotEmpty(list.getBasePdCd()) && StringUtils.isNotEmpty(list.getApyStrtYm()) && StringUtils.isNotEmpty(list.getApyEndYm())) {
                // 입력 중복 체크
                if (CollectionUtils.isNotEmpty(checks)) {
                    for (int i = 0; i < checks.size(); i++) {
                        if (checks.get(i).get("basePdCd").equals(list.getBasePdCd())
                            && checks.get(i).get("apyStrtYm").equals(list.getApyStrtYm())
                            && checks.get(i).get("apyEndYm").equals(list.getApyEndYm())) {
                            errorDvo.setErrorRow(finalRow);
                            errorDvo.setHeaderName(headerTitle.get("basePdCd"));
                            errorDvo.setErrorData(messageService.getMessage("MSG_ALT_DUPLICATE_EXISTS")); //중복된 값이 존재합니다.
                        }
                    }
                }
                // DB중복체크
                cntrExistCnt = mapper.selectDuplicateSellProductType(list);
                if (cntrExistCnt > 0) { //기존데이터 체크
                    errorDvo.setErrorRow(finalRow);
                    errorDvo.setHeaderName(headerTitle.get("basePdCd"));
                    errorDvo.setErrorData(messageService.getMessage("MSG_TXT_SMD_INF_EXST")); // 동일한 정보가 존재합니다.
                }
                check.put("basePdCd", list.getBasePdCd());
                check.put("apyStrtYm", list.getApyStrtYm());
                check.put("apyEndYm", list.getApyEndYm());
                checks.add(check);
            }
            if (null != errorDvo && (errorDvo.getErrorRow() != 0)) {
                errorDvos.add(errorDvo);
            }
            row++;
        }
        if (CollectionUtils.isNotEmpty(errorDvos)) {
            status = "E";
        }
        // 성공시 insert gogo
        if (status.equals("S")) {
            for (WfeySellProductTypeDvo list : lists) {
                mapper.insertSellProductType(list);
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
