package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncIntergratedQrProcsListSearchDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncIntergratedQrProcsListSearchDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0239M01 통합QR 처리현황
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.25
 */
@Mapper
public interface WsncIntergratedQrProcsListSearchMapper {
    /**
     * B/S 처리 현황(조직) 조회
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsncIntergratedQrProcsListSearchDvo> selectByOgList(SearchReq searchReq);

    List<WsncIntergratedQrProcsListSearchDvo> selectOgDetailList(SearchReq searchReq);
}
