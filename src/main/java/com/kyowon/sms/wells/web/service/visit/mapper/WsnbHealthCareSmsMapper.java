package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbHealthCareSmsDvo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 *
 * <pre>
 * W-SV-S-0038 건강케어 알림톡 발송 , 설치 후 다음날 발송 안마의자, 웰스팜, 매트리스
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2023-01-13
 */
@Mapper
public interface WsnbHealthCareSmsMapper {
    /**
     * 안마의자, 웰스팜, 매트리스 등 설치 후 건강케어 고객에게 알림톡으로 발송한다.
     */
    List<WsnbHealthCareSmsDvo> selectHealthCareSms();
}
