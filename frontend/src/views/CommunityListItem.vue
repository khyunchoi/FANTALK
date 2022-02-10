<template>
  <div>
    <!-- <img src="" alt=""> -->
    <div>
      <h2 style="padding: 2% 0 2% 16%; font-size: 1.5em;">커뮤니티 게시글 목록</h2>

      <!-- <tr
        v-for="article in articles" :key="article"
        @click="enterDetailArticle(article.articleId)"
      >
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>
        <td style="text-align: center;"></td>


      </tr> -->

      <v-data-table
        :headers="headers"
        :items="articles"
        :items-per-page="5"
        class="elevation-1"
        @click:row="enterDetailArticle()"
      >
        <template v-slot:items="props">
          <td style="text-align: center;"></td>
          <td style="text-align: center;">{{ props.item.title }}</td>
          <td style="text-align: center;"></td>
          <td style="text-align: center;"></td>
        </template>
      </v-data-table>
      

    </div>

    <hr>

    <div class="community-list-articles-container">

      <h3>{{ communityId }}번 게시판</h3>
      <br>
      <br>
      <hr>

      <!-- <button
        v-for="article in articles" :key="article"
        style="padding: 1%; border-style: solid; border-width: 2px; margin: 5px"
        @click="enterDetailArticle(article.articleId)"
      >
        {{ article.articleId }} | {{ article.title }} | {{ article.createdAt.slice(0,10) }} | 조회수 : {{ article.hits }}
      </button> -->
      <br>
      <div style="">
        <button @click="enterCreateArticle()" style="padding: 3%; border-style: solid; border-width: 2px;">
          글쓰기
        </button>
      </div>
      

    </div>


    

  </div>
</template>

<script>
  export default {
    name: 'CommunityListItem',
    components: {
    },
    data: function() {
      return {
        communityId: null,
        communityName: null,
        articles: [],
        headers: [
          { 
            text: '', 
            value: 'articleId',
            sortable: false,
          },
          { 
            text: '제목', 
            value: 'title',
            sortable: false,
          },
          { 
            text: '작성날짜', 
            value: 'createdAt',
            sortable: false,
          },
          {
            text: '조회수',
            value: 'hits',
            sortable: false,
          },
        ],
      }
    },
    methods: {
      // goBack () {
      //   this.$router.push({ name: 'CommunityList', params: {communityId: this.communityId} })
      // },
      enterDetailArticle: function (idx) {
        console.log('aa')
        this.$router.push({name: 'DetailArticle', params:{ articleId:idx }})
        this.$router.push({name: 'DetailArticle', params:{ communityId:this.communityId }})
        DetailArticle.data.push({articleId:idx})
        DetailArticle.data.push({communityId:this.communityId})
      },
      enterCreateArticle: function () {
        this.$router.push({name: 'CreateArticle'})

      }
    },
    created: function () {
      this.communityId = this.$route.params.communityId
      console.log(this.communityId)
      this.$axios({
        method:'get',
        url: `http://localhost:8080/api/v1/communities/${this.communityId}/articles`
      })
      .then(res => {
        return res.data
      })
      .then(res => {
        this.articles = res
        console.log(this.articles)
        return this.articles
      })
      .catch(error => {
          console.log(error)
      })
      .finally(function () {
        console.log('done')
      })
    }
  }
</script>

<style>
  .community-list-articles-container {
    width: 100%;
    display: flex;
    padding: 5%;
    flex-direction: column;
    align-items: left;
  }
</style>