package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbAutoPrepaymentDiscountExcludeMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchContractReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchContractRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbAutoPrepaymentDiscountExcludeMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbAutoPrepaymentDiscountExcludeMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbAutoPrepaymentDiscountExcludeMgtService {

    private final WwdbAutoPrepaymentDiscountExcludeMgtMapper mapper;

    private final WwdbAutoPrepaymentDiscountExcludeMgtConverter convert;

    /**
     * 자동 선납할인제외 관리 조회 목록조회
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public PagingResult<SearchRes> getAutoPrepaymentDiscountExcludePages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAutoPrepaymentDiscountExcludes(dto, pageInfo);

    }

    /**
     * 자동 선납할인제외 관리 조회 엑셀 다운로드
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public List<SearchRes> getAutoPrepaymentDiscountExcludeExcels(SearchReq dto) {
        return mapper.selectAutoPrepaymentDiscountExcludes(dto);

    }

    /**
     * 자동 선납할인제외 관리 계약 정보 조회
     *
     * @param req
     * @param pageInfo
     * @return PagingResult
     */
    @Transactional
    public SearchContractRes getAutoPrepaymentDiscountExcludeContractInformation(
        SearchContractReq dto
    ) {
        return mapper.selectAutoPrepaymentDiscountExcludeContractInformation(dto);

    }

    /**
     * 자동 선납할인제외 관리 저장
     *
     * @param SaveReq
     * @return int
     */
    @Transactional
    public int saveAutoPrepaymentDiscountExcludes(
        List<SaveReq> dtos
    ) throws Exception {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WwdbAutoPrepaymentDiscountExcludeMgtDvo dvo = convert
                .mapSaveWwdbAutoPrepaymentDiscountExcludeMgtDvo(dto);
            //            String cntr = dto.cntr();
            //            if (!StringUtil.isEmpty(cntr)) {
            //                dvo.setCntrNo(cntr.substring(0, 12));
            //                dvo.setCntrSn(cntr.substring(12));
            //            } else {
            //                throw new BizException("계약번호를 입력해주세요.");
            //            }

            String cntr = dto.cntr();
            BizAssert.hasText(cntr, "MSG_ALT_CHK_CNTR_NO");

            dvo.setCntrNo(cntr.substring(0, 12));
            dvo.setCntrSn(cntr.substring(12));

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int duplicateCount = mapper.selectAutoPrepaymentDiscountExcludesDuplicateCount(dvo);
                    //                    if (duplicateCount != 0) {
                    //                        throw new BizException("중복 된 값이 존재합니다.");
                    //                    } else {
                    //                        processCount += mapper.insertAutoPrepaymentDiscountExcludes(dvo);
                    //                        processCount += mapper.insertAutoPrepaymentDiscountExcludeHistory(dvo);
                    //                    }
                    BizAssert.isTrue(duplicateCount == 0, "MSG_ALT_DUPLICATE_EXISTS");

                    processCount += mapper.insertAutoPrepaymentDiscountExcludes(dvo);
                    processCount += mapper.insertAutoPrepaymentDiscountExcludeHistory(dvo);

                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateAutoPrepaymentDiscountExcludes(dvo);
                    processCount += mapper.insertAutoPrepaymentDiscountExcludeHistory(dvo);
                }
                case CommConst.ROW_STATE_DELETED -> {
                    processCount += mapper.deleteAutoPrepaymentDiscountExcludes(dvo);
                    processCount += mapper.insertAutoPrepaymentDiscountExcludeHistory(dvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }

        return processCount;
    }

}
