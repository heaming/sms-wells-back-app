package com.kyowon.sms.wells.web.customer.contact.converter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sflex.common.common.dvo.FormatAddressDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoByEccDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoReqDvo;
import com.kyowon.sms.common.web.customer.contact.dvo.ZcsaCstCtplcBasDvo;
import com.kyowon.sms.common.web.customer.contact.dvo.ZcsaCustomerDvo;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.CreateCustomerForNaverRentalRes;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerInfoReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerRes;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaCustomerAgreementDvo;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaCustomerAgreementResultDvo;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaCustomerInfoDvo;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaInterfaceResultDvo;

/**
 * <pre>
 * 고객 인터페이스 관리 - WELLS Converter
 * </pre>
 *
 * @author jeongeon.kim
 * @since 2023-02-01
 */
@Mapper(componentModel = "spring")
public interface WcsaCustomerInterfaceConverter {

    @Mapping(target = "searchType", constant = "C01")
    ZcsaCustomerInfoReqDvo copy(SearchCustomerInfoReq dto);

    SearchCustomerRes copy(WcsaInterfaceResultDvo dvo);

    @Mapping(source = "dvo.cstNo", target = "cstNo")
    @Mapping(source = "dvo.exnoEncr", target = "exno")
    @Mapping(source = "dvo.mexnoEncr", target = "mexno")
    @Mapping(source = "dvo.newAdrZip", target = "zip")
    @Mapping(source = "dvo.rnadr", target = "basAdr")
    @Mapping(source = "dvo.rdadr", target = "dtlAdr")
    SearchCustomerRes mapCreateCustomerDtlToCustomerRes(ZcsaCustomerInfoDvo dvo, WcsaInterfaceResultDvo dvo2);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo.cstNo", target = "cstNo")
    @Mapping(source = "dvo.rsCd", target = "rsCd")
    @Mapping(source = "dvo.rsMsg", target = "rsMsg")

    WcsaCustomerInterfaceDto.SearchCustomerInfoEditRes mapCustomerInfoEditToInterfaceResultRes(
        WcsaInterfaceResultDvo dvo
    );

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dto.calngDvCd", target = "calngDvCd")
    @Mapping(source = "dto.copnDvCd", target = "copnDvCd")
    @Mapping(source = "dto.cstNo", target = "cstNo")
    @Mapping(source = "dto.locaraTno", target = "locaraTno")
    @Mapping(source = "dto.exno", target = "exno")
    @Mapping(source = "dto.idvTno", target = "idvTno")
    @Mapping(source = "dto.cralLocaraTno", target = "cralLocaraTno")
    @Mapping(source = "dto.mexno", target = "mexno")
    @Mapping(source = "dto.cralIdvTno", target = "cralIdvTno")
    @Mapping(source = "dto.adrId", target = "adrId")
    @Mapping(source = "dto.adrDvCd", target = "adrDvCd")
    @Mapping(source = "dto.chLtrqConfYn", target = "chLtrqConfYn")
    @Mapping(source = "dto.chLtrqConfDt", target = "chLtrqConfDt")
    @Mapping(source = "dto.unuitmCn", target = "unuitmCn")
    @Mapping(source = "dto.rgstMdfcUsrId", target = "rgstMdfcUsrId")
    WcsaCustomerInfoDvo mapCustomerInfoEditToCustomerInfoByEcc(
        WcsaCustomerInterfaceDto.SearchCustomerInfoEditReq dto
    );

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dto.calngDvCd", target = "calngDvCd")
    @Mapping(source = "dto.copnDvCd", target = "copnDvCd")
    @Mapping(source = "dto.cstNo", target = "cstNo")
    @Mapping(source = "dto.locaraTno", target = "locaraTno")
    @Mapping(source = "dto.exno", target = "exno")
    @Mapping(source = "dto.idvTno", target = "idvTno")
    @Mapping(source = "dto.cralLocaraTno", target = "cralLocaraTno")
    @Mapping(source = "dto.mexno", target = "mexno")
    @Mapping(source = "dto.cralIdvTno", target = "cralIdvTno")
    @Mapping(source = "dto.adrId", target = "adrId")
    @Mapping(source = "dto.adrDvCd", target = "adrDvCd")
    @Mapping(source = "dto.chLtrqConfYn", target = "chLtrqConfYn")
    @Mapping(source = "dto.chLtrqConfDt", target = "chLtrqConfDt")
    @Mapping(source = "dto.unuitmCn", target = "unuitmCn")
    @Mapping(source = "dto.rgstMdfcUsrId", target = "rgstMdfcUsrId")
    ZcsaCustomerInfoByEccDvo mapCustomerInfoToCustomerInfoByEcc(
        WcsaCustomerInterfaceDto.SearchCustomerInfoEditReq dto
    );

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "adrId", target = "adrId")
    @Mapping(source = "adrDvCd", target = "adrDvCd")
    ZcsaCstCtplcBasDvo mapCstCtplcBasToCustomerInfoByEcc(
        ZcsaCustomerInfoByEccDvo dvo
    );

    WcsaCustomerAgreementDvo mapSaveCustomerAgreementReqToWcsaCustomerAgreementDvo(
        WcsaCustomerInterfaceDto.SaveCustomerAgreementReq dto
    );

    WcsaCustomerInterfaceDto.SaveCustomerAgreementRes mapWcsaCustomerAgreementResultDvoToSaveCustomerAgreementRes(
        WcsaCustomerAgreementResultDvo resultDvo
    );

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo.rsCd", target = "rsCd")
    @Mapping(source = "dvo.rsMsg", target = "rsMsg")
    @Mapping(source = "dvo.cstNo", target = "cstNo")
    @Mapping(target = "sfkVal", constant = "")
    @Mapping(target = "sfkValIsDtm", constant = "")
    CreateCustomerForNaverRentalRes mapCustomerForHomepageToCustomerRes(
        WcsaInterfaceResultDvo dvo
    );

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "reqCstType", constant = "C")
    @Mapping(target = "cstHomepage", constant = "C")
    @Mapping(target = "cstInflwPhDvCd", constant = "02")
    @Mapping(source = "dto.coCd", target = "corpDivCd")
    @Mapping(source = "dto.cstKnm", target = "cstNm")
    @Mapping(source = "dto.copnDvCd", target = "copnDvCd")
    @Mapping(source = "dto.bryyMmdd", target = "bryyMmdd")
    @Mapping(source = "dto.sexDvCd", target = "sexDvCd")
    @Mapping(source = "dto.bzrno", target = "bzrno")
    @Mapping(source = "dto.cralLocaraTno", target = "cralLocaraTno")
    @Mapping(source = "dto.meno", target = "mexnoEncr")
    @Mapping(source = "dto.cralIdvTno", target = "cralIdvTno")
    @Mapping(source = "dto.sfkVal", target = "safeKey")
    @Mapping(source = "dto.cikVal", target = "cikVal")
    @Mapping(source = "dto.hsCtfDt", target = "skeyRcvDate")
    @Mapping(source = "dvo2.adrId", target = "adrId")
    @Mapping(source = "dvo2.zipCode", target = "zip")
    @Mapping(source = "dvo2.add1", target = "basAdr")
    @Mapping(source = "dvo2.add2", target = "dtlAdr")
    ZcsaCustomerDvo mapCustomerToCustomerForHomepage(
        WcsaCustomerInterfaceDto.CreateCustomerForNaverRentalReq dto, FormatAddressDvo dvo2
    );
}
