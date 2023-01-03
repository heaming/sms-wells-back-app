package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRpbLocaraPsicMngtConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraPsicMngtDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraPsicMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraPsicMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbLocaraPsicDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRpbLocaraPsicMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0017M01 책임지역 담당자 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.22
 */
@Service
@RequiredArgsConstructor
public class WsncRpbLocaraPsicMngtService {

    private final WsncRpbLocaraPsicMngtMapper mapper;

    private final WsncRpbLocaraPsicMngtConverter converter;

    /**
     * 책임지역 담당자 관리 - 조회 (페이징)
     *
     * @param dto : { zipFrom: 우편번호 From, zipTo: 우편번호 To, ctpvNm: 시도명, ctctyNm: 시군구명, ogId: 서비스센터(조직ID), wkGrpCd: 작업그룹코드, applyDate: 적용일자, rpbLocaraCdFrom: 지역코드 From, rpbLocaraCdTo: 지역코드 To }
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getPersonInChargePages(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectPersonInCharges(dto, pageInfo);
    }

    /**
     * 책임지역 담당자 관리 - 조회 (엑셀 다운로드)
     *
     * @param dto : { zipFrom: 우편번호 From, zipTo: 우편번호 To, ctpvNm: 시도명, ctctyNm: 시군구명, ogId: 서비스센터(조직ID), wkGrpCd: 작업그룹코드, applyDate: 적용일자, rpbLocaraCdFrom: 지역코드 From, rpbLocaraCdTo: 지역코드 To }
     * @return
     */
    public List<SearchRes> getPersonInChargesForExcelDownload(SearchReq dto) {
        return this.mapper.selectPersonInCharges(dto);
    }

    /**
     * 책임지역 담당자 관리 - 저장
     *
     * @param dtos : [{ wkGrpCd: 작업그룹코드, rpbLocaraCd: 책임지역코드, izSn: 내역일련번호, ichrPrtnrNo: 담당파트너번호, pprnIchrPrtnrNo1: 예비담당파트너번호1, pprnIchrPrtnrNo2: 예비담당파트너번호2, pprnIchrPrtnrNo3: 예비담당파트너번호3, pprnIchrPrtnrNo4: 예비담당파트너번호4, pprnIchrPrtnrNo5: 예비담당파트너번호5, vstDowVal: 방문요일값, mmtAvLdtm: 이동평균소요시간, rstrCndtUseYn: 제약조건사용여부, udsnUseYn: 미지정사용여부, locaraCenStruAdr: 지역중심건물주소, w1W3SatWrkYn: 1주3주토요일근무여부, rpbLocaraGrpCd: 책임지역그룹코드, apyStrtdt: 적용시작일자, apyEnddt: 적용종료일자 }]
     * @return
     * @throws Exception
     */
    public int createPersonInCharge(List<WsncRpbLocaraPsicMngtDto.CreateReq> dtos) throws Exception {
        int processCount = 0;

        for (WsncRpbLocaraPsicMngtDto.CreateReq dto : dtos) {
            WsncRpbLocaraPsicDvo rpbLocaraPsic = this.converter.mapCreateReqToWsncRpbLocaraPsicDvo(dto);
            processCount += this.mapper.insertPersonInCharge(rpbLocaraPsic);
        }

        return processCount;
    }

}
