package com.kyowon.sms.wells.web.product.manage.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapRes;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcPropertyMetaDvo;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcRelationMgtService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.common.web.product.zcommon.utils.PdProductUtils;
import com.kyowon.sms.wells.web.product.manage.service.WpdcMaterialMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 상품 >> 교재/자재 WELLS Controller
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@RestController
@Api(tags = "[WPDC] 상품 >> 상품운영관리 >> 교재/자재관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/materials")
@RequiredArgsConstructor
@Validated
public class WpdcMaterialMgtController {

    private final ZpdcProductService cmnService;
    private final ZpdcRelationMgtService relationService;
    private final WpdcMaterialMgtService service;
    private final ZpdcProductService pdService;

    //    private final PdExcelReadService excelReadService;
    private final ExcelReadService excelReadService;
    private final MessageResourceService messageResourceService;

    /**
     * 교재/자재 단건 조회
     * @param pdCd 상품코드(PK)
     * @return 교재/자재 단건 정보
     * @throws Exception 모든오류
     */
    @ApiOperation(value = "교재/자재 단건 조회")
    @GetMapping("/{pdCd}")
    public ZpdcMaterialMgtDto.ProductInfoRes getMaterialInfo(@PathVariable
    String pdCd) throws Exception {
        ZpdcProductDto.TbPdbsPdBas pdBas = cmnService.getProductByPdCd(pdCd);
        return ZpdcMaterialMgtDto.ProductInfoRes.builder()
            .tbPdbsPdBas(pdBas)
            .tbPdbsPdDtl(pdService.getProductDetailsByPdCd(pdCd))
            .tbPdbsPdEcomPrpDtl(cmnService.getEachCompanyProps(pdCd))
            .groupCodes(cmnService.getPropertyGroupCodes(pdBas.pdTpCd(), "", null))
            .tbPdbsPdRel(relationService.getRelationProducts(pdCd, null))
            .build();
    }

    /**
     * 교재/자재 저장 및 임시저장
     * @param dto 교재/자재 정보
     * @return 교재/자재 정보
     * @throws Exception 모든오류
     */
    @ApiOperation(value = "교재/자재 저장 및 임시저장", notes = "최초 신규 생성 또는 최초 임시저장한 교재/자재 정보를 반영한다.")
    @PostMapping
    public SaveResponse createMaterial(
        @Valid
        @RequestBody
        ZpdcMaterialMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            //            .data(service.saveMaterial(dto))
            .data(
                service.saveMaterial(
                    ZpdcMaterialMgtDto.SaveReq.builder()
                        .pdCd(dto.pdCd())
                        .tbPdbsPdBas(dto.tbPdbsPdBas()) /* FRONT pdConst.js 동기화 */
                        .tbPdbsPdEcomPrpDtl(dto.tbPdbsPdEcomPrpDtl())
                        .tbPdbsPdDtl(dto.tbPdbsPdDtl()) // 제품 상세
                        .tbPdbsPdRel(dto.tbPdbsPdRel()) // 연결상품
                        .isModifiedProp(dto.isModifiedProp())
                        .isOnlyFileModified(dto.isOnlyFileModified())
                        .isModifiedRelation(dto.isModifiedRelation())
                        .build(),
                    false
                )
            )
            .build();
    }

    /**
     * 교재/자재 수정
     * @param dto 교재/자재 정보
     * @return 교재/자재 정보
     * @throws Exception 모든오류
     */
    @ApiOperation(value = "교재/자재 수정", notes = "수정된 교재/자재 정보를 반영한다.")
    @PutMapping
    public SaveResponse editMaterial(
        @Valid
        @RequestBody
        ZpdcMaterialMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            //            .data(service.editMaterial(dto))
            .data(
                service.editMaterial(
                    ZpdcMaterialMgtDto.EditReq.builder()
                        .pdCd(dto.pdCd())
                        .tbPdbsPdBas(dto.tbPdbsPdBas()) /* FRONT pdConst.js 동기화 */
                        .tbPdbsPdEcomPrpDtl(dto.tbPdbsPdEcomPrpDtl())
                        .tbPdbsPdDtl(dto.tbPdbsPdDtl())
                        .tbPdbsPdRel(dto.tbPdbsPdRel())
                        .isModifiedProp(dto.isModifiedProp())
                        .isOnlyFileModified(dto.isOnlyFileModified())
                        .isModifiedRelation(dto.isModifiedRelation())
                        .build(),
                    false
                )
            )
            .build();
    }

