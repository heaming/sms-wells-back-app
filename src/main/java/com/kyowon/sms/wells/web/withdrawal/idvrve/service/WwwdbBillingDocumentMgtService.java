package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwwdbBillingDocumentMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchDtlsRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchFwRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentForwardingDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwwdbBillingDocumentMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwwdbBillingDocumentMgtMapper;
import com.sds.sflex.common.common.dvo.UserSessionDvo;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwwdbBillingDocumentMgtService {

    private final WwwdbBillingDocumentMgtMapper mapper;

    private final WwwdbBillingDocumentMgtConverter convert;

    private final TemplateService templateService;

    private final KakaoMessageService kakaoMessageService; //카카오톡 메신저 알림톡

    private final EmailService emailService; //메일 보내기

    /**
     * 청구서 관리 목록조회
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public PagingResult<SearchRes> getBillingDocumentMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectBillingDocuments(dto, pageInfo);
    }

    /**
     * 청구서 관리 엑셀 다운로드
     *
     * @param ZwdbCorporationAcInfMgtDto.SearchReq
     * @param pageInfo
     * @return
     */
    @Transactional
    public List<SearchRes> getBillingDocumentMgtExcels(SearchReq dto) {
        return mapper.selectBillingDocuments(dto);
    }

    @Transactional
    public int removeBillingDocumentMgts(List<RemoveReq> dtos) throws Exception {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WwwdbBillingDocumentMgtDvo dvo = convert.mapDeleteWwwdbBillingDocumentMgtDvo(dto);
            processCount += mapper.deleteBillingDocuments(dvo);
            processCount += mapper.deleteBillingDocumentDtails(dvo);
        }

        return processCount;
    }

    @Transactional
    public int saveBillingDocumentMgts(SaveReq dto) throws Exception {

        int processCount = 0;

        WwwdbBillingDocumentDvo dvo = convert.mapSaveWwwdbBillingDocumentDvo(dto.saveMainReq());
        switch (dto.saveMainReq().state()) {
            case CommConst.ROW_STATE_CREATED -> {
                String pk = mapper.selectBillingDocumentPk();
                dvo.setBildcPblNo(pk);
                processCount += mapper.insertBillingDocument(dvo);
                processCount = saveBillingDtails(dto, processCount, dvo);
            }
            case CommConst.ROW_STATE_UPDATED -> {
                processCount = saveBillingDtails(dto, processCount, dvo);
            }
            default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
        }

        return processCount;
    }

    @Transactional
    private int saveBillingDtails(SaveReq dto, int processCount, WwwdbBillingDocumentDvo dvo) throws Exception {
        for (SaveDtlsReq dtlDto : dto.saveDtlsReq()) {
            WwwdbBillingDocumentDetailDvo dtlDvo = convert.mapSaveWwwdbBillingDocumentDetailDvo(dtlDto);
            dtlDvo.setBildcPblNo(dvo.getBildcPblNo());
            switch (dtlDto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    processCount += mapper.insertBillingDocumentDtails(dtlDvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateBillingDocumentDtails(dtlDvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

    @Transactional
    public List<SearchDtlsRes> getBillingDocumentDetails(SearchDtlsReq dto) {
        return mapper.selectBillingDocumentDetails(dto);
    }

    @Transactional
    public List<SearchFwRes> getBillingDocumentForwardings(SearchFwReq dto) {
        return mapper.selectBillingDocumentForwardings(dto);
    }

    @Transactional
    public int saveBillingDocumentForwarding(SaveFwReq dto) throws Exception {
        int processCount = 0;

        WwwdbBillingDocumentForwardingDvo dvo = convert.mapSaveWwwBillingDocumentForwardingDvo(dto);

        if (dto.bildcFwTpCd().equals("K")) {
            // 알림톡 메시지 발송
            processCount = sendKakao(processCount, dvo);
        } else if (dto.bildcFwTpCd().equals("E")) {
            //메일 보내기            
            processCount = sendMail(processCount, dvo);
        }

        return processCount;
    }

    //메일 보내기
    private int sendMail(int processCount, WwwdbBillingDocumentForwardingDvo dvo) throws Exception {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        String content = "TEST";
        //            String content = templateService.getTemplateContentOriginal("TMP_WDB_MAIL_TEST"); //이거 템플리 서식 들고오는거 알아보자

        EmailSendReqDvo mailDvo = EmailSendReqDvo.builder()
            .title("WELLS 전자청구서 안내메일") //채번 예정
            .content(content)
            .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dvo.getFromMail())))
            .build();

        String emailUid = emailService.sendEmail(mailDvo);
        String tenantId = userSession.getTenantId();
        dvo.setBildcFwDrmNo1(tenantId);
        dvo.setBildcFwDrmNo2(emailUid);
        processCount += mapper.insertBillingDocumentForwarding(dvo);
        return processCount;
    }

    // 알림톡 메시지 발송
    private int sendKakao(int processCount, WwwdbBillingDocumentForwardingDvo dvo) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cstFnm", dvo.getCstFnm());
        paramMap.put("pdNm", dvo.getPdNm());
        paramMap.put("pdQty", dvo.getPdQtySum());
        paramMap.put("pdSellAmtSum", dvo.getPdSellAmtSum());

        KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
            .templateCode("wells51878") //서식은 나중에 만들어야함
            .templateParamMap(paramMap)
            .destInfo(dvo.getPdNm() + "^" + dvo.getDestInfo())
            .callback(dvo.getCallback())
            .build();

        String selectMmtSeq = mapper.selectMmtSeq();
        processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo); //채번에 문제가 있음
        dvo.setBildcFwDrmNo1(selectMmtSeq);
        processCount += mapper.insertBillingDocumentForwarding(dvo);
        return processCount;
    }

}
