package com.kyowon.sms.wells.web.contract.interfaces.rest;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.service.WctiCustomerService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import com.sds.sflex.system.config.webclient.ivo.Header;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 고객센터I/F")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/customer-centers")
@RequiredArgsConstructor
@Validated
public class WctiCustomerController {

    private final WctiCustomerService service;

    @ApiOperation(value = "[EAI_WCUI1010] 고객 통합 리스트 조회", notes = "입력받은 고객명, 휴대전화번호 고객번호를 조건으로 계약, 가망고객, 무료체험 고객에 해당하는 고객정보를 조회")
    @PostMapping("/customer-lists")
    public EaiWrapper getCustomers(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        try {
            List<SearchRes> res = service.getCustomers(reqWrapper.getBody());
            // Response Body 세팅
            resWrapper.setBody(res);
        } catch (BizException ex) {
            Header resHeader = resWrapper.getHeader();
            resHeader.setErrOcYn("X");
            resHeader.setRspMsg(ex.getMessage());
            resHeader.setRspDtlMsg(ex.toString());
        }

        return resWrapper;
    }
}
