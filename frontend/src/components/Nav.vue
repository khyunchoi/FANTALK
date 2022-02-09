<template>
  <v-toolbar>
    <router-link style="text-decoration: none; color: #797BF8; font-size: 1.2em; font-weight: bold;" :to="{name: 'Index'}">
        FANTALK
    </router-link>
    <v-toolbar-items class="container">
      <!-- <v-btn style="background-color: #FFFFFF;">팬미팅 입장</v-btn> -->
      <div style="text-decoration: none; color: #000000; font-weight: bold">
        팬미팅 입장
      </div>
      <router-link style="text-decoration: none; color: #000000; font-weight: bold" :to="{name: 'CommunityList'}">
        팬 커뮤니티
      </router-link>
      <router-link style="text-decoration: none; color: #000000; font-weight: bold" :to="{name: 'CheckManager'}">
        팬미팅 신청
      </router-link>
    </v-toolbar-items>
    <v-btn style="background-color: #FFFFFF; font-weight: bold">
      <div v-if="userId == 0" style="display: flex;">
        <img src="../assets/google.png" alt="logo" width="20px" height="20px">
        <a href="/oauth2/authorization/google" class="login" style="text-decoration: none;" hrefclass="login">로그인</a>
        
      </div>
      <div v-else>
        <a href="/logout" style="text-decoration: none;">로그아웃</a>
      </div>
    </v-btn>
  </v-toolbar>
</template>

<script>
  export default {
    name: 'Nav',
    data: function() {
      return {
        userId: 0
      }
    },

    created: function () {
      this.$axios({
        method: 'get',
        url: `http://localhost:8080/api/v1/users/me`
      })
      .then(res => {
        console.log(res)
        this.userId = res.data.id
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
</script>

<style scoped>
  .container {
    width: 90%;
    display: flex-start;
    justify-content: space-between;
    padding: 1.2% 15%;
  }
  
  .login {
    padding: 0 5%;
    font-size: 1em;
  }
</style>