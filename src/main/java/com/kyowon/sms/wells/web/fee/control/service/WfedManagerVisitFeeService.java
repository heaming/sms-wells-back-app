package com.kyowon.sms.wells.web.fee.control.service;

import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchReq;
import static com.kyowon.sms.wells.web.fee.control.dto.WfedManagerVisitFeeDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.converter.WfedManagerVisitFeeConverter;
import com.kyowon.sms.wells.web.fee.control.dvo.WfedManagerVisitFeeDvo;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedManagerVisitFeeMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 웰스매니저 방문수수료 현황
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.22
 */
@Service
@RequiredArgsConstructor
public class WfedManagerVisitFeeService {

    private final WfedManagerVisitFeeMapper mapper;

    private final WfedManagerVisitFeeConverter converter;

    public List<SearchRes> getManagerVisitFees(SearchReq dto) {
        int cnt = mapper.selectManagerVisitFeeAgrgYn(dto);

        // 월단위 집계가 완료된 후에는 월단위 기준으로 조회
        // 월단위 집계를 하지 않았으면 일단위 기준으로 조회
        WfedManagerVisitFeeDvo dvo = converter.mapSaveReqToWfedManagerVisitFeeDvo(dto);
        if (cnt > 0)
            dvo.setTableName("TB_FEAM_WELS_SV_PERF_IZ"); // 웰스서비스실적내역
        else
            dvo.setTableName("TB_FEAM_WELS_SV_PED_IZ"); //웰스서비스실적일내역

        return mapper.selectManagerVisitFees(dvo);
    }
}
