package com.kyowon.sms.wells.web.closing.payment.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.converter.WdcaCancellationFeeComputationConverter;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaCancellationFeeComputationDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaCancellationFeeComputationDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaCancellationFeeComputationDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaCancellationFeeComputationResultDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaComputationObjectContractDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaComputationObjectSalesDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaRentalFeeDiscountRstlCcamDvo;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaCancellationFeeComputationMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 위약금 산출 서비스(W-CL-S-0010)
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-06-16
 */
@Service
@RequiredArgsConstructor
public class WdcaCancellationFeeComputationService {
    private final WdcaCancellationFeeComputationConverter converter;
    private final WdcaCancellationFeeComputationMapper mapper;

    /**
     * WELLS 예상위약금 산출, 계약 취소 시 위약금 산출 및 생성 관리 프로그램
        - 고객센터 및 영업부 고객 안내를 위한 예상위약금 산출
        - 영업부 취소 위약금 산출 및 데이터 생성 관리
     * @param cntrNo         계약번호
     * @param cntrSn         계약일련번호
     * @param rqdt           요청일자
     * @param duedt          취소일자
     * @param canTpCd        취소유형코드
     * @param lsRntf         분실손료
     * @param pBorAmt        포인트위약금액
     * @param cnfmYm         확정여부
     * @return WdcaCancellationFeeComputationDto
     * @throws BizException SQL 오류 발생 시 Exception 처리
     */
    @Transactional
    public SearchRes saveDelinquentDepositRefund(SearchReq dto) throws BizException {
        int resRtlfeBorAmt = 0; /* 잔여렌탈료위약금액 */
        int rgstCostDscBorAmt = 0; /* 등록비할인위약금액 */
        int rentalDscBorAmt = 0; /* 렌탈할인위약금액 */
        int rstlBorAmt = 0; /* 재약정위약금액 */
        int csmbCostBorAmt = 0; /* 소모품비위약금액 */
        int pBorAmt = 0; /* 포인트위약금액 */
        int reqdCsBorAmt = 0; /* 철거비용위약금액 */
        int lsRntf = 0; /* 분실손료 */
        int borAmt = 0; /* 위약금액 */

        String coffeeBeanPdClsfId = "05003002";
        String airCleanerPdClsfId = "02001001002";
        String wellsFarmWidePdClsfId = "05001003001";
        String wellsFarmSlimPdClsfId = "05001003002";
        String faceCarePdClsfId = "03004001";
        int coffeeBeanUnitPrice = 5000;

        WdcaCancellationFeeComputationResultDvo resultDvo = new WdcaCancellationFeeComputationResultDvo();

        WdcaCancellationFeeComputationDvo inputDvo = converter.mapSaveReqToWdcaCancellationFeeComputationDvo(dto);

        /* 위약금 산출 대상 계약정보 조회 */
        WdcaComputationObjectContractDvo searchContractDvo = mapper.selectComputationObjectContract(inputDvo);

        /* 위약금액 산출 */
        if ("".equals(searchContractDvo.getIstDt()) || searchContractDvo.getIstDt() == null) {
            /* 설치일자 = '' OR 설치일자 = NULL 일 경우 출력값 항목을 모두 0으로 세팅하고 호출 프로그램으로 출력값을 전달 후 프로그램 종료 */
            resRtlfeBorAmt = 0;
            rgstCostDscBorAmt = 0;
            rentalDscBorAmt = 0;
            rstlBorAmt = 0;
            csmbCostBorAmt = 0;
            pBorAmt = 0;
            reqdCsBorAmt = 0;
            lsRntf = 0;
            borAmt = 0;
        } else {
            /* 위약금 산출 대상 매출정보 조회 */
            WdcaComputationObjectSalesDvo searchSalesDvo = mapper.selectComputationObjectSales(inputDvo);
            if ("".equals(searchSalesDvo.getCntrNo()) || searchSalesDvo.getCntrNo() == null) {
                /* 계약번호 = NULL이면, 아래 조건으로 위약금 산출 대상 매출정보 조회 재 수행 */
                Calendar calendar = Calendar.getInstance();
                calendar.set(
                    Integer.parseInt(inputDvo.getRqdt().substring(0, 2)),
                    Integer.parseInt(inputDvo.getRqdt().substring(2, 6)) - 1,
                    Integer.parseInt(inputDvo.getRqdt().substring(6, 8))
                );
                calendar.add(Calendar.MONTH, -1);

                SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
                inputDvo.setRqdt(date.format(calendar));

                searchSalesDvo = mapper.selectComputationObjectSales(inputDvo);
            }

            if (coffeeBeanPdClsfId.equals(searchContractDvo.getPdClsfId())) {
                /* 상품분류ID 원두 여부 확인 */
                String baseYm = searchSalesDvo.getSlClYm();
                /* 처리 로직 - 웰스서비스실적집계내역 Table 조회 방문 처리 여부 확인 */
                int cnt = mapper.selectWellsServicePerformanceCount(baseYm, inputDvo);
                int rentalTn = 0;
                /* 처리 로직 - 미 방문 시 렌탈회차 - 1 처리 */
                if (cnt == 0) {
                    rentalTn = searchSalesDvo.getRentalTn() - 1;
                } else {
                    rentalTn = searchSalesDvo.getRentalTn();
                }
                /* 처리 로직 - 위약금액 산출 */
                resRtlfeBorAmt = 0;
                rgstCostDscBorAmt = 0;
                rentalDscBorAmt = 0;
                rstlBorAmt = 0;
                csmbCostBorAmt = 0;
                pBorAmt = 0;
                reqdCsBorAmt = 0;
                lsRntf = 0;
                borAmt = rentalTn * coffeeBeanUnitPrice; //(원두 단가 = 5,000원)

                if ("Y".equals(inputDvo.getCnfmYm())) {
                    /* 위약금액 저장 : 입력값_확정여부 = 'Y'인 경우만 처리 */
                    int processCnt = 0;
                    processCnt += mapper.insertWellsBorAmtBas(resultDvo); /* 위약금액기본 저장 */
                    processCnt += mapper.insertWellsBorAmtBasHist(resultDvo); /* 위약금액기본이력 저장 */
                    BizAssert.isTrue(processCnt > 0, "MSG_ALT_BOR_AMT_CMPT_PROCS_ERR"); //위약금액 산출 처리 시 오류가 발생했습니다.
                }
            } else {
                /* 처리 로직 - 렌탈/리스 위약금액 산출 */
                int resIntam = 0;
                int resIntam1 = 0; /*잔여일수에 따른 잔여할부금*/
                int resIntam2 = 0; /*약정만료일자의 일에 따른 잔여할부금*/
                int resIntam3 = 0; /*약정만료일자의 일에 따른 잔여할부금*/

                String sellTpCd = searchContractDvo.getSellTpCd(); // 판매유형코드
                String sellTpDtlCd = searchContractDvo.getSellTpDtlCd(); // 판매유형상세코드
                String alncmpCd = searchContractDvo.getAlncmpCd(); // 제휴사코드
                int resDc = Integer.parseInt(searchContractDvo.getResDc()); //잔여일수
                int resMcn = Integer.parseInt(searchContractDvo.getResMcn()); //잔여개월수
                int mmIstmAmt = Integer.parseInt(searchContractDvo.getMmIstmAmt()); //월할부금액
                String stplExnDt = searchContractDvo.getStplExnDt(); //약정만료일자
                int resCcamRat = Integer.parseInt(searchContractDvo.getResCcamRat()); //잔여위약금비율
                int dscAmt = Integer.parseInt(searchContractDvo.getDscAmt()); //할인금액
                int stplPtrm = Integer.parseInt(searchContractDvo.getStplPtrm()); //약정기간
                int frisuBfsvcPtrmN = Integer.parseInt(searchContractDvo.getFrisuBfsvcPtrmN()); //무상BS기간수
                String pdClsfId = searchContractDvo.getPdClsfId(); //상품분류ID
                String pdClsfId1 = searchContractDvo.getPdClsfId1(); //상품분류ID-세분류
                int pasgDc = Integer.parseInt(searchContractDvo.getPasgDc()); //경과일수
                String cntrRcpdt = searchContractDvo.getCntrRcpdt(); //계약접수일자
                String sellInflwChnlDtlCd = searchContractDvo.getSellInflwChnlDtlCd(); //판매유입채널상세코드
                int reqdCs = Integer.parseInt(searchContractDvo.getReqdCs()); //철거비용
                int csmnCs = Integer.parseInt(searchContractDvo.getCsmbCs()); //소모품비용
                String cstGdCd = searchContractDvo.getCstGdCd(); //고객등급코드

                /* 렌탈인 경우 잔여렌탈료위약금액 산출 */
                if ("2".equals(sellTpCd) && ("21".equals(sellTpDtlCd)
                    || "23".equals(sellTpDtlCd))) {
                    /*계약 채결 후 14일이내 취소건은 잔여렌탈료위약금액 = 0, 단. K멤버스 제휴건은 7일이내일 경우 잔여렌탈료위약금액 = 0*/
                    if (("45".equals(alncmpCd)
                        && pasgDc <= 7)
                        || (!"45".equals(alncmpCd)
                            && pasgDc <= 14)) {
                        resRtlfeBorAmt = 0;
                    } else {
                        /*잔여일수에 따른 잔여할부금 산출*/
                        if (resDc >= 30) {
                            resIntam1 = mmIstmAmt;
                        } else {
                            resIntam1 = (mmIstmAmt / 30 * resDc) / 10 * 10;
                        }
                        /*약정만료일자의 일에 따른 잔여할부금 산출*/
                        if (Integer.parseInt(stplExnDt.substring(7, 2)) >= 30) {
                            resIntam2 = mmIstmAmt;
                        } else {
                            resIntam2 = (mmIstmAmt / 30 * Integer.parseInt(stplExnDt.substring(7, 2))) / 10 * 10;
                        }
                        /*잔여개월수에 따른 잔여할부금 산출*/
                        resIntam3 = mmIstmAmt * resMcn;
                        /*잔여할부금 산출*/
                        resIntam = resIntam1 + resIntam2 + resIntam3;
                        /*잔여렌탈위약금액 산출 - 잔여위약금비율이 존재하면 잔여위약금비율을 반영하여 처리하고, 잔여위약금비율이 없으면 10% 적용*/
                        if (resIntam > 0) {
                            if (resCcamRat > 0) {
                                resRtlfeBorAmt = (resIntam * resCcamRat / 100) / 10 * 10;
                            } else {
                                resRtlfeBorAmt = (resIntam / 10) / 10 * 10;
                            }
                        } else {
                            resRtlfeBorAmt = 0; /*잔여렌탈위약금액은 음수가 될수 없음*/
                        }
                    }
                }
                /* 리스인 경우 잔여렌탈료위약금액 산출 */
                else if ("2".equals(sellTpCd) && ("22".equals(sellTpDtlCd)
                    || "24".equals(sellTpDtlCd)
                    || "25".equals(sellTpDtlCd)
                    || "26".equals(sellTpDtlCd))) { //[판매유형상세코드 22:리스, 24:환경리스, 25:장기할부, 26:환경할부]
                    /*잔여할부금 산출*/
                    resIntam = resMcn * mmIstmAmt;
                    /*잔여렌탈위약금액 산출*/
                    /*잔여위약금비율이 존재하면 잔여위약금비율을 반영하여 처리하고, 잔여위약금비율이 없으면 10% 적용*/
                    if (resIntam > 0) {
                        if (resCcamRat > 0) {
                            resRtlfeBorAmt = (resIntam * resCcamRat / 100) / 10 * 10;
                        } else {
                            resRtlfeBorAmt = (resIntam / 10) / 10 * 10;
                        }
                    } else {
                        resRtlfeBorAmt = 0; /*잔여렌탈위약금액은 음수가 될수 없음*/
                    }
                } else {
                    resRtlfeBorAmt = 0;
                }
                /* 처리로직 - 등록비할인위약금액 산출 */
                if ("2".equals(sellTpCd)) {
                    /* 렌탈 & 리스 등록비할인위약금액 산출 */
                    /*계약 채결 후 14일이내 취소건은 잔여렌탈료위약금액 = 0, 단. K멤버스 제휴건은 7일이내일 경우 잔여렌탈료위약금액 = 0*/
                    if (("45".equals(alncmpCd)
                        && pasgDc <= 7)
                        || (!"45".equals(alncmpCd)
                            && pasgDc <= 14)) {
                        rgstCostDscBorAmt = 0;
                    } else {
                        rgstCostDscBorAmt = Integer.parseInt(searchContractDvo.getCntramDscAmt());
                    }
                } else {
                    rgstCostDscBorAmt = 0;
                }
                /* 처리로직 - 렌탈료할인위약금액과 재약정위약금액 산출 */
                if ("2".equals(sellTpCd)) {
                    /*계약 채결 후 14일이내 취소건은 잔여렌탈료위약금액 = 0, 단. K멤버스 제휴건은 7일이내일 경우 잔여렌탈료위약금액 = 0*/
                    if (("45".equals(alncmpCd)
                        && pasgDc <= 7)
                        || (!"45".equals(alncmpCd)
                            && pasgDc <= 14)) { // [제휴사코드 45:K멤버스]
                        rentalDscBorAmt = 0;
                        rstlBorAmt = 0;
                    } else {
                        /*설치월 취소건은 위약금액 산출 제외*/
                        if (searchContractDvo.getIstDt().substring(0, 6).equals(inputDvo.getRqdt().substring(0, 6))) { // 입력값_요청일자의 년월 = 설치일자 TODO: 날짜 비교 확인 할것 ?????
                            rentalDscBorAmt = 0;
                            rstlBorAmt = 0;
                        }
                        /*렌탈 렌탈료할인위약금액, 재약정위약금액 산출*/
                        WdcaRentalFeeDiscountRstlCcamDvo rentalFeeDiscountRstlCcamDvo = mapper
                            .selectRentalFeeDiscountRstlCcam(inputDvo);
                        if ("21".equals(sellTpDtlCd)
                            || "23".equals(sellTpDtlCd)) { //[판매유형상세코드 21:일반렌탈, 23:공유렌탈]
                            int rentalDc = mapper.selectRentalDc(inputDvo); /* 설치월 렌탈일수 조회 */
                            rentalDscBorAmt = rentalFeeDiscountRstlCcamDvo.getRentalDscBorAmt() + (dscAmt * resMcn)
                                + ((dscAmt / 30 * rentalDc) / 10 * 10);
                            rstlBorAmt = rentalFeeDiscountRstlCcamDvo.getRstlBorAmt();
                        } else {
                            /*리스 렌탈료할인위약금액 산출 - 리스는 재약정이 없어 산출한 값이 없음*/
                            rentalDscBorAmt = rentalFeeDiscountRstlCcamDvo.getRentalDscBorAmt() + (dscAmt * resMcn);
                            rstlBorAmt = 0;
                        }
                    }
                } else {
                    rentalDscBorAmt = 0;
                    rstlBorAmt = 0;
                }
                /* 처리로직 - 소모품비위약금액과 철거비위약금액 산출 */
                /* 렌탈/리스 공통부 소모품비위약금액 산출 */

                /* 미니공기청정기 소모품위약금액 산출 - AS-IS 미니공기청정기(4129) */
                if (airCleanerPdClsfId.equals(pdClsfId1) && pasgDc <= 14) { /* [상품분류ID세분류 = '02001001002' (공기청정기)] */
                    csmbCostBorAmt = 0;
                }
                /* 웰스팜 영업부 소모품위약금액 산출 */
                if ((wellsFarmWidePdClsfId.equals(pdClsfId1) || wellsFarmSlimPdClsfId.equals(pdClsfId1))
                    && cntrRcpdt.compareTo("20180701") >= 0 // 계약접수일자(cntrRcpdt)가 20180701보다 같거나 큼.
                    && "1010".equals(sellInflwChnlDtlCd)
                    && pasgDc <= 14) { // [상품분류ID세분류 = 05001003001' [웰스팜와이드], 상품분류ID세분류 = '05001003002' [웰스팜슬림]]
                    csmbCostBorAmt = 0;
                }

                /* 웰스팜 홈쇼핑 소모품위약금액 산출 */
                String sellInflwChnlDtlCds[] = {"6010", "6020", "6030", "6040", "6050", "6060", "6070"};
                if ((wellsFarmWidePdClsfId.equals(pdClsfId1) || wellsFarmSlimPdClsfId.equals(pdClsfId1))
                    && cntrRcpdt.compareTo("20180701") >= 0 // 계약접수일자(cntrRcpdt)가 20180701보다 같거나 큼.
                    && Arrays.asList(sellInflwChnlDtlCds).contains(sellInflwChnlDtlCd)
                    && pasgDc <= 14) { // [상품분류ID세분류 = 05001003001' [웰스팜와이드], 상품분류ID세분류 = '05001003002' [웰스팜슬림]]
                    csmbCostBorAmt = 0;
                }
                /* 리스 소모품비위약금액, 철거비위약금액 산출 */
                if ("2".equals(sellTpCd) && ("22".equals(sellTpDtlCd)
                    || "24".equals(sellTpDtlCd)
                    || "25".equals(sellTpDtlCd)
                    || "26".equals(sellTpDtlCd))) { //[판매유형상세코드 22:리스, 24:환경리스, 25:장기할부, 26:환경할부]
                    /* 홈쇼핑LED 사은품 홈쇼핑 소모품위약금액산출 */
                    String alncmpCds[] = {"01", "03", "18", "19", "20", "26", "27", "28", "29", "31", "32", "43"};
                    if (faceCarePdClsfId.equals(pdClsfId) // [상품분류ID = '03004001' (Face Care)]
                        && Arrays.asList(alncmpCds).contains(alncmpCd)) {
                        /* 사은품접수내역 테이블을 활용해서, 사은품 제공을 확인하여 소모품비위약금액을 산출한다. */
                        inputDvo.setFgptPdCd("7887"); // TODO: AS-IS기준 '7887' - 판매 사은품접수내역의 사은품상품코드 확정 후 TO-BE 변환 우선 AS-IS 코드로 세팅
                        int fgptDsbCt = mapper.selectFreeGiftReceipt(inputDvo);
                        /*사은품접수내역 테이블 정보가 존재하면, 일괄적으로 소모품비위약금액 = 30,000원을 부여한다.*/
                        if (fgptDsbCt > 0) {
                            csmbCostBorAmt = 30000;
                        }
                    }
                    /* 철거비위약금액 산출 */
                    if (("45".equals(alncmpCd) && pasgDc <= 7) || (!"45".equals(alncmpCd) && pasgDc <= 14)) { //[제휴사코드 45:K멤버스]
                        reqdCsBorAmt = 0;
                    } else {
                        reqdCsBorAmt = reqdCs;
                    }
                }
                /* 렌탈 소모품비위약금액, 철거비위약금액 산출 */
                if ("2".equals(sellTpCd) && ("22".equals(sellTpDtlCd) || "24".equals(sellTpDtlCd)
                    || "25".equals(sellTpDtlCd) || "26".equals(sellTpDtlCd))) { // [판매유형상세코드 22:리스, 24:환경리스, 25:장기할부, 26:환경할부]
                    /* 사은품비위약금액 산출 */
                    if (stplExnDt.compareTo(inputDvo.getRqdt()) > 1) {
                        if (csmnCs > 0) {
                            csmbCostBorAmt = csmnCs; // ????????
                        } else {
                            csmbCostBorAmt = 40000;
                        }
                    } else {
                        csmbCostBorAmt = 0;
                    }
                    /* 철거비위약금액 산출 */
                    if ((("45".equals(alncmpCd) && pasgDc <= 7)
                        || (!"45".equals(alncmpCd) && pasgDc <= 14))
                        && (cntrRcpdt.compareTo("20200601") > 1 && "2".equals(cstGdCd))) { // [제휴사코드 45:K멤버스]
                        reqdCsBorAmt = 0;
                    } else {
                        reqdCsBorAmt = reqdCs;
                    }
                }
                /* 제빙기 사은품에 대한 소모품비위약금액 산출 */
                if (cntrRcpdt.compareTo("20180701") >= 1 && cntrRcpdt.compareTo("20180831") <= 1) {
                    inputDvo.setFgptPdCd("9410"); // TODO: AS-IS기준 '9410' - 판매 사은품접수내역의 사은품상품코드 확정 후 TO-BE 변환 우선 AS-IS 코드로 세팅
                    int fgptDsbCt = mapper.selectFreeGiftReceipt(inputDvo);
                    /*사은품접수내역 테이블 정보가 존재하면, 일괄적으로 소모품비위약금액 = 30,000원을 부여한다.*/
                    if (fgptDsbCt > 0) {
                        csmbCostBorAmt = 167000;
                    }
                }
                /* 위약금액 초기화 예외 로직 및 위약금액 산출 (렌탈, 리스 공통 로직) */
                if ("2".equals(sellTpCd)) {
                    if (stplPtrm < frisuBfsvcPtrmN) {
                        resRtlfeBorAmt = 0; /* 잔여렌탈료위약금액 */
                        rgstCostDscBorAmt = 0; /* 등록비할인위약금액 */
                        rentalDscBorAmt = 0; /* 렌탈할인위약금액 */
                        csmbCostBorAmt = 0; /* 소모품비위약금액 */
                        reqdCsBorAmt = 0; /* 철거비용위약금액 */
                        pBorAmt = 0; /* 포인트위약금액 */
                    } else if ("21".equals(alncmpCd) && stplPtrm == searchSalesDvo.getRentalTn()
                        && "19".equals(dto.canTpCd())) {
                        resRtlfeBorAmt = 0; /* 잔여렌탈료위약금액 */
                        rgstCostDscBorAmt = 0; /* 등록비할인위약금액 */
                        rentalDscBorAmt = 0; /* 렌탈할인위약금액 */
                        csmbCostBorAmt = 0; /* 소모품비위약금액 */
                        reqdCsBorAmt = 0; /* 철거비용위약금액 */
                        pBorAmt = 0; /* 포인트위약금액 */
                    }
                }
                borAmt = resRtlfeBorAmt + rgstCostDscBorAmt + rentalDscBorAmt + rstlBorAmt + csmbCostBorAmt
                    + reqdCsBorAmt + pBorAmt;
                if ("Y".equals(inputDvo.getCnfmYm())) {
                    /* 위약금액 저장 : 입력값_확정여부 = 'Y'인 경우만 처리 */
                    int processCnt = 0;
                    processCnt += mapper.insertWellsBorAmtBas(resultDvo); /* 위약금액기본 저장 */
                    processCnt += mapper.insertWellsBorAmtBasHist(resultDvo); /* 위약금액기본이력 저장 */
                    BizAssert.isTrue(processCnt > 0, "MSG_ALT_BOR_AMT_CMPT_PROCS_ERR"); //위약금액 산출 처리 시 오류가 발생했습니다.
                }
            }
        }

        resultDvo.setResRtlfeBorAmt(resRtlfeBorAmt);
        resultDvo.setRgstCostDscBorAmt(rgstCostDscBorAmt);
        resultDvo.setRentalDscBorAmt(rentalDscBorAmt);
        resultDvo.setRstlBorAmt(rstlBorAmt);
        resultDvo.setCsmbCostBorAmt(csmbCostBorAmt);
        resultDvo.setPBorAmt(pBorAmt);
        resultDvo.setReqdCsBorAmt(reqdCsBorAmt);
        resultDvo.setLsRntf(lsRntf);
        resultDvo.setBorAmt(borAmt);
        return converter.mapCancellationFeeComputationResultToSearchRes(resultDvo);
    }
}
