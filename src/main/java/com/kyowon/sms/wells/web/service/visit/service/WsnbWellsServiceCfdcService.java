package com.kyowon.sms.wells.web.service.visit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.kyowon.sflex.common.message.dto.EmailDto;
import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sflex.common.report.dvo.ReportEntryDvo;
import com.kyowon.sflex.common.report.service.ReportService;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWellsServiceCfdcDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbWellsServiceCfdcMapper;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbWellsServiceCfdcService {
    private final WsnbWellsServiceCfdcMapper mapper;
    private final KakaoMessageService kakaoMessageService;
    private final EmailService emailService;
    private final TemplateService templateService;
    private final ReportService reportService;

    @Value("${report.ozUrl}")
    private String ozUrl;

    public PagingResult<SearchRes> getWellsServiceConfirmations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectWellsServiceConfirmations(dto, pageInfo);
    }

    public List<SearchRes> getWellsServiceConfirmationsForExcelDownload(SearchReq dto) {
        return mapper.selectWellsServiceConfirmations(dto);
    }

    public int sendWellsServiceConfirmationByKakao(KakaoReq dto) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custNm", dto.nm());
        paramMap.put(
            "url", "https://wsm.kyowon.co.kr/anonymous/sms/wells/service/wells-service-cfdc/report/"
                + dto.cstSvAsnNo()
                + "/auth"
        );

        KakaoSendReqDvo dvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("Wells18053")
            .templateParamMap(paramMap)
            .destInfo(dto.nm() + "^" + dto.receivingNumber())
            .callback(dto.callingNumber())
            .sendDatetime(dto.publishDatetime()) // yyyyMMddHHmmss
            .reserved2("Wells18053")
            .reserved3(dto.cstSvAsnNo())
            .build();

        return kakaoMessageService.sendMessage(dvo);
    }

    public int sendWellsServiceConfirmationByEmail(EmailReq dto) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custNm", dto.nm());
        paramMap.put(
            "url",
            "https://d-wsm.kyowon.co.kr/anonymous/sms/wells/service/wells-service-cfdc/report/"
                + dto.cstSvAsnNo()
                + "/auth"
        );

        List<EmailDto.CreateReceiptUserReq> receiptUsers = new ArrayList<>();
        receiptUsers.add(
            new EmailDto.CreateReceiptUserReq(
                "",
                dto.receiver(),
                "TO",
                1
            )
        );

        EmailDto.CreateReq emailDto = new EmailDto.CreateReq(
            templateService.getTemplateByTemplateId("TMP_SNB_WELLS18053").getSendTemplateTitle(),
            "TMP_SNB_WELLS18053",
            templateService.getTemplateContent("TMP_SNB_WELLS18053", paramMap),
            "wellsorder@kyowon.co.kr",
            "",
            "Y",
            dto.publishDatetime(),
            "PERSONAL",
            receiptUsers,
            null
        );

        emailService.createEmail(emailDto);

        return 1;
    }

    public PagingResult<HistoryRes> getWellsServiceConfirmationHistoriesForKakao(HistoryReq dto, PageInfo pageInfo) {
        return mapper.selectWellsServiceConfirmationHistoriesForKakao(dto, pageInfo);
    }

    public PagingResult<HistoryRes> getWellsServiceConfirmationHistoriesForEmail(HistoryReq dto, PageInfo pageInfo) {
        return mapper.selectWellsServiceConfirmationHistoriesForEmail(dto, pageInfo);
    }

    public ModelAndView openReportAuthEntry(String cstSvAsnNo) {
        WsnbWellsServiceCfdcDvo cstDvo = mapper.selectCustomer(cstSvAsnNo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

        ReportEntryDvo dvo = new ReportEntryDvo();
        dvo.setBzopNoYn("N"); //사업자여부
        dvo.setCustName(cstDvo.getCstNm());
        dvo.setReturnUrl("/anonymous/sms/wells/service/wells-service-cfdc/report/" + cstSvAsnNo + "/auth");
        return reportService.openReportAuthEntry(dvo);
    }

    public ModelAndView openReportWithAuth(String cstSvAsnNo, String birth) {
        WsnbWellsServiceCfdcDvo cstDvo = mapper.selectCustomer(cstSvAsnNo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

        if (birth.equals(cstDvo.getCstBthd())) {
            return openReport(cstSvAsnNo);
        } else {
            ReportEntryDvo dvo = new ReportEntryDvo();
            dvo.setBzopNoYn("N"); //사업자여부
            dvo.setCustName(cstDvo.getCstNm());
            dvo.setReturnUrl("/anonymous/sms/wells/service/wells-service-cfdc/report/" + cstSvAsnNo + "/auth");
            dvo.setError("error");
            dvo.setErrorMessage("등록된 생년월일과 일치하지 않습니다.");
            return reportService.openReportAuthEntry(dvo);
        }
    }

    public ModelAndView openReport(String cstSvAsnNo) {
        Map<String, String> map = new HashMap();
        map.put("cstSvAsnNo", cstSvAsnNo);
        map.put("searchApiUrl", "/api/v1/anonymous/sms/wells/service/wells-service-cfdc/oz");
        map.put("rcgvpNm", "");
        map.put("prtnrNm", "");

        ModelAndView mv = new ModelAndView("common/common/view");
        mv.addObject("ozrPath", "/kyowon_as/wellsServConf.ozr");
        mv.addObject("ozUrl", ozUrl);
        mv.addObject("args", map);

        return mv;
    }

    public Map<String, Object> getOzReport(FindOzReq dto) {
        WsnbWellsServiceCfdcDvo dvo = mapper.selectOzReport(dto).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );

        List<Map<String, Object>> list1 = new ArrayList<>();
        Map<String, Object> obj1 = new HashMap<>();
        obj1.put("CUSTNM", dvo.getRcgvpNm());
        obj1.put("CHKNAM", "사번");
        obj1.put("REGDAT", dvo.getCntrCnfmDtm());
        obj1.put("CNT", "1");
        list1.add(obj1);

        List<Map<String, Object>> list2 = new ArrayList<>();
        Map<String, Object> obj2 = new HashMap<>();
        obj2.put("ROWNUM", "1");
        obj2.put("CUST_CD", dvo.getCntrNo() + "-" + dvo.getCntrSn());
        obj2.put("ITEM_NM", dvo.getPdNm());
        obj2.put("CUST_NM", dvo.getRcgvpNm());
        if (StringUtils.isEmpty(dvo.getRdadr())) {
            obj2.put("ADDR", dvo.getRnadr());
        } else {
            obj2.put("ADDR", dvo.getRnadr() + " " + dvo.getRdadr());
        }
        obj2.put("WRK_DATE", dvo.getVstFshDt());
        obj2.put("PROC_TXT", dvo.getSvProcsCn());
        obj2.put("WRK_EMP_NM", dvo.getPsicPrtnrNm());
        obj2.put("CHKVAL", dvo.getPsicPrtnrNo());
        list2.add(obj2);

        List<Map<String, Object>> list3 = new ArrayList<>();

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("jsonMaster", list1);
        rtn.put("jsonList1", list2);
        rtn.put("jsonData2", list3);

        return rtn;
    }
}
