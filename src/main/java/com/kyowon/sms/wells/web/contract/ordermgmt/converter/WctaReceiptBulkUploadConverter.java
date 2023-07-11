package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.CreateProspectCstReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstCnslBasDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WctaReceiptBulkUploadConverter {
    @Mapping(constant = "10", target = "mgntTpCd") /*가망고객유형코드 - '10' 홈쇼핑*/
    @Mapping(constant = "KR", target = "natCd") /*국가코드 - 'KR'*/
    @Mapping(constant = "N", target = "dtaDlYn")
    @Mapping(source = "alncmpDgPrtnrMapngCd", target = "ichrPrtnrNo") /*담당파트너번호 = 대표파트너번호*/
    @Mapping(source = "alncmpDgPrtnrOgTpCd", target = "ichrOgTpCd") /*담당조직유형코드 = 조직유형코드*/
    WctaPspcCstBasDvo mapCreateProspectCstReqToWctaPspcCstBasDvo(CreateProspectCstReq req);

    @Mapping(constant = "001", target = "pspcCstCnslTpCd") /*가망고객상담유형코드 - '001' 신규문의*/
    @Mapping(constant = "60", target = "sellInflwChnlDvCd") /*판매유입채널구분코드 - '60' 홈쇼핑*/
    @Mapping(constant = "N", target = "dtaDlYn")
    @Mapping(source = "basePdCd", target = "inrtPdCd") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdHclsfId", target = "inrtPdHclsfId") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdMclsfId", target = "inrtPdMclsfId") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdLclsfId", target = "inrtPdLclsfId") /*담당조직유형코드 = 조직유형코드*/
    @Mapping(source = "pdDclsfId", target = "inrtPdDclsfId") /*담당조직유형코드 = 조직유형코드*/
    WctaPspcCstCnslBasDvo mapCreateProspectCstReqToWctaPspcCstCnslBasDvo(CreateProspectCstReq req);
}
