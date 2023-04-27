package com.kyowon.sms.wells.web.bond.transfer.service;

import java.util.List;

import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondContractBasicHistReqDvo;
import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondTransferAssignDvo;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondContractBasicHistService;
import com.kyowon.sms.common.web.bond.transfer.service.ZbnaBondTransferAssignMgtService;
import com.kyowon.sms.common.web.bond.zcommon.constants.BnBondConst;
import com.kyowon.sms.wells.web.bond.transfer.converter.WbnaBondPartTransferConverter;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondPartTransferDvo;
import com.kyowon.sms.wells.web.bond.transfer.mapper.WbnaBondPartTransferMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 파트이관 조회,생성,저장 관련 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-02-17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WbnaBondPartTransferService {

    private final WbnaBondPartTransferMapper mapper;
    private final WbnaBondPartTransferConverter converter;
    private final ZbnaBondTransferAssignMgtService bondTransferAssignMgtService;
    private final ZbnaBondContractBasicHistService bondContractBasicHistService;

    /**
     * 파트이관 집계 정보 조회
     * @param dto 검색 조건
     * @return 파트이관 집계 조회 결과
     */
    public List<SearchRes> getPartTransferAggregation(SearchReq dto) {
        return mapper.selectPartTransferAggregation(dto);
    }

    /**
     * 파트이관 집계 상세 정보 조회
     * @param dto 검색 조건
     * @param pageInfo 페이징 처리 정보
     * @return PagingResult 페이지, 파트이관 집계 상세 목록
     */
    public PagingResult<SearchDetailRes> getPartTransferDetails(
        SearchDetailReq dto, PageInfo pageInfo
    ) {
        return mapper.selectPartTransferDetailPages(dto, pageInfo);
    }

    public SearchDetailSummaryRes getPartTransferDetailSummary(
        SearchDetailReq dto
    ) {
        return mapper.selectPartTransferDetailSummary(dto);
    }

    /**
     * 파트이관 집계 상세 정보 조회 엑셀다운로드 기준(페이징 없음)
     * @param dto 검색 조건
     * @return 파트이관 집계 상세 목록
     */
    public List<SearchDetailRes> getExcelDownload(
        SearchDetailReq dto
    ) {
        return mapper.selectPartTransferDetailPages(dto);
    }

    /**
     * 파트이관 정보 생성
     * @param dto 생성 요청 정보(기준년월, 사업부구분코드, 채권구분코드)
     * @return 갱신건수
     */
    @Transactional
    public int createPartTransfer(
        CreateReq dto
    ) {
        WbnaBondPartTransferDvo dvo = converter.mapCreateReqToWbnaBondPartTransferDvo(dto);

        //기존에 등록된 파트이관 있는 경우 우선 삭제
        mapper.deletePartTransfers(dvo);
        // TODO processCount 이 정보 활용 할 수 없을거 같음... 마무리 전 확인 후 가망 없으면 삭제
        int processCount = mapper.insertPartTransfers(dvo); //생성은 기준년월, 사업본부기준

        // 배정에 저장은 기준년월, 사업본부기준, 집금구분 기준
        ZbnaBondTransferAssignDvo bondTransferAssignDvo = converter.mapCreateReqToZbnaBondTransferAssignDvo(dto);
        // TODO 코드 유틸 찾지 못해서 상수로 작업 코드 유틸 확인 하면 추가 작업 예정
        bondTransferAssignDvo.setTfBizDvCd(BnBondConst.TfBizDvCd.PART_TRANSFER.getValue()); // 파트이관:01

        bondTransferAssignDvo.setClctamDvCd(BnBondConst.ClctamDvCd.SHORT_TERM.getValue()); // 단기
        bondTransferAssignDvo.setExcnSn(bondTransferAssignMgtService.getExcnSn(bondTransferAssignDvo));
        bondTransferAssignMgtService.createBondTransferAssign(bondTransferAssignDvo);

        bondTransferAssignDvo.setClctamDvCd(BnBondConst.ClctamDvCd.MID_TERM.getValue()); // 중기
        bondTransferAssignDvo.setExcnSn(bondTransferAssignMgtService.getExcnSn(bondTransferAssignDvo));
        bondTransferAssignMgtService.createBondTransferAssign(bondTransferAssignDvo);

        bondTransferAssignDvo.setClctamDvCd(BnBondConst.ClctamDvCd.LAWSUIT.getValue()); // 소송
        bondTransferAssignDvo.setExcnSn(bondTransferAssignMgtService.getExcnSn(bondTransferAssignDvo));
        bondTransferAssignMgtService.createBondTransferAssign(bondTransferAssignDvo);

        bondTransferAssignDvo.setClctamDvCd(BnBondConst.ClctamDvCd.EXECUTION.getValue()); // 집행
        bondTransferAssignDvo.setExcnSn(bondTransferAssignMgtService.getExcnSn(bondTransferAssignDvo));
        bondTransferAssignMgtService.createBondTransferAssign(bondTransferAssignDvo);

        ZbnaBondContractBasicHistReqDvo bondContractBasicHistReqDvo = converter
            .mapCreateReqToZbnaBondContractBasicHistReqDvo(dto); // TODO ZbnaBondContractBasicHistReqDvo 공통으로 사용할 dvo를 만들어서 교체 할지 정의
        bondContractBasicHistService.createBondContractHistorys(bondContractBasicHistReqDvo); // 배정기본 내역 저장

        mapper.updateCollectionPartForBndCntrBas(dvo); // 집금파트 정보 수정

        return processCount;
    }

    /**
     * 채권 집금구분 정보 갱신
     * @param dtos 갱신 요청 정보(기준년월, 사업부구분코드, 계약번호, 계약일련번호, 고객번호, 집금구분코드)
     * @return 갱신건수
     */
    @Transactional
    public int editPartTransferDetails(
        List<EditReq> dtos
    ) {
        int processCount = 0;
        for (EditReq dto : dtos) {
            // TODO 명세서 아직 갱신 중 계속 수정 되는 영역 에러 날 수 있음 에러시 명세서 맞춰 현행화 필요한 상태
            log.debug(dto.cntrNo());
            WbnaBondPartTransferDvo dvo = converter.mapEditReqToWbnaBondPartTransferDvo(dto);

            int result = mapper.updateBondContractBase(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            result = mapper.updateBondAssignItemization(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR"); // TODO 메시지 변경 필요(설계 혹은 공통 메시지 나오면 수정)

            processCount += result;
        }
        return processCount;
    }

    /**
     * 채권 배정 이력 확인(해당 년월 기준 배정 내역 존재 여부 확인)
     * @param baseYm 기준년월
     * @return 조회 결과 true:배정내역존재, false:배정내역미존재
     * @throws BizException 정상 조회 결과 아닌 경우 Exception 처리
     */
    public boolean hasPartTransfer(
        String baseYm
    ) throws BizException {

        //조회된 정보가 true, false에 해당하지 않는 경우 exception발생을 위해 switch 처리
        return switch (mapper.selectHasPartTransfer(baseYm)) {
            case 1 -> true;
            case 0 -> false;
            default -> throw new BizException("MSG_ALT_ERR_CONTACT_ADMIN");
        };
    }

    /**
     * 파트이관 생성전 기존 생성 이력 확인
     * @param dto 검색 조건(기준년월,사업부구분)
     * @return 조회 결과 true:생성이력존재, false:생성이력미존재
     * @throws BizException 정상 조회 결과 아닌 경우 Exception 처리
     */
    public boolean hasPartTransferDetail(
        SearchDetailReq dto
    ) throws BizException {

        //조회된 정보가 true, false에 해당하지 않는 경우 exception발생을 위해 switch 처리
        return switch (mapper.selectHasPartTransferDetail(dto)) {
            case 1 -> true;
            case 0 -> false;
            default -> throw new BizException("MSG_ALT_ERR_CONTACT_ADMIN");
        };
    }
}
