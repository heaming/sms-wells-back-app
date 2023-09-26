package com.kyowon.sms.wells.web.kakaosync.common.mvc;

import com.kyowon.sms.wells.web.kakaosync.common.dto.KakaoWcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.kakaosync.common.service.KakaoWcsbProspecCustomerMgtService;
import com.sds.sflex.system.config.response.SaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class KakaoSyncViewController {

    private final KakaoWcsbProspecCustomerMgtService service;

    @GetMapping
    @RequestMapping(value = "/anonymous/smplJoin/login", name = "카카오싱크 간편 가입")
    public String getKakaoSyncdata(Model model, HttpServletRequest request) {
        model.addAttribute("akdcde", request.getParameter("akdcde"));//판매자번호
        return "/kakaosync/prospectCustomersLogin";
    }

    @GetMapping
    @RequestMapping(value = "/anonymous/smplJoin/saveSmplJoinCustByKakao", name = "카카오싱크 간편 가입")
    public ModelAndView saveSmplJoinCustByKakao(
        @Valid
        KakaoWcsbProspecCustomerMgtDto.SaveReq dto
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/kakaosync/prospectCustomersMain");
        try {
            SaveResponse data =
                SaveResponse.builder()
                    .processCount(service.saveProspecCustomers(dto))
                    .data(dto)
                    .build();

            Object rawData = data.getData();
            KakaoWcsbProspecCustomerMgtDto.SaveReq saveReq = (KakaoWcsbProspecCustomerMgtDto.SaveReq) rawData;
            mv.addObject("SaveReq", saveReq);
            mv.addObject("rcod", "1");

        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("SaveReq", new KakaoWcsbProspecCustomerMgtDto.SaveReq(null,null,null,null,null,null,null,null,null,null,null,null,null,null));
            mv.addObject("rcod", "0");
            mv.addObject("errorobject", e);
            return mv;
        }

        return mv;
    }
}
