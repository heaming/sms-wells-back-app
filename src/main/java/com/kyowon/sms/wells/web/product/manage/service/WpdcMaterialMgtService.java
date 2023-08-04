package com.kyowon.sms.wells.web.product.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.ValidationReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcRelationMgtDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcEachCompanyPropDtlDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcGbcoSapMatDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcPropertyMetaDvo;
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
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchSapRes> getMaterialSapPages(SearchSapReq dto, PageInfo pageInfo) {
        return mapper.selectMaterialSapPages(dto, pageInfo);
    }

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
        this.editEachTbPdbsPdRel(dvo.getPdCd(), dto.tbPdbsPdRel(), dto.tbPdbsPdBas().tempSaveYn());

        // #6. 이력 INSERT
        if (!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(dto.tbPdbsPdBas().tempSaveYn())) {

            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }

        if (dto.isModifiedRelation()) {
            relService.saveProductRelations(dvo.getPdCd(), dto.tbPdbsPdRel(), startDtm);
            hisService.createRelationHistory(dvo.getPdCd(), startDtm);
        }

        return productConverter.mapProductDvoToPdBas(dvo);
    }

    /**
     * 연결상품
     * @param pdCd
     * @param tbPdbsPdRels
     * @throws Exception
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
                        .vlStrtDtm(startDtm)
                        .vlEndDtm(endDtm)
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

        this.editEachTbPdbsPdRel(dvo.getPdCd(), dto.tbPdbsPdRel(), dto.tbPdbsPdBas().tempSaveYn());

        if (!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(dto.tbPdbsPdBas().tempSaveYn())) {

            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }
        return productConverter.mapProductDvoToPdBas(dvo);
    }

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
     * @param excelData
     * @param metaItems
     * @param tbPdbsPdBas
     * @param tbPdbsPdEcomPrpDtl
     * @return
     * @throws Exception
     */
    public List<ExcelUploadErrorDvo> checkDataValidation(
        List<Map<String, Object>> excelData,
        List<ZpdcPropertyMetaDvo> metaItems,
        List<ZpdcPropertyMetaDvo> tbPdbsPdBas,
        List<ZpdcPropertyMetaDvo> tbPdbsPdEcomPrpDtl
    ) throws Exception {

        List<ExcelUploadErrorDvo> dataErrors = new ArrayList<ExcelUploadErrorDvo>();

        int rowIndex = 1;
        for (Map<String, Object> excelDataMap : excelData) {

            for (Entry<String, Object> entry : excelDataMap.entrySet()) {
                for (ZpdcPropertyMetaDvo metaVo : tbPdbsPdBas) {

                    if (entry.getKey().equals(PdProductConst.SAP_MAT_CD)
                        && PdProductConst.SAP_MAT_CD.equals(metaVo.getColNm())) {
                        this.checkExcelValidation(
                            entry, metaVo, rowIndex, dataErrors, PdProductConst.VALIDATION_TARGET_DB
                        );
                    } else if (entry.getKey().equals(metaVo.getColNm())) {
                        this.checkExcelValidation(
                            entry, metaVo, rowIndex, dataErrors, PdProductConst.VALIDATION_TARGET_META
                        );
                    }
                }

                for (ZpdcPropertyMetaDvo metaVo : tbPdbsPdEcomPrpDtl) {
                    if (entry.getKey().equals(metaVo.getColNm())) {
                        this.checkExcelValidation(
                            entry, metaVo, rowIndex, dataErrors, PdProductConst.VALIDATION_TARGET_META
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
        String validationTarget
    ) {

        String compareValue = StringUtil.nvl2(entry.getValue().toString(), "");
        if (compareValue.split("\\|").length > 1) {
            compareValue = compareValue.split("\\|")[1].trim();
        }

        if (PdProductConst.VALIDATION_TARGET_DB.equals(validationTarget)) {

            if (PdProductConst.SAP_MAT_CD.equals(metaVo.getColNm())) {
                // DB에 실재하는 유효값인지 체크.
                // 자재코드값({0})이 올바르지 않습니다.
                if (!"".equals(compareValue)) {
                    // 넘어온 자재코드 값이 I/F 테이블에 존재하는지 확인.
                    //                    ZpdcGbcoSapMatDvo sapMatVo = mapper.selectMaterialSap(compareValue);
                    List<ZpdcGbcoSapMatDvo> sapMatVos = mapper.selectMaterialSaps(compareValue);

                    if (sapMatVos.isEmpty()) {
                        ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
                        errorVo.setHeaderName(metaVo.getPrpNm());
                        errorVo.setErrorRow(rowIndex);
                        errorVo.setErrorData(
                            messageResourceService
                                .getMessage("MSG_ALT_ABNORMAL_SAP_MAT_CD", new String[] {compareValue})
                        );
                        dataErrors.add(errorVo);
                    } else if (sapMatVos.size() > 1) {
                        ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
                        errorVo.setHeaderName(metaVo.getPrpNm());
                        errorVo.setErrorRow(rowIndex);
                        errorVo.setErrorData(
                            // {0} 결과값이 2건 이상 존재합니다.
                            messageResourceService
                                .getMessage(
                                    "MSG_ALT_ABNORMAL_TO_MUCH_RESULT",
                                    new String[] {messageResourceService.getMessage("MSG_TXT_MATI_CD")}
                                )
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
                    ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
                    errorVo.setHeaderName(metaVo.getPrpNm());
                    errorVo.setErrorRow(rowIndex);
                    errorVo.setErrorData(
                        messageResourceService.getMessage("MSG_ALT_EXIST_SAP_MAT_CD", new String[] {compareValue})
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

        // #3. Length Check - 입력 가능 길이를 초과하였습니다 (최대: {0}, 입력: {1})
        if (null != metaVo.getDtaLnth() && null != entry.getValue()
            && metaVo.getDtaLnth().intValue() < compareValue.length()) {
            ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
            errorVo.setHeaderName(metaVo.getPrpNm());
            errorVo.setErrorRow(rowIndex);
            errorVo.setErrorData(
                messageResourceService.getMessage(
                    "MSG_ALT_INPUT_OVER_LEN",
                    new String[] {metaVo.getDtaLnth() + "", compareValue.length() + ""}
                )
            );
            dataErrors.add(errorVo);
        }

    }

    /**
     * Excel Data를 DB에 저장.
     * 코드값은 Excel Dropdown으로 'CODE_NM|CODE_CD'로 입력받는다는 대전제.
     * @param excelData
     * @param metaItems
     * @param tbPdbsPdBas
     * @param tbPdbsPdEcomPrpDtl
     * @param prgGrpDves
     * @throws Exception
     */
    @Transactional
    public void saveExcelUpload(
        List<Map<String, Object>> excelData,
        List<ZpdcPropertyMetaDvo> metaItems,
        List<ZpdcPropertyMetaDvo> tbPdbsPdBas,
        List<ZpdcPropertyMetaDvo> tbPdbsPdEcomPrpDtl,
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
                        if (entry.getValue().toString().split("\\|").length > 1) {
                            String tempVal[] = entry.getValue().toString().split("\\|");
                            log.debug(metaVo.getColId() + " && " + tempVal[0].trim() + " && " + tempVal[1].trim());
                            masterMap.put(metaVo.getColId(), tempVal[1].trim());
                        } else {
                            masterMap.put(metaVo.getColId(), entry.getValue().toString().trim());
                        }
                    }
                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ZpdcProductDvo dvo = objectMapper.convertValue(masterMap, ZpdcProductDvo.class);

            // 230303 자재코드가 들어오면 UI와 동일하게 자동으로 Fill-In
            if (StringUtil.isNotEmpty(dvo.getSapMatCd())) {
                // TODO 조회쿼리 한방 날리고 값들 채우고!!! 여기부터 시작!!!!
                ZpdcGbcoSapMatDvo sapMatVo = mapper.selectMaterialSap(dvo.getSapMatCd());

                dvo.setModelNo(sapMatVo.getModelNo());
                dvo.setSapPdctSclsrtStrcVal(sapMatVo.getSapPdctSclsrtStrcVal());
                dvo.setSapPlntCd(sapMatVo.getSapPlntVal());
                dvo.setSapMatEvlClssVal(sapMatVo.getSapMatEvlClssVal());
                dvo.setSapMatGrpVal(sapMatVo.getSapMatGrpVal());
            } else {
                dvo.setModelNo(null);
                dvo.setSapPdctSclsrtStrcVal(null);
                dvo.setSapPlntCd(null);
                dvo.setSapMatEvlClssVal(null);
                dvo.setSapMatGrpVal(null);
            }

            // #1. 상품 마스터 INSERT
            dvo.setPdTpCd(PdProductConst.PD_TP_CD_MATERIAL);
            dvo.setPdTpDtlCd(PdProductConst.PD_TP_DTL_CD_M);
            dvo.setTempSaveYn(PdProductConst.TEMP_SAVE_N);
            dvo = productService.saveProductBase(dvo, startDtm);

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
                            if (entry.getValue().toString().split("\\|").length > 1) {
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
                    propertyMap = new HashMap<String, Object>();
                }

            }

            // #3. 이력 INSERT
            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }

    }

    /**
     * 유효성 체크 조회
     * @param dto
     * @return
     */
    public String checkValidation(ValidationReq dto) {
        return this.mapper.selectValidation(dto);
    }

}
