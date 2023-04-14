package com.kyowon.sms.wells.web.closing.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaBusinessAnticipationAmtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 영업선수금 생성 서비스
 * </pre>
 *
 * @author kicheol.choi
 * @since 2023-02-21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdcaBusinessAnticipationAmtService {
    private final WdcaBusinessAnticipationAmtMapper mapper;

    /**
    * 수납번호, 수납일련번호, 입금마감일자, 계약번호, 계약일련번호, 회사코드, 수납코드, 담당파트너번호, 수납금액, 은행코드, 카드사코드, 기타선수금번호, 기타선수금일련번호, SAP입금유형코드, SAP상품구분코드, SAP상품항목코드로 영업선수금 테이블 저장
    * @param dvo
    * @return count
    */
    @Transactional
    public String createBusinessAnticipationAmt(
        WdcaBusinessAnticipationAmtDvo dvo
    ) {
        // TODO - 현재 임시 작업함. 테이블이 확정되면 추후 재작업

        // 1. 영업선수금기본 테이블에 존재하는지 확인
        int cnt = mapper.selectBusinessBasicCheck(
            dvo.getRveNo(),
            dvo.getRveSn()
        );

        if (cnt > 0) { // 1-1 존재하면 UPDATE
            mapper.updateBusinessBasic(
                dvo.getRveNo(),
                dvo.getRveSn()
            );

            dvo.setBznsAtamProcsCd("2");

            mapper.insertBusinessProcess(dvo);

        } else { // 1-2 미존재하면 INSERT
            // 영업선수금 기본
            mapper.insertBusinessBasic(dvo);
            // 영업선수금 처리내역
            dvo.setBznsAtamProcsCd("1");

            mapper.insertBusinessProcess(dvo);
        }

        return "SUCCESS";
    }
}
