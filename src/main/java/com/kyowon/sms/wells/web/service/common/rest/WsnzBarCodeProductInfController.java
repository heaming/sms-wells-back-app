package com.kyowon.sms.wells.web.service.common.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.kyowon.sms.wells.web.service.common.dto.WsnzBarCodeProductInfDto;
import com.kyowon.sms.wells.web.service.common.service.WsnzBarCodeProductInfService;
import com.sds.sflex.common.uifw.service.MessageResourceService;
import com.sds.sflex.system.config.constant.CommConst;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNZ] QR코드별 상품정보 조회 팝업")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/barcode-products")
public class WsnzBarCodeProductInfController {

    private final WsnzBarCodeProductInfService service;

    private final MessageResourceService messageService;

    @GetMapping
    public void getBarCodeProducts(
        @RequestParam
        String QRCD, HttpServletResponse response
    ) throws IOException, Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        log.info("[WsnzRegistrationBarCodeController.getRegistrationBarCodes] QRCD ::: " + QRCD);

        JsonObject json = new JsonObject();

        try {
            WsnzBarCodeProductInfDto.SearchRes res = service.getBarCodeProducts(QRCD);

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
