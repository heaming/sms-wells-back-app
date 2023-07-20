package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaDisposalMaterialsPsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaDisposalMaterialsPsMapper;
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

        return this.mapper.selectDisposalMaterials(baseYm, wareNo);
    }
}
