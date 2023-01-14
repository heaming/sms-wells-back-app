package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIotProductIstBiztalkDvo;

/**
 * <pre>
 * W-SV-S-0039 IoT 제품 설치 완료 후, 익일 오후 3시에 알림톡 발송
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.01.11
 */
@Mapper
public interface WsnbIotProductIstBiztalkMapper {

    List<WsnbIotProductIstBiztalkDvo> selectBiztalkTargets();

}
