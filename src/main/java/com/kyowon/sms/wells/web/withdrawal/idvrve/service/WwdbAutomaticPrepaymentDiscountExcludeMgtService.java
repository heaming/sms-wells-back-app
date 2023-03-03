package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbAutomaticPrepaymentDiscountExcludeMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchContractInformationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchContractInformationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbAutomaticPrepaymentDiscountExcludeMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbAutomaticPrepaymentDiscountExcludeMgtMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbAutomaticPrepaymentDiscountExcludeMgtService {

    private final WwdbAutomaticPrepaymentDiscountExcludeMgtMapper mapper;

    private final WwdbAutomaticPrepaymentDiscountExcludeMgtConverter convert;

    /**
     * 자동 선납할인제외 관리 조회 목록조회
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public PagingResult<SearchRes> getAutomaticPrepaymentDiscountExcludePages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAutomaticPrepaymentDiscountExcludes(dto, pageInfo);

    }

    /**
     * 자동 선납할인제외 관리 조회 엑셀 다운로드
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public List<SearchRes> getAutomaticPrepaymentDiscountExcludeExcels(SearchReq dto) {
        return mapper.selectAutomaticPrepaymentDiscountExcludes(dto);

    }

    /**
     * 자동 선납할인제외 관리 계약 정보 조회
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public SearchContractInformationRes getAutomaticPrepaymentDiscountExcludeContractInformation(
        SearchContractInformationReq dto
    ) {
        return mapper.selectAutomaticPrepaymentDiscountExcludeContractInformation(dto);

    }

    /**
     * 자동 선납할인제외 관리 저장
     *
     * @param SaveReq
     * @return int
     */
    @Transactional
    public int saveAutomaticPrepaymentDiscountExcludes(
        List<SaveReq> dtos
    ) throws Exception {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WwdbAutomaticPrepaymentDiscountExcludeMgtDvo dvo = convert
                .mapSaveWwdbAutomaticPrepaymentDiscountExcludeMgtDvo(dto);
            String cntr = dto.cntr();
            if (!StringUtil.isEmpty(cntr)) {
                dvo.setCntrNo(cntr.substring(0, 12));
                dvo.setCntrSn(cntr.substring(12));
            } else {
                throw new BizException("계약번호를 입력해주세요.");
            }

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int duplicateCount = mapper.selectAutomaticPrepaymentDiscountExcludesDuplicateCount(dvo);
                    if (duplicateCount != 0) {
                        throw new BizException("중복 된 값이 존재합니다.");
                    } else {
                        processCount += mapper.insertAutomaticPrepaymentDiscountExcludes(dvo);
                        processCount += mapper.insertAutomaticPrepaymentDiscountExcludeHistory(dvo);
                    }
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateAutomaticPrepaymentDiscountExcludes(dvo);
                    processCount += mapper.insertAutomaticPrepaymentDiscountExcludeHistory(dvo);
                }
                case CommConst.ROW_STATE_DELETED -> {
                    processCount += mapper.deleteAutomaticPrepaymentDiscountExcludes(dvo);
                    processCount += mapper.insertAutomaticPrepaymentDiscountExcludeHistory(dvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }

        return processCount;
    }

}
