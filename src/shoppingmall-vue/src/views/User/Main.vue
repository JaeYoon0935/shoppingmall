<template>
  <div class="home">
     <!-- 판매랭킹 사진 -->
     <p class="size24" style="text-align:left; margin-top:30px; margin-left:10%">Ranking
       <span class="text">판매랭킹순</span>
     </p>
     <div style="text-align:left; margin-left:15%; margin-bottom:100px;">
        <span v-for="(item, index) in $store.state.imageByRank" v-bind:key="index">
          <!-- <router-link to="preparing"> -->
            <span v-if="index <4">
                <img :src="image(item.image)" class="image" @click="prepare()">
            </span>
          <!-- </router-link> -->
        </span>
     </div>
     <!-- 거실등 사진 -->
     <hr width = "80%" color = "gray" style="margin: auto;">
     <p class="size24" style="text-align:left; margin-top:30px; margin-left:10%">Living Room
       <span class="text">LED 거실등</span>
     </p>
     <div style="text-align:left; margin-left:15%; margin-bottom:100px;">
        <span v-for="(item, index) in $store.state.imageLiving" v-bind:key="index">
          <!-- <router-link to="preparing"> -->
            <span v-if="index <4">
              <img :src="image(item.image)" class="image" @click="prepare()">
            </span>
          <!-- </router-link> -->
        </span>
     </div>
     <!-- 방등 사진 -->
     <hr width = "80%" color = "gray" style="margin: auto;">
     <p class="size24" style="text-align:left; margin-top:30px; margin-left:10%">Room Lamp
       <span class="text">LED 방등</span>
     </p>
     <div style="text-align:left; margin-left:15%; margin-bottom:100px;">
        <span v-for="(item, index) in $store.state.imageRoom" v-bind:key="index">
          <!-- <router-link to="preparing"> -->
            <span v-if="index <4">
              <img :src="image(item.image)" class="image" @click="prepare()">
            </span>
          <!-- </router-link> -->
        </span>
     </div>

     <!-- 전기재료 사진 -->
     <hr width = "80%" color = "gray" style="margin: auto;">
     <p class="size24" style="text-align:left; margin-top:30px; margin-left:10%">Electrical Materials
       <span class="text">전기재료</span>
     </p>
     <div style="text-align:left; margin-left:15%; margin-bottom:100px;">
          <span v-for="(item, index) in $store.state.imageMaterials" v-bind:key="index">
            <!-- <router-link to="preparing"> -->
              <span v-if="index <4">
                <img :src="image(item.image)" class="image" @click="prepare()">
              </span>
            <!-- </router-link> -->
          </span>
     </div>

     <!-- 사용자 정보 -->
     <hr width = "80%" color = "gray" style="margin: auto;">
     <p class="size24" style="text-align:left; margin-top:30px; margin-left:10%">Information
       <span class="text">판매자 정보</span>
     </p>
     <p class="size18" style="margin-bottom:0px; margin-top:10px;">
       <span>CEO  홍길동</span>
       <span>Business License  000-00-00000</span>
       <span>Location  대구광역시 중구 대봉로 260</span>
       <span></span>
     </p>
     <p class="size18"> 
       <span>Tel  0000-0000</span>
       <span>E-Mail  abc@abcde.com</span>
       <span>FAX  0000-000-0000</span> 
     </p>

  </div>
</template>

<style scoped>
.size18{
  font-size:18px;
  color:rgb(90, 90, 90)
}

.size24{
  font-size:24px;
}
.image{
  width:200px; 
  height:160px;
}
div[data-v-fae5bece] {
    width: 2200px;
    height: 1700px;
    
    background-color: rgb(255, 255, 255);
    margin: 0 auto;
    padding: 0px;
    border-radius: 10px;
    font-size: 32px;
    color: black;
    font-family: 'Times New Roman', Times, serif;
    font-weight: 700;
    line-height: 50px;
    text-align: center;
}
span{
  margin:30px;
}

.text{
    font-family: "Spoqa Han Sans",sans-serif;
    color: #807f7f;
    font-size: 14px;
    margin-left:10px;
    font-weight:700;
}


</style>


<script>
export default {
   created(){
    this.$store.dispatch('imageByRank')
    this.$store.dispatch('imageLiving')
    this.$store.dispatch('imageRoom')
    this.$store.dispatch('imageMaterials')
  },
  methods:{
    image(image){
        //경로를 조합해줄 메서드.
        if(image == null){
          return require('@/images/null.jpg');
        }
        /* 랜더링 되는 시간 때문에 발생하는 오류를 try, catch로 잡아준다. */
        try {
          return require('@/images/'+ image +'.jpg')
        } catch (e) {
          return require('@/images/null.jpg')
        }
      },
    prepare(){
      alert('준비 중입니다.')
    }
  }
}
</script>