package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.bond.common.dvo.ZbnzMessageDvo;
import com.kyowon.sms.common.web.bond.common.service.ZbnzMessageMgtService;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwIzRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchCharacterFwUldRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPrepaymentExpirationDto.SendReq;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccPrepaymentExpirationMapper;
import com.sds.sflex.common.common.dvo.SendTemplateDvo;
import com.sds.sflex.common.common.service.TemplateService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdccPrepaymentExpirationService {

    private final WdccPrepaymentExpirationMapper mapper;
    private final ZbnzMessageMgtService messageMgtService;
    private final TemplateService templateService;

    public List<SearchRes> getObjectPresentState(SearchReq dto) {
        return this.mapper.selectObjectPresentState(dto);
    }

    public List<SearchCharacterFwUldRes> getCharacterFwUld(SearchCharacterFwUldReq dto) {
        return this.mapper.selectCharacterFwUld(dto);
    }

    public List<SearchCharacterFwIzRes> getCharacterFwIz(SearchCharacterFwIzReq dto) {
        return this.mapper.selectCharacterFwIz(dto);
    }

    @Transactional
    public int sendPrepaymentExpirationHistorys(List<SendReq> dtos) throws Exception {
        int processCount = 0;
        for (SendReq dto : dtos) {
            Map<String, Object> paramMap = new HashMap<String, Object>();

            paramMap.put("cstKnm", dto.cstKnm());
            paramMap.put("prmEndMm", dto.prmEndMm());
            paramMap.put("cntrNo", dto.cntrNo());
            paramMap.put("pdNm", dto.pdNm());
            paramMap.put("cnt", dto.cnt());
            paramMap.put("currMm", dto.currMm());
            paramMap.put("postYy", dto.postYy());
            paramMap.put("postMm", dto.postMm());
            paramMap.put("dsphTelNo", "1588-4113");
            // 템플릿정보조회
            SendTemplateDvo template = templateService
                .getTemplateByTemplateCode("Wells18038");

            String templateContent = templateService.getTemplateContent(template.getSendTemplateId(), paramMap);

            ZbnzMessageDvo sendDvo = new ZbnzMessageDvo();
            sendDvo.setFwBizNm("BN_KAKAO_MESSAGE"); // 발송업무명
            sendDvo.setFwbooDtm(dto.fwbooDate() + dto.fwbooTime() + "00"); // 발송예약일시
            sendDvo.setMsgFwTpCd("01"); // 메시지발송유형코드
            sendDvo.setCstNo(dto.cstNo()); // 고객번호
            sendDvo.setCstNm(dto.cstKnm()); // 고객명
            sendDvo.setCntrNo(dto.cntrNo()); // 계약번호
            sendDvo.setCntrSn(dto.cntrSn()); // 계약일련번호
            sendDvo.setDsptrTno("1588-4113"); // 발신자번호
            sendDvo.setRcvrTno(dto.cntrCralTno()); // 수신자번호
            sendDvo.setMsgTit("[웰스] 선납만료 안내");
            sendDvo.setMsgCn(templateContent);
            sendDvo.setBndMsgTpVal1("Wells18038");// 채권메시지유형값1
            sendDvo.setBndMsgTpVal2(dto.prmEndYm());// 채권메시지유형값2
            sendDvo.setBndMsgTpVal4(dto.cntrInfo());// 채권메시지유형값4
            sendDvo.setReserved8("N");

            int result = messageMgtService.createMessage(sendDvo, "Wells18038", paramMap);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            processCount += result;
        }
        return processCount;
    }

}
