package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKakaotalksDto.SearchKakaotalkFwIzsReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaKakaotalksDto.SearchKakaotalkFwIzsRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaKakaotalksMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaKakaotalksService {

    private final WctaKakaotalksMapper mapper;

    public List<SearchKakaotalkFwIzsRes> getKakaotalkFwIzs(SearchKakaotalkFwIzsReq dto) {
        List<String> tempCodeList = new ArrayList<String>();
        if (dto.concDiv().equals("R")) {
            tempCodeList.add("wells51865"); // 구 템플릿
            tempCodeList.add("wells17945"); // 신 템플릿
            tempCodeList.add("Wells18182"); // 템플릿 테스트 데이터
        } else {//if(StringUtil.isEquals("N", concDiv) || StringUtil.isEquals("U", concDiv) || StringUtil.isEquals("A", concDiv)){
            tempCodeList.add("Wells5ECC2"); // 구 계약 템플릿
            tempCodeList.add("wells17904"); // 구 템플릿(계약)
            tempCodeList.add("wells17947"); // 신 템플릿(계약)
            tempCodeList.add("Wells18104"); // 신 템플릿(계약) - 2021.06.08 (공유렌탈 소개 페이지 URL 링크 추가)
            tempCodeList.add("Wells18157"); // 신 템플릿(계약) - 2021.10.20 (IoT 안내 URL 링크 추가)
            tempCodeList.add("wells51866"); // 구 템플릿(모종일시불)
            tempCodeList.add("wells17944"); // 신 템플릿(모종일시불)
            tempCodeList.add("wells17905"); // 구 템플릿(설치자용)
            tempCodeList.add("wells17948"); // 신 템플릿(설치자용)
            tempCodeList.add("Wells18183"); // 건강케어 서비스 알림톡
            tempCodeList.add("Wells18188"); // 건강케어 서비스 알림톡(수정)
            tempCodeList.add("Wells18206"); // 피버 바이크 플러스 온라인 강의 무료 구독 신청
            tempCodeList.add("Wells18202"); // 장기 할부 안내 문구 추가 템플릿(iot)
            tempCodeList.add("Wells18203"); // 장기 할부 안내 문구 추가 템플릿(일반)
        }

        return mapper.SelectKakaotalkFwIzs(dto.mtPr(), tempCodeList);
    }
}
