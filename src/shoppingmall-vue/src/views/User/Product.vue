<template>
  <div class="home">
    <v-divider></v-divider>
    <v-row>
       <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
            <img :src="require('@/assets/null.jpg')">
        </v-col>
        <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
          <v-row style="height:250px">
              <v-simple-table>
                <tr>
                  <td>제품명:</td>
                  <td>준비중</td>
                </tr>
                <tr>
                  <td>제품가격:</td>
                  <td>100,000원</td>
                </tr>
                <tr>
                  <td>제품수량:</td>
                  <td>9999</td>
                </tr>
              </v-simple-table>
          </v-row>
          <v-row>
            <v-btn class="ma-3">즉시구매</v-btn>
            <v-btn class="ma-3">장바구니</v-btn>
          </v-row>
        </v-col>
    </v-row>
    <v-divider></v-divider>
    <v-row>
      <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
            <img :src="require('@/assets/null.jpg')">
      </v-col>
       <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
          <p style="text-align:left;">
            제품 상세설명 입니다.
          </p>
      </v-col>
    </v-row>
    <v-divider></v-divider>
    <v-row>
      구매시 주의사항
      
    </v-row>
    <v-row>
        <v-icon>mdi-circle-medium</v-icon> 환불 및 교환 관련 규정
    </v-row>
    <v-row>
        <v-icon>mdi-circle-medium</v-icon> 배송관련정보
    </v-row>
  </div>
</template>