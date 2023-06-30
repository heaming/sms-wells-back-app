package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.*;
import com.kyowon.sms.wells.web.fee.confirm.dvo.WfeeIndividualFeeDvo;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.02.17
 */
@Mapper
public interface WfeeIndividualFeeMapper {
    List<SearchPlarRes> selectIndividualPerformancePlarDetails(
        SearchReq dto
    );

    List<SearchMngerRes> selectIndividualPerformanceMngerDetails(
        SearchReq dto
    );

    List<SearchHmstRes> selectIndividualPerformanceHmstDetails(
        SearchReq dto
    );

    FindHmstRes selectHmst(
        SearchHmstReq dto
    );

    List<SearchHmstEtcRes> selectHmstEtcs(
        SearchHmstReq dto
    );

    List<SearchHmstFeeRes> selectHmstFees(
        SearchHmstReq dto
    );

    List<FindHmstDeductionRes> selectHmstDeductions(
        SearchHmstReq dto
    );

    List<SearchHmstPnpyamRes> selectHmstPnpyams(
        SearchHmstReq dto
    );

    FindPlarRes selectPlar(
        SearchPlarReq dto
    );

    List<SearchPlarEtcRes> selectPlarEtcs(
        SearchPlarReq dto
    );

    List<SearchPlarFeeRes> selectPlarFees(
        SearchPlarReq dto
    );

    FindPlarDeductionRes selectPlarDeduction(
        SearchPlarReq dto
    );

    List<SearchPlarPnpyamRes> selectPlarPnpyams(
        SearchPlarReq dto
    );

    FindMngerRes selectMnger(
        SearchMngerReq dto
    );

    List<SearchMngerEtcRes> selectMngerEtcs(
        SearchMngerReq dto
    );

    List<SearchMngerBeforeServiceRes> selectMngerBeforeServices(
        SearchMngerReq dto
    );

    List<SearchMngerFeeRes> selectMngerFees(
        SearchMngerReq dto
    );

    FindMngerDeductionRes selectMngerDeduction(
        SearchMngerReq dto
    );

    List<SearchMngerPnpyamRes> selectMngerPnpyams(
        SearchMngerReq dto
    );

    List<SearchFeeRes> selectFees(
        WfeeIndividualFeeDvo dvo
    );

    List<SearchFeeHmstRes> selectFeeHmsts(
        WfeeIndividualFeeDvo dvo
    );
}
