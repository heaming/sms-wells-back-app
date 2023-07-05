package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrBasicChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrPrccchHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContractWellsDetailHistDvo;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractRegStep2Mapper;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegStep2Service {
    private final WctaContractRegStep2Mapper mapper;

    private final WctaContractRegService regService;
    private final WctzHistoryService historyService;

    public WctaContractRegDvo selectStepInfo(String cntrNo) {
        WctaContractRegStep2Dvo step2Dvo = new WctaContractRegStep2Dvo();
        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);
        step2Dvo.setBas(bas);
        List<WctaContractDtlDvo> dtls = regService.selectProductInfos(cntrNo);
        for (WctaContractDtlDvo dtl : dtls) {
            int cntrSn = dtl.getCntrSn();

            dtl.setPdCd(dtl.getBasePdCd());
            // 판매유형코드 등에 따라 select option 조회
            String sellTpCd = dtl.getSellTpCd();
            WctaContractDtlDvo sels = selectProductSelects(
                WctaContractDto.SearchPdSelReq.builder()
                    .sellInflwChnlDtlCd(bas.getSellInflwChnlDtlCd())
                    .pdCd(dtl.getBasePdCd())
                    .sellTpCd(sellTpCd)
                    .build()
            );

            // 정기배송인 경우 계약관계테이블의 계약관계상세코드 세팅
            if ("6".equals(sellTpCd)) {
                List<WctaContractRelDvo> rels = regService.selectContractRel(cntrNo);
                WctaContractRelDvo rel = rels.stream().filter((r) -> r.getBaseDtlCntrSn() == cntrSn).findFirst()
                    .orElseThrow();
                dtl.setCntrRelDtlCd(rel.getCntrRelDtlCd());
                dtl.setPkg(dtl.getPdCd());
                WctaContractDtlDvo m = dtls.stream().filter((d) -> d.getCntrSn() == rel.getOjDtlCntrSn()).findFirst()
                    .orElseThrow();
                List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> pkgs = selectWelsfHcfPkgs(m.getPdCd());
                pkgs.forEach((p) -> p.setCntrRelDtlCd(rel.getCntrRelDtlCd()));
                dtl.setPkgs(pkgs);
                dtl.setSdingCapsls(mapper.selectSdingCapsls(dtl.getPdCd()));
            }

            // select option 세팅(converter 사용?)
            dtl.setSvPdCds(sels.getSvPdCds());
            if ("1".equals(sellTpCd)) {
                dtl.setAlncmpCntrDrmVals(null);
                dtl.setSellDscrCds(sels.getSellDscrCds());
                dtl.setSellDscDvCds(sels.getSellDscDvCds());
                dtl.setFrisuBfsvcPtrmNs(sels.getFrisuBfsvcPtrmNs());
            } else if ("2".equals(sellTpCd)) {
                dtl.setStplPtrms(sels.getStplPtrms());
                dtl.setCntrPtrms(sels.getCntrPtrms());
                dtl.setRgstCss(sels.getRgstCss());
                dtl.setSellDscTpCds(sels.getSellDscTpCds());
                dtl.setSellDscrCds(sels.getSellDscrCds());
                dtl.setSellDscDvCds(sels.getSellDscDvCds());
            }

            // 계약상품관계 조회, 상품관계 유형코드가 기준-서비스("03")인 데이터가 있다면(서비스상품) 세팅
            WctaContractPdRelDvo svPdRel = regService.selectContractPdRel(cntrNo, cntrSn).stream()
                .filter((pdRel) -> pdRel.getPdRelTpCd().equals("03")).findFirst().orElse(null);
            if (ObjectUtils.isNotEmpty(svPdRel)) {
                dtl.setSvPdCd(svPdRel.getOjPdCd());
            }

            // 제휴상품 노출판단, dto가 record 라서...
            boolean existAlncPds = false;
            if ("2".equals(sellTpCd)) {
                if ("02".equals(bas.getCntrTpCd())) {
                    existAlncPds = mapper.isExistAlncPds(
                        WctaContractDto.SearchPdAmtReq.builder()
                            .pdCd(dtl.getBasePdCd())
                            .sellTpCd(sellTpCd)
                            .svPdCd(dtl.getSvPdCd())
                            .stplPtrm(dtl.getStplPtrm())
                            .sellDscTpCd(dtl.getSellDscTpCd())
                            .sellDscDvCd(dtl.getSellDscDvCd())
                            .sellDscrCd(dtl.getSellDscrCd())
                            .build()
                    );
                } else {
                    existAlncPds = mapper.isExistAlncPds(
                        WctaContractDto.SearchPdAmtReq.builder()
                            .pdCd(dtl.getBasePdCd())
                            .sellTpCd(sellTpCd)
                            .svPdCd(dtl.getSvPdCd())
                            .stplPtrm(dtl.getStplPtrm())
                            .sellDscTpCd(dtl.getSellDscTpCd())
                            .sellDscDvCd(dtl.getSellDscDvCd())
                            .build()
                    );
                }
            } else {
                existAlncPds = mapper.isExistAlncPds(
                    WctaContractDto.SearchPdAmtReq.builder()
                        .pdCd(dtl.getBasePdCd())
                        .sellTpCd(sellTpCd)
                        .svPdCd(dtl.getSvPdCd())
                        .build()
                );
            }
            dtl.setExistAlncPds(existAlncPds);

            // 계약가격산출내역 조회
            List<WctaContractPrcCmptIzDvo> prcCmptIzDvos = regService
                .selectContractPrcCmptIz(cntrNo, cntrSn);
            if (CollectionUtils.isNotEmpty(prcCmptIzDvos)) {
                WctaContractPrcCmptIzDvo prcCmptIz = prcCmptIzDvos.get(0);
                dtl.setPdPrcFnlDtlId(prcCmptIz.getPdPrcFnlDtlId());
                dtl.setVerSn(prcCmptIz.getVerSn());
                dtl.setFxamFxrtDvCd(prcCmptIz.getFxamFxrtDvCd());
                dtl.setCtrVal(prcCmptIz.getCtrVal());
                dtl.setPdPrcId(prcCmptIz.getPdPrcId());
            }

            // 계약WELLS상세 조회
            WctaContractWellsDtlDvo wellsDtlDvo = regService.selectContractWellsDtl(cntrNo, cntrSn);
            dtl.setFrisuBfsvcPtrmN(wellsDtlDvo.getFrisuBfsvcPtrmN());
            dtl.setFrisuAsPtrmN(wellsDtlDvo.getFrisuAsPtrmN());
        }
        step2Dvo.setDtls(dtls);
        return WctaContractRegDvo.builder()
            .step2(step2Dvo)
            .build();
    }

    public WctaContractRegStep2Dvo.PdAmtDvo selectProductPrices(WctaContractDto.SearchPdAmtReq dto) {
        List<WctaContractRegStep2Dvo.PdAmtDvo> pdAmts = mapper.selectProductPrices(dto);
        if (pdAmts.size() == 1) {
            WctaContractRegStep2Dvo.PdAmtDvo pdAmt = pdAmts.get(0);
            // 제휴상품 노출여부 판단
            pdAmt.setExistAlncPds(mapper.isExistAlncPds(dto));
            return pdAmt;
        } else {
            // 가격정보 없거나 2건 이상 시 결정할 수 없으므로 빈 객체 return
            return new WctaContractRegStep2Dvo.PdAmtDvo();
        }
    }

    public WctaContractDtlDvo selectProductSelects(WctaContractDto.SearchPdSelReq dto) {
        String pdCd = dto.pdCd();
        String sellTpCd = dto.sellTpCd();
        String sellInflwChnlDtlCd = dto.sellInflwChnlDtlCd();
        WctaContractDtlDvo dvo = WctaContractDtlDvo.builder()
            .svPdCds(mapper.selectProductServiceInfo(pdCd))
            .build();
        if ("1".equals(sellTpCd)) {
            // 일시불인 경우 제휴상품목록(렌탈은 화면에서 select 변경될 때마다 실시간 체크)
            dvo.setAlncmpCntrDrmVals(null);
            dvo.setSellDscrCds(mapper.selectProductDsrtsSpay(pdCd, sellTpCd, sellInflwChnlDtlCd));
            dvo.setSellDscDvCds(mapper.selectProductDsdvsSpay(pdCd, sellTpCd, sellInflwChnlDtlCd));
            dvo.setFrisuBfsvcPtrmNs(mapper.selectProductFrisuMshPtrms(pdCd, sellTpCd, sellInflwChnlDtlCd));
        } else if ("2".equals(sellTpCd)) {
            dvo.setStplPtrms(mapper.selectProductStplPtrms(pdCd, sellTpCd, sellInflwChnlDtlCd));
            dvo.setCntrPtrms(mapper.selectProductCntrPtrms(pdCd, sellTpCd, sellInflwChnlDtlCd));
            dvo.setRgstCss(mapper.selectProductRgstCss(pdCd, sellTpCd, sellInflwChnlDtlCd));
            dvo.setSellDscTpCds(mapper.selectProductDstps(pdCd, sellTpCd, sellInflwChnlDtlCd));
            dvo.setSellDscrCds(mapper.selectProductDsrtsRntl(pdCd, sellTpCd, sellInflwChnlDtlCd));
            dvo.setSellDscDvCds(mapper.selectProductDsdvsRntl(pdCd, sellTpCd, sellInflwChnlDtlCd));
        }
        return dvo;
    }

    private List<WctaContractRegStep2Dvo.PdClsfDvo> buildPdClsfs(String... kv) {
        List<WctaContractRegStep2Dvo.PdClsfDvo> pdClsfs = Lists.newArrayList();
        for (int i = 0; i < kv.length; i += 2) {
            pdClsfs.add(
                WctaContractRegStep2Dvo.PdClsfDvo.builder()
                    .pdClsfId(kv[i])
                    .pdClsfNm(kv[i + 1])
                    .products(Lists.newArrayList())
                    .build()
            );
        }
        return pdClsfs;
    }

    public WctaContractRegStep2Dvo selectProducts(String cntrNo, String pdFilter) {
        WctaContractBasDvo bas = regService.selectContractBas(cntrNo);

        WctaContractRegStep2Dvo pdDvo = new WctaContractRegStep2Dvo();
        List<WctaContractRegStep2Dvo.PdClsfDvo> pdClsfs = buildPdClsfs(
            "1", "정수기",
            "2", "청정기",
            "3", "비데",
            "4", "삼성가전",
            "5", "정기배송",
            "6", "기타",
            "7", "복합상품"
        );

        String sellInflwChnlDtlCd = bas.getSellInflwChnlDtlCd();

        List<WctaContractRegStep2Dvo.PdDvo> pds = mapper.selectProducts(sellInflwChnlDtlCd, pdFilter);
        for (WctaContractRegStep2Dvo.PdDvo pd : pds) {
            // TODO 로직 추가
            pdClsfs.stream().filter((pdClsf) -> pdClsf.getPdClsfId().equals(pd.getPdClsf())).findFirst().get()
                .getProducts().add(pd);
        }
        pdClsfs.removeIf((pdClsf) -> CollectionUtils.isEmpty(pdClsf.getProducts()));
        pdDvo.setPdClsf(pdClsfs);
        // pdDvo.setProducts(pds);
        return pdDvo;
    }

    public List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> selectWelsfHcfPkgs(String pdCd) {
        List<WctaContractRegStep2Dvo.PdWelsfHcfPkg> welsfHcfPkgs = mapper.selectWelsfHcfPkgs(pdCd);
        welsfHcfPkgs.forEach((pkg) -> {
            pkg.setSdingCapsls(mapper.selectSdingCapsls(pkg.getPdCd()));
        });
        return welsfHcfPkgs.stream().sorted(Comparator.comparing(WctaContractRegStep2Dvo.PdWelsfHcfPkg::getCodeId))
            .toList();
    }

    @Transactional
    public String saveContractStep2(WctaContractRegStep2Dvo dvo) {
        String now = DateUtil.todayNnow();
        String cntrNo = dvo.getBas().getCntrNo();
        // 0. 계약기본
        regService.updateCntrPrgsStatCd(cntrNo, CtContractConst.CNTR_PRGS_STAT_CD_TEMP_STEP2);

        historyService.createContractBasicChangeHistory(
            WctzCntrBasicChangeHistDvo.builder()
                .cntrNo(cntrNo)
                .build()
        );

        // 1. step 저장은 계약기본 제외하고 delete and insert
        regService.removeStep2Data(cntrNo);
        regService.removeStep3Data(cntrNo);
        regService.removeStep4Data(cntrNo);

        for (WctaContractDtlDvo dtl : dvo.getDtls()) {
            int cntrSn = dtl.getCntrSn();
            String sellTpCd = dtl.getSellTpCd();

            dtl.setCntrNo(cntrNo);
            dtl.setBasePdCd(dtl.getPdCd());
            dtl.setSvPrd(0l); // TODO 서비스 주기 조회해서 저장해야 할듯(콤보에 주기 정보 없으므로)
            dtl.setCntrwTpCd(""); // TODO 계약서유형코드
            dtl.setBlgCrpCd("D0");
            dtl.setRveCrpCd("D0");
            dtl.setCoCd("2000");
            dtl.setPdBaseAmt(0l); // TODO 20230616
            dtl.setSellAmt(Math.multiplyExact(dtl.getPdQty(), dtl.getFnlAmt()));
            dtl.setSppDuedt(""); // TODO 배송예정일자
            dtl.setRstlYn(""); // TODO 재약정여부

            // 정기배송(기기+모종캡슐)인 경우 기기의 계약기간, 약정기간 세팅
            if ("216".equals(dtl.getCntrRelDtlCd())) {
                WctaContractDtlDvo m = dvo.getDtls().stream().filter((d) -> d.getCntrSn() == cntrSn - 1).findFirst()
                    .orElseThrow();
                dtl.setCntrPtrm(m.getCntrPtrm());
                dtl.setStplPtrm(m.getStplPtrm());
            }

            if (sellTpCd.equals("1")) {
                // 일시불인 경우 계약금 없음
                dtl.setCntrAmt(null);
            }

            // 2-1. 계약상세
            mapper.insertCntrDtlStep2(dtl);
            // 2-2. 계약상세이력
            historyService.createContractDetailChangeHistory(
                WctzCntrDetailChangeHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histStrtDtm(now)
                    .build()
            );

            // 3. 계약상품관계 (기준상품에 서비스가 있는경우)
            mapper.selectPdSvcsInBasePd(dtl.getBasePdCd()).stream()
                .forEach(
                    (pdRel) -> {
                        pdRel.setCntrNo(cntrNo);
                        pdRel.setCntrSn(cntrSn);
                        pdRel.setVlStrtDtm(now);
                        pdRel.setVlEndDtm(CtContractConst.END_DTM);
                        pdRel.setPdQty(1l);
                        mapper.insertCntrPdRelStep2(pdRel);
                    }
                );
            if (StringUtils.isNotEmpty(dtl.getSvPdCd())) {
                String pdRelId = mapper.selectProductRelId(dtl.getBasePdCd(), dtl.getSvPdCd());
                mapper.insertCntrPdRelStep2(
                    WctaContractPdRelDvo.builder()
                        .cntrNo(cntrNo)
                        .cntrSn(cntrSn)
                        .pdRelId(pdRelId)
                        .vlStrtDtm(now)
                        .vlEndDtm(CtContractConst.END_DTM)
                        .ojPdCd(dtl.getSvPdCd())
                        .basePdCd(dtl.getBasePdCd())
                        .pdRelTpCd("03")
                        .pdQty(1l)
                        .build()
                );
            }
            // 3-2. 정기배송제품
            if (CollectionUtils.isNotEmpty(dtl.getSdingCapsls())) {
                dtl.getSdingCapsls().forEach((pdSdingCapsl -> {
                    mapper.insertCntrPdRelStep2(
                        WctaContractPdRelDvo.builder()
                            .cntrNo(cntrNo)
                            .cntrSn(cntrSn)
                            .pdRelId(pdSdingCapsl.getPdRelId())
                            .vlStrtDtm(now)
                            .vlEndDtm(CtContractConst.END_DTM)
                            .ojPdCd(pdSdingCapsl.getPartPdCd())
                            .basePdCd(dtl.getPdCd())
                            .pdRelTpCd(pdSdingCapsl.getPdRelTpCd())
                            .pdQty(pdSdingCapsl.getPartUseQty())
                            .build()
                    );
                }));
            }

            // 4. 계약관계 - 1+1, 다건구매할인, 복합상품구매, 법인다건구매(기기변경 제외)
            if (StringUtils.containsAny(
                dtl.getCntrRelDtlCd(),
                "216", "214", "215", "22P", "22M", "22W"
            )) {
                String ojCntrNo = StringUtils.defaultString(dtl.getOjCntrNo(), cntrNo);
                int ojCntrSn = ObjectUtils.isNotEmpty(dtl.getOjCntrSn()) ? dtl.getOjCntrSn() : cntrSn - 1;
                mapper.insertCntrRelStep2(
                    WctaContractRelDvo.builder()
                        .baseDtlCntrNo(cntrNo)
                        .baseDtlCntrSn(cntrSn)
                        .ojDtlCntrNo(ojCntrNo)
                        .ojDtlCntrSn(ojCntrSn)
                        .vlStrtDtm(now)
                        .vlEndDtm(CtContractConst.END_DTM)
                        .cntrRelDtlCd(dtl.getCntrRelDtlCd())
                        .cntrRelTpCd("20")
                        .cntrUnitTpCd("020")
                        .build()
                );
            }

            // 5. 기기변경내역

            /* TODO 프로모션, 사은품 정의되면 처리
            // 6. 계약프로모션내역(프로모션을 선택한 경우)
            mapper.insertCntrPmotIzStep2(
                WctaContractPmotIzDvo.builder()
                    .build()
            );
            // 7. 사은품접수내역(사은품이 있는 경우)
            mapper.insertFgptRcpIzStep2(
                WctaFgptRcpIzDvo.builder()
                    .build()
            );
             */

            // 8. 관계사제휴계약내역

            // 9-1. 계약가격산출내역
            mapper.insertCntrPrcCmptIzStep2(
                WctaContractPrcCmptIzDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .pdCd(dtl.getBasePdCd())
                    .pdPrcFnlDtlId(dtl.getPdPrcFnlDtlId())
                    .verSn(dtl.getVerSn())
                    .fxamFxrtDvCd(dtl.getFxamFxrtDvCd())
                    .ctrVal(dtl.getCtrVal())
                    .fnlVal(dtl.getFnlAmt())
                    .pdPrcId(dtl.getPdPrcId())
                    .build()
            );
            // 9-2. 계약가격산출변경이력
            historyService.createCntrPrccchHistory(
                WctzCntrPrccchHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histStrtDtm(now)
                    .build()
            );

            // 10. 계약WELLS상세
            mapper.insertCntrWellsDtlStep2(
                WctaContractWellsDtlDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .frisuBfsvcPtrmN(dtl.getFrisuBfsvcPtrmN())
                    .frisuAsPtrmN(dtl.getFrisuAsPtrmN())
                    .sellEvCd("") // TODO 판매행사코드
                    .ocoCpsBzsDvCd("") // TODO 타사보상업체코드
                    .build()
            );
            historyService.createContractWellsDetailChangeHistory(
                WctzContractWellsDetailHistDvo.builder()
                    .cntrNo(cntrNo)
                    .cntrSn(cntrSn)
                    .histStrtDtm(now)
                    .build()
            );
        }

        return cntrNo;
    }
}
