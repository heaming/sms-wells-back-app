package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCosmeticRegularSchdDvo;

/**
 * TODO: 필요 서비스 개발 완료 후 추가 작업
 * <pre>
 * W-SV-S-0050 화장품 캡슐 정기구매 고객 스케줄 INSERT/UPDATE
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.01.25
 */
@Mapper
public interface WsnbCosmeticRegularSchdMapper {

    List<WsnbCosmeticRegularSchdDvo> selectCosmeticRegularSchedules();

    void deleteCosmeticRegularSchedule(WsnbCosmeticRegularSchdDvo dvo);

    void updateCosmeticRegularSchedule(WsnbCosmeticRegularSchdDvo dvo);

    void mergeCosmeticRegularSchedules(WsnbCosmeticRegularSchdDvo dvo);

}
