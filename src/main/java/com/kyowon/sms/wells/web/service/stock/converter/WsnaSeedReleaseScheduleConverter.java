package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.*;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dvo.*;

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
    @Mapping(source = "qty6", target = "sdingQty6")
    @Mapping(source = "sowDt6", target = "sdingSowDt6")
    @Mapping(source = "qty7", target = "sdingQty7")
    @Mapping(source = "sowDt7", target = "sdingSowDt7")
    @Mapping(source = "qty8", target = "sdingQty8")
    @Mapping(source = "sowDt8", target = "sdingSowDt8")
    @Mapping(source = "qty9", target = "sdingQty9")
    @Mapping(source = "sowDt9", target = "sdingSowDt9")
    @Mapping(source = "qty10", target = "sdingQty10")
    @Mapping(source = "sowDt10", target = "sdingSowDt10")
    @Mapping(source = "receiptDt", target = "rcpDt")
    @Mapping(source = "ostrScheDt", target = "sppDuedt")
    @Mapping(source = "ostrCnfmDt", target = "sppCnfmdt")
    @Mapping(source = "prtnrNo", target = "ichrPrtnrNo")
    WsnaSeedReleaseScheduleCnfmDvo mapCreateReqToWsnaSeedReleaseScheduleCnfmDvo(CreateReq dto);

    @Mapping(source = "sdingPkgPdCd", target = "pdctPdCd")
    WsnaSeedReleaseScheduleWkRsDvo mapWsnaSeedReleaseScheduleCnfmDvoToWsnaSeedReleaseScheduleWkRsDvo(
        WsnaSeedReleaseScheduleCnfmDvo dvo
    );

    @Mapping(source = "pdCd", target = "pdctPdCd")
    WsnaSeedReleaseScheduleWkRsDvo mapWsnaSeedReleaseScheduleSproutDvoToWsnaSeedReleaseScheduleWkRsDvo(
        WsnaSeedReleaseScheduleSproutDvo dvo
    );

}
