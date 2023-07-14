package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.ProdutCodeRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbGiveAOrderDvo;

@Mapper
public interface WsnaBsCsmbGiveAOrderMapper {
    List<WsnaBsCsmbGiveAOrderDvo> selectBeforeBsCsmbGiveAOrderQty(SearchReq dto);

    List<WsnaBsCsmbGiveAOrderDvo> selectBsCsmbGiveAOrderQty(SearchReq dto);

    int insertBsCsmbGiveAOrderQty(WsnaBsCsmbGiveAOrderDvo dvo);

    int deleteBsCsmbGiveAOrderQty(String mngtYm);

    int selectExistBsCsmbGiveAOrderQtyYn(String mngtYm);

    List<ProdutCodeRes> selectProductCodesByItmKndCd(String itmKndCd);
}
