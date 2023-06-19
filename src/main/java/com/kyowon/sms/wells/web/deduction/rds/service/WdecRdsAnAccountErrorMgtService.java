package com.kyowon.sms.wells.web.deduction.rds.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.deduction.rds.converter.ZdecRdsAnAccountErrorMgtConverter;
import com.kyowon.sms.common.web.deduction.rds.dto.ZdecRdsAnAccountErrorMgtDto.SearchRdsAnAccountErrorChkReq;
import com.kyowon.sms.common.web.deduction.rds.dto.ZdecRdsAnAccountErrorMgtDto.SearchRdsAnAccountErrorNewChkRes;
import com.kyowon.sms.common.web.deduction.rds.dvo.ZdecRdsAnAccountErrorMgtDvo;
import com.kyowon.sms.common.web.deduction.rds.mapper.ZdecRdsAnAccountErrorMgtMapper;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdaAutoTransferInterfaceService;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.common.docs.service.AttachFileService;
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

    private final AttachFileService attachFileService;
    private final String groupId = "ATG_DEC_BAI_DCMT_FILE";

    //    private final EaiInterfaceService interfaceService;

    private final WwdaAutoTransferInterfaceService wwdaAutoTransferInterfaceService;

    /**
     * RDS 계좌오류 체크 조회
     * @param dto
     * @return SearchRdsAnAccountErrorRes
     */
    @Transactional
    public ZdecRdsAnAccountErrorMgtDvo getRdsAnAccountErrorChk(SearchRdsAnAccountErrorChkReq dto) {
        /*
        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 X - 기존 계좌 오류 X = 처리 X
        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 X - 기존 계좌 오류 O = 기존 계좌 오류정보 삭제처리
        
        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 O - 기존 계좌 오류 X = 입력 계좌 오류정보 저장
        기존 계좌 O - 입력 계좌번호와 동일 - 입력 계좌 오류 O - 기존 계좌 오류 O = 처리 X
        
        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 X - 기존 계좌 오류 X = 처리 X
        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 X - 기존 계좌 오류 O = 처리 X
        
        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 O - 기존 계좌 오류 X = 기존 계좌정보 삭제처리, 입력 계좌정보 저장, 입력 계좌 오류정보 저장
        기존 계좌 O - 입력 계좌번호와 다름 - 입력 계좌 오류 O - 기존 계좌 오류 O = 기존 계좌정보 삭제처리, 기존 계좌 오류정보 삭제처리, 입력 계좌정보 저장, 입력 계좌 오류정보 저장
        */
        ZdecRdsAnAccountErrorMgtDvo errorInfo = converter.mapAnAccountChk(dto);

        int proccCnt = 0;

        // 계좌 신규 체크
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

        // TODO: 계좌확인 서비스 만들어지면 리턴값 확인하고 set
        String errorYn = accountResult.get("acFntRsCd");
        ; // 임시------ N: 에러가아님, Y:에러
        String rdsAcErrId = "EFEDD151133854700709"; // 임시
        String acErrDvCd = "9";// 임시 RDS계좌오류구분코드, 확인필요

        /* errorInfo.setPrtnrErrAcId(newChk.prtnrAcId());   컬럼삭제로 인한 주석*/
        /*errorInfo.setPrtnrErrAcIdTmp(newChk.prtnrAcId()); 컬럼삭제로 인한 주석*/

        String updateRdsAcErrId = mapper.isRdsAnAccountErrorExist(errorInfo);
        /*String keyVal = newChk.prtnrAcId(); 컬럼삭제로 인한 주석*/

        // 기존에 계좌가 있고 api호출부에서 에러라고 했을때
        if ("N".equals(newChk.chkYn()) && "Y".equals(errorYn)) {
            // 기존계좌와 입력한 계좌가 다른경우
            if ("Y".equals(errorInfo.getUpdateYn())) {
                //기존계좌 종료 prtnrAcId; /* 파트너계좌ID */
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

                /*keyVal = mapper.selectRdsAnAccountPrtnrAcId(); 컬럼삭제로 인한 주석*/
                /*errorInfo.setPrtnrAcId(keyVal); 컬럼삭제로 인한 주석*/ // 파트너계좌기본 채번 set

                // 계좌마스터 저장
                // TODO: [FEDD] 대표계좌여부 set 해주어야 하는지 확인 필요.
                errorInfo.setDgAcYn("N");

                // 유효시작일시 채번
                String vlStrtDtm = mapper.selectVlStrtDtm();
                errorInfo.setVlStrtDtm(vlStrtDtm);

                mapper.insertAnAccount(errorInfo);
            }

            // 위에서, 기존계좌 종료시키고 에러 상세에 에러가 남아있어서 그것도 삭제되고 나서 신규 다시 에러처리위한 체크
            String rdsAcErrId2 = mapper.isRdsAnAccountErrorExist(errorInfo);

            // 상세 에러에 데이터가 없을 경우 insert
            //if (updateRdsAcErrId == null || updateRdsAcErrId.isEmpty()) { //예전것.과장님작성부분
            if (rdsAcErrId2 == null || rdsAcErrId2.isEmpty()) {

                // 채번
                String newRdsAcErrId = mapper.selectRdsAnAccountErrorPrtnrErrAcId();
                errorInfo.setRdsAcErrId(newRdsAcErrId);
                errorInfo.setErrVlStrtDtm(errorInfo.getVlStrtDtm()); // 계좌기본에서 받아온 유효시작일시를 오류유효시작일시로 set
                errorInfo.setRdsAcErrOcDvCd("02"); // RDS계좌오류발생구분코드 set 01:배치, 02:화면, 03:수기
                errorInfo.setRdsAcErrDvCd("9"); // TODO:계좌확인서비스 호출 후 받는값인지확인필요 임시 9 지정, RDS계좌오류구분코드set 1:해지계좌, 3:예금주다름 9:기타
                /*errorInfo.setPrtnrErrAcId(keyVal); 컬럼삭제로 인한 주석*/
                //errorInfo.setDtaDlYn("N"); // 삭제여부 set 테이블기본값으로 인한 주석
                //errorInfo.setTcalYn("N"); // 전화통화여부 set 테이블기본값으로 인한 주석
                /*errorInfo.setPrtnrAcId(null);  컬럼삭제로 인한 주석 */ // 오류상세에 오류로 적용해야함으로 파트너계좌ID 컬럼은 null

                // 오류등록일자 set
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date time = new Date();
                String errRgstDt = dateFormat.format(time);
                errorInfo.setErrRgstDt(errRgstDt);

                proccCnt += mapper.insertRdsAnAccountError(errorInfo);
                mapper.insertRdsAnAccountErrorHist(errorInfo);
            }

            errorInfo.setErrYn(errorYn);
        } else if ("N".equals(newChk.chkYn()) && "N".equals(errorYn)) { // 기존에 계좌가 있고 api호출부에서 에러가 아니라고 했을경우

            // 기존계좌와 입력한 계좌가 같은경우
            if ("N".equals(errorInfo.getUpdateYn())) {
                // 상세 에러에 데이터가 있을 경우 update
                if (updateRdsAcErrId != null && !updateRdsAcErrId.isEmpty()) {
                    errorInfo.setRdsAcErrId(updateRdsAcErrId);
                    /*errorInfo.setPrtnrErrAcId(null);  컬럼삭제로 인한 주석 */
                    //errorInfo.setDtaDlYn("Y");

                    errorInfo.setNomVlStrtDtm(errorInfo.getVlStrtDtm());
                    errorInfo.setDtaDlYn("N");

                    proccCnt += mapper.updateRdsAnAccountError(errorInfo); //
                    mapper.insertRdsAnAccountErrorHist(errorInfo);

                    // 삭제처리
                    //mapper.deleteAnAccountInfo(errorInfo);
                }
            }
        }

        // 파트너계좌id 리턴
        /*errorInfo.setPrtnrAcIdTmp(newChk.prtnrAcId()); 컬럼삭제로 인한 주석*/
        // 계좌번호 초기화
        // errorInfo.setAcnoEncr("");
        errorInfo.setDtaDlYn("");
        errorInfo.setErrYn(errorYn);
        errorInfo.setCheckPrtnrKnm(accountResult.get("owrKnm"));

        //        BizAssert.isTrue(proccCnt == 1, "MSG_ALT_SVE_ERR");

        // 리턴값
        // PrtnrErrAcId, PrtnrErrAcIdTmp, RdsAcErrId, PrtnrAcId, UpdateYn, ChkYn, ErrYn, PrtnrAcIdTmp
        return errorInfo;
    }

    /**
     * 계좌실명인증 서비스 호출
     */
    public Map<String, String> accountRealNameService(ZdecRdsAnAccountErrorMgtDvo dvo) {

        WwdaAutoTransferInterfaceDto.SearchRealNameCertificationReq dto = new WwdaAutoTransferInterfaceDto.SearchRealNameCertificationReq(
            dvo.getFnitCd(), dvo.getAcnoEncr(), dvo.getPrtnrKnm(), dvo.getBryyMmdd()
        );

        List<WwdaAutoTransferInterfaceDto.SearchRealNameCertificationRes> res = wwdaAutoTransferInterfaceService
            .getRealNameCertification(dto);

        BizAssert.isTrue(null != res, "MSG_ALT_AC_CHECK_ERR", new String[] {""});

        Map<String, String> accountResult = new HashMap<>();

        if ("1".equals(res.get(0).acFntRsCd())) {
            accountResult.put("acFntRsCd", "N");
        } else {
            accountResult.put("acFntRsCd", "Y");
        }

        accountResult.put("acFntRsCdNm", res.get(0).acFntRsCdNm());
        accountResult.put("owrKnm", res.get(0).owrKnm());

        return accountResult;
    }
}