    /**
     * 교재/자재 삭제
     * @param pdCd 상품코드(PK)
     * @return 성공여부
     * @throws Exception 모든오류
     */
    @ApiOperation(value = "교재/자재 삭제")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "상품코드", value = "PD_CD", paramType = "path", required = true)
    })
    @DeleteMapping("/{pdCd}")
    public SaveResponse removeMaterial(@PathVariable
    String pdCd) throws Exception {
        return SaveResponse.builder().processCount(service.removeMaterial(pdCd)).build();
    }

    /**
     * SAP 교재/자재 페이징 조회(팝업)
     * @param dto 조회조건
     * @param pageInfo 페이징정보
     * @return SAP 교재/자재 목록정보
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchCond", value = "모델No 또는 자재코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "searchWord", value = "모델No 또는 자재코드명", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "SAP 교재/자재 페이징 조회(팝업)", notes = "검색조건을 입력받아 교재/자재 목록을 조회한다.")
    @GetMapping("/sap-material")
    public PagingResult<SearchSapRes> getMaterialSapPages(SearchSapReq dto, @Valid
    PageInfo pageInfo) {
        return service.getMaterialSapPages(dto, pageInfo);
    }

    /**
     * SAP 교재/자재 엑셀다운(팝업)
     * @param dto 조회조건
     * @return SAP 교재/자재 목록정보
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchCond", value = "모델No 또는 자재코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "searchWord", value = "모델No 또는 자재코드명", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "SAP 교재/자재 페이징 조회(팝업)", notes = "검색조건을 입력받아 교재/자재 목록을 조회한다.")
    @GetMapping("/sap-material/excel-download")
    public List<SearchSapRes> getMaterialSapForExcelDownload(SearchSapReq dto) {
        return service.getMaterialSapForExcelDownload(dto);
    }

    /**
     * 교재/자재 엑셀 업로드를 통한 일괄등록
     * @param file 일괄 업로드할 엑셀파일정보
     * @return 업로드 관련 정보
     * @throws Exception 모든오류
     */
    @ApiOperation(value = "교재/자재 엑셀 업로드를 통한 일괄등록")
    @PostMapping("/excel-upload")
    public UploadRes saveForDirectExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {

        // Excel 데이터
        List<Map<String, Object>> convertXlsList = new ArrayList<Map<String, Object>>();

        /* -------------------------------------------------------------
            Excel Column들의 실제 Physical Table 및 column 정보들을 가진 객체 조회
         ------------------------------------------------------------- */
        List<ZpdcPropertyMetaDvo> metaItems = cmnService
            .getPropertyMetas(
                ZpdcProductDto.SearchPropMetaReq.builder()
                    .pdTpCd(PdProductConst.PD_TP_CD_MATERIAL) /* 교재/자재 */
                    .pdTpDtlCd(PdProductConst.PD_TP_DTL_CD_M) /* 제품 */
                    .build(),
                true
            );

        /* -------------------------------------------------------------
            META DB에 반드시 들어가야할(=엑셀에서 반드시 입력받아야할) 입력필수 항목
         ------------------------------------------------------------- */
        List<ZpdcPropertyMetaDvo> mendatoryColumns = metaItems.stream()
            .filter(x -> PdProductConst.MNDT_Y.equals(x.getMndtYn())).toList();

        /* -------------------------------------------------------------
            암호화된 물리적 엑셀 파일 복호화 및 List<Map)으로 치환.
         ------------------------------------------------------------- */
        Map<String, String> headerTitle = Maps.newLinkedHashMap();
        List<Map<String, Object>> xlsList = excelReadService
            .readExcel(file, new ExcelMetaDvo(0, headerTitle));

        ArrayList<String> headerKeys = new ArrayList<String>();
        boolean isError = false;
        if (xlsList.size() >= 4) {
            /* -------------------------------------------------------------
                0번째 row에서 물리적 DB 컬럼명을 Camel 표기법으로 KEY 추출
             ------------------------------------------------------------- */
            Map<String, Object> headerKeyMap = xlsList.get(0);
            for (int ii = 0; ii < headerKeyMap.size(); ii++) {
                String key = (String)headerKeyMap.get(Integer.toString(ii));
                if (StringUtil.isNull(key)) {
                    isError = true;
                    break;
                }

                headerKeys.add(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key.trim()));
            }

            /* -------------------------------------------------------------
                실제 데이터 ROW Map의 KEY를 0번째에서 추출한 Camel 표기법으로 치환하여 신규 MAP return
             ------------------------------------------------------------- */
            Map<String, Object> convertKeyMap = null;
            for (int xx = 3; xx < xlsList.size(); xx++) {
                Map<String, Object> dataMap = xlsList.get(xx);
                convertKeyMap = new HashMap<String, Object>();

                for (int yy = 0; yy < headerKeys.size(); yy++) {
                    convertKeyMap.put(headerKeys.get(yy), dataMap.get(Integer.toString(yy)));
                }
                convertXlsList.add(convertKeyMap);
            }

        }
        if (isError || xlsList.size() < 4) {
            String msg = messageResourceService
                .getMessage(isError ? "MSG_ALT_WRONG_FILE_FORMAT" : "MSG_ALT_NOT_FOUND_XLS_DATA");
            List<ExcelUploadErrorDvo> headerErrors = new ArrayList<ExcelUploadErrorDvo>();
            ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
            errorVo.setHeaderName("");
            errorVo.setErrorRow(0);
            errorVo.setErrorData(msg);
            headerErrors.add(errorVo);

            return ExcelUploadDto.UploadRes.builder()
                .status(PdProductConst.EXCEL_UPLOAD_ERROR)
                .excelData(convertXlsList)
                .errorInfo(headerErrors)
                .build();
        }

        /* -------------------------------------------------------------
            필수입력받아야할 데이터 컬럼 존재유무 확인 및 없을시 바로 return
         ------------------------------------------------------------- */
        String validationMsg = messageResourceService.getMessage("MSG_ALT_NOT_EXIST_ESSENTIAL_COLUMN");
        List<ExcelUploadErrorDvo> headerErrors = PdProductUtils
            .checkHeaderValidation(mendatoryColumns, headerKeys, validationMsg);

        if (headerErrors.size() > 0) {
            // Not Null 컬럼 누락시 데이터 확인없이 Error Throw
            return ExcelUploadDto.UploadRes.builder()
                .status(PdProductConst.EXCEL_UPLOAD_ERROR)
                .excelData(convertXlsList)
                .errorInfo(headerErrors)
                .build();
        } else {
            // 사용자 입력데이터들에 대한 유효성 체크 및 DB 저장처리.
            String uploadStatus = PdProductConst.EXCEL_UPLOAD_SUCCESS;

            // 각사속성 그룹코드
            ArrayList<String> prgGrpDves = new ArrayList<>();
            for (ZpdcPropertyMetaDvo vo : metaItems) {
                if (!prgGrpDves.contains(vo.getPdPrpGrpDtlDvCd())) {
                    prgGrpDves.add(vo.getPdPrpGrpDtlDvCd());
                }
            }

            // 대상 테이블별 추출대상 Column 선별.
            List<ZpdcPropertyMetaDvo> tbPdbsPdBas = metaItems.stream()
                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_BAS.equals(x.getTblId())).toList();
            List<ZpdcPropertyMetaDvo> tbPdbsPdEcomPrpDtl = metaItems.stream()
                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_ECOM_PRP_DTL.equals(x.getTblId())).toList();
            List<ZpdcPropertyMetaDvo> tbPdbsPdDtl = metaItems.stream()
                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_DTL.equals(x.getTblId())).toList();

            // 유효성 체크
            List<ExcelUploadErrorDvo> dataErrors = service
                .checkDataValidation(convertXlsList, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl);

            if (dataErrors.size() > 0) {
                uploadStatus = PdProductConst.EXCEL_UPLOAD_ERROR;
            } else {
                service.saveExcelUpload(
                    convertXlsList, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl, tbPdbsPdDtl, prgGrpDves
                );
            }

            return ExcelUploadDto.UploadRes.builder()
                .status(uploadStatus)
                .excelData(convertXlsList)
                .errorInfo(dataErrors)
                .build();
        }

    }

    /**
     * 유효성 체크 정보
     * @param dto 체크할 유효성 관련 정보
     * @return 체크된 유효성 정보
     */
    //    @GetMapping("/check-validation")
    //    public String checkValidation(
    //        ValidationReq dto
    //    ) {
    //        return this.service.checkValidation(dto);
    //    }

    //    @ApiOperation(value = "교재/자재 엑셀 업로드를 통한 일괄등록")
    //    @PostMapping("/excel-upload")
    //    public UploadRes saveForDirectExcelUpload(
    //        @RequestParam("file")
    //        MultipartFile file
    //    ) throws Exception {
    //
    //        // Excel Column들의 실제 Physical Table 및 column 정보들을 가진 객체.
    //        List<ZpdcPropertyMetaDvo> metaItems = cmnService
    //            .getPropertyMetas(
    //                ZpdcProductDto.SearchPropMetaReq.builder()
    //                    .pdTpCd(PdProductConst.PD_TP_CD_MATERIAL) /* 교재/자재 */
    //                    .pdTpDtlCd(PdProductConst.PD_TP_DTL_CD_M) /* 제품 */
    //                    .build(),
    //                true
    //            );
    //
    //        // META DB에 반드시 들어가야할(=엑셀에서 반드시 입력받아야할) 입력필수 항목
    //        List<ZpdcPropertyMetaDvo> mendatoryColumns = metaItems.stream()
    //            .filter(x -> PdProductConst.MNDT_Y.equals(x.getMndtYn())).toList();
    //        List<ExcelUploadErrorDvo> headerErrors = excelReadService
    //            .checkHeaderValidation(file, mendatoryColumns, true);
    //
    //        // Excel Data Drm 해제 및 Data 파싱.
    //        List<Map<String, Object>> excelData = excelReadService.readExcel(file, true);
    //
    //        if (headerErrors.size() > 0) {
    //            // Not Null 컬럼 누락시 데이터 확인없이 Error Throw
    //            return ExcelUploadDto.UploadRes.builder()
    //                .status(PdProductConst.EXCEL_UPLOAD_ERROR)
    //                .excelData(excelData)
    //                .errorInfo(headerErrors)
    //                .build();
    //        } else {
    //            // 사용자 입력데이터들에 대한 유효성 체크 및 DB 저장처리.
    //            String uploadStatus = PdProductConst.EXCEL_UPLOAD_SUCCESS;
    //
    //            // 각사속성 그룹코드
    //            ArrayList<String> prgGrpDves = new ArrayList<>();
    //            for (ZpdcPropertyMetaDvo vo : metaItems) {
    //                if (!prgGrpDves.contains(vo.getPdPrpGrpDtlDvCd())) {
    //                    prgGrpDves.add(vo.getPdPrpGrpDtlDvCd());
    //                }
    //            }
    //
    //            // 대상 테이블별 추출대상 Column 선별.
    //            List<ZpdcPropertyMetaDvo> tbPdbsPdBas = metaItems.stream()
    //                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_BAS.equals(x.getTblId())).toList();
    //            List<ZpdcPropertyMetaDvo> tbPdbsPdEcomPrpDtl = metaItems.stream()
    //                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_ECOM_PRP_DTL.equals(x.getTblId())).toList();
    //            List<ZpdcPropertyMetaDvo> tbPdbsPdDtl = metaItems.stream()
    //                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_DTL.equals(x.getTblId())).toList();
    //
    //            // 유효성 체크
    //            List<ExcelUploadErrorDvo> dataErrors = service
    //                .checkDataValidation(excelData, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl);
    //
    //            if (dataErrors.size() > 0) {
    //                uploadStatus = PdProductConst.EXCEL_UPLOAD_ERROR;
    //            } else {
    //                service.saveExcelUpload(excelData, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl, tbPdbsPdDtl, prgGrpDves);
    //            }
    //
    //            return ExcelUploadDto.UploadRes.builder()
    //                .status(uploadStatus)
    //                .excelData(excelData)
    //                .errorInfo(dataErrors)
    //                .build();
    //        }
    //
    //    }

}
