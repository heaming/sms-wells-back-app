package com.kyowon.sms.wells.web.service.allocate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitPeriodRecrtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncVisitPeriodRecrtDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncVisitPeriodRecrtMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncVisitPeriodRecrtService {

    private final WsncVisitPeriodRecrtMapper mapper;

    @Transactional
    public int saveVisitPeriodRecrt(WsncVisitPeriodRecrtDto.SaveReq dto) throws Exception {
        /*
         *  1. 해당 계약건의 방문주기 삭제
         *  2. 해당 계약건의 주기표유형에 따라 분기하여 주기표 생성 (if 처리. 위쪽이 우선순위)
         *   2-1 배송인경우 : W-SV-S-0068 (홈카페 캡슐 B/S주기표를 생성) 수행
         *   2-2 모종인경우 : W-SV-S-0069 (웰스팜 모종 B/S주기표를 생성) 수행
         *   2-3 삼성전자 에어컨인 경우 : W-SV-S-0070 (삼성전자 에어컨의 정기 방문 주기 생성) 수행
         *   2-4 멤버십고객인 경우 : W-SV-S-0071 (정기 B/S주기표를 생성하는 작업)
         *   2-5 그 외 : W-SV-S-0072 (정기 B/S주기표를 생성)
         *  3. 메시지 출력
         *   3-1 정상 처리 된 경우 부모화면으로 : "정상처리 되었습니다." 메시지 전달
         *   3-2 에러 발생 시 : "주기표 생성 오류가 발생하였습니다." 메시지 전달
         */
        try {
            WsncVisitPeriodRecrtDvo dvo = mapper.selectTempQuery(dto);

            if ("배송".equals(dvo.getCntrNo())) {
                log.info("[WsncVisitPeriodRecrtService.saveVisitPeriodRecrt] Case 1");
            } else if ("모종".equals(dvo.getCntrNo())) {
                log.info("[WsncVisitPeriodRecrtService.saveVisitPeriodRecrt] Case 2");
            } else if ("삼성전자 에어컨".equals(dvo.getCntrNo())) {
                log.info("[WsncVisitPeriodRecrtService.saveVisitPeriodRecrt] Case 3");
            } else if ("멤버십".equals(dvo.getCntrNo())) {
                log.info("[WsncVisitPeriodRecrtService.saveVisitPeriodRecrt] Case 4");
            } else {
                log.info("[WsncVisitPeriodRecrtService.saveVisitPeriodRecrt] Case 5");
            }
        } catch (Exception e) {
            throw new BizException("주기표 생성 오류가 발생하였습니다.");
        }

        return 1;
    }

}
