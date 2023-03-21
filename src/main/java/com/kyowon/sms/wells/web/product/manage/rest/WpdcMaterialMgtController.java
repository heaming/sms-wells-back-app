package com.kyowon.sms.wells.web.product.manage.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapRes;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcPropertyMetaDvo;
import com.kyowon.sms.common.web.product.manage.service.PdExcelReadService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcRelationMgtService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.manage.service.WpdcMaterialMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "[WPDC] 상품 >> 상품운영관리 >> 교재/자재관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/materials")
@RequiredArgsConstructor
@Validated
public class WpdcMaterialMgtController {

    private final ZpdcProductService cmnService;
    private final ZpdcRelationMgtService relationService;
    private final WpdcMaterialMgtService service;

    private final PdExcelReadService excelReadService;

    @ApiOperation(value = "교재/자재 단건 조회")
    @GetMapping("/{pdCd}")
    public ZpdcMaterialMgtDto.ProductInfoRes getMaterialInfo(@PathVariable
    String pdCd) throws Exception {
        ZpdcProductDto.TbPdbsPdBas pdBas = cmnService.getProductByPdCd(pdCd);
        return ZpdcMaterialMgtDto.ProductInfoRes.builder()
            .tbPdbsPdBas(pdBas)
            .tbPdbsPdEcomPrpDtl(cmnService.getEachCompanyProps(pdCd))
            .groupCodes(cmnService.getPropertyGroupCodes(pdBas.pdTpCd(), "", null))
            .tbPdbsPdRel(relationService.getRelationProducts(pdCd, null))
            .build();
    }

    @ApiOperation(value = "교재/자재 저장 및 임시저장", notes = "최초 신규 생성 또는 최초 임시저장한 교재/자재 정보를 반영한다.")
    @PostMapping
    public SaveResponse createMaterial(
        @Valid
        @RequestBody
        ZpdcMaterialMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.saveMaterial(dto))
            .build();
    }

    @ApiOperation(value = "교재/자재 수정", notes = "수정된 교재/자재 정보를 반영한다.")
    @PutMapping
    public SaveResponse editMaterial(
        @Valid
        @RequestBody
        ZpdcMaterialMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .data(service.editMaterial(dto))
            .build();
    }

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
     * @param dto
     * @param pageInfo
     * @return
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

    @ApiOperation(value = "교재/자재 엑셀 업로드를 통한 일괄등록")
    @PostMapping("/excel-upload")
    public UploadRes saveForDirectExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {

        // Excel Column들의 실제 Physical Table 및 column 정보들을 가진 객체.
        List<ZpdcPropertyMetaDvo> metaItems = cmnService
            .getPropertyMetas(
                ZpdcProductDto.SearchPropMetaReq.builder().pdTpCd(PdProductConst.PD_TP_CD_MATERIAL).build(), true
            );

        // META DB에 반드시 들어가야할(=엑셀에서 반드시 입력받아야할) 입력필수 항목
        List<ZpdcPropertyMetaDvo> mendatoryColumns = metaItems.stream()
            .filter(x -> PdProductConst.MNDT_Y.equals(x.getMndtYn())).toList();
        List<ExcelUploadErrorDvo> headerErrors = excelReadService
            .readExcelHeader(file, mendatoryColumns);

        // Excel Data Drm 해제 및 Data 파싱.
        List<Map<String, Object>> excelData = excelReadService.readExcel(file);

        if (headerErrors.size() > 0) {
            // Not Null 컬럼 누락시 데이터 확인없이 Error Throw
            return ExcelUploadDto.UploadRes.builder()
                .status(PdProductConst.EXCEL_UPLOAD_ERROR)
                .excelData(excelData)
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

            // 유효성 체크
            List<ExcelUploadErrorDvo> dataErrors = service
                .checkValidationForExcelUpload(excelData, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl);

            if (dataErrors.size() > 0) {
                uploadStatus = PdProductConst.EXCEL_UPLOAD_ERROR;
            } else {
                service.saveExcelUpload(excelData, metaItems, tbPdbsPdBas, tbPdbsPdEcomPrpDtl, prgGrpDves);
            }

            return ExcelUploadDto.UploadRes.builder()
                .status(uploadStatus)
                .excelData(excelData)
                .errorInfo(dataErrors)
                .build();
        }

    }

}
