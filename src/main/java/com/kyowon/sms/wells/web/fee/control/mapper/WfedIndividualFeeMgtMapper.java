package com.kyowon.sms.wells.web.fee.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeMgtDto.*;

/**
 * <pre>
 * 개인별 수수료 관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.02
 */
@Mapper
public interface WfedIndividualFeeMgtMapper {
    FindHmstEntrpRes selectHmstEntrp(
        SearchHmstReq dto
    );

    FindHmstDeductionRes selectHmstDeduction(
        SearchHmstReq dto
    );

    List<SearchHmstFeeRes> selectHmstFee(
        SearchHmstReq dto
    );

    List<SearchHmstControlRes> selectHmstControls(
        SearchHmstReq dto
    );

    FindMngerEntrpRes selectMngerBasic(
        SearchMngerReq dto
    );

    List<SearchMngerSellEtcsRes> selectMngerSellEtcs(
        SearchMngerReq dto
    );

    List<SearchMngerBeforeServiceRes> selectMngerBeforeServices(
        SearchMngerReq dto
    );

    List<SearchMngerDeductionRes> selectMngerDeduction(
        SearchMngerReq dto
    );

    List<SearchMngerFeeRes> selectMngerFees(
        SearchMngerReq dto, String feeAtcTpCd
    );

    List<SearchMngerControlRes> selectMngerControls(
        SearchMngerReq dto
    );
}
