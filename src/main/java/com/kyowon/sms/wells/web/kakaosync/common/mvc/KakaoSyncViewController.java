package com.kyowon.sms.wells.web.kakaosync.common.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/anonymous/smplJoin/login", name = "카카오싱크 간편 가입")
public class KakaoSyncViewController {
    @GetMapping
    public String getKakaoSyncdata(Model model, HttpServletRequest request) {
        model.addAttribute("akdcde", request.getParameter("akdcde"));//판매자번호
        return "/kakaosync/prospectCustomersLogin";
    }
}
