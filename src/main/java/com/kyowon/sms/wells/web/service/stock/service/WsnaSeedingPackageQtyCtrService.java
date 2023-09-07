package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageQtyCtrDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageQtyCtrDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedingPackageQtyCtrMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0298M01 모종 패키지 수량 조정 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-24
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedingPackageQtyCtrService {

    private final WsnaSeedingPackageQtyCtrMapper mapper;

    /**
     * 제외수량 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedingPackageQtyCtrExcdQtys(SearchReq dto) {
        return this.mapper.selectSeedingPackageQtyCtrExcdQtys(dto);
    }

    /**
     * 추가수량 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedingPackageQtyCtrSpmtQtys(SearchReq dto) {
        return this.mapper.selectSeedingPackageQtyCtrSpmtQtys(dto);
    }

}
