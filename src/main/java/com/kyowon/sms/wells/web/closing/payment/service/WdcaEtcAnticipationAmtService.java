package com.kyowon.sms.wells.web.closing.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaEtcAnticipationAmtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 기타선수금 생성 서비스
 * </pre>
 *
 * @author kicheol.choi
 * @since 2023-02-27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdcaEtcAnticipationAmtService {
    private final WdcaEtcAnticipationAmtMapper mapper;

    @Transactional
    public int createEtcAnticipationAmt(
        com.kyowon.sms.wells.web.closing.payment.dvo.WdcaEtcAnticipationAmtDvo dvo
    ) {
        // TODO - 현재 임시 작업함. 테이블이 확정되면 추후 재작업

        // 1. 기타선수금번호 채번
        dvo = mapper.selectMaxEtcAnticipationAmtNo();

        // 2. 기타선수금 테이블 저장

        return 1;
    }
}
