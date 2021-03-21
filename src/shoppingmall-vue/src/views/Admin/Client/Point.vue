<template>
  <div class="about">
    <v-row>
      <v-col><h2 class="pt-2">포인트관리</h2></v-col>
      <v-col class="mr-7 mt-4">
        <v-btn color="indigo lighten-1" absolute right router :to="{name:'ProductRegistration'}">
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
            <td>
              <span>{{row.item.content}}</span>
            </td>
            <td>
              <span>{{row.item.point}}</span>
            </td>
            <td>
              <span>{{row.item.total_point}}</span>
            </td>
          </tr>
        </template>
     </v-data-table>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('Point')
    },
    data () {
      return {     
        new_username: '',
        password: '',

      }
    },
    computed: {
      ...mapState(["point","point_headers"]),
    },

  }
</script>