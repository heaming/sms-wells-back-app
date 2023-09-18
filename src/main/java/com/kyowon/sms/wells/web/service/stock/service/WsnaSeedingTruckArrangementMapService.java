package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchSeedAgg;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchSeedAgrgRes;

import java.util.*;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchLabelRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchSeedTotalRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.SearchTodayGgLct;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingTruckArrangementMapGgLctDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingTruckArrangementMapSeedDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedingTruckArrangementMapMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * K-W-SV-U-0302M01 모종 출하대차 MAP
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedingTruckArrangementMapService {

    private final WsnaSeedingTruckArrangementMapMapper mapper;
    //    private final ArrayList<WsnaSeedingTruckArrangementMapSeedDvo> seedTotal;

    /**
     * 모종 출고 센터 조회
     * @param dto
     * @return
     */
    public List<SearchTodayGgLct> getTodayGgLct(
        SearchReq dto
    ) {
        return mapper.selectTodayGgLct(dto);
    }

    /**
     * 모종 출하대차 MAP 조회
     * @param dto
     * @return
     */
    public WsnaSeedingTruckArrangementMapDto.SearchRes getSeedingTruckArragementMap(
        SearchReq dto
    ) {
        List<SearchSeedAgrgRes> result = new ArrayList<>();
        List<SearchSeedAgg> seedAggs = mapper.selectSeedAggregation(dto);

        Map<WsnaSeedingTruckArrangementMapGgLctDvo, Queue<WsnaSeedingTruckArrangementMapSeedDvo>> aggMap = new HashMap<>();
        Map<String, Integer> seedTotalMap = new HashMap<>();

        // 센터별 모종수량 리스트
        for (int i = 0; i < seedAggs.size(); i++) {
            SearchSeedAgg seedAgg = seedAggs.get(i);
            WsnaSeedingTruckArrangementMapGgLctDvo ggLct = new WsnaSeedingTruckArrangementMapGgLctDvo(
                seedAgg.dgGgLctCd(), seedAgg.dgGgLctNm()
            );

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.getOrDefault(ggLct, new LinkedList<>());
            seedQtyQue.offer(
                new WsnaSeedingTruckArrangementMapSeedDvo(
                    seedAgg.sdingPkgGrpCd(), seedAgg.sdingPkgGrpCdNm(), seedAgg.sdingQty()
                )
            );

            aggMap.put(ggLct, seedQtyQue);

            seedTotalMap
                .put(
                    seedAgg.sdingPkgGrpCd(), seedTotalMap.getOrDefault(seedAgg.sdingPkgGrpCd(), 0) + seedAgg.sdingQty()
                );
        }

        // 모종별 합계
        //        for (String sdingPkgGrpCd : seedTotalMap.keySet()) {
        //            int qty = seedTotalMap.get(sdingPkgGrpCd);
        //            seedTotal.add(new WsnaSeedingTruckArrangementMapSeedDvo(sdingPkgGrpCd, sdingPkgGrpCd, qty));
        //        }

        // 모종별 합산
        SearchSeedTotalRes seedTotalRes = new SearchSeedTotalRes(
            seedTotalMap.getOrDefault("PAK01", 0),
            seedTotalMap.getOrDefault("PAK02", 0),
            seedTotalMap.getOrDefault("PAK03", 0),
            seedTotalMap.getOrDefault("PAK04", 0),
            seedTotalMap.getOrDefault("PAK05", 0),
            seedTotalMap.getOrDefault("PAK13", 0),
            seedTotalMap.getOrDefault("PAK23", 0),
            seedTotalMap.getOrDefault("PAK50", 0),
            seedTotalMap.getOrDefault("PAK08", 0),
            seedTotalMap.getOrDefault("PAK09", 0),
            seedTotalMap.getOrDefault("PAK12", 0),
            seedTotalMap.getOrDefault("PAK51", 0),
            seedTotalMap.getOrDefault("PAK52", 0),
            seedTotalMap.getOrDefault("PAK53", 0),
            seedTotalMap.getOrDefault("PAK54", 0),
            seedTotalMap.getOrDefault("PAK55", 0),
            seedTotalMap.getOrDefault("PAK56", 0),
            seedTotalMap.getOrDefault("PAK57", 0),
            seedTotalMap.getOrDefault("PAK58", 0),
            seedTotalMap.getOrDefault("PAK59", 0),
            seedTotalMap.getOrDefault("PAK60", 0),
            seedTotalMap.getOrDefault("PAK24", 0),
            seedTotalMap.getOrDefault("PAK28", 0),
            seedTotalMap.getOrDefault("PAK29", 0),
            seedTotalMap.getOrDefault("PAK30", 0),
            seedTotalMap.getOrDefault("PAK31", 0)
        );

        // 모종수량리스트 배차
        for (WsnaSeedingTruckArrangementMapGgLctDvo ggLct : aggMap.keySet()) {
            List<String> cart1F = new ArrayList<>();
            List<String> cart1B = new ArrayList<>();
            List<String> cart2F = new ArrayList<>();
            List<String> cart2B = new ArrayList<>();
            List<String> cart3F = new ArrayList<>();
            List<String> cart3B = new ArrayList<>();
            List<String> cart4F = new ArrayList<>();
            List<String> cart4B = new ArrayList<>();
            List<String> cart5F = new ArrayList<>();
            List<String> cart5B = new ArrayList<>();
            List<String> cart6F = new ArrayList<>();
            List<String> cart6B = new ArrayList<>();
            List<String> cart7F = new ArrayList<>();
            List<String> cart7B = new ArrayList<>();
            List<String> cart8F = new ArrayList<>();
            List<String> cart8B = new ArrayList<>();
            List<String> cart9F = new ArrayList<>();
            List<String> cart9B = new ArrayList<>();

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.get(ggLct);
            List<String> cart = new ArrayList<>();
            int truckNo = 1;
            String fb = "F";
            int sdQtyInTruck = 0;

            while (!seedQtyQue.isEmpty()) {
                //                if (fb.equals("F")) {
                //                    truckNo++;
                //                }
                WsnaSeedingTruckArrangementMapSeedDvo seed = seedQtyQue.peek();

                if (sdQtyInTruck + seed.getSdingPkgGrpqty() <= 16) {
                    seed = seedQtyQue.poll();
                    cart.add(seed.getSdingPkgGrpCdNm() + "(" + seed.getSdingPkgGrpqty() + ")");
                    sdQtyInTruck += seed.getSdingPkgGrpqty();
                } else if (sdQtyInTruck + seed.getSdingPkgGrpqty() > 16) {
                    int left = 16 - sdQtyInTruck;
                    cart.add(seed.getSdingPkgGrpCdNm() + "(" + left + ")");
                    seed.setSdingPkgGrpqty(seed.getSdingPkgGrpqty() - left);
                    sdQtyInTruck += left;
                }

                if (sdQtyInTruck >= 16 || seedQtyQue.isEmpty()) {
                    switch (truckNo) {
                        case 1:
                            if (fb.equals("F")) {
                                cart1F.addAll(cart);
                            } else {
                                cart1B.addAll(cart);
                            }
                            break;
                        case 2:
                            if (fb.equals("F")) {
                                cart2F.addAll(cart);
                            } else {
                                cart2B.addAll(cart);
                            }
                            break;
                        case 3:
                            if (fb.equals("F")) {
                                cart3F.addAll(cart);
                            } else {
                                cart3B.addAll(cart);
                            }
                            break;
                        case 4:
                            if (fb.equals("F")) {
                                cart4F.addAll(cart);
                            } else {
                                cart4B.addAll(cart);
                            }
                            break;
                        case 5:
                            if (fb.equals("F")) {
                                cart5F.addAll(cart);
                            } else {
                                cart5B.addAll(cart);
                            }
                            break;
                        case 6:
                            if (fb.equals("F")) {
                                cart6F.addAll(cart);
                            } else {
                                cart6B.addAll(cart);
                            }
                            break;
                        case 7:
                            if (fb.equals("F")) {
                                cart7F.addAll(cart);
                            } else {
                                cart7B.addAll(cart);
                            }
                            break;
                        case 8:
                            if (fb.equals("F")) {
                                cart8F.addAll(cart);
                            } else {
                                cart8B.addAll(cart);
                            }
                            break;
                        case 9:
                            if (fb.equals("F")) {
                                cart9F.addAll(cart);
                            } else {
                                cart9B.addAll(cart);
                            }
                        default:
                            break;
                    }

                    sdQtyInTruck = 0;
                    if (fb.equals("B") && !seedQtyQue.isEmpty()) {
                        truckNo++;
                    }
                    fb = fb.equals("F") ? "B" : "F";
                    cart.clear();
                }
            }

            SearchSeedAgrgRes searchSeedAgrgRes = new SearchSeedAgrgRes(
                ggLct.getGgLctCd(),
                ggLct.getGgLctNm(),
                truckNo,
                String.join("\n", cart1F),
                String.join("\n", cart1B),
                String.join("\n", cart2F),
                String.join("\n", cart2B),
                String.join("\n", cart3F),
                String.join("\n", cart3B),
                String.join("\n", cart4F),
                String.join("\n", cart4B),
                String.join("\n", cart5F),
                String.join("\n", cart5B),
                String.join("\n", cart6F),
                String.join("\n", cart6B),
                String.join("\n", cart7F),
                String.join("\n", cart7B),
                String.join("\n", cart8F),
                String.join("\n", cart8B),
                String.join("\n", cart9F),
                String.join("\n", cart9B)
                //                cart1F,
                //                cart1B,
                //                cart2F,
                //                cart2B,
                //                cart3F,
                //                cart3B,
                //                cart4F,
                //                cart4B,
                //                cart5F,
                //                cart5B,
                //                cart6F,
                //                cart6B,
                //                cart7F,
                //                cart7B,
                //                cart8F,
                //                cart8B,
                //                cart9F,
                //                cart9B
            );
            result.add(searchSeedAgrgRes);

        }
        Collections.sort(result, Comparator.comparing(SearchSeedAgrgRes::dgLctCd));

        return new WsnaSeedingTruckArrangementMapDto.SearchRes(result, seedTotalRes);
    }

    /**
     * 모종 출하대차 MAP 조회
     * @param dto
     * @return
     */
    public List<SearchLabelRes> getSeedingTruckArrangementLabel(
        SearchReq dto
    ) {
        List<SearchLabelRes> result = new ArrayList<>();

        List<SearchSeedAgg> seedAggs = mapper.selectSeedAggregation(dto);
        Map<WsnaSeedingTruckArrangementMapGgLctDvo, Queue<WsnaSeedingTruckArrangementMapSeedDvo>> aggMap = new HashMap<>();

        // 센터별 모종수량 리스트
        for (int i = 0; i < seedAggs.size(); i++) {
            SearchSeedAgg seedAgg = seedAggs.get(i);
            WsnaSeedingTruckArrangementMapGgLctDvo ggLct = new WsnaSeedingTruckArrangementMapGgLctDvo(
                seedAgg.dgGgLctCd(), seedAgg.dgGgLctNm()
            );

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.getOrDefault(ggLct, new LinkedList<>());
            seedQtyQue.offer(
                new WsnaSeedingTruckArrangementMapSeedDvo(
                    seedAgg.sdingPkgGrpCd(), seedAgg.sdingPkgGrpCdNm(), seedAgg.sdingQty()
                )
            );

            aggMap.put(ggLct, seedQtyQue);

        }
        // 센터 [샐러드 16, 야채 15]

        // 배차
        for (WsnaSeedingTruckArrangementMapGgLctDvo ggLct : aggMap.keySet()) {

            Queue<WsnaSeedingTruckArrangementMapSeedDvo> seedQtyQue = aggMap.get(ggLct); // [샐러드 16, 야채 15]

            List<String> cartF = new ArrayList<>();
            List<String> cartB = new ArrayList<>();
            List<String> cart = new ArrayList<>(); // temp
            int truckNo = 1;
            String fb = "F";
            int sdQtyInTruck = 0; // 16개 되는지 확인

            // 차 앞||뒤 16개 채우기
            while (!seedQtyQue.isEmpty()) {
                WsnaSeedingTruckArrangementMapSeedDvo seed = seedQtyQue.peek();

                if (sdQtyInTruck + seed.getSdingPkgGrpqty() <= 16) {
                    seed = seedQtyQue.poll();
                    cart.add(seed.getSdingPkgGrpCdNm() + "\t(" + seed.getSdingPkgGrpqty() + ")");
                    sdQtyInTruck += seed.getSdingPkgGrpqty();
                    continue;
                } else if (sdQtyInTruck + seed.getSdingPkgGrpqty() > 16) {
                    int left = 16 - sdQtyInTruck;
                    cart.add(seed.getSdingPkgGrpCdNm() + "\t(" + left + ")");
                    seed.setSdingPkgGrpqty(seed.getSdingPkgGrpqty() - left);
                    sdQtyInTruck += left;
                }

                if (sdQtyInTruck >= 16 || seedQtyQue.isEmpty()) {
                    if (fb.equals("F")) {
                        cartF.addAll(cart);

                        if (seedQtyQue.isEmpty()) {
                            SearchLabelRes labelRes = new SearchLabelRes(
                                ggLct.getGgLctNm(),
                                truckNo,
                                0,
                                dto.baseDt(),
                                String.join("\n", cartF),
                                ""
                            );
                            result.add(labelRes);
                            cartF.clear();
                            cartB.clear();
                        }
                    } else {
                        cartB.addAll(cart);

                        SearchLabelRes labelRes = new SearchLabelRes(
                            ggLct.getGgLctNm(),
                            truckNo,
                            0,
                            dto.baseDt(),
                            String.join("\n", cartF),
                            String.join("\n", cartB)
                        );
                        result.add(labelRes);
                        cartF.clear();
                        cartB.clear();
                    }

                    sdQtyInTruck = 0;
                    System.out.println(seedQtyQue);
                    if (fb.equals("B") && !seedQtyQue.isEmpty()) {
                        truckNo++;
                    }
                    fb = fb.equals("F") ? "B" : "F";
                    cart.clear();
                }

                //                if (seedQtyQue.isEmpty()) {

                //                }
            }
            int finalTruckNo = truckNo;
            result.stream()
                .filter(item -> item.dgLctNm().equals(ggLct.getGgLctNm())).map(
                    item -> new SearchLabelRes(
                        item.dgLctNm(),
                        item.cartNo(),
                        finalTruckNo,
                        item.basedt(),
                        item.cartF(),
                        item.cartB()
                    )
                );
        }

        return result;
    }
}
