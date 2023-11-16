package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageCtrQtyRegDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaSeedingPackageCtrQtyRegConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingPackageCtrQtyRegDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedingPackageCtrQtyRegMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0298P01 모종패키지 조정 수량 등록 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-25
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedingPackageCtrQtyRegService {

    private final WsnaSeedingPackageCtrQtyRegMapper mapper;

    private final WsnaSeedingPackageCtrQtyRegConverter converter;

    /**
     * 센터별 요일코드 조회
     * @param dgGgLctCd
     * @return
     */
    public List<String> getCnrSppDowDvCd(String dgGgLctCd) {
        return this.mapper.selectCnrSppDowDvCd(dgGgLctCd);
    }

    /**
     * 모종패키지 조정 수량 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedingPackageCtrQtys(SearchReq dto) {
        return this.mapper.selectSeedingPackageCtrQtys(dto);
    }

    /**
     * 모종패키지 조정 수량 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int saveSeedingPackageCtrQtys(List<SaveReq> dtos) {

        int count = 0;

        List<WsnaSeedingPackageCtrQtyRegDvo> dvos = this.converter.mapAllSaveReqToWsnaSeedingPackageCtrQtyRegDvo(dtos);

        for (WsnaSeedingPackageCtrQtyRegDvo dvo : dvos) {
            count += this.mapper.mergeSdingPkgQtyCtrIz(dvo);
        }

        return count;
    }

}
