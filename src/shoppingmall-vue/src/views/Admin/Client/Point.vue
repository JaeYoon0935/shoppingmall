<template>
  <div class="about">
    <v-row>
      <v-col style="padding-top:0px; padding-bottom:25px;"><h2 class="pt-2">포인트관리</h2></v-col>
      <v-col class="mr-7 mt-4">
        <v-btn color="indigo lighten-1" absolute right router :to="{name:'PointAdd'}">
          <span class="white--text">포인트 내역추가</span>
        </v-btn>
      </v-col>
    </v-row>
    <v-data-table
      class="elevation-1"
       :headers="point_headers"
       :items="point"
     >
        <template v-slot:item="row">
         <tr>     
            <td>
              <span>{{row.item.username}}</span>
            </td>
            <td>
              <span>{{row.item.name}}</span>  
            </td>
            <td style="width:350px;">
              <span>{{row.item.content}}</span>
            </td>     
            <td>
              <span>{{pointToString(row.item.point)}}</span>
            </td>
              <td>
              <span>{{date(row.item.date)}}</span>
            </td>
            <td>
              <span>{{pointToString(row.item.total_point)}}</span>
            </td>
          </tr>
        </template>
     </v-data-table>
  </div>
</template>

<script>
import { mapState} from "vuex"
  export default {
    created(){
      this.$store.dispatch('Point')
    },
    computed: {
      ...mapState(["point","point_headers"]),
    },
    methods:{
      date(time){
        var year = time.slice(0, 4) +'년'+ time.slice(4,6) + '월' + time.slice(6,8)+'일 ' +time.slice(9,11)+ '시' + time.slice(11,13)+ '분' + time.slice(13,15) + '초';
        return year;
      },
       pointToString(price) {
         return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },
    }
  }
</script>