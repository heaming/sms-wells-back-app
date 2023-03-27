package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.google.gson.JsonObject;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarCodeDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniRegistrationBarCodeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/regist-barcodes")
public class WsniRegistrationBarCodeController {

    private final WsniRegistrationBarCodeService service;

    private final MessageResourceService messageService;

    @ApiOperation(value = "W-SV-I-0010 QR코드별 상품정보 조회 팝업")
    @GetMapping
    public void getRegistrationBarCodes(
        @RequestParam(required = true)
        String QRCD, HttpServletResponse response
    ) throws IOException, Exception {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        log.info("[WsniRegistrationBarCodeController.getRegistrationBarCodes] QRCD ::: " + QRCD);

        JsonObject json = new JsonObject();

        try {
            WsniRegistrationBarCodeDto.SearchRes res = service.getRegistrationBarCodes(QRCD);

            if (res == null || StringUtils.isEmpty(res.custCd())) {
                json.addProperty("CODE", "1");
                json.addProperty("MESSAGE", messageService.getMessage("MSG_TXT_NOT_EXIST_QR")); //정보가 존재하지 않습니다
            } else {
                json.addProperty("CODE", "0");
                json.addProperty("MESSAGE", messageService.getMessage("MSG_TXT_NOM")); //정상
                json.addProperty("LCNCDE", res.lcncde());
                json.addProperty("LCIUSE", res.lciuse());
                json.addProperty("GDS_CD", res.gdsCd());
                json.addProperty("FARM_YN", res.farmYn());
                json.addProperty("ITEM_NM", res.itemNm());
                json.addProperty("CUST_CD", res.custCd());
                json.addProperty("CUST_NM", res.custNm());
                json.addProperty("HNO_NO", res.hnoNo());
                json.addProperty("CSMR_YR", res.csmrYr());
                json.addProperty("CSMR_CD", res.csmrCd());
                json.addProperty("ADDR", res.addr());
                json.addProperty("ZIPNO", res.zipno());
                json.addProperty("EMP_ID", res.empId());
                json.addProperty("EMP_NM", res.empNm());
                json.addProperty("DEPT_NM", res.deptNm());
                json.addProperty("MNG_HP_NO", res.mngHpNo());
                json.addProperty("VST_DT", res.vstDt());
                json.addProperty("MNG_TYP", res.mngTyp());
                json.addProperty("MNG_CYC", res.mngCyc());
                json.addProperty("DBLD_NM", res.dbldNm());
                json.addProperty("FILTER_YN", res.filterYn());
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.addProperty("CODE", "1");
            json.addProperty("MESSAGE", messageService.getMessage("MSG_TXT_QR_SEARCH_ERROR")); //QR코드 조회 오류
        }

        try {
            out.print(json);
            out.close();
        } catch (Exception e) {
            json.addProperty("CODE", "1");
            json.addProperty("MESSAGE", messageService.getMessage("MSG_TXT_JSON_CREATE_ERROR")); //Json파일 생성오류
            out.print(json);
            out.close();
        }
    }

}
