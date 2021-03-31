<template>
  <div class="about">
    <h2 class="pt-2">판매순위</h2>
      <v-data-table
        :headers="$store.state.ranking_header"
        :items="$store.state.ranking"
        :items-per-page="5"
        class="elevation-1"
      ></v-data-table>

        <v-row no-gutters style="height: 50px;"></v-row>

         <v-row no-gutters>
            <v-col style="text-align:center;" :cols="1">
              <v-icon>mdi-circle-medium</v-icon> 
                항목설정:
              </v-col>
            <v-col style="padding-left:20px" :cols="11">
              <v-combobox                
                  :items="categoryname"
                  label="분류명"
                  v-model="name"
                  outlined
                  dense
                  v-on:change="CategorySelect({name, date1: time1, date2: time2})"
                  style = 'width:130px;'
                ></v-combobox>
            </v-col>
         </v-row>
         <v-row no-gutters style="height: 10px;"></v-row>
         <v-row no-gutters>
            <v-col style="text-align:left; margin-left:5px;" :cols="6">
              <v-icon>mdi-circle-medium</v-icon> 
                판매시기설정:
                <input type="date" v-model="time1" class="i_size" @click="check()">
                <input type="date" v-model="time2" class="i_size" @click="check()">
               <v-btn color="indigo lighten-1" class="v_size" @click="SalesByTime({name, date1: time1, date2: time2})">확인</v-btn>
           
            </v-col>
            <v-col :cols="6">
              
            </v-col>
         </v-row>
         <v-row>
            <v-col style="margin-left:5px; margin-top:25px; margin-bottom:100px;">
              <v-icon>mdi-circle-medium</v-icon> 
                <label style="margin-left:4px;">전체기간으로 조회</label> 
                <input type="checkbox" 
                        v-model="toggle"
                        true-value="yes"
                        false-value="no"
                        @click="[allTime(),CategorySelect({name, date1: time1, date2: time2})]" 
                        style='zoom:1.3; padding-left:30px;'> 
            </v-col>
         </v-row>
  </div>
</template>

<style scoped>
.i_size{  
  border:solid 1px; 
  margin-bottom:5px; 
  margin-right:5px; 
  margin-left:10px;
  width:150px;
}
.v_size{
  margin-left:15px; 
  bottom:3px;
  width:50px;
  height:20px;
  color:white;
}
</style>

<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('Ranking')
      this.$store.dispatch('CategoryName')
    },
    computed: {
      ...mapState(["categoryname"]),
    },
    data () {
      return {     
        name: '전체',
        time1: '',
        time2: '',
        toggle: '',
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
       ...mapActions(["SalesByTime"]),
       allTime(){
         this.time1 = '',
         this.time2 = ''
       },
       check(){
         //만약 체크박스가 체크되어 있으면 해제하기
         if(this.toggle == 'yes'){
           this.toggle = 'no'
         }
       }
    },
  }
</script>