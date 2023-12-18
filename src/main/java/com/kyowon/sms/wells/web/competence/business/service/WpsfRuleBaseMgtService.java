package com.kyowon.sms.wells.web.competence.business.service;

import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.business.converter.WpsfRuleBaseMgtConverter;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SaveReq;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfRuleBaseMgtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseDvo;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfRuleBaseInquiryDvo;
import com.kyowon.sms.wells.web.competence.business.mapper.WpsfRuleBaseMgtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WpsfRuleBaseMgtService {

    private final WpsfRuleBaseMgtMapper mapper;
    private final WpsfRuleBaseMgtConverter converter;
    private final AttachFileService attachFileService;

    /**
     * 규정 및 기준 관리 - 리스트 조회
     *
     * @param dto
     * @return List<SearchRes>
     */
    public List<SearchRes> getRuleBaseList(SearchReq dto) {
        return mapper.selectRuleBaseMgtPages(dto);
    }

   /**
     * 규정 및 기준 관리 - 상세 조회
     * @param req
     * @return SearchRes
     */
    public SearchRes getRuleBaseDetail(SearchReq req) {
        return mapper.selectRuleBaseDetail(req);
    }

    /**
     * 규정 및 기준조회 - 리스트 조회
     *
     * @param req
     * @return List<SearchRes>
     */
    public List<SearchRes> getUserRuleBase(SearchReq req) {
        return mapper.selectUserRuleBase(req);
    }

    /**
     * 규정 및 기준관리 등록,수정
     *
     * @param dto
     * @return processCount
     */
    @Transactional
    public int saveRuleBase(SaveReq dto) throws Exception {
        int processCount = 0;
        WpsfRuleBaseDvo dvo = converter.mapSaveReq(dto);
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);

        // 1) 영업지원매뉴얼ID 존재 여부
        if(StringUtils.isBlank(dvo.getBznsSpptMnalId())){
            // 2) 신규 등록
            processCount = mapper.insertRuleBase(dvo);
            if(StringUtils.isBlank(dvo.getApnFileDocId())){
                dvo.setApnFileDocId(dvo.getHgrBznsSpptMnalId().concat(dvo.getBznsSpptMnalId()));
            }
        }else{
            // 2) 기존 데이터 유효기간 종료 업데이트 처리
            mapper.updatePrevRuleBase(dvo);

            // 3) 데이터 Insert
            processCount = mapper.insertRuleBase(dvo);
        }
        // 4) 공개범위(일부 공개) 저장
        if (CollectionUtils.isNotEmpty(dvo.getRsbDvCds())) {
            WpsfRuleBaseInquiryDvo wDvo = new WpsfRuleBaseInquiryDvo();
            wDvo.setDtaDlYn(DeDeductionConst.DELETE_N);

            // 기존 직책 삭제처리
            mapper.deleteRuleBaseRel(dvo);
            for (String rsbDvCd : dvo.getRsbDvCds()) {
                wDvo.setBznsSpptMnalId(dvo.getBznsSpptMnalId());
                wDvo.setCstCnrInqrPrmitYn(dvo.getCstCnrInqrPrmitYn());
                wDvo.setRsbDvCd(rsbDvCd);
                mapper.insertRuleBaseRel(wDvo);
            }
        }

        // 첨부파일 저장 호출
        if (CollectionUtils.isNotEmpty(dto.attachFiles())) {
            attachFileService.saveAttachFiles("ATG_PSF_RUL_BASE", dvo.getApnFileDocId(), dto.attachFiles());
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
    public int saveRuleBaseTree(List<SaveReq> dtos) {
        int processCount = 0;
        for (SaveReq dto : dtos) {
            WpsfRuleBaseDvo dvo = converter.mapSaveReq(dto);
            dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
            processCount = mapper.updateRuleBaseTree(dvo);
        }
        return processCount;
    }


}
