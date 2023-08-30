package com.kyowon.sms.wells.web.customer.common.service;

import com.kyowon.sms.wells.web.customer.common.converter.WcszPartnerSearchConverter;
import com.kyowon.sms.wells.web.customer.common.dto.WcszPartnerSearchDto.*;
import com.kyowon.sms.wells.web.customer.common.dvo.*;
import com.kyowon.sms.wells.web.customer.common.mapper.WcszPartnerSearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @name : WcszPartnerSearchService
 * @description : 파트너 정보 (EP) 조회
 */
@Service
@RequiredArgsConstructor
public class WcszPartnerSearchService {

    private final WcszPartnerSearchMapper mapper;
    private final WcszPartnerSearchConverter converter;

    /**
    * EP 및 조직 정보 조회 - 사번으로 파트너(EP) 정보 조회
    * @param : 조직유형코드 (ogTpCd), 파트너번호 (prtnrNo)
    * @return : 파트너정보
    * @description : (Controller서비스)입력받은 파트너사번으로 월파트너 내역의 파트너정보와 상위조직, 상위조직장, 연락처 정보등을 조회한다
    */
    public SearchPartnerByPkRes getPartnerByPk(SearchPartnerByPkReq dto) {
        WcszPartnerDvo dvo = this.getPartnerByPk(dto.ogTpCd(), dto.prtnrNo());
        return converter.mapWcszPartnerDvoToSearchPartnerByPkRes(dvo);
    }

        /**
    * EP 및 조직 정보 조회 - 사번으로 파트너(EP) 정보 조회
    * @param : 조직유형코드 (ogTpCd), 파트너번호 (prtnrNo)
    * @return : 파트너정보
    * @description : (공통서비스)입력받은 파트너사번으로 월파트너 내역의 파트너정보와 상위조직, 상위조직장, 연락처 정보등을 조회한다
    */
    public WcszPartnerDvo getPartnerByPk(
        String ogTpCd, String prtnrNo
    ) {
        return mapper.selectPartnerByPk(ogTpCd, prtnrNo);
    }

}
