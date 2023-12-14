package com.kyowon.sms.wells.web.product.manage.service;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyowon.sms.common.web.product.category.service.ZpdaClassificationMgtService;
import com.kyowon.sms.common.web.product.manage.converter.ZpdcProductConverter;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapRes;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcRelationMgtDto;
import com.kyowon.sms.common.web.product.manage.dvo.*;
import com.kyowon.sms.common.web.product.manage.mapper.ZpdcProductMapper;
import com.kyowon.sms.common.web.product.manage.service.ZpdcHistoryMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcRelationMgtService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcMaterialMgtMapper;
import com.sds.sflex.common.common.dto.CodeDto.CodeComponent;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.CodeService;
import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 상품 >> 교재/자재 WELLS Service
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WpdcMaterialMgtService {

    private final WpdcMaterialMgtMapper mapper;
    private final ZpdcProductConverter productConverter;
    private final ZpdaClassificationMgtService clsfService;
    private final ZpdcProductMapper productMapper;
    private final ZpdcProductService productService;
    private final ZpdcHistoryMgtService hisService;
    //    private final ZpdcMaterialConverter converter;

    private final ZpdcRelationMgtService relService;

    private final AttachFileService fileService;

    private final CodeService codeService;
    private final MessageResourceService messageResourceService;

    /**
     * SAP 교재/자재 페이징 조회(팝업)
     * @param dto SAP 자재코드 조회조건
     * @param pageInfo 페이징정보
     * @return SAP 자재코드 데이터 목록
     */
    public PagingResult<SearchSapRes> getMaterialSapPages(SearchSapReq dto, PageInfo pageInfo) {
        return mapper.selectMaterialSapPages(dto, pageInfo);
    }

    /**
     * SAP 교재/자재 엑셀다운(팝업)
     * @param dto 조회조건
     * @return SAP 교재/자재 목록정보
     */
    public List<SearchSapRes> getMaterialSapForExcelDownload(SearchSapReq dto) {
        return mapper.selectMaterialSapPages(dto);
    }

    /**
     * 교재/자재 저장
     * @param dto 교재/자재 정보
     * @return 교재/자재 정보
     * @throws Exception 모든 오류
     */
    @Transactional
    public ZpdcProductDto.TbPdbsPdBas saveMaterial(ZpdcMaterialMgtDto.SaveReq dto, boolean isCreate)
        throws Exception {
        String startDtm = DateUtil.getDate(new Date());

        int processCount = 0;
        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());

        // #1. Wells 특화
        // 교재/자재 : PD_TP_DTL_M(제품) , AS부품: PD_TP_DTL_A (As)부품
        dvo.setPdTpDtlCd(PdProductConst.PD_TP_DTL_CD_M);

        // #2. 분류체계 분류 계층값 FILL-IN
        dvo = clsfService.getClassifcationHierarchy(dvo);

        // #3. 상품 마스터 INSERT
        processCount = productMapper.insertProduct(dvo);

        // #3-0 상세
        productService.saveProductDetail(dvo.getPdCd(), dto.tbPdbsPdDtl(), false, startDtm);

        // #4. 각사 속성 INSERT
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        productService.saveEachCompanyPropDtl(dvo.getPdCd(), dto.tbPdbsPdEcomPrpDtl());

        if (dto.tbPdbsPdBas().isAttach()) {
            if (CollectionUtils.isEmpty(dvo.getAttachFiles())) {
                List<AttachFile> empty = new ArrayList<AttachFile>();
                fileService.saveAttachFiles(PdProductConst.ATTACH_GROUP_ID_PRD, dvo.getPdCd(), empty);
            } else {
                fileService.saveAttachFiles(PdProductConst.ATTACH_GROUP_ID_PRD, dvo.getPdCd(), dvo.getAttachFiles());
            }
        }

        // #5. 연결상품 INSERT
        // this.editEachTbPdbsPdRel(dvo.getPdCd(), dto.tbPdbsPdRel(), dto.tbPdbsPdBas().tempSaveYn());
        if (dto.isModifiedRelation() || CollectionUtils.isNotEmpty(dto.tbPdbsPdRel())) {
            relService.saveProductRelations(dvo.getPdCd(), dto.tbPdbsPdRel(), startDtm);
            hisService.createRelationHistory(dvo.getPdCd(), startDtm);
        }

        // #6. 이력 INSERT
        if (!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(dto.tbPdbsPdBas().tempSaveYn())) {

            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }

        return productConverter.mapProductDvoToPdBas(dvo);
    }

    /**
     * 연결상품 수정
     * @param pdCd 상품코드(PK)
     * @param tbPdbsPdRels 교재/자재 연결상품 정보
     * @throws Exception 모든오류
     */
    @Transactional
    public void editEachTbPdbsPdRel(
        String pdCd, List<ZpdcRelationMgtDto.ProductRelation> tbPdbsPdRels, String tempSaveYn
    )
        throws Exception {

        String startDtm = DateUtil.getDate(new Date());
        if (CollectionUtils.isNotEmpty(tbPdbsPdRels)) {
            String endDtm = PdProductConst.END_DATE_STR;

            // #1. 화면에서 삭제된 데이터 일괄 삭제처리.
            mapper.deleteTbPdbsPdRel(pdCd, tbPdbsPdRels, "NOTALL", startDtm);

            // 23-04-05 Converter로 Dto에서 Dvo로 변환시 모두 null로 반환함.
            // dto로
            for (ZpdcRelationMgtDto.ProductRelation relDto : tbPdbsPdRels) {
                mapper.mergeEachTbPdbsPdRelByDto(
                    ZpdcRelationMgtDto.ProductRelation.builder()
                        .ojPdCd(relDto.ojPdCd())
                        .pdRelId(relDto.pdRelId())
                        .basePdCd(pdCd)
                        .vlStrtDtm(StringUtil.isEmpty(relDto.vlStrtDtm()) ? startDtm : relDto.vlStrtDtm())
                        .vlEndDtm(StringUtil.isEmpty(relDto.vlEndDtm()) ? endDtm : relDto.vlEndDtm())
                        .tempSaveYn(tempSaveYn)
                        .pdRelTpCd(relDto.pdRelTpCd())
                        .build()
                );

            }

            // #2. 신규 추가 항목 INSER
            //            List<ZpdcEachTbPdbsPdRelDvo> dvos = converter.mapTbPdbsPdRelDvos(tbPdbsPdRels);
            //            for (ZpdcEachTbPdbsPdRelDvo dvo : dvos) {
            //                dvo.setBasePdCd(pdCd); /* 상품관계ID */
            //                dvo.setVlStrtDtm(startDtm);
            //                dvo.setVlEndDtm(endDtm);
            //                mapper.mergeEachTbPdbsPdRel(dvo);
            //            }
        } else {
            // BASE_PD_CD에 걸려있는 모든 REL 데이터 일괄 삭제처리.
            mapper.deleteTbPdbsPdRel(pdCd, tbPdbsPdRels, "ALL", startDtm);
        }
    }

    /**
     * 교재/자재 수정
     * @param dto 교재/자재 정보
     * @param isCreate 신규생성유무
     * @return 교재/자재 정보
     * @throws Exception 모든오류
     */
    @Transactional
    public ZpdcProductDto.TbPdbsPdBas editMaterial(ZpdcMaterialMgtDto.EditReq dto, boolean isCreate)
        throws Exception {

        String startDtm = DateUtil.getDate(new Date());

        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());

        dvo = clsfService.getClassifcationHierarchy(dvo);

        int processCount = 0;
        processCount = productMapper.updateProduct(dvo);

        // #3-0 상세
        productService.saveProductDetail(dvo.getPdCd(), dto.tbPdbsPdDtl(), false, startDtm);

        if (dto.tbPdbsPdBas().isAttach()) {
            if (CollectionUtils.isEmpty(dvo.getAttachFiles())) {
                List<AttachFile> empty = new ArrayList<AttachFile>();
                fileService.saveAttachFiles(PdProductConst.ATTACH_GROUP_ID_PRD, dvo.getPdCd(), empty);
            } else {
                fileService.saveAttachFiles(PdProductConst.ATTACH_GROUP_ID_PRD, dvo.getPdCd(), dvo.getAttachFiles());
            }
        }

        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        productService.saveEachCompanyPropDtl(dvo.getPdCd(), dto.tbPdbsPdEcomPrpDtl());

        // this.editEachTbPdbsPdRel(dvo.getPdCd(), dto.tbPdbsPdRel(), dto.tbPdbsPdBas().tempSaveYn());
        // 연결 상품 저장
        if (dto.isModifiedRelation()) {
            relService.saveProductRelations(dvo.getPdCd(), dto.tbPdbsPdRel(), startDtm);
            hisService.createRelationHistory(dvo.getPdCd(), startDtm);
        }

        if (!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(dto.tbPdbsPdBas().tempSaveYn())
            && dto.isModifiedProp()) {
            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }

        return productConverter.mapProductDvoToPdBas(dvo);
    }

    /**
     * 교재/자재 삭제
     * @param pdCd 상품코드(PK)
     * @return 성공여부
     * @throws Exception 모든오류
     */
    @Transactional
    public int removeMaterial(String pdCd) throws Exception {
        productMapper.deleteEachCompanyProps(pdCd, "");
        mapper.deleteEachPdbsPdRels(pdCd);

        int processCount = productMapper.deleteProduct(pdCd);
        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(pdCd, startDtm);
        return processCount;
    }

    /**
     * 엑셀 데이터 유효성 체크
     * @param excelData 엑셀데이터
     * @param metaItems 상품 Meta 테이블정보
     * @param tbPdbsPdBas 상품 Meta Master 정보
     * @param tbPdbsPdEcomPrpDtl 상품 각사속성 정보
     * @return 화면에 return할 엑셀오류정보
     * @throws Exception 오류정보
     */
    public List<ExcelUploadErrorDvo> checkDataValidation(
        List<Map<String, Object>> excelData,
        List<ZpdcPropertyMetaDvo> metaItems,
        List<ZpdcPropertyMetaDvo> tbPdbsPdBas,
        List<ZpdcPropertyMetaDvo> tbPdbsPdEcomPrpDtl
    ) throws Exception {

        String[] msgStrArr = new String[1];
        List<ExcelUploadErrorDvo> dataErrors = new ArrayList<ExcelUploadErrorDvo>();

        int rowIndex = 1;
        for (Map<String, Object> excelDataMap : excelData) {

            // #1. 분류체계 유효성 체크
            String compareValue = this.getExcelValue(excelDataMap.get("pdClsfId"));
            msgStrArr[0] = compareValue;
            if (null == clsfService.getClassification(compareValue)) {
                ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
                errorVo.setHeaderName("분류");
                errorVo.setErrorRow(rowIndex);
                errorVo.setErrorData(
                    messageResourceService.getMessage("MSG_ALT_DELETED_CLSF_ID", msgStrArr)
                );
                dataErrors.add(errorVo);
            }

            for (Entry<String, Object> entry : excelDataMap.entrySet()) {
                for (ZpdcPropertyMetaDvo metaVo : tbPdbsPdBas) {
                    /*
                    if (entry.getKey().equals(PdProductConst.SAP_MAT_CD)
                        && PdProductConst.SAP_MAT_CD.equals(metaVo.getColNm())) {

                        String sapPlntVal = getExcelValue2(excelDataMap, tbPdbsPdBas, PdProductConst.SAP_PLNT_VAL);

                        this.checkExcelValidation(
                            entry, metaVo, rowIndex, dataErrors, PdProductConst.VALIDATION_TARGET_DB, sapPlntVal
                        );
                    } else
                    */
                    if (entry.getKey().equals(metaVo.getColNm())) {
                        this.checkExcelValidation(
                            entry, metaVo, rowIndex, dataErrors, PdProductConst.VALIDATION_TARGET_META, null
                        );
                    }
                }

                for (ZpdcPropertyMetaDvo metaVo : tbPdbsPdEcomPrpDtl) {
                    if (entry.getKey().equals(metaVo.getColNm())) {
                        this.checkExcelValidation(
                            entry, metaVo, rowIndex, dataErrors, PdProductConst.VALIDATION_TARGET_META, null
                        );
                    }
                }
            }
            rowIndex++;
        }

        return dataErrors;
    }

    /**
     * 엑셀 유효성 체크 Method
     * @param entry - Excel 데이터객체
     * @param metaVo - Meta 정보객체
     * @param rowIndex - 현재 읽은 Excel Data Row Number
     * @param dataErrors - 오류 정보를 담고 있는 배열객체.
     */
    private void checkExcelValidation(
        Entry<String, Object> entry,
        ZpdcPropertyMetaDvo metaVo,
        int rowIndex,
        List<ExcelUploadErrorDvo> dataErrors,
        String validationTarget,
        String optionVal
    ) {
        String[] msgStrArr = new String[1];
        String compareValue = StringUtil.nvl(entry.getValue(), "");
        if (compareValue.split("\\|").length > 1) {
            compareValue = compareValue.split("\\|")[1].trim();
        }
        msgStrArr[0] = compareValue;

        if (PdProductConst.VALIDATION_TARGET_DB.equals(validationTarget)) {

            if (PdProductConst.SAP_MAT_CD.equals(metaVo.getColNm())) {
                // DB에 실재하는 유효값인지 체크.
                // 자재코드값({0})이 올바르지 않습니다.
                if (!"".equals(compareValue)) {
                    // 넘어온 자재코드 값이 I/F 테이블에 존재하는지 확인.
                    //                    ZpdcGbcoSapMatDvo sapMatVo = mapper.selectMaterialSap(compareValue);
                    //                    String sapPlntVal = getExcelValue2(excelDataMap, metaVo, PdProductConst.SAP_PLNT_VAL);
                    //                    System.out.println("optionValoptionValoptionValoptionVal : " + optionVal);
                    List<ZpdcGbcoSapMatDvo> sapMatVos = mapper.selectMaterialSaps(compareValue, optionVal);

                    if (sapMatVos.isEmpty()) {
                        ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
                        errorVo.setHeaderName(metaVo.getPrpNm());
                        errorVo.setErrorRow(rowIndex);
                        errorVo.setErrorData(
                            messageResourceService
                                .getMessage("MSG_ALT_ABNORMAL_SAP_MAT_CD", msgStrArr)
                        );
                        dataErrors.add(errorVo);
                    } else if (sapMatVos.size() > 1) {
                        msgStrArr[0] = messageResourceService.getMessage("MSG_TXT_MATI_CD");
                        ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
                        errorVo.setHeaderName(metaVo.getPrpNm());
                        errorVo.setErrorRow(rowIndex);
                        errorVo.setErrorData(
                            // {0} 결과값이 2건 이상 존재합니다.
                            messageResourceService
                                .getMessage("MSG_ALT_ABNORMAL_TO_MUCH_RESULT", msgStrArr)
                        );
                        dataErrors.add(errorVo);
                    }

                }
                // 넘어온 자재코드 값이 다른 교재/자재에서 이미 사용중인지 체크.
                // 다른 교재/제품에서 이미 사용 중인 SAP자재코드입니다. (사용 교재/제품코드: {0}/{1}) - 교재명/자재코드
                if (!"N".equals(
                    this.mapper.selectValidation(
                        ZpdcMaterialMgtDto.ValidationReq.builder()
                            .validationType(PdProductConst.SAP_MAT_CD).pdCd(null).sapMatCd(compareValue).build()
                    )
                )) {
                    msgStrArr[0] = compareValue;
                    ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
                    errorVo.setHeaderName(metaVo.getPrpNm());
                    errorVo.setErrorRow(rowIndex);
                    errorVo.setErrorData(
                        messageResourceService.getMessage("MSG_ALT_EXIST_SAP_MAT_CD", msgStrArr)
                    );
                }
            }

        }

        // #1.Essential Value - 필수값이 누락되어 있습니다.
        if (PdProductConst.MNDT_Y.equals(metaVo.getMndtYn()) && "".equals(compareValue)) {
            ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
            errorVo.setHeaderName(metaVo.getPrpNm());
            errorVo.setErrorRow(rowIndex);
            errorVo.setErrorData(messageResourceService.getMessage("MSG_ALT_EMPTY_REQUIRED_VAL"));
            dataErrors.add(errorVo);
        }

        // #2. Number Type - 숫자만 입력 가능합니다.
        if (PdProductConst.DTA_TP_NUMBER.equals(metaVo.getDtaTpCd())
            && !compareValue.matches("[-+]?\\d*\\.?\\d+|")) {
            ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
            errorVo.setHeaderName(metaVo.getPrpNm());
            errorVo.setErrorRow(rowIndex);
            errorVo.setErrorData(messageResourceService.getMessage("MSG_ALT_ONLY_NUMBER"));
            dataErrors.add(errorVo);
        }

        // #3. Date Format Check
        if (PdProductConst.DTA_TP_DATE.equals(metaVo.getDtaTpCd())
            && !compareValue.matches("^[\\d]{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$")) {
            ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
            errorVo.setHeaderName(metaVo.getPrpNm());
            errorVo.setErrorRow(rowIndex);
            errorVo.setErrorData(messageResourceService.getMessage("MSG_ALT_CHK_DT"));
            dataErrors.add(errorVo);
        }

        // #4. Length Check - 입력 가능 길이를 초과하였습니다 (최대: {0}, 입력: {1})
        if (null != metaVo.getDtaLnth() && null != entry.getValue()
            && metaVo.getDtaLnth().intValue() < compareValue.length()) {
            String[] lengthMsgStrArr = new String[2];
            lengthMsgStrArr[0] = metaVo.getDtaLnth().toString();
            lengthMsgStrArr[1] = compareValue;

            ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
            errorVo.setHeaderName(metaVo.getPrpNm());
            errorVo.setErrorRow(rowIndex);
            errorVo.setErrorData(
                messageResourceService.getMessage("MSG_ALT_INPUT_OVER_LEN", lengthMsgStrArr)
            );
            dataErrors.add(errorVo);
        }

    }

    /**
     * Excel Data를 DB에 저장.
     * 코드값은 Excel Dropdown으로 'CODE_NM|CODE_CD'로 입력받는다는 대전제.
     * @param excelData 엑셀데이터
     * @param metaItems 상품 Meta 테이블정보
     * @param tbPdbsPdBas 상품 Meta Master 정보
     * @param tbPdbsPdEcomPrpDtl 상품 각사속성 정보
     * @param prgGrpDves 상품 Meta에 등록된 상품속성그룹구분코드
     * @throws Exception 모든오류
     */
    @Transactional
    public void saveExcelUpload(
        List<Map<String, Object>> excelData,
        List<ZpdcPropertyMetaDvo> metaItems,
        List<ZpdcPropertyMetaDvo> tbPdbsPdBas,
        List<ZpdcPropertyMetaDvo> tbPdbsPdEcomPrpDtl,
        List<ZpdcPropertyMetaDvo> tbPdbsPdDtl,
        ArrayList<String> prgGrpDves
    ) throws Exception {

        String startDtm = DateUtil.getDate(new Date());
        // 단계그룹구분코드(공통 코드값), 예외적으로 해당 컬럼만 CODE_NM으로 받고 JAVA에서 mapping처리.
        List<CodeComponent> lrnnLvGrpDvCds = codeService.getCodesByCodeId(PdProductConst.LRNN_LV_GRP_DV_CD, null);

        for (Map<String, Object> excelDataMap : excelData) {

            Map<String, Object> masterMap = new HashMap<String, Object>();
            for (Entry<String, Object> entry : excelDataMap.entrySet()) {
                for (ZpdcPropertyMetaDvo metaVo : tbPdbsPdBas) {
                    if (entry.getKey().equals(metaVo.getColNm())) {
                        if (StringUtil.nvl(entry.getValue(), "").split("\\|").length > 1) {
                            String tempVal[] = entry.getValue().toString().split("\\|");
                            log.debug(metaVo.getColId() + " && " + tempVal[0].trim() + " && " + tempVal[1].trim());
                            masterMap.put(metaVo.getColId(), tempVal[1].trim());
                        } else {
                            masterMap.put(metaVo.getColId(), StringUtil.nvl(entry.getValue(), "").trim());
                        }
                    }
                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ZpdcProductDvo dvo = objectMapper.convertValue(masterMap, ZpdcProductDvo.class);

            // 230303 자재코드가 들어오면 UI와 동일하게 자동으로 Fill-In
            boolean isResetSapMaterial = false;
            if (StringUtil.isNotEmpty(dvo.getSapMatCd())) {

                String sapPlntVal = this.getExcelValue(dvo.getSapPlntCd());
                List<ZpdcGbcoSapMatDvo> sapMatVoList = mapper.selectMaterialSaps(dvo.getSapMatCd(), sapPlntVal);

                if (sapMatVoList.size() == 1) {
                    // dvo.setModelNo(sapMatVo.getModelNo());
                    dvo.setSapPdctSclsrtStrcVal(sapMatVoList.get(0).getSapPdctSclsrtStrcVal());
                    dvo.setSapPlntCd(sapMatVoList.get(0).getSapPlntVal());
                    dvo.setSapMatEvlClssVal(sapMatVoList.get(0).getSapMatEvlClssVal());
                    dvo.setSapMatGrpVal(sapMatVoList.get(0).getSapMatGrpVal());
                    dvo.setSapMatTpVal(sapMatVoList.get(0).getSapMatTpVal());
                } else {
                    isResetSapMaterial = true;
                }
            } else {
                isResetSapMaterial = true;
            }

            if (isResetSapMaterial) {
                //                dvo.setModelNo(null);
                dvo.setSapPdctSclsrtStrcVal(null);
                dvo.setSapPlntCd(null);
                dvo.setSapMatEvlClssVal(null);
                dvo.setSapMatGrpVal(null);
                dvo.setSapMatTpVal(null);
            }

            // #1. 상품 마스터 INSERT
            dvo.setPdTpCd(PdProductConst.PD_TP_CD_MATERIAL);
            dvo.setPdTpDtlCd(PdProductConst.PD_TP_DTL_CD_M);
            dvo.setTempSaveYn(PdProductConst.TEMP_SAVE_N);
            dvo = productService.saveProductBase(dvo, startDtm);

            // ---------------------------------------------------------------------------------------------------------
            // 제품 상세
            Map<String, Object> masterMap2 = new HashMap<String, Object>();
            for (Entry<String, Object> entry : excelDataMap.entrySet()) {
                for (ZpdcPropertyMetaDvo metaVo : tbPdbsPdDtl) {
                    if (entry.getKey().equals(metaVo.getColNm())) {
                        if (StringUtil.nvl(entry.getValue(), "").split("\\|").length > 1) {
                            String tempVal[] = entry.getValue().toString().split("\\|");
                            log.debug(metaVo.getColId() + " && " + tempVal[0].trim() + " && " + tempVal[1].trim());
                            masterMap2.put(metaVo.getColId(), tempVal[1].trim());
                        } else {
                            masterMap2.put(metaVo.getColId(), StringUtil.nvl(entry.getValue(), "").trim());
                        }
                    }
                }
            }

            objectMapper = new ObjectMapper();
            ZpdcProductDetailDvo propertyVo2 = objectMapper.convertValue(masterMap2, ZpdcProductDetailDvo.class);
            propertyVo2.setPdDtlDvCd("02"); // 식재 코드값(추후 값 갯수 늘어날때 살펴봐야할 Point)
            productService.saveProductDetail(dvo.getPdCd(), startDtm, propertyVo2);
            // ---------------------------------------------------------------------------------------------------------

            /**
             * 각사 속성의 경우
             * TB_PDBS_PD_PRP_META_BAS.PD_PRP_GRP_DV_CD(=상품속성그룹구분코드) Lv INSERT
             */
            for (String pdPrpGrpDtlDvCd : prgGrpDves) {
                StringBuffer colsSb = new StringBuffer();
                List<ZpdcPropertyMetaDvo> eachPdPrpGrpDtlDvCd = tbPdbsPdEcomPrpDtl.stream()
                    .filter(x -> pdPrpGrpDtlDvCd.equals(x.getPdPrpGrpDtlDvCd())).toList();

                Map<String, Object> propertyMap = new HashMap<String, Object>();
                for (Entry<String, Object> entry : excelDataMap.entrySet()) {
                    for (ZpdcPropertyMetaDvo metaVo : eachPdPrpGrpDtlDvCd) {

                        propertyMap.put("pdExtsPrpGrpCd", pdPrpGrpDtlDvCd);
                        if (entry.getKey().equals(metaVo.getColNm())) {
                            if (StringUtil.nvl(entry.getValue(), "").split("\\|").length > 1) {
                                String tempVal[] = entry.getValue().toString().split("\\|");
                                propertyMap.put(metaVo.getColId(), tempVal[1].trim());
                                colsSb.append(metaVo.getColId()).append(",");
                            } else {
                                // 단계그룹구분코드(LRNN_LV_GRP_CD) 예외케이스
                                // 해당 값은 Text로 받아와 DB INSERT 할때 Code 값으로 치환.
                                if (PdProductConst.PD_EXTS_PRP_GRP_CD_LRNN.equals(pdPrpGrpDtlDvCd)
                                    && PdProductConst.CARMEL_LRNN_LV_CD.equals(metaVo.getColNm())) {

                                    for (CodeComponent codeVo : lrnnLvGrpDvCds) {
                                        if (entry.getValue().equals(codeVo.codeName())) {
                                            propertyMap.put(metaVo.getColId(), codeVo.codeId());
                                            colsSb.append(metaVo.getColId()).append(",");
                                        }
                                    }

                                } else {
                                    propertyMap.put(metaVo.getColId(), entry.getValue());
                                    colsSb.append(metaVo.getColId()).append(",");
                                }

                            }
                        }

                    }

                }
                propertyMap.put("cols", colsSb.toString());

                objectMapper = new ObjectMapper();
                ZpdcEachCompanyPropDtlDvo propertyVo = objectMapper
                    .convertValue(propertyMap, ZpdcEachCompanyPropDtlDvo.class);

                propertyVo.setPdCd(dvo.getPdCd());
                if (null != propertyVo.getPdExtsPrpGrpCd()) {
                    productService.saveEachCompanyPropDtl(propertyVo);
                }

            }

            // #3. 이력 INSERT
            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }

    }

    public String getExcelValue(Object obj) {
        String compareValue = StringUtil.nvl2(obj.toString(), "");
        if (compareValue.split("\\|").length > 1) {
            compareValue = compareValue.split("\\|")[1].trim();
        }
        return compareValue;
    }

}
