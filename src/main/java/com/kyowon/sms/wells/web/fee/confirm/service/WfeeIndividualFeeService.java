package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.*;
import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeIndividualFeeMapper;

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
public class WfeeIndividualFeeService {
    private final WfeeIndividualFeeMapper mapper;

    /**
     * 수수료 개인별 실적 상세 조회(M조직)
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerRes> getIndividualPerformanceMngerDetails(
        SearchReq dto
    ) {
        return this.mapper.selectIndividualPerformanceMngerDetails(dto);
    }

    /**
     * 수수료 개인별 실적 상세 조회(홈마스터)
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstRes> getIndividualPerformanceHmstDetails(
        SearchReq dto
    ) {
        return this.mapper.selectIndividualPerformanceHmstDetails(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 기본정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindMngerBasicRes getMngerBasic(SearchMngerReq dto) {
        return mapper.selectMngerBasic(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 기타내역 조회
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
     * 수수료 M조직 개인별 실적 BS 목록 조회
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
     * 수수료 M조직 개인별 실적 수수료 내역 조회
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
     * 수수료 M조직 개인별 실적 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerDeductionRes> getMngerDeduction(SearchMngerReq dto) {
        return mapper.selectMngerDeduction(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 가지급금 세부내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerPnpyamRes> getMngerPnpyams(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerPnpyams(dto);
    }

    /**
     * 추진단 수수료 조회
     * @param dto : {
     * perfYm : 실적년월,
     * ogTp : 조직유형,
     * rsbTp : 직책유형,
     * ogLevl1 : 조직레벨1,
     * ogLevl2 : 조직레벨2,
     * ogLevl3 : 조직레벨3,
     * no : 번호,
     * feeDsbYn : 수수료지급여부}
     * @return 조회결과
     */
    public List<SearchFeeRes> getFees(
        SearchFeeReq dto
    ) {
        return this.mapper.selectFees(dto);
    }

    /**
     * 사용자 고용정보 형태 조회
     *  @param dto : {
     * userEmpId : 사용자ID,
     * feeDsbYn : 수수료지급여부}
     * @return 조회결과
     */
    //    public SearchUserInfoRes getUserInfo(
    //        SearchFeeReq dto
    //    ) {
    //        return this.mapper.selectUserInfo(dto);
    //    }

}
