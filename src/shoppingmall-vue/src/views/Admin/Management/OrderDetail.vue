<template>
  <div class="about">
    <h2 class="pt-2">주문상세내역</h2>
    <table>
      <tr>
        <td><h6 class="pt-2 pr-5">주문번호 : {{$route.params.id}}</h6><td>
        <td><h6 class="pt-2">주문일자 : {{$store.state.orderDetailList.date}}</h6></td>
      </tr>
    </table>
    <v-data-table
        :headers="orderdetail_headers_1"
        :items="orderDetailList.orderdetail"
        :items-per-page="3"
        :footer-props="footerProps"
        class="elevation-1"
    >
      <template v-slot:item="row">
        <tr>
          <td style="width:200px; padding-left:35px;">
            <!--상품코드 -->
            {{row.item.od_id}}
          </td>
          <td style="width:420px;">
            <!-- 상품 이름과 사진 불러오기 -->
            <v-row style="display:flex; width:400px; align-items:center;"> 
              <v-col cols="5">상품명: {{row.item.product}}</v-col>
              <v-col cols="6"><img :src="image(row.item.image)"/></v-col>  
              <v-col cols="1"></v-col>
            </v-row>
          </td>
          <td style="padding-left:30px;">
              <span v-if="temp == row.item.od_id">
                <input :style="{width:'40px'}" v-model="count">개
              </span>
              <span v-else>{{row.item.count}}개</span>
          </td>
          <td style="text-align:left;">
              <span v-if="temp == row.item.od_id">
                <input :style="{width:'100px'}" v-model="price">원
              </span>
              <span v-else>가격: {{priceToString(row.item.price)}}원</span>
          </td>
          <td>
            <v-row style="display:flex;">
              <span v-if="temp != row.item.od_id">
                <v-card-actions class="justify-start">  
                  <v-btn dark small color="grey" class="ma-1" @click="Update(row.item)">수정</v-btn>
                  <v-btn dark small color="grey" class="ma-1" @click="OrderDetailDelete(row.item)">삭제</v-btn>
                </v-card-actions>
              </span>
              <span v-else>
                <v-card-actions class="justify-start">  
                  <v-btn dark small color="grey" class="ma-1"
                    @click="[OrderDetailUpdate({count, price, od_id, o_id}),reload()]">수정완료</v-btn>
                    <v-btn dark small color="grey" class="ma-1" @click="Update_cancle()">취소</v-btn>
                </v-card-actions>
              </span>
            </v-row>
           </td>
         </tr>
      </template>
    </v-data-table>
    <table class="detail" style="width:650px;">
      <tbody>
        <tr>     
          <th style="text-align:center; background-color:rgb(245, 245, 245);">최종결제금액</th>
          <td style="text-align:center;">   
            <!-- beforeUpdate 이후에 값이 들어옴으로, 랜더링시 오류를 방지하기 위해 v-if를 사용함.-->
            <span v-if="this.$store.state.temp != null">
              {{priceToString($store.state.orderDetailList.total_price)}}원
            </span>
          </td>
        </tr>
        <tr>
          <th style="text-align:center; background-color:rgb(245, 245, 245);">배송지</th>
          <td style="text-align:center;">
           <span v-if="this.$store.state.temp != null"> {{$store.getters.get_orderDetailList.user.address}}</span>
          </td>
        </tr>
        <tr>
            <th style="text-align:center; background-color:rgb(245, 245, 245);">받는사람</th>
            <td style="text-align:center;">
              <span v-if="this.$store.state.temp != null">{{this.$store.getters.get_orderDetailList.user.name}}</span>
            </td>
        </tr>
        <tr>
          <th style="text-align:center; background-color:rgb(245, 245, 245);">연락처</th>
          <td style="text-align:center;">
            <span v-if="this.$store.state.temp != null">{{this.$store.getters.get_orderDetailList.user.phone}}</span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.detail{
    height:300px;
    margin:70px 0 200px 130px;
    border:1px solid rgb(185, 185, 185);
}
.detail tr{
    border:1px solid rgb(185, 185, 185);
}
.detail tr td{
    border:1px solid rgb(185, 185, 185);
}
img{
  width:90%; height:100px;
}

</style>

<script>
import { mapState, mapActions } from "vuex"
import { mapGetters } from 'vuex'
  export default {
    created(){
      this.$store.dispatch('OrderDetail')
      this.$store.state.temp = this.$store.getters.get_orderDetailList.user
    },
    beforeUpdate(){
      console.log('beforeUpdate')
      this.$store.state.temp = this.$store.getters.get_orderDetailList.user
    },
    computed: {
      ...mapState(["orderDetailList","orderdetail_headers_1"]),
      ...mapGetters([
        'get_orderDetailList',
      ])
    },
    data () {
      return {     
        count:'',
        price:'',
        temp:' ',
        footerProps: {'items-per-page-options': [3, 6, 9, 12]}
      }
    },
    methods:{
      ...mapActions(["OrderDetailDelete"]),
      ...mapActions(["OrderDetailUpdate"]),
     image(image){ //경로를 조합해줄 메서드.
      if(image == null){ 
        return require('@/images/null.jpg');
      }
      return require('@/images/'+ image +'.jpg');
      },
     priceToString(price) {
         return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },
     Update(orderdetail) {
        var login_flag = this.$store.state.login_flag
        if(login_flag == true){

          // 시큐리티 설정 때문에 권한을 넘겨받는 부분이 그냥 로그인 했을때와 새로고침했을때 변수명에서 조금 차이가 난다.
          // 그래서 통일시키려면 작업이 많이 복잡하고 소요가 되므로 아래와 같이 처리하게 되었다.
          var login_auth = this.$store.state.Userinfo.User_auth[0]
          var refresh_auth = this.$store.state.Userinfo.User_auth[0].authority

          if (login_auth == 'ROLE_ADMIN' || refresh_auth == 'ROLE_ADMIN'){
                this.temp = orderdetail.od_id
                this.count = orderdetail.count
                this.price = orderdetail.price
                this.od_id = orderdetail.od_id
                this.o_id = orderdetail.o_id
            }else{
              alert('권한이 없습니다.')
            }
        }else{
          alert('권한이 없습니다.')
        }
      },
     Update_cancle: function(){
      this.temp = 0  
     },
     reload() {
      window.location.reload()
     }
  }
  }
</script>