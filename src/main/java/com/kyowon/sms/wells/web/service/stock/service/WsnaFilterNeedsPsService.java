package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterNeedsPsDto.SearchReq;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaFilterNeedsPsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaFilterNeedsPsMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0278M01 필터소요 현황(교체주기) 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

@Service
@RequiredArgsConstructor
public class WsnaFilterNeedsPsService {

    private final WsnaFilterNeedsPsMapper mapper;

    /**
     * 필터소요 현황(교체주기) 조회
     * @param dto
     * @return
     */
    public List<WsnaFilterNeedsPsDvo> getFilterNeedsState(SearchReq dto) {

        // b2b 관리코드
        String b2bMngtCd = dto.b2bMngtCd();
        if (StringUtils.isNotEmpty(b2bMngtCd) && List.of("A", "P").contains(b2bMngtCd)) {
            return this.mapper.selectFilterNeedsStateForB2B(dto);
        } else {
            return this.mapper.selectFilterNeedsState(dto);
        }
    }

}
