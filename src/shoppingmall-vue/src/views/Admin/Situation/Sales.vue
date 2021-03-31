<template>
  <div class="about">
    <h2 class="pt-2">매출통계</h2>
    <div>
        <v-row>
          <span>일일 매출</span>
          <input type="date" v-model="day" class="i_size">
          <span>일 하루</span>
          <v-btn color="indigo lighten-1" class="v_size" router :to="{name:'SalesData', params: {date1: day}}">확인</v-btn>
        </v-row>
        <v-row>
          <span>일간 매출</span>
          <input type="date" v-model="day1" class="i_size">
          <span>일 ~</span>
          <input type="date" v-model="day2" class="i_size">
          <span>일</span>
          <v-btn color="indigo lighten-1" class="v_size" router :to="{name:'SalesData', params: {date1: day1, date2:day2}}">확인</v-btn>
        </v-row>
        <v-row>
          <span>월간 매출</span>
          <input type="month" v-model="month1" class="i_size">
          <span>월 ~</span>
          <input type="month" v-model="month2" class="i_size">
          <span>월</span>
          <v-btn color="indigo lighten-1" class="v_size" router :to="{name:'SalesData', params: {date1: month1, date2:month2}}">확인</v-btn>
        </v-row>
        <v-row>
          <span>연간 매출</span>
          <select v-model="year1" class="i_size">
            <option value="" disabled selected>연도</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
            <option value="2018">2018</option>
            <option value="2019">2019</option>
            <option value="2020">2020</option>
            <option value="2021">2021</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
            <option value="2025">2025</option>
          </select>
          <span>년 ~</span>
          <select v-model="year2" class="i_size">
            <option value="" disabled selected>연도</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
            <option value="2018">2018</option>
            <option value="2019">2019</option>
            <option value="2020">2020</option>
            <option value="2021">2021</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
            <option value="2025">2025</option>
          </select>
          <span>년</span>
          <v-btn color="indigo lighten-1" class="v_size" router :to="{name:'SalesData', params: {date1: year1, date2:year2}}">확인</v-btn>
        </v-row>
    </div>
  </div>
</template>

<style scoped>
.row{
  margin-left: 25px;
  margin-top: 25px;
}
.i_size{  
  border:solid 1px; 
  margin-bottom:5px; 
  margin-right:5px; 
  margin-left:10px;
  width:150px;
}
.v_size{
  margin-left:10px; 
  bottom:5px;
  width:50px;
  height:20px;
  color:white;
}
</style>

<script>
  export default {
    data() {
      return{
        day:'',
        day1:'',
        day2:'',
        month1:'',
        month2:'',
        year1:'',
        year2:'',
      }
    },
  }
</script>
