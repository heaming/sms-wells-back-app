package com.kyowon.sms.wells.web.deduction.rds.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.rds.converter.ZdecRdsAnAccountErrorMgtConverter;
import com.kyowon.sms.common.web.deduction.rds.dto.ZdecRdsAnAccountErrorMgtDto.SearchRdsAnAccountErrorChkReq;
import com.kyowon.sms.common.web.deduction.rds.dto.ZdecRdsAnAccountErrorMgtDto.SearchRdsAnAccountErrorNewChkRes;
import com.kyowon.sms.common.web.deduction.rds.dvo.ZdecRdsAnAccountErrorMgtDvo;
import com.kyowon.sms.common.web.deduction.rds.mapper.ZdecRdsAnAccountErrorMgtMapper;
import com.kyowon.sms.common.web.withdrawal.bilfnt.dvo.ZwdaAutoTransferRealTimeAccountCheckDvo;
import com.kyowon.sms.common.web.withdrawal.bilfnt.service.ZwdaAutoTransferRealTimeAccountService;
import com.kyowon.sms.common.web.withdrawal.bilfnt.service.ZwdaSettleBankSendAndReceiveService;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdecRdsAnAccountErrorMgtService {

    private final ExcelDownloadService excelDownloadService;
    //    private final MessageResourceService msgService;

    //    private final ZdezAnAccountEftnService zdezAnAccountEftnService;

    private final ZdecRdsAnAccountErrorMgtMapper mapper;
    private final ZdecRdsAnAccountErrorMgtConverter converter;

    private final ZwdaAutoTransferRealTimeAccountService acService;
    private final AttachFileService attachFileService;
    private static final String groupId = "ATG_DEC_BAI_DCMT_FILE";

    //    private final EaiInterfaceService interfaceService;

    //    private final WwdaAutoTransferInterfaceService wwdaAutoTransferInterfaceService;

    private final ZwdaSettleBankSendAndReceiveService zwdaSettleBankSendAndReceiveService;

    /**
     * RDS 계좌오류 체크 조회
     * @param dto
     * @return SearchRdsAnAccountErrorRes
     */
    @Transactional
    public ZdecRdsAnAccountErrorMgtDvo getRdsAnAccountErrorChk(SearchRdsAnAccountErrorChkReq dto) {
        /*
        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 X - 기존 계좌 오류 X = 처리 X
        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 X - 기존 계좌 오류 O = 기존 계좌 오류정보 삭제처리(오류해제)

        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 O - 기존 계좌 오류 X = 처리 x 오류여부만 리턴
        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 O - 기존 계좌 오류 O = 처리 X 오류여부만 리턴

        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 X - 기존 계좌 오류 X = 처리 X
        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 X - 기존 계좌 오류 O = 처리 X

        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 O - 기존 계좌 오류 X = 처리 x 오류여부만 리턴
        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 O - 기존 계좌 오류 O = 처리 x 오류여부만 리턴
        */
        ZdecRdsAnAccountErrorMgtDvo errorInfo = converter.mapAnAccountChk(dto);

        // 계좌 신규 체크 N:신규아님(파트너계좌기본에계좌존재), Y:신규(파트너계좌기본에 계좌없음)
        SearchRdsAnAccountErrorNewChkRes newChk = mapper.isRdsAnAccountNewChk(dto);

        // 계좌 번호 변경 여부
        if (null != newChk.acnoEncr() && newChk.acnoEncr().equals(errorInfo.getAcnoEncr())) {
            errorInfo.setUpdateYn("N"); // 수정X
        } else {
            errorInfo.setUpdateYn("Y"); // 수정O
        }

        // 신규 여부
        errorInfo.setChkYn(newChk.chkYn());

        // 계좌실명인증 서비스 호출
        Map<String, String> accountResult = accountRealNameService(errorInfo);

        // acFntRsCd N:정상계좌, Y:오류계좌
        String errorYn = accountResult.get("acFntRsCd");
        //        String rdsAcErrId = "EFEDD151133854700709"; // 임시
        //        String acErrDvCd = "9";// 임시 RDS계좌오류구분코드, 확인필요

        // 기존 존재하는 오류계좌 존재 체크
        String updateRdsAcErrId = mapper.isRdsAnAccountErrorExist(errorInfo);

        // 기존에 계좌가 있고 계좌확인api호출부에서 에러라고 했을때
        if ("N".equals(newChk.chkYn()) && "Y".equals(errorYn)) {
            /*통테결함 테스트용 주석
            // 기존계좌와 입력한 계좌가 다른경우
            if ("Y".equals(errorInfo.getUpdateYn())) {

                //기존계좌 종료 prtnrAcId;
                mapper.deleteAnAccount(errorInfo);

                String rdsAcErrId1 = mapper.isRdsAnAccountErrorExist(errorInfo);

                if (rdsAcErrId1 != null && !rdsAcErrId1.isEmpty()) {
                    errorInfo.setRdsAcErrId(rdsAcErrId1);
                    errorInfo.setDtaDlYn("Y"); // 삭제처리

                    mapper.updateRdsAnAccountError(errorInfo);
                    mapper.insertRdsAnAccountErrorHist(errorInfo);

                    // 삭제처리
                    mapper.deleteAnAccountInfo(errorInfo);
                }

                // 파트너계좌기본 저장
                // 대표계좌여부 set
                errorInfo.setDgAcYn("Y");

                // 유효시작일시 채번
                String vlStrtDtm = mapper.selectVlStrtDtm();
                errorInfo.setVlStrtDtm(vlStrtDtm);

                mapper.insertAnAccount(errorInfo);
            }

            // 기존계좌와 입력한 계좌가 같은경우 + 체크한계좌가 에러일때

            // 위에서, 기존계좌 종료시키고 에러 상세에 에러가 남아있어서 그것도 삭제되고 나서 신규 다시 에러처리위한 체크
            // 기존에 오류계좌에 존재하는지 체크.
            String rdsAcErrId2 = mapper.isRdsAnAccountErrorExist(errorInfo);

            // 오류계좌상세 에러에 데이터가 없을 경우 오류등록 insert
            //if (updateRdsAcErrId == null || updateRdsAcErrId.isEmpty()) { //예전것.과장님작성부분
            if (rdsAcErrId2 == null || rdsAcErrId2.isEmpty()) {

                // 채번
                String newRdsAcErrId = mapper.selectRdsAnAccountErrorPrtnrErrAcId();
                errorInfo.setRdsAcErrId(newRdsAcErrId);
                errorInfo.setErrVlStrtDtm(errorInfo.getVlStrtDtm()); // 계좌기본에서 받아온 유효시작일시를 오류유효시작일시로 set
                errorInfo.setRdsAcErrOcDvCd("02"); // RDS계좌오류발생구분코드 set 01:배치, 02:화면, 03:수기
                errorInfo.setRdsAcErrDvCd("9"); // TODO:계좌확인서비스 호출 후 받는값인지확인필요 임시 9 지정, RDS계좌오류구분코드set 1:해지계좌, 3:예금주다름 9:기타
                //errorInfo.setPrtnrAcId(null);  컬럼삭제로 인한 주석 오류상세에 오류로 적용해야함으로 파트너계좌ID 컬럼은 null

                // 오류등록일자 set
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date time = new Date();
                String errRgstDt = dateFormat.format(time);
                errorInfo.setErrRgstDt(errRgstDt);

                mapper.insertRdsAnAccountError(errorInfo);
                mapper.insertRdsAnAccountErrorHist(errorInfo);
            }

            */
            // 오류 여부만 리턴. 계좌저장 시첨에 오류처리.
            errorInfo.setErrYn(errorYn);
            errorInfo.setRdsAcErrId(updateRdsAcErrId);

        } else if ("N".equals(newChk.chkYn()) && "N".equals(errorYn)) { // 기존에 계좌가 있고 api호출부에서 에러가 아니라고 했을경우

            // 기존계좌와 입력한 계좌가 같은경우
            if ("N".equals(errorInfo.getUpdateYn())) {
                // 오류계좌상세 에러에 데이터가 있을 경우 update(기존엔 오류였지만 api에서 에러가아니라고 판별하면 오류해제)
                if (updateRdsAcErrId != null && !updateRdsAcErrId.isEmpty()) {
                    errorInfo.setRdsAcErrId(updateRdsAcErrId);

                    errorInfo.setNomVlStrtDtm(errorInfo.getVlStrtDtm()); // 정상유효시작일시 set
                    errorInfo.setDtaDlYn("Y");

                    mapper.updateRdsAnAccountError(errorInfo);
                    mapper.insertRdsAnAccountErrorHist(errorInfo);
                }
            }
        }

        // 파트너계좌id 리턴
        // 계좌번호 초기화
        // errorInfo.setAcnoEncr("");
        errorInfo.setDtaDlYn("");
        errorInfo.setErrYn(errorYn);
        errorInfo.setCheckPrtnrKnm(accountResult.get("owrKnm"));

        // 리턴값
        // PrtnrErrAcId, PrtnrErrAcIdTmp, RdsAcErrId, PrtnrAcId, UpdateYn, ChkYn, ErrYn, PrtnrAcIdTmp
        return errorInfo;
    }

    /**
     * 계좌실명인증 서비스 호출
     */
    public Map<String, String> accountRealNameService(ZdecRdsAnAccountErrorMgtDvo dvo) {

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        // Z-WD-S-0027(은행계좌유효성체크_SB) 인터페이스 다이렉트 아닌것 용 param
        Map<String, Object> reqParam2 = new HashMap<>();
        reqParam2.put("bnkCd", dvo.getFnitCd()); // 은행코드
        reqParam2.put("acNo", dvo.getAcnoEncr()); // 계좌번호
        reqParam2.put("achldrNm", dvo.getPrtnrKnm()); // 예금주
        reqParam2.put("sysDvCd", session.getTenantCd()); // 시스템구분코드 EDU, WELLS
        reqParam2.put("copnDvDrmVal", "1"); // 법인격구분식별
        reqParam2.put("copnDvCd", "1"); // 법인격구분코드 개인/법인 구분값

        // Z-WD-S-0027(은행계좌유효성체크_SB) 인터페이스 다이렉트 아닌것 이력까지 같이
        ZwdaAutoTransferRealTimeAccountCheckDvo res = acService.saveAftnAcEftnChecks(reqParam2);

        // OSBN2_CDEO1001 (계좌유효성검사) 인터페이스 다이렉트 용 param
        //        Map<String, Object> reqParam = new HashMap<>();
        //        reqParam.put("FNIT_CD", dvo.getFnitCd()); // 금융기관코드
        //        reqParam.put("BANK_CD", dvo.getFnitCd()); // 은행코드
        //        reqParam.put("ACC_NO", dvo.getAcnoEncr());//계좌번호
        //        reqParam.put("SYS_DV_CD", session.getTenantCd());//시스템구분코드 EDU, WELLS

        // OSBN2_CDEO1001 (계좌유효성검사) 인터페이스 다이렉트
        //        ZwdaAnAccountEffectivenessResDvo res = zwdaSettleBankSendAndReceiveService
        //            .getAccountOwnerRnmConfInterface(reqParam);

        BizAssert.isTrue(null != res, "MSG_ALT_AC_CHECK_ERR", new String[] {""});

        Map<String, String> accountResult = new HashMap<>();

        //        rqdt; // 거래일자
        //        userDealNo; //사용자거래번호
        //        rplyCd; // 응답코드
        //        depsPrsnNm; //예금주명

        // acFntRsCd N:정상계좌, Y:오류계좌
        if ("0000".equals(res.getAcFntRsCd())) {
            accountResult.put("acFntRsCd", "N");
        } else {
            accountResult.put("acFntRsCd", "Y");
        }

        accountResult.put("owrKnm", res.getAchldrNm());

        //        if ("0000".equals(res.getRplyCd())) {
        //            accountResult.put("acFntRsCd", "N");
        //        } else {
        //            accountResult.put("acFntRsCd", "Y");
        //        }

        //        accountResult.put("owrKnm", res.getDepsPrsnNm());

        return accountResult;
    }
}
