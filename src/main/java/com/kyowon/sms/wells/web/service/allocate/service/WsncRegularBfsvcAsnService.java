package com.kyowon.sms.wells.web.service.allocate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRegularBfsvcAsnConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncRegularBfsvcAsnService {

    private final WsncSpecCustAsnService wsncSpecCustAsnService;    //[W-SV-S-0027] 특정고객 배정 인서트

    private final WsncSpecCustPlanMatService wsncSpecCustPlanMatService;    //[W-SV-S-0029] 특정고객 예정자재 인서트

    private final WsncSpecCustMngrAsnService wsncSpecCustMngrAsnService;    //[W-SV-S-0031] 특정고객 담당자 지정 BS 오더 생성

    private final WsncRegularBfsvcAsnConverter converter;

    @Transactional
    public int processRegularBfsvcAsn(WsncRegularBfsvcAsnDto.SaveProcessReq dto) throws Exception {

        /*
         * 아래의 step 이 모두 완료되어야 정상 처리한다. (All or nothing)
         * - 아래의 서비스에 배정년월과 담당자사번, 정기BS 배정할 계약 정보를 파라미터로 전달한다.
         * 1. 특정고객 배정 인서트 (W-SV-S-0027) 를 수행한다.
         * 2. 특정고객 예정자재 인서트 (W-SV-S-0029) 서비스를 수행한다.
         * 3. 특정고객 담당자 지정 BS 오더 생성 서비스(W-SV-S-0031)를 수행한다.
         * 4. 성공/실패 여부를 리턴한다. (성공: S, 실패: F)
         */

        //Step 1. [W-SV-S-0027] ::: 특정고객 배정 인서트를 수행한다.
        wsncSpecCustAsnService.processSpecCustAsn(converter.mapSaveProcessReqToSpecCustAsnDvo(dto));

        //Step 2. [W-SV-S-0029] ::: 특정고객 예정자재 인서트 서비스를 수행한다.
        wsncSpecCustPlanMatService.processSpecCustPlanMat(converter.mapSaveProcessReqToSpecCustPlanMatDvo(dto));

        //Step 3. [W-SV-S-0031] ::: 특정고객 담당자 지정 BS 오더 생성 서비스를 수행한다.
        wsncSpecCustMngrAsnService.processSpecCustAsn(converter.mapSaveProcessReqToSpecCustMngrAsnDvo(dto));


        return 1;
    }
}
