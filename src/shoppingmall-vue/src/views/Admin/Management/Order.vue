<template>
  <div class="about">
    <h2 class="pt-2">주문내역</h2>
      <v-data-table
        :headers="$store.state.order_headers"
        :items="$store.state.orderlist"
        :items-per-page="10"
        class="elevation-1"
      >
       <template v-slot:item="row">
         <tr>     
           <td>
             {{row.item.id}}
           </td>
           <td>
             {{row.item.product}}
           </td>
           <td>
             {{row.item.user_id}}
           </td>
           <td>
             {{row.item.date}}
           </td>
           <td>
             {{priceToString(row.item.total_price)}}원
           </td>
           <td>
             {{row.item.state}}
           </td>
           <td>
              <v-btn dark small color="grey" router :to="{name: 'OrderDetail', params: {id: row.item.id}}">상세보기</v-btn>
           </td>
         </tr>
       </template>
  </v-data-table>

  </div>
</template>

<script>
  export default {
    created(){
      this.$store.dispatch('OrderList')
    },
    data () {
      return {     
      }
    },
    methods:{
      priceToString(price) {
         return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },
    }
  }
</script>