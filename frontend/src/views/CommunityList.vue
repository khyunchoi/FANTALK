<template>
  <div>
    <!-- <CommunityListSearch />
    <hr> -->

    <div class="community-list-cards-container">

      <div>
        <span>
          팬 커뮤니티 목록
        </span>
      </div>  
      <br>
      <div>
        <button
          v-for="community in communityList"
          style="padding: 3%; border-style: solid; border-width: 1px"
          @click="enterCommunity(community.id)"
        >
          {{ community.id }} | {{ community.name }} | {{ community.title }}
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
        url: 'http://localhost:8080/api/v1/communities'
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

</style>