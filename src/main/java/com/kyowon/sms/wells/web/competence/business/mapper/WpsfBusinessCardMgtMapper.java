package com.kyowon.sms.wells.web.competence.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfBusinessCardMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfPartnerContactBaseDvo;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfPartnerCustomerContactBaseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpsfBusinessCardMgtMapper {

    PagingResult<WpsfPartnerCustomerContactBaseDvo> selectPartnerCustomerContactBasePages(
        WpsfBusinessCardMgtDto.SearchReq dto, PageInfo pageInfo
    );

    List<WpsfPartnerCustomerContactBaseDvo> selectPartnerCustomerContactBasePages(
        WpsfBusinessCardMgtDto.SearchReq dto
    );

    int insertPartnerCustomerContactBase(WpsfPartnerCustomerContactBaseDvo dvo);

    int selectMaxCtplcSn();

    int updatePartnerCustomerContactBaseDtaDlyn(WpsfPartnerCustomerContactBaseDvo dvo);

    int updatePartnerCustomerContactBase(WpsfPartnerCustomerContactBaseDvo dvo);

    WpsfPartnerContactBaseDvo selectPartnerContactBase(WpsfBusinessCardMgtDto.SearchReq dto);

    WpsfBusinessCardMgtDto.SearchPartnerRes selectPartnerInfo();

    int selectCountPartnerContactBase();

    int insertPartnerContactBase(WpsfPartnerContactBaseDvo dvo);

    int updatePartnerContactBase(WpsfPartnerContactBaseDvo dvo);

}
