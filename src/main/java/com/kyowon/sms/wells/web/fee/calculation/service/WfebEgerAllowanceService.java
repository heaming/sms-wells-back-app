package com.kyowon.sms.wells.web.fee.calculation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.calculation.converter.WfebEgerAllowanceConverter;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebEgerAllowanceDto;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebEgerAllowanceDvo;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebEgerAllowanceMapper;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 엔지니어 수당 생성관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfebEgerAllowanceService {

    private final WfebEgerAllowanceMapper mapper;
    private final WfebEgerAllowanceConverter converter;

    /**
     * 엔지니어 수당 내역 - 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schRsbTp : 직책유형,
     * schOgLevl1 : 조직레벨1,
     * schOgLevl2 : 조직레벨2,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfebEgerAllowanceDto.SearchEngineerRes> getEngineerAllowances(
        WfebEgerAllowanceDto.SearchReq dto
    ) {
        return this.mapper.selectEngineerAllowances(dto);
    }

    /**
     * 엔지니어 관리자 수당 내역 - 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schRsbTp : 직책유형,
     * schOgLevl1 : 조직레벨1,
     * schOgLevl2 : 조직레벨2,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfebEgerAllowanceDto.SearchEngineerManagerRes> getEngineerManagerAllowances(
        WfebEgerAllowanceDto.SearchReq dto
    ) {
        return this.mapper.selectEngineerManagerAllowances(dto);
    }

    /**
     * 엔지니어 수당 조정
     * @param dtos
     * @return 처리건수
     */
    @Transactional
    public int editEgerAllowances(List<WfebEgerAllowanceDto.EditReq> dtos) {
        int processCnt = 0;

        for (WfebEgerAllowanceDto.EditReq dto : dtos) {
            WfebEgerAllowanceDvo dvo = converter.mapEditReqToWfebEgerAllowanceDvo(dto);
            mapper.insertEgerAllowanceHist(dvo);
            processCnt = mapper.updateEgerAllowanceControl(dvo);
        }

        BizAssert.isTrue(processCnt > 0, "MSG_ALT_SVE_ERR");

        return processCnt;
    }

    /**
     * 엔지니어 수당 확정 내역 조회
     * @param perfYm
     * @return 조회결과
     */
    public List<WfebEgerAllowanceDto.SearchConfirmRes> getEgerAllowanceConfirms(
        String perfYm
    ) {
        return this.mapper.selectEgerAllowanceConfirms(perfYm);
    }

    /**
     * 엔지니어 수당 확정/확정취소
     * @param dtos
     * @return 처리건수
     */
    @Transactional
    public int confirmEgerAllowances(List<WfebEgerAllowanceDto.ConfirmReq> dtos) {
        int processCnt = 0;
        String msg = "";

        for (WfebEgerAllowanceDto.ConfirmReq dto : dtos) {
            WfebEgerAllowanceDvo dvo = converter.mapConfirmReqToWfebEgerAllowanceDvo(dto);
            // type: 본사(H) / 센터(C) 구분
            // confirm: 확정(Y) / 확정취소(N) 구분
            // 확정취소는 type 이 본사인 경우에만 해당
            switch (dto.type()) {
                case "C" -> { // 센터
                    if ("Y".equals(dto.confirm())) { // 확정
                        msg = "MSG_ALT_CNFM_FAIL";
                        int cnt = mapper.selectConfirmYnCheck(dvo);
                        BizAssert.isFalse(cnt > 0, "MSG_ALT_BF_CNFM_CONF"); // 이미 확정되었습니다.

                        processCnt = mapper.insertEgerAllowanceConfirm(dvo);
                    } else
                        throw new BizException("MSG_ALT_CNFM_FAIL");
                }
                case "H" -> { // 본사
                    dvo.setHdof("Y");
                    int cnt = mapper.selectConfirmYnCheck(dvo);
                    BizAssert.isFalse(cnt > 0, "MSG_ALT_BF_CNFM_CONF"); // 이미 확정되었습니다.

                    if ("Y".equals(dto.confirm())) { // 확정
                        msg = "MSG_ALT_CNFM_FAIL";
                        processCnt = mapper.updateEgerAllowanceConfirm(dvo);
                    } else if ("N".equals(dto.confirm())) { //센터확정취소
                        msg = "MSG_ALT_CNFM_CANCEL_FAIL"; // 확정취소를 실패하였습니다.
                        processCnt = mapper.updateEgerAllowanceConfirmCancel(dvo);
                    }
                }
                default -> throw new BizException("MSG_ALT_CNFM_FAIL");
            }
        }

        BizAssert.isTrue(processCnt > 0, msg);

        return processCnt;
    }

    /**
     * 엔지니어 수당 생성 - 생성
     * @param dto : {
     * param1 : 실적년월,
     * param2 : 직책유형
     * @return 생성건수
     */
    @Transactional
    public int saveEgerAllowances(WfebEgerAllowanceDto.SaveReq dto) {
        int processCount = 0;

        WfebEgerAllowanceDvo dvo = this.converter.mapSaveReqToWfebEgerAllowanceDvo(dto);

        this.mapper.deleteEgerAllowances(dvo);
        processCount = this.mapper.insertEgerAllowances(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }
}
