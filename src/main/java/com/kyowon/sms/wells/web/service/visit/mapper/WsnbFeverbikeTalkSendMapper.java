package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFeverbikeTalkSendDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbFeverbikeTalkSendDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * <pre>
 * W-SV-S-0037 피버바이크 알림톡 발송
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2023-01-11
 */
@Mapper
public interface WsnbFeverbikeTalkSendMapper {

    /**
     * 피버 바이크 플러스 온라인 강의 무료 구독 신청완료 후 고객에게 알림톡으로 발송한다.
     *
     * @return 변경 개수
     */
    List<WsnbFeverbikeTalkSendDvo> selectFeverbikeTalkSendTarget();
}
