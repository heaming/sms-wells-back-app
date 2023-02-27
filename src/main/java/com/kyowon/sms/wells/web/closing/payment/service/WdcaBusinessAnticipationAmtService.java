package com.kyowon.sms.wells.web.closing.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaBusinessAnticipationAmtMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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
    * @throws BizException 조회 결과가 없는 경우 Exception 처리
    */
    @Transactional
    public int createBusinessAnticipationAmt(
        WdcaBusinessAnticipationAmtDvo dvo
    ) {
        // TODO - 현재 임시 작업함. 테이블이 확정되면 추후 재작업

        // 1. SAP입금유형코드 조회
        dvo = mapper.selectSapDepositTypeCode(
            dvo.getCoCd(),
            dvo.getApyStrtdt(),
            dvo.getApyEnddt(),
            dvo.getRveTpCd(),
            dvo.getDpKndCd(),
            dvo.getDpTpCd()
        );
        Optional<WdcaBusinessAnticipationAmtDvo> optionalDepositRes = Optional.ofNullable(dvo);
        optionalDepositRes.orElseThrow(
            () -> new BizException("MSG_ALT_SAP_DP_TP_CD_BASE_NEX")
        );

        // 2. SAP상품구분코드, SAP상품항목코드 조회
        dvo = mapper.selectSapProductClassificationCode(
            dvo.getCoCd(),
            dvo.getApyStrtdt(),
            dvo.getApyEnddt(),
            dvo.getRveTpCd(),
            dvo.getDpKndCd(),
            dvo.getDpTpCd(),
            dvo.getPdHcsfId(),
            dvo.getPdHcsfId(),
            dvo.getPdLclsfId(),
            dvo.getSellTpCd()
        );
        Optional<WdcaBusinessAnticipationAmtDvo> optionalProductRes = Optional.ofNullable(dvo);
        optionalProductRes.orElseThrow(
            () -> new BizException("MSG_ALT_SAP_PD_DV_NEX")
        );

        // 3. 영업선수금 테이블 저장

        return 1;
    }
}
