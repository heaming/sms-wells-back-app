package com.kyowon.sms.wells.web.product.manage.service;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyowon.sms.common.web.product.category.service.ZpdaClassificationMgtService;
import com.kyowon.sms.common.web.product.manage.converter.ZpdcProductConverter;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.ValidationReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcEachCompanyPropDtlDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcGbcoSapMatDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcPropertyMetaDvo;
import com.kyowon.sms.common.web.product.manage.mapper.ZpdcProductMapper;
import com.kyowon.sms.common.web.product.manage.service.ZpdcHistoryMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartMgtDto;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcAsPartMgtMapper;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcMaterialMgtMapper;
import com.sds.sflex.common.common.dto.CodeDto.CodeComponent;
import com.sds.sflex.common.common.service.CodeService;
import com.sds.sflex.common.docs.dto.AttachFileDto.AttachFile;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 상품 >> AS부품 관리 Service
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@Service
@RequiredArgsConstructor
public class WpdcAsPartsMgtService {

    private final WpdcAsPartMgtMapper mapper;
    private final ZpdcProductConverter productConverter;
    private final ZpdaClassificationMgtService clsfService;
    private final ZpdcProductMapper productMapper;
    private final ZpdcProductService productService;
    private final ZpdcHistoryMgtService hisService;
    private final AttachFileService fileService;
    private final WpdcMaterialMgtMapper wMapper;
    private final CodeService codeService;

    /**
     * AS부품 목록 페이징 조회
     * @param dto 검색조건
     * @param pageInfo 페이징정보
     * @return AS부품 목록
     */
    public PagingResult<WpdcAsPartMgtDto.SearchRes> getAsPartPages(
        WpdcAsPartMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAsPartPages(dto, pageInfo);
    }

    /**
     * AS부품 목록 엑셀다운로드
     * @param dto 검색조건
     * @return AS부품 목록
     */
    public List<WpdcAsPartMgtDto.SearchRes> getAsPartsForExcelDownload(
        WpdcAsPartMgtDto.SearchReq dto
    ) {
        return this.mapper.selectAsPartPages(dto);
    }

    /**
     * AS 부품관리 정보 저장
     * @param dto AS 부품관리 정보
     * @return AS 부품관리
     * @throws Exception 오류정보
     */
    @Transactional
    public ZpdcProductDto.TbPdbsPdBas saveAsParts(WpdcAsPartMgtDto.SaveReq dto)
        throws Exception {

        String startDtm = DateUtil.getDate(new Date());

        int processCount = 0;
        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());

        // #1. 분류체계 분류 계층값 FILL-IN(순서변경불가.)
        dvo = clsfService.getClassifcationHierarchy(dvo);

        /*
         * #2. 상품 마스터 INSERT/UPDATE
         * 교재/자재 : PD_TP_DTL_M(제품) , AS부품: PD_TP_DTL_A (As)부품
         * 해당 값이 없으면 'AS-PART'에서 신규추가한 데이터이므로 INSERT
         *        있으면 '교재/자재'에서  생성된    데이터이므로 UPDATE
         */
        if (StringUtil.isEmpty(dvo.getPdTpDtlCd())) {
            dvo.setPdTpDtlCd(PdProductConst.PD_TP_DTL_CD_P);
            processCount = productMapper.insertProduct(dvo);
        } else {
            processCount = productMapper.updateProduct(dvo);
        }

        // #3-0 상세
        productService.saveProductDetail(dvo.getPdCd(), dto.tbPdbsPdDtl(), false, startDtm);

        // #3. 각사 속성 INSERT
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

        // #4. 이력 INSERT
        if (!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(dto.tbPdbsPdBas().tempSaveYn())) {
            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }

