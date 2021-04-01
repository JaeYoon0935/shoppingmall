<template>
  <div class="home">
    <v-divider></v-divider>
    <v-row style="margin-top:50px;">
       <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
         <img :src="image($store.state.product[0].image)" style="margin-left:30%; width:400px;" >
        </v-col>
        <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
         <v-card style ="width:320px; height:330px; padding-left:30px; padding-top:25px">
            <v-row>
                <v-simple-table style="width:300px;">
                  <tr>
                    <td>제품명:</td>
                    <td>{{$store.state.product[0].name}}</td>
                  </tr>
                  <tr>
                    <td>제품가격:</td>
                    <td class="pa-3">{{priceToString($store.state.product[0].price)}}원</td>
                  </tr>
                  <tr>
                    <td>제품수량:</td>
                    <td class="pl-2">
                      <a style='width:30px; height:30px; background-color:rgb(180, 180, 180); color:black' href="#" @click="change(-1)">-</a>
                        <input style='width:30px; height:30px; background-color:white; border:1px solid black; text-align:center;' type='text' value='1' v-model="amount" readonly> 
                      <a style='width:30px; height:30px; background-color:rgb(180, 180, 180); color:black;' href="#" @click="change(1)">+</a>
                    </td>
                  </tr>
                  <tr style="height:30px;">
                    <td></td><td></td></tr>
                </v-simple-table>
            </v-row>
              <div class="total" style="height: 50px; width:260px;">
                    <div style="height: 50px; width:260px; ">
                      <div style="height: 50px;">총 주문 금액 :</div>
                      <div style="height: 50px; padding-left:10px">{{total(this.amount)}}원</div>
                    </div>
                  </div>
            <v-row>
              <v-btn class="ml-14 mt-7">즉시구매</v-btn>
              <v-btn class="ml-5 mt-7">장바구니</v-btn>
            </v-row>
         </v-card>
        </v-col>
        
    </v-row>
    <v-divider></v-divider>
    <v-row style="margin-top:50px; margin-bottom:50px;">
      <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
        <!-- <img :src="image($store.state.product[0].image)"> -->
            <img style="margin-left:30%;"
             :src="require('@/images/detail.jpg')">
      </v-col>
       <v-col
            cols="6"
            lg="6"
            sm="6"
            md="6"
          >
          <p style="text-align:left;">
            {{$store.state.product[0].text}}
          </p>
      </v-col>
    </v-row>
    <v-divider></v-divider>
    <v-row style="margin-top:50px; margin-left:15%; margin-bottom:1%" class="information">
      <!-- 아이콘 만드는 건 나중에 하기 -->
        <v-icon>alert-circle-outline</v-icon>구매시 주의사항
    </v-row>
    <v-row style="margin-left:15%; margin-bottom:1%" class="information">
        <v-icon>mdi-circle-medium</v-icon> 환불 및 교환 관련 규정
    </v-row>
    <v-row style="margin-left:15%; margin-bottom:5%" class="information">
        <v-icon>mdi-circle-medium</v-icon> 배송관련정보
    </v-row>
  </div>
</template>


<style scoped>
.home{
  width:100%; 
}
.v-data-table > .v-data-table__wrapper > table {
  width:90%!important;
}
.total > div{    
  background-color: white;
  border:1px gray solid;
  height:50px!important;
  display:flex;
  justify-content: center;
  line-height: 50px;
}
a { 
  display: inline-block; 
}
.v-application a {
  color:black;
}
.row {
  font-size:20px;
  font-weight: 500;       /* 글자 굵기 */
  color:rgb(0, 0, 0)!important;   
}
table tr td {
  font-size:20px;
  font-weight: 500;       /* 글자 굵기 */
  color:rgb(0, 0, 0);
}
.theme--light.v-data-table {
  /* transparent는 투명으로 만드는 속성이다. */
  background-color: transparent;
}
</style>

<script>
export default {
  created(){
      this.$store.dispatch('Product')
    },
  data () {
    return {     
      id:{
          id: this.$route.params.id, 
          },
      name: this.$store.state.product[0].name,
      price: this.$store.state.product[0].price,
      quantity: this.$store.state.product[0].quantity,
      text: this.$store.state.product[0].text,

      amount:1,
    }
  },
  methods:{
    priceToString(price) {
            return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        },
    change(num){
          this.amount += num;
          if(this.amount < 1) {
              alert("더 이상 줄일 수 없습니다.");
              this.amount = 1;
          }   
    },
    image(image){
      //경로를 조합해줄 메서드.
      if(image == null){
        return require('@/images/null.jpg');
      }
      return require('@/images/'+ image +'.jpg');
    },
    total(amount){
        return (amount * this.$store.state.product[0].price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }
  },
}

</script>