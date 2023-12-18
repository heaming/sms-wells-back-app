package com.kyowon.sms.wells.web.product.manage.service;

import java.util.Date;
import java.util.List;

import org.eclipse.jetty.util.StringUtil;
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
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 기준 상품 관리 서비스 
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Service
@RequiredArgsConstructor
public class WpdcStandardMgtService {

    private final ZpdcProductService pdService;
    private final ZpdcPriceMgtService prcService;
    private final ZpdcRelationMgtService relService;
    private final ZpdcHistoryMgtService hisService;

    /**
     * 기준상품 저장
     * @param dto 상품 저장 정보
     * @param isCreate - 생성(true), 수정(false)
     * @return 생성됨 상품 정보
     * @throws Exception 미처리 시 Exception 처리
     */
    @Transactional
    public ZpdcProductDto.TbPdbsPdBas saveProduct(WpdcStandardMgtDto.SaveReq dto, boolean isCreate)
        throws Exception {
        String startDtm = DateUtil.getDate(new Date());
        String pdCd = dto.pdCd();
        if (isCreate || dto.isModifiedProp()) {
            // 최초생성이거나 상품 속성이 수정된 경우
            ZpdcProductDvo prd = pdService.saveProductBase(dto.tbPdbsPdBas(), isCreate, startDtm);
            pdCd = prd.getPdCd();
            pdService.saveProductDetail(prd.getPdCd(), dto.tbPdbsPdDtl(), isCreate, startDtm);
            pdService.saveEachCompanyPropDtl(prd.getPdCd(), dto.tbPdbsPdEcomPrpDtl());
            if ((!dto.isOnlyFileModified() && PdProductConst.TEMP_SAVE_N.equals(prd.getTempSaveYn()))
                || isCreate) {
                // 최초생성, 첨부파일만 수정이 아닌경우, 임시저장이 아닌경우 상품 이력 저장 (가격 X)
                hisService.createProductHistory(pdCd, startDtm);
            }
        } else {
            if (StringUtil.isNotBlank(pdCd)) {
                // 동시 저장을 방지하기 위해, 상풍수정일 저장
                pdService.saveProductBaseFinalDtm(dto.pdCd());
            }
        }
        // 가격 저장
        if (isCreate || dto.isModifiedPrice()) {
            // 최초생성이거나 가격 속성이 수정된 경우
            ZpdcProductPriceDvo prc = prcService.saveProductPrices(pdCd, startDtm);
            List<ZpdcProductPriceDetailDvo> dtls = prcService.savePricePropDetails(prc, dto.tbPdbsPdPrcDtl(), startDtm);
            prcService.savePricePropFinalDetails(prc, dtls, dto.tbPdbsPdPrcFnlDtl(), startDtm);
            prcService.savePriceDiscountPremiumDetail(pdCd, dto.tbPdbsPdDscPrumDtl());
        }
        // 연결상품 저장
        if (isCreate || dto.isModifiedRelation()) {
            // 최초생성이거나 연결상품이 수정된 경우
            relService.saveProductRelations(pdCd, dto.tbPdbsPdRel(), startDtm);
            hisService.createRelationHistory(pdCd, startDtm);
        }
        return pdService.getProductByPdCd(pdCd);
    }

    /**
     * 기준상품 삭제
     * @param pdCd 상품코드
     * @return 상품 삭제 건수
     * @throws Exception 미처리 시 Exception 처리
     */
    @Transactional
    public int removeProduct(String pdCd) throws Exception {
        String startDtm = DateUtil.getDate(new Date());
        return pdService.removeProduct(pdCd, startDtm);
    }

}
