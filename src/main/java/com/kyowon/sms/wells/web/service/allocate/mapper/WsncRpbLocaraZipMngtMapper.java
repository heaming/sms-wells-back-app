/*
 ****************************************************************************************************
 * 프로그램 개요
 ****************************************************************************************************
 1. 모듈 : SNC (배정관리)
 2. 프로그램 ID : W-SV-U-0036M01 AS 책임지역 우편번호 관리
 3. 작성자 : gs.piit130
 4. 작성일 : 2022.11.17
 ****************************************************************************************************
 * 프로그램 설명
 ****************************************************************************************************
 - 책임지역 우편번호 관리 (http://localhost:3000/#/service/wwsnc-responsibility-local-area-zip-mgt)
 ****************************************************************************************************
 */
package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraZipMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraZipMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;

@Mapper
public interface WsncRpbLocaraZipMngtMapper {

    List<WsncRpbLocaraZipMngtDvo> selectRpbLocaraZips(WsncRpbLocaraZipMngtDto.SearchReq dto, PageInfo pageInfo);

    List<WsncRpbLocaraZipMngtDvo> selectRpbLocaraZips(WsncRpbLocaraZipMngtDto.SearchReq dto);

}
