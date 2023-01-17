package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitGuideSmsDvo;

/**
 * <pre>
 * W-SV-S-0032 방문일 D-1 전에 방문 안내 문자 발송
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.01.06
 */
@Mapper
public interface WsnbVisitGuideSmsMapper {

    List<WsnbVisitGuideSmsDvo> selectCustomers();

}
