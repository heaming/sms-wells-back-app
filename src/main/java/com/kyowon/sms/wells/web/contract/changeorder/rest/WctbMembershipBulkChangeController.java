package com.kyowon.sms.wells.web.contract.changeorder.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbMembershipBulkChangeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "멤버십 일괄변경 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbMembershipBulkChangeController {
    private final WctbMembershipBulkChangeService service;

    @ApiOperation(value = "멤버십 일괄변경 관리", notes = "멤버십 주문건에 대한 일괄 변경 처리 및 조회하면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "rfdt", value = "반영일", paramType = "query"),
    })
    @GetMapping("/membership-bulk-change")
    public List<WctbMembershipBulkChangeDto.SearchRes> getMembershipBulkChanges(
        String cntrNo,
        String cntrSn,
        @Validated
        String rfdt
    ) {
        return service.getMembershipBulkChanges(cntrNo, cntrSn, rfdt);
    }
}
