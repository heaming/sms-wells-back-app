package com.kyowon.sms.wells.web.fee.control.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeePlannerMgtDto.*;

/**
 * <pre>
 * P조직 개인별 수수료 관리
 * </pre>
 *
 * @author gs.piit272
 * @since 2023.10.17
 */
@Mapper
public interface WfedIndividualFeePlannerMgtMapper {
    FindPlarEntrpRes selectPlarEntrp(
        SearchPlarReq dto
    );

    FindPlarDeductionRes selectPlarDeduction(
        SearchPlarReq dto
    );

    SearchPlarEtcRes selectPlarEtcs(
        SearchPlarReq dto
    );

    List<SearchPlarFeeRes> selectPlarFee(
        SearchPlarReq dto
    );

    List<SearchPlarControlRes> selectPlarControls(
        SearchPlarReq dto
    );
}
