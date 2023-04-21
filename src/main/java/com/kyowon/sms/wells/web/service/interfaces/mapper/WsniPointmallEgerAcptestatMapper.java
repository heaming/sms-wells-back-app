package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniPointmallEgerAcptestatDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniPointmallEgerAcptestatDto.SearchRes;

/**
 * <pre>
 * W-SV-I-0018 포인트몰 금융리스 안마의자,전기레인지 엔지니어 수락상태값 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.04.20
 */
@Mapper
public interface WsniPointmallEgerAcptestatMapper {

    List<SearchRes> selectPointmallEgerAcptestats(SearchReq dto);

}
