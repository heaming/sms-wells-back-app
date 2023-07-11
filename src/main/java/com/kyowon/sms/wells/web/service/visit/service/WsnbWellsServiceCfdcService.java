package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.ReportReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.KakaoReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.EmailReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.HistoryReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.HistoryRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbWellsServiceCfdcMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        paramMap.put("userNm", dto.prtnrKnm()); // 추후 적용 예정
        paramMap.put("prodNm", "[테스트] 웰스 서비스 확인서"); // 추후 적용 예정

        KakaoSendReqDvo dvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("TEST") // 추후 적용 예정
            .templateParamMap(paramMap)
            .destInfo(dto.nm() + "^" + dto.receivingNumber())
            .callback(dto.callingNumber())
            .sendDatetime(dto.publishDatetime()) // yyyyMMddHHmmss
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
