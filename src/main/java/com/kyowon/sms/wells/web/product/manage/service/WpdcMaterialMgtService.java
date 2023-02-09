package com.kyowon.sms.wells.web.product.manage.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.product.manage.converter.ZpdcMaterialConverter;
import com.kyowon.sms.common.web.product.manage.converter.ZpdcProductConverter;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapReq;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcMaterialMgtDto.SearchSapRes;
import com.kyowon.sms.common.web.product.manage.dto.ZpdcProductDto;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcEachTbPdbsPdRelDvo;
import com.kyowon.sms.common.web.product.manage.dvo.ZpdcProductDvo;
import com.kyowon.sms.common.web.product.manage.mapper.ZpdcProductMapper;
import com.kyowon.sms.common.web.product.manage.service.ZpdcHistoryMgtService;
import com.kyowon.sms.common.web.product.manage.service.ZpdcProductService;
import com.kyowon.sms.common.web.product.zcommon.constants.PdProductConst;
import com.kyowon.sms.wells.web.product.manage.mapper.WpdcMaterialMgtMapper;
import com.sds.sflex.common.common.dvo.UserSessionDvo;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpdcMaterialMgtService {

    private final WpdcMaterialMgtMapper mapper;
    private final ZpdcProductConverter productConverter;
    private final ZpdaClassificationMgtService clsfService;
    private final ZpdcProductMapper productMapper;
    private final ZpdcProductService productService;
    private final ZpdcHistoryMgtService hisService;
    private final ZpdcMaterialConverter converter;

    /**
     * SAP 교재/자재 페이징 조회(팝업)
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchSapRes> getMaterialSapPages(SearchSapReq dto, PageInfo pageInfo) {
        return mapper.selectMaterialSapPages(dto, pageInfo);
    }

    @Transactional
    public ZpdcProductDto.TbPdbsPdBas saveMaterial(ZpdcMaterialMgtDto.SaveReq dto)
        throws Exception {

        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());

        int processCount = 0;
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        if (session.getTenantCd().equals("W")) {
            // 교재/자재 : PD_TP_DTL_M(제품) , AS부품: PD_TP_DTL_A (As)부품
            dvo.setPdTpDtlCd(PdProductConst.PD_TP_DTL_CD_M);
        }

        // #1. 분류체계 분류 계층값 FILL-IN
        dvo = clsfService.getClassifcationHierarchy(dvo);

        // #2. 상품 마스터 INSERT
        processCount = productMapper.insertProduct(dvo);

        // #3. 각사 속성 INSERT
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        productService.saveEachCompanyPropDtl(dvo.getPdCd(), dto.tbPdbsPdEcomPrpDtl());

        // #4. 연결상품 INSERT
        this.editEachTbPdbsPdRel(dvo.getPdCd(), dto.tbPdbsPdRel());

        // #5. 이력 INSERT
        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(dvo.getPdCd(), startDtm);

        return productConverter.mapProductDvoToPdBas(dvo);
    }

    /**
     * 연결상품
     * @param pdCd
     * @param tbPdbsPdEcomPrpDtls
     * @throws Exception
     */
    public void editEachTbPdbsPdRel(String pdCd, List<ZpdcMaterialMgtDto.TbPdbsPdRel> tbPdbsPdRels)
        throws Exception {

        if (CollectionUtils.isNotEmpty(tbPdbsPdRels)) {
            String startDtm = DateUtil.getDate(new Date());
            String endDtm = PdProductConst.END_DATE_STR;

            // #1. 화면에서 삭제된 데이터 일괄 삭제처리.
            mapper.deleteTbPdbsPdRel(pdCd, tbPdbsPdRels, "NOTALL");

            // #2. 신규 추가 항목 INSERT
            List<ZpdcEachTbPdbsPdRelDvo> dvos = converter.mapTbPdbsPdRelDvos(tbPdbsPdRels);
            for (ZpdcEachTbPdbsPdRelDvo dvo : dvos) {
                dvo.setBasePdCd(pdCd); /* 상품관계ID */
                dvo.setVlStrtDtm(startDtm);
                dvo.setVlEndDtm(endDtm);
                mapper.mergeEachTbPdbsPdRel(dvo);
            }
        } else {
            // BASE_PD_CD에 걸려있는 모든 REL 데이터 일괄 삭제처리.
            mapper.deleteTbPdbsPdRel(pdCd, tbPdbsPdRels, "ALL");
        }
    }

    @Transactional
    public ZpdcProductDto.TbPdbsPdBas editMaterial(ZpdcMaterialMgtDto.EditReq dto)
        throws Exception {

        ZpdcProductDvo dvo = productConverter.mapPdBasToProductDvo(dto.tbPdbsPdBas());

        int processCount = 0;
        processCount = productMapper.updateProduct(dvo);

        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        productService.saveEachCompanyPropDtl(dvo.getPdCd(), dto.tbPdbsPdEcomPrpDtl());

        this.editEachTbPdbsPdRel(dvo.getPdCd(), dto.tbPdbsPdRel());

        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(dvo.getPdCd(), startDtm);
        return productConverter.mapProductDvoToPdBas(dvo);
    }

    @Transactional
    public int removeMaterial(String pdCd) throws Exception {
        productMapper.deleteEachCompanyProps(pdCd, "");
        mapper.deleteEachPdbsPdRels(pdCd);

        int processCount = productMapper.deleteProduct(pdCd);
        String startDtm = DateUtil.getDate(new Date());
        hisService.createProductHistory(pdCd, startDtm);
        return processCount;
    }
}
