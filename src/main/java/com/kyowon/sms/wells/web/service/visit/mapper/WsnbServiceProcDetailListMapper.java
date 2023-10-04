package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceProcDetailListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailBilItemDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailListDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailPuPartDvo;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbServiceProcDetailStlmIzDvo;

@Mapper
public interface WsnbServiceProcDetailListMapper {

    WsnbServiceProcDetailListDvo selectServiceProcDetailList(SearchReq dto);

    List<WsnbServiceProcDetailStlmIzDvo> selectServiceProcDetailStlmIz(SearchReq dto);

    List<WsnbServiceProcDetailBilItemDvo> selectServiceProcDetailBilItem(SearchReq dto);

    List<WsnbServiceProcDetailPuPartDvo> selectServiceProcDetailPuPart(SearchReq dto);
}
