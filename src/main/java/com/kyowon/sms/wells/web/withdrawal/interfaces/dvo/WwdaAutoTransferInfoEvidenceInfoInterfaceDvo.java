package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferInfoEvidenceInfoInterfaceDvo {

    private String rctDt; /*접수일자*/
    private String rctTm; /*접수시간*/
    private String chRcpUsrId; /*변경접수사용자ID*/
    private String psicNm; /*담당자명*/
    private String cntrNo; /*계약번호*/
    private String cntrSn; /*계약일련번호*/
    private String sellTpCd; /*판매유형코드*/
    private String sellTpCdNm; /*판매유형코드명*/
    private String pdNm; /*상품명*/
    private String mpno; /*휴대전화번호*/
    private String bfchFnitNm; /*변경전금융기관명*/
    private String bfchAcnoCdno; /*변경전계좌카드번호*/
    private String bfchOwrKnm; /*변경전소유자한글명*/
    private String afchFnitNm; /*변경후금융기관명*/
    @DBDecField
    private String afchAcnoCdno; /*변경후계좌카드번호*/
    private String afchOwrKnm; /*변경후소유자한글명*/
    private String fntStplD; /*이체약정일*/
    private String aftnEvidFshDt; /*자동이체증빙완료일자*/
    private String aftnEvidFshYn; /*자동이체증빙완료여부*/
    private String fnitAprRsCd; /*금융기관승인결과코드*/
    private String cralLocaraTno; /*휴대지역전화번호*/
    @DBDecField
    private String mexnoEncr; /*휴대전화국번호암호화*/
    private String cralIdvTno; /*휴대개별전화번호*/
}
