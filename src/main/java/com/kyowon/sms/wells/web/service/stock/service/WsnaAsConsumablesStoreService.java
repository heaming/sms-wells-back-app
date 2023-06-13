package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.common.web.bond.zcommon.utils.BnBondUtils;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsConsumablesStoreDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsConsumablesStoreDvo;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAsConsumablesStoreConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsConsumablesStoreMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsConsumablesStoreDto.*;
import static com.sds.sflex.common.common.dto.ExcelUploadDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class WsnaAsConsumablesStoreService {

    private final WsnaAsConsumablesStoreMapper mapper;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    private final WsnaAsConsumablesStoreConverter converter;

    public PagingResult<SearchRes> getAsConsumablesStorePages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAsConsumablesStorePages(dto, pageInfo);
    }

    public List<SearchRes> getAsConsumablesStoresForExcelDownload(SearchReq dto) {
        return mapper.selectAsConsumablesStorePages(dto);
    }

    @Transactional
    public UploadRes saveAsConsumablesStoresExcelUpload(MultipartFile file) throws Exception {
        // 업로드 엑셀 헤더 설정
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("strWareNo", messageResourceService.getMessage("MSG_TXT_STR_WARE_MNGT_NO")); //입고창고번호
        headerTitle.put("wareNm", messageResourceService.getMessage("MSG_TXT_CNT_NM")); //센터명
        headerTitle.put("strRgstDt", messageResourceService.getMessage("MSG_TXT_STR_RGST_DT")); //입고등록일자
        headerTitle.put("itmPdCd", messageResourceService.getMessage("MSG_TXT_ITM_CD")); //품목코드
        headerTitle.put("itmPdNm", messageResourceService.getMessage("MSG_TXT_ITM_NM")); //품목명
        headerTitle.put("itmGdCd", messageResourceService.getMessage("MSG_TXT_GD")); //등급
        headerTitle.put("strQty", messageResourceService.getMessage("MSG_TXT_STR_QTY")); //입고수량
        headerTitle.put("rmkCn", messageResourceService.getMessage("MSG_TXT_STR_RSON")); //입고사유

        // file 복호화
        List<WsnaAsConsumablesStoreDvo> list = excelReadService
            .readExcel(file, new ExcelMetaDvo(1, headerTitle), WsnaAsConsumablesStoreDvo.class, true);
        // 업로드 에러
        List<ExcelUploadErrorDvo> excelUploadErrorDvos = new ArrayList<>();

        //유효성체크
        // 유효성 걸린 엑셀 row 체크
        int row = 2;
        for (WsnaAsConsumablesStoreDvo dvo : list) {
            // null check
            String[] nullColumnName = new String[8];
            if (StringUtil.isBlank(dvo.getStrWareNo())) {
                nullColumnName[0] = "strWareNo";
            }
            if (StringUtil.isBlank(dvo.getStrWareNo())) {
                nullColumnName[1] = "wareNm";
            }

            if (StringUtil.isBlank(dvo.getStrRgstDt())) {
                nullColumnName[2] = "strRgstDt";
            }
            if (StringUtil.isBlank(dvo.getItmPdCd())) {
                nullColumnName[3] = "itmPdCd";
            }

            if (StringUtil.isBlank(dvo.getItmPdNm())) {
                nullColumnName[4] = "itmPdNm";
            }

            if (StringUtil.isBlank(dvo.getItmGdCd())) {
                nullColumnName[5] = "itmGdCd";
            }

            if (StringUtil.isBlank(dvo.getStrQty())) {
                nullColumnName[6] = "strQty";
            }

            if (StringUtil.isBlank(dvo.getRmkCn())) {
                nullColumnName[7] = "rmkCn";
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

            //입고등록일자 검증
            if (StringUtil.isNotBlank(dvo.getStrRgstDt())) {
                if (BnBondUtils.checkDate(dvo.getStrRgstDt())) {
                    String strRgstDt = dvo.getStrRgstDt().replaceAll("[^0-9]", "");
                    dvo.setStrRgstDt(strRgstDt);
                } else {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("strRgstDt"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_EXIST_INVALID_ITEM" // 유효하지 않은 항목이 있습니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }

            }

            //입고창고번호 검증
            //1. 실제존재하는 입고창고번호인지
            if (StringUtil.isNotBlank(dvo.getStrWareNo())) {

                int strCount = mapper.selectMcbyWareIzCount(dvo);

                if (strCount == 0) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("strWareNo"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_INVLD_WARE_NO" // 잘못된 창고번호입니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
            }
            //품목상품코드 검증
            if (StringUtil.isNotBlank(dvo.getItmPdCd())) {

                int strItmPdCdCount = mapper.selectItmPdCdCount(dvo);
                String mgtUnt = mapper.selectMgtUntFind(dvo);
                dvo.setMngtUnit(mgtUnt);

                if (strItmPdCdCount == 0) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("strWareNo"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_INVLD_ITM_PD_CD" // 잘못된 품목상품코드 입니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);

                }
            }

            //입고등록일자가 당월이 아닌경우 체크
            if (StringUtil.isNotBlank(dvo.getStrRgstDt())) {
                if (BnBondUtils.checkDate(dvo.getStrRgstDt())) {
                    int strRgstDt = Integer
                        .parseInt(StringUtils.substring(dvo.getStrRgstDt().replaceAll("[^0-9]", ""), 0, 6));
                    int toMonthsRgstDt = Integer.parseInt(StringUtils.substring(DateUtil.getNowDayString(), 0, 6));

                    if (strRgstDt < toMonthsRgstDt) {
                        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                        errorDvo.setErrorRow(row);
                        errorDvo.setHeaderName(headerTitle.get("strRgstDt"));
                        errorDvo.setErrorData(
                            messageResourceService.getMessage(
                                "MSG_ALT_STR_DT_THM_RGST" // 입고등록일자가 당월인 건만 등록 가능합니다.
                            )
                        );
                        excelUploadErrorDvos.add(errorDvo);
                    }
                }
            }
            //입고수량이 마이너스 이거나 0일경우 체크
            if (StringUtil.isNotBlank(dvo.getStrQty())) {
                int validStrQty = Integer.parseInt(dvo.getStrQty());

                if (validStrQty <= 0) {
                    ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                    errorDvo.setErrorRow(row);
                    errorDvo.setHeaderName(headerTitle.get("strQty"));
                    errorDvo.setErrorData(
                        messageResourceService.getMessage(
                            "MSG_ALT_MINUS_ZR_VAL_INC" //값이 0이거나 마이너스입니다.
                        )
                    );
                    excelUploadErrorDvos.add(errorDvo);
                }
            }
            row++;
        }
        //insert 로직 실행
        if (excelUploadErrorDvos.size() == 0) {

            List<String> strRgstDts = list.stream().map(WsnaAsConsumablesStoreDvo::getStrRgstDt).distinct().toList();

            for (String strRgstDt : strRgstDts) {
                // seq 따고
                String strTpCd = "117"; //기타입고
                String itmStrNo = null;
                itmStrNo = mapper.selectNextItmStrNo(strTpCd, strRgstDt);

                List<WsnaAsConsumablesStoreDvo> dvos = list.stream()
                    .filter(data -> strRgstDt.equals(data.getStrRgstDt())).toList();
                int seq = 1;
                for (WsnaAsConsumablesStoreDvo insertDvo : dvos) {
                    insertDvo.setItmStrSn(seq++);
                    insertDvo.setItmStrNo(itmStrNo);
                    insertDvo.setStrTpCd(strTpCd);

                    int result = mapper.insertAsConsumablesStore(insertDvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                    //TODO : 품목재고내역 create 붙여야함(서비스로 변경 후 서비스호출예정)

                }
            }
        }

        return UploadRes.builder()
            .status(excelUploadErrorDvos.isEmpty() ? "S" : "E").errorInfo(excelUploadErrorDvos).excelData(list).build();
    }

    @Transactional
    public int saveAsConsumablesStores(List<SaveReq> dtos) {
        int processCount = 0;
        String itmStrNo = null;
        String strTpCd = "117";

        SaveReq saveReq = dtos.get(0);
        String strRgstDtParam = saveReq.strRgstDt().replaceAll("[^0-9]", "");
        if (StringUtils.isEmpty(saveReq.itmStrNo())) {
            itmStrNo = this.mapper.selectNextItmStrNo(strTpCd, strRgstDtParam);

        }

        for (SaveReq dto : dtos) {
            WsnaAsConsumablesStoreDvo dvo = this.converter.mapSaveReqToAsConsumablesStoreDvo(dto);

            //STEP01 : 입고등록일자 검증진행
            if (BnBondUtils.checkDate(dvo.getStrRgstDt())) {
                String strRgstDt = dvo.getStrRgstDt().replaceAll("[^0-9]", "");
                dvo.setStrRgstDt(strRgstDt);
            }

            //STEP02 : 창고마감일자 체크
            int chkWareClose = this.mapper.selectChkWareClose(dvo);
            //해당 입고년월은 이미 마감이 완료되어, 입고작업이 불가능합니다.
            BizAssert.isTrue(chkWareClose == 0, "MSG_ALT_STR_YM_CL_FSH_STR_WK_IMP");

            //STEP03 : 현재사용중인 창고인지 체크
            if (StringUtil.isNotBlank(dvo.getStrWareNo())) {
                int strCount = mapper.selectMcbyWareIzCount(dvo);
                //잘못된 창고번호입니다.
                BizAssert.isTrue(strCount != 0, "MSG_ALT_INVLD_WARE_NO");
            }
            //STEP03 : 정상적인 품목상품코드인지 확인
            if (StringUtil.isNotBlank(dvo.getItmPdCd())) {
                int strItmPdCdCount = mapper.selectItmPdCdCount(dvo);
                String mgtUnt = mapper.selectMgtUntFind(dvo);
                dvo.setMngtUnit(mgtUnt);
                // 잘못된 품목상품코드 입니다.
                BizAssert.isTrue(strItmPdCdCount != 0, "MSG_ALT_INVLD_ITM_PD_CD");
            }

            //STEP04 : 입고등록일자가 당월이 아닌경우 체크
            if (StringUtil.isNotBlank(dvo.getStrRgstDt())) {
                int strRgstDt = Integer
                    .parseInt(StringUtils.substring(dvo.getStrRgstDt().replaceAll("[^0-9]", ""), 0, 6));
                int toMonthsRgstDt = Integer.parseInt(StringUtils.substring(DateUtil.getNowDayString(), 0, 6));
                // 입고등록일자가 당월인 건만 등록 가능합니다.
                BizAssert.isTrue(strRgstDt == toMonthsRgstDt, "MSG_ALT_STR_DT_THM_RGST");
            }

            //STEP05 : 입고수량이 마이너스 이거나 0일경우 체크
            if (StringUtil.isNotBlank(dvo.getStrQty())) {
                int validStrQty = Integer.parseInt(dvo.getStrQty());
                //값이 0이거나 마이너스입니다.
                BizAssert.isTrue(validStrQty > 0, "MSG_ALT_MINUS_ZR_VAL_INC");
            }

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    if (!StringUtils.isEmpty(itmStrNo)) {
                        dvo.setItmStrNo(itmStrNo);
                    }
                    dvo.setStrTpCd(strTpCd);
                    int result = this.mapper.insertLineAsConsumablesStore(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    //TODO : 품목재고내역 create 붙여야함(서비스로 변경 후 서비스호출예정)
                    processCount += result;

                }

                case CommConst.ROW_STATE_UPDATED -> {
                    int result = this.mapper.updateAsConsumablesStore(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    processCount += result;

                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }

        return processCount;
    }

    public int removeAsConsumablesStores(List<RemoveReq> dtos) {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WsnaAsConsumablesStoreDvo dvo = this.converter.mapRemoveReqToAsConsumablesStoreDvo(dto);

            //STEP01 : 창고마감일자 체크
            int chkWareClose = this.mapper.selectChkWareClose(dvo);
            //해당 입고년월은 이미 마감이 완료되어, 입고작업이 불가능합니다.
            BizAssert.isTrue(chkWareClose == 0, "MSG_ALT_STR_YM_CL_FSH_STR_WK_IMP");

            int result = this.mapper.deleteAsConsumablesStores(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;

            //TODO : 품목재고내역 삭제 메소드 붙여야함(서비스로 변경 후 서비스호출예정)

        }

        return processCount;
    }

    public List<SearchRes> getItemProductCodes(SearchItemReq dto) {
        return this.mapper.selectItemProductCodes(dto);
    }
}
