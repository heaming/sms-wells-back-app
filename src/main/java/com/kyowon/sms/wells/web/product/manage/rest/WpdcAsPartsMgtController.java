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
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.ValidationReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcPropertyMetaDvo;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.common.web.product.zcommon.utils.PdProductUtils;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartMgtDto;
import com.kyowon.sms.wells.web.product.manage.service.WpdcAsPartsMgtService;
import com.kyowon.sms.wells.web.product.manage.service.WpdcMaterialMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
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
 * 상품 >> AS부품 관리 Controller
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@RestController
@Api(tags = "[WPDC] 상품 >> 상품운영관리 >> AS부품관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/as-parts")
@RequiredArgsConstructor
@Validated
public class WpdcAsPartsMgtController {

    private final ZpdcProductService cmnService;
    private final WpdcAsPartsMgtService service;
    private final ExcelReadService excelReadService;

    private final ZpdcProductService pdService;
    private final WpdcMaterialMgtService wAsservice;
    private final MessageResourceService messageResourceService;

    /**
     * AS 부품관리 목록 조회
     * @param dto 검색조건
     * @param pageInfo 페이징정보
     * @return AS 부품관리 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdTpCd", value = "상품구분", paramType = "query", required = false, example = "S"),
        @ApiImplicitParam(name = "pdNm", value = "AS부품명", paramType = "query", required = false, example = "정수기"),
        @ApiImplicitParam(name = "pdCd", value = "AS부품코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prdtCateHigh", value = "대분류", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prdtCateMid", value = "중분류", paramType = "query", required = false, example = ""),
    })
    @ApiOperation(value = "AS부품 목록 페이징 조회", notes = "검색조건을 입력 받아 Paging된 AS부품 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WpdcAsPartMgtDto.SearchRes> getAsPartPages(
        WpdcAsPartMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAsPartPages(dto, pageInfo);
    }

    /**
     * AS 부품관리 목록 조회
     * @param dto 검색조건
     * @return AS 부품관리 목록
     */
    @ApiOperation(value = "AS부품 목록 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 AS부품 목록을 조회한다.")
    @GetMapping({"/excel-download"})
    public List<WpdcAsPartMgtDto.SearchRes> getAsPartsForExcelDownload(
        WpdcAsPartMgtDto.SearchReq dto
    ) {
        return this.service.getAsPartsForExcelDownload(dto);
    }

