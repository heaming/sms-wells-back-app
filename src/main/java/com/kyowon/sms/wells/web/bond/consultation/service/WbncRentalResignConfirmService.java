package com.kyowon.sms.wells.web.bond.consultation.service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncRentalResignConfirmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WbncRentalResignConfirmService {
    private final WbncRentalResignConfirmMapper mapper;

    /**
     * <pre>
     * 직권해지 (렌탈) 확정 조회
     * </pre>
     *
     * @param dto authRsgCnfmdtStart 확정년월시작(필수)
     *            authRsgCnfmdtEnd 확정년월종료(필수)
     *            clctamDvCd 집금구분코드
     *            clctamPrtnrNo 집금담당자
     *            cstNo 고객번호
     *            cntrNo 계약번호
     *            cntrSn 계약일련번호
     * @author sieun.bae
     * @since 2023-10-17
     */
    public List<SearchRes> getRentalResignConfirms(SearchReq dto) {
        return mapper.selectRentalResignConfirms(dto);
    }
}
