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
            <v-col style="text-align:center;" :cols="1">
              <v-icon>mdi-circle-medium</v-icon> 
                항목설정:
              </v-col>
            <v-col style="padding-left:20px" :cols="11">
              <v-combobox                
                  :items="categoryname"
                  label="분류명"
                  v-model="cg_select"
                  outlined
                  dense
                  v-on:change="CategorySelect(cg_select)"
                  style = 'width:130px;'
                ></v-combobox>
            </v-col>
         </v-row>
         <v-row no-gutters style="height: 20px;"></v-row>

         <v-row no-gutters>
            <v-col style="text-align:center;" :cols="1">
              <v-icon>mdi-circle-medium</v-icon> 
                판매시기설정:
            </v-col>
            <v-col style="padding-left:0px;" :cols="1">
              <v-combobox
                :items="year"
                label="연도"
                outlined
                dense
                style = 'width:130px;'
              ></v-combobox>   
              </v-col>
            <v-col style="padding-left:30px;" :cols="2">
              <v-combobox
                :items="month"
                label="월"
                outlined
                dense
                style = 'width:140px;'
              ></v-combobox>
            </v-col>
            <v-col :cols="2">
              <v-icon>mdi-circle-medium</v-icon> 
                <label>전체기간으로 조회</label> <input type="checkbox" style='zoom:1.3; padding-left:30px;'> 
            </v-col>
            <v-btn dark small color="grey" @click="CategorySelect(cg_select)">테스트</v-btn>
         </v-row>
  </div>
</template>


<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('Ranking')
      this.$store.dispatch('CategoryName')
    },
    computed: {
      ...mapState(["categoryname"]),
      //State는 배열로 가져오므로 1개를 가져오더라도 반드시 가져올때 대괄호로 감싸줘야함.
    },
    data () {
      return {     
        cg_select: '전체',
        items:[

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
    methods:{
       ...mapActions(["CategorySelect"]),
        test(cg_select){
            console.log(cg_select);
        },
    },
  }
</script>