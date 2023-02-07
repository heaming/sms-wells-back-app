package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCosmeticRegularSchdDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbCosmeticRegularSchdMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO: 필요 서비스 개발 완료 후 추가 작업
 * <pre>
 * W-SV-S-0050 화장품 캡슐 정기구매 고객 스케줄 INSERT/UPDATE
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.01.25
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbCosmeticRegularSchdService {

    private final WsnbCosmeticRegularSchdMapper mapper;

    /*
    private final Wsnb방문주기재성생Service 방문주기재성생Service;
    private final Wsnb고객정기BS삭제Service 고객정기BS삭제Service;
    private final Wsnb고객정기BS배정Service 고객정기BS배정Service;
    */

    @Transactional
    public int saveCosmeticRegularSchedules() {
        // TB_SVPD_CST_SV_EXCN_IZ /* 고객서비스수행내역 */ 조회
        List<WsnbCosmeticRegularSchdDvo> dvos = this.mapper.selectCosmeticRegularSchedules();

        int processCount = 0;

        for (WsnbCosmeticRegularSchdDvo dvo : dvos) {
            // merge insert/update
            this.mapper.mergeCosmeticRegularSchedules(dvo);

            if (dvo.getCancDt() != null) {
                // 스케줄 데이터 삭제
                this.mapper.deleteCosmeticRegularSchedule(dvo);

                // 방문주기 재성생 서비스 호출

                // 고객 정기BS 삭제 서비스 호출

                // 고객 정기BS 배정 서비스 호출
            } else {
                // 취소일자 업데이트
                this.mapper.updateCosmeticRegularSchedule(dvo);

                // 스케줄 데이터 삭제
                this.mapper.deleteCosmeticRegularSchedule(dvo);

                // 고객 정기BS 삭제 서비스 호출
            }

            processCount += 1;
        }

        return processCount;
    }

}
