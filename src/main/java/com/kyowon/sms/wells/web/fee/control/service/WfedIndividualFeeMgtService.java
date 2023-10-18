package com.kyowon.sms.wells.web.fee.control.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedIndividualFeeMgtMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfedIndividualFeeMgtService {
    private final WfedIndividualFeeMgtMapper mapper;

    /**
     * 개인별 수수료 관리 홈마스터 사업자 정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstEntrpRes getHmstEntrp(SearchHmstReq dto) {
        return mapper.selectHmstEntrp(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstDeductionRes getHmstDeduction(SearchHmstReq dto) {
        return mapper.selectHmstDeduction(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstFeeRes> getHmstFee(SearchHmstReq dto) {
        return mapper.selectHmstFee(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 조정내역 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstControlRes> getHmstControls(
        SearchHmstReq dto
    ) {
        return this.mapper.selectHmstControls(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 사업자 정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindPlarEntrpRes getPlarEntrp(SearchPlarReq dto) {
        return mapper.selectPlarEntrp(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindPlarDeductionRes getPlarDeduction(SearchPlarReq dto) {
        return mapper.selectPlarDeduction(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 기본 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarEtcRes> getPlarEtcs(SearchPlarReq dto) {
        return mapper.selectPlarEtcs(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarFeeRes> getPlarFee(SearchPlarReq dto) {
        return mapper.selectPlarFee(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 조정내역 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarControlRes> getPlarControls(
        SearchPlarReq dto
    ) {
        return this.mapper.selectPlarControls(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 사업자 정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindMngerBasicRes getMngerBasic(SearchMngerReq dto) {
        return mapper.selectMngerBasic(dto);

    }

    /**
     * 개인별 수수료 관리 M조직 기본내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerSellEtcsRes> getMngerSellEtcs(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerSellEtcs(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 BS 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerBeforeServiceRes> getMngerBeforeServices(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerBeforeServices(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerDeductionRes> getMngerDeduction(SearchMngerReq dto) {
        return mapper.selectMngerDeduction(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<HashMap<String, Object>> getMngerFees(
        SearchMngerReq dto
    ) {
        /*
        * 수수료항목유형코드
        * 01 : BS수수료
        * 02 : 개인수수료
        * 03 : 조직수수료
        * 04 : 판매수수료
        * 05 : 서비스수수료
        * 05 : 교육수수료
        * 07 : 기타수수료
        * */
        List<SearchMngerFeeRes> indvFeeList = mapper.selectMngerFees(dto, "02");
        List<SearchMngerFeeRes> ogFeeList = mapper.selectMngerFees(dto, "03");
        List<SearchMngerFeeRes> bsFeeList = mapper.selectMngerFees(dto, "01");
        List<SearchMngerFeeRes> etcFeeList = mapper.selectMngerFees(dto, "07");

        Integer indvFeeListCnt = indvFeeList.size();
        Integer ogFeeListCnt = ogFeeList.size();
        Integer bsFeeListCnt = bsFeeList.size();
        Integer etcFeeListCnt = etcFeeList.size();

        Integer feeListCnt = Math
            .max(Math.max(Math.max(indvFeeListCnt, ogFeeListCnt), bsFeeListCnt), etcFeeListCnt);

        ArrayList<HashMap<String, Object>> feeDataList = new ArrayList<>();

        for (int i = 0; i < feeListCnt; i++) {

            HashMap<String, Object> feeHashMap = new HashMap<>();

            String feeNm1 = "";
            String feeAtcVal1 = "";
            String feeNm2 = "";
            String feeAtcVal2 = "";
            String feeNm3 = "";
            String feeAtcVal3 = "";
            String feeNm4 = "";
            String feeAtcVal4 = "";

            if (i < indvFeeListCnt) {
                feeNm1 = indvFeeList.get(i).srnMarkFeeNm();
                feeAtcVal1 = indvFeeList.get(i).feeAtcVal();
            }

            if (i < ogFeeListCnt) {
                feeNm2 = ogFeeList.get(i).srnMarkFeeNm();
                feeAtcVal2 = ogFeeList.get(i).feeAtcVal();
            }

            if (i < bsFeeListCnt) {
                feeNm3 = bsFeeList.get(i).srnMarkFeeNm();
                feeAtcVal3 = bsFeeList.get(i).feeAtcVal();
            }

            if (i < etcFeeListCnt) {
                feeNm4 = etcFeeList.get(i).srnMarkFeeNm();
                feeAtcVal4 = etcFeeList.get(i).feeAtcVal();
            }

            feeHashMap.put("feeNm1", feeNm1);
            feeHashMap.put("feeAtcVal1", feeAtcVal1);

            feeHashMap.put("feeNm2", feeNm2);
            feeHashMap.put("feeAtcVal2", feeAtcVal2);

            feeHashMap.put("feeNm3", feeNm3);
            feeHashMap.put("feeAtcVal3", feeAtcVal3);

            feeHashMap.put("feeNm4", feeNm4);
            feeHashMap.put("feeAtcVal4", feeAtcVal4);

            feeDataList.add(feeHashMap);
        }

        return feeDataList;
    }

    /**
     * 개인별 수수료 관리 M조직 조정내역 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerControlRes> getMngerControls(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerControls(dto);
    }

}
