<template>
    <b-container fluid class="pt-2">
    <h2>판매순위 페이지</h2>
    <v-data-table
      :headers="$store.state.ranking_header"
      :items="$store.state.ranking"
      :items-per-page="5"
      class="elevation-1"
    ></v-data-table>

      <b-row class="text-left">
        <b-col cols="1">항목설정</b-col>
        <b-col cols="11">
          <v-combobox
            v-model="select"
            :items="items"
            label="분류명"
            multiple
            outlined
            dense
            style = 'width:130px;'
          ></v-combobox>
        </b-col>
      </b-row>

      <b-row class="text-left">
        <b-col lg="1">
        판매시기 설정
        </b-col>
        <b-col lg="1" class="col-1-5">
          <v-combobox
            :items="year"
            label="연도"
            multiple
            outlined
            dense
            style = 'width:130px;'
          ></v-combobox>   
        </b-col>
        <b-col lg="7" offset-lg="3">
            <v-combobox
            :items="month"
            label="월"
            multiple
            outlined
            dense
            style = 'width:120px;'
          ></v-combobox>
        </b-col> 
      </b-row>
      
      <b-row class="text-left">
        <b-col cols="12">
        <label>전체기간으로 조회</label> <input type="checkbox" style='zoom:1.3;'> 
        </b-col>
      </b-row>

    </b-container>
</template>

<style scoped>
.col-1-5 {
    flex: 0 0 12.3%;
    max-width: 12.3%;
    position: relative;
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
}
</style>

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