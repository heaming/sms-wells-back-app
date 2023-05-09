package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SaveReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailVirtualAcSmsDto.SearchReq;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailVirtualAcSmsConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailVirtualAcSmsDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailVirtualAcSmsMapper;
import com.sds.sflex.common.common.service.TemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaOrderDetailVirtualAcSmsService {

    private final WctaOrderDetailVirtualAcSmsMapper mapper;
    private final WctaOrderDetailVirtualAcSmsConverter converter;
    private final TemplateService templateService;
    private final SmsMessageService smsMessageService;

    public WctaOrderDetailVirtualAcSmsDvo getVirtualAccountTemplate(SearchReq dto) throws Exception {
        String templateId = "TMP_CTA_VIRTUAL_ACCOUNT_GUIDE";
        WctaOrderDetailVirtualAcSmsDvo dvo = mapper.selectVirtualAcCustomer(dto);

        String cstNm = StringUtils.isEmpty(dvo.getCstNm()) ? "" : dvo.getCstNm();
        String cntrNo = StringUtils.isEmpty(dvo.getCntrNo()) ? "" : dvo.getCntrNo();
        int cntrSn = Objects.isNull(dvo.getCntrSn()) ? 0 : dvo.getCntrSn();
        String vacBnkNm = StringUtils.isEmpty(dvo.getVacBnkNm()) ? "" : dvo.getVacBnkNm();
        String vacNo = StringUtils.isEmpty(dvo.getVacNo()) ? "" : dvo.getVacNo();

        String template = templateService.getTemplateContent(
            templateId, Map.of(
                "cstNm", cstNm,
                "cntrNo", cntrNo,
                "cntrSn", cntrSn,
                "vacBnkNm", vacBnkNm,
                "vacNo", vacNo
            )
        );

        dvo.setTemplate(template);
        return dvo;
    }

    public int saveVirtualAccountMessages(SaveReq dto) throws Exception {
        int processCount = 0;
        WctaOrderDetailVirtualAcSmsDvo dvo = converter.mapSaveReqToOrderDetailVirtualAcSmsDvo(dto);
        SmsSendReqDvo smsSendReqDvo = SmsSendReqDvo.withContents()
            .subject(dvo.getMsgTit())
            .msgContent(dvo.getTemplate())
            .destInfo(dvo.getCstNm() + "^" + dvo.getCralLocaraTno() + dvo.getMexnoEncr() + dvo.getCralIdvTno())
            .build();

        processCount += smsMessageService.sendMessage(smsSendReqDvo);
        return processCount;
    }
}
