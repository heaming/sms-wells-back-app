package com.kyowon.sms.wells.web.service.allocate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBeforeServiceAsnBatDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsncBeforeServiceAsnBatService {

    @Transactional
    public int processBeforeServiceAsnBat(WsncBeforeServiceAsnBatDto.SaveProcessReq dto) throws Exception {

        /*
         * 해당 배치는 조직 파트의 월조직마감 배치가 수행된 후 3시간 뒤에 작업시작 예정 (3영업일 이전에 작업 생성 요청 - 정확한 시간은 추후 정의)
         * 아래의 step 이 모두 완료되어야 정상 처리한다. (All or nothing)
         * - 아래의 서비스에 배정년월과 담당자사번 데이터만 파라미터로 전달한다. (특정 계약번호를 입력하지 않은 경우 해당 배정년월의 전체 대상으로 서비스를 수행한다.)
         * 1. 특정고객 배정 인서트 (W-SV-S-0027) 를 수행한다.
         * 2. 특정고객 예정자재 인서트 (W-SV-S-0029) 서비스를 수행한다.
         * 3. 특정고객 담당자 지정 BS 오더 생성 서비스(W-SV-S-0031)를 수행한다.
         * 4. 성공/실패 확인 후 배치로그를 생성한다.
         */

        //Step 1. [W-SV-S-0027] ::: 특정고객 배정 인서트를 수행한다.


        //Step 2. [W-SV-S-0029] ::: 특정고객 예정자재 인서트 서비스를 수행한다.


        //Step 3. [W-SV-S-0031] ::: 특정고객 담당자 지정 BS 오더 생성 서비스를 수행한다.


        return 1;
    }
}
