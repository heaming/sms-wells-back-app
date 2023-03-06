package com.kyowon.sms.wells.web.fee.control.service;

import java.util.List;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.dto.WfedWelsMngerBsFeeDto.*;
import com.kyowon.sms.wells.web.fee.control.dvo.WfedWelsMngerBsFeeDvo;
import com.kyowon.sms.wells.web.fee.control.converter.WfedWelsMngerBsFeeConverter;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedWelsMngerBsFeeMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfedWelsMngerBsFeeService {
    private final WfedWelsMngerBsFeeMapper mapper;
    private final WfedWelsMngerBsFeeConverter converter;

    /**
     * WM방문실적 수수료관리 인사정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHumanRes getHuman(SearchReq dto) {
        return mapper.selectHuman(dto);
    }

    /**
     * WM방문실적 수수료관리 방문실적 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchVisitRes> getVisits(SearchReq dto) {
        return mapper.selectVisits(dto);
    }

    /**
     * WM방문실적 수수료관리 수수료 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchFeeRes> getFees(SearchReq dto) {
        return mapper.selectFees(dto);
    }

    /**
     * WM방문실적 수수료관리 방문실적 집계 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindVisitAgrgRes getVisitAgrg(SearchReq dto) {
        return mapper.selectVisitAgrg(dto);
    }

    /**
     * WM방문실적 수수료관리 수수료 집계 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindFeeAgrgRes getFeeAgrg(SearchReq dto) {
        return mapper.selectFeeAgrg(dto);
    }

    /**
     * WM방문실적 수수료관리 수수료 집계 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public int saveFee(SaveFeeReq dto) {

        int processCount = 0;

        WfedWelsMngerBsFeeDvo dvo = converter.mapSaveFeeReqToWfedWelsMngerBsFeeDvo(dto);

        mapper.deleteFee(dvo);
        processCount = mapper.insertFee(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_SVE_ERR");

        return processCount;
    }

}
