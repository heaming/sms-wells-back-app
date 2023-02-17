package com.kyowon.sms.wells.web.contract.interfaces.rest;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.service.WctiCustomerService;
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

        // 파라미터 체크 - 파라미터값이 NULL 인 경우, 조회 하지 않음
        SearchReq req = reqWrapper.getBody();
        if (StringUtils.isEmpty(req.CST_NM())
            && StringUtils.isEmpty(req.CST_NO())
            && StringUtils.isEmpty(req.CRAL_LOCARA_TNO())
            && StringUtils.isEmpty(req.MEXNO())
            && StringUtils.isEmpty(req.CRAL_IDV_TNO())
            && StringUtils.isEmpty(req.LOCARA_TNO())
            && StringUtils.isEmpty(req.EXNO())
            && StringUtils.isEmpty(req.IDV_TNO())) {
            return resWrapper;
        }

        // 파라미터 체크 - 연락처 검색어가 2개 이상 아닐 경우, 조회 하지 않음
        int emptyCnt = 0;
        if (StringUtils.isEmpty(req.CRAL_LOCARA_TNO()))
            ++emptyCnt;
        if (StringUtils.isEmpty(req.MEXNO()))
            ++emptyCnt;
        if (StringUtils.isEmpty(req.CRAL_IDV_TNO()))
            ++emptyCnt;
        if (emptyCnt == 2)
            return resWrapper;

        emptyCnt = 0;
        if (StringUtils.isEmpty(req.LOCARA_TNO()))
            ++emptyCnt;
        if (StringUtils.isEmpty(req.EXNO()))
            ++emptyCnt;
        if (StringUtils.isEmpty(req.IDV_TNO()))
            ++emptyCnt;
        if (emptyCnt == 2)
            return resWrapper;

        // 서비스 메소드 호출
        List<SearchRes> res = service.getCustomers(req);

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
