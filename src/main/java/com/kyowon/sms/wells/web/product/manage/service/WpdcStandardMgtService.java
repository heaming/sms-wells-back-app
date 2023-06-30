package com.kyowon.sms.wells.web.product.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductPriceDetailDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductPriceDvo;
import com.kyowon.sms.common.web.product.manage.service.ZpdcHistoryMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcPriceMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcRelationMgtService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcStandardMgtDto;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcStandardMgtMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcStandardMgtService {

    private final ZpdcProductService pdService;
    private final ZpdcPriceMgtService prcService;
    private final ZpdcRelationMgtService relService;
    private final ZpdcHistoryMgtService hisService;
    private final WpdcStandardMgtMapper mapper;

    @Transactional
    public ZpdcProductDto.TbPdbsPdBas saveProduct(WpdcStandardMgtDto.SaveReq dto, boolean isCreate)
        throws Exception {
        String startDtm = DateUtil.getDate(new Date());
        String pdCd = dto.pdCd();
        if (isCreate || dto.isModifiedProp()) {
            ZpdcProductDvo prd = pdService.saveProductBase(dto.tbPdbsPdBas(), isCreate, startDtm);
            pdCd = prd.getPdCd();
            pdService.saveProductDetail(prd.getPdCd(), dto.tbPdbsPdDtl(), isCreate, startDtm);
            pdService.saveEachCompanyPropDtl(prd.getPdCd(), dto.tbPdbsPdEcomPrpDtl());
            if ((!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(prd.getTempSaveYn()))
                || isCreate) {
                // 상품 정보 이력 저장 (가격 X)
                hisService.createProductHistory(pdCd, startDtm);
            }
        }
        if (isCreate || dto.isModifiedPrice()) {
            ZpdcProductPriceDvo prc = prcService.saveProductPrices(pdCd, startDtm);
            List<ZpdcProductPriceDetailDvo> dtls = prcService.savePricePropDetails(prc, dto.tbPdbsPdPrcDtl(), startDtm);
            prcService.savePricePropFinalDetails(prc, dtls, dto.tbPdbsPdPrcFnlDtl(), startDtm);
            prcService.savePriceDiscountPremiumDetail(pdCd, dto.tbPdbsPdDscPrumDtl());
        }
        if (isCreate || dto.isModifiedRelation()) {
            relService.saveProductRelations(pdCd, dto.tbPdbsPdRel(), startDtm);
            hisService.createRelationHistory(pdCd, startDtm);
        }
        return pdService.getProductByPdCd(pdCd);
    }

    @Transactional
    public int removeProduct(String pdCd) throws Exception {
        String startDtm = DateUtil.getDate(new Date());
        prcService.removePdPriceByPdCd(pdCd);
        return pdService.removeProduct(pdCd, startDtm);
    }

    public WpdcStandardMgtDto.SaleRecognitionClassification getSaleRecognClassName(String slRcogClsfCd) {
        return mapper.selectSaleRecognClassName(slRcogClsfCd);
    }

}
