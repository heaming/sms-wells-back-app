package com.kyowon.sms.wells.web.bond.credit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.bond.common.dvo.ZbnzMessageDvo;
import com.kyowon.sms.common.web.bond.common.service.ZbnzMessageMgtService;
import com.kyowon.sms.wells.web.bond.credit.converter.WbndRentalCbMgtDelinquentHistoryConverter;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SaveReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SendReq;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;
import com.kyowon.sms.wells.web.bond.credit.mapper.WbndRentalCbMgtDelinquentHistoryMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WbndRentalCbMgtDelinquentHistoryService {

    private final WbndRentalCbMgtDelinquentHistoryMapper mapper;
    private final WbndRentalCbMgtDelinquentHistoryConverter converter;
    private final ZbnzMessageMgtService messageMgtService;

    public List<SearchRes> getRentalCbMgtDelinquentHistories(SearchReq dto) {
        return this.converter.listRentalCbDlqIzDvoToSearchRes(
            this.mapper.selectRentalCbMgtDelinquentHistories(this.converter.mapSearchReqToRentalCbDlqIzDvo(dto))
        );
    }

    @Transactional
    public int saveRentalCbMgtDelinquentHistories(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WbndRentalCbDelinquentIzDvo dvo = this.converter.mapSaveReqToRentalCbDlqIzDvo(dto);
            // 연체내역 update
            int resultIz = this.mapper.updateRentalCbDlqIz(dvo);
            BizAssert.isTrue(resultIz == 1, "MSG_ALT_SVE_ERR");
            processCount += resultIz;
            // 연체이력 insert
            int resultHist = this.mapper.insertRentalCbDlqHistory(dvo);
            BizAssert.isTrue(resultHist == 1, "MSG_ALT_SVE_ERR");
            processCount += resultHist;
        }

        return processCount;
    }

    @Transactional
    public int sendRentalCbMgtDelinquentHistories(List<SendReq> dtos) throws Exception {
        int processCount = 0;
        String nowDate = DateUtil.getNowDayString(); // 현재년월일
        String ocBsdt = DateUtil.addDays(nowDate, -1); // 발생일자
        String rgstSchDt = DateUtil.addDays(nowDate, 5); // 등록예정일자

        for (SendReq dto : dtos) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("cstKnm", dto.cstKnm());
            paramMap.put("ocBsdt", DateUtil.formatDateKR(ocBsdt));
            paramMap.put("rgstSchDt", DateUtil.formatDateKR(rgstSchDt));
            paramMap.put("dlqBlam", dto.dlqBlam().replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
            paramMap.put("dsphTelNo", "1588-4113");

            ZbnzMessageDvo sendDvo = new ZbnzMessageDvo();
            sendDvo.setFwBizNm("BN_KAKAO_MESSAGE"); // 발송업무명
            sendDvo.setFwbooDtm(dto.fwbooDate() + dto.fwbooTime() + "00"); // 발송예약일시
            sendDvo.setMsgFwTpCd("01"); // 메시지발송유형코드
            sendDvo.setCstNo(dto.cstNo()); // 고객번호
            sendDvo.setCstNm(dto.cstKnm()); // 고객명
            sendDvo.setRcvrTno(dto.cralTno()); // 수신자번호
            sendDvo.setDsptrTno("1588-4113"); // 발신자번호
            sendDvo.setBndMsgTpVal1("B"); // 채권메시지유형값1
            sendDvo.setBndMsgTpVal2("B05"); // 채권메시지유형값2

            int result = messageMgtService.createMessage(sendDvo, "wells18153", paramMap);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            int resultIz = this.mapper.updateMessageSendDate(dto.cstNo(), dto.baseYm());
            BizAssert.isTrue(resultIz == 1, "MSG_ALT_SVE_ERR");

            processCount += result;
        }
        return processCount;
    }
}
