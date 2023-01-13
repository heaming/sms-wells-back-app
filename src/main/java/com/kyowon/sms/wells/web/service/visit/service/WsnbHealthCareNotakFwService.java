package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbHealthCareNotakFwConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbHealthCareNotakFwDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbHealthCareNotakFwDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbHealthCareNotakFwMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * <pre>
 * W-SV-S-0038 건강케어 알림톡 발송 , 설치 후 다음날 발송 안마의자, 웰스팜, 매트리스
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2023-01-13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbHealthCareNotakFwService {

    private final WsnbHealthCareNotakFwMapper mapper;
    private final WsnbHealthCareNotakFwConverter converter;
    private final SmsMessageService smsMessageService;

    /**
     * 안마의자, 웰스팜, 매트리스 등 설치 후 건강케어 고객에게 알림톡으로 발송한다.
     *
     * @return 변경 개수
     */
    public int saveHealthCareNotakFws() {
        AtomicInteger updateCount = new AtomicInteger();
        List<SearchRes> rows = mapper.selectHealthcareBiztalk();
        for (SearchRes row : rows) {


            SmsSendReqDvo dvo = SmsSendReqDvo.builder()
                .subject("SUBJECT")
                .msgContent("content")
                .destInfo(row.cstNm().concat("^").concat(row.cphonIdvTno()))
                .documentId("ID")
                .build();

            smsMessageService.sendMessage();
        }

        //        for (WsnbHealthCareNotakFwDto.SaveReq row : rows) {
        //            if (CommConst.ROW_STATE_DELETED.equals(row.rowState())) {
        //                updateCount.addAndGet(mapper.deleteHealthCareNotakFws(converter.mapAllSaveReqToRemoveReq(row)));
        //            }
        //        }
        //        for (WsnbHealthCareNotakFwDto.SaveReq row : rows) {
        //            switch (row.rowState()) {
        //                case CommConst.ROW_STATE_CREATED -> {
        //                    updateCount.addAndGet(mapper.insertHealthCareNotakFws(converter.mapAllSaveReqToCreateReq(row)));
        //                }
        //                case CommConst.ROW_STATE_UPDATED -> {
        //                    updateCount.addAndGet(mapper.updateHealthCareNotakFws(converter.mapAllSaveReqToEditReq(row)));
        //                }
        //                default -> {}
        //            }
        //        }
        return updateCount.get();
    }

}
