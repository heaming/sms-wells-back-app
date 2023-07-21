package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            if (dvo.getInputGubun().equals("1")) {

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

                if (sapPdDvCd.equals("A1")) {
                    if (dpTpCd.equals("0801")) {
                        dvo.setSapPdAtcCd("07");
                    } else if (dpTpCd.equals("0802")) {
                        dvo.setSapPdAtcCd("06");
                    } else if (rveDvCd.equals("01")) {
                        dvo.setSapPdAtcCd("01");
                    } else if (rveDvCd.equals("03")) {
                        dvo.setSapPdAtcCd("02");
                    }
                }

                // 대표고객 mapping
                if (!rveDvCd.equals("02")) {
                    String dgCstId = mapper.selectCustomerId(dvo);
                    dvo.setDgCstId(dgCstId);
                } else {
                    String sellTpCd = dvo.getSellTpCd();
                    String sellTpDtlCd = dvo.getSellTpDtlCd();
                    if (sellTpCd.equals("1")) {
                        dvo.setDgCstId("1000000226");
                    } else if (sellTpCd.equals("2") && (sellTpDtlCd.equals("24") || sellTpDtlCd.equals("26"))) {
                        dvo.setDgCstId("1000000165");
                    } else if (sellTpCd.equals("2") && sellTpDtlCd.equals("22")) {
                        dvo.setDgCstId("1000000098");
                    } else if (sellTpCd.equals("3") && sellTpDtlCd.equals("33")) {
                        dvo.setDgCstId("1000000108");
                    } else if (sellTpCd.equals("3")) {
                        dvo.setDgCstId("1000000045");
                    } else if (sellTpCd.equals("6")) {
                        dvo.setDgCstId("1000000167");
                    }
                }

                // 법인계좌번호
                if (dvo.getDpTpCd().equals("0104")) {
                    dvo.setCrpAcno(dvo.getAcnoEncr());
                }

                // SAP입금유형코드 mapping
                String dpDvCd = dvo.getDpDvCd();
                String dpMesCd = dvo.getDpMesCd();
                String rvePhCd = dvo.getRvePhCd();
                String vncoDvCd = dvo.getVncoDvCd();
                String sellTpCd = dvo.getSellTpCd();

                if (dpDvCd.equals("1")) {
                    switch (dpMesCd) {
                        case "01" -> {
                            dvo.setSapDpTpCd("31");
                            if (rvePhCd.equals("05")) {
                                dvo.setSapDpTpCd("41");
                            } else if (dpTpCd.equals("0102")) {
                                dvo.setSapDpTpCd("52");
                            }
                        }
                        case "02" -> {
                            dvo.setSapDpTpCd("22");
                            if (rvePhCd.equals("02")) {
                                dvo.setSapDpTpCd("32");
                            } else if (rvePhCd.equals("05")) {
                                dvo.setSapDpTpCd("42");
                            } else if (dpTpCd.equals("0203")) {
                                if (vncoDvCd.equals("001")) {
                                    dvo.setSapDpTpCd("55");
                                } else if (vncoDvCd.equals("002")) {
                                    dvo.setSapDpTpCd("54");
                                } else if (vncoDvCd.equals("003")) {
                                    dvo.setSapDpTpCd("53");
                                }
                            }
                        }
                        case "04" -> {
                            dvo.setSapDpTpCd("81");
                            if (rveDvCd.equals("13")) {
                                dvo.setSapDpTpCd("89");
                            }
                        }
                        default -> {
                            if (rvePhCd.equals("10")) {
                                dvo.setSapDpTpCd("51");
                            } else if (rveDvCd.equals("13")) {
                                dvo.setSapDpTpCd("Y2");
                            }
                        }
                    }
                } else if (dpDvCd.equals("2")) {
                    if (dpMesCd.equals("01")) {
                        dvo.setSapDpTpCd("Y1");
                    } else if (dpMesCd.equals("02")) {
                        dvo.setSapDpTpCd("Y3");
                    }
                } else if (dpDvCd.equals("3") || dpDvCd.equals("4")) {
                    if (sellTpCd.equals("1")) {
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

            } else if (dvo.getInputGubun().equals("2")) {
                resultCount = mapper.updateBusinessBasic(dvo);
                BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");

                resultCount = mapper.insertBusinessProcess(dvo);
                BizAssert.isFalse(resultCount == -2147482646, "MSG_ALT_SVE_ERR");
            }
        }

        return resultCount;
    }
}
