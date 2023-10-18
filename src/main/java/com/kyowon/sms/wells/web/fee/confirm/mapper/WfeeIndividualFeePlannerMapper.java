package com.kyowon.sms.wells.web.fee.confirm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.SearchReq;
import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeePlannerDto.*;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.02.17
 */
@Mapper
public interface WfeeIndividualFeePlannerMapper {
    List<SearchPlarRes> selectIndividualPerformancePlarDetails(
        SearchReq dto
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

    List<FindPlarDeductionRes> selectPlarDeduction(
        SearchPlarReq dto
    );

    List<SearchPlarPnpyamRes> selectPlarPnpyams(
        SearchPlarReq dto
    );
}
