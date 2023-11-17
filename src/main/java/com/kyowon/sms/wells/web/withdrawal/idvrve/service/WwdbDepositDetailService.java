package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbDepositDetailConvert;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositDetailSearchDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositDetailMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 입금내역조회(웰스입금상세) 서비스
 * </pre>
 *
 * @author ShinSoJeong
 * @since 2023-06-16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbDepositDetailService {

    private final WwdbDepositDetailMapper mapper;

    private final WwdbDepositDetailConvert convert;

    /**
     * 입금내역 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @Transactional
    public PagingResult<SearchRes> getDepositDetailPages(SearchReq dto, PageInfo pageInfo) {
        WwdbDepositDetailSearchDvo dvo = convert.mapWwdbDepositDetailSearchDvo(dto);
        if (!StringUtil.isEmpty(dto.ogTpCd())) {
            String[] ogTpCdList = dto.ogTpCd().split(",");
            dvo.setOgTpCdList(ogTpCdList);
        }
        return mapper.selectDepositDetail(dvo, pageInfo);
    }

    /**
     * 입금내역 엑셀다운로드
     * @param dto
     * @return List<SearchRes>
     */
    @Transactional
    public List<SearchRes> getDepositDetailExcels(SearchReq dto) {
        WwdbDepositDetailSearchDvo dvo = convert.mapWwdbDepositDetailSearchDvo(dto);
        if (!StringUtil.isEmpty(dto.ogTpCd())) {
            String[] ogTpCdList = dto.ogTpCd().split(",");
            dvo.setOgTpCdList(ogTpCdList);
        }
        return mapper.selectDepositDetail(dvo);
    }

}
