(function(t){function e(e){for(var r,o,i=e[0],l=e[1],c=e[2],u=0,v=[];u<i.length;u++)o=i[u],Object.prototype.hasOwnProperty.call(s,o)&&s[o]&&v.push(s[o][0]),s[o]=0;for(r in l)Object.prototype.hasOwnProperty.call(l,r)&&(t[r]=l[r]);m&&m(e);while(v.length)v.shift()();return n.push.apply(n,c||[]),a()}function a(){for(var t,e=0;e<n.length;e++){for(var a=n[e],r=!0,o=1;o<a.length;o++){var l=a[o];0!==s[l]&&(r=!1)}r&&(n.splice(e--,1),t=i(i.s=a[0]))}return t}var r={},s={app:0},n=[];function o(t){return i.p+"js/"+({about:"about"}[t]||t)+"."+{about:"c25a03d3"}[t]+".js"}function i(e){if(r[e])return r[e].exports;var a=r[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,i),a.l=!0,a.exports}i.e=function(t){var e=[],a=s[t];if(0!==a)if(a)e.push(a[2]);else{var r=new Promise((function(e,r){a=s[t]=[e,r]}));e.push(a[2]=r);var n,l=document.createElement("script");l.charset="utf-8",l.timeout=120,i.nc&&l.setAttribute("nonce",i.nc),l.src=o(t);var c=new Error;n=function(e){l.onerror=l.onload=null,clearTimeout(u);var a=s[t];if(0!==a){if(a){var r=e&&("load"===e.type?"missing":e.type),n=e&&e.target&&e.target.src;c.message="Loading chunk "+t+" failed.\n("+r+": "+n+")",c.name="ChunkLoadError",c.type=r,c.request=n,a[1](c)}s[t]=void 0}};var u=setTimeout((function(){n({type:"timeout",target:l})}),12e4);l.onerror=l.onload=n,document.head.appendChild(l)}return Promise.all(e)},i.m=t,i.c=r,i.d=function(t,e,a){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(t,e){if(1&e&&(t=i(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(i.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)i.d(a,r,function(e){return t[e]}.bind(null,r));return a},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="/",i.oe=function(t){throw console.error(t),t};var l=window["webpackJsonp"]=window["webpackJsonp"]||[],c=l.push.bind(l);l.push=e,l=l.slice();for(var u=0;u<l.length;u++)e(l[u]);var m=c;n.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"56d7":function(t,e,a){"use strict";a.r(e);a("e260"),a("e6cf"),a("cca6"),a("a79d");var r=a("2b0e"),s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-app",{attrs:{id:"inspire"}},[a("v-navigation-drawer",{attrs:{app:""},model:{value:t.drawer,callback:function(e){t.drawer=e},expression:"drawer"}},[a("v-list",{attrs:{dense:""}},[a("v-list-item",{attrs:{router:"",to:{name:"Login"}}},[a("v-list-item-action",[a("v-icon",[t._v("mdi-home")])],1),a("v-list-item-content",[a("v-list-item-title",[t._v("Login")])],1)],1),a("v-list-item",{attrs:{router:"",to:{name:"BoardList"}}},[a("v-list-item-action",[a("v-icon",[t._v("mdi-card-text-outline")])],1),a("v-list-item-content",[a("v-list-item-title",[t._v("게시판")])],1)],1)],1)],1),a("v-app-bar",{attrs:{app:"",color:"primary",dark:""}},[a("v-app-bar-nav-icon",{on:{click:function(e){e.stopPropagation(),t.drawer=!t.drawer}}}),a("v-toolbar-title",[t._v("LcomputerStudy")])],1),a("v-main",[a("v-container",{staticClass:"fill-height",attrs:{fluid:""}},[a("router-view")],1)],1),a("v-footer",{attrs:{color:"primary",app:""}},[a("span",{staticClass:"white--text"},[t._v("© "+t._s((new Date).getFullYear()))])])],1)},n=[],o={props:{source:String},data:function(){return{drawer:null}}},i=o,l=a("2877"),c=a("6544"),u=a.n(c),m=a("7496"),v=a("40dc"),d=a("5bc1"),p=a("a523"),f=a("553a"),h=a("132d"),b=a("8860"),g=a("da13"),_=a("1800"),y=a("5d23"),x=a("f6c4"),w=a("f774"),U=a("2a7f"),V=Object(l["a"])(i,s,n,!1,null,null,null),C=V.exports;u()(V,{VApp:m["a"],VAppBar:v["a"],VAppBarNavIcon:d["a"],VContainer:p["a"],VFooter:f["a"],VIcon:h["a"],VList:b["a"],VListItem:g["a"],VListItemAction:_["a"],VListItemContent:y["a"],VListItemTitle:y["c"],VMain:x["a"],VNavigationDrawer:w["a"],VToolbarTitle:U["a"]});a("d3b7");var j=a("8c4f"),k=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"home"},[a("HelloWorld",{attrs:{msg:"Welcome to Your Vue.js App"}})],1)},P=[],L=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("v-container",[r("v-row",{staticClass:"text-center"},[r("v-col",{attrs:{cols:"12"}},[r("v-img",{staticClass:"my-3",attrs:{src:a("9b19"),contain:"",height:"200"}})],1),r("v-col",{staticClass:"mb-4"},[r("h1",{staticClass:"display-2 font-weight-bold mb-3"},[t._v(" Welcome to Vuetify ")]),r("p",{staticClass:"subheading font-weight-regular"},[t._v(" For help and collaboration with other Vuetify developers, "),r("br"),t._v("please join our online "),r("a",{attrs:{href:"https://community.vuetifyjs.com",target:"_blank"}},[t._v("Discord Community")])])]),r("v-col",{staticClass:"mb-5",attrs:{cols:"12"}},[r("h2",{staticClass:"headline font-weight-bold mb-3"},[t._v(" What's next? ")]),r("v-row",{attrs:{justify:"center"}},t._l(t.whatsNext,(function(e,a){return r("a",{key:a,staticClass:"subheading mx-3",attrs:{href:e.href,target:"_blank"}},[t._v(" "+t._s(e.text)+" ")])})),0)],1),r("v-col",{staticClass:"mb-5",attrs:{cols:"12"}},[r("h2",{staticClass:"headline font-weight-bold mb-3"},[t._v(" Important Links ")]),r("v-row",{attrs:{justify:"center"}},t._l(t.importantLinks,(function(e,a){return r("a",{key:a,staticClass:"subheading mx-3",attrs:{href:e.href,target:"_blank"}},[t._v(" "+t._s(e.text)+" ")])})),0)],1),r("v-col",{staticClass:"mb-5",attrs:{cols:"12"}},[r("h2",{staticClass:"headline font-weight-bold mb-3"},[t._v(" Ecosystem ")]),r("v-row",{attrs:{justify:"center"}},t._l(t.ecosystem,(function(e,a){return r("a",{key:a,staticClass:"subheading mx-3",attrs:{href:e.href,target:"_blank"}},[t._v(" "+t._s(e.text)+" ")])})),0)],1)],1)],1)},S=[],O={name:"HelloWorld",data:function(){return{ecosystem:[{text:"vuetify-loader",href:"https://github.com/vuetifyjs/vuetify-loader"},{text:"github",href:"https://github.com/vuetifyjs/vuetify"},{text:"awesome-vuetify",href:"https://github.com/vuetifyjs/awesome-vuetify"}],importantLinks:[{text:"Documentation",href:"https://vuetifyjs.com"},{text:"Chat",href:"https://community.vuetifyjs.com"},{text:"Made with Vuetify",href:"https://madewithvuejs.com/vuetify"},{text:"Twitter",href:"https://twitter.com/vuetifyjs"},{text:"Articles",href:"https://medium.com/vuetify"}],whatsNext:[{text:"Explore components",href:"https://vuetifyjs.com/components/api-explorer"},{text:"Select a layout",href:"https://vuetifyjs.com/getting-started/pre-made-layouts"},{text:"Frequently Asked Questions",href:"https://vuetifyjs.com/getting-started/frequently-asked-questions"}]}}},I=O,T=a("62ad"),N=a("adda"),E=a("0fd9"),F=Object(l["a"])(I,L,S,!1,null,null,null),A=F.exports;u()(F,{VCol:T["a"],VContainer:p["a"],VImg:N["a"],VRow:E["a"]});var B={name:"Home",components:{HelloWorld:A}},$=B,D=Object(l["a"])($,k,P,!1,null,null,null),M=D.exports,W=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",{staticStyle:{"max-width":"500px"}},[a("v-layout",[a("v-flex",{attrs:{xs12:""}},[a("v-alert",{staticClass:"mb-3",attrs:{type:"error",value:t.login_err}},[t._v(" 아이디 / 비밀번호를 확인해주세요 ")]),a("v-alert",{staticClass:"mb-3",attrs:{type:"success",value:t.login_success}},[t._v(" 로그인 성공 ")]),a("v-card",{staticClass:"elevation-12"},[a("v-toolbar",{attrs:{color:"primary",dark:"",flat:""}},[a("v-toolbar-title",[t._v(" Login ")]),a("v-spacer")],1),a("v-card-text",[a("v-form",[a("v-text-field",{attrs:{label:"ID",name:"username","prepend-icon":"mdi-account",type:"text"},model:{value:t.username,callback:function(e){t.username=e},expression:"username"}}),a("v-text-field",{attrs:{id:"Password",label:"Password",name:"password","prepend-icon":"mdi-lock",type:"password"},model:{value:t.password,callback:function(e){t.password=e},expression:"password"}})],1)],1),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.login({username:t.username,password:t.password})}}},[t._v("Login")]),a("v-btn",{attrs:{color:"#ECEFF1",router:"",to:{name:"SignUp"}}},[t._v("Sign Up")])],1)],1)],1)],1)],1)},H=[],q=a("5530"),R=a("2f62"),J={data:function(){return{username:null,password:null}},methods:{login:function(t){this.$store.dispatch("loginProcess",t)}},computed:Object(q["a"])({},Object(R["c"])(["login_err","login_success"]))},Y=J,z=a("0798"),G=a("8336"),Q=a("b0af"),K=a("99d9"),X=a("0e8f"),Z=a("4bd4"),tt=a("a722"),et=a("2fa4"),at=a("8654"),rt=a("71d9"),st=Object(l["a"])(Y,W,H,!1,null,null,null),nt=st.exports;u()(st,{VAlert:z["a"],VBtn:G["a"],VCard:Q["a"],VCardActions:K["a"],VCardText:K["b"],VContainer:p["a"],VFlex:X["a"],VForm:Z["a"],VLayout:tt["a"],VSpacer:et["a"],VTextField:at["a"],VToolbar:rt["a"],VToolbarTitle:U["a"]});var ot=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",{staticStyle:{"max-width":"500px"}},[a("v-layout",[a("v-flex",{attrs:{xs12:""}},[a("v-card",{staticClass:"elevation-12"},[a("v-toolbar",{attrs:{color:"#FBC02D",dark:"",flat:""}},[a("v-toolbar-title",[t._v(" Sign Up ")]),a("v-spacer")],1),a("v-card-text",[a("v-form",[a("v-text-field",{attrs:{label:"Id",name:"UserId","prepend-icon":"mdi-account",type:"text"},model:{value:t.UserId,callback:function(e){t.UserId=e},expression:"UserId"}}),a("v-text-field",{attrs:{id:"UserPassword",label:"Password",name:"UserPassword","prepend-icon":"mdi-lock",type:"password"},model:{value:t.UserPassword,callback:function(e){t.UserPassword=e},expression:"UserPassword"}}),a("v-text-field",{attrs:{id:"UserName",label:"Name",name:"UserName","prepend-icon":"mdi-card-account-details",type:"text"},model:{value:t.UserName,callback:function(e){t.UserName=e},expression:"UserName"}}),a("v-text-field",{attrs:{id:"UserPhone",label:"Phone",name:"UserPhone","prepend-icon":"mdi-phone",type:"number"},model:{value:t.UserPhone,callback:function(e){t.UserPhone=e},expression:"UserPhone"}})],1)],1),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"#FBC02D"},on:{click:t.SignUp}},[t._v("Sign Up")])],1)],1)],1)],1)],1)},it=[],lt={data:function(){return{UserId:null,UserPassword:null,UserName:null,UserPhone:null}},methods:Object(q["a"])(Object(q["a"])({},Object(R["b"])(["NewUsers"])),{},{SignUp:function(){var t={UserId:this.UserId,UserPassword:this.UserPassword,UserName:this.UserName,UserPhone:this.UserPhone};console.log(t),this.NewUsers(t)}})},ct=lt,ut=Object(l["a"])(ct,ot,it,!1,null,null,null),mt=ut.exports;u()(ut,{VBtn:G["a"],VCard:Q["a"],VCardActions:K["a"],VCardText:K["b"],VContainer:p["a"],VFlex:X["a"],VForm:Z["a"],VLayout:tt["a"],VSpacer:et["a"],VTextField:at["a"],VToolbar:rt["a"],VToolbarTitle:U["a"]});var vt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",[a("v-card",{staticClass:"pa-3 "},[a("v-list",{attrs:{shaped:t.shaped,subheader:t.subheader}},[a("v-subheader",[t._v("게시판")]),a("v-list-item-group",{attrs:{color:"primary"},model:{value:t.item,callback:function(e){t.item=e},expression:"item"}},[a("v-list-item",[a("v-list-item-content",[a("v-list-item-title",[t._v("글 제목")]),a("v-list-item-subtitle",[t._v(" 글 내용")])],1)],1),a("v-list-item",[a("v-list-item-content",[a("v-list-item-title",[t._v("글 제목1")]),a("v-list-item-subtitle",[t._v(" 글 내용11")])],1)],1),a("v-list-item",[a("v-list-item-content",[a("v-list-item-title",[t._v("글 제목2")]),a("v-list-item-subtitle",[t._v(" 글 내용2")])],1)],1),a("v-list-item",[a("v-list-item-content",[a("v-list-item-title",[t._v("글 제목3")]),a("v-list-item-subtitle",[t._v(" 글 내용3")])],1)],1),a("v-list-item",[a("v-list-item-content",[a("v-list-item-title",[t._v("글 제목4")]),a("v-list-item-subtitle",[t._v(" 글 내용4")])],1)],1),a("v-list-item",[a("v-list-item-content",[a("v-list-item-title",[t._v("글 제목5")]),a("v-list-item-subtitle",[t._v(" 글 내용5")])],1)],1)],1)],1)],1)],1)},dt=[],pt=a("1baa"),ft=a("e0c7"),ht={},bt=Object(l["a"])(ht,vt,dt,!1,null,null,null),gt=bt.exports;u()(bt,{VCard:Q["a"],VContainer:p["a"],VList:b["a"],VListItem:g["a"],VListItemContent:y["a"],VListItemGroup:pt["a"],VListItemSubtitle:y["b"],VListItemTitle:y["c"],VSubheader:ft["a"]});var _t=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-flex",{staticStyle:{"text-align":"center"},attrs:{xs12:""}},[a("h1",[t._v(t._s(t.Userinfo.User_Name)+"님 환영합니다.")])])},yt=[],xt={computed:Object(q["a"])({},Object(R["c"])(["Userinfo"]))},wt=xt,Ut=Object(l["a"])(wt,_t,yt,!1,null,null,null),Vt=Ut.exports;u()(Ut,{VFlex:X["a"]}),r["a"].use(j["a"]);var Ct=[{path:"/",name:"Home",component:M},{path:"/about",name:"About",component:function(){return a.e("about").then(a.bind(null,"f820"))}},{path:"/login",name:"Login",component:nt},{path:"/signup",name:"SignUp",component:mt},{path:"/boardlist",name:"BoardList",component:gt},{path:"/user",name:"User",component:Vt}],jt=new j["a"]({mode:"history",base:"/",routes:Ct}),kt=jt,Pt=(a("b0c0"),a("bc3a")),Lt=a.n(Pt);r["a"].use(R["a"]);var St=new R["a"].Store({state:{Userinfo:{User_Id:null,User_Name:null,User_auth:[]},login_err:!1,login_success:!1},getters:{allUsers:function(t){return t.UserList.length}},mutations:{NewUsers:function(t,e){t.UserList.push(e),kt.push("/login")},SET_USER:function(t,e){t.Userinfo.User_Id=e.username,t.Userinfo.User_Name=e.name,t.Userinfo.User_auth=e.authorities,kt.push("/user")}},actions:{NewUsers:function(t,e){var a=t.commit;a("NewUsers",e)},loginProcess:function(t,e){var a=t.commit;return console.log(e),new Promise((function(t,r){Lt.a.post("http://localhost:9000/api/auth/signin",e).then((function(t){console.log(t.data),null!=t.data.username&&(Lt.a.defaults.headers.common["Authorization"]="Bearer ".concat(t.data.token),a("SET_USER",t.data))})).catch((function(t){console.log("error"),r(t)}))}))}}}),Ot=a("f309");r["a"].use(Ot["a"]);var It=new Ot["a"]({});r["a"].config.productionTip=!1,new r["a"]({router:kt,store:St,vuetify:It,render:function(t){return t(C)}}).$mount("#app")},"9b19":function(t,e,a){t.exports=a.p+"img/logo.63a7d78d.svg"}});
//# sourceMappingURL=app.719cd3af.js.map