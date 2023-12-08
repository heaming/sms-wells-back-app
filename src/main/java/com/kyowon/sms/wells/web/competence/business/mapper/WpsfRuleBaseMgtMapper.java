package com.kyowon.sms.wells.web.competence.business.mapper;

import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseDvo;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseInquiryDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchRes;

@Mapper
public interface WpsfRuleBaseMgtMapper {

    List<SearchRes> selectRuleBaseMgtPages(
        SearchReq dto
    );

    List<SearchRes> selectRuleBase(
        SearchReq dto
    );

    int updateRuleBase(WpsfRuleBaseDvo dvo);

    int insertRuleBaseRel(WpsfRuleBaseInquiryDvo wDvo);

    int updateRuleBaseEndDtm(WpsfRuleBaseDvo dvo);

    int updateRuleBaseTree(WpsfRuleBaseDvo dvo);

    int updatePrevRuleBase(WpsfRuleBaseDvo dvo);
    int insertRuleBase(WpsfRuleBaseDvo dvo);

    int deleteRuleBaseRel(WpsfRuleBaseDvo dvo);

}
