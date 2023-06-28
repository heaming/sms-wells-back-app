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

    FindHmstFeeRes selectHmstFee(
        SearchHmstReq dto
    );

    List<SearchHmstControlRes> selectHmstControls(
        SearchHmstReq dto
    );

    FindPlarEntrpRes selectPlarEntrp(
        SearchPlarReq dto
    );

    FindPlarDeductionRes selectPlarDeduction(
        SearchPlarReq dto
    );

    FindPlarFeeRes selectPlarFee(
        SearchPlarReq dto
    );

    List<SearchPlarControlRes> selectPlarControls(
        SearchPlarReq dto
    );

    FindMngerEntrpRes selectMngerEntrp(
        SearchMngerReq dto
    );

    List<SearchMngerBaseInfoRes> selectMngerBaseInfo(
        SearchMngerReq dto
    );

    List<SearchMngerBeforeServiceRes> selectMngerBeforeServices(
        SearchMngerReq dto
    );

    FindMngerDeductionRes selectMngerDeduction(
        SearchMngerReq dto
    );

    List<SearchMngerFeeRes> selectMngerFees(
        SearchMngerReq dto
    );

    List<SearchMngerControlRes> selectMngerControls(
        SearchMngerReq dto
    );
}
