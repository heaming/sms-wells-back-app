package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto.SaveReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallDto.SaveRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiContractInstallService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 고객센터I/F")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/customer-centers")
@RequiredArgsConstructor
@Validated
public class WctiContractIntallController {
    private final WctiContractInstallService service;

    @ApiOperation(value = "[EAI_WSSI1051] 계약처, 설치처 정보 변경", notes = "설치처 정보 변경에 한함")
    @PostMapping("/contract-installs")
    public EaiWrapper editContractInstall(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SaveRes>> resWrapper = reqWrapper.newResInstance();
        SaveReq req = reqWrapper.getBody();
        String rsProcsYn = "N";

        // 파라미터 체크 - 필수 입력 외 파라미터값이 NULL 인 경우, 처리결과를 'N'으로 전송
        if (StringUtils.isEmpty(req.ADR_ID())
            && StringUtils.isEmpty(req.CRAL_LOCARA_TNO())
            && StringUtils.isEmpty(req.MEXNO())
            && StringUtils.isEmpty(req.CRAL_IDV_TNO())
            && StringUtils.isEmpty(req.LOCARA_TNO())
            && StringUtils.isEmpty(req.EXNO())
            && StringUtils.isEmpty(req.IDV_TNO())) {
            rsProcsYn = "N";
        } else {
            // 서비스 메소드 호출
            rsProcsYn = service.editContractInstallInf(req);
        }

        // Response Body 세팅
        SaveRes res = new WctiContractInstallDto.SaveRes(rsProcsYn);
        resWrapper.setBody(List.of(res));

        return resWrapper;
    }
}
