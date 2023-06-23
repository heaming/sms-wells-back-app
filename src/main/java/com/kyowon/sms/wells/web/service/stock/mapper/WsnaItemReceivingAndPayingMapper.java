package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemReceivingAndPayingDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReceiptsAndPaymentsDvo;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaItemReceivingAndPayingMapper {

    long selectReceiptsAndPaymentCount(WsnaReceiptsAndPaymentsDvo dvo);

    List<SearchRes> selectReceiptsAndPaymentsPages(
        WsnaReceiptsAndPaymentsDvo dvo
    );

    List<SearchRes> selectReceiptsAndPaymentsPages(
        SearchReq dto
    );

    PagingResult<SearchDateRes> selectDateReceivingAndPayings(SearchDateReq dto, PageInfo pageInfo);

    List<SearchDateRes> selectDateReceivingAndPayings(SearchDateReq dto);
}
