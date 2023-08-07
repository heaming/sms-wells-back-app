package com.kyowon.sms.wells.web.service.visit.mapper;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbBsOgProcsListDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbBsOgProcsListDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0238M01 B/S 처리 현황(조직)
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.02
 */
@Mapper
public interface WsnbBsOgProcsListMapper {
    /**
     * B/S 처리 현황(조직) 조회
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnbBsOgProcsListDvo> selectBsOgProcsList(SearchReq searchReq);

    List<WsnbBsOgProcsListDvo> selectCrdOvrList(SearchReq searchReq);
}
