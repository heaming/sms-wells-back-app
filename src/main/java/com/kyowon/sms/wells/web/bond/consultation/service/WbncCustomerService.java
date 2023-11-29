package com.kyowon.sms.wells.web.bond.consultation.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.bond.consultation.converter.WbncCustomerConverter;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindBaseYmRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselRegistrationReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselRegistrationRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindRecIdReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindUnusualArticlesReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindUnusualArticlesRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveCounselReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveUnuitmCnReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchUserInfoReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchUserInfoRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncCustomerDvo;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncPetitionCreateDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncCustomerMapper;
import com.sds.sflex.system.config.core.service.UserService;
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
    private final UserService user;

    /**
     * 기준년월 조회
     * @param dto 검색 조건
     * @return 기준년월 정보조회 결과
     */
    public FindBaseYmRes getBaseYm() {
        return mapper.selectBaseYm();
    }

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
    public FindRes getCustomerDetail(FindReq dto) {
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
     * 고객상세 특이사항 조회
     * @param dto 검색 조건
     * @return 고객상세 특이사항 정보조회 결과
     */
    public FindUnusualArticlesRes getUnusualArticles(FindUnusualArticlesReq dto) {
        return mapper.selectUnusualArticles(dto);
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
        WbncCustomerDvo dvo = converter.mapSaveReqToWbncUnuitmCnDvo(dto);
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
        WbncCustomerDvo dvo = converter.mapSaveReqToWbncCounselDvo(dto);

        if (user.getUserCustomParam() != null) {
            dvo.setRdgId(user.getUserCustomParam().get("recId").toString());
        }

        processCount = mapper.insertCounsel(dvo);
        BizAssert.isTrue(processCount > 0, "MSG_ALT_SVE_ERR");

        processCount = mapper.insertPromise(dvo);
        BizAssert.isTrue(processCount > 0, "MSG_ALT_SVE_ERR");

        if (user.getUserCustomParam() != null) {
            user.setUserCustomParam(null);
        }

        return processCount;
    }

    public List<SearchUserInfoRes> getUserInfo(SearchUserInfoReq dto) throws Exception {
        return mapper.selectUserInfo(dto);
    }

    public String getRecId(FindRecIdReq dto) {
        String result = "";
        try {
            String ext = dto.inlnNo();
            String url = "http://10.1.73.14:8088?type=get&cmd=callkeyap&ext=" + ext; // 운영

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)obj.openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            // 연결 타임아웃 설정 
            conn.setConnectTimeout(30000); // 10초 

            // 읽기 타임아웃 설정 
            conn.setReadTimeout(30000); // 10초

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            result = response.toString();

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("recId", result);

            user.setUserCustomParam(paramMap);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;

    }

    /**
     * 고객상세 상담등록 조회
     * @param dto 검색 조건
     * @return 고객상세 상담등록 정보조회 결과
     */
    public FindCounselRegistrationRes getCounselRegistration(FindCounselRegistrationReq dto) {
        return mapper.selectCounselRegistration(dto);
    }

    /**
     * 소장생성 체크
     * @param cntrDtlNo
     * @return 소장생성 체크
     */

    public int getPetitionCreateCheck(String cntrDtlNo, String baseYm) {
        int returnCount = 0;

        WbncPetitionCreateDvo petitionDvo = mapper.selectPetitionType(cntrDtlNo, baseYm);

        if (petitionDvo != null) {
            if ("L20".equals(petitionDvo.getBndBizDvCd()) && "02".equals(petitionDvo.getBndLwsBilDvCd())
                && "@".equals(petitionDvo.getBndBilPpsDvCd()) && !"302".equals(petitionDvo.getCntrDtlStatCd())
                && !"303".equals(petitionDvo.getCntrDtlStatCd())) {
                returnCount = mapper.selectPetitionType1(cntrDtlNo);
            }

            if ("L21".equals(petitionDvo.getBndBizDvCd()) && "02".equals(petitionDvo.getBndLwsBilDvCd())
                && "@".equals(petitionDvo.getBndBilPpsDvCd()) && "N".equals(petitionDvo.getIstmYn())
                && "N".equals(petitionDvo.getDscYn()) && !"302".equals(petitionDvo.getCntrDtlStatCd())
                && !"303".equals(petitionDvo.getCntrDtlStatCd())) {
                returnCount = mapper.selectPetitionType2(cntrDtlNo);
            }

            if ("L21".equals(petitionDvo.getBndBizDvCd()) && "02".equals(petitionDvo.getBndLwsBilDvCd())
                && "@".equals(petitionDvo.getBndBilPpsDvCd()) && "Y".equals(petitionDvo.getIstmYn())
                && "Y".equals(petitionDvo.getDscYn()) && !"302".equals(petitionDvo.getCntrDtlStatCd())
                && !"303".equals(petitionDvo.getCntrDtlStatCd())) {
                returnCount = mapper.selectPetitionType3(cntrDtlNo);
            }

            if ("L10".equals(petitionDvo.getBndBizDvCd()) && "02".equals(petitionDvo.getBndLwsBilDvCd())
                && "@".equals(petitionDvo.getBndBilPpsDvCd()) && "Y".equals(petitionDvo.getIstmYn())
                && "N".equals(petitionDvo.getDscYn()) && !"302".equals(petitionDvo.getCntrDtlStatCd())
                && !"303".equals(petitionDvo.getCntrDtlStatCd())) {
                returnCount = mapper.selectPetitionType4(cntrDtlNo);
            }

            if (("L30".equals(petitionDvo.getBndBizDvCd()) || "L60".equals(petitionDvo.getBndBizDvCd()))
                && "02".equals(petitionDvo.getBndLwsBilDvCd()) && "@".equals(petitionDvo.getBndBilPpsDvCd())
                && "N".equals(petitionDvo.getIstmYn()) && "N".equals(petitionDvo.getDscYn())
                && !"302".equals(petitionDvo.getCntrDtlStatCd()) && !"303".equals(petitionDvo.getCntrDtlStatCd())) {
                returnCount = mapper.selectPetitionType5(cntrDtlNo);
            }

            if (("L20".equals(petitionDvo.getBndBizDvCd()) || "L21".equals(petitionDvo.getBndBizDvCd())
                || "L60".equals(petitionDvo.getBndBizDvCd())) && "02".equals(petitionDvo.getBndLwsBilDvCd())
                && "@".equals(petitionDvo.getBndBilPpsDvCd()) && "303".equals(petitionDvo.getCntrDtlStatCd())) {
                returnCount = mapper.selectPetitionType6(cntrDtlNo);
            }

            if ("L20".equals(petitionDvo.getBndBizDvCd()) && "02".equals(petitionDvo.getBndLwsBilDvCd())
                && "@".equals(petitionDvo.getBndBilPpsDvCd()) && "302".equals(petitionDvo.getCntrDtlStatCd())) {
                returnCount = mapper.selectPetitionType7(cntrDtlNo);
            }
        }
        return returnCount;
    }

}