        return productConverter.mapProductDvoToPdBas(dvo);
    }

    /**
     * AS 부품관리 수정
     * @param dto AS 부품관리 정보
     * @return AS 부품관리
     * @throws Exception 오류정보
     */
    @Transactional
    public ZpdcProductDto.TbPdbsPdBas editAsParts(WpdcAsPartMgtDto.EditReq dto)
        throws Exception {

        int processCount = 0;
        String startDtm = DateUtil.getDate(new Date());

        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());
        dvo = clsfService.getClassifcationHierarchy(dvo);
        processCount = productMapper.updateProduct(dvo);

        // #3-0 상세
        productService.saveProductDetail(dvo.getPdCd(), dto.tbPdbsPdDtl(), false, startDtm);

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

        if (!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(dto.tbPdbsPdBas().tempSaveYn())) {

            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }
        return productConverter.mapProductDvoToPdBas(dvo);
    }

    /**
     * AS 부품관리 삭제
     * @param pdCd 상품코드(PK)
     * @return 성공여부
     * @throws Exception 오류정보
     */
    @Transactional
    public int removeAsParts(String pdCd) throws Exception {
        productMapper.deleteEachCompanyProps(pdCd, "");

        int processCount = productMapper.deleteProduct(pdCd);
        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(pdCd, startDtm);
        return processCount;
    }

    /**
     * Excel Data를 DB에 저장.
     * 코드값은 Excel Dropdown으로 'CODE_NM|CODE_CD'로 입력받는다는 대전제.
     * @param excelData 엑셀데이터
     * @param metaItems 상품 Meta 테이블정보
     * @param tbPdbsPdBas 상품 Meta Master 정보
     * @param tbPdbsPdEcomPrpDtl 상품 각사속성 정보
     * @param tbPdbsPdDtl 상품 상세
     * @param prgGrpDves 상품 Meta에 등록된 상품속성그룹구분코드
     * @throws Exception 오류정보
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
                            masterMap.put(metaVo.getColId(), tempVal[1].trim());
                        } else {
                            masterMap.put(metaVo.getColId(), StringUtil.nvl(entry.getValue(), ""));
                        }
                    }
                }
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ZpdcProductDvo dvo = objectMapper.convertValue(masterMap, ZpdcProductDvo.class);

            // 230303 자재코드가 들어오면 UI와 동일하게 자동으로 Fill-In
            boolean isResetSapMaterial = false;
            if (StringUtil.isNotEmpty(dvo.getSapMatCd())) {

                //                ZpdcGbcoSapMatDvo sapMatVo = wMapper.selectMaterialSap(dvo.getSapMatCd());
                String sapPlntVal = this.getExcelValue(dvo.getSapPlntCd());
                List<ZpdcGbcoSapMatDvo> sapMatVoList = wMapper.selectMaterialSaps(dvo.getSapMatCd(), sapPlntVal);

                if (sapMatVoList.size() == 1) {
                    //                dvo.setModelNo(sapMatVo.getModelNo());
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
            dvo.setPdTpDtlCd(PdProductConst.PD_TP_DTL_CD_P);
            dvo.setTempSaveYn(PdProductConst.TEMP_SAVE_N);
            dvo = productService.saveProductBase(dvo, startDtm);

            /**
             * 각사 속성의 경우
             * TB_PDBS_PD_PRP_META_BAS.PD_PRP_GRP_DV_CD(=상품속성그룹구분코드) Lv INSERT
             */
            for (String pdPrpGrpDtlDvCd : prgGrpDves) {
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
                            } else {
                                // 단계그룹구분코드(LRNN_LV_GRP_CD) 예외케이스
                                // 해당 값은 Text로 받아와 DB INSERT 할때 Code 값으로 치환.
                                if (PdProductConst.PD_EXTS_PRP_GRP_CD_LRNN.equals(pdPrpGrpDtlDvCd)
                                    && PdProductConst.CARMEL_LRNN_LV_CD.equals(metaVo.getColNm())) {

                                    for (CodeComponent codeVo : lrnnLvGrpDvCds) {
                                        if (StringUtil.nvl(entry.getValue(), "").equals(codeVo.codeName())) {
                                            propertyMap.put(metaVo.getColId(), codeVo.codeId());
                                        }
                                    }

                                } else {
                                    propertyMap.put(metaVo.getColId(), StringUtil.nvl(entry.getValue(), ""));
                                }

                            }
                        }

                    }

                }
                objectMapper = new ObjectMapper();
                ZpdcEachCompanyPropDtlDvo propertyVo = objectMapper
                    .convertValue(propertyMap, ZpdcEachCompanyPropDtlDvo.class);

                propertyVo.setPdCd(dvo.getPdCd());
                if (null != propertyVo.getPdExtsPrpGrpCd()) {
                    productService.saveEachCompanyPropDtl(propertyVo);
                    // sonarQube 대응. 불필요한 변수 초기화
                    // propertyMap = new HashMap<String, Object>();
                }

            }

            // #3. 이력 INSERT
            hisService.createProductHistory(dvo.getPdCd(), startDtm);
        }

    }

    /**
     * 유효성 체크 조회
     * @param dto 유효성 체크 대상정보
     * @return 유효성 체크 결과
     */
    public String checkValidation(ValidationReq dto) {
        return this.mapper.selectValidation(dto);
    }

    /**
     * 엑셀 Column 값에서 값 추출 공통함수
     * @param obj 엑셀 Column 값
     * @return 가공된 엑셀 Column 값
     */
    public String getExcelValue(Object obj) {
        String compareValue = StringUtil.nvl2(obj.toString(), "");
        if (compareValue.split("\\|").length > 1) {
            compareValue = compareValue.split("\\|")[1].trim();
        }
        return compareValue;
    }
}
