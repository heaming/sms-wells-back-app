package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaDelinquentDepositRefundDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaBusinessAnticipationAmtMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 영업선수금 생성 서비스
 * </pre>
 *
 * @author kicheol.choi
 * @since 2023-07-14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdcaBusinessAnticipationAmtService {
    private final WdcaBusinessAnticipationAmtMapper mapper;
    private final WdcaDelinquentDepositRefundService service;

    /**
    * @param dvos list dvo
    * @return count
    */
    @Transactional
    public int createBusinessAnticipationAmt(
        List<WdcaBusinessAnticipationAmtDvo> dvos
    ) {
        int resultCount = 0;

        for (WdcaBusinessAnticipationAmtDvo dvo : dvos) {
            // 입력구분이 입출금인 경우
            if ("1".equals(dvo.getInputGubun())) {

                WdcaDelinquentDepositRefundDvo refundDvo = new WdcaDelinquentDepositRefundDvo();

                refundDvo.setCntrNo(dvo.getCntrNo());
                refundDvo.setCntrSn(dvo.getCntrSn());
                refundDvo.setKwGrpCoCd(dvo.getKwGrpCoCd());
                refundDvo.setRveNo(dvo.getRveNo());
                refundDvo.setRveSn(dvo.getRveSn());
                refundDvo.setDpDvCd(dvo.getDpDvCd());
                refundDvo.setDpMesCd(dvo.getDpMesCd());
                refundDvo.setDpTpCd(dvo.getDpTpCd());
                refundDvo.setRveDvCd(dvo.getRveDvCd());
                refundDvo.setRveCd(dvo.getRveCd());
                refundDvo.setRveDt(dvo.getRveDt());
                refundDvo.setPerfDt(dvo.getPerfDt());
                refundDvo.setRveAmt(dvo.getRveAmt());

                // 영업선수금 잔액
                dvo.setBznsAtamBlam(dvo.getRveAmt());

                // SAP상품구분코드
                String sapPdDvCd = mapper.selectSapProductDivisionCode(dvo);
                dvo.setSapPdDvCd(sapPdDvCd);

                // 계약 관련
                Map<String, String> contractInfo = mapper.selectContract(dvo);
                String cntrDtlStatCd = contractInfo.get("CNTR_DTL_STAT_CD");

                // SAP항목유형코드
                String rveDvCd = dvo.getRveDvCd();
                String dpTpCd = dvo.getDpTpCd();

                dvo.setSapPdDvCd("00");

                if ("A1".equals(sapPdDvCd)) {
                    if ("0801".equals(dpTpCd)) {
                        dvo.setSapPdAtcCd("07");
                    } else if ("0802".equals(dpTpCd)) {
                        dvo.setSapPdAtcCd("06");
                    } else if ("01".equals(rveDvCd)) {
                        dvo.setSapPdAtcCd("01");
                    } else if ("03".equals(rveDvCd)) {
                        dvo.setSapPdAtcCd("02");
                    }
                }

                // 대표고객 mapping
                if (!"02".equals(rveDvCd)) {
                    String dgCstId = mapper.selectCustomerId(dvo);
                    dvo.setDgCstId(dgCstId);
                } else {
                    String sellTpCd = dvo.getSellTpCd();
                    String sellTpDtlCd = dvo.getSellTpDtlCd();
                    switch (sellTpCd) {
                        case "1" -> dvo.setDgCstId("1000000226");
                        case "2" -> {
                            if ("24".equals(sellTpDtlCd) || "26".equals(sellTpDtlCd)) {
                                dvo.setDgCstId("1000000165");
                            } else if ("22".equals(sellTpDtlCd)) {
                                dvo.setDgCstId("1000000098");
                            }
                        }
                        case "3" -> {
                            if ("33".equals(sellTpDtlCd)) {
                                dvo.setDgCstId("1000000108");
                            } else {
                                dvo.setDgCstId("1000000045");
                            }
                        }
                        case "6" -> dvo.setDgCstId("1000000167");
                    }
                }

                // 법인계좌번호
                if ("0104".equals(dvo.getDpTpCd())) {
                    dvo.setCrpAcno(dvo.getAcnoEncr());
                }

                // SAP입금유형코드 mapping
                String dpDvCd = dvo.getDpDvCd();
                String dpMesCd = dvo.getDpMesCd();
                String rvePhCd = dvo.getRvePhCd();
                String vncoDvCd = dvo.getVncoDvCd();
                String sellTpCd = dvo.getSellTpCd();

                if ("1".equals(dpDvCd)) {
                    switch (dpMesCd) {
                        case "01" -> {
                            dvo.setSapDpTpCd("31");
                            if ("05".equals(rvePhCd)) {
                                dvo.setSapDpTpCd("41");
                            } else if ("0102".equals(dpTpCd)) {
                                dvo.setSapDpTpCd("52");
                            }
                        }
                        case "02" -> {
                            dvo.setSapDpTpCd("22");
                            if ("02".equals(rvePhCd)) {
                                dvo.setSapDpTpCd("32");
                            } else if ("05".equals(rvePhCd)) {
                                dvo.setSapDpTpCd("42");
                            } else if ("0203".equals(dpTpCd)) {
                                if ("001".equals(vncoDvCd)) {
                                    dvo.setSapDpTpCd("55");
                                } else if ("002".equals(vncoDvCd)) {
                                    dvo.setSapDpTpCd("54");
                                } else if ("003".equals(vncoDvCd)) {
                                    dvo.setSapDpTpCd("53");
                                }
                            }
                        }
                        case "04" -> {
                            dvo.setSapDpTpCd("81");
                            if ("13".equals(rveDvCd)) {
                                dvo.setSapDpTpCd("89");
                            }
                        }
                        default -> {
                            if ("10".equals(rvePhCd)) {
                                dvo.setSapDpTpCd("51");
                            } else if ("13".equals(rveDvCd)) {
                                dvo.setSapDpTpCd("Y2");
                            }
                        }
                    }
                } else if ("2".equals(dpDvCd)) {
                    if ("01".equals(dpMesCd)) {
                        dvo.setSapDpTpCd("Y1");
                    } else if ("02".equals(dpMesCd)) {
                        dvo.setSapDpTpCd("Y3");
                    }
                } else if ("3".equals(dpDvCd) || "4".equals(dpDvCd)) {
                    if ("1".equals(sellTpCd)) {
                        dvo.setSapDpTpCd("72");
                    } else {
                        dvo.setSapDpTpCd("71");
                    }
                }

                // 영업선수금 기본 INSERT
                resultCount = mapper.insertBusinessBasic(dvo);
                BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");

                // 기타선수금 데이터 여부
                if (StringUtils.isNotEmpty(dvo.getEtcAtamNo())) {
                    resultCount = mapper.insertEtcProcess(dvo);
                    BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");

                    resultCount = mapper.updateEtcBasic(dvo);
                    BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");
                }

                // 연체, 대손처리 서비스 호출
                resultCount = service.saveDelinquentDepositRefund(refundDvo);
                BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");

            } else if ("2".equals(dvo.getInputGubun())) {
                resultCount = mapper.updateBusinessBasic(dvo);
                BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");

                resultCount = mapper.insertBusinessProcess(dvo);
                BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");
            }
        }

        return resultCount;
    }
}
