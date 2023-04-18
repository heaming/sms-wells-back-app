package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.bond.consultation.converter.WbncCustomerConverter;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveCounselReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveUnuitmCnReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncCustomerDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncCustomerMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 채권상담 고객리스트
 * </pre>
 *
 * @author sunghun.choi
 * @since 2023-02-10
 */
@Service
@RequiredArgsConstructor
public class WbncCustomerService {

    private final WbncCustomerMapper mapper;
    private final WbncCustomerConverter converter;

    /**
     * 채권상담 고객리스트 조회
     * @param dto 검색 조건
     * @return 채권상담 고객리스트 조회 결과
     */
    public List<SearchRes> getCustomers(SearchReq dto) throws Exception {
        return mapper.selectCustomers(dto);
    }

    /**
     * 채권상담 고객상세 조회
     * @param dto 검색 조건
     * @return 채권상담 고객상세 정보조회 결과
     */
    public Optional<FindRes> getCustomerDetail(FindReq dto) {
        return mapper.selectCustomerDetail(dto);
    }

    /**
     * 채권상담 고객상세 조회
     * @param dto 검색 조건
     * @return 채권상담 고객상세 목록조회 결과
     */
    public List<FindCustomerDetailRes> getCustomerDetails(FindCustomerDetailReq dto) throws Exception {
        return mapper.selectCustomerDetails(dto);
    }

    /**
     * 채권상담 고객상세 상담이력 조회
     * @param dto 검색 조건
     * @return 채권상담 고객상세 상담이력 조회 결과
     */
    public List<FindCounselHistoryRes> getCounselHistorys(FindCounselHistoryReq dto) throws Exception {
        return mapper.selectCounselHistorys(dto);
    }

    /**
     * 고객상세 특이상항 내용 저장
     * @param SaveReq 저장 조건 정보
     * @return 저장 결과
     */
    @Transactional
    public int saveUnuitmCn(SaveUnuitmCnReq dto) throws Exception {
        int processCount = 0;
        WbncCustomerDvo dvo = converter.mapSaveReqToEbncUnuitmCnDvo(dto);
        processCount = mapper.mergeUnuitmCn(dvo);
        BizAssert.isTrue(processCount > 0, "MSG_ALT_SVE_ERR");

        return processCount;
    }

    /**
     * 고객상세 상담/약속 내용 저장
     * @param SaveReq 저장 조건 정보
     * @return 저장 결과
     */
    @Transactional
    public int saveCounsel(SaveCounselReq dto) throws Exception {
        int processCount = 0;
        WbncCustomerDvo dvo = converter.mapSaveReqToEbncCounselDvo(dto);
        processCount = mapper.insertCounsel(dvo);

        processCount = mapper.insertPromise(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_SVE_ERR");

        return processCount;
    }
}
