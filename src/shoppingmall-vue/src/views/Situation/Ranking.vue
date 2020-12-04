<template>
    <b-container fluid>
    <h1>판매순위 페이지입니다.</h1>
    <v-data-table
      :headers="$store.state.ranking_header"
      :items="$store.state.ranking"
      :items-per-page="5"
      class="elevation-1"
    ></v-data-table>


      <b-row align-h="start">
        <b-col cols="1">항목설정</b-col>
        <b-col cols="11">
          <v-combobox
            v-model="select"
            :items="items"
            label="분류명"
            multiple
            outlined
            dense
            style = 'width:100px;'
          ></v-combobox>
        </b-col>
      </b-row>

      <b-row class="text-left">
        <b-col cols="1">판매시기 설정</b-col>
        <b-col cols="1">
          <v-combobox
            v-model="select"
            :items="items"
            label="연도"
            multiple
            outlined
            dense
            style = 'width:100px;'
          ></v-combobox>   
        </b-col>
        <b-col cols="10">
            <v-combobox
            v-model="select"
            :items="items"
            label="월"
            multiple
            outlined
            dense
            style = 'width:100px;'
          ></v-combobox>
        </b-col> 
      </b-row>
      
      <b-row class="text-left">
        <label>전체기간으로 조회</label> <input type="checkbox" style='zoom:1.3;'> 
      </b-row>

    </b-container>


    <!-- <div style="float: left;">
        <div>
          <div style="float: left;">
          <p>항목설정</p>
          </div>
          <div style="float: left;">
             추후에 카테고리 테이블에 있는 분류명(name칼럼)을 들고와야함. , 콤보박스를 사용 할 것 
              <v-combobox
                v-model="select"
                :items="items"
                label="분류명"
                multiple
                outlined
                dense
                style = 'width:100px;'
              ></v-combobox>   
          </div>
        </div>

        <div>
          <div style="float: left;">
            <div>
            <div style="float: left;">
              <p>판매시기 설정</p>
            </div>

              <div style="float: left;">
                <v-combobox
                    v-model="select"
                    :items="items"
                    label="연도"
                    multiple
                    outlined
                    dense
                    style = 'width:100px;'
                  ></v-combobox>   
              </div>

              <div style="float: left;">
                  <v-combobox
                    v-model="select"
                    :items="items"
                    label="월"
                    multiple
                    outlined
                    dense
                    style = 'width:100px;'
                  ></v-combobox>   
              </div>
            </div>
          </div>
        </div>
         <v-checkbox label="전체기간으로 조회" v-model="ex3" color="black" value=" " hide-details></v-checkbox>

          <div style="float: left;">
            <p>
              <label>전체기간으로 조회</label> <input type="checkbox" style='zoom:1.3;'> 
            </p>
          </div>
    </div> -->



</template>

<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('Ranking')
    },
    data () {
      return {     
      }
    },
  }
</script>