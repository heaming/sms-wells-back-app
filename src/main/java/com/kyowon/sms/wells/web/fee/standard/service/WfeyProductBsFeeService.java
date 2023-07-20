package com.kyowon.sms.wells.web.fee.standard.service;

import com.kyowon.sms.wells.web.fee.standard.converter.WfeyProductBsFeeConverter;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SaveProductBsFeeReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SearchProductBsFeeRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyProductBsFeeDvo;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyProductBsFeeMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * <pre>
 * 상품별 BS 수수료 기준정보
 * </pre>
 *
 * @author mj
 * @since 2023.07.13
 */
@Service
@RequiredArgsConstructor
public class WfeyProductBsFeeService {

    private final WfeyProductBsFeeMapper mapper;
    private final WfeyProductBsFeeConverter converter;

    /**
     * 조회
     * @param req
     * @return
     */
    public List<SearchProductBsFeeRes> getProductBsFeeList(SearchProductBsFeeReq req) {
        return mapper.selectProductBsFeeList(req);
    }
    public PagingResult<SearchProductBsFeeRes> getProductBsFeeList(SearchProductBsFeeReq req, PageInfo pageInfo) {
        return mapper.selectProductBsFeeList(req, pageInfo);
    }

    /**
     * 저장
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveProductBsFee(List<SaveProductBsFeeReq> req) throws Exception {
        int processCount = 0;
        for (SaveProductBsFeeReq dto : req) {
            WfeyProductBsFeeDvo dvo = converter.mapSaveReqWfeyProductBsFeeDvo(dto);
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int validCount = mapper.selectValidProductBsFee(dvo);
                    BizAssert.isTrue(validCount == 0, "MSG_ALT_DUPLICATE_EXISTS");
                    processCount += mapper.insertProductBsFee(dvo);
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateProductBsFee(dvo);
                }
                case CommConst.ROW_STATE_DELETED -> {
                    processCount += mapper.deleteProductBsFee(dvo);
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }
        return processCount;
    }

}
