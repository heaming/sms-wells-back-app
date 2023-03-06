package com.kyowon.sms.wells.web.fee.control.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.fee.control.dvo.WfedWelsMngerBsFeeDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.control.dto.WfedWelsMngerBsFeeDto.*;
import org.apache.ibatis.annotations.Param;

/**
 * <pre>
 * WM 방문실적 수수료관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.02
 */
@Mapper
public interface WfedWelsMngerBsFeeMapper {

    FindHumanRes selectHuman(
        SearchReq dto
    );

    List<SearchVisitRes> selectVisits(
        SearchReq dto
    );

    List<SearchFeeRes> selectFees(
        SearchReq dto
    );

    FindVisitAgrgRes selectVisitAgrg(
        SearchReq dto
    );

    FindFeeAgrgRes selectFeeAgrg(
        SearchReq dto
    );

    int deleteFee(
        WfedWelsMngerBsFeeDvo dvo
    );

    int insertFee(
        WfedWelsMngerBsFeeDvo dvo
    );
}
