<template>
  <div>
    <!-- <CommunityListSearch />
    <hr> -->
    <div class="community-list-cards-container">
      <div>
        <h2 style="font-size: 1.5em;">팬 커뮤니티 목록</h2>
      </div>  
      <br>

      <div>
        <button
          v-for="community in communityList" :key="community"
          class="community-list-card"
          @click="enterCommunity(community.id)"
        >
          {{ community.id }} | {{ community.name }}
          <br>
          <br>
          {{ community.title }}
          <!-- <router-link :to="{name:'CommunityListItem', params:{ communinityId:community.id }}">
            <button>입장</button>
          </router-link> -->
        </button>
      </div>
      

    </div>  
  </div>
</template>

<script>
  import CommunityListSearch from '../components/CommunityListSearch.vue'
  const SERVER_URL = process.env.VUE_APP_API_URL
  export default {
    name: 'CommunityList',
    components: {
      CommunityListSearch
    },
    data: function() {
      return {
        communityList: {}
      }
    },
    methods:{
      enterCommunity: function (idx) {
        this.$router.push({name:'CommunityListItem', params:{ communityId:idx }})
        CommunityListItem.data.push({communityId:idx})
      }

    },
    created: function () {
      this.$axios({
        method: 'get',
        url: `${SERVER_URL}/api/v1/communities`
      })
      .then(response => {
        return response.data
      })
      .then(response => {
        this.communityList = response
        console.log(this.communityList)
        return this.communityList
      })
      .catch(error => {
          console.log(error)
      })
      .finally(function () {
        console.log('파이팅!')
      })
    }
  }
</script>

<style>
  .community-list-cards-container {
    width: 100%;
    display: flex;
    padding: 10%;
    flex-direction: column;
    align-items: left;
  }

  .community-list-card {
    width: 20%;
    padding: 3%;
    border-style: solid;
    border-width: 5px;
    border-color: #797BF8;
    margin: 10px;
  }

</style>
