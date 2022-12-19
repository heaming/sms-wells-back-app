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
package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAfterServiceCodeDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAfterServiceCodeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnyAfterServiceCodeMapper {

    List<WsnyAfterServiceCodeDvo> selectAfterServiceCode(
        WsnyAfterServiceCodeDto.SearchReq dto, PageInfo pageInfo
    );
}
