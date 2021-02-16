<template>
  <div class="about">
    <h2 class="pt-2">매출통계</h2>
      <table>
        <tr>
          <td><h5 style="margin-left:10px; margin-top:2px;">20XX-XX-XX일 매출현황</h5></td>
        </tr>
      </table>
      <v-data-table
        :headers="$store.state.sales_headers"
        :items="$store.state.saleslist"
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