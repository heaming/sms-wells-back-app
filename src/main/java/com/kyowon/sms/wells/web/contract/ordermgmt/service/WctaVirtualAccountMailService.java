package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaVirtualAccountMailDto;
import com.sds.sflex.common.common.service.TemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaVirtualAccountMailService {
    private final EmailService emailService;
    private final TemplateService templateService;

    @Transactional
    public int saveVirtualAccountMail(WctaVirtualAccountMailDto.SaveReq dto) throws Exception {

        // TODO: 명세서 이메일 발송 마스터 테이블에 INSERT
        // TODO: 신규 발송번호 채번하면서, 파라미터로 받은 데이터를 저장한다.

        // 채번한 발송번호로 PDF 접근키를 생성한다.
        String decStr = "채번한 발송번호";
        String encStr = Base64.encodeBase64String(decStr.getBytes());

        // 템플릿코드를 불러와서 이메일을 전송(등록)한다.
        String templateId = "TMP_CTA_VIRTUAL_ACCOUNT_CFDC"; // WELLS 가상계좌확인서 안내메일
        String pdfUrl = "http://wellsorder.kyowon.co.kr/specView/vrtlMail/" + encStr;
        String titleStr = "가상계좌확인서";

        EmailSendReqDvo emailDvo = EmailSendReqDvo.builder()
            .title(templateService.getTemplateByTemplateId(templateId).getSendTemplateTitle())
            .content(
                templateService.getTemplateContent(
                    templateId,
                    Map.of("titleStr", titleStr, "pdfUrl", pdfUrl)
                )
            )
            .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(dto.mailAddr())))
            .build();

        String emailUid = emailService.sendEmail(emailDvo);

        return 1;
    }
}
