package com.kyowon.sms.wells.web.deduction.redf.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchMutualAidFeeReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchTotalMutualAidFeeForExcelDownloadRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WwdeaMutualAidFeeMgtDto.SearchTotalMutualAidFeeRes;
import com.kyowon.sms.wells.web.deduction.redf.mapper.WwdeaMutualAidFeeMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdeaMutualAidFeeMgtService {
    private final WwdeaMutualAidFeeMgtMapper mapper;

    /**
     * 상조 되물림생성 조회
     * @param dto
     * @return SearchTotalMutualAidFeeReq
     */
    public SearchTotalMutualAidFeeRes getMutualAidFees(SearchMutualAidFeeReq dto, PageInfo pageInfo) {
        if ("01".equals(dto.dvCd())) {
            return SearchTotalMutualAidFeeRes.builder()
                .mutualAidFee(mapper.selectMutalAids(dto, pageInfo))
                .build();
        } else {
            return SearchTotalMutualAidFeeRes.builder()
                .mutualAidFeeSub(mapper.selectMutalAidsSub(dto, pageInfo))
                .build();
        }
    }

    /**
     * 상조 되물림생성 조회 엑셀 다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public SearchTotalMutualAidFeeForExcelDownloadRes getMutualAidFeeForExcelDownload(SearchMutualAidFeeReq dto) {
        if ("01".equals(dto.dvCd())) {
            return SearchTotalMutualAidFeeForExcelDownloadRes.builder()
                .mutualAidFeeForExcelDownload(mapper.selectMutalAids(dto))
                .build();
        } else {
            return SearchTotalMutualAidFeeForExcelDownloadRes.builder()
                .mutualAidFeeSubForExcelDownload(mapper.selectMutalAidsSub(dto))
                .build();
        }
    }
}
