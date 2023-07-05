package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.*;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleCnfmDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleSearchDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleWkRsDvo;

@Mapper(componentModel = "spring", imports = {org.apache.commons.lang3.StringUtils.class})
public interface WsnaSeedReleaseScheduleConverter {

    @Mapping(target = "ichrCtnt", expression = "java(StringUtils.defaultString(dvo.getIchrCralLocaraTno()) + StringUtils.defaultString(dvo.getIchrMexnoEncr()) + StringUtils.defaultString(dvo.getIchrCralIdvTno()))")
    @Mapping(target = "cstCtnt", expression = "java(StringUtils.defaultString(dvo.getCstCralLocaraTno()) + StringUtils.defaultString(dvo.getCstMexnoEncr()) + StringUtils.defaultString(dvo.getCstCralIdvTno()))")
    SearchRes mapWsnaSeedReleaseScheduleSearchDvoToSearchRes(WsnaSeedReleaseScheduleSearchDvo dvo);

    List<SearchRes> mapAllWsnaSeedReleaseScheduleSearchDvoToSearchRes(List<WsnaSeedReleaseScheduleSearchDvo> dvos);

    WsnaSeedReleaseScheduleDvo mapEditReqToWsnaSeedReleaseScheduleDvo(EditReq dtos);

    @Mapping(source = "mchnCstNo", target = "sdingMcnrCntrNo")
    @Mapping(source = "qty1", target = "sdingQty1")
    @Mapping(source = "sowDt1", target = "sdingSowDt1")
    @Mapping(source = "qty2", target = "sdingQty2")
    @Mapping(source = "sowDt2", target = "sdingSowDt2")
    @Mapping(source = "qty3", target = "sdingQty3")
    @Mapping(source = "sowDt3", target = "sdingSowDt3")
    @Mapping(source = "qty4", target = "sdingQty4")
    @Mapping(source = "sowDt4", target = "sdingSowDt4")
    @Mapping(source = "qty5", target = "sdingQty5")
    @Mapping(source = "sowDt5", target = "sdingSowDt5")
    @Mapping(source = "receiptDt", target = "rcpDt")
    @Mapping(source = "ostrScheDt", target = "sppDuedt")
    @Mapping(source = "ostrCnfmDt", target = "sppCnfmdt")
    @Mapping(source = "prtnrNo", target = "ichrPrtnrNo")
    WsnaSeedReleaseScheduleCnfmDvo mapCreateReqToWsnaSeedReleaseScheduleCnfmDvo(CreateReq dto);

    @Mapping(source = "sdingPkgPdCd", target = "pdctPdCd")
    WsnaSeedReleaseScheduleWkRsDvo mapWsnaSeedReleaseScheduleCnfmDvoToWsnaSeedReleaseScheduleWkRsDvo(
        WsnaSeedReleaseScheduleCnfmDvo dvo
    );

}
