package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaWelsMngerSettlementAwConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaWelsMngerSettlementAwDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaWelsMngerSettlementAwDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaWelsMngerSettlementAwMapper;
import org.springframework.stereotype.Service;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 웰스매니저 정착수당
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfeaWelsMngerSettlementAwService {
    private final WfeaWelsMngerSettlementAwMapper mapper;
    private final WfeaWelsMngerSettlementAwConverter converter;

    /**
     * 웰스매니저 정착수당 조회
     * @param dto : {
     * perfYm: 실적년월,
     * tcnt: 회차,
     * prtnrNo: 번호,
     * prtnrKnm: 이름,
     * schDiv: 구분,
     * schRsbDvCd: 직급구분  }
     * @return 조회결과
     */
    public List<WfeaWelsMngerSettlementAwDto.SearchRes> getWelsMngers(WfeaWelsMngerSettlementAwDto.SearchReq dto) {
        System.out.println("###############################:" + dto.toString());
        return mapper.selectWelsMngers(dto);
    }

    /**
     * 웰스매니저 정착수당 개시구분 생성
     * @param dto : {
     * baseYm : 기준년월,
     * prtnrNo : 번호,
     * tcntDvCd : 회차구분,
     * opngCd : 개시구분코드,
     * cnfmStatYn : 확정여부 }
     * @return 조회결과
     */
    public int saveWelsMngerOpngs(WfeaWelsMngerSettlementAwDto.SaveOpngReq dto) {

        int processCount = 0;
        int chkCount = 0;

        chkCount = mapper.selectCheckWelsMngerOpng(dto);
        if (chkCount == 0) {
            WfeaWelsMngerSettlementAwDvo dvo = converter.mapSaveOpngReqToWfeaWelsMngerSettlementAwDvo(dto);
            processCount = mapper.insertWelsMngerOpng(dvo);
            BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");
        } else {
            BizAssert.isTrue(chkCount == 0, "MSG_ALT_CRT_FAIL");
        }
        return processCount;
    }

    /**
     * 웰스매니저 정착수당 개시구분 저장
     * @param info : {
     * baseYm : 기준년월,
     * prtnrNo : 번호,
     * tcntDvCd : 회차구분,
     * opngCd : 개시구분코드,
     * cnfmStatYn : 확정여부 }
     * @return 조회결과
     */
    public int saveWelsMngers(List<WfeaWelsMngerSettlementAwDto.SaveReq> info) {
        AtomicInteger processCount = new AtomicInteger();
        info.forEach(data -> {
            WfeaWelsMngerSettlementAwDvo dvo = this.converter.mapSaveReqToWfeaWelsMngerSettlementAwDvo(data);
            processCount.addAndGet(this.mapper.updateWelsMnger(dvo));
        });
        BizAssert.isTrue(processCount.get() > 0, "MSG_ALT_CRT_FAIL");

        return processCount.get();
    }

    /**
     * 웰스매니저 정착수당 확정여부 저장
     * @param dto : {
     * baseYm : 기준년월,
     * prtnrNo : 번호,
     * tcntDvCd : 회차구분,
     * opngCd : 개시구분코드,
     * cnfmStatYn : 확정여부 }
     * @return 조회결과
     */
    public int saveWelsMngerConfirms(WfeaWelsMngerSettlementAwDto.SaveConfirmReq dto) {

        int processCount = 0;
        WfeaWelsMngerSettlementAwDvo dvo = converter.mapSaveConfirmReqToWfeaWelsMngerSettlementAwDvo(dto);
        processCount = mapper.updateWelsMngerConfirm(dvo);
        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");
        return processCount;
    }

}
