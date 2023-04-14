package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbMutualAidAllianceBulkDepositRegConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SaveUploadReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchDepositRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbMutualAidAllianceBulkDepositRegDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbIntegrationDepositMapper;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbMutualAidAllianceBulkDepositRegMapper;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbMutualAidAllianceBulkDepositRegService {
    private final WwdbMutualAidAllianceBulkDepositRegMapper mapper;

    private final WwdbMutualAidAllianceBulkDepositRegConverter convert;

    private final WwdbIntegrationDepositMapper depositMapper;

    private final ExcelReadService excelReadService;

    private final MessageResourceService messageResourceService;

    @Transactional
    public PagingResult<SearchRes> getMutualAidAllianceBulkDepositRegPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectMutualAidAllianceBulkDepositRegs(dto, pageInfo);
    }

    @Transactional
    public List<SearchRes> getMutualAidAllianceBulkDepositRegExcels(SearchReq dto) {
        return mapper.selectMutualAidAllianceBulkDepositRegs(dto);
    }

    @Transactional
    public SearchSumRes getMutualAidAllianceBulkDepositRegsSum(SearchSumReq dto) {
        return mapper.selectMutualAidAllianceBulkDepositRegsSum(dto);
    }

    @Transactional
    public int saveMutualAidAllianceBulkDepositRegExcelUpload(String lifAlncDvCd, String lifSpptYm, MultipartFile file)
        throws Exception {

        int processCount = 0;

        //        Map<String, String> headerTitle = setLifAlncDvCdHeader(lifAlncDvCd);
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("test1", messageResourceService.getMessage("MSG_TXT_SPPT_YM")); /* 순번 */
        headerTitle.put("test2", messageResourceService.getMessage("MSG_TXT_PRDT_NM")); /*상품명*/
        headerTitle.put("test3", messageResourceService.getMessage("MSG_TXT_MUTU_CNTR_NO")); /*상조계약번호*/
        headerTitle.put("test4", messageResourceService.getMessage("MSG_TXT_CUST_STMT")); /* 고객성명 */
        headerTitle.put("test5", messageResourceService.getMessage("MSG_TXT_CNTR_DATE")); /* 계약일자 */
        headerTitle.put("test6", messageResourceService.getMessage("MSG_TXT_RSG_DT")); /* 해지일자 */
        headerTitle.put("test7", messageResourceService.getMessage("MSG_TXT_EV_DT")); /*행사일자*/
        headerTitle.put("test8", messageResourceService.getMessage("MSG_TXT_MPY")); /*납부*/
        headerTitle.put("test9", messageResourceService.getMessage("MSG_TXT_AGGS")); /*누계*/
        headerTitle.put("test10", messageResourceService.getMessage("MSG_TXT_INSLM_AMT")); /*부금금액*/
        headerTitle.put("test11", messageResourceService.getMessage("MSG_TXT_INSLM_AGG")); /*부금누계*/
        headerTitle.put("test12", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); /*지원금액*/
        headerTitle.put("test13", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); /*라이프지원누계금액*/
        headerTitle.put("test14", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); /*라이프환수금액*/
        headerTitle.put("test15", messageResourceService.getMessage("MSG_TXT_WELS_MB_CD"));/*웰스회원코드*/
        headerTitle.put("test16", messageResourceService.getMessage("MSG_TXT_WELS_MB_CD"));/*웰스회원코드*/

        ExcelMetaDvo meta = new ExcelMetaDvo(7, headerTitle);

        List<WwdbMutualAidAllianceBulkDepositRegDvo> readExcel = excelReadService
            .readExcel(file, meta, WwdbMutualAidAllianceBulkDepositRegDvo.class);
        ;

        log.info("===========================");
        log.info(readExcel.get(0).toString());
        log.info("===========================");

        //        List<WwdbMutualAidAllianceBulkDepositRegDvo> dataDvo = new ArrayList<WwdbMutualAidAllianceBulkDepositRegDvo>();

        //        for (WwdbMutualAidAllianceBulkDepositRegDvo dvo : dvos) {
        //            log.info("===test");
        //            System.out.println(!ObjectUtils.isEmpty(dvo.getLifCntrNo()));
        //            log.info("===test");
        //            if (!ObjectUtils.isEmpty(dvo.getLifCntrNo())) {
        //                log.info("===test2");
        //                log.info(dvo.toString());
        //                log.info("===test2");
        //                //                dvo.setLifSpptYm(WithdrawalExcelUtil.formatObjToString(dvo.getLifSpptYm())); /* 지원년월 */
        //                dvo.setWelsCntrNo(WithdrawalExcelUtil.formatObjToString(dvo.getWelsCntrNo())); /*웰스계약번호*/
        //                //                dvo.setWelsCntrSn(WithdrawalExcelUtil.formatObjToString(dvo.getWelsCntrSn())); /*웰스계약일련번호*/
        //                dvo.setLifAlncPdCd(WithdrawalExcelUtil.formatObjToString(dvo.getLifAlncPdCd())); /*상품*/
        //                dvo.setLifAlncPdNm(WithdrawalExcelUtil.formatObjToString(dvo.getLifAlncPdNm())); /*상품명*/
        //                dvo.setLifSpptAmt(WithdrawalExcelUtil.formatObjToString(dvo.getLifSpptAmt())); /*지원금액*/
        //                dvo.setLifSpptAggAmt(WithdrawalExcelUtil.formatObjToString(dvo.getLifSpptAggAmt())); /*라이프지원누계금액*/
        //                dvo.setLifRepAmt(WithdrawalExcelUtil.formatObjToString(dvo.getLifRepAmt())); /*라이프환수금액*/
        //                dvo.setLifCntrNo(WithdrawalExcelUtil.formatObjToString(dvo.getLifCntrNo())); /*상조계약번호*/
        //                //                dvo.setLifCntrSn(WithdrawalExcelUtil.formatObjToString(dvo.getLifCntrSn())); /*상조계약번호*/
        //                dataDvo.add(dvo);
        //            }
        //        }

        /* Validation */
        //        validateExcelDatas(headerTitle, dataDvo);
        //        for (WwdbMutualAidAllianceBulkDepositRegDvo dvo : dataDvo) {
        //            if (!ObjectUtils.isEmpty(dvo.getLifCntrNo())) {
        //                dvo.setWelsCntrNo("W" + dvo.getWelsCntrNo().toString().replace("-", ""));
        //                dvo.setLifSpptYm(lifSpptYm); //지원년월
        //                dvo.setLifAlncDvCd(lifAlncDvCd); //제휴코드
        //                dvo.setLifCntrSn("1");
        //                dvo.setWelsCntrSn("1");
        //                processCount += mapper.insertMutualAidAllianceBulkDepositReg(dvo);
        //            }
        //        }

        return processCount;

    }

    public Map<String, String> setLifAlncDvCdHeader(String lifAlncDvCd) {
        Map<String, String> headerTitle = new LinkedHashMap<>();
        if (lifAlncDvCd.equals("30")) { //웰스399
            /*      headerTitle.put("sn", messageResourceService.getMessage("MSG_TXT_SPPT_YM"));  순번 
            headerTitle.put("welsCntrNo", messageResourceService.getMessage("MSG_TXT_CST_CD"));고객코드
            headerTitle.put("test1", messageResourceService.getMessage("MSG_TXT_CUST_STMT"));  고객성명 
            headerTitle.put("lifAlncPdCd", messageResourceService.getMessage("MSG_TXT_PRDT")); 상품
            headerTitle.put("lifAlncPdNm", messageResourceService.getMessage("MSG_TXT_PRDT_NM")); 상품명
            headerTitle.put("test7", messageResourceService.getMessage("MSG_TXT_CNTR_DATE"));  계약일자 
            headerTitle.put("test8", messageResourceService.getMessage("MSG_TXT_RSG_DT"));  해지일자 
            headerTitle.put("test2", messageResourceService.getMessage("MSG_TXT_EV_DT")); 행사일자
            headerTitle.put("test3", messageResourceService.getMessage("MSG_TXT_MPY")); 납부
            headerTitle.put("test4", messageResourceService.getMessage("MSG_TXT_AGGS")); 누계
            headerTitle.put("test5", messageResourceService.getMessage("MSG_TXT_INSLM_AMT")); 부금금액
            headerTitle.put("test6", messageResourceService.getMessage("MSG_TXT_INSLM_AGG")); 부금누계
            headerTitle.put("lifSpptAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); 지원금액
            headerTitle.put("lifSpptAggAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); 라이프지원누계금액
            headerTitle.put("lifRepAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); 라이프환수금액
            headerTitle.put("lifCntrNo", messageResourceService.getMessage("MSG_TXT_MUTU_CNTR_NO")); 상조계약번호*/
            headerTitle.put("sn", "순번"); /* 순번 */
            headerTitle.put("welsCntrNo", "고객코드");/*고객코드*/
            headerTitle.put("test1", "고객성명"); /* 고객성명 */
            headerTitle.put("lifAlncPdCd", "상품"); /*상품*/
            headerTitle.put("lifAlncPdNm", "상품명"); /*상품명*/
            headerTitle.put("test7", "계약일자"); /* 계약일자 */
            headerTitle.put("test8", "해지일자"); /* 해지일자 */
            headerTitle.put("test2", "행사일자"); /*행사일자*/
            headerTitle.put("test3", "납부"); /*납부*/
            headerTitle.put("test4", "누계"); /*누계*/
            headerTitle.put("test5", "부금금액"); /*부금금액*/
            headerTitle.put("test6", "부금누계"); /*부금누계*/
            headerTitle.put("lifSpptAmt", "지원금액"); /*지원금액*/
            headerTitle.put("lifSpptAggAmt", "라이프지원누계금액"); /*라이프지원누계금액*/
            headerTitle.put("lifRepAmt", "라이프환수금액"); /*라이프환수금액*/
            headerTitle.put("lifCntrNo", "상조계약번호"); /*상조계약번호*/
        } else {
            headerTitle.put("sn", messageResourceService.getMessage("MSG_TXT_SPPT_YM")); /* 순번 */
            headerTitle.put("lifAlncPdNm", messageResourceService.getMessage("MSG_TXT_PRDT_NM")); /*상품명*/
            headerTitle.put("lifCntrNo", messageResourceService.getMessage("MSG_TXT_MUTU_CNTR_NO")); /*상조계약번호*/
            headerTitle.put("test1", messageResourceService.getMessage("MSG_TXT_CUST_STMT")); /* 고객성명 */
            headerTitle.put("test7", messageResourceService.getMessage("MSG_TXT_CNTR_DATE")); /* 계약일자 */
            headerTitle.put("test8", messageResourceService.getMessage("MSG_TXT_RSG_DT")); /* 해지일자 */
            headerTitle.put("test2", messageResourceService.getMessage("MSG_TXT_EV_DT")); /*행사일자*/
            headerTitle.put("test3", messageResourceService.getMessage("MSG_TXT_MPY")); /*납부*/
            headerTitle.put("test4", messageResourceService.getMessage("MSG_TXT_AGGS")); /*누계*/
            headerTitle.put("test5", messageResourceService.getMessage("MSG_TXT_INSLM_AMT")); /*부금금액*/
            headerTitle.put("test6", messageResourceService.getMessage("MSG_TXT_INSLM_AGG")); /*부금누계*/
            headerTitle.put("lifSpptAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); /*지원금액*/
            headerTitle.put("lifSpptAggAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); /*라이프지원누계금액*/
            headerTitle.put("lifRepAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AMT")); /*라이프환수금액*/
            headerTitle.put("welsCntrNo", messageResourceService.getMessage("MSG_TXT_WELS_MB_CD"));/*웰스회원코드*/
        }

        return headerTitle;
    }

    public ExcelUploadErrorDvo getErrorDvo(int row, String header, String errorData) {
        ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
        errorDvo.setErrorRow(row);
        errorDvo.setHeaderName(header);
        errorDvo.setErrorData(errorData);
        return errorDvo;
    }

    //    public void validateExcelDatas(
    //        Map<String, String> header, List<WwdbMutualAidAllianceBulkDepositRegDvo> dvos
    //    ) {
    //        //        List<ExcelUploadErrorDvo> errorDvos = new ArrayList<>();
    //
    //        int row = 1;
    //
    //        for (int i = 0; i < dvos.size(); i++) {
    //            WwdbMutualAidAllianceBulkDepositRegDvo dvo = dvos.get(i);
    //
    //            log.info("[{}] {}", i + 1, dvo.toString());
    //
    //            /* 데이터 검증 */
    //            if (StringUtil.isBlank(dvo.getWelsCntrNo().toString())) { //웰스계약번호
    //                BizAssert.hasText(
    //                    dvo.getWelsCntrNo().toString(), "MSG_ALT_INVALID_UPLOAD_DATA",
    //                    new String[] {String.valueOf(row), header.get("welsCntrNo"), dvo.getWelsCntrNo().toString()}
    //                );
    //
    //                //                errorDvos.add(getErrorDvo(i + 1, header.get("welsCntrNo"), dvo.getWelsCntrNo().toString()));
    //            }
    //            if (StringUtil.isBlank(dvo.getLifAlncPdNm().toString())) { //상품명
    //                BizAssert.hasText(
    //                    dvo.getLifAlncPdNm().toString(), "MSG_ALT_INVALID_UPLOAD_DATA",
    //                    new String[] {String.valueOf(row), header.get("lifAlncPdNm"), dvo.getLifAlncPdNm().toString()}
    //                );
    //                //                errorDvos.add(getErrorDvo(i + 1, header.get("lifAlncPdNm"), dvo.getLifAlncPdNm().toString()));
    //            }
    //
    //            if (StringUtil.isBlank(dvo.getLifCntrNo().toString())) { //상조계약번호
    //                BizAssert.hasText(
    //                    dvo.getLifCntrNo().toString(), "MSG_ALT_INVALID_UPLOAD_DATA",
    //                    new String[] {String.valueOf(row), header.get("lifCntrNo"), dvo.getLifCntrNo().toString()}
    //                );
    //                //                errorDvos.add(getErrorDvo(i + 1, header.get("lifCntrNo"), dvo.getLifCntrNo().toString()));
    //            }
    //
    //            if (StringUtil.isBlank(dvo.getLifSpptAmt().toString())) { //지원금액
    //                BizAssert.hasText(
    //                    dvo.getLifSpptAmt().toString(), "MSG_ALT_INVALID_UPLOAD_DATA",
    //                    new String[] {String.valueOf(row), header.get("lifSpptAmt"), dvo.getLifSpptAmt().toString()}
    //                );
    //                //                errorDvos.add(getErrorDvo(i + 1, header.get("lifSpptAmt"), dvo.getLifSpptAmt().toString()));
    //            }
    //
    //        }
    //    }

    @Transactional
    public int saveMutualAidAllianceBulkDepositReg(List<SaveUploadReq> dtos) throws Exception {
        int processCount = 0;

        for (SaveUploadReq dto : dtos) {

            WwdbMutualAidAllianceBulkDepositRegDvo dvo = convert.mapSaveWwdbMutualAidAllianceBulkDepositRegDvo(dto);
            processCount += mapper.insertMutualAidAllianceBulkDepositReg(dvo);

        }
        return processCount;
    }

    @Transactional
    public int saveMutualAidAllianceBulkDepositRegs(SaveReq dto) throws Exception {
        int processCount = 0;

        //통합입금 조회
        SearchDepositRes selectIntegrationDeposit = depositMapper.selectIntegrationDeposit(dto);

        //통합입금 조회 결과가 없을경우
        if (StringUtil.isEmpty(selectIntegrationDeposit.itgDpNo())) {
            throw new BizException("통합입금 데이타가 존재하지 않습니다. [통합입금번호 오류]");
        }

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYm = sysDate.substring(0, 6);
        //한달전
        String prevSysDate = DateUtil.addMonths(sysDateYm, -1);
        String prevSysDateYm = prevSysDate.substring(0, 6);

        //한달전 날짜와 라이프지원년월이 다르면 예외 발생
        if (!prevSysDate.contentEquals(dto.lifSpptYm())) {
            throw new BizException("상조입금생성은 전월만 가능합니다.");
        }

        int diffDay = DateUtil.getDays(sysDateYm, dto.rveDt());

        //이전만 가능하기에 0보다 크면 예외 발생
        BizAssert.isTrue(diffDay > 0, "수납일자는 현재일 과 이전만 가능 합니다.");

        //수납일자가 같은 월이 아니면 예외 발생
        if (!sysDateYm.contentEquals(dto.rveDt().substring(0, 6))) {
            throw new BizException("수납일자 는 현재월 만 가능합니다.");
        }

        diffDay = DateUtil.getDays(sysDateYm, dto.perfDt());

        //오늘 날짜와 실적일자사이가 0보다 클 경우 예외 발생
        BizAssert.isTrue(diffDay > 0, "수납일자는 현재일 과 이전만 가능 합니다.");

        if (!sysDateYm.contentEquals(dto.perfDt().substring(0, 6))) {
            throw new BizException("실적일자 는 현재월 만 가능합니다.");
        }

        //수납마감 체크 테이블 여쭤보기
        SearchReq chkDto = new SearchReq(dto.lifSpptYm(), dto.lifAlncDvCd());

        List<SearchRes> selectMutualAidAllianceBulkDepositRegs = mapper.selectMutualAidAllianceBulkDepositRegs(chkDto);

        if (selectMutualAidAllianceBulkDepositRegs.size() == 0) {
            throw new BizException("일괄일금등록 생성할 데이타가 존재하지 않습니다.");
        }

        if (!prevSysDateYm.contentEquals(selectMutualAidAllianceBulkDepositRegs.get(0).lifSpptYm())) {
            throw new BizException("상조입금생성은 전월만 가능합니다.");
        }

        long dpBlam = selectIntegrationDeposit.dpBlam();
        long sumAmt = selectMutualAidAllianceBulkDepositRegs.get(0).sumAmt();

        BizAssert.isTrue(dpBlam == 0, "대사 할 입금잔액이 없습니다. 입금잔액을 확인하세요.");

        BizAssert.isTrue(dpBlam != sumAmt, "통합 입금잔액 과 총 대사금액 이 일치하지 않습니다.");

        /* 아직 테스트 중이라 미완성*/

        return processCount;
    }
}
