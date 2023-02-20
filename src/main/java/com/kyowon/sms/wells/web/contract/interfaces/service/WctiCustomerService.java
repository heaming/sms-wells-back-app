package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto.SearchRes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiCustomerMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiCustomerService {

    private final WctiCustomerMapper mapper;

    public List<SearchRes> getCustomers(SearchReq req) {
        // 파라미터 체크 - 파라미터값이 NULL 인 경우, 조회 하지 않음
        if (StringUtils.isEmpty(req.CST_NM())
            && StringUtils.isEmpty(req.CST_NO())
            && StringUtils.isEmpty(req.CRAL_LOCARA_TNO())
            && StringUtils.isEmpty(req.MEXNO())
            && StringUtils.isEmpty(req.CRAL_IDV_TNO())
            && StringUtils.isEmpty(req.LOCARA_TNO())
            && StringUtils.isEmpty(req.EXNO())
            && StringUtils.isEmpty(req.IDV_TNO())) {

            throw new BizException("검색어를 입력해주세요.");
        }

        // 파라미터 체크 - 연락처 검색어가 2개 이상 아닐 경우, 조회 하지 않음
        int emptyCnt = 0;
        if (StringUtils.isEmpty(req.CRAL_LOCARA_TNO())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(req.MEXNO())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(req.CRAL_IDV_TNO())) {
            ++emptyCnt;
        }

        if (emptyCnt == 2) {
            throw new BizException("휴대전화번호를 2개 이상 입력해주세요.");
        }

        emptyCnt = 0;
        if (StringUtils.isEmpty(req.LOCARA_TNO())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(req.EXNO())) {
            ++emptyCnt;
        }
        if (StringUtils.isEmpty(req.IDV_TNO())) {
            ++emptyCnt;
        }

        if (emptyCnt == 2) {
            throw new BizException("전화번호를 2개 이상 입력해주세요.");
        }

        //고객 검색
        List<SearchRes> resList = new ArrayList<>();

        resList.addAll(mapper.selectCustomers(req));
        resList.addAll(mapper.selectProspactCustomers(req));

        return resList;
    }
}
