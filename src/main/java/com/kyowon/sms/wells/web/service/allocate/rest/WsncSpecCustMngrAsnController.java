package com.kyowon.sms.wells.web.service.allocate.rest;

import com.sds.sflex.system.config.constant.CommConst;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[WSNC] 특정고객 담당자 지정 BS 오더 생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/spec-cust-mngr-asns")
public class WsncSpecCustMngrAsnController {
}
