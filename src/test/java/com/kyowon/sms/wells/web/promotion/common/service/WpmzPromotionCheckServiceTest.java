package com.kyowon.sms.wells.web.promotion.common.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.kyowon.sms.wells.web.promotion.zcommon.constants.PmPromotionConst;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.kyowon.sms.common.web.promotion.common.mapper.ZpmzPromotionApplyMapper;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionInputDvo;
import com.kyowon.sms.wells.web.promotion.common.dvo.WpmzPromotionOutputDvo;
import com.sds.sflex.system.config.test.SpringTestSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WpmzPromotionCheckServiceTest extends SpringTestSupport {

    private static final String FILTER_KEY_NMS = "SYS_CMPP_NM";
    private static final String FILTER_FVR_KEY_NMS = "FVR_SYS_CMPP_NM";
    private static final String VAL_CMPR_DV_LEFT = "05"; // ">"
    private static final String VAL_CMPR_DV_RIGHT = "06"; //"<"

    private final WpmzPromotionCheckService service;
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
    @DisplayName("적용 프로모션 조회 - 데이터 존재")
    void getAppliedPromotions() throws NoSuchFieldException, IllegalAccessException {

        // given
        WpmzPromotionInputDvo paramDvo = getTestData(new String[]{"basePdPrcDtlCd"}, true);
        if (paramDvo == null && StringUtils.isEmpty(paramDvo.getBasePdPrcDtlCd())){
            log.info("▶▶▶ Test data don't exists.");
            return;
        }

        // when
        List<WpmzPromotionOutputDvo> result = service.getAppliedPromotions(paramDvo);

        // then
        log.info("▶▶▶ INPUT : {}", paramDvo.toString());
        log.info("▶▶▶ OUTPUT : {} 건", result.size());
        log.info("▶▶▶ OUTPUT : {}", result.toString());

        Assertions.assertThat(result.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("적용 프로모션 조회 - 데이터 미존재")
    void getAppliedPromotionsNoData() throws NoSuchFieldException, IllegalAccessException {

        // given
        WpmzPromotionInputDvo paramDvo = new WpmzPromotionInputDvo();
        paramDvo.setBasePdCd("NODATA");

        // when
        List<WpmzPromotionOutputDvo> result = service.getAppliedPromotions(paramDvo);

        // then
        log.info("▶▶▶ INPUT : {}", paramDvo.toString());
        log.info("▶▶▶ OUTPUT : {} 건", result.size());
        log.info("▶▶▶ OUTPUT : {}", result.toString());

        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("적용 프로모션 조회 - 필수항목 누락")
    void getAppliedPromotionsMissingRequiredParameters() {

        // given
        WpmzPromotionInputDvo paramDvo = new WpmzPromotionInputDvo();
        paramDvo.setChdvcPrmitYn("A");
        log.info("▶▶▶ Required Parameter : {}", PmPromotionConst.MANDATORY_INPUT_ATCS.toString());

        // when / then
        log.info("▶▶▶ INPUT : {}", paramDvo.toString());
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.getAppliedPromotions(paramDvo));
    }

    private WpmzPromotionInputDvo getTestData(String[] filterKey, boolean isExistFreeGift) throws NoSuchFieldException, IllegalAccessException {
        WpmzPromotionInputDvo resultDvo = new WpmzPromotionInputDvo();
        if (testData.isEmpty()) {
            log.info("▶▶▶ Test data don't exists.");
        } else {
            // filter에 모두 충족하는 데이터 찾기
            HashMap<String, Object> paramMap = testData.stream().filter(item -> {
                String sysCmppNm = Objects.toString(item.get(FILTER_KEY_NMS), "");
                String fvrSysCmppNm = Objects.toString(item.get(FILTER_FVR_KEY_NMS), "");
                boolean isPass = false;
                for (String key : filterKey) {
                    if (sysCmppNm.contains(key)) {
                        if (!isExistFreeGift || fvrSysCmppNm.contains(PmPromotionConst.SYS_CMPP_NM_FREE_GIFT)) {
                            isPass = true;
                        }
                    }
                }
                return isPass;
            }).findAny().get();
            if (paramMap == null || paramMap.isEmpty()) {
                log.info("▶▶▶ Test data don't exists.");
            } else {
                // WpmzPromotionInputDvo 데이터 설정
                List<String> dvoNames = Arrays.stream(resultDvo.getClass().getDeclaredFields()).map(fd -> fd.getName()).toList();
                for (String item : Objects.toString(paramMap.get(FILTER_KEY_NMS), "").split(",")) {

                    String value = item.split("\\|")[1];
                    if (StringUtils.equals(item.split("\\|")[2], VAL_CMPR_DV_LEFT)) {
                        value = String.valueOf(Integer.parseInt(value) + 1);
                    } else if (StringUtils.equals(item.split("\\|")[2], VAL_CMPR_DV_RIGHT)) {
                        value = String.valueOf(Integer.parseInt(value) - 1);
                    }

                    if (dvoNames.contains(item.split("\\|")[0])) {
                        Field field = resultDvo.getClass().getDeclaredField(item.split("\\|")[0]);
                        field.setAccessible(true);
                        field.set(resultDvo, value);
                    }
                }
            }
        }
        return resultDvo;
    }
}
