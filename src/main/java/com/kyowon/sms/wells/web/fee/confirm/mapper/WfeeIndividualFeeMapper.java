package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.*;

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

    SearchPrtnrRsbRes selectIndividualPerformancePrtnrRsb(SearchReq dto);

    List<SearchMngerRes> selectIndividualPerformanceMngerDetails(
        SearchReq dto
    );

    FindMngerBasicRes selectMngerBasic(
        SearchMngerReq dto
    );

    List<SearchMngerSellEtcsRes> selectMngerSellEtcs(
        SearchMngerReq dto
    );

    List<SearchMngerBeforeServiceRes> selectMngerBeforeServices(
        SearchMngerReq dto
    );

    List<SearchMngerFeeRes> selectMngerFees(
        SearchMngerReq dto, String feeAtcTpCd
    );

    List<SearchMngerDeductionRes> selectMngerDeduction(
        SearchMngerReq dto
    );

    List<SearchMngerPnpyamRes> selectMngerPnpyams(
        SearchMngerReq dto
    );

    List<SearchFeeRes> selectFees(
        SearchFeeReq dto
    );

    //    SearchUserInfoRes selectUserInfo(
    //        SearchFeeReq dto
    //    );
}
