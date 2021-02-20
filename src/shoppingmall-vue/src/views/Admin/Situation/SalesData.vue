<template>
  <div class="about">
    <h2 class="pt-2">매출통계</h2>
      <table>
        <tr>
          <span v-if="$route.params.date2 == null">
            <td><h5 style="margin-left:10px; margin-top:2px;">{{$route.params.date1}}일 매출현황</h5></td>
          </span>
          <span v-if="$route.params.date2 != null && 8< $route.params.date1.length">
            <td><h5 style="margin-left:10px; margin-top:2px;">{{$route.params.date1}}일 ~ {{$route.params.date2}}일 매출현황</h5></td>
          </span>
          <span v-if=" 5< $route.params.date1.length && $route.params.date1.length <8">
            <td><h5 style="margin-left:10px; margin-top:2px;">{{$route.params.date1}}월 ~ {{$route.params.date2}}월 매출현황</h5></td>
          </span>    
          <span v-if="$route.params.date1.length <5">
            <td><h5 style="margin-left:10px; margin-top:2px;">{{$route.params.date1}}년 ~ {{$route.params.date2}}년 매출현황</h5></td>
          </span>          
        </tr>
      </table>
      <v-data-table
        :headers="$store.state.sales_headers"
        :items="$store.state.salesdata"
        :items-per-page="10"
        class="elevation-1"
      >
       <template v-slot:item="row">
         <tr>     
           <td style="width:150px;">
             {{row.item.id}}
           </td>
           <td>
             {{row.item.date}}
           </td>
           <td>
             {{row.item.user_id}}
           </td>
           <td>
             {{row.item.user.name}}
           </td>
           <td>
             {{row.item.user.phone}}
           </td>
           <td>
             {{priceToString(row.item.order_price)}}원
           </td>
           <td>
              <v-btn dark small color="grey" router :to="{name: 'OrderDetail', params: {id: row.item.id}}">상세보기</v-btn>
           </td>
         </tr>
       </template>
  </v-data-table>
  
  <table class="detail">
    <tbody>
      <tr>
        <td style="background-color:rgb(245, 245, 245);">최종합계</td>
        <td style="background-color:rgb(245, 245, 245);">{{priceToString($store.state.salesdata[0].total_price)}}원</td>
      </tr>
    </tbody>
  </table>
  </div>
</template>

<style scoped>
.detail{
    width:650px;
    height:110px;
    margin:70px 0 200px 130px;
    border:1px solid rgb(185, 185, 185);
}
.detail tr{
    border:1px solid rgb(185, 185, 185);
}
.detail tr td{
    text-align: center;
    border:1px solid rgb(185, 185, 185);
    font-size:20px;
    font-weight:600;
}
</style>

<script>
export default {
    created(){
      this.$store.dispatch('SalesData')
    },
    methods:{
    priceToString(price) {
      return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  }
}
</script>