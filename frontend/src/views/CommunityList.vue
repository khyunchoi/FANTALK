<template>
  <div>
    <!-- <CommunityListSearch />
    <hr> -->
    <div class="community-list">

      <div class="community-list-title">
        <h2>팬 커뮤니티 목록</h2>
      </div>

      <br><br>

      <div class="community-list-cards">
        <div class="community-list-cards-column">
          <button v-for="community in communityList1" :key="community" class="community-list-card" @click="enterCommunity(community.id)" style="background-color: #FF6666;">
            {{ community.id }} | {{ community.name }}<br><br>{{ community.title }}
          </button>
        </div>
        <div class="community-list-cards-column">
          <button v-for="community in communityList2" :key="community" class="community-list-card" @click="enterCommunity(community.id)" style="background-color: #F7A400;">
            {{ community.id }} | {{ community.name }}<br><br>{{ community.title }}
          </button>
        </div>
        <div class="community-list-cards-column">
          <button v-for="community in communityList3" :key="community" class="community-list-card" @click="enterCommunity(community.id)" style="background-color: #03B962;">
            {{ community.id }} | {{ community.name }}<br><br>{{ community.title }}
          </button>
        </div>
        <div class="community-list-cards-column">
          <button v-for="community in communityList4" :key="community" class="community-list-card" @click="enterCommunity(community.id)" style="background-color: #ACAEFF;">
            {{ community.id }} | {{ community.name }}<br><br>{{ community.title }}
          </button>
        </div>
      </div>


      <br><br><br>



      
      <!-- <div class="community-list-cards">
        <div>
          <button v-for="community in communityList" :key="community" class="community-list-card" @click="enterCommunity(community.id)">
            {{ community.id }} | {{ community.name }}<br><br>{{ community.title }}
          </button>
        </div>
      </div> -->

    </div>  







    
    <!-- <div class="community-list-cards-container">
      <br><br><br><br>
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
        </button>
      </div>
    </div>   -->



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
        communityList: [],
        communityList1: [],
        communityList2: [],
        communityList3: [],
        communityList4: [],
      }
    },
    methods:{
      enterCommunity: function (idx) {
        this.$router.push({name:'CommunityListItem', params:{ communityId:idx }})
        CommunityListItem.data.push({communityId:idx})
      },
      communityNum: function (idx) {
        return idx % 4
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
        // console.log(this.communityList)

        for (var i = 0; i < this.communityList.length; i++) {
          if (i % 4 === 0){
            this.communityList1.push(response[i])
          }else if (i % 4 === 1){
            this.communityList2.push(response[i])
          }else if (i % 4 === 2){
            this.communityList3.push(response[i])
          }else if (i % 4 === 3){
            this.communityList4.push(response[i])
          }
          
        }
        // console.log(this.communityList1)
        


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
  .community-list {
    display: flex;
    width: 100%;
    padding: 5%;
    flex-direction: column;
    align-items: left;
  }
  .community-list-title {
    display: flex;
    /* background-color: antiquewhite; */
    /* flex-direction: column;
    align-items: left; */
  }
  .community-list-cards {
    display: flex;
    justify-content: space-between;
    /* background-color: antiquewhite; */
    /* flex-direction: column;
    align-items: left; */
  }
  .community-list-cards-column {
    display: flex;
    flex-direction: column;
    width: 25%;
    padding: 5px;
    margin: 5px;
    /* background-color: white; */
  }
  




  .community-list-card {
    display: flex;
    justify-content: center;
    padding: 20% 5% 5% 5%;
    margin: 10px;
    height: 300px;
    color: white;
    /* border-style: solid;
    border-width: 5px;
    border-color: #797BF8; */
    border-radius: 15px;
    
    /* background-color: #FFACAC"
    background-color: #FFD98E"
    background-color: #14D076"
    background-color: #EBEBFF" */
    
    
    
  }







  .community-list-cards-container {
    width: 100%;
    display: flex;
    padding: 10%;
    flex-direction: column;
    align-items: left;
  }


</style>
