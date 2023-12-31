package com.kyowon.sms.wells.web.customer.prospective.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.AssignReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.ContactReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.PartnerRes;
import com.kyowon.sms.wells.web.customer.prospective.service.WcsbNewReceiptMgtService;
import com.kyowon.sms.wells.web.customer.zcommon.constants.CsCustomerConst;
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
 * 고객 >>  신규접수 배정관리 Controller
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@RestController
@Api(tags = "[WCSB] 고객 >> 가망고객관리 >> 신규접수 배정관리")
@RequestMapping(value = CsCustomerConst.REST_URL_V1 + "/receipts")
@RequiredArgsConstructor
@Validated
public class WcsbNewReceiptMgtController {
    private final WcsbNewReceiptMgtService service;

    /*
     * ---------------------------------------
     *       접수조회 (TAB)   - Receipt
     * ---------------------------------------
     */

    /**
     * 신규접수 배정 접수목록 조회
     * @param dto 검색조건
     * @param pageInfo 페이징정보
     * @return 신규접수 배정 접수목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "assignDtFrom", value = "접수기간시작일", paramType = "query", required = true, example = "20220501"),
        @ApiImplicitParam(name = "assignDtTo", value = "접수기간종료일", paramType = "query", required = true, example = "20220530"),
        @ApiImplicitParam(name = "sellInflwchnlDvCd", value = "접수구분", paramType = "query", required = true, example = "01"),
        @ApiImplicitParam(name = "prdtType", value = "상품구분", paramType = "query", required = true, example = "01"),
    })
    @ApiOperation(value = "접수 목록 페이징 조회", notes = "검색조건을 입력 받아 Paging된 접수 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WcsbNewReceiptMgtDto.SearchRes> getReceiptPages(
        WcsbNewReceiptMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getReceiptPages(dto, pageInfo);
    }

    /**
     * 신규접수 배정 접수목록 조회
     * @param dto 검색조건
     * @return 신규접수 배정 접수목록
     */
    @ApiOperation(value = "접수 목록 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 접수 목록을 조회한다.")
    @GetMapping({"/excel-download"})
    public List<WcsbNewReceiptMgtDto.SearchRes> getReceiptsForExcelDownload(
        WcsbNewReceiptMgtDto.SearchReq dto
    ) {
        return this.service.getReceiptsForExcelDownload(dto);
    }

    /**
     * 신규접수 배정 단건조회
     * @param pspcCstCnslId 가망고객상담ID
     * @param cntrNo 계약번호
     * @return 신규접수 배정 정보
     * @throws Exception 오류정보
     */
    @ApiOperation(value = "배정/접수 (단건)상세 조회", notes = "배정/접수 상세 데이터를 조회한다.")
    @GetMapping("/assign/{pspcCstCnslId}/{cntrNo}")
    public WcsbNewReceiptMgtDto.SearchDtlRes getPspcCstCnslAssign(
        @Valid
        @RequestBody
        @PathVariable
        String pspcCstCnslId,
        @PathVariable
        String cntrNo
    ) throws Exception {
        return service.getPspcCstCnslAssign(pspcCstCnslId, cntrNo);
    }

    /**
     * 담당자 정보 조회
     * @param prtnrNo  파트너번호
     * @return 담당자 정보
     * @throws Exception 오류정보
     */
    @ApiOperation(value = "담당자 수동배정 조회", notes = "담당자 수동배정을 위해 사번을 입력받아 담당자 정보를 조회한다.")
    @GetMapping("/partner/{prtnrNo}")
    public PartnerRes getPartnerInfoByPrtnrNo(
        @Valid
        @RequestBody
        @PathVariable
        String prtnrNo
    ) throws Exception {
        return service.getPartnerInfoByPrtnrNo(prtnrNo);
    }

    /**
     * 신규접수 배정 - 담당자 수동 배정
     * @param dto 신규접수 배정을 위한 배정 정보
     * @return 성공여부
     * @throws Exception 오류정보
     */
    @ApiOperation(value = "담당자 수동배정 저장", notes = "담당자 수동배정에서 변경된 담당자로 수정한다.  ")
    @PutMapping("/assign")
    public SaveResponse editPspcCstCnslAssign(
        @Valid
        @RequestBody
        AssignReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.editPspcCstCnslAssign(dto))
            .build();
    }

    /**
     * 신규접수 배정 수정
     * @param dto 신규접수 배정 정보
     * @return 성공여부
     * @throws Exception 오류정보
     */
    @ApiOperation(value = "배정정보 수정", notes = "상세 화면에서 조회된 데이터를 수정한다.")
    @PutMapping("/contact")
    public SaveResponse editPspcCstCnslContact(
        @Valid
        @RequestBody
        ContactReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.editPspcCstCnslContact(dto))
            .build();
    }

    /*
     * ---------------------------------------
     *       배정조회 (TAB)   - Assign
     * ---------------------------------------
     */

    /**
     * 신규접수 배정 배정목록 조회
     * @param dto 검색조건
     * @param pageInfo 페이징정보
     * @return 신규접수 배정 배정목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "assignDtFrom", value = "접수기간시작일", paramType = "query", required = true, example = "20220501"),
        @ApiImplicitParam(name = "assignDtTo", value = "접수기간종료일", paramType = "query", required = true, example = "20220530"),
        @ApiImplicitParam(name = "ichrPrtnrNo", value = "배정담당자사번", paramType = "query", required = true, example = "123456"),
    })
    @ApiOperation(value = "접수 배정 목록 페이징 조회", notes = "검색조건을 입력 받아 Paging된 접수배정 목록을 조회한다.")
    @GetMapping("/assign/paging")
    public PagingResult<WcsbNewReceiptMgtDto.SearchDtlRes> getAssignPages(
        WcsbNewReceiptMgtDto.SearchAssignReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAssignPages(dto, pageInfo);
    }

    /**
     * 신규접수 배정 배정목록
     * @param dto 검색조건
     * @return 신규접수 배정 배정목록
     */
    @ApiOperation(value = "접수 배정 목록 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 접수배정 목록을 조회한다.")
    @GetMapping({"/assign/excel-download"})
    public List<WcsbNewReceiptMgtDto.SearchDtlRes> getAssignsForExcelDownload(
        WcsbNewReceiptMgtDto.SearchAssignReq dto
    ) {
        return this.service.getAssignsForExcelDownload(dto);
    }

    /*
     * ---------------------------------------
     *       집계 (TAB) - Summaries
     * ---------------------------------------
     */

    /**
     * 신규접수 배정 집계정보
     * @param dto 검색조건
     * @return 신규접수 배정 집계정보
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "assignDtFrom", value = "접수기간시작일", paramType = "query", required = true, example = "20220501"),
        @ApiImplicitParam(name = "assignDtTo", value = "접수기간종료일", paramType = "query", required = true, example = "20220530"),
        @ApiImplicitParam(name = "prdtType", value = "상품구분", paramType = "query", required = true, example = "01"),
    })
    @ApiOperation(value = "집계정보 조회", notes = "검색조건을 입력 받아 집계(통계) 목록을 조회한다.")
    @GetMapping("/summary")
    public List<WcsbNewReceiptMgtDto.SearchSummariesRes> getPspcCstCnslRecvSummaries(
        WcsbNewReceiptMgtDto.SearchSummariesReq dto
    ) {
        return service.getPspcCstCnslRecvSummaries(dto);
    }

}
