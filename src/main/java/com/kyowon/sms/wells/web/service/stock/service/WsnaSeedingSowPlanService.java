package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingSowPlanDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingSowPlanDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedingSowPlanMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0130M01 모종 파종 예정리스트 조회 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-06
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedingSowPlanService {

    private final WsnaSeedingSowPlanMapper mapper;

    /**
     * 모종 파종 예정리스트 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getSeedingSowPlansPaging(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectSeedingSowPlansPaging(dto, pageInfo);
    }

    /**
     * 모종 파종 예정리스트 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedingSowPlansExcelDownload(SearchReq dto) {
        return this.mapper.selectSeedingSowPlansPaging(dto);
    }

}
