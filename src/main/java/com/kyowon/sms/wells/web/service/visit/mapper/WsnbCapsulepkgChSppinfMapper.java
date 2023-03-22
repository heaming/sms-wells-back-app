package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCapsulepkgChSppinfDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCapsulepkgChSppinfDto.SearchRes;

/**
 * <pre>
 * W-SV-I-0020 Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.03.20
 */
@Mapper
public interface WsnbCapsulepkgChSppinfMapper {

    List<SearchRes> selectCapsulepkgChSppinfs(SearchReq dto);

}
