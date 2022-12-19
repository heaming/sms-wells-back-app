/*
 ****************************************************************************************************
 * 프로그램 개요
 ****************************************************************************************************
 1. 모듈 : SNY (기준정보)
 2. 프로그램 ID : W-SV-U-0016M01 AS 코드관리
 3. 작성자 : gs.piit122
 4. 작성일 : 2022.11.08
 ****************************************************************************************************
 * 프로그램 설명
 ****************************************************************************************************
 - AS코드관리 (http://localhost:3000/#/service/wwsny-after-service-code-mgt)
 ****************************************************************************************************
 */
package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAfterServiceCodeDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAfterServiceCodeDvo;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnyAfterServiceCodeConverter {

    List<WsnyAfterServiceCodeDto.SearchRes> mapAllSearchResToDvo(List<WsnyAfterServiceCodeDvo> dvoList);
}
