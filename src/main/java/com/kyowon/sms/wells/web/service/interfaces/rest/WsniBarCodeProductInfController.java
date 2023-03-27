package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarCodeProductInfDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniBarCodeProductInfService;
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
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/barcode-products")
public class WsniBarCodeProductInfController {

    private final WsniBarCodeProductInfService service;

    private final MessageResourceService messageService;

    @ApiOperation(value = "W-SV-I-0011 바코드를 입력받아 등록된 바코드인지 확인 API")
    @GetMapping
    public void getBarCodeProducts(
        @RequestParam
        String QRCD, HttpServletResponse response
    ) throws IOException, Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        log.info("[WsniRegistrationBarCodeController.getRegistrationBarCodes] QRCD ::: " + QRCD);

        JsonObject json = new JsonObject();

        try {
            WsniBarCodeProductInfDto.SearchRes res = service.getBarCodeProducts(QRCD);

            if (res.resultcode() > 0) {
                json.addProperty("resultCode", "0");
                if (res.regi() > 0) {
                    json.addProperty("regi", "true");
                } else {
                    json.addProperty("regi", "false");
                }
            } else {
                json.addProperty("resultCode", "1");
                json.addProperty("resultMessage", messageService.getMessage("MSG_TXT_BARCODE_INFO_INVALID")); //바코드 정보가 올바르지 않습니다
                if (res.regi() > 0) {
                    json.addProperty("regi", "true");
                } else {
                    json.addProperty("regi", "false");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.addProperty("resultCode", "1");
            json.addProperty("resultMessage", messageService.getMessage("MSG_TXT_BARCODE_SEARCH_ERROR")); //바코드 조회 오류
        }

        try {
            out.print(json);
            out.close();
        } catch (Exception e) {
            json.addProperty("resultCode", "1");
            json.addProperty("resultMessage", messageService.getMessage("MSG_TXT_JSON_CREATE_ERROR")); //Json파일 생성오류
            out.print(json);
            out.close();
        }
    }
}
