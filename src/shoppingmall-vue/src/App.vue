<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
    >   
     <v-card
        class="mx-auto"
        max-width="300"
        tile
      >
       <v-subheader>목록</v-subheader>
        <v-list dense>
          <v-menu
            open-on-hover
            top
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                block
                depressed
                color="gray"             
                v-bind="attrs"
                v-on="on"
              >
                회원관리
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in item1"
                :key="index"
                 router :to="{name: item.name}"
              >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
            </v-list>
          </v-menu>
        </v-list>

        <v-list dense>
          <v-menu
            open-on-hover
            top
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                block
                depressed
                color="gray"
                v-bind="attrs"
                v-on="on"
              >
                쇼핑몰관리
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in item2"
                :key="index"
                router :to="{name: item.name}"
              >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
            </v-list>
          </v-menu>
        </v-list>

        <v-list dense>
          <v-menu
            open-on-hover
            top
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                block
                depressed
                color="gray"
                v-bind="attrs"
                v-on="on"
              >
                쇼핑몰현황
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in item3"
                :key="index"
                 router :to="{name: item.name}"
              >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
            </v-list>
          </v-menu>
        </v-list>
    </v-card>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>
          <v-btn router :to="{name: 'Home'}" flat>관리자 페이지 (메인) </v-btn>
        </v-toolbar-title>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    data: () => ({ 
      drawer: null, 
      selectedItem: 1,
      item1: [
        { title: '회원정보', name: 'UserList' },
        { title: '포인트관리', name: 'Point' },
      ],
      item2: [
        { title: '쇼핑몰정보', name: 'Information'},
        { title: '주문내역', name: 'Order'},
        { title: '분류관리', name: 'Category'},
        { title: '상품관리', name: 'Product'},
        { title: '재고관리', name: 'Stock'},
        { title: '1대1문의', name: 'Inquire'},
      ],
      item3: [
        { title: '판매순위', name: 'Ranking'},
        { title: '매출통계', name: 'Sales'},
      ],
    }),
  }
</script>