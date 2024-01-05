package com.kyowon.sms.wells.web.withdrawal.interfaces.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdaAutoTransferObjectItemizationInterfaceSearchDvo {

    private String cstNo; /*고객번호*/
    private List<String> sellTps; /*판매유형코드리스트*/
    private String excdYn01; /*제외여부01*/
    private String excdYn02; /*제외여부02*/
    private String excdYn03; /*제외여부03*/
    private String excdYn04; /*제외여부04*/
    private String excdYn05; /*제외여부05*/
}
