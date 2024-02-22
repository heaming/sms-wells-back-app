package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-I-0035 고객센터 인터페이스 특이사항 조회
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.04.04
 */
@Setter
@Getter
public class WsniCustomerInformationDvo {

    String sysDvCd;
    String fstRgstDtm;
    String fstRgstUsrId;
    String prtnrKnm;
    String ogCd;
    String ogNm;
    String cstUnuitmCn;

}
