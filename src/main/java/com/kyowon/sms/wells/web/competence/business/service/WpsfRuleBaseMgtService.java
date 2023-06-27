package com.kyowon.sms.wells.web.competence.business.service;

import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchRes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.business.converter.WpsfRuleBaseMgtConverter;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseDvo;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseInquiryDvo;
import com.kyowon.sms.wells.web.competence.business.mapper.WpsfRuleBaseMgtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpsfRuleBaseMgtService {

    private final WpsfRuleBaseMgtMapper mapper;
    private final WpsfRuleBaseMgtConverter converter;
    private final AttachFileService attachFileService;
    private final String groupId = "ATG_PSF_RUL_BASE";

    public PagingResult<SearchRes> getRuleBaseMgtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRuleBaseMgtPages(dto, pageInfo);
    }

    public List<SearchRes> getRuleBaseMgtsForExcelDownload(SearchReq dto) {
        return mapper.selectRuleBaseMgtPages(dto);
    }

    public List<SearchRes> getRuleBase(SearchReq dto) {
        return mapper.selectRuleBase(dto);
    }

    /**
     * 규정 및 기준관리 등록,수정
     *
     * @param dto
     * @return processCount
     */
    @Transactional
    public int saveRuleBase(WpsfRuleBaseMgtDto.SaveReq dto) throws Exception {
        int processCount = 0;
        WpsfRuleBaseDvo dvo = converter.mapSaveReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);

        if (dvo.getInqrLvTcnt() == 3 && dvo.getBznsSpptMnalRgstCd().equals("02")) {

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String nowDate = formatter.format(date);

            BigDecimal vlEndDtm = new BigDecimal(nowDate);
            BigDecimal one = new BigDecimal("1");
            String strBigNum = vlEndDtm.subtract(one).toString();
            dvo.setVlEndDtm(strBigNum);
            processCount = mapper.updatePrevRuleBase(dvo);

            dvo.setBznsSpptMnalRgstCd("01");
            //String BnzsSpptMnalId = mapper.selectBnzsSpptMnalId();
            //dvo.setBznsSpptMnalId(BnzsSpptMnalId);
            dvo.setVlStrtDtm(nowDate);
            processCount = mapper.insertRuleBase(dvo);
        } else {
            processCount = mapper.updateRuleBase(dvo);
        }

        if (CollectionUtils.isNotEmpty(dto.rsbDvCds())) {
            WpsfRuleBaseInquiryDvo wDvo = new WpsfRuleBaseInquiryDvo();
            wDvo.setDtaDlYn(DeDeductionConst.DELETE_N);

            for (String rsbDvCd : dto.rsbDvCds()) {
                String mnalRghId = mapper.selectMnalRghId();
                wDvo.setMnalRghRelId(mnalRghId);
                wDvo.setBznsSpptMnalId(dvo.getBznsSpptMnalId());
                wDvo.setMnalRghId(mnalRghId);
                wDvo.setOgTpCd(dto.ogTpCd());
                wDvo.setRsbDvCd(rsbDvCd);
                processCount = mapper.insertRuleBaseRel(wDvo);
            }
        }

        // 첨부파일 저장 호출
        if (CollectionUtils.isNotEmpty(dto.attachFiles())) {
            attachFileService.saveAttachFiles(groupId, dvo.getApnFileDocId(), dto.attachFiles());
        }
        return processCount;
    }

    /**
     * 규정 및 기준관리 등록,수정
     *
     * @param dtos
     * @return processCount
     */
    @Transactional
    public int saveRuleBaseTree(List<WpsfRuleBaseMgtDto.SaveReq> dtos) {
        int processCount = 0;
        for (WpsfRuleBaseMgtDto.SaveReq dto : dtos) {
            WpsfRuleBaseDvo dvo = converter.mapSaveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            processCount = mapper.updateRuleBaseTree(dvo);
        }
        return processCount;
    }

}
