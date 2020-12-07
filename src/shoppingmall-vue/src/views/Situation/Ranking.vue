<template>
  <div class="about">
    <h1>판매순위 페이지</h1>
      <v-data-table
        :headers="$store.state.ranking_header"
        :items="$store.state.ranking"
        :items-per-page="5"
        class="elevation-1"
      ></v-data-table>

        <v-row no-gutters style="height: 60px;"></v-row>

         <v-row no-gutters>
            <v-col  style="text-align: right" :cols="1">항목설정:</v-col>
            <v-col :cols="11">
              <v-combobox
                  v-model="select"
                  :items="items"
                  label="분류명"
                  multiple
                  outlined
                  dense
                  style = 'width:130px;'
                ></v-combobox>
            </v-col>
         </v-row>

         <v-row no-gutters style="height: 20px;"></v-row>

         <v-row no-gutters>
            <v-col  style="text-align: right" :cols="1">판매시기설정:</v-col>
            <v-col :cols="2">
              <v-combobox
                :items="year"
                label="연도"
                multiple
                outlined
                dense
                style = 'width:130px;'
              ></v-combobox>   
              <v-combobox
                :items="month"
                label="월"
                multiple
                outlined
                dense
                style = 'width:140px;'
              ></v-combobox>
            </v-col>
            <v-col :cols="9">
                <label>전체기간으로 조회</label> <input type="checkbox" style='zoom:1.3;'> 
            </v-col>
         </v-row>
  </div>
</template>


<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('Ranking')
    },
    data () {
      return {     
        items:[
          '방등',
          '거실등',
        ],
        year:[
          '2015년',
          '2016년',
          '2017년',
          '2018년',
          '2019년',
          '2020년',
        ],
        month:[
          '1월',
          '2월',
          '3월',
          '4월',
          '5월',
          '6월',
          '7월',
          '8월',
          '9월',
          '10월',
          '11월',
          '12월',
        ]
      }
    },
  }
</script>