package com.kyowon.sms.wells.web.fee.calculation.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.calculation.converter.WfebOrganizationFeeConverter;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOrganizationFeeDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebOrganizationFeeDvo;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebOrganizationFeeMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * WELLS 홈마스터 수수료 생성관리 서비스
 * </pre>
 *
 * @author gs.piit150
 * @since 2023-02-03
 */

@Service
@RequiredArgsConstructor
public class WfebOrganizationFeeService {

    private final WfebOrganizationFeeMapper mapper;
    private final WfebOrganizationFeeConverter converter;;

    /**
     * WELLS 홈마스터 수수료 생성관리 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchHmstRes> getHomeMasterFees(SearchHmstReq dto) {
        return this.mapper.selectHomeMasterFees(dto);
    }

    /**
     * WELLS 홈마스터 지점장 수수료 생성관리 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchHmstBrmgrRes> getHomeMasterBranchManagerFees(SearchHmstReq dto) {
        return this.mapper.selectHomeMasterBranchManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 생성관리 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchMngerRes> getManagerFees(SearchMngerReq dto) {
        return this.mapper.selectManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 생성관리 지점장 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchMngerBrmgrRes> getManagerBranchManagerFees(SearchMngerReq dto) {
        return this.mapper.selectManagerBranchManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 생성관리 전체 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchMngerTotalRes> getManagerTotalFees(SearchMngerReq dto) {
        return this.mapper.selectManagerTotalFees(dto);
    }

    /**
     * WELLS P조직 수수료 생성관리 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchPlarRes> getPlannerFees(SearchPlarReq dto) {
        return this.mapper.selectPlannerFees(dto);
    }

    /**
     * WELLS P조직 수수료 생성관리 지점장 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchPlarBrmgrRes> getPlannerBranchManagerFees(SearchPlarReq dto) {
        return this.mapper.selectPlannerBranchManagerFees(dto);
    }

    /**
     * WELLS P조직 수수료 생성관리 전체 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchPlarTotalRes> getPlannerTotalFees(SearchPlarReq dto) {
        return this.mapper.selectPlannerTotalFees(dto);
    }

    /** WM수수료 내역 - 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchWmRes> getWmFees(SearchWmReq dto) {
        return this.mapper.selectWmFees(dto);
    }

    /**
     * WM수수료 생성
     * @param dto : {
     * perfYm : 실적년월
     * }
     * @return 생성건수
     * @throws Exception
     * */
    @Transactional
    public int saveWmFees(SaveReq dto) {
        int processCount = 0;

        WfebOrganizationFeeDvo dvo = converter.mapSaveReqToWfebOgFeeDvo(dto);

        // TODO: WM 수수료 생성 서비스 호출
        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }

    /**
     * WELLS 결재품의 진행단계 조회
     * @param 'SearchDsbCnstReq'
     * @return 조회된 데이터
     */

    public SearchDsbCnstRes getdsbCnst(SearchDsbCnstReq dto) {
        return this.mapper.selectdsbCnst(dto);
    }

    /**
     * 품의결재 지급내역 업데이트 및 품의결재이력 데이터 생성
     * @param dto : {
     * perfYm : 실적년월
     * ogTp : 조직유형
     * appKey : 품의결재키
     * }
     * @return 생성건수
     * @throws Exception
     * */
    @Transactional
    public int saveDsbCnstIz(SaveDsbCnstReq dto) {
        int processCount = 0;

        WfebOrganizationFeeDvo dvo = converter.mapSaveDsbCnstReqToWfebOgFeeDvo(dto);
        this.mapper.updateDsbIz(dvo);
        processCount = this.mapper.insertDsbCnstIz(dvo);
        // TODO: WM 수수료 생성 서비스 호출
        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }

    /**
     * 반송, 회수의 이유로 재결재시 이전 품의결재이력 최종여부 수정
     * @param dto : {
     * perfYm : 실적년월
     * ogTp : 조직유형
     * }
     * @return 생성건수
     * @throws Exception
     * */
    @Transactional
    public int updateDsbCnstIz(SearchDsbCnstReq dto) {
        int processCount = 0;

        WfebOrganizationFeeDvo dvo = converter.mapSearchDsbCnstReqToWfebOgFeeDvo(dto);
        processCount = this.mapper.updateDsbCnstIz(dvo);
        // TODO: WM 수수료 생성 서비스 호출
        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }

    /**
     * 홈마스터 수수료 생성관리 품의 데이터 조회
     * @return
     */
    public HashMap<String, String> getHmstFeeFormDtl() {
        HashMap<String, String> rtnMap = new HashMap<String, String>();
        String perfYm = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        rtnMap = mapper.selectHmstFeeFormDtl(perfYm);

        return rtnMap;

    }
}
