package com.kyowon.sms.wells.web.service.stock.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaDisposalMaterialsPsDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaDisposalMaterialsPsTxtDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaDisposalMaterialsPsMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0277M01 매각자재관리 현황 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-18
 */

@Service
@RequiredArgsConstructor
public class WsnaDisposalMaterialsPsService {

    private final WsnaDisposalMaterialsPsMapper mapper;

    // 메시지 서비스
    private final MessageResourceService messageService;

    /**
     * 창고조회
     * @param baseYm
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getMcbyWareHouses(String baseYm) {
        ValidAssert.hasText(baseYm);

        return this.mapper.selectMcbyWareHouses(baseYm);
    }

    /**
     * 매각자재 현황 조회
     * @param baseYm
     * @param wareNo
     * @return
     */
    public List<WsnaDisposalMaterialsPsDvo> getDisposalMaterials(String baseYm, String wareNo) {
        ValidAssert.hasText(baseYm);

        List<WsnaDisposalMaterialsPsDvo> dvos = this.mapper.selectDisposalMaterials(baseYm, wareNo);
        // 합계 데이터 만들기
        this.makeDisposalMaterialTotalSum(dvos);

        return dvos;
    }

    /**
     * 합계 데이터 만들기
     * @param dvos
     */
    private void makeDisposalMaterialTotalSum(List<WsnaDisposalMaterialsPsDvo> dvos) {
        // 합계
        String txtSum = this.messageService.getMessage("MSG_TXT_SUM");

        List<WsnaDisposalMaterialsPsTxtDvo> txtDvos = this.mapper.selectDisposalMaterialTexts();
        for (WsnaDisposalMaterialsPsTxtDvo txtDvo : txtDvos) {
            String gubun = txtDvo.getGubun();
            String gubunCd = txtDvo.getGubunCd();

            WsnaDisposalMaterialsPsDvo addDvo = new WsnaDisposalMaterialsPsDvo();
            addDvo.setWareNm(txtSum);
            addDvo.setGubun(gubun);

            Stream<WsnaDisposalMaterialsPsDvo> streams = dvos.stream().filter(dvo -> gubunCd.equals(dvo.getGubunCd()));

            long d01Qty = streams.mapToLong(dvo -> dvo.getD01Qty().longValue()).sum();
            addDvo.setD01Qty(BigDecimal.valueOf(d01Qty));

            long d02Qty = streams.mapToLong(dvo -> dvo.getD02Qty().longValue()).sum();
            addDvo.setD02Qty(BigDecimal.valueOf(d02Qty));

            long d03Qty = streams.mapToLong(dvo -> dvo.getD03Qty().longValue()).sum();
            addDvo.setD03Qty(BigDecimal.valueOf(d03Qty));

            long d04Qty = streams.mapToLong(dvo -> dvo.getD04Qty().longValue()).sum();
            addDvo.setD04Qty(BigDecimal.valueOf(d04Qty));

            long d05Qty = streams.mapToLong(dvo -> dvo.getD05Qty().longValue()).sum();
            addDvo.setD05Qty(BigDecimal.valueOf(d05Qty));

            long d06Qty = streams.mapToLong(dvo -> dvo.getD06Qty().longValue()).sum();
            addDvo.setD06Qty(BigDecimal.valueOf(d06Qty));

            long d07Qty = streams.mapToLong(dvo -> dvo.getD07Qty().longValue()).sum();
            addDvo.setD07Qty(BigDecimal.valueOf(d07Qty));

            long d08Qty = streams.mapToLong(dvo -> dvo.getD08Qty().longValue()).sum();
            addDvo.setD08Qty(BigDecimal.valueOf(d08Qty));

            long d09Qty = streams.mapToLong(dvo -> dvo.getD09Qty().longValue()).sum();
            addDvo.setD09Qty(BigDecimal.valueOf(d09Qty));

            long d10Qty = streams.mapToLong(dvo -> dvo.getD10Qty().longValue()).sum();
            addDvo.setD10Qty(BigDecimal.valueOf(d10Qty));

            long d11Qty = streams.mapToLong(dvo -> dvo.getD11Qty().longValue()).sum();
            addDvo.setD11Qty(BigDecimal.valueOf(d11Qty));

            long d12Qty = streams.mapToLong(dvo -> dvo.getD12Qty().longValue()).sum();
            addDvo.setD12Qty(BigDecimal.valueOf(d12Qty));

            long d13Qty = streams.mapToLong(dvo -> dvo.getD13Qty().longValue()).sum();
            addDvo.setD13Qty(BigDecimal.valueOf(d13Qty));

            long d14Qty = streams.mapToLong(dvo -> dvo.getD14Qty().longValue()).sum();
            addDvo.setD14Qty(BigDecimal.valueOf(d14Qty));

            long d15Qty = streams.mapToLong(dvo -> dvo.getD15Qty().longValue()).sum();
            addDvo.setD15Qty(BigDecimal.valueOf(d15Qty));

            long d16Qty = streams.mapToLong(dvo -> dvo.getD16Qty().longValue()).sum();
            addDvo.setD16Qty(BigDecimal.valueOf(d16Qty));

            long d17Qty = streams.mapToLong(dvo -> dvo.getD17Qty().longValue()).sum();
            addDvo.setD17Qty(BigDecimal.valueOf(d17Qty));

            long d18Qty = streams.mapToLong(dvo -> dvo.getD18Qty().longValue()).sum();
            addDvo.setD18Qty(BigDecimal.valueOf(d18Qty));

            long d19Qty = streams.mapToLong(dvo -> dvo.getD19Qty().longValue()).sum();
            addDvo.setD19Qty(BigDecimal.valueOf(d19Qty));

            long d20Qty = streams.mapToLong(dvo -> dvo.getD20Qty().longValue()).sum();
            addDvo.setD20Qty(BigDecimal.valueOf(d20Qty));

            long d21Qty = streams.mapToLong(dvo -> dvo.getD21Qty().longValue()).sum();
            addDvo.setD21Qty(BigDecimal.valueOf(d21Qty));

            long d22Qty = streams.mapToLong(dvo -> dvo.getD22Qty().longValue()).sum();
            addDvo.setD22Qty(BigDecimal.valueOf(d22Qty));

            long d23Qty = streams.mapToLong(dvo -> dvo.getD23Qty().longValue()).sum();
            addDvo.setD23Qty(BigDecimal.valueOf(d23Qty));

            long d24Qty = streams.mapToLong(dvo -> dvo.getD24Qty().longValue()).sum();
            addDvo.setD24Qty(BigDecimal.valueOf(d24Qty));

            long d25Qty = streams.mapToLong(dvo -> dvo.getD25Qty().longValue()).sum();
            addDvo.setD25Qty(BigDecimal.valueOf(d25Qty));

            long d26Qty = streams.mapToLong(dvo -> dvo.getD26Qty().longValue()).sum();
            addDvo.setD26Qty(BigDecimal.valueOf(d26Qty));

            long d27Qty = streams.mapToLong(dvo -> dvo.getD27Qty().longValue()).sum();
            addDvo.setD27Qty(BigDecimal.valueOf(d27Qty));

            long d28Qty = streams.mapToLong(dvo -> dvo.getD28Qty().longValue()).sum();
            addDvo.setD28Qty(BigDecimal.valueOf(d28Qty));

            long d29Qty = streams.mapToLong(dvo -> dvo.getD29Qty().longValue()).sum();
            addDvo.setD29Qty(BigDecimal.valueOf(d29Qty));

            long d30Qty = streams.mapToLong(dvo -> dvo.getD30Qty().longValue()).sum();
            addDvo.setD30Qty(BigDecimal.valueOf(d30Qty));

            long d31Qty = streams.mapToLong(dvo -> dvo.getD31Qty().longValue()).sum();
            addDvo.setD31Qty(BigDecimal.valueOf(d31Qty));

            long totQty = streams.mapToLong(dvo -> dvo.getTotQty().longValue()).sum();
            addDvo.setTotQty(BigDecimal.valueOf(totQty));

            dvos.add(addDvo);
        }
    }

}
