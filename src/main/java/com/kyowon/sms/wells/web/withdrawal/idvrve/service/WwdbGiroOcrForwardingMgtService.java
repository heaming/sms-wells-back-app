package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbGiroOcrForwardingMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDeleteDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintSeqDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbGiroOcrForwardingMgtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 지로OCR발송관리 서비스
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-02-15
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbGiroOcrForwardingMgtService {
    private final WwdbGiroOcrForwardingMgtMapper mapper;

    private final WwdbGiroOcrForwardingMgtConverter convert;

    /**
     * 지로OCR발송관리 목록조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @Transactional
    public PagingResult<SearchRes> getGiroOcrForwardingPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectGiroOcrForwardings(dto, pageInfo);
    }

    /**
     * 지로OCR발송관리 목록 조회 / 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    @Transactional
    public List<SearchRes> getGiroOcrForwardingExcels(SearchReq dto) {
        return mapper.selectGiroOcrForwardings(dto);
    }

    /**
     * 지로OCR발송관리 대상 조회
     * @param cntr 계약번호
     * @param wkDt 작업일자
     * @return List<SearchObjectRes>
     */
    @Transactional
    public List<SearchObjectRes> getGiroOcrForwardingObjects(String cntr, String wkDt) {
        WwdbGiroOcrForwardingMgtDto.SearchCntrReq req;
        if (!"no".equals(cntr)) {
            String cntrNo = cntr.substring(0, 12);
            String cntrSn = cntr.substring(12);
            req = new WwdbGiroOcrForwardingMgtDto.SearchCntrReq(cntrNo, cntrSn, cntr, wkDt);
        } else {
            req = new WwdbGiroOcrForwardingMgtDto.SearchCntrReq("", "", cntr, "");
        }

        return mapper.selectGiroOcrForwardingObjects(req);
    }

    /**
     * 지로OCR발송관리 대상 정보 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return String
     */
    @Transactional
    public SearchGiroCntractRes getGiroOcrForwardingObjectContractInfo(String cntrNo, String cntrSn) {

        return mapper.selectGiroOcrForwardingObjectContractInfo(cntrNo, cntrSn);
    }

    /**
     * 지로OCR발송관리 저장
     * @param dtos
     * @return int processCount
     */
    @Transactional
    public int saveGiroOcrForwardings(List<SaveReq> dtos) {
        int processCount = 0;

        for (SaveReq dto : dtos) {
            WwdbGiroOcrForwardingMgtDvo dvo = convert.mapSaveGiroOcrForwardingDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> processCount += mapper.insertGiroOcrForwardings(dvo);
                case CommConst.ROW_STATE_UPDATED -> processCount += mapper.updateGiroOcrForwardings(dvo);
                case "none" -> processCount += mapper.deleteGiroOcrForwardings(dvo);
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            }
        }

        return processCount;
    }

    /**
     * 지로OCR발송관리 삭제
     * @param dtos
     * @return int processCount
     */
    @Transactional
    public int removeGiroOcrForwardings(List<RemoveReq> dtos) {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WwdbGiroOcrForwardingMgtDvo dvo = convert.mapRemoveGiroOcrForwardingDvo(dto);
            processCount += mapper.deleteGiroOcrForwardings(dvo);
        }
        return processCount;
    }

    /**
     * 지로OCR발송관리 출력 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return
     */
    @Transactional
    public PagingResult<SearchPrintRes> getGiroOcrForwardingPrints(SearchPrintReq dto, PageInfo pageInfo) {
        return mapper.selectGiroOcrForwardingPrints(dto, pageInfo);
    }

    /**
     * 지로OCR발송관리 출력 조회 / 엑셀 다운로드
     * @param dto
     * @return List<SearchPrintRes>
     */
    @Transactional
    public List<SearchPrintRes> getGiroOcrForwardingExels(SearchPrintReq dto) {
        return mapper.selectGiroOcrForwardingPrints(dto);
    }

    /**
     * 지로OCR발송관리 출력 등록
     * @param dtos
     * @param response
     * @return int processCount
     * @throws Exception
     */
    @Transactional
    public int saveGiroOcrForwardingPrints(SavePrintReq dtos, HttpServletResponse response) throws Exception {
        int processCount = 0;
        int giroOcrForwardingDetailCount;
        StringBuilder retStr = new StringBuilder();

        WwdbGiroOcrForwardingPrintDvo dvo = convert.mapSaveGiroOcrForwardingPrintDvo(dtos);
        if (CommConst.ROW_STATE_CREATED.equals(dtos.state())) {
            WwdbGiroOcrForwardingPrintSeqDvo selectGiroOcrForwardingPrintInfo = mapper
                .selectGiroOcrForwardingPrintInfo(dtos);

            dvo.setGiroOcrPblSeqn(selectGiroOcrForwardingPrintInfo.getGiroOcrPblSeqn());
            dvo.setGiroOcrPblDtm(selectGiroOcrForwardingPrintInfo.getGiroOcrPblDtm());
            dvo.setGiroRglrDvCd(dtos.giroRglrDvCd());
            giroOcrForwardingDetailCount = mapper.insertGiroOcrForwardingDetailPrints(dvo);

            BizAssert.isFalse(giroOcrForwardingDetailCount == 0, "생성할 자료가 없습니다. 작업일을 확인하세요.");

            dvo.setGiroOcrPblTotQty(giroOcrForwardingDetailCount);
            processCount += mapper.insertGiroOcrForwardingPrints(dvo);

            List<SearchPrintCreateRes> searchPrintCreateRes = mapper.selectGiroPrintCreate(dvo);

            int selCnt = searchPrintCreateRes.size() - 1;

            int i = 0;

            for (SearchPrintCreateRes dto : searchPrintCreateRes) {
                if (i == 0) {
                    retStr.setLength(0);
                    retStr.append(dto.c1());
                } else if (i == selCnt) {
                    retStr.append(dto.c1());
                } else {
                    retStr.append(dto.c1());
                    retStr.append(dto.c2());
                    retStr.append(dto.c3());
                }
                i++;
            }

            if (StringUtil.isNotEmpty(retStr.toString())) {
                try {
                    ServletOutputStream output = response.getOutputStream();

                    BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(output, StandardCharsets.UTF_8)
                    );

                    writer.write(retStr.toString());
                    writer.flush();
                    writer.close();
                } catch (IOException ioe) {
                    log.debug("sendData:" + ioe.getMessage());
                }
            }
        } else {
            throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
        }
        return processCount;
    }

    /**
     * 지로OCR발송관리 출력 삭제
     * @param dtos
     * @return int processCount
     * @throws Exception
     */
    @Transactional
    public int removeGiroOcrForwardingPrints(List<removePrintReq> dtos) throws Exception {
        int processCount = 0;

        for (removePrintReq dto : dtos) {
            WwdbGiroOcrForwardingPrintDeleteDvo dvo = convert.mapDeleteGiroOcrForwardingPrintDvo(dto);
            processCount += mapper.deleteGiroOcrForwardingPrints(dvo);
        }
        return processCount;
    }

    /**
     * 지로 출력 업데이트
     * @param dto
     * @return int processCount
     * @throws Exception
     */
    @Transactional
    public int saveGiroPrintDate(WwdbGiroOcrForwardingMgtDto.saveGiroPrintReq dto) throws Exception {
        int processCount = 0;

        WwdbGiroOcrForwardingPrintDvo dvo = convert.mapSaveGiroPrintDvo(dto);

        String sysDate = DateUtil.getNowString();
        String sysDateYmd = sysDate.substring(0, 8);

        dvo.setGiroOcrPrntDt(sysDateYmd);

        processCount += mapper.updateGiroPrintDate(dvo);

        processCount += mapper.insertGiroPrintDate(dvo);

        return processCount;
    }
}
