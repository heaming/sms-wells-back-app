package com.kyowon.sms.wells.web.closing.sales.rest;

import com.kyowon.sms.wells.web.closing.sales.service.WdcbSalesInterfaceService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesInterfaceDto.SearchByContractReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesInterfaceDto.SearchAllianceContractRes;
import javax.validation.Valid;

@InterfaceController
@Api(tags = "[WDCB] Sales Interface")
@RequiredArgsConstructor
@RequestMapping(DcClosingConst.INTERFACE_URL_V1 + "/sales")
public class WdcbSalesInterfaceController {

    private final WdcbSalesInterfaceService service;

    @ApiOperation(value = "[W-CL-I-0001] WELLS 입금/환불 제휴 정보 조회", notes = "압력된 계약정보로 Wells 측으로 제공되는 라이프, K멤버스 제휴 등의 렌탈정보를 조회하는 인터페이스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false)
    })
    @PostMapping(value = "/alliance-contracts")
    public EaiWrapper getAllianceContract(
        @Valid
        @RequestBody
        final EaiWrapper<SearchByContractReq> reqEaiWrapper
    ) {
        final SearchAllianceContractRes res = service.getAllianceContract(reqEaiWrapper.getBody());
        final EaiWrapper<SearchAllianceContractRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(res);

        return resEaiWrapper;
    }

}
