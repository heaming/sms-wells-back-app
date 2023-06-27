package com.kyowon.sms.wells.web.competence.business.mapper;

import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseDvo;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseInquiryDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpsfRuleBaseMgtMapper {

    PagingResult<SearchRes> selectRuleBaseMgtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectRuleBaseMgtPages(
        SearchReq dto
    );

    List<SearchRes> selectRuleBase(
        SearchReq dto
    );

    int insertRuleBase(WpsfRuleBaseDvo dvo);

    int updateRuleBase(WpsfRuleBaseDvo dvo);

    int insertRuleBaseRel(WpsfRuleBaseInquiryDvo wDvo);

    String selectMnalRghId();

    int updateRuleBaseEndDtm(WpsfRuleBaseDvo dvo);

    int updateRuleBaseTree(WpsfRuleBaseDvo dvo);

    int updatePrevRuleBase(WpsfRuleBaseDvo dvo);

    String selectBnzsSpptMnalId();
}
