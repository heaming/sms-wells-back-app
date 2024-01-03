package com.kyowon.sms.wells.web.withdrawal.bilfnt.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SearchAutoFntDsnWdrwCstReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SearchAutoFntDsnWdrwCstRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.service.WwdaDesignationWithdrawalCustomerMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WdWithdrawalConst.REST_URL_V1 + "/bilfnt")
@Api(tags = "[WWDA] 자동이체 지정 출금 고객 관리")
public class WwdaDesignationWithdrawalCustomerMgtController {

    private final WwdaDesignationWithdrawalCustomerMgtService service;

    /**
     * 자동이체 지정 출금 고객 조회 / 페이징
     * @param req
     * @param pageInfo 페이징
     * @return PagingResult<SearchAutoFntDsnWdrwCstRes>
     */
    @ApiOperation(value = "자동이체 지정 출금 고객 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "W20220086004"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/designation-wdrw-csts")
    public PagingResult<SearchAutoFntDsnWdrwCstRes> getAftnDsnWdrwCstInqrPages(
        @Valid
        SearchAutoFntDsnWdrwCstReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAftnDsnWdrwCstInqrPages(req, pageInfo);
    }

    /**
     * 자동이체 지정 출금 고객 엑셀다운로드
     * @param req
     * @return List<SearchAutoFntDsnWdrwCstRes>
     */
    @ApiOperation(value = "자동이체 지정 출금 고객 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "W20220086004"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/designation-wdrw-csts/excel-download")
    public List<SearchAutoFntDsnWdrwCstRes> getAftnDsnWdrwCstInqrExcels(
        @Valid
        SearchAutoFntDsnWdrwCstReq req
    ) {
        return service.getAftnDsnWdrwCstInqrExcels(req);
    }

    /**
     * 자동이체 지정 출금 고객 저장
     * @param req
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "자동이체 지정 출금 고객 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rowState", value = "행 상태", paramType = "query", required = false, example = "created"),
        @ApiImplicitParam(name = "dataRow", value = "행 번호", paramType = "query", required = false, example = "0"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "W20220086004"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "dsnWdrwAmt", value = "지정금액", paramType = "query", required = false, example = "99999"),
        @ApiImplicitParam(name = "dpAmt", value = "입금금액", paramType = "query", required = false, example = "900"),
        @ApiImplicitParam(name = "fntYn", value = "이체구분", paramType = "query", required = false, example = "Y"),
        @ApiImplicitParam(name = "dsnWdrwFntD", value = "이체일자", paramType = "query", required = false, example = "10"),
        @ApiImplicitParam(name = "dsnWdrwFntPrdCd", value = "이체주기", paramType = "query", required = false, example = "2"),
    })
    @PostMapping("/designation-wdrw-csts")
    public SaveResponse saveAutoFntDsnWdrwCst(
        @RequestBody @Valid @NotEmpty
        List<SaveReq> req
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAutoFntDsnWdrwCst(req))
            .build();
    }

    /**
     * 자동이체 지정 출금 고객 삭제
     * @param req
     * @return SaveResponse
     */
    @ApiOperation(value = "자동이체 지정 출금 고객 삭제")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "W20220086004"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false, example = "1"),
    })
    @DeleteMapping("/designation-wdrw-csts")
    public SaveResponse deleteAutoFntDsnWdrwCst(
        @RequestBody @Valid @NotEmpty
        List<RemoveReq> req
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.deleteAutoFntDsnWdrwCst(req))
            .build();
    }
}
