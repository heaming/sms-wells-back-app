package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvSendDtlDvo;

@Mapper
public interface WsnaPcsvSendDtlMapper {

    String selectOstAkNo();

    /**
     * TB_SVPD_OSTR_AK_PCSV_SEND_DTL  출고요청택배송신상세 
     * @param vo
     * @return
     */
    int insertPcsvSendDtl(WsnaPcsvSendDtlDvo vo);
}
