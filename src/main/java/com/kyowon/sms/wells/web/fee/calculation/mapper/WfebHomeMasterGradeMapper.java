package com.kyowon.sms.wells.web.fee.calculation.mapper;

import java.util.List;

import com.kyowon.sms.common.web.fee.ddtnrplc.dvo.ZfecPcPnpyamMgtDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebHomeMasterGradeDto.*;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebHomeMasterGradeDvo;

/**
 * <pre>
 * 홈마스터 등급 관리
 * </pre>
 *
 * @author gs.piit150
 * @since 2023.03.09
 */
@Mapper
public interface WfebHomeMasterGradeMapper {

    List<SearchRes> selectHomeMasterGrades(SearchReq dto);

    List<SearchDetailRes> selectHomeMasterGradeDetails(
        SearchDetailReq dto
    );

    int mergeHomeMasterGrades(WfebHomeMasterGradeDvo dvo);

    int mergeHomeMasterPoint(WfebHomeMasterGradeDvo dvo);

    int deletegeHomeMasterGrades(WfebHomeMasterGradeDvo dvo);

    int insertHomeMasterGradeTransfers(WfebHomeMasterGradeDvo dvo);

    int deleteHomeMasterPoint(WfebHomeMasterGradeDvo dvo);

}
