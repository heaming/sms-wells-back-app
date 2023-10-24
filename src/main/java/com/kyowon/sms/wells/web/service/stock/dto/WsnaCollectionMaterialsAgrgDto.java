package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaCollectionMaterialsAgrgDto {

    @ApiModel(value = "WsnaCollectionMaterialsAgrgDto-SearchReq")
    public record SearchReq(

        String startDt,

        String endDt,

        String wareDtlDvCd,

        String wareNoM,

        String wareNoD
    ) {}
    @ApiModel(value = "WsnaCollectionMaterialsAgrgDto-SearchRes")
    public record SearchRes(

        String wareNo,

        String wareNm,

        String qtySum,

        String qtyWm07101720, /* 회수 키트류 */

        String qtyWm07101721, /* 회수 조리수밸브 */

        String qtyWm07101722, /* 회수 연수기밸브류 */

        String qtyWm07101723 /* 회수 연수기호스류 */
    ) {}
}
