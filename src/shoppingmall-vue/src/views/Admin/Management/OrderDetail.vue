<template>
  <div class="about">
    <h2 class="pt-2">주문상세내역</h2>
    <table>
      <tr>
        <td><h6 class="pt-2 pr-5">주문번호 :{{$route.params.id }}</h6><td>
        <td><h6 class="pt-2">주문일자 : 2020-12-23 </h6></td>
      </tr>
    </table>
      <v-data-table
        :headers="$store.state.orderdetail_headers_1"
        :items="$store.state.orderlist"
        :items-per-page="5"
        class="elevation-1"
      >
       <template v-slot:item="row">
         <tr>     
           <td>
             <!--상품은 주문상세내역 완료 후 받아오도록 하기 -->
             {{row.item.code}}
           </td>
           <td>
            <!-- 상품 이름과 사진 불러오기 -->
            {{row.item.product}}
            {{row.item.image}}
           </td>
           <td>
             {{row.item.count}}
           </td>
           <td>
             {{row.item.price}}
           </td>
         </tr>
       </template>
  </v-data-table>
  <table class="detail" style="width:650px">
      <tbody>
        <tr>     
          <th style="text-align:center; background-color:rgb(245, 245, 245);">최종결제금액</th>
          <td>999,999원</td>
        </tr>
        <tr>
          <th style="text-align:center; background-color:rgb(245, 245, 245);">배송지</th>
          <td>{{this.$store.state.orderDetailList[0].address}}</td>
        </tr>
        <tr>
          <th style="text-align:center; background-color:rgb(245, 245, 245);">받는사람</th>
          <td>{{this.$store.state.orderDetailList[0].name}}</td>
        </tr>
        <tr>
          <th style="text-align:center; background-color:rgb(245, 245, 245);">연락처</th>
          <td>{{this.$store.state.orderDetailList[0].phone}}</td>
        </tr>
    </tbody>
  </table>
  </div>
</template>

<style scoped>
.detail{
    height:300px;
    margin:50px 0 0 130px;
    border:1px solid rgb(185, 185, 185);
}
.detail tr{
    border:1px solid rgb(185, 185, 185);
}
.detail tr td{
    border:1px solid rgb(185, 185, 185);
}
</style>

<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('OrderDetail')
    },
    data () {
      return {     
      }
    },
    methods:{

    }
  }
</script>