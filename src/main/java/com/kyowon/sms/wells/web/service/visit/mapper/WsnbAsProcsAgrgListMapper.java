package com.kyowon.sms.wells.web.service.visit.mapper;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbAsProcsAgrgListDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAsProcsAgrgListDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0304M01 A/S 처리 현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.07.21
 */
@Mapper
public interface WsnbAsProcsAgrgListMapper {
    /**
     * A/S 처리 현황 조회
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnbAsProcsAgrgListDvo> selectAsProcsAgrgList(SearchReq searchReq);

    List<WsnbAsProcsAgrgListDvo> selectBsProcsAgrgList(SearchReq searchReq);

}
