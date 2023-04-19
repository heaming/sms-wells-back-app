package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrChRcchStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WwctaDocumentReceiptPssConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WwctaDocumentReceiptPssRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WwctaDocumentReceiptPssMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WwctaDocumentReceiptPssService {

    //TODO : CALLBACK 수정
    private static final String CALLBACK = "15776688";
    private final WwctaDocumentReceiptPssMapper mapper;
    private final WwctaDocumentReceiptPssConverter converter;
    private final WctzHistoryService historyService;
    private final KakaoMessageService kakaoMessageService; //카카오톡 메신저 알림톡

    public List<SearchRes> getDocumentReceipts(SearchReq dto) {
        WwctaDocumentReceiptPssRequestDvo dvo = converter.mapSearchReqToWwctaDocumentReceiptPssDvo(dto);
        return converter.mapWwctaDocumentReceiptPssDvoToSearchRes(mapper.selectDocumentReceipts(dvo));
    }

    public List<SearchRes> getDocumentReceiptsExcelDownload(SearchReq dto) {
        WwctaDocumentReceiptPssRequestDvo dvo = converter.mapSearchReqToWwctaDocumentReceiptPssDvo(dto);
        return converter.mapWwctaDocumentReceiptPssDvoToSearchRes(mapper.selectDocumentReceipts(dvo));
    }

    public int saveDocumentRcpCnfm(SearchReq dto) throws Exception {
        int processCount = 0;
        String histStrtDtm = DateUtil.getNowString();

        if (StringUtil.isNotEmpty(dto.cntrChRcpId())) {
            processCount = mapper.updateDocumentRcpCnfm(dto);
            historyService.createContractChangeRcchStatChangeHistory(
                WctzCntrChRcchStatChangeHistDvo.builder()
                    .cntrChRcpId(dto.cntrChRcpId())
                    .cntrChPrgsStatCd(dto.cntrChPrgsStatCd())
                    .histStrtDtm(histStrtDtm)
                    .build()
            );
            // 10(접수대기)+재접수사유 존재, 20(접수완료), 30(접수반려), 50(처리완료)
            if (dto.cntrChPrgsStatCd().equals("10")
                || Arrays.asList(new String[] {"20", "30", "50"}).contains(dto.cntrChPrgsStatCd())) {
                // 알림톡 메시지 발송
                processCount = sendKakao(processCount, dto);
            }
        }
        return processCount;
    }

    // 알림톡 메시지 발송
    private int sendKakao(int processCount, SearchReq dto) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cstKnm", dto.cstKnm());
        paramMap.put("cralLocaraTno", dto.cralLocaraTno());
        paramMap.put("mexnoEncr", dto.mexnoEncr());
        paramMap.put("cralIdvTno", dto.cralIdvTno());

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("WellsTest") //서식은 나중에 만들어야함
            .templateParamMap(paramMap)
            .destInfo(dto.cstKnm() + "^" + dto.cralLocaraTno() + dto.mexnoEncr() + dto.cralIdvTno())
            .callback(CALLBACK)
            .build();

        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);
        return processCount;
    }
}
