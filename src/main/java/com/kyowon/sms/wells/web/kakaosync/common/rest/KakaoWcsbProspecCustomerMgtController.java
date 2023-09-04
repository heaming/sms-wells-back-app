package com.kyowon.sms.wells.web.kakaosync.common.rest;

import com.kyowon.sms.wells.web.kakaosync.common.dto.KakaoWcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.kakaosync.common.service.KakaoWcsbProspecCustomerMgtService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Api(tags = "[Wcsb] 고객 >> 가망고객관리 >> 고객 DB 관리")
@RequestMapping(value = CommConst.REST_URL_V1 +"/anonymous/sflex/common/common/kakao-prospect-customers")
@RequiredArgsConstructor
@Validated
public class KakaoWcsbProspecCustomerMgtController {

    private final KakaoWcsbProspecCustomerMgtService service;

    /**
     * 가망고객 DB 목록 저장
     * @param dto
     * @return
     */

    @ApiOperation(value = "가망고객 DB 목록 저장 - Kakao", notes = "CUD 변경 데이터를 List 형태로 받아 일괄 저장한다. - Kakao ver")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "nickname", value = "프로필정보-닉네임", paramType = "query", example = "NbhUco_UqSiKRAZmQTULQA"),
        @ApiImplicitParam(name = "email", value = "이메일", paramType = "query", example = "zmTkxYDztHBjWdhvhl2J8Q"),
        @ApiImplicitParam(name = "gender", value = "성별구분코드", paramType = "query", example = "K200QtUN80c8BfeL2pFyKQ"),
        @ApiImplicitParam(name = "birthyear", value = "출생년도", paramType = "query", example = "vU17DpOYvEAPWwpFkUfCWQ"),
        @ApiImplicitParam(name = "birthday", value = "생일", paramType = "query", example = "uhmfxdtMo_vyJpxGCMJTUA"),
        @ApiImplicitParam(name = "phone_number", value = "전화번호", paramType = "query", example = "vbs1FeQ6zAioF9fDYAhzoQ"),
        @ApiImplicitParam(name = "zipcode", value = "우편번호", paramType = "query", example = "gQC7q3Oh_qN6hu2AqQSYcw"),
        @ApiImplicitParam(name = "base_address", value = "기본주소", paramType = "query", example = "OGk5Lc9bF_klLScDcaISqBEy_dhk69eA8y5EsdRbQ3EBMzSgGPORsqyy6BjNkpHK"),
        @ApiImplicitParam(name = "detail_address", value = "상세주소", paramType = "query", example = "yrrTpIF7Vt6kRC3mtT2WuZ59k1IyDn9t8SzxRqJiufk"),
        @ApiImplicitParam(name = "employee_id", value = "추천인 사번", paramType = "query", example = ""),
        @ApiImplicitParam(name = "marketing_yn", value = "마케팅 목적 처리 동의서", paramType = "query", example = "klViZ89rncyWj7fET-EhZA"),
        @ApiImplicitParam(name = "akdcde", value = "파트너번호", paramType = "query", example = "1438045"),
        @ApiImplicitParam(name = "channel_yn", value = "외부채널추가여부", paramType = "query", example = "7ybdKNvetTE53J4m3f5q9A"),
        @ApiImplicitParam(name = "auid", value = "외부연계식별값", paramType = "query", example = "b33d8nuWlv4NKM1YLMEmzQ"),
    })
    @GetMapping
    public SaveResponse KakaosaveProspecCustomers(
        @Valid
        KakaoWcsbProspecCustomerMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveProspecCustomers(dto))
            .data(dto)
            .build();
    }

}
