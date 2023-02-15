package com.kyowon.sms.wells.web.product.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.product.category.service.ZpdaClassificationMgtService;
import com.kyowon.sms.common.web.product.manage.converter.ZpdcProductConverter;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductDvo;
import com.kyowon.sms.common.web.product.manage.mapper.ZpdcProductMapper;
import com.kyowon.sms.common.web.product.manage.service.ZpdcHistoryMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartMgtDto;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcAsPartMgtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcAsPartsMgtService {

    private final WpdcAsPartMgtMapper mapper;
    private final ZpdcProductConverter productConverter;
    private final ZpdaClassificationMgtService clsfService;
    private final ZpdcProductMapper productMapper;
    private final ZpdcProductService productService;
    private final ZpdcHistoryMgtService hisService;

    /**
     * AS부품 목록 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WpdcAsPartMgtDto.SearchRes> getAsPartPages(
        WpdcAsPartMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAsPartPages(dto, pageInfo);
    }

    /**
     * AS부품 목록 엑셀다운로드
     * @param dto
     * @return
     */
    public List<WpdcAsPartMgtDto.SearchRes> getAsPartsForExcelDownload(
        WpdcAsPartMgtDto.SearchReq dto
    ) {
        return this.mapper.selectAsPartPages(dto);
    }

    @Transactional
    public ZpdcProductDto.TbPdbsPdBas saveAsParts(WpdcAsPartMgtDto.SaveReq dto)
        throws Exception {

        int processCount = 0;
        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());

        // #1. Wells 특화
        // 교재/자재 : PD_TP_DTL_M(제품) , AS부품: PD_TP_DTL_A (As)부품
        dvo.setPdTpDtlCd(PdProductConst.PD_TP_DTL_CD_P);

        // #2. 분류체계 분류 계층값 FILL-IN
        dvo = clsfService.getClassifcationHierarchy(dvo);

        // #3. 상품 마스터 INSERT
        processCount = productMapper.insertProduct(dvo);

        // #4. 각사 속성 INSERT
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        productService.saveEachCompanyPropDtl(dvo.getPdCd(), dto.tbPdbsPdEcomPrpDtl());

        // #5. 이력 INSERT
        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(dvo.getPdCd(), startDtm);

        return productConverter.mapProductDvoToPdBas(dvo);
    }

    @Transactional
    public ZpdcProductDto.TbPdbsPdBas editAsParts(WpdcAsPartMgtDto.EditReq dto)
        throws Exception {

        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());

        int processCount = 0;
        processCount = productMapper.updateProduct(dvo);

        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        productService.saveEachCompanyPropDtl(dvo.getPdCd(), dto.tbPdbsPdEcomPrpDtl());

        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(dvo.getPdCd(), startDtm);
        return productConverter.mapProductDvoToPdBas(dvo);
    }

    @Transactional
    public int removeAsParts(String pdCd) throws Exception {
        productMapper.deleteEachCompanyProps(pdCd, "");

        int processCount = productMapper.deleteProduct(pdCd);
        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(pdCd, startDtm);
        return processCount;
    }
}
