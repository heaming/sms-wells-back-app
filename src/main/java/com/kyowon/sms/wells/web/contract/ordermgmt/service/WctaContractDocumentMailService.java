package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractDocumentMailConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDocumentMailDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractDocumentMailDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaContractDocumentMailDvo.Contract;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractDocumentMailMapper;
import com.sds.sflex.common.common.service.TemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractDocumentMailService {

    private final WctaContractDocumentMailMapper mapper;
    private final WctaContractDocumentMailConverter converter;
    private final TemplateService templateService;
    private final EmailService emailService;

    public String getContractCstNm(String cntrNo, int cntrSn) {
        return mapper.selectContractCstNm(cntrNo, cntrSn);
    }

    @Transactional
    public int saveDcevdnMlTrss(SaveReq dto) throws Exception {
        WctaContractDocumentMailDvo dvo = converter.saveReqToWctaContractDocumentMailDvo(dto);
        List<Contract> cntrList = dvo.getCstList();
        for (Contract cntrDvo : cntrList) {
            String cntrNoFull = cntrDvo.getCntrNoFull();

            String cntrNo = cntrNoFull.split("-")[0];
            String cntrSn = cntrNoFull.split("-")[1];
            String docDvCd = dvo.getDocDvCd();
            String email = dvo.getRecpMail();
            String cstNm = dvo.getCstKnm();

            String title = "";
            String titleStr = "";
            String templateId = "TMP_CTA_EVIDENCE_DOC_GUIDE";

            switch (docDvCd) {
                case "1" -> {
                    title = "WELLS 입금내역서 안내메일";
                    titleStr = "입금내역서";
                }
                case "2" -> {
                    title = "WELLS 거래명세서 안내메일";
                    titleStr = "거래명세서";
                }
                case "3" -> {
                    title = "WELLS 카드매출전표 안내메일";
                    titleStr = "카드매출전표";
                }
                case "4" -> {
                    title = "WELLS 계약사항 안내메일";
                    titleStr = "계약사항";
                }
                default -> {}
            }
            /* TODO: 증빙서류 테이블 추가 후 작업 예정 */
            // 채번한 발송번호로 PDF 접근키를 생성한다.
            //        String decStr = "채번한 발송번호";
            //        String encStr = Base64.encodeBase64String(decStr.getBytes());

            String workNo = "";

            String pdfUrl = "http://wellsorder.kyowon.co.kr/specView/mail/" + workNo; /* TODO: 주소 변경 될 가능성 있음 */

            String perdStr = dvo.getStartDt() + " ~ " + dvo.getEndDt();

            EmailSendReqDvo sendDvo = EmailSendReqDvo.builder()
                .title(title)
                .content(
                    templateService.getTemplateContent(
                        templateId, Map.of(
                            "titleStr", titleStr,
                            "sndgDiv", docDvCd,
                            "perdStr", perdStr,
                            "pdfUrl", pdfUrl,
                            "cstNm", cstNm
                        )
                    )
                )
                .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(email)))
                .build();

            String emailUid = emailService.sendEmail(sendDvo);

        }

        return 0;
    }
}
