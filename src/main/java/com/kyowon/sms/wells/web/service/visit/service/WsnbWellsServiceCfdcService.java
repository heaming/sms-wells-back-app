package com.kyowon.sms.wells.web.service.visit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.kyowon.sflex.common.message.dto.EmailDto;
import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sflex.common.report.dvo.ReportDvo;
import com.kyowon.sflex.common.report.dvo.ReportEntryDvo;
import com.kyowon.sflex.common.report.service.ReportService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbWellsServiceCfdcConverter;
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
    private final WsnbWellsServiceCfdcConverter converter;
    private final KakaoMessageService kakaoMessageService;
    private final EmailService emailService;
    private final TemplateService templateService;
    private final ReportService reportService;

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
            "url", "https://wsm.kyowon.co.kr/anonymous/sms/wells/service/wells-service-cfdc/report/" + dto.cstSvAsnNo()
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
            "url", "https://wsm.kyowon.co.kr/anonymous/sms/wells/service/wells-service-cfdc/report/" + dto.cstSvAsnNo()
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
        dvo.setReturnUrl("/anonymous/sms/wells/service/wells-service-cfdc/report/" + cstSvAsnNo);
        return reportService.openReportAuthEntry(dvo);
    }

    public ModelAndView openReport(String cstSvAsnNo, String birth) {
        WsnbWellsServiceCfdcDvo cstDvo = mapper.selectCustomer(cstSvAsnNo)
            .orElseThrow(() -> new BizException("MSG_ALT_NO_DATA"));

        if (birth.equals(cstDvo.getCstBthd())) {
            ReportDvo dvo = new ReportDvo();
            dvo.setOzrPath("/kyowon_as/wellsServConf.ozr");
            Map<String, String> map = new HashMap();
            map.put("cstSvAsnNo", cstSvAsnNo);
            map.put("cstNm", cstDvo.getCstNm());
            map.put("cstBthd", cstDvo.getCstBthd());
            dvo.setArgs(map);
            return reportService.openReport(dvo);
        } else {
            ReportEntryDvo dvo = new ReportEntryDvo();
            dvo.setBzopNoYn("N"); //사업자여부
            dvo.setCustName(cstDvo.getCstNm());
            dvo.setReturnUrl("/anonymous/sms/wells/service/wells-service-cfdc/report/" + cstSvAsnNo);
            dvo.setError("error");
            dvo.setErrorMessage("등록된 생년월일과 일치하지 않습니다.");
            return reportService.openReportAuthEntry(dvo);
        }
    }

    public FindOzRes getOzReport(String cstSvAsnNo) {
        WsnbWellsServiceCfdcDvo dvo = mapper.selectOzReport(cstSvAsnNo).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );
        return converter.mapWellsServiceCfdcDvoToFindOzRes(dvo);
    }
}
