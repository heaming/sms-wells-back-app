package com.kyowon.sms.wells.web.fee.calculation.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.calculation.converter.WfebHomeMasterGradeConverter;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebHomeMasterGradeDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebHomeMasterGradeDvo;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebHomeMasterGradeMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 홈마스터 등급관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.09
 */
@Service
@RequiredArgsConstructor
public class WfebHomeMasterGradeService {

    private final WfebHomeMasterGradeMapper mapper;
    private final WfebHomeMasterGradeConverter converter;

    /**
     * 홈마스터 등급관리 - 조회
     * @param dto : {
     * mgtYm : 관리년월,
     * emlNm : 성명,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchRes> getHomeMasterGrades(
        SearchReq dto
    ) {
        return this.mapper.selectHomeMasterGrades(dto);
    }

    /**
     * 홈마스터 등급관리 상세 - 조회
     * @param dto : {
     * mgtYm : 관리년월,
     * emlNm : 성명,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchDetailRes> getHomeMasterGradeDetails(
        SearchDetailReq dto
    ) {
        return this.mapper.selectHomeMasterGradeDetails(dto);
    }

    /**
     * 홈마스터 등급 - 생성/수정
     * @param dto : { 홈마스터 등급 개별 저장
     * @return 처리 건수
     */

    @Transactional
    public int saveHomeMasterGrade(SaveReq dto) {
        int processCount = 0;

        WfebHomeMasterGradeDvo dvo = this.converter.mapSaveReqToWfebHomeMasterGradeDvo(dto);
        processCount = this.mapper.mergeHomeMasterGrades(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }

    /**
     * 홈마스터 등급 리스트 - 생성/수정
     * @param info : 홈마스터 등급관리 목록 저장
     * @return 처리 건수
     */

    @Transactional
    public int saveHomeMasterGrades(List<SaveReq> info) {
        AtomicInteger processCount = new AtomicInteger();
        info.forEach(data -> {
            WfebHomeMasterGradeDvo dvo = this.converter.mapSaveReqToWfebHomeMasterGradeDvo(data);
            processCount.addAndGet(this.mapper.mergeHomeMasterGrades(dvo));
        });
        BizAssert.isTrue(processCount.get() > 0, "MSG_ALT_CRT_FAIL");

        return processCount.get();
    }

    /**
     * 홈마스터 포인트 단건 - 생성/수정
     * @param dto : SavePointReq
     * @return 처리건수
     */
    @Transactional
    public int saveHomeMasterPoint(SavePointReq dto) {
        int processCount = 0;

        WfebHomeMasterGradeDvo dvo = this.converter.mapSaveReqToWfebHomeMasterPointDvo(dto);
        processCount = this.mapper.mergeHomeMasterPoint(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }

    /**
     * 홈마스터 포인트 리스트 - 생성/수정
     *
     * @param info : 홈마스터 포인트 목록 저장
     * @return 처리 건수
     */
    @Transactional
    public int saveHomeMasterPoints(List<SavePointReq> info) {
        AtomicInteger processCount = new AtomicInteger();
        info.forEach(data -> {
            WfebHomeMasterGradeDvo dvo = this.converter.mapSaveReqToWfebHomeMasterPointDvo(data);
            processCount.addAndGet(this.mapper.mergeHomeMasterPoint(dvo));
        });
        BizAssert.isTrue(processCount.get() > 0, "MSG_ALT_CRT_FAIL");

        return processCount.get();
    }

    /**
     * 홈마스터 등급 이관 - 삭제/생성
     * @param dto : {
     * mgtYm : 관리년월,
     * emlNm : 성명,
     * no : 번호 }
     * @return 처리 건수
     */

    @Transactional
    public int saveHomeMasterGradeTransfers(SearchTransferReq dto) {
        int processCount = 0;

        WfebHomeMasterGradeDvo dvo = this.converter.mapSaveReqToWfebHomeMasterGradeTransferDvo(dto);
        this.mapper.deletegeHomeMasterGrades(dvo);
        processCount = this.mapper.insertHomeMasterGradeTransfers(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }

    /**
     * 홈마스터 포인트 - 삭제
     * @return 처리 건수
     */

    @Transactional
    public int removeHomeMasterPoint(RemoveReq dto) throws Exception {
        int processCount = 0;
        WfebHomeMasterGradeDvo dvo = converter.mapRemoveReqToWfebHomeMasterGradeTransferDvo(dto);
        processCount = mapper.deleteHomeMasterPoint(dvo);
        BizAssert.isTrue(processCount > 0, "MSG_ALT_DEL_ERR");
        return processCount;
    }
}
