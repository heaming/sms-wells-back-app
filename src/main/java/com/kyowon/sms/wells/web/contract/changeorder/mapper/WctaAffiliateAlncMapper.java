package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctaAffiliateAlncDto.SaveReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctaAffiliateAlncDvo;

@Mapper
public interface WctaAffiliateAlncMapper {
    WctaAffiliateAlncDvo selectAffiliateAlncCheck(SaveReq dto);

    List<WctaAffiliateAlncDvo> selectAffiliateAlnc(SaveReq dto);

    int updateCntrWellsDtl(WctaAffiliateAlncDvo dvo);

    int updateAcmpalCntrIz(WctaAffiliateAlncDvo dvo);
}
