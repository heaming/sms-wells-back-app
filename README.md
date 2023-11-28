# K-STATION

교원그룹 영업관리시스템 차세대 프로젝트

## Contents

- [ABOUT](#ABOUT)
- [SAMPLE CODE](#SAMPLE-CODE)

## ABOUT

모든 고객이 머물면서 모든 것을 해결하는 '장소' **K-station**
**K-station**은 교원 그룹사(빨간펜, 구몬, 웰스, 라이프) 사업 부문의 모든 고객이 머물면서 모든 업무를 해결하는 '장소'로, 보다 더 빠른 서비스를 지원하기 위한 영업관리 플랫폼입니다.
분화되어 있던 시스템 및 DB를 하나로 통합하고, Biz/IT프로세스 개선과 설계로 변화와 고객 대응에 유연한 서비스를 제공합니다.

* 서비스/모바일 영역 개발
* 팀 내 맡은 역할 :
    - 백엔드 / 프론트엔드 개발
    - Sk렌터카 API - 웰스 영업차량 관리 인터페이스 개발 :
      채팅 등록 / 조회 / 수정 / 삭제
    - DB설계 :
      웰스팜 모종 관련 TABLE 설계 (상품 정보, 배송 스케쥴, 재고, 고객 방문 타임테이블 등)
    - Common :
      상품 검색, 웰스 매니저 & 엔지니어 조직 검색 컴포넌트 개발

## 프로젝트 일정

![img.png](img.png)

## 기술표준

* 개발 언어 :
    * BE : Open JDK 17.0.4(Zulu)
    * FE : JavaScript ES6
* Framework & 도구 :
    * BE : Spring Boot 2.6.11, Spring Framework 5.3.22, MyBatis 3.5.10, Junit 5.8.2, Lego Enterprise 2.0.0, Mave 3.8.6
    * FE : vue 3.2.25, vue-router 4.0.15, vuex 4.0.2, axios 0.27.2, quasar 2.6.6, Vite 2.9, RealGrid2
    * 형상관리 : Git, Gitlab

## SAMPLE CODE

> 1. 모종출하대차MAP
> 2. 일자별 설치재고 현황
> 3. 공통 컴포넌트 - 조직(엔지니어/매니저) 조회

### 1. 모종출하대차 MAP
![image](https://github.com/heaming/sms-wells-back-app/assets/85826542/73655055-557a-437f-8060-6452d56902ec)
![image](https://github.com/heaming/sms-wells-back-app/assets/85826542/56d2fbda-1955-4e6a-b70c-9e6282ab36f1)
![image](https://github.com/heaming/sms-wells-back-app/assets/85826542/4fcc9ac4-7acd-4ff3-b3db-d352bd9e0395)

* 기준일에 출고 확정된 모종 상품 + 집하 센터에서 추가 요청한 모종 상품 - 집하 센터에서 제외 요청한 모종 상품을 계산해 화면으로 보여주는 페이지
* 집계 현황을 통해 출고 차량에 실을 상품 라벨스티커를 출력할 수 있음
* 대표집하센터별 출고 요일이 정해져 있음
* cart 1대에 앞/뒤로 각각 16개의 상품을 실을 수 있음

<br/>
**1-1. dvo**

```java
/*
 * @title 모종 출하대차 MAP - WsnaSeedingTruckArrangementMapDvo
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
@Setter
public class WsnaSeedingTruckArrangementMapDvo {
    String dgLctCd;
    String dgLctNm;
    Integer truckNo;
    String cart1F;
    String cart1B;
    String cart2F;
    String cart2B;
    String cart3F;
    String cart3B;
    String cart4F;
    String cart4B;
    String cart5F;
    String cart5B;
    String cart6F;
    String cart6B;
    String cart7F;
    String cart7B;
    String cart8F;
    String cart8B;
    String cart9F;
    String cart9B;
}

/**
 * @title 모종 출하대차 MAP - WsnaSeedingTruckArrangementMapGgLctDvo
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
//@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
public class WsnaSeedingTruckArrangementMapGgLctDvo {
    private final String ggLctCd;
    private final String ggLctNm;
}

/**
 * @title 모종 출하대차 MAP - WsnaSeedingTruckArrangementMapSeedDvo
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
@Setter
@AllArgsConstructor
public class WsnaSeedingTruckArrangementMapSeedDvo {

    private String sdingPkgGrpCd;
    private String sdingPkgGrpCdNm;
    private Integer sdingPkgGrpqty;

}

```
<br/>
**1-2. dto**
```java
/* dto */

/**
 * @title 모종 출하대차 MAP dto
 * @author heymi.cho
 * @since 2023-09-07
 */

public class WsnaSeedingTruckArrangementMapDto {

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchReq")
    public record SearchReq(
        @NotNull
        String baseDt
    ) {
    }

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchTodayGgLct")
    public record SearchTodayGgLct(
        String dgGgLctCd,
        String dgGgLctNm
    ) {
    }

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedAgg")
    public record SearchSeedAgg(
        String dgGgLctCd,
        String dgGgLctNm,
        String sdingPkgGrpCd,

        String sdingPkgGrpCdNm,

        Integer sdingQty
    ) {
    }

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedTotalRes")
    public record SearchSeedTotalRes(
        Integer pak01,
        Integer pak02,
        Integer pak03,
        Integer pak04,
        Integer pak05,
        Integer pak13,
        Integer pak23,
        Integer pak50,
        Integer pak08,
        Integer pak09,
        Integer pak12,
        Integer pak51,
        Integer pak52,
        Integer pak53,
        Integer pak54,
        Integer pak55,
        Integer pak56,
        Integer pak57,
        Integer pak58,
        Integer pak59,
        Integer pak60,
        Integer pak24,
        Integer pak28,
        Integer pak29,
        Integer pak30,
        Integer pak31
    ) {
    }

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchRes")
    public record SearchRes(
        List<SearchSeedAgrgRes> seedAgrgRes,
        SearchSeedTotalRes seedTotalRes
    ) {
    }

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchLabelRes")
    public record SearchLabelRes(
        String dgLctNm,
        Integer cartNo,
        Integer truckNo,
        String basedt,
        String cartF,
        String cartB
    ) {
    }

    @Builder
    @ApiModel("WsnaSeedingTruckArrangementMapDto-SearchSeedAgrgRes")
    public record SearchSeedAgrgRes(
        String dgLctCd,
        String dgLctNm,
        Integer truckNo,
        String cart1F,
        String cart1B,
        String cart2F,
        String cart2B,
        String cart3F,
        String cart3B,
        String cart4F,
        String cart4B,
        String cart5F,
        String cart5B,
        String cart6F,
        String cart6B,
        String cart7F,
        String cart7B,
        String cart8F,
        String cart8B,
        String cart9F,
        String cart9B

    ) {
    }

}

```
<br/>
**1-3. service**

```java

/**
 * @title 모종 출하대차 MAP
 * @author heymi.cho
 * @since 2023-09-07
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedingTruckArrangementMapService {

    private final WsnaSeedingTruckArrangementMapMapper mapper;

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
                            break;
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

            );
            result.add(searchSeedAgrgRes);

        }
        Collections.sort(result, Comparator.comparing(SearchSeedAgrgRes::dgLctCd));

        return new WsnaSeedingTruckArrangementMapDto.SearchRes(result, seedTotalRes);
    }

    /**
     * 모종 출하대차 MAP 라벨스티커 생성
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
                } else if (sdQtyInTruck + seed.getSdingPkgGrpqty() > 16) {
                    int left = 16 - sdQtyInTruck;

                    if (left > 0) {
                        cart.add(seed.getSdingPkgGrpCdNm() + "\t(" + left + ")");
                        seed.setSdingPkgGrpqty(seed.getSdingPkgGrpqty() - left);
                        sdQtyInTruck += left;
                    }

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

                    if (fb.equals("B") && !seedQtyQue.isEmpty()) {
                        truckNo++;
                    }
                    fb = fb.equals("F") ? "B" : "F";
                    cart.clear();
                }
            }
            int finalTruckNo = truckNo;
            result = result.stream().map(
                item -> {
                    if (item.dgLctNm().equals(ggLct.getGgLctNm())) {
                        item = new SearchLabelRes(
                            item.dgLctNm(),
                            item.cartNo(),
                            finalTruckNo,
                            item.basedt(),
                            item.cartF(),
                            item.cartB()
                        );
                    }
                    return item;
                }
            ).collect(Collectors.toList());
        }

        return result;
    }
}

```
<br/>
**1-4. controller**

```java

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/seeding-truck-arrangement-map")
@Api(tags = "모종 출하대차 MAP REST API")
@RequiredArgsConstructor
@Validated
public class WsnaSeedingTruckArrangementMapController {

    private final WsnaSeedingTruckArrangementMapService service;

    @ApiOperation(value = "모종 출고센터 조회", notes = "요일별 출고센터를 조회한다")
    @GetMapping("/today-gglct")
    public List<SearchTodayGgLct> getTodayGgLct(
        SearchReq dto
    ) {
        return this.service.getTodayGgLct(dto);
    }

    @ApiOperation(value = "모종 출하대차 MAP", notes = "검색 조건을 입력받아 데이터를 조회한다.")
    @GetMapping("/map")
    public SearchRes getSeedingTruckArragementMap(
        SearchReq dto
    ) {
        return this.service.getSeedingTruckArragementMap(dto);
    }

    @ApiOperation(value = "모종 출하대차 MAP", notes = "검색 조건을 입력받아 데이터를 조회한다.")
    @GetMapping("/label")
    public List<SearchLabelRes> getSeedingTruckArrangementLabel(
        SearchReq dto
    ) {
        return this.service.getSeedingTruckArrangementLabel(dto);
    }
}


```
<br/>
**1-5.query(xml)**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedingTruckArrangementMapMapper">
    <select id="selectSeedAggregation"
            resultType="com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto$SearchSeedAgg">
        WITH BASE AS ( SELECT SDING_PKG_PD_CD AS SHIP_PKG_ID
        , T8.PD_NM AS SHIP_PKG /* 배송패키지 */
        , OG_NM AS VST_CENTER /* 방문센터 */
        , OG_ID AS VST_CENTER_ID
        , OG_CD AS VST_CENTER_CD
        , COUNT(*) AS CNT
        FROM ( SELECT D1.CNTR_NO
        , D1.CNTR_SN
        , D1.SV_BIZ_HCLSF_CD
        , D1.SV_BIZ_DCLSF_CD
        , D1.SDING_SPP_NO
        , D1.SDING_SPP_SN
        , D1.REFRI_DV_CD
        , D1.OSTR_CNFMDT
        , D1.ITM_IOST_DV_CD
        , D1.SDING_PKG_PD_CD
        , D5.PDCT_PD_CD
        , D5.REQD_DT
        , D2.RCPDT
        , D3.VST_CNFMDT
        , D1.OSTR_DUEDT
        , '' AS BS_FSH_DT
        , D1.DP_DT
        , D4.OG_CD
        , D4.OG_ID
        , D4.OG_NM
        , D4.OG_TP_CD
        , D4.PRTNR_NO
        , D1.MNGR_DV_CD
        , D1.DP_EPTT_NM
        , D1.RECAP_CS_AMT
        , D3.CST_SV_ASN_NO
        FROM TB_SVPD_SDING_SPP_PLAN_IZ D1 /* 모종배송계획내역 */
        INNER JOIN TB_SVPD_CST_SVAS_IST_OJ_IZ D2 /* 고객서비스AS설치대상내역 */
        ON D2.CNTR_NO = D1.CNTR_NO
        AND D2.CNTR_SN = D1.CNTR_SN
        AND D2.SV_BIZ_HCLSF_CD = D1.SV_BIZ_HCLSF_CD
        AND D2.SV_BIZ_DCLSF_CD = D1.SV_BIZ_DCLSF_CD
        AND D2.CST_SV_ASN_NO = D1.SDING_SPP_NO
        INNER JOIN TB_SVPD_CST_SVAS_IST_ASN_IZ D3 /* 고객서비스AS설치배정내역 */
        ON D3.CST_SV_ASN_NO = D2.CST_SV_ASN_NO
        INNER JOIN TB_OGBS_MM_PRTNR_IZ D4 /* 월파트너내역 */
        ON D4.OG_TP_CD = D3.ICHR_OG_TP_CD
        AND D4.PRTNR_NO = D3.ICHR_PRTNR_NO
        AND D4.BASE_YM = SUBSTR(#{baseDt}, 1, 6)
        INNER JOIN TB_SVPD_CST_SV_EXCN_IZ D5 /* 고객서비스수행내역 */
        ON D5.CNTR_NO = D2.CNTR_NO
        AND D5.CNTR_NO = D2.CNTR_NO
        INNER JOIN TB_PDBS_PD_ECOM_PRP_DTL D6 /* 상품각사속성상세 */
        ON D6.PD_CD = D2.PD_CD
        INNER JOIN TB_SSCT_CNTR_REL D7 /* 계약관계-(모종패키지 계약과 결합된) 웰스팜계약 */
        ON D7.BASE_DTL_CNTR_NO = D1.CNTR_NO
        AND D7.BASE_DTL_CNTR_SN = D1.CNTR_SN
        WHERE D1.DTA_DL_YN = 'N'
        AND D2.DTA_DL_YN = 'N'
        AND D3.DTA_DL_YN = 'N'
        AND D4.DTA_DL_YN = 'N'
        AND D5.DTA_DL_YN = 'N'
        AND D5.CNTR_DTL_STAT_CD IN ('101', '201', '202', '203') /* 정상, 고객요청정지, 연체정지, 해약접수정지 */
        AND D6.DTA_DL_YN = 'N'
        AND D6.PD_EXTS_PRP_GRP_CD = 'PART'
        AND D7.DTA_DL_YN = 'N'
        AND D7.CNTR_REL_DTL_CD = '216' /* 모종결합(웰스팜+모종) */
        AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN D7.VL_STRT_DTM AND D7.VL_END_DTM
        AND D1.CAN_DT IS NULL
        AND D5.REQD_DT IS NULL
        AND D6.PD_PRP_VAL20 = '11' /* 모종 */
        AND D6.PD_PRP_VAL19 IN ('3', '4') /* 모종, 상품 */
        AND D1.ITM_IOST_DV_CD = '1'
        AND D1.OSTR_CNFMDT BETWEEN #{baseDt} AND #{baseDt} /* 확정일자 */
        AND EXISTS ( SELECT 1
        FROM TB_PDBS_PD_REL S1
        INNER JOIN TB_PDBS_PD_ECOM_PRP_DTL S2
        ON S2.PD_CD = S1.BASE_PD_CD
        WHERE S1.DTA_DL_YN = 'N'
        AND S2.DTA_DL_YN = 'N'
        AND S2.PD_EXTS_PRP_GRP_CD = 'SPP'
        AND TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') BETWEEN S1.VL_STRT_DTM AND S1.VL_END_DTM
        AND S1.OJ_PD_CD = D2.PD_CD
        AND S2.PD_PRP_VAL12 = '1' )

        UNION ALL

        SELECT D1.CNTR_NO
        , D1.CNTR_SN
        , D1.SV_BIZ_HCLSF_CD
        , D1.SV_BIZ_DCLSF_CD
        , D1.SDING_SPP_NO
        , D1.SDING_SPP_SN
        , D1.REFRI_DV_CD
        , D1.OSTR_CNFMDT
        , D1.ITM_IOST_DV_CD
        , D1.SDING_PKG_PD_CD
        , D5.PDCT_PD_CD
        , D5.REQD_DT
        , SUBSTR(D2.FST_RGST_DTM, 1, 8) AS RCPDT
        , D3.VST_CNFMDT
        , D1.OSTR_DUEDT
        , D3.WK_EXCN_DT AS BS_FSH_DT
        , D1.DP_DT
        , D4.OG_CD
        , D4.OG_ID
        , D4.OG_NM
        , D4.OG_TP_CD
        , D4.PRTNR_NO
        , D1.MNGR_DV_CD
        , D1.DP_EPTT_NM
        , D1.RECAP_CS_AMT
        , D3.CST_SV_ASN_NO
        FROM TB_SVPD_SDING_SPP_PLAN_IZ D1
        INNER JOIN TB_SVPD_CST_SV_BFSVC_OJ_IZ D2
        ON D2.CNTR_NO = D1.CNTR_NO
        AND D2.CNTR_SN = D1.CNTR_SN
        AND D2.SV_BIZ_DCLSF_CD = D1.SV_BIZ_DCLSF_CD
        AND D2.CST_SV_ASN_NO = D1.SDING_SPP_NO
        INNER JOIN TB_SVPD_CST_SV_BFSVC_ASN_IZ D3 /* 고객서비스BS배정내역 */
        ON D3.CST_SV_ASN_NO = D2.CST_SV_ASN_NO
        LEFT OUTER JOIN TB_OGBS_MM_PRTNR_IZ D4 /* 월파트너내역 */
        ON D4.OG_TP_CD = D3.CNFM_PSIC_PRTNR_OG_TP_CD
        AND D4.PRTNR_NO = D3.CNFM_PSIC_PRTNR_NO
        AND D4.DTA_DL_YN = 'N'
        AND D4.BASE_YM = SUBSTR(#{baseDt}, 1, 6)
        INNER JOIN TB_SVPD_CST_SV_EXCN_IZ D5 /* 고객서비스수행내역 */
        ON D5.CNTR_NO = D2.CNTR_NO
        AND D5.CNTR_SN = D2.CNTR_SN
        INNER JOIN TB_PDBS_PD_ECOM_PRP_DTL D6 /* 상품각사속성상세 */
        ON D6.PD_CD = D2.PDCT_PD_CD
        WHERE D1.DTA_DL_YN = 'N'
        AND D1.SV_BIZ_HCLSF_CD = '2'
        AND D2.DTA_DL_YN = 'N'
        AND D3.DTA_DL_YN = 'N'
        AND D5.DTA_DL_YN = 'N'
        AND D5.CNTR_DTL_STAT_CD = '101' /* 정상 */
        AND D6.DTA_DL_YN = 'N'
        AND D6.PD_EXTS_PRP_GRP_CD = 'PART'
        AND D1.CAN_DT IS NULL
        AND D5.REQD_DT IS NULL
        AND D6.PD_PRP_VAL01 LIKE '60%'
        AND D1.OSTR_CNFMDT BETWEEN #{baseDt} AND #{baseDt} /* 확정일자 */
        AND D1.ITM_IOST_DV_CD = '1'
        AND D3.VST_PRGS_STAT_CD IN ('00', '10') /* 작업대기, 진행중 */
        AND D6.PD_PRP_VAL01 BETWEEN '60100' AND '60799'
        ) T1
        INNER JOIN TB_PDBS_PD_BAS T8 /* 상품기본-배송패키지 */
        ON T8.PD_CD = T1.SDING_PKG_PD_CD
        GROUP BY SDING_PKG_PD_CD
        , T8.PD_NM
        , OG_NM
        , OG_ID
        , OG_CD
        )
        , LCT_INF AS ( SELECT DISTINCT
        DG_GG_LCT_CD
        , CNR_OG_CD
        , DG_GG_LCT_NM
        FROM TB_SVPD_SDING_CNR_SPP_DOW_IZ
        WHERE #{baseDt} BETWEEN APY_STRTDT AND APY_ENDDT
        )
        , T_A AS ( SELECT DG_GG_LCT_CD
        , SDING_PKG_GRP_CD
        , SDING_PKG_GRP_CD_NM
        , DG_GG_LCT_NM
        , SUM(EXCD_QTY + SPMT_QTY) AS EXTRA_QTY
        FROM ( SELECT T2.DG_GG_LCT_CD /* 센터코드 */
        , T1.SDING_PKG_GRP_CD /* 모종패키지그룹코드 */
        , T1.SDING_PKG_GRP_CD_NM
        , LCT_INF.DG_GG_LCT_NM
        , SUM(T2.EXCD_QTY) * T1.SDING_PKG_APY_WTCF * -1 AS EXCD_QTY /* 제외수량 */
        , SUM(T2.SPMT_QTY) * T1.SDING_PKG_APY_WTCF AS SPMT_QTY /* 추가수량 */
        FROM ( SELECT SDING_PKG_CD /* 모종패키지코드 */
        , SDING_PKG_CD_NM /* 모종패키지코드명 */
        , SDING_PKG_APY_WTCF /* 모종패키지적용가중치 */
        , SDING_PKG_GRP_CD /* 모종패키지그룹코드 */
        , SDING_PKG_GRP_CD_NM /* 모종패키지그룹코드명 */
        , SDING_PD_CD /* 모종상품코드 */
        , ROW_NUMBER() OVER(PARTITION BY SDING_PKG_CD, SDING_PKG_GRP_CD, SDING_PKG_APY_WTCF ORDER BY SDING_PKG_CD) AS RN
        FROM TB_SVPD_SDING_PKG_ITM_IZ /* 모종패키지품목내역 */
        WHERE DTA_DL_YN = 'N'
        ) T1
        INNER JOIN ( SELECT S1.DEPT AS DG_GG_LCT_CD
        , S1.GDS AS SDING_PKG_CD
        , NVL(S2.EXCD_QTY, 0) AS EXCD_QTY
        , NVL(S2.SPMT_QTY, 0) AS SPMT_QTY
        FROM ( SELECT TEMP_DEPT.DEPT
        , TEMP_GDS.GDS
        FROM ( SELECT CD_VLD_VAL AS GDS
        FROM T_CMZ_CD_D
        WHERE DEL_YN = 'N'
        AND TENANT_ID = 'TNT_WELLS'
        AND CD_ID = 'SDING_PKG_CD'
        ) TEMP_GDS
        INNER JOIN ( SELECT CD_VLD_VAL AS DEPT
        FROM T_CMZ_CD_D
        WHERE DEL_YN = 'N'
        AND TENANT_ID = 'TNT_WELLS'
        AND CD_ID = 'GG_LCT_CD'
        UNION ALL
        SELECT '17' AS DEPT /* 택배 */
        FROM DUAL
        ) TEMP_DEPT
        ON 1 = 1
        ) S1
        LEFT OUTER JOIN ( SELECT DG_GG_LCT_CD /* 대표집하위치코드 */
        , SDING_PKG_CD /* 모종패키지코드 */
        , OSTR_DUEDT /* 출고예정일자 */
        , EXCD_QTY /* 제외수량 */
        , SPMT_QTY /* 추가수량 */
        FROM TB_SVPD_SDING_PKG_QTY_CTR_IZ /* 모종패키지수량조정내역 */
        WHERE DTA_DL_YN = 'N'
        AND OSTR_DUEDT = #{baseDt}
        ) S2
        ON S2.DG_GG_LCT_CD = S1.DEPT
        AND S2.SDING_PKG_CD = S1.GDS
        ) T2
        ON T2.SDING_PKG_CD = T1.SDING_PKG_CD
        INNER JOIN LCT_INF
        ON LCT_INF.DG_GG_LCT_CD = T2.DG_GG_LCT_CD
        WHERE T1.RN = 1
        GROUP BY T2.DG_GG_LCT_CD
        , T1.SDING_PKG_GRP_CD
        , T1.SDING_PKG_APY_WTCF
        , LCT_INF.DG_GG_LCT_NM
        , T1.SDING_PKG_GRP_CD_NM
        )
        GROUP BY DG_GG_LCT_CD
        , SDING_PKG_GRP_CD
        , SDING_PKG_GRP_CD_NM
        , DG_GG_LCT_NM
        )
        SELECT T_A.DG_GG_LCT_CD
        , T_A.DG_GG_LCT_NM
        , T_A.SDING_PKG_GRP_CD
        , T_A.SDING_PKG_GRP_CD_NM
        , SUM(QTY)+SUM(EXTRA_QTY) AS SDING_QTY
        FROM T_A
        INNER JOIN ( SELECT DG_GG_LCT_CD
        , DG_GG_LCT_NM
        , SHIP_PKG_ID
        , P1.*
        , BASE.CNT * SDING_PKG_APY_WTCF AS QTY
        FROM BASE
        INNER JOIN LCT_INF
        ON BASE.VST_CENTER_CD = LCT_INF.CNR_OG_CD
        INNER JOIN TB_SVPD_SDING_PKG_ITM_IZ P1 /* 모종패키지품목내역 */
        ON BASE.SHIP_PKG_ID = P1.SDING_PD_CD
        ) AGG
        ON T_A.DG_GG_LCT_CD = AGG.DG_GG_LCT_CD
        AND T_A.SDING_PKG_GRP_CD = AGG.SDING_PKG_GRP_CD
        GROUP BY T_A.DG_GG_LCT_CD
        , T_A.DG_GG_LCT_NM
        , T_A.SDING_PKG_GRP_CD
        , T_A.SDING_PKG_GRP_CD_NM
        ORDER BY T_A.SDING_PKG_GRP_CD
    </select>

    <select id="selectTodayGgLct"
            resultType="com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto$SearchTodayGgLct">
        SELECT DISTINCT
        DG_GG_LCT_CD
        , DG_GG_LCT_NM
        FROM TB_SVPD_SDING_CNR_SPP_DOW_IZ
        WHERE TO_NUMBER(TO_CHAR(TO_DATE(#{baseDt}, 'YYYYMMDD'), 'd')) = TO_NUMBER(DOW_DV_CD)+1
        ORDER BY DG_GG_LCT_CD
    </select>
</mapper>

```
<br/>
**1-6. Front Page**

```vue
<template>
  <kw-page>
    <kw-search
      @search="onClickSearch"
    >
      <kw-search-row>
        <kw-search-item
          :label="$t('MSG_TXT_BASE_DT')"
          required
        >
          <kw-date-picker
            v-model="searchParams.baseDt"
            :label="$t('MSG_TXT_BASE_DT')"
            type="date"
            rules="required"
          />
        </kw-search-item>
      </kw-search-row>
    </kw-search>

    <div class="result-area">
      <h3>{{ $t('MSG_TIT_SDING_TRUCK_AGRG_BY_PCK') }}</h3>
      <kw-grid
        ref="grdSubRef"
        name="grdSub"
        :visible-rows="1"
        class="mb5"
        @init="initGrid1"
      />
      <h3>{{ t('MSG_TIT_SDING_TRUCK_AGRG_BY_CNTER') }}</h3>
      <kw-action-top>
        <kw-btn
          icon="download_on"
          dense
          secondary
          :label="$t('MSG_BTN_EXCEL_DOWN')"
          :disable="totalCount===0"
          @click="onClickExcelDownload"
        />
        <kw-btn
          dense
          primary
          :label="$t('MSG_BTN_PRINT_LABEL')"
          :disable="totalCount===0"
          @click="onClickLabelDownload"
        />
      </kw-action-top>
      <!-- <ul class="filter-box mb12">
        <li class="filter-box__item">
          <p class="filter-box__item-label">
            {{ $t('MSG_TXT_DG_GG_LCT') }}
          </p>
          <kw-option-group
            dense
            type="checkbox"
            :options="codes.GG_LCT_CD"
            :model-value="filterCenters"
            @change="onChangeDgLct"
          />
        </li>
      </ul> -->
      <kw-grid
        ref="grdMainRef"
        name="grdMain"
        :total-count="totalCount"
        @init="initGrid"
      />
      <kw-grid
        ref="grdLabelRef"
        name="grdLabel"
        @init="initLabelGrid"
      />
    </div>
  </kw-page>
</template>

<script setup>
// -------------------------------------------------------------------------------------------------
// Import & Declaration
// -------------------------------------------------------------------------------------------------
import { getComponentType, gridUtil, useDataService, defineGrid } from 'kw-lib';
import { cloneDeep } from 'lodash-es';
import dayjs from 'dayjs';

const { t } = useI18n();
const now = dayjs();
// const { modal } = useGlobal();
const { currentRoute } = useRouter();
const dataService = useDataService();

// -------------------------------------------------------------------------------------------------
// Function & Event
// -------------------------------------------------------------------------------------------------

const grdMainRef = ref(getComponentType('KwGrid'));
const grdSubRef = ref(getComponentType('KwGrid'));
const grdLabelRef = ref(getComponentType('KwGrid'));
let cachedParams;
const totalCount = ref(0);

// const codes = await codeUtil.getMultiCodes(
//   'COD_PAGE_SIZE_OPTIONS',
//   'SDING_PKG_GRP_CD', // 모종패키지
//   'GG_LCT_CD', // 집하위치
// );

/*
 *  Search Parameter
 */
const searchParams = ref({
  fshProcsCd: '00', // 작업대기
  dtTpCd: '3', // 확정일자
  sppDvCd: '1', // 방문
  pkgDvCd: '1', // 모종
  baseDt: now.format('YYYYMMDD'),
});

/*
 * 필터링 - 보류
 */
// const filterCenters = ref([]);
// const onChangeDgLct = (val) => {
//   const view = grdMainRef.value.getView();
//   const dgLctCdFilterCond = codes.GG_LCT_CD.map((v) => ({ name: v.codeId, criteria: `value="${v.codeId}"` }));
//   view.setColumnFilters('dgLctCd', dgLctCdFilterCond);
//   console.log(dgLctCdFilterCond);
//   view.activateAllColumnFilters('dgLctCd', true);
//   if (dgLctCdFilterCond) {
//     const f = view.getActiveColumnFilters('dgLctCd');
//     console.log(f);
//   }

//   view.activateAllColumnFilters('dgLctCd', false);
//   console.log(view.getColumnFilters('dgLctCd'));

//   if (val) {
//     const f = view.getActiveColumnFilters('dgLctCd');
//     console.log(f);
//     view.activateColumnFilters('dgLctCd', 'dgLctCdFilterCond', true);
//   }
// };

/*
 *  Main Grid
 */
async function fetchData() {
  // eslint-disable-next-line max-len
  const res = await dataService.get('/sms/wells/service/seeding-truck-arrangement-map/map', { params: { ...cachedParams } });
  const viewMain = grdMainRef.value.getView();
  viewMain.getDataSource().setRows(res.data.seedAgrgRes);
  viewMain.resetCurrent();
  totalCount.value = res.data.seedAgrgRes.length;
  // await gridUtil.reset(viewMain);

  const viewSub = grdSubRef.value.getView();
  const temp = [];
  temp.push(res.data.seedTotalRes);
  viewSub.getDataSource().setRows(temp);

  // console.log(res.data.seedTotalRes);
  viewSub.resetCurrent();
  // await gridUtil.reset(viewSub);
}

/*
 *  Search - 조회
 */

async function onClickSearch() {
  cachedParams = cloneDeep(searchParams.value);
  console.log(cachedParams);
  await fetchData();
}

async function onClickExcelDownload() {
  const view = grdMainRef.value.getView();

  // eslint-disable-next-line max-len
  const res = await dataService.get('/sms/wells/service/seeding-truck-arrangement-map/map', { params: searchParams.value });

  await gridUtil.exportView(view, {
    fileName: currentRoute.value.meta.menuName,
    timePostfix: true,
    exportData: res.data.seedAgrgRes,
  });
}

async function onClickLabelDownload() {
  const view = grdLabelRef.value.getView();

  // eslint-disable-next-line max-len
  const res = await dataService.get('/sms/wells/service/seeding-truck-arrangement-map/label', { params: searchParams.value });
  view.getDataSource().setRows(res.data);
  view.resetCurrent();

  await gridUtil.exportView(view, {
    fileName: currentRoute.value.meta.menuName,
    timePostfix: true,
    exportData: res.data,
    searchCondition: false,
  });
}
/*
 * 필터 만들기
 */
// async function initGridRows() {
//   const view = grdMainRef.value?.getView();
//   if (view) {
//     const dgLctCdFilterCond = codes.GG_LCT_CD.map((v) => ({ name: v.codeId, criteria: `value='${v.codeId}'` }));

//     if (dgLctCdFilterCond) {
//       view.setColumnFilters('dgLctCd', dgLctCdFilterCond, true);
//     }

//     // view.getDataSource().setRows();
//   }
// }

// -------------------------------------------------------------------------------------------------
// Initialize Grid
// -------------------------------------------------------------------------------------------------

const initGrid = defineGrid((data, view) => {
  const columns = [
    { fieldName: 'dgLctCd', header: 'NO', width: '50', styleName: 'text-center kw-bc--bg-grid text-weight-medium' },
    { fieldName: 'dgLctNm', header: t('MSG_TXT_DG_GG_LCT'), width: '70', styleName: 'text-center kw-bc--bg-grid text-weight-medium' },
    { fieldName: 'truckNo', header: t(''), width: '70', styleName: 'text-center' },
    { fieldName: 'cart1', header: 'cart1', width: '150', styleName: 'text-left' },
    { fieldName: 'cart1F', header: 'cart1F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart1B', header: 'cart1B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart2', header: 'cart2', width: '150', styleName: 'text-left ' },
    { fieldName: 'cart2F', header: 'cart2F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart2B', header: 'cart2B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart3', header: 'cart3', width: '150', styleName: 'text-left' },
    { fieldName: 'cart3F', header: 'cart3F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart3B', header: 'cart3B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart4', header: 'cart4', width: '150', styleName: 'text-left' },
    { fieldName: 'cart4F', header: 'cart4F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart4B', header: 'cart4B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart5', header: 'cart5', width: '150', styleName: 'text-left' },
    { fieldName: 'cart5F', header: 'cart5F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart5B', header: 'cart5B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart6', header: 'cart6', width: '150', styleName: 'text-left' },
    { fieldName: 'cart6F', header: 'cart6F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart6B', header: 'cart6B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart7', header: 'cart7', width: '150', styleName: 'text-left' },
    { fieldName: 'cart7F', header: 'cart7F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart7B', header: 'cart7B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart8', header: 'cart8', width: '150', styleName: 'text-left' },
    { fieldName: 'cart8F', header: 'cart8F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart8B', header: 'cart8B', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart9', header: 'cart9', width: '150', styleName: 'text-left' },
    { fieldName: 'cart9F', header: 'cart9F', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cart9B', header: 'cart9B', width: '150', styleName: 'text-left multiline' },
  ];

  const fields = columns.map(({ fieldName }) => ({ fieldName }));
  // eslint-disable-next-line max-len
  // const fields = columns.map(({ fieldName, nanText }) => (nanText ? { fieldName, dataType: 'number' } : { fieldName }));
  data.setFields(fields);
  view.setColumns(columns);

  view.setColumnLayout([
    'dgLctCd',
    'dgLctNm',
    'truckNo',
    {
      name: 'cart1',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart1F',
        'cart1B',
      ],
      header: {
        text: 'cart1',
      },
    },
    {
      name: 'cart2',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart2F',
        'cart2B',
      ],
      header: {
        text: 'cart2',
      },
    },
    {
      name: 'cart3',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart3F',
        'cart3B',
      ],
      header: {
        text: 'cart3',
      },
    },
    {
      name: 'cart4',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart4F',
        'cart4B',
      ],
      header: {
        text: 'cart4',
      },
    },
    {
      name: 'cart5',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart5F',
        'cart5B',
      ],
      header: {
        text: 'cart5',
      },
    },
    {
      name: 'cart6',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart6F',
        'cart6B',
      ],
      header: {
        text: 'cart6',
      },
    },
    {
      name: 'cart7',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart7F',
        'cart7B',
      ],
      header: {
        text: 'cart7',
      },
    },
    {
      name: 'cart8',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart8F',
        'cart8B',
      ],
      header: {
        text: 'cart8',
      },
    },
    {
      name: 'cart9',
      direction: 'horizontal',
      // hideChildHeaders: true,
      items: [
        'cart9F',
        'cart9B',
      ],
      header: {
        text: 'cart9',
      },
    },
  ]);

  view.displayOptions.rowHeight = -1;
  view.fixedOptions.colCount = 2;
  view.filteringOptions.enabled = false;

  // initGridRows();
  // view.groupBy(['dgLctNm']);
  // view.rowIndicator.visible = true;
  // view.setRowGroup({ mergeMode: true });
});

const initGrid1 = defineGrid((data, view) => {
  const fields = [
    { fieldName: 'pak01', dataType: 'number' },
    { fieldName: 'pak02', dataType: 'number' },
    { fieldName: 'pak03', dataType: 'number' },
    { fieldName: 'pak04', dataType: 'number' },
    { fieldName: 'pak05', dataType: 'number' },
    { fieldName: 'pak13', dataType: 'number' },
    { fieldName: 'pak23', dataType: 'number' },
    { fieldName: 'pak50', dataType: 'number' },
    { fieldName: 'pak24', dataType: 'number' },
    { fieldName: 'pak08', dataType: 'number' },
    { fieldName: 'pak09', dataType: 'number' },
    { fieldName: 'pak12', dataType: 'number' },
    { fieldName: 'pak51', dataType: 'number' },
    { fieldName: 'pak52', dataType: 'number' },
    { fieldName: 'pak53', dataType: 'number' },
    { fieldName: 'pak54', dataType: 'number' },
    { fieldName: 'pak55', dataType: 'number' },
    { fieldName: 'pak56', dataType: 'number' },
    { fieldName: 'pak57', dataType: 'number' },
    { fieldName: 'pak58', dataType: 'number' },
    { fieldName: 'pak60', dataType: 'number' },
    { fieldName: 'pak28', dataType: 'number' },
    { fieldName: 'pak29', dataType: 'number' },
    { fieldName: 'pak30', dataType: 'number' },
    { fieldName: 'pak31', dataType: 'number' },
  ];

  const columns = [
    { fieldName: 'pak01',
      header: t('MSG_TXT_SALAD'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak02',
      header: t('MSG_TXT_VGT_DIET'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak03',
      header: t('MSG_TXT_HL_DINING_TBL'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak04',
      header: t('MSG_TXT_CANCER_HL'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak05',
      header: t('MSG_TXT_SLEEP_HEALING'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak13',
      header: t('MSG_TXT_CLD_FOOD'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak23',
      header: t('MSG_TXT_MINI'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak50',
      header: t('MSG_TXT_CLD_GWUP'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak24',
      header: t('MSG_TXT_CHO_SDING'),
      width: '100',
      styleName: 'text-right' },
    { fieldName: 'pak08',
      header: t('MSG_TXT_BUTTER_HEAD'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak09',
      header: t('MSG_TXT_KALE'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak12',
      header: t('MSG_TXT_VITAMINS'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak51',
      header: t('MSG_TXT_SATIVA'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak52',
      header: t('MSG_TXT_YUREUM'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak53',
      header: t('MSG_TXT_BOK_CHOY'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak54',
      header: t('MSG_TXT_SATIVA_YUREUM'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak55',
      header: t('MSG_TXT_SATIVA_BOK_CHOY'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak56',
      header: t('MSG_TXT_SATIVA_RED'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak57',
      header: t('MSG_TXT_YUREUM_BOK_CHOY'),
      width: '100',
      styleName: 'text-right m',
    },
    { fieldName: 'pak58',
      header: t('MSG_TXT_YEREUM_RED'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak60',
      header: `${t('MSG_TXT_FRDM_WIDE')}`,
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak28',
      header: t('MSG_TXT_EUROP'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak29',
      header: t('MSG_TXT_OU_FML'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak30',
      header: t('MSG_TXT_ASSORT_WRAPS'),
      width: '100',
      styleName: 'text-right',
    },
    { fieldName: 'pak31',
      header: t('MSG_TXT_FNC_VGT'),
      width: '100',
      styleName: 'text-right',
    },
  ];

  data.setFields(fields);
  view.setColumns(columns);
  view.header.height += view.header.height + 50;
  // data.setRows([
  //   { pak01: 13 },
  // ]);
});

const initLabelGrid = defineGrid((data, view) => {
  const columns = [
    // eslint-disable-next-line max-len
    { fieldName: 'dgLctNm', header: 'dgLctNm', width: '70', styleName: 'text-center kw-bc--bg-grid text-weight-medium' },
    { fieldName: 'baseDt', header: 'baseDt', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'truckNo', header: 'truckNo', width: '70', styleName: 'text-center' },
    { fieldName: 'cartNo', header: 'cartNo', width: '70', styleName: 'text-center' },
    { fieldName: 'cartF', header: 'cartF', width: '150', styleName: 'text-left multiline' },
    { fieldName: 'cartB', header: 'cartB', width: '150', styleName: 'text-left multiline' },

  ];

  // const fields = columns.map(({ fieldName }) => ({ fieldName }));
  // eslint-disable-next-line max-len
  const fields = columns.map(({ fieldName, nanText }) => (nanText ? { fieldName, dataType: 'number' } : { fieldName }));
  data.setFields(fields);
  view.setColumns(columns);
  view.setVisible(false);
});
</script>

```

### 2. 학생관리 - 성적


