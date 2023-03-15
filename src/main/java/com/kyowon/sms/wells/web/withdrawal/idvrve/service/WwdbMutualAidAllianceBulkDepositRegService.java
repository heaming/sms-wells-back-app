package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

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
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.uifw.service.MessageResourceService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
    public int saveMutualAidAllianceBulkDepositRegExcelUpload(MultipartFile file)
        throws Exception {

        Map<String, String> headerTitle = Map.of(
            "lifSpptYm", messageResourceService.getMessage("MSG_TXT_SPPT_YM"), /* 지원년월 */
            "welsCntrNo", messageResourceService.getMessage("MSG_TXT_WELLS_CNTR_NO"), /*웰스계약번호*/
            "welsCntrSn", messageResourceService.getMessage("MSG_TXT_WELLS_CNTR_SN"), /*웰스계약일련번호*/
            "lifAlncPdCd", messageResourceService.getMessage("MSG_TXT_PRDT"), /*상품*/
            "lifAlncPdNm", messageResourceService.getMessage("MSG_TXT_PRDT_NM"), /*상품명*/
            "lifSpptAmt", messageResourceService.getMessage("MSG_TXT_SPPT_AMT"), /*지원금액*/
            "lifCntrNo", messageResourceService.getMessage("MSG_TXT_MUTU_CNTR_NO"), /*상조계약번호*/
            "lifCntrSn", messageResourceService.getMessage("MSG_TXT_MUTU_CNTR_SN") /*상조계약번호*/
        );

        List<SaveUploadReq> dtos = excelReadService.readExcel(file, new ExcelMetaDvo(7), SaveUploadReq.class);

        int row = 1;

        Map<String, String> headerTitleValidation;

        for (SaveUploadReq req : dtos) {
            headerTitleValidation = Map.of(
                "lifSpptYm", req.lifSpptYm(), /* 지원년월 */
                "welsCntrNo", req.welsCntrNo(), /*웰스계약번호*/
                "welsCntrSn", req.welsCntrSn(), /*웰스계약일련번호*/
                "lifAlncPdCd", req.lifAlncPdCd(), /*상품*/
                "lifAlncPdNm", req.lifAlncPdNm(), /*상품명*/
                "lifSpptAmt", req.lifSpptAmt(), /*지원금액*/
                "lifCntrNo", req.lifCntrNo(), /*상조계약번호*/
                "lifCntrSn", req.lifCntrSn() /*상조일련번호*/
            );

            for (String key : headerTitleValidation.keySet()) {
                BizAssert.hasText(
                    headerTitleValidation.get(key), "MSG_ALT_INVALID_UPLOAD_DATA",
                    new String[] {String.valueOf(row), headerTitle.get(key), headerTitleValidation.get(key)}
                );
            }
            row++;
        }
        return saveMutualAidAllianceBulkDepositRegs(dtos);
    }

    @Transactional
    public int saveMutualAidAllianceBulkDepositRegs(List<SaveUploadReq> dtos) throws Exception {

        int processCount = 0;

        for (SaveUploadReq dto : dtos) {
            WwdbMutualAidAllianceBulkDepositRegDvo dvo = convert.mapSaveWwdbMutualAidAllianceBulkDepositRegDvo(dto);
            dvo.setLifAlncDvCd("30");
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
