package com.kyowon.sms.wells.web.closing.payment.service;

import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaEtcAnticipationAmtDvo;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaEtcAnticipationAmtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
        List<WdcaEtcAnticipationAmtDvo> dvos
    ) {
        int resultCount = 0;

        for (WdcaEtcAnticipationAmtDvo dvo : dvos) {
            String etcAtamProcsCd = dvo.getEtcAtamProcsCd();
            if (etcAtamProcsCd.equals("0")) {
                // 기타선수금 채번
                dvo.setEtcAtamNo(mapper.selectMaxEtcAnticipationNo());
            } else if (etcAtamProcsCd.equals("2")) {
                BizAssert.isFalse(dvo.getEtcAtamNo().isEmpty(), "MSG_ALT_NOT_EXIST_REFUND_TARGET");
            }

            // 기타선수금 일련번호 채번
            dvo.setEtcAtamSn(mapper.selectMaxEtcAnticipationSn(dvo));

            // SAP입금유형코드
            dvo.setSapDpTpCd("ZZ");

            // SAP상품구분코드
            dvo.setSapPdDvCd(mapper.selectSapPdDvCd(dvo));

            // SAP상품항목코드
            dvo.setSapPdAtcCd("00");

            // 대표고객ID
            dvo.setDgCstId(mapper.selectCustomerId(dvo));

            // 법인계좌번호
            if (dvo.getDpTpCd().equals("0104")) {
                dvo.setCrpAcno(dvo.getAcnoEncr());
            }

            if (etcAtamProcsCd.equals("0")) {
                // 기타선수금 처리내역 INSERT
                resultCount = mapper.insertEtcProcess(dvo);
                BizAssert.isTrue(resultCount == -2147482646, "MSG_ALT_SVE_ERR");
            } else if (etcAtamProcsCd.equals("2")) {
                resultCount = mapper.updateEtcBasic(dvo);
                BizAssert.isTrue(resultCount == -2147482646, "MSG_ALT_SVE_ERR");
            }
        }

        return resultCount;
    }
}
