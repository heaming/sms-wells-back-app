package com.kyowon.sms.wells.web.product.manage.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductDvo;
import com.kyowon.sms.common.web.product.manage.service.ZpdcHistoryMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcRelationMgtService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcServiceMgtDto;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcServiceMgtService {

    private final ZpdcProductService pdService;
    private final ZpdcHistoryMgtService hisService;
    private final ZpdcRelationMgtService relService;

    @Transactional
    public ZpdcProductDto.TbPdbsPdBas saveProduct(WpdcServiceMgtDto.SaveReq dto, boolean isCreate)
        throws Exception {
        String startDtm = DateUtil.getDate(new Date());
        String pdCd = dto.pdCd();
        if (isCreate || dto.isModifiedProp()) {
            ZpdcProductDvo prd = pdService.saveProductBase(dto.tbPdbsPdBas(), isCreate, startDtm);
            pdCd = prd.getPdCd();
            pdService.saveEachCompanyPropDtl(pdCd, dto.tbPdbsPdEcomPrpDtl());
            if ((!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(prd.getTempSaveYn()))
                || isCreate) {
                // 상품 정보 이력 저장 (가격 X)
                hisService.createProductHistory(pdCd, startDtm);
            }
        }
        relService.saveProductRelations(pdCd, dto.tbPdbsPdRel(), startDtm);
        return pdService.getProductByPdCd(pdCd);
    }

    @Transactional
    public int removeProduct(String pdCd) throws Exception {
        String startDtm = DateUtil.getDate(new Date());
        return pdService.removeProduct(pdCd, startDtm);
    }
}
