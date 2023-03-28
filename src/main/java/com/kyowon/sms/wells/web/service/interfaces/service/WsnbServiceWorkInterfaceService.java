package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbServiceWorkInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbServiceWorkInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbServiceWorkInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsnbServiceWorkInterfaceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-I-0009 타시스템(kyowonwells, CubigCC, kmembers)에서 설치/AS/BS/홈케어 서비스 작업 오더 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.03
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbServiceWorkInterfaceService {

    private final WsnbServiceWorkInterfaceMapper mapper;

    static final String P_WRK_GB = "9"; // TODO RequestBody 값 여부 확인 필요
    static final String P_WRK_TYPE_DTL = "11"; // TODO RequestBody 값 여부 확인 필요

    static final String P_WRK_GB_DEL_CST = "9";
    static final String P_WRK_GB_IST_A = "11";
    static final String P_WRK_GB_IST_B = "41";

    @Transactional
    public CreateRes createServiceWorks(CreateReq createReq) throws Exception {

        this.saveDefaultData();

        // LC_ASREGN_API_I07 이전 과정이 모두 성공 시 commit 및 설치가 접수될 시, 엔지니어 방문 안내 알림톡 발송

        WsnbServiceWorkInterfaceDvo dvo = new WsnbServiceWorkInterfaceDvo();
        dvo.setAsIstOjNo(createReq.asIstOjNo());
        dvo.setCntrNo(createReq.cntrNo());

        // LC_ASREGN_API_S02 - 고객서비스AS설치대상내역 조회
        dvo = this.mapper.selectAsInstallationTarget(dvo);

        return null;
    }

    public void saveDefaultData() {

        // LC_ASREGN_API_U01M_LOG_T 로그 저장

        if (P_WRK_GB_DEL_CST.equalsIgnoreCase(P_WRK_GB)) { // 고객 삭제
            log.debug("고객 삭제");
            // LC_ASREGN_API_U03_T
        } else { // LC_ASREGN_API_U01M_T 오더 처리 (생성/수정/취소)
            log.debug("오더 처리");
            // LC_ASREGN_API_U01M_T
        }

        if (P_WRK_GB_IST_A.equals(P_WRK_TYPE_DTL)
            || P_WRK_GB_IST_B.equals(P_WRK_TYPE_DTL)) {
            // LC_ASREGN_API_U02_T 설치 건이면서 KIWI 에서 정상 처리 되었다면 기간계(5250)에 예정일자 업데이트 처리
            log.debug("예정일자 업데이트");
        }

        /*
         * 고객 삭제 아니고 웰스팜 기기 설치 오더라면 모종 배송 스케쥴을 생성
         * (output.get("KAETCT") != null && P_WRK_TYP_DTL != null && output.get("KAETCT").toString().equalsIgnoreCase("4E"))
         */
        if (!P_WRK_GB_DEL_CST.equalsIgnoreCase(P_WRK_GB) && P_WRK_GB_IST_A.equals(P_WRK_TYPE_DTL)) {
            // LC_ASREGN_API_U05_T 모종 마스터 생성 및 작업 오더 (설치 개념)
            log.debug("모종 마스터 생성 및 작업 오더");
        }
    }

}
