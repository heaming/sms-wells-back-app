package com.kyowon.sms.wells.web.closing.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaEtcAnticipationAmtDvo;
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
        WdcaEtcAnticipationAmtDvo dvo
    ) {
        // TODO - 현재 임시 작업함. 설계서 확정되면 추후 재작업

        // 1. 기타선수금번호가 넘어오는지 확인
        if (dvo.getEtcAtamNo().isEmpty()) {
            // 1-1 통합입금번호 중심으로 채번
            // 기타선수금 번호
            dvo.setEtcAtamNo(
                mapper.selectMaxEtcAnticipationNo(
                    dvo.getItgDpNo()
                )
            );

            mapper.insertEtcBasic(dvo);

        } else {
            // 1-2 기타선수금 잔액 수정
            mapper.updateEtcBasic(dvo.getEtcAtamNo());
        }

        // 기타선수금 일련번호
        dvo.setEtcAtamSn(
            mapper.selectMaxEtcAnticipationSn(
                dvo.getEtcAtamNo()
            )
        );

        mapper.insertEtcProcess(dvo);

        return 1;
    }
}
