package com.kyowon.sms.common.web.promotion.common.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionInfoDvo;
import com.kyowon.sms.common.web.promotion.common.dvo.ZpmzPromotionInputCndtDvo;
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
    void getPromotionsByConditionsNormal() throws NoSuchFieldException, IllegalAccessException {

        // given - 프로모션 조건 중 기준상품코드(basePdCd), 기준상품판매가(basePdUprc)가 포함된 프로모션 발췌하여 입력값으로 사용
        ZpmzPromotionInputCndtDvo paramDvo = getTestData(new String[]{"basePdCd", "basePdUprc"});
        if (paramDvo == null) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByConditions(paramDvo);

        // then
        log.info("▶▶▶ INPUT : {}", paramDvo.toString());
        log.info("▶▶▶ OUTPUT : {} 건", result.size());
        for (ZpmzPromotionInfoDvo dvo : result) {
            log.info("▶▶▶ OUTPUT : {}", dvo.toString());
        }
        Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("조건별 프로모션 정보 조회 - 데이터 미존재 - 가격조건 불일치")
    void getPromotionsByConditionsPriceError() throws NoSuchFieldException, IllegalAccessException {

        // given - 프로모션 조건 중 기준상품코드(basePdCd), 기준상품판매가(basePdUprc)가 포함된 프로모션 발췌하여 입력값으로 사용
        ZpmzPromotionInputCndtDvo paramDvo = getTestData(new String[]{"basePdCd", "basePdUprc"});
        if (paramDvo == null) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        paramDvo.setBasePdUprc(String.valueOf(Integer.parseInt(paramDvo.getBasePdUprc()) - 1)); // 임의로 가격 변경

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByConditions(paramDvo);

        // then
        log.info("▶▶▶ INPUT : {}", paramDvo.toString());
        log.info("▶▶▶ OUTPUT : {}", result.toString());

        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("조건별 프로모션 정보 조회 - 데이터 미존재 - 일자조건 불일치")
    void getPromotionsByConditionsDateError() throws NoSuchFieldException, IllegalAccessException {

        // given - 프로모션 조건 중 기준상품코드(basePdCd), 연계상품접수일(lkOrdRcpdt)가 포함된 프로모션 발췌하여 입력값으로 사용
        ZpmzPromotionInputCndtDvo paramDvo = getTestData(new String[]{"basePdCd", "lkOrdRcpdt"});
        if (paramDvo == null) {
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        paramDvo.setLkOrdRcpdt(String.valueOf(Integer.parseInt(paramDvo.getLkOrdRcpdt()) + 1)); // 임의로 일자 변경

        // when
        List<ZpmzPromotionInfoDvo> result = service.getPromotionsByConditions(paramDvo);

        // then
        log.info("▶▶▶ INPUT : {}", paramDvo.toString());
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

    private ZpmzPromotionInputCndtDvo getTestData(String[] filterKey) throws NoSuchFieldException, IllegalAccessException {

        ZpmzPromotionInputCndtDvo resultDvo = null;

        if (testData.isEmpty()) {
            log.info("▶▶▶ Test data don't exists.");
        } else {
            // filter에 모두 충족하는 데이터 찾기
            List<HashMap<String, Object>> params = testData.stream().filter(item -> {
                String sysCmppNm = Objects.toString(item.get(FILTER_KEY_NMS), "");
                for (String key : filterKey) {
                    if (!sysCmppNm.contains(key)) {
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
            if (params != null && params.isEmpty()) {
                log.info("▶▶▶ Test data don't exists.");
            } else {
                // ZpmzPromotionInputCndtDvo 데이터 설정
                HashMap<String, Object> param = params.get(0);
                resultDvo = new ZpmzPromotionInputCndtDvo();
                for (String item : Objects.toString(param.get(FILTER_KEY_NMS), "").split(",")) {
                    Field field = resultDvo.getClass().getDeclaredField(item.split("\\|")[0]);
                    field.setAccessible(true);
                    String value = item.split("\\|")[1];
                    if (StringUtils.equals(item.split("\\|")[2], PmPromotionConst.VAL_CMPR_DV_LEFT)) {
                        value = String.valueOf(Integer.parseInt(value) + 1);
                    } else if (StringUtils.equals(item.split("\\|")[2], PmPromotionConst.VAL_CMPR_DV_RIGHT)) {
                        value = String.valueOf(Integer.parseInt(value) - 1);
                    }
                    field.set(resultDvo, value);
                }
            }
        }

        return resultDvo;
    }

}
