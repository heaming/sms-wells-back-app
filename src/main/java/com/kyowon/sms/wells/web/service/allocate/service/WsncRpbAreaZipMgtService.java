package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncRpbAreaZipMgtConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.District;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaZipNoDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRpbAreaZipMgtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0036M01 책임지역 우편번호 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.11.17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncRpbAreaZipMgtService {

    private final WsncRpbAreaZipMgtMapper mapper;

    private final WsncRpbAreaZipMgtConverter converter;

    /**
     * 책임지역 우편번호 관리 - 조회
     *
     * @param dto : { zipFrom: 우편번호From, zipTo: 우편번호To, ctpvNm: 시도명, ctctyNm: 시군구명, wkGrpCd: 작업그룹코드, applyDate: 적용일자 }
     * @return
     */
    public List<SearchRes> getZipNos(SearchReq dto) {
        return this.mapper.selectZipNos(dto);
    }

    /**
     * 책임지역 법정동 행정동 리스트 조회
     *
     * @return
     */
    public List<District> getDistricts() {
        return this.mapper.selectDistricts();
    }

    /**
     * 책임지역 우편번호 관리 - 저장
     *
     * @param dtos : [{ newAdrZip: 신주소우편번호, emdSn: 읍면동일련번호, fr2pLgldCd: 앞2자리법정동코드, lawcEmdNm: 법정읍면동명, amtdNm: 행정동명, kynorLocaraYn: 경북지역여부, dtaDlYn: 데이터삭제여부, ctpvNm: 시도명, ctctyNm: 시군구명, ildYn: 섬여부, pdlvNo: 출고지번호 }]
     * @return
     * @throws Exception
     */
    @Transactional
    public int createZip(List<CreateReq> dtos) throws Exception {
        int processCount = 0;

        for (CreateReq dto : dtos) {
            WsncRpbAreaZipNoDvo rpbLocaraZip = this.converter.mapCreateReqToWsncRpbAreaZipNoDvo(dto);
            this.mapper.deleteZipNo(rpbLocaraZip);
            processCount += this.mapper.insertZipNo(rpbLocaraZip);
        }

        return processCount;
    }

}
