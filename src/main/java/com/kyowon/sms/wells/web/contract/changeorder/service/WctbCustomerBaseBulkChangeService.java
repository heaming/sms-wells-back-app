package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbCustomerBaseBulkChangeConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCustomerBaseBulkChangeDto;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCustomerBaseBulkChangeDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbCustomerBaseBulkChangeMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCustomerBaseBulkChangeService {
    private final WctbCustomerBaseBulkChangeMapper mapper;
    private final WctbCustomerBaseBulkChangeConverter converter;
    private final MessageResourceService messageResourceService;

    public List<WctbCustomerBaseBulkChangeDto.SearchCustomerRes> getBulkChangeObjects(
        WctbCustomerBaseBulkChangeDto.SearchReq searchReq
    ) {
        //고객기준 일괄변경 대상 조회(자동이체,설치자명,세금계산서발행여부)
        WctbCustomerBaseBulkChangeDvo paramDvo = converter.mapSearchReqToWctbCustomerBaseBulkChangeDvo(searchReq);

        List<WctbCustomerBaseBulkChangeDvo> resDvos = mapper.selectBulkChangeObjects(paramDvo);

        List<WctbCustomerBaseBulkChangeDto.SearchCustomerRes> resDtos = new ArrayList<>();

        for (WctbCustomerBaseBulkChangeDvo dvoItem : resDvos) {
            WctbCustomerBaseBulkChangeDto.SearchCustomerRes resDto = converter
                .mapWctbCustomerBaseBulkChangeDvoToSearchCustomerRes(dvoItem);
            resDtos.add(resDto);
        }

        return resDtos;
    }

    public List<WctbCustomerBaseBulkChangeDto.SearchPartnerRes> getPlannerChanges(
        WctbCustomerBaseBulkChangeDto.SearchReq searchReq
    ) {
        //고객기준 일괄변경 대상 조회(플래너변경)
        WctbCustomerBaseBulkChangeDvo paramDvo = converter.mapSearchReqToWctbCustomerBaseBulkChangeDvo(searchReq);

        List<WctbCustomerBaseBulkChangeDvo> resDvos = mapper.selectPlannerChanges(paramDvo);

        List<WctbCustomerBaseBulkChangeDto.SearchPartnerRes> resDtos = new ArrayList<>();

        for (WctbCustomerBaseBulkChangeDvo dvoItem : resDvos) {
            WctbCustomerBaseBulkChangeDto.SearchPartnerRes resDto = converter
                .mapWctbCustomerBaseBulkChangeDvoToSearchPartnerRes(dvoItem);
            resDtos.add(resDto);
        }

        return resDtos;
    }

    @Transactional
    public int saveCstBaseBulkChangeOjMdfcs(WctbCustomerBaseBulkChangeDto.SaveReq dto) {
        WctbCustomerBaseBulkChangeDvo dvo = converter.mapSaveReqToWctbCustomerBaseBulkChangeDvo(dto);
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        int processCount = 0;
        String userNm = session.getUserName();
        for (WctbCustomerBaseBulkChangeDvo.Contract contractDvo : dvo.getContractList()) {

            String prcDvCd = dvo.getPrcDvCd();
            String cntrNo = contractDvo.getCntrNo();
            String cntrSn = contractDvo.getCntrSn();
            String cntrCstNo = contractDvo.getCntrCstNo();
            String endDtm = "99991231235959";
            String endDt = "99991231";

            WctbCustomerBaseBulkChangeDvo dateDvo = mapper.selectDateTime();

            String fstRgstDtm = dateDvo.getFstRgstDtm(); /* 최초등록일시 */
            String fstRgstUsrId = dateDvo.getFstRgstUsrId(); /* 최초등록사용자ID */
            String fstRgstPrgId = dateDvo.getFstRgstPrgId(); /* 최초등록프로그램ID */
            String fstRgstDeptId = dateDvo.getFstRgstDeptId(); /* 최초등록부서ID */
            String fnlMdfcDtm = dateDvo.getFnlMdfcDtm(); /* 최종수정일시 */
            String fnlMdfcUsrId = dateDvo.getFnlMdfcUsrId(); /* 최종수정사용자ID */
            String fnlMdfcPrgId = dateDvo.getFnlMdfcPrgId(); /* 최종수정프로그램ID */
            String fnlMdfcDeptId = dateDvo.getFnlMdfcDeptId(); /* 최종수정부서ID */

            String now = fstRgstDtm.substring(0, 8);

            /* 최종(최초)등록일시 및 등록자 ID */
            dvo.setFstRgstDtm(fstRgstDtm);
            dvo.setFstRgstUsrId(fstRgstUsrId);
            dvo.setFstRgstPrgId(fstRgstPrgId);
            dvo.setFstRgstDeptId(fstRgstDeptId);
            dvo.setFnlMdfcDtm(fnlMdfcDtm);
            dvo.setFnlMdfcUsrId(fnlMdfcUsrId);
            dvo.setFnlMdfcPrgId(fnlMdfcPrgId);
            dvo.setFnlMdfcDeptId(fnlMdfcDeptId);

            dvo.setHistStrtDtm(fstRgstDtm); /* 이력시작일시 */
            dvo.setHistEndDtm(endDtm); /* 이력종료일시*/
            dvo.setDtaDlYn("N"); /* 삭제여부 */

            switch (prcDvCd) {
                case "1" -> {
                    String cntrAdrpcId = mapper.selectContractAddressId(cntrNo, cntrSn);
                    String cntrChAkCn = String.format("수령자한글명: %s", dvo.getIstNm());/* 계약변경요청내용 */
                    String bfchCn = String.format("수령자한글명: %s", contractDvo.getIstKnm()); /* 변경전내용 */

                    BizAssert.isTrue(
                        StringUtils.isNotEmpty(cntrAdrpcId), "MSG_ALT_CHK_CNTR_NO",
                        new String[] {contractDvo.getCntrNo()}
                    );

                    dvo.setCntrChRcpDtm(fstRgstDtm); /* 계약변경접수일자 */
                    dvo.setCntrChTpCd("102"); /* 계약변경유형코드 */
                    dvo.setChRqrDvCd("2"); /* 변경요청자구분코드 */
                    dvo.setChRqrNm(userNm); /* 변경요청자명 */
                    dvo.setCstNo(cntrCstNo); /* 고객번호 */
                    dvo.setCntrChPrgsStatCd("50"); /* 계약변경진행상태코드 */
                    dvo.setChRcpUsrId(fstRgstUsrId); /* 변경접수사용자ID */
                    dvo.setAprDtm(fstRgstDtm); /* 승인일시 */
                    dvo.setAprUsrId(fstRgstUsrId); /* 승인사용자ID */
                    dvo.setCntrChFshDtm(fstRgstDtm); /* 계약변경완료일시 */

                    int baseRes = mapper.insertContractChangeRcpBase(dvo);
                    BizAssert.isTrue(baseRes > 0, "MSG_ALT_SVE_ERR");

                    baseRes = mapper.insertContractChangeRcpBaseHist(dvo);
                    BizAssert.isTrue(baseRes > 0, "MSG_ALT_SVE_ERR");

                    dvo.setCntrUnitTpCd("020"); /* 계약단위유형코드 */
                    dvo.setDtlCntrNo(cntrNo); /* 상세계약번호 */
                    dvo.setDtlCntrSn(Integer.parseInt(cntrSn)); /* 상세계약일련번호 */
                    dvo.setCntrNo(""); /* 계약번호 */
                    dvo.setCntrChAtcDvCd("10"); /* 계약변경항목구분코드 */
                    dvo.setChApyStrtdt(now); /* 변경적용시작일자 */
                    dvo.setChApyEnddt(endDt); /* 변경적용종료일자 */
                    dvo.setCntrChAkCn(cntrChAkCn); /* 계약변경요청내용 */
                    dvo.setCntrAdrpcId(cntrAdrpcId); /* 계약주소지ID */
                    dvo.setBfchCn(bfchCn); /* 변경전내용 */
                    dvo.setProcsYn("Y"); /* 처리여부 */
                    dvo.setProcsDuedt(now); /* 처리예정일자 */
                    dvo.setProcsFshDtm(fstRgstDtm); /* 처리완료일시 */
                    dvo.setRcgvpKnm(dvo.getIstNm());

                    int dtlRes = mapper.insertContractChangeRcpDtl(dvo);
                    BizAssert.isTrue(dtlRes > 0, "MSG_ALT_SVE_ERR");

                    dtlRes = mapper.insertContractChangeRcpDtlHist(dvo);
                    BizAssert.isTrue(dtlRes > 0, "MSG_ALT_SVE_ERR");

                    int edtRes = mapper.updateContractAdrpcBase(dvo);
                    BizAssert.isTrue(edtRes > 0, "MSG_ALT_SVE_ERR");

                    processCount += baseRes;
                    break;
                }
                case "3" -> {

                    String cntrChAkCn = String.format("세금계산서발행대상여부: %s", dvo.getPblYn());/* 계약변경요청내용 */
                    String bfchCn = String.format("세금계산서발행대상여부: %s", contractDvo.getTxinvPblOjYn()); /* 변경전내용 */

                    dvo.setCntrChRcpDtm(fstRgstDtm); /* 계약변경접수일자 */
                    dvo.setCntrChTpCd("205"); /* 계약변경유형코드 */
                    dvo.setChRqrDvCd("2"); /* 변경요청자구분코드 */
                    dvo.setChRqrNm(userNm); /* 변경요청자명 */
                    dvo.setCstNo(cntrCstNo); /* 고객번호 */
                    dvo.setCntrChPrgsStatCd("50"); /* 계약변경진행상태코드 */
                    dvo.setChRcpUsrId(fstRgstUsrId); /* 변경접수사용자ID */
                    dvo.setAprDtm(fstRgstDtm); /* 승인일시 */
                    dvo.setAprUsrId(fstRgstUsrId); /* 승인사용자ID */
                    dvo.setCntrChFshDtm(fstRgstDtm); /* 계약변경완료일시 */

                    int baseRes = mapper.insertContractChangeRcpBase(dvo);
                    BizAssert.isTrue(baseRes > 0, "MSG_ALT_SVE_ERR");

                    baseRes = mapper.insertContractChangeRcpBaseHist(dvo);
                    BizAssert.isTrue(baseRes > 0, "MSG_ALT_SVE_ERR");

                    dvo.setCntrUnitTpCd("020"); /* 계약단위유형코드 */
                    dvo.setDtlCntrNo(cntrNo); /* 상세계약번호 */
                    dvo.setDtlCntrSn(Integer.parseInt(cntrSn)); /* 상세계약일련번호 */
                    dvo.setCntrNo(""); /* 계약번호 */
                    dvo.setCntrChAtcDvCd("10"); /* 계약변경항목구분코드 */
                    dvo.setChApyStrtdt(now); /* 변경적용시작일자 */
                    dvo.setChApyEnddt(endDt); /* 변경적용종료일자 */
                    dvo.setCntrChAkCn(cntrChAkCn); /* 계약변경요청내용 */
                    dvo.setBfchCn(bfchCn); /* 변경전내용 */
                    dvo.setProcsYn("Y"); /* 처리여부 */
                    dvo.setProcsDuedt(now); /* 처리예정일자 */
                    dvo.setProcsFshDtm(fstRgstDtm); /* 처리완료일시 */

                    int dtlRes = mapper.insertContractChangeRcpDtl(dvo);
                    BizAssert.isTrue(dtlRes > 0, "MSG_ALT_SVE_ERR");

                    dtlRes = mapper.insertContractChangeRcpDtlHist(dvo);
                    BizAssert.isTrue(dtlRes > 0, "MSG_ALT_SVE_ERR");

                    int contractRes = mapper.updateContractDetail(dvo);
                    BizAssert.isTrue(contractRes > 0, "MSG_ALT_SVE_ERR");

                    contractRes = mapper.insertContractDetailHist(dvo);
                    BizAssert.isTrue(contractRes > 0, "MSG_ALT_SVE_ERR");

                    processCount += baseRes;
                    break;
                }
                case "4" -> {

                    String sellInflwChnlDtlCd = mapper
                        .selectSellInflwChannelDetailCd(dvo.getPrtnrNo(), dvo.getOgTpCd());

                    BizAssert.isTrue(
                        StringUtils.isNotEmpty(sellInflwChnlDtlCd), "MSG_ALT_NOT_EXIST_PLNNER_INFO_IT",
                        new String[] {messageResourceService.getMessage("MSG_TXT_SELL_INFLW_CHNL_DTL_CD")}
                    );

                    String sellTpOgCd = StringUtils.defaultIfEmpty(mapper.selectSellOgTpCd(cntrNo), "");

                    String cntrChAkCn = String
                        .format(
                            "판매파트너번호: %s|판매조직유형코드: %s|판매유입채널상세코드: %s", dvo.getPrtnrNo(), dvo.getOgTpCd(),
                            sellInflwChnlDtlCd
                        );/* 계약변경요청내용 */
                    String bfchCn = String.format(
                        "판매파트너번호: %s|판매조직유형코드: %s|판매유입채널상세코드: %s", dvo.getSellPrtnrNo(), sellTpOgCd,
                        sellInflwChnlDtlCd
                    ); /* 변경전내용 */

                    dvo.setSellInflwChnlDtlCd(sellInflwChnlDtlCd); /* 판매유입채널상세코드 */
                    dvo.setCntrChRcpDtm(fstRgstDtm); /* 계약변경접수일자 */
                    dvo.setCntrChTpCd("105"); /* 계약변경유형코드 */
                    dvo.setChRqrDvCd("2"); /* 변경요청자구분코드 */
                    dvo.setChRqrNm(userNm); /* 변경요청자명 */
                    dvo.setCstNo(cntrCstNo); /* 고객번호 */
                    dvo.setCntrChPrgsStatCd("50"); /* 계약변경진행상태코드 */
                    dvo.setChRcpUsrId(fstRgstUsrId); /* 변경접수사용자ID */
                    dvo.setAprDtm(fstRgstDtm); /* 승인일시 */
                    dvo.setAprUsrId(fstRgstUsrId); /* 승인사용자ID */
                    dvo.setCntrChFshDtm(fstRgstDtm); /* 계약변경완료일시 */

                    int baseRes = mapper.insertContractChangeRcpBase(dvo);
                    BizAssert.isTrue(baseRes > 0, "MSG_ALT_SVE_ERR");

                    baseRes = mapper.insertContractChangeRcpBaseHist(dvo);
                    BizAssert.isTrue(baseRes > 0, "MSG_ALT_SVE_ERR");

                    dvo.setCntrUnitTpCd("020"); /* 계약단위유형코드 */
                    dvo.setDtlCntrNo(cntrNo); /* 상세계약번호 */
                    dvo.setDtlCntrSn(1); /* 상세계약일련번호 */
                    dvo.setCntrNo(""); /* 계약번호 */
                    dvo.setCntrChAtcDvCd("10"); /* 계약변경항목구분코드 */
                    dvo.setChApyStrtdt(now); /* 변경적용시작일자 */
                    dvo.setChApyEnddt(endDt); /* 변경적용종료일자 */
                    dvo.setCntrChAkCn(cntrChAkCn); /* 계약변경요청내용 */
                    dvo.setBfchCn(bfchCn); /* 변경전내용 */
                    dvo.setProcsYn("Y"); /* 처리여부 */
                    dvo.setProcsDuedt(now); /* 처리예정일자 */
                    dvo.setProcsFshDtm(fstRgstDtm); /* 처리완료일시 */

                    int dtlRes = mapper.insertContractChangeRcpDtl(dvo);
                    BizAssert.isTrue(dtlRes > 0, "MSG_ALT_SVE_ERR");

                    dtlRes = mapper.insertContractChangeRcpDtlHist(dvo);
                    BizAssert.isTrue(dtlRes > 0, "MSG_ALT_SVE_ERR");

                    int contractRes = mapper.updateContractBase(dvo);
                    BizAssert.isTrue(contractRes > 0, "MSG_ALT_SVE_ERR");

                    contractRes = mapper.insertContractBaseHist(dvo);
                    BizAssert.isTrue(contractRes > 0, "MSG_ALT_SVE_ERR");

                    processCount += baseRes;
                    break;
                }
            }
        }
        return processCount;
    }
}
