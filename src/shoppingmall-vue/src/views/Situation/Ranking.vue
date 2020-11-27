<template>
  <div class="about">
    <h1>판매순위 페이지입니다.</h1>
    <v-data-table
      :headers="$store.state.ranking_header"
      :items="$store.state.ranking"
      :items-per-page="5"
      class="elevation-1"
    ></v-data-table>

    <p>항목설정 : 추후에 카테고리 테이블에 있는 분류명(name칼럼)을 들고와야함.        
                  일단 Vuetify 이용해야함.
                  1.리스트탭 만들어서 카테고리 내용들을 선택하게 만들수 있도록 해야함.
                  2.리스트탭에서 하나를 선택하면 그 분류의 내용들이 뜨도록 해야함.
    </p>

    <p>판매시기 설정</p>

    <p>전체기간 설정</p>
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
      }
    },
  }
</script>