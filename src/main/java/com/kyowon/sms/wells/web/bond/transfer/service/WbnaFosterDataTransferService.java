package com.kyowon.sms.wells.web.bond.transfer.service;

import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaFosterDataSearchReqDvo;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaFosterDataTransferDvo;
import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaFosterDataTransferMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class WbnaFosterDataTransferService {
    private final WbnaFosterDataTransferMapper mapper;
    @Value("${sflex.file.path.root}")
    private String filePath; // TODO 파일경로 정의 되면 수정 필요

    /**
     * 추심사에 발송 할 전문자료 생성
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부
     * @param bndClctnPrpDvCd 채권추심속성구분코드
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param cstNo 고객번호
     */
    @Transactional
    public void getFosterData(
        String baseYm, String bzHdqDvCd, String bndClctnPrpDvCd, String cntrNo, String cntrSn, String cstNo
    ) throws IOException {
        WbnaFosterDataSearchReqDvo reqDvo = new WbnaFosterDataSearchReqDvo();
        reqDvo.setBaseYm(baseYm);
        reqDvo.setBzHdqDvCd(bzHdqDvCd);
        reqDvo.setBndClctnPrpDvCd(bndClctnPrpDvCd);
        reqDvo.setCntrNo(cntrNo);
        reqDvo.setCntrSn(cntrSn);
        reqDvo.setCstNo(cstNo);

        WbnaFosterDataTransferDvo dvo = mapper.selectFosterData(reqDvo);
        if (dvo != null && !StringUtils.equals(dvo.getCstNo(), "")) {
            File file = new File(filePath + "test.csv"); // TODO 파일경로 정의 되면 수정 필요
            //파일 없는 경우 파일 생성 후 작업
            if (!file.exists()) {
                file.createNewFile(); // TODO 파일 관련 작업 이후가 정의 되지 않아 확인 중
            }
            FileUtils.writeStringToFile(file, dvo.toTransmissionContent(), "EUC-KR", true);
        }

        //TODO WbnaFosterTransferMgtController 구현 후 관련 내용 추가 작업 필요(현재는 쿼리문만 작업)
        //채권위탁이관내역 Table (TB_CBBO_BND_FSTR_TF_IZ) Table에 "위탁이관 집계결과 상세" 저장
        //위탁이관 집계결과 상세조회:WbnaFosterTransferMgtController.getFosterTransferDetails() -> 채권위탁이관내역 Table Insert
        //Wells채권추심사송신내역 Table(TB_CBBO_WELLS_BND_CLCO_SEND_IZ)에 "위탁이관 전문자료" 저장
        //WbnaFosterTransferMgtController.getFosterTransferDetails() -> Wells채권추심사송신내역 Table Insert
    }
}
