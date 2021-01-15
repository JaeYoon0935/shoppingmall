<template>
  <div class="about">
    <h2 class="pt-2">주문상세내역</h2>
    <table>
      <tr>
        <td><h6 class="pt-2 pr-5">주문번호 :{{$route.params.id}}</h6><td>
        <td><h6 class="pt-2">주문일자 : {{$store.state.orderDetailList.date}} </h6></td>
      </tr>
    </table>
    <v-data-table
        :headers="$store.state.orderdetail_headers_1"
        :items="$store.state.orderDetailList.orderdetail"
        :items-per-page="3"
        class="elevation-1"
    >
      <template v-slot:item="row">
        <tr>
          <td style="width:200px; padding-left:35px;">
            <!--상품코드 -->
            {{row.item.id}}
          </td>
          <td style="width:420px;">
            <!-- 상품 이름과 사진 불러오기 -->
            <v-row style="display:flex; width:420px; align-items:center;"> 
              <v-col><img :src="image(row.item.image)"/></v-col>  
              <v-col>{{row.item.product}}</v-col>
            </v-row>
          </td>
          <td style="padding-left:30px;">
            {{row.item.count}}
          </td>
          <td style="text-align:left;">
              가격: {{priceToString(row.item.price)}}원
          </td>
          <td>
            <v-row style="display:flex;">
              <div>  
                <v-btn dark small color="grey" class="ma-2" @click="ProductUpdate(row.item)">수정</v-btn>
              </div>
              <div>
                <v-btn dark small color="grey" class="ma-2" @click="ProductDelete(row.item)">삭제</v-btn>
              </div>
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
            999,999원
          </td>
        </tr>
        <tr>
          <th style="text-align:center; background-color:rgb(245, 245, 245);">배송지</th>
          <td style="text-align:center;">
           <span v-if="this.$store.state.temp != null"> {{$store.getters.get_orderDetailList.user.address}}</span>
           <!-- <span v-if="this.$store.state.temp != null"> {{$store.state.orderDetailList.user.address}}</span> -->
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
    // mounted(){
    //   console.log('mount')
    //   // this.$store.state.temp = this.$store.getters.doneorderDetailList.user
    // },
    beforeUpdate(){
      console.log('beforeUpdate')
      this.$store.state.temp = this.$store.getters.get_orderDetailList.user
    },
    // updated(){
    //   console.log('updated')
    //   // this.$store.state.temp = this.$store.getters.doneorderDetailList.user
    // },
    computed: {
      ...mapGetters([
        'get_orderDetailList',
      ])
    },
    data () {
      return {     

      }
    },
    methods:{
     image(image){ //경로를 조합해줄 메서드.
      if(image == null){
        return require('@/assets/null.jpg');
      }
      return require('@/assets/'+ image +'.jpg');
      },
     priceToString(price) {
         return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },
    }
  }
</script>