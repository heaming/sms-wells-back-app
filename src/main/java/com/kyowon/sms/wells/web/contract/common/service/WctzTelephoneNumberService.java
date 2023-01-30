package com.kyowon.sms.wells.web.contract.common.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.dto.WctzTelephoneNumberDto.SearchMpnoRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzTelephoneNumberDto.SearchTnoRes;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzTelephoneNumberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzTelephoneNumberService {

    private final WctzTelephoneNumberMapper mapper;

    /**
     * 계약번호로 계약자의 전화번호를 조회한다.
     * @param cntrNo 계약번호
     * @return 전화번호 DTO
     */
    public SearchTnoRes getContractorTnoByCntr(String cntrNo) {
        return mapper.selectContractorTnoByCntr(cntrNo);
    }

    /**
     * 계약번호로 계약자의 휴대전화번호를 조회한다.
     * @param cntrNo 계약번호
     * @return 전화번호 DTO
     */
    public SearchMpnoRes getContractorMpnoByCntr(String cntrNo) {
        return mapper.selectContractorMpnoByCntr(cntrNo);
    }

    /**
     * 계약번호, 일련번호로 설치자의 전화번호를 조회한다.
     * @param dtlCntrNo 상세계약번호
     * @param dtlCntrSn 상세계약일련번호
     * @return 전화번호 DTO
     */
    public SearchTnoRes getInstallerTnoByCntr(String dtlCntrNo, int dtlCntrSn) {
        return mapper.selectInstallerTnoByCntr(dtlCntrNo, dtlCntrSn);
    }

    /**
     * 계약번호, 일련번호로 설치자의 휴대전화번호를 조회한다.
     * @param dtlCntrNo 상세계약번호
     * @param dtlCntrSn 상세계약일련번호
     * @return 전화번호 DTO
     */
    public SearchMpnoRes getInstallerMpnoByCntr(String dtlCntrNo, int dtlCntrSn) {
        return mapper.selectInstallerMpnoByCntr(dtlCntrNo, dtlCntrSn);
    }

    /**
     * 계약번호로 파트너의 휴대전화번호를 조회한다.
     * @param cntrNo 계약번호
     * @return 전화번호 DTO
     */
    public SearchMpnoRes getPartnerMpnoByCntr(String cntrNo) {
        return mapper.selectPartnerMpnoByCntr(cntrNo);
    }
}
