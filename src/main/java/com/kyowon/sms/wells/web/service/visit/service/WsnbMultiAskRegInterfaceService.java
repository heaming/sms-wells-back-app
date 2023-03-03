package com.kyowon.sms.wells.web.service.visit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultiAskRegInterfaceDto.AskingInfo;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultiAskRegInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultiAskRegInterfaceDto.SaveRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbMultiAskRegInterfaceMapper;

import lombok.RequiredArgsConstructor;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-S-0062 A/S, 분리, 재설치 및 설치정보 변경 등록 API
 * </pre>
 *
 * @author yeonghwa.cheon 천영화
 * @since 2023.03.03
 */
@Service
@RequiredArgsConstructor
public class WsnbMultiAskRegInterfaceService {

    private final WsnbMultiAskRegInterfaceMapper mapper;

    final String INSTALL_REQUEST = "11"; // 설치요청
    final String SEPARATION_REQUEST = "331"; // 분리요청

    @Transactional
    public SaveRes saveMultiAsks(SaveReq saveReq) {

        if (INSTALL_REQUEST.equals(saveReq.wrkTypDtl().substring(0, 2))) { // 설치요청

            // 고객삭제 (당일설치요청후 계약 취소일 경우, kiwi 상에 모든 기록 삭제
            if ("9".equals(saveReq.wrkGb().substring(0, 1))) {

                for (AskingInfo askingInfo : saveReq.askingInfos()) {
                    // TODO: LC_ASREGN_API_U03_T
                }

            } else {

                for (AskingInfo askingInfo : saveReq.askingInfos()) {
                    // TODO: 1.KIWI 처리 로직 - LC_ASREGN_API_U01M_T

                    // TODO: 2. 설치 건이면서 1번 정상 처리 시 예정일자 업데이트 처리 - LC_ASREGN_API_U02_T

                    // TODO: 3. 웰스팜 기기 설치 일 경우 모종 고객 생성 및 배송 오더 생성 - LC_ASREGN_API_U05_T

                    // TODO: 4. 홈케어 패키지 일 경우 5개중에 4개 선택 오더 생성 - LC_ASREGN_API_U06_T
                }

            }

        } else if (SEPARATION_REQUEST.equals(saveReq.wrkTypDtl().substring(0, 3))) { // 분리요청

            for (AskingInfo askingInfo : saveReq.askingInfos()) {
                // TODO: 1.KIWI 처리 로직 - LC_ASREGN_API_U01M_T

                // TODO: LC_USERDETAILINFO_S03M_T, LC_USERDETAILINFO_I04M_T, LC_USERDETAILINFO_I05M_T
            }

        } else { // 분리이외 요청 + 정보변경

        }

        return null;
    }

}
