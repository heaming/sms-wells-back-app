package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaSeedReleaseScheduleConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleSearchDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedReleaseScheduleMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedReleaseScheduleService {

    private final WsnaSeedReleaseScheduleMapper maaper;

    private final WsnaSeedReleaseScheduleConverter converter;

    /**
     * 모종 출고 예정 리스트 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getSeedReleaseSchedulesPaging(
        SearchReq dto, PageInfo pageInfo
    ) {

        PagingResult<WsnaSeedReleaseScheduleSearchDvo> pageRes = this.maaper
            .selectSeedReleaseSchedulesPaging(dto, pageInfo);

        List<WsnaSeedReleaseScheduleSearchDvo> dvos = pageRes.getList();
        List<SearchRes> searchRes = this.converter.mapAllWsnaSeedReleaseScheduleSearchDvoToSearchRes(dvos);

        return new PagingResult<>(searchRes, pageInfo);
    }

    /**
     * 모종 출고 예정 리스트 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedReleaseSchedulesExcelDownload(SearchReq dto) {

        List<WsnaSeedReleaseScheduleSearchDvo> dvos = this.maaper.selectSeedReleaseSchedulesPaging(dto);

        return this.converter.mapAllWsnaSeedReleaseScheduleSearchDvoToSearchRes(dvos);
    }

    /**
     * 모종 출고 예정 리스트 저장
     * @param dtos
     * @return
     */
    public int editSeedReleaseSchedules(List<EditReq> dtos) {

        int count = 0;

        for (EditReq dto : dtos) {
            WsnaSeedReleaseScheduleDvo dvo = this.converter.mapEditReqToWsnaSeedReleaseScheduleDvo(dto);

            int result = this.maaper.updateSdingSppPlanIzDpDt(dvo);
            // 저장에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            count += result;
        }

        return count;
    }

}
