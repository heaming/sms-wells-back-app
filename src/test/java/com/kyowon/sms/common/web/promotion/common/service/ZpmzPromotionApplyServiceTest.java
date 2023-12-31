package com.kyowon.sms.common.web.promotion.common.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionAtcDvo;
import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionInfoDvo;
import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionInputPdDvo;
import com.kyowon.sms.common.web.promotion.common.mapper.ZpmzPromotionApplyMapper;
import com.kyowon.sms.common.web.promotion.zcommon.constants.PmPromotionConst;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ZpmzPromotionApplyServiceTest extends SpringTestSupport {

    private static final String FILTER_KEY_NMS = "SYS_CMPP_NM";
    private static final String FILTER_KEY_PROMOTION = "PMOT_CD";
    private static final String FILTER_KEY_PRODUCT = "basepdcd";
    private static final String FILTER_KEY_PRODUCT_PRICE = "basepdprcdtlcd";
    private static final String SYS_CMPP_NM_BASE_PRODUCT_PRICE = "basePdUprc";
    private static final String SYS_CMPP_NM_LINK_ORDER_RECEIPT_DATE = "lkOrdRcpdt";
    private static final String NO_DATA = "NODATA";

    private final ZpmzPromotionApplyService service;
    private final ZpmzPromotionApplyMapper mapper;

    List<HashMap<String, Object>> testData;

    @BeforeAll
    void setData() {
        /*
         테스트용 데이터를 임의로 만들기엔 너무 많은 테이블에 데이터를 넣어야 되기 때문에
         이미 존재하는 데이터를 조회하여 그 중에 입력값에 해당하는 데이터를 발췌하는 방식으로 테스트 코드 진행
         */
        testData = mapper.selectPromotionForJunitTest();
    }

    @Test
    @DisplayName("상품별 프로모션 정보 조회 - 데이터 존재")
    void getPromotionsByPdInfo() {
        // given - 프로모션 조건 중 상품코드가 들어간 데이터 발췌하여 입력값으로 활용
        String[] pdInfo = getTestData(FILTER_KEY_PRODUCT);
        if (pdInfo != null && pdInfo.length != 2) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }
        ZpmzPromotionInputPdDvo inputDvo = new ZpmzPromotionInputPdDvo(pdInfo[0], pdInfo[1]);

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByPdInfo(inputDvo);

        // then
        log.info("▶▶▶ INPUT : {}", inputDvo.toString());
        log.info("▶▶▶ OUTPUT : {} 건", result.size());
        for (ZpmzPromotionInfoDvo dvo : result) {
            log.info("▶▶▶ OUTPUT : {}", dvo.toString());
        }
        Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("상품별 프로모션 정보 조회 - 데이터 미존재")
    void getPromotionsByPdInfoNoData() {
        // given
        ZpmzPromotionInputPdDvo inputDvo = new ZpmzPromotionInputPdDvo(NO_DATA, "");

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByPdInfo(inputDvo);

        // then
        log.info("▶▶▶ INPUT : {}", inputDvo.toString());
        log.info("▶▶▶ OUTPUT : {}", result.toString());

        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("프로모션 코드별 프로모션 정보 조회 - 데이터 존재")
    void getPromotionConditionsByPmotCd() {
        // given - 이미 존재하는 프로모션 중 임의로 하나 발췌하여 입력값(프로모션 코드)으로 사용
        String[] pmotCd = getTestData(FILTER_KEY_PROMOTION);
        if (pmotCd != null && pmotCd.length != 1) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByPmotCd(pmotCd[0]);

        // then
        log.info("▶▶▶ INPUT : {}", pmotCd);
        log.info("▶▶▶ OUTPUT : {} 건", result.size());
        for (ZpmzPromotionInfoDvo dvo : result) {
            log.info("▶▶▶ OUTPUT : {}", dvo.toString());
        }
        Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("프로모션 코드별 프로모션 정보 조회 - 데이터 미존재")
    void getPromotionConditionsByPmotCdNodata() {
        // given
        String pmotCd = NO_DATA;

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByPmotCd(pmotCd);

        // then
        log.info("▶▶▶ INPUT : {}", pmotCd);
        log.info("▶▶▶ OUTPUT : {}", result.toString());

        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("조건별 프로모션 정보 조회 - 데이터 존재")
    void getPromotionsByConditionsNormal() throws NoSuchFieldException, IllegalAccessException, ParseException {

        // given - 프로모션 조건 중 기준상품코드(basePdCd), 기준상품판매가(basePdUprc)가 포함된 프로모션 발췌하여 입력값으로 사용
        List<ZpmzPromotionAtcDvo> paramDvos = getTestData(new String[]{"basePdCd", "basePdUprc"});
        if (paramDvos == null || paramDvos.isEmpty()) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByConditions(paramDvos);

        // then
        log.info("▶▶▶ INPUT : {}", paramDvos.toString());
        log.info("▶▶▶ OUTPUT : {} 건", result.size());
        for (ZpmzPromotionInfoDvo dvo : result) {
            log.info("▶▶▶ OUTPUT : {}", dvo.toString());
        }
        Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("조건별 프로모션 정보 조회 - 데이터 미존재 - 가격조건 불일치")
    void getPromotionsByConditionsPriceError() throws NoSuchFieldException, IllegalAccessException, ParseException {

        // given - 프로모션 조건 중 기준상품코드(basePdCd), 기준상품판매가(basePdUprc)가 포함된 프로모션 발췌하여 입력값으로 사용
        List<ZpmzPromotionAtcDvo> paramDvos = getTestData(new String[]{"basePdCd", "basePdUprc"});
        if (paramDvos == null || paramDvos.isEmpty()) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        ZpmzPromotionAtcDvo changeDvo = paramDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(),SYS_CMPP_NM_BASE_PRODUCT_PRICE)).findAny().get();
        changeDvo.setVarbBasVal(String.valueOf(Integer.parseInt(changeDvo.getVarbBasVal()) - 1)); // 임의로 가격 변경

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByConditions(paramDvos);

        // then
        log.info("▶▶▶ INPUT : {}", changeDvo.toString());
        log.info("▶▶▶ OUTPUT : {}", result.toString());

        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("조건별 프로모션 정보 조회 - 데이터 미존재 - 일자조건 불일치")
    void getPromotionsByConditionsDateError() throws NoSuchFieldException, IllegalAccessException, ParseException {

        // given - 프로모션 조건 중 기준상품코드(basePdCd), 연계상품접수일(lkOrdRcpdt)가 포함된 프로모션 발췌하여 입력값으로 사용
        List<ZpmzPromotionAtcDvo> paramDvos = getTestData(new String[]{"basePdCd", "lkOrdRcpdt"});
        if (paramDvos == null || paramDvos.isEmpty()) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        ZpmzPromotionAtcDvo changeDvo = paramDvos.stream().filter(item -> StringUtils.equals(item.getSysCmppNm(),SYS_CMPP_NM_LINK_ORDER_RECEIPT_DATE)).findAny().get();
        changeDvo.setVarbBasVal(String.valueOf(Integer.parseInt(changeDvo.getVarbBasVal()) + 1)); // 임의로 일자 변경

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByConditions(paramDvos);

        // then
        log.info("▶▶▶ INPUT : {}", paramDvos.toString());
        log.info("▶▶▶ OUTPUT : {}", result.toString());

        Assertions.assertThat(result.size()).isEqualTo(0);
    }


    private String[] getTestData(String filterKey) {

        String[] result = new String[StringUtils.equals(filterKey, FILTER_KEY_PRODUCT) ? 2 : 1];

        if (testData.isEmpty()) {
            log.info("▶▶▶ Test data don't exists.");
        } else {
            if (StringUtils.equals(filterKey, FILTER_KEY_PROMOTION)) {
                result[0] = Objects.toString(testData.get(0).get(FILTER_KEY_PROMOTION), "");
            } else {
                HashMap<String, Object> param = testData.stream().filter(item -> {
                    boolean isSuccess = Objects.toString(item.get(FILTER_KEY_NMS), "").toLowerCase().contains(filterKey);
                    if (isSuccess && StringUtils.equals(filterKey, FILTER_KEY_PRODUCT)) {
                        isSuccess = Objects.toString(item.get(FILTER_KEY_NMS), "").toLowerCase().contains(FILTER_KEY_PRODUCT_PRICE);
                    }
                    return isSuccess;
                }).findAny().get();

                for (String item : Objects.toString(param.get(FILTER_KEY_NMS), "").split(",")) {
                    if (item.toLowerCase().contains(filterKey)) {
                        result[0] = item.split("\\|")[1];
                        if (!StringUtils.equals(filterKey, FILTER_KEY_PRODUCT))   break;
                    }
                    if (StringUtils.equals(filterKey, FILTER_KEY_PRODUCT) && item.toLowerCase().contains(FILTER_KEY_PRODUCT_PRICE)) {
                        result[1] = item.split("\\|")[1];
                    }
                }
            }
            ;
        }

        return result;
    }

    private List<ZpmzPromotionAtcDvo> getTestData(String[] filterKey) {

        List<ZpmzPromotionAtcDvo> resultAtcs = new ArrayList<>();

        if (testData.isEmpty()) {
            log.info("▶▶▶ Test data don't exists.");
        } else {
            // filter에 모두 충족하는 데이터 찾기
            HashMap<String, Object> paramMap = testData.stream().filter(item -> {
                String sysCmppNm = Objects.toString(item.get(FILTER_KEY_NMS), "");
                for (String key : filterKey) {
                    if (!sysCmppNm.contains(key)) {
                        return false;
                    }
                }
                return true;
            }).findAny().get();
            if (paramMap == null || paramMap.isEmpty()) {
                log.info("▶▶▶ Test data don't exists.");
            } else {
                // ZpmzPromotionInputCndtDvo 데이터 설정
                for (String item : Objects.toString(paramMap.get(FILTER_KEY_NMS), "").split(",")) {
                    String value = item.split("\\|")[1];
                    if (StringUtils.equals(item.split("\\|")[2], PmPromotionConst.VAL_CMPR_DV_LEFT)) {
                        value = String.valueOf(Integer.parseInt(value) + 1);
                    } else if (StringUtils.equals(item.split("\\|")[2], PmPromotionConst.VAL_CMPR_DV_RIGHT)) {
                        value = String.valueOf(Integer.parseInt(value) - 1);
                    }
                    resultAtcs.add(new ZpmzPromotionAtcDvo(item.split("\\|")[0], value));
                }
            }
        }

        return resultAtcs;
    }

}
