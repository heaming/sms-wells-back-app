package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SearchRes;

import java.util.*;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaWarehouseOrganizationConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingTruckArrangementMapDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWarehouseOrganizationMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0138M01 창고조직 관리
 * W-SV-U-0175P01 창고조직 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022.12.08
 */
@Service
@RequiredArgsConstructor
public class WsnaSeedingTruckArrangementMapService {

    private final WsnaWarehouseOrganizationMapper mapper;
    private final WsnaWarehouseOrganizationConverter converter;

    /**
     * 창고조직관리 - 조회
     *
     * @param dto : { baseYm : 기준년월, wareDv : 창고구분, codeUseYn : 사용여부, wareLocaraCd : 창고지역코드}
     * @return 조회결과
     */

    public List<WsnaSeedingTruckArrangementMapDto.SearchRes> getSeedingTruckArragementMap(
        SearchReq dto
    ) {

        @Getter
        @Setter
        @AllArgsConstructor
        class GgLct {
            private String ggLctCd;
            private String ggLctNm;
        }

        @Getter
        @Setter
        @AllArgsConstructor
        class Seed {
            private String sdingPkgGrpCd;
            private String sdingPkgGrpNm;
            private Integer sdingPkgGrpqty;
        }

        List<WsnaSeedingTruckArrangementMapDvo> pkgReses = mapper.selectSeedingTruckArrangement(dto);
        //        K : ggLctCd
        Map<GgLct, Queue<Seed>> map = new HashMap<>();
        List<WsnaSeedingTruckArrangementMapDto.SearchRes> results = new ArrayList<>();

        // 서비스센터별 모종수량 리스트
        for (int i = 0; i < pkgReses.size(); i++) {
            WsnaSeedingTruckArrangementMapDvo pkgRes = pkgResList.get(i);
            GgLct ggLct = new GgLct(pkgRes.getGgLctCd(), pkgRes.getGgLctNm());
            Queue<Seed> sdingQty = map.getOrDefault(
                ggLct, new LinkedList<>()
            );
            sdingQty.add(new Seed(pkgRes.getSdingPkgGrpCd(), pkgRes.getSdingPkgGrpNm(), pkgRes.getSdingPkgGrpqty()));
            map.put(ggLct, sdingQty);
        }

        // 모종수량리스트 배차 : 센터별 최대 9대 / 1대 : 앞 16개 모종 / 뒤 16개
        for (GgLct ggLct : map.keySet()) {
            List<String> cart1F = null;
            List<String> cart1B = null;
            List<String> cart2F = null;
            List<String> cart2B = null;
            List<String> cart3F = null;
            List<String> cart3B = null;
            List<String> cart4F = null;
            List<String> cart4B = null;
            List<String> cart5F = null;
            List<String> cart5B = null;
            List<String> cart6F = null;
            List<String> cart6B = null;
            List<String> cart7F = null;
            List<String> cart7B = null;
            List<String> cart8F = null;
            List<String> cart8B = null;
            List<String> cart9F = null;
            List<String> cart9B = null;

            Queue<Seed> sdingQty = map.get(ggLct);
            List<String> cart = new ArrayList<>();
            int truckNo = 0;
            String fb = "F";

            int sdQtyInTruck = 0;

            while (!sdingQty.isEmpty()) {
                if (fb.equals("F")) {
                    truckNo++;
                }
                Seed seed = sdingQty.peek();

                if (sdQtyInTruck + seed.getSdingPkgGrpqty() <= 16) {
                    seed = sdingQty.poll();
                    cart.add(seed.getSdingPkgGrpNm() + "(" + seed.getSdingPkgGrpqty() + ") ");
                    sdQtyInTruck += seed.getSdingPkgGrpqty();
                } else if (sdQtyInTruck + seed.getSdingPkgGrpqty() > 16) {
                    int left = 16 - sdQtyInTruck;
                    cart.add(seed.getSdingPkgGrpNm() + " " + left + " ");
                    seed.setSdingPkgGrpqty(seed.getSdingPkgGrpqty() - left);
                    sdQtyInTruck += left;
                }

                if (sdQtyInTruck >= 16) {
                    switch (truckNo) {
                        case 1:
                            if (fb.equals("F")) {
                                cart1F = cart;
                            } else {
                                cart1B = cart;
                            }
                        case 2:
                            if (fb.equals("F")) {
                                cart2F = cart;
                            } else {
                                cart2B = cart;
                            }
                        case 3:
                            if (fb.equals("F")) {
                                cart3F = cart;
                            } else {
                                cart3B = cart;
                            }
                        case 4:
                            if (fb.equals("F")) {
                                cart4F = cart;
                            } else {
                                cart4B = cart;
                            }
                        case 5:
                            if (fb.equals("F")) {
                                cart5F = cart;
                            } else {
                                cart5B = cart;
                            }
                        case 6:
                            if (fb.equals("F")) {
                                cart6F = cart;
                            } else {
                                cart6B = cart;
                            }
                        case 7:
                            if (fb.equals("F")) {
                                cart7F = cart;
                            } else {
                                cart7B = cart;
                            }
                        case 8:
                            if (fb.equals("F")) {
                                cart8F = cart;
                            } else {
                                cart8B = cart;
                            }
                        case 9:
                            if (fb.equals("F")) {
                                cart9F = cart;
                            } else {
                                cart9B = cart;
                            }
                    }

                    sdQtyInTruck = 0;
                    fb = fb.equals("F") ? "B" : "F";
                    cart.clear();
                }
            }
            WsnaSeedingTruckArrangementMapDto.SearchRes searchRes = new WsnaSeedingTruckArrangementMapDto.SearchRes(
                ggLct.getGgLctCd(),
                ggLct.getGgLctNm(),
                truckNo,
                fb,
                cart1F,
                cart1B,
                cart2F,
                cart2B,
                cart3F,
                cart3B,
                cart4F,
                cart4B,
                cart5F,
                cart5B,
                cart6F,
                cart6B,
                cart7F,
                cart7B,
                cart8F,
                cart8B,
                cart9F,
                cart9B
            );
            results.add(searchRes);
        }

        return results;
    }

    /**
     * 창고조직관리 - 엑셀 다운로드
     *
     * @param dto : { baseYm : 기준년월, wareDv : 창고구분, codeUseYn : 사용여부, wareLocaraCd : 창고지역코드}
     * @return 조회결과
     */
    public List<SearchRes> getWarehouseOgsExcelDownload(SearchReq dto) {
        return this.mapper.selectWarehouseOgs(dto);
    }
}
