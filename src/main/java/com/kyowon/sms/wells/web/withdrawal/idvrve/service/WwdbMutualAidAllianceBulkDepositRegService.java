package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCorporationDepositDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbCorporationDepositMapper;
import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbMutualAidAllianceBulkDepositRegConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbMutualAidAllianceBulkDepositRegDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbMutualAidAllianceBulkDepositRegMapper;
import com.kyowon.sms.wells.web.withdrawal.idvrve.util.WithdrawalExcelUtil;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 상조제휴 일괄입금 등록 Service
 * </pre>
 *
 * @author jaeha.yeon
 * @since 2023-10-20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbMutualAidAllianceBulkDepositRegService {
    private final WwdbMutualAidAllianceBulkDepositRegMapper mapper;

    private final WwdbMutualAidAllianceBulkDepositRegConverter convert;

    private final ExcelReadService excelReadService;

    private final MessageResourceService messageResourceService;

    private final BatchCallService batchCallService;

    private final ZwdbCorporationDepositMapper corporationDepositMapper;

    private static final String MSG_ALT_INVALID_UPLOAD_DATA = "MSG_ALT_INVALID_UPLOAD_DATA";
    private static final String WELS_CNTR_NO = "welsCntrNo"; // 고객코드
    private static final String LIF_ALNC_PD_NM = "lifAlncPdNm"; // 상품명
    private static final String LIF_CNTR_NO = "lifCntrNo"; // 지원금액
    private static final String LIF_SPPT_AMT = "lifSpptAmt"; // 상조계약번호

    /**
     * 상조제휴 일괄입금 등록 목록 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    @Transactional
    public PagingResult<SearchRes> getMutualAidAllianceBulkDepositRegPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectMutualAidAllianceBulkDepositRegs(dto, pageInfo);
    }

    /**
     * 상조제휴 일괄입금 등록 목록 엑셀다운로드
     * @param dto
     * @return
     */
    @Transactional
    public List<SearchRes> getMutualAidAllianceBulkDepositRegExcels(SearchReq dto) {
        return mapper.selectMutualAidAllianceBulkDepositRegs(dto);
    }

    /**
     * 상조제휴 일괄입금 등록 입금내역 조회
     * @param dto
     * @return
     */
    @Transactional
    public SearchSumRes getMutualAidAllianceBulkDepositRegsSum(SearchSumReq dto) {
        return mapper.selectMutualAidAllianceBulkDepositRegsSum(dto);
    }

    /**
     * 상조제휴 일괄입금 등록 엑셀 업로드
     * @param lifAlncDvCd
     * @param lifSpptYm
     * @param file
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveMutualAidAllianceBulkDepositRegExcelUpload(String lifAlncDvCd, String lifSpptYm, MultipartFile file)
        throws Exception {

        int processCount = 0;

        Map<String, String> headerTitle = setLifAlncDvCdHeader(lifAlncDvCd);

        ExcelMetaDvo meta = new ExcelMetaDvo(7, headerTitle);

        List<WwdbMutualAidAllianceBulkDepositRegDvo> readExcel = excelReadService
            .readExcel(file, meta, WwdbMutualAidAllianceBulkDepositRegDvo.class);

        List<WwdbMutualAidAllianceBulkDepositRegDvo> dataDvo = new ArrayList<>();

        for (WwdbMutualAidAllianceBulkDepositRegDvo dvo : readExcel) {
            if (!ObjectUtils.isEmpty(dvo.getLifCntrNo())) {
                //                dvo.setLifSpptYm(WithdrawalExcelUtil.formatObjToString(dvo.getLifSpptYm())); /* 지원년월 */
                dvo.setWelsCntrNo(WithdrawalExcelUtil.formatObjToString(dvo.getWelsCntrNo())); /*웰스계약번호*/
                //                dvo.setWelsCntrSn(WithdrawalExcelUtil.formatObjToString(dvo.getWelsCntrSn())); /*웰스계약일련번호*/
                dvo.setLifAlncPdCd(WithdrawalExcelUtil.formatObjToString(dvo.getLifAlncPdCd())); /*상품*/
                dvo.setLifAlncPdNm(WithdrawalExcelUtil.formatObjToString(dvo.getLifAlncPdNm())); /*상품명*/
                dvo.setLifSpptAmt(WithdrawalExcelUtil.formatObjToString(dvo.getLifSpptAmt())); /*지원금액*/
                dvo.setLifSpptAggAmt(WithdrawalExcelUtil.formatObjToString(dvo.getLifSpptAggAmt())); /*라이프지원누계금액*/
                dvo.setLifRepAmt(WithdrawalExcelUtil.formatObjToString(dvo.getLifRepAmt())); /*라이프환수금액*/
                dvo.setLifCntrNo(WithdrawalExcelUtil.formatObjToString(dvo.getLifCntrNo())); /*상조계약번호*/
                //                dvo.setLifCntrSn(WithdrawalExcelUtil.formatObjToString(dvo.getLifCntrSn())); /*상조계약번호*/
                dataDvo.add(dvo);
            }
        }

        /* Validation */
        validateExcelDatas(headerTitle, dataDvo);

        for (WwdbMutualAidAllianceBulkDepositRegDvo dvo : dataDvo) {
            if (!ObjectUtils.isEmpty(dvo.getLifCntrNo())) {
                dvo.setWelsCntrNo("W" + dvo.getWelsCntrNo().toString().replace("-", ""));
                dvo.setLifCntrNo(dvo.getLifCntrNo().toString().replace("-", ""));
                dvo.setLifSpptYm(lifSpptYm); //지원년월
                dvo.setLifAlncDvCd(lifAlncDvCd); //제휴코드
                dvo.setLifCntrSn("1");
                dvo.setWelsCntrSn("1");
                processCount += mapper.insertMutualAidAllianceBulkDepositReg(dvo);
            }
        }

        return processCount;

    }

    /**
     * 상조제휴 일괄입금 등록 엑셀 업로드 Header
     * @param lifAlncDvCd
     * @return
     */
    public Map<String, String> setLifAlncDvCdHeader(String lifAlncDvCd) {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        if (lifAlncDvCd.equals("30")) { //웰스399
            headerTitle.put("sn", messageResourceService.getMessage("MSG_TXT_SN")); // 순번
            headerTitle.put(WELS_CNTR_NO, messageResourceService.getMessage("MSG_TXT_CST_CD"));//고객코드
            headerTitle.put("test1", messageResourceService.getMessage("MSG_TXT_CUST_STMT")); // 고객성명
            headerTitle.put("lifAlncPdCd", messageResourceService.getMessage("MSG_TXT_PRDT"));// 상품
            headerTitle.put(LIF_ALNC_PD_NM, messageResourceService.getMessage("MSG_TXT_PRDT_NM"));// 상품명
            headerTitle.put("test7", messageResourceService.getMessage("MSG_TXT_CNTR_DATE")); //계약일자
            headerTitle.put("test8", messageResourceService.getMessage("MSG_TXT_RSG_DT")); //해지일자
            headerTitle.put("test2", messageResourceService.getMessage("MSG_TXT_EV_DT")); //행사일자
            headerTitle.put("test3", messageResourceService.getMessage("MSG_TXT_MPY"));// 납부
            headerTitle.put("test4", messageResourceService.getMessage("MSG_TXT_AGGS"));// 누계
            headerTitle.put("test5", messageResourceService.getMessage("MSG_TXT_INSLM_AMT"));// 부금금액
            headerTitle.put("test6", messageResourceService.getMessage("MSG_TXT_INSLM_AGG"));// 부금누계
            headerTitle.put(LIF_SPPT_AMT, messageResourceService.getMessage("MSG_TXT_SPPT_AMT"));// 지원금액
            headerTitle.put("lifSpptAggAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AGG"));// 라이프지원누계금액
            headerTitle.put("lifRepAmt", messageResourceService.getMessage("MSG_TXT_REP_AMT")); //라이프환수금액
            headerTitle.put(LIF_CNTR_NO, messageResourceService.getMessage("MSG_TXT_MUTU_MB_CD"));// 상조계약번호
            headerTitle.put("dummy", messageResourceService.getMessage(""));// 더미데이터
            headerTitle.put("dummy1", messageResourceService.getMessage(""));// 더미데이터
            headerTitle.put("dummy2", messageResourceService.getMessage(""));// 더미데이터
            headerTitle.put("dummy3", messageResourceService.getMessage(""));// 더미데이터
        } else {
            headerTitle.put("sn", messageResourceService.getMessage("MSG_TXT_SPPT_YM")); /* 순번 */
            headerTitle.put(LIF_ALNC_PD_NM, messageResourceService.getMessage("MSG_TXT_PRDT_NM")); /*상품명*/
            headerTitle.put(LIF_CNTR_NO, messageResourceService.getMessage("MSG_TXT_MUTU_CNTR_NO")); /*상조계약번호*/
            headerTitle.put("test1", messageResourceService.getMessage("MSG_TXT_CUST_STMT")); /* 고객성명 */
            headerTitle.put("test7", messageResourceService.getMessage("MSG_TXT_CNTR_DATE")); /* 계약일자 */
            headerTitle.put("test8", messageResourceService.getMessage("MSG_TXT_RSG_DT")); /* 해지일자 */
            headerTitle.put("test2", messageResourceService.getMessage("MSG_TXT_EV_DT")); /*행사일자*/
            headerTitle.put("test3", messageResourceService.getMessage("MSG_TXT_MPY")); /*납부*/
            headerTitle.put("test4", messageResourceService.getMessage("MSG_TXT_AGGS")); /*누계*/
            headerTitle.put("test5", messageResourceService.getMessage("MSG_TXT_INSLM_AMT")); /*부금금액*/
            headerTitle.put("test6", messageResourceService.getMessage("MSG_TXT_INSLM_AGG")); /*부금누계*/
            headerTitle.put(LIF_SPPT_AMT, messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); /*지원금액*/
            headerTitle.put("lifSpptAggAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AGG")); /*라이프지원누계금액*/
            headerTitle.put("lifRepAmt", messageResourceService.getMessage("MSG_TXT_REP_AMT")); /*라이프환수금액*/
            headerTitle.put(WELS_CNTR_NO, messageResourceService.getMessage("MSG_TXT_WELS_MB_CD"));/*웰스회원코드*/
            headerTitle.put("dummy", messageResourceService.getMessage(""));// 더미데이터
            headerTitle.put("dummy1", messageResourceService.getMessage(""));// 더미데이터
            headerTitle.put("dummy2", messageResourceService.getMessage(""));// 더미데이터
            headerTitle.put("dummy3", messageResourceService.getMessage(""));// 더미데이터
        }

        return headerTitle;
    }

    /**
     * 엑셀업로드 유효성 체크
     * @param header
     * @param dvos
     */
    public void validateExcelDatas(
        Map<String, String> header, List<WwdbMutualAidAllianceBulkDepositRegDvo> dvos
    ) {

        int row = 1;

        for (int i = 0; i < dvos.size(); i++) {
            WwdbMutualAidAllianceBulkDepositRegDvo dvo = dvos.get(i);
            /* 데이터 검증 */
            if (StringUtil.isBlank(dvo.getWelsCntrNo().toString())) { //웰스계약번호

                BizAssert.hasText(
                    dvo.getWelsCntrNo().toString(), MSG_ALT_INVALID_UPLOAD_DATA,
                    new String[] {String.valueOf(row), header.get(WELS_CNTR_NO), dvo.getWelsCntrNo().toString()}
                );

            }
            if (StringUtil.isBlank(dvo.getLifAlncPdNm().toString())) { //상품명
                BizAssert.hasText(
                    dvo.getLifAlncPdNm().toString(), MSG_ALT_INVALID_UPLOAD_DATA,
                    new String[] {String.valueOf(row), header.get(LIF_ALNC_PD_NM), dvo.getLifAlncPdNm().toString()}
                );
            }

            if (StringUtil.isBlank(dvo.getLifCntrNo().toString())) { //상조계약번호
                BizAssert.hasText(
                    dvo.getLifCntrNo().toString(), MSG_ALT_INVALID_UPLOAD_DATA,
                    new String[] {String.valueOf(row), header.get(LIF_CNTR_NO), dvo.getLifCntrNo().toString()}
                );
            }

            if (StringUtil.isBlank(dvo.getLifSpptAmt().toString())) { //지원금액
                BizAssert.hasText(
                    dvo.getLifSpptAmt().toString(), MSG_ALT_INVALID_UPLOAD_DATA,
                    new String[] {String.valueOf(row), header.get(LIF_SPPT_AMT), dvo.getLifSpptAmt().toString()}
                );
            }
        }
    }

    /**
     * 상조제휴 일괄입금 등록 생성
     * @param dtos
     * @return
     */
    @Transactional
    public int saveMutualAidAllianceBulkDepositReg(List<SaveUploadReq> dtos) {
        int processCount = 0;

        for (SaveUploadReq dto : dtos) {
            WwdbMutualAidAllianceBulkDepositRegDvo dvo = convert.mapSaveWwdbMutualAidAllianceBulkDepositRegDvo(dto);
            processCount += mapper.insertMutualAidAllianceBulkDepositReg(dvo);
        }
        return processCount;
    }

    /**
     * 상조제휴 일괄입금 등록 생성
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveMutualAidAllianceBulkDepositRegs(SaveReq dto) throws Exception {
        int processCount = 0;

        //통합입금 조회
        ZwdbCorporationDepositDto.SearchIntegrationDepositRes searchIntegrationDepositRes = corporationDepositMapper
            .selectIntegrationDepositInfo(dto.itgDpNo());

        //통합입금 조회 결과가 없을경우
        BizAssert.hasText(searchIntegrationDepositRes.itgDpNo(), "MSG_ALT_ITG_DP_DTA_NOT_EXST"); // 통합입금 데이터가 존재하지 않습니다. [통합입금번호 오류]

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYm = sysDate.substring(0, 6);
        String sysDateYmd = sysDate.substring(0, 8);
        //한달전
        String prevSysDate = DateUtil.addMonths(sysDateYmd, -1);
        String prevSysDateYm = prevSysDate.substring(0, 6);

        //한달전 날짜와 라이프지원년월이 다르면 예외 발생
        if (!prevSysDateYm.contentEquals(dto.lifSpptYm())) {
            throw new BizException("MSG_ALT_MUTU_DP_CRT_LSTMM_PSB"); // 상조입금생성은 전월만 가능합니다.
        }

        int diffDay = DateUtil.getDays(sysDateYmd, dto.rveDt());

        //이전만 가능하기에 0보다 크면 예외 발생
        BizAssert.isFalse(diffDay > 0, "MSG_ALT_RVE_DT_CRTL_D_BF_PSB"); // 수납일자는 현재일 과 이전만 가능 합니다.

        //수납일자가 같은 월이 아니면 예외 발생
        if (!sysDateYm.contentEquals(dto.rveDt().substring(0, 6))) {
            throw new BizException("MSG_ALT_RVE_DT_CRTL_PSB"); // 수납일자 는 현재월 만 가능합니다.
        }

        diffDay = DateUtil.getDays(sysDateYmd, dto.perfDt());

        //오늘 날짜와 실적일자사이가 0보다 클 경우 예외 발생
        BizAssert.isFalse(diffDay > 0, "MSG_ALT_PERF_DT_CRTL_D_BF_PSB"); // 실적일자는 현재일 과 이전만 가능 합니다.

        if (!sysDateYm.contentEquals(dto.perfDt().substring(0, 6))) {
            throw new BizException("MSG_ALT_PERF_DT_CRTL_PSB");
        }

        //수납마감 체크 테이블 여쭤보기
        SearchReq chkDto = new SearchReq(dto.lifSpptYm(), dto.lifAlncDvCd());

        List<SearchRes> selectMutualAidAllianceBulkDepositRegs = mapper.selectMutualAidAllianceBulkDepositRegs(chkDto);

        BizAssert.notEmpty(selectMutualAidAllianceBulkDepositRegs, "MSG_ALT_BLK_DP_RGST_CRT_DTA_EXST");

        if (!prevSysDateYm.contentEquals(selectMutualAidAllianceBulkDepositRegs.get(0).lifSpptYm())) {
            throw new BizException("MSG_ALT_MUTU_DP_CRT_LSTMM_PSB"); //상조입금생성은 전월만 가능합니다.
        }

        long dpBlam = Long.parseLong(searchIntegrationDepositRes.dpBlam());
        long sumAmt = selectMutualAidAllianceBulkDepositRegs.get(0).sumAmt();

        BizAssert.isFalse(dpBlam == 0, "MSG_ALT_CPRCNF_NOT_DP_BLAM"); // 대사 할 입금잔액이 없습니다. 입금잔액을 확인하세요.

        BizAssert.isFalse(dpBlam != sumAmt, "MSG_ALT_CPRCNF_NOT_DP_BLAM"); // 통합 입금잔액 과 총 대사금액 이 일치하지 않습니다.

        BatchCallReqDvo batchDvo = new BatchCallReqDvo();

        batchDvo.setJobKey("SMS_WD_OZ0003");
        final Map<String, String> params = new HashMap<>();

        params.put("itgDpNo", dto.itgDpNo());
        params.put("param1", dto.lifSpptYm());
        params.put("param2", dto.lifAlncDvCd());
        params.put("rveDt", dto.rveDt());
        params.put("perfDt", dto.perfDt());

        batchDvo.setParams(params);
        String resultJobId = batchCallService.runJob(batchDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(resultJobId), "MSG_ALT_SVE_ERR");
        return processCount;
    }
}
