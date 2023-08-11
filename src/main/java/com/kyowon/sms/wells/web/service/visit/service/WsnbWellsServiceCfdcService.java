package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbWellsServiceCfdcMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbWellsServiceCfdcService {
    private final WsnbWellsServiceCfdcMapper mapper;

    private final KakaoMessageService kakaoMessageService;

    private final EmailService emailService;

    public PagingResult<SearchRes> getWellsServiceConfirmations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectWellsServiceConfirmations(dto, pageInfo);
    }

    public List<SearchRes> getWellsServiceConfirmationsForExcelDownload(SearchReq dto) {
        return mapper.selectWellsServiceConfirmations(dto);
    }

    public int printWellsServiceConfirmationByReport(ReportReq dto) throws Exception {
        return 0;
    }

    public int sendWellsServiceConfirmationByKakao(KakaoReq dto) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custNm", dto.nm());
        paramMap.put("url", "test url");

        KakaoSendReqDvo dvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("Wells18053")
            .templateParamMap(paramMap)
            .destInfo(dto.nm() + "^" + dto.receivingNumber())
            .callback(dto.callingNumber())
            .sendDatetime(dto.publishDatetime()) // yyyyMMddHHmmss
            .reserved2("Wells18053")
            .build();

        return kakaoMessageService.sendMessage(dvo);
    }

    public int sendWellsServiceConfirmationByEmail(EmailReq dto) throws Exception {
        EmailSendReqDvo dvo = EmailSendReqDvo.builder()
            .title("[테스트] 웰스 서비스 확인서") // 추후 적용 예정
            .content("[테스트] 웰스 서비스 확인서") // 추후 적용 예정
            .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dto.receiver())))
            .build();

        emailService.sendEmailBySystem(dvo);
        return 1;
    }

    public PagingResult<HistoryRes> getWellsServiceConfirmationHistoriesForKakao(HistoryReq dto, PageInfo pageInfo) {
        return mapper.selectWellsServiceConfirmationHistoriesForKakao(dto, pageInfo);
    }

    public PagingResult<HistoryRes> getWellsServiceConfirmationHistoriesForEmail(HistoryReq dto, PageInfo pageInfo) {
        return mapper.selectWellsServiceConfirmationHistoriesForEmail(dto, pageInfo);
    }
}
