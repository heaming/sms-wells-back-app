package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.FindRes;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsDataDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsHgrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaTransferMaterialsIostDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOrganizationDvo;

/**
 * <pre>
 * W-SV-U-0138M01 창고조직 관리
 * W-SV-U-0175P01 창고조직 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022-12-13
 */
@Mapper(componentModel = "spring")
public interface WsnaWarehouseOrganizationConverter {

    WsnaWarehouseOrganizationDvo mapCreateReqToWsnaWarehouseOgDvo(CreateReq dto);

    FindRes mapWsnaWarehouseOgDvoToFindRes(WsnaWarehouseOrganizationDvo dvo);

    WsnaWarehouseOrganizationDvo mapSaveReqToWsnaWarehouseOgDvo(SaveReq dto);

    WsnaTransferMaterialsIostDvo mapDataDvoToIostDvo(WsnaTransferMaterialsDataDvo transferDvo);

    WsnaTransferMaterialsHgrDvo mapDataDvoToHgrDvo(WsnaTransferMaterialsDataDvo dataDvo);

}
