package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnItemDto.FindDetailRes;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnItemDto.FindRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaQomAsnItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 물량배정 품목 조회 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-09
 */

@Service
@RequiredArgsConstructor
public class WsnaQomAsnItemService {

    private final WsnaQomAsnItemMapper mapper;

    /**
     * 물량배정 품목 조회 상단 정보 조회
     * @param itmOstrNo
     * @return
     */
    public FindRes getQomAsnItemMasterInfo(String itmOstrNo) {
        return this.mapper.selectQomAsnItemMasterInfo(itmOstrNo);
    }

    /**
     * 물량배정 품목 조회
     * @param itmOstrNo
     * @return
     */
    public List<FindDetailRes> getQomAsnItems(String itmOstrNo) {
        return this.mapper.selectQomAsnItems(itmOstrNo);
    }

}
