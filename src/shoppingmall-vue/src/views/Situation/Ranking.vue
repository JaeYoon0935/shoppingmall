<template>
  <div class="about">
    <h1>판매순위 페이지입니다.</h1>
    <v-data-table
      :headers="$store.state.ranking_header"
      :items="$store.state.ranking_item"
      :items-per-page="5"
      class="elevation-1"
    ></v-data-table>

    <p>항목설정 : 추후에 카테고리 테이블에 있는 분류명(name칼럼)을 들고와야함.</p>

    <p>판매시기 설정</p>

    <p>전체기간 설정</p>
  </div>
</template>

<script>
  export default {
    data () {
      return {     
      }
    },
  }
</script>