    /**
     * AS 부품관리 단건조회
     * @param pdCd 상품코드(PK)
     * @return AS 부품관리 정보
     * @throws Exception 오류정보
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "path", required = true, example = "WM04103177"),
    })
    @ApiOperation(value = "AS부품 단건 조회")
    @GetMapping("/{pdCd}")
    public WpdcAsPartMgtDto.ProductInfoRes getAsPartsInfo(@PathVariable
    String pdCd) throws Exception {
        ZpdcProductDto.TbPdbsPdBas pdBas = cmnService.getProductByPdCd(pdCd);
        return WpdcAsPartMgtDto.ProductInfoRes.builder()
            .tbPdbsPdBas(pdBas)
            .tbPdbsPdDtl(pdService.getProductDetailsByPdCd(pdCd))
            .tbPdbsPdEcomPrpDtl(cmnService.getEachCompanyProps(pdCd))
            .groupCodes(cmnService.getPropertyGroupCodes(pdBas.pdTpCd(), "", null))
            .build();
    }

    /**
     * AS 부품관리 정보 저장
     * @param dto AS 부품관리 정보
     * @return AS 부품관리 정보
     * @throws Exception 오류정보
     */
    @ApiOperation(value = "AS부품 저장 및 임시저장", notes = "최초 신규 생성 또는 최초 임시저장한 AS부품 정보를 반영한다.")
    @PostMapping
    public SaveResponse createAsParts(
        @Valid
        @RequestBody
        WpdcAsPartMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.saveAsParts(dto))
            .build();
    }

    /**
     * AS 부품관리 정보 수정
     * @param dto AS 부품관리 정보
     * @return AS 부품관리 정보
     * @throws Exception 오류정보
     */
    @ApiOperation(value = "AS부품 수정", notes = "수정된 AS부품 정보를 반영한다.")
    @PutMapping
    public SaveResponse editAsParts(
        @Valid
        @RequestBody
        WpdcAsPartMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.editAsParts(dto))
            .build();
    }

    /**
     * AS 부품관리 정보 삭제
     * @param pdCd 상품코드(PK)
     * @return 성공여부
     * @throws Exception 오류정보
     */
    @ApiOperation(value = "AS부품 삭제")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "상품코드", value = "PD_CD", paramType = "path", required = true)
    })
    @DeleteMapping("/{pdCd}")
    public SaveResponse removeAsParts(@PathVariable
    String pdCd) throws Exception {
        return SaveResponse.builder().processCount(service.removeAsParts(pdCd)).build();
    }

    /**
     * AS 부품관리 정보 일과등록
     * @param file AS 부품관리 엑셀정보
     * @return AS 부품관리 업로드 정보
     * @throws Exception 오류정보
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
                    .pdTpDtlCd(PdProductConst.PD_TP_DTL_CD_P) /* AS 부품 */
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
        if (xlsList.size() >= 4) {
            /* -------------------------------------------------------------
                0번째 row에서 물리적 DB 컬럼명을 Camel 표기법으로 KEY 추출
             ------------------------------------------------------------- */
            Map<String, Object> headerKeyMap = xlsList.get(0);
            for (int ii = 0; ii < headerKeyMap.size(); ii++) {
                String key = (String)headerKeyMap.get(Integer.toString(ii));
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

        } else {

            List<ExcelUploadErrorDvo> headerErrors = new ArrayList<ExcelUploadErrorDvo>();
            ExcelUploadErrorDvo errorVo = new ExcelUploadErrorDvo();
            errorVo.setHeaderName("");
            errorVo.setErrorRow(0);
            errorVo.setErrorData(messageResourceService.getMessage("MSG_ALT_NOT_FOUND_XLS_DATA"));
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

            /* -------------------------------------------------------------
                1. 데이터에 대한 유효성 체크(Ex.데이터타입, 길이, 필수컬럼값 null 여부, etc...
                    1-1) Pass : 실제 Excel Upload 수행
                    1-2) Fail : 오류항목들 return
             ------------------------------------------------------------ */
            String uploadStatus = PdProductConst.EXCEL_UPLOAD_SUCCESS;

            // 각사속성 그룹코드
            ArrayList<String> prgGrpDves = new ArrayList<>();
            for (ZpdcPropertyMetaDvo vo : metaItems) {
                if (!prgGrpDves.contains(vo.getPdPrpGrpDtlDvCd())) {
                    prgGrpDves.add(vo.getPdPrpGrpDtlDvCd());
                }
            }

            // 대상(Target) 테이블별 추출대상 Column 선별.
            List<ZpdcPropertyMetaDvo> tbPdbsPdBas = metaItems.stream()
                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_BAS.equals(x.getTblId())).toList();
            List<ZpdcPropertyMetaDvo> tbPdbsPdEcomPrpDtl = metaItems.stream()
                .filter(x -> PdProductConst.TBL_TB_PDBS_PD_ECOM_PRP_DTL.equals(x.getTblId())).toList();

            // 유효성 체크(현재는 '교재/자재'와 동일한 유효성 체크 이므로 함께 사용. (추후 분기 필요시 처리필요)
            List<ExcelUploadErrorDvo> dataErrors = wAsservice
                .checkDataValidation(convertXlsList, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl);

            if (dataErrors.size() > 0) {
                uploadStatus = PdProductConst.EXCEL_UPLOAD_ERROR;
            } else {
                service.saveExcelUpload(convertXlsList, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl, null, prgGrpDves);
            }

            return ExcelUploadDto.UploadRes.builder()
                .status(uploadStatus)
                .excelData(convertXlsList)
                .errorInfo(dataErrors)
                .build();
        }

    }

    /**
     * AS 부품관리 유효성 체크
     * @param dto AS 부품관리 정보
     * @return AS 부품관리 유효성 체크 결과
     */
    @GetMapping("/check-validation")
    public String checkValidation(ValidationReq dto) {
        return this.service.checkValidation(dto);
    }
}
