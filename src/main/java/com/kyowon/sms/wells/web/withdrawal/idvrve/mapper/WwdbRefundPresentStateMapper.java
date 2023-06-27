package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundPresentStateDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundPresentStateDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRefundPresentStateMapper {

    PagingResult<WwdbRefundPresentStateDvo> selectRefundPresentStatePages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<WwdbRefundPresentStateDvo> selectRefundPresentStatePages(
        SearchReq dto
    );
}
