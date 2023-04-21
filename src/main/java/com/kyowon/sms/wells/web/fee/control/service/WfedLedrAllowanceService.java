package com.kyowon.sms.wells.web.fee.control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.dto.WfedLedrAllowanceDto;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedLedrAllowanceMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 단장 수당 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfedLedrAllowanceService {

    private final WfedLedrAllowanceMapper mapper;

    /**
     * 단장 수당 목록(개인별) 조회
     * @param dto : {
     * perfYm : 실적년월,
     * rsbDvCd : 직책유형,
     * inqrDv : 조회구분,
     * no : 번호 }
     * @return 조회결과
     */
    public List<WfedLedrAllowanceDto.SearchIndividualRes> getIndividualLeaderAllowances(
        WfedLedrAllowanceDto.SearchReq dto
    ) {
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        //TODO: 세션 정보 추가하면 파라미터 DVO로 수정
        return mapper.selectIndividualLeaderAllowances(dto);
    }

    /**
     * 단장 수당 목록(합계) 조회
     * @param dto : {
     * perfYm : 실적년월,
     * rsbDvCd : 직책유형,
     * inqrDv : 조회구분,
     * no : 번호 }
     * @return 조회결과
     */
    public List<WfedLedrAllowanceDto.SearchSumRes> getSumLeaderAllowances(
        WfedLedrAllowanceDto.SearchReq dto
    ) {
        return mapper.selectSumLeaderAllowances(dto);
    }

}
