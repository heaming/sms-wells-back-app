package com.kyowon.sms.wells.web.kakaosync.common.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kyowon.sms.wells.web.kakaosync.common.dto.KakaoWcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.kakaosync.common.service.KakaoWcsbProspecCustomerMgtService;
import com.sds.sflex.system.config.response.SaveResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/anonymous/smplJoin", name = "카카오싱크 간편 가입")
public class KakaoSyncViewController {

    private final KakaoWcsbProspecCustomerMgtService service;

    @GetMapping("/login")
    public ModelAndView getKakaoSyncdata(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/kakaosync/prospectCustomersLogin");
        mv.addObject("akdcde", request.getParameter("akdcde"));//판매자번호

        return mv;
    }

    @GetMapping("/saveSmplJoinCustByKakao")
    public ModelAndView saveSmplJoinCustByKakao(
        @Valid
        KakaoWcsbProspecCustomerMgtDto.SaveReq dto
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/kakaosync/prospectCustomersMain");
        try {
            SaveResponse data = SaveResponse.builder()
                .processCount(service.saveProspecCustomers(dto))
                .data(dto)
                .build();

            Object rawData = data.getData();
            KakaoWcsbProspecCustomerMgtDto.SaveReq saveReq = (KakaoWcsbProspecCustomerMgtDto.SaveReq)rawData;
            mv.addObject("SaveReq", saveReq);
            mv.addObject("rcod", "1");

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject(
                "SaveReq",
                new KakaoWcsbProspecCustomerMgtDto.SaveReq(
                    null, null, null, null, null, null, null, null, null, null, null, null, null, null
                )
            );
            mv.addObject("rcod", "0");
            mv.addObject("errorobject", e);
            return mv;
        }

        return mv;
    }
}
