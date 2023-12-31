package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncContractDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncContractDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncContractService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 채권상담 계약리스트")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/bond-counsel")
public class WbncContractController {

    private final WbncContractService service;

    @ApiOperation(value = "채권상담 계약리스트", notes = "검색 조건을 입력 받아 채권상담 계약리스트 정보를 조회 한다.")
    @GetMapping("contracts")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schClctamNo", value = "집금번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstNm", value = "고객명", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDlqMcntStrt", value = "form 연체개월", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDlqMcntEnd", value = "to 연체개월", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstLocaraTno", value = "설치전화번호1", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstExnoEncr", value = "설치전화번호2", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstIdvTno", value = "설치전화번호3", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCntrDtlNo", value = "계약상세번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstNo", value = "고객번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schBizDv", value = "업무구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstCralLocaraTno", value = "설치휴대전화번호1    ", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstMexnoEncr", value = "설치휴대전화번호2    ", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schIstCralIdvTno", value = "설치휴대전화번호3    ", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCntrLocaraTno", value = "전화번호1", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCntrExnoEncr", value = "전화번호2", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCntrIdvTno", value = "전화번호3", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schSfK", value = "세이프키", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstDv", value = "고객구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schOjBlamStrt", value = "form 대상잔액", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schOjBlamEnd", value = "to 대상잔액", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCntrCralLocaraTno", value = "휴대전화번호1", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCntrMexnoEncr", value = "휴대전화번호2", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCntrCralIdvTno", value = "휴대전화번호3", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schTfDtStrt ", value = "from 이관일자", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schTfDtEnd", value = "to 이관일자", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schFntDv ", value = "이체구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schFntDtStrt ", value = "from 이체일자", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schFntDtEnd", value = "to 이체일자", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schBilDv", value = "청구구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schCstThmDp", value = "고객당월입금", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schAuthRsgYn", value = "직권해지여부", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDv1", value = "구분1", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDv2", value = "구분2", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDv3", value = "구분3", paramType = "query", example = ""),
        @ApiImplicitParam(name = "schDv4", value = "구분4", paramType = "query", example = ""),
    })
    public List<SearchRes> getContracts(SearchReq dto) throws Exception {
        return service.getContracts(dto);
    }
}
