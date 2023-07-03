package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCancelPresentStateMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCancelPresentStateService {

    private final WctbCancelPresentStateMapper mapper;

    /**
     * 프로그램ID : W-SS-U-0160M01
     *  - 취소현황 조회(렌탈)
     * @param dto
     * @return list
     */
    public List<WctbCancelPresentStateDto.SearchRentalRes> getRentalCancelPresentStates(WctbCancelPresentStateDto.SearchReq dto) {
        return mapper.selectRentalCancelPresentStates(dto);
    }

    /**
     * 프로그램ID : W-SS-U-0160M02
     *  - 정기배송 취소현황
     * @param dto
     * @return list
     */
    public List<WctbCancelPresentStateDto.SearchRegularShippingRes> getRegularShippingCancelPresentStates(WctbCancelPresentStateDto.SearchReq dto) {
        return mapper.selectRegularShippingCancelPresentStates(dto);
    }

    /**
     * 프로그램ID : W-SS-U-0160M04
     *  - 멤버십 취소현황
     * @param dto
     * @return list
     */
    public List<WctbCancelPresentStateDto.SearchMembershipRes> getMembershipCancelPresentStates(WctbCancelPresentStateDto.SearchReq dto) {
        return mapper.selectMembershipCancelPresentStates(dto);
    }
}